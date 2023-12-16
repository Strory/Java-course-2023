package edu.hw11;

import edu.hw11.task2.ArithmeticUtils;
import edu.hw11.task2.SumInterceptor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Проверка замены метода")
    public void testReplacingMethod() {
        TypeDescription typeDescription = TypePool.Default.ofSystemLoader()
            .describe("edu.hw11.task2.ArithmeticUtils")
            .resolve();

        new ByteBuddy()
            .redefine(typeDescription, ClassFileLocator.ForClassLoader.ofSystemLoader())
            .method(named("sum"))
            .intercept(MethodDelegation.to(SumInterceptor.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);

        assertThat(15).isEqualTo(ArithmeticUtils.sum(5, 3));
    }
}
