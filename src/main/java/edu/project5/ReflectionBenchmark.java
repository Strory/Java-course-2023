package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class ReflectionBenchmark {

    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Function<Student, String> lambdaFunction;

    private void lambdaFunctionInit(MethodHandles.Lookup lookup) throws Throwable {
        CallSite site = LambdaMetafactory.metafactory(
            lookup,
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            methodHandle,
            methodHandle.type()
        );
        lambdaFunction = (Function<Student, String>) site.getTarget().invokeExact();
    }

    @SuppressWarnings("MultipleStringLiterals")
    @Setup
    public void setup() throws Throwable {
        student = new Student("SomeName", "SomeSurname");
        method = Student.class.getMethod("name");
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, "name", MethodType.methodType(String.class));
        lambdaFunctionInit(lookup);
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandles(Blackhole bh) throws Throwable {
        String name = (String) methodHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetaFactory(Blackhole bh) {
        String name = lambdaFunction.apply(student);
        bh.consume(name);
    }
}
