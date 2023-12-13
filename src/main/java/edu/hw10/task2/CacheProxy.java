package edu.hw10.task2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CacheProxy implements InvocationHandler {

    private Logger logger = Logger.getLogger("CacheProxy");
    private final String pathName = "temp";
    private final Object target;
    private final Map<String, Object> cache;

    private CacheProxy(Object target) {
        this.target = target;
        this.cache = new HashMap<>();
    }

    public static <T> T create(Object target, Class<T> inputClass) {
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        CacheProxy handler = new CacheProxy(target);
        return (T) Proxy.newProxyInstance(classLoader, interfaces, handler);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cacheAnnotation = method.getAnnotation(Cache.class);
        if (cacheAnnotation != null) {
            if (!cacheAnnotation.persist() || loadFromDisk(method, args)) {
                return cache.get(method.getName());
            }
        }

        Object result = method.invoke(target, args);

        if (cacheAnnotation != null) {
            cache.put(method.getName(), result);
            if (cacheAnnotation.persist()) {
                saveToDisk(method, args, result);
            }
        }

        return result;
    }

    private boolean loadFromDisk(Method method, Object[] args) {
        String fileName = generateFileName(method, args);

        File tempDir = new File(pathName);
        tempDir.mkdirs();

        Path filePath = tempDir.toPath().resolve(fileName);
        if (Files.exists(filePath)) {
            try {
                byte[] data = Files.readAllBytes(filePath);
                Object result = deserialize(data);
                cache.put(fileName, result);

                return true;
            } catch (IOException | ClassNotFoundException e) {
                logger.severe(e.getMessage());
            }
        }

        return false;
    }

    private void saveToDisk(Method method, Object[] args, Object result) {
        String fileName = generateFileName(method, args);

        File tempDir = new File(pathName);
        tempDir.mkdirs();

        Path filePath = tempDir.toPath().resolve(fileName);

        try {
            byte[] data = serialize(result);

            Files.write(filePath, data, StandardOpenOption.CREATE);
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }

    private String generateFileName(Method method, Object[] args) {
        StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(method.getName());
        fileNameBuilder.append("_");

        for (Object arg : args) {
            fileNameBuilder.append(arg.hashCode());
            fileNameBuilder.append("_");
        }

        return fileNameBuilder.toString();
    }

    private byte[] serialize(Object object) throws IOException {
        try (ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
             ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput)) {
            objectOutput.writeObject(object);
            return byteOutput.toByteArray();
        }
    }

    private Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteInputStream = new ByteArrayInputStream(data);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream)) {
            return objectInputStream.readObject();
        }
    }
}
