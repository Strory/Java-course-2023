package edu.hw11;

import edu.hw11.task3.NewClass;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Проверка создания нового класса")
    void testAppendClass() throws InstantiationException, IllegalAccessException, NoSuchMethodException,
        InvocationTargetException {

        NewClass appendClass = new NewClass();

        Object obj = new ByteBuddy()
            .subclass(Object.class)
            .name("FibClass")
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameter(int.class, "n")
            .intercept(new Implementation.Simple(appendClass))
            .modifiers(Opcodes.ACC_PUBLIC)
            .make()
            .load(Task3Test.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
            .getLoaded()
            .newInstance();

        assertThat(8L).isEqualTo(obj.getClass().getMethod("fib", int.class).invoke(obj, 6));
    }
}
