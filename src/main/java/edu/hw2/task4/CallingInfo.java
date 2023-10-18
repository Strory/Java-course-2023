package edu.hw2.task4;

public record CallingInfo() {
    public String callingInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String className = stackTrace[1].getClassName();
        String methodName = stackTrace[1].getMethodName();
        return className + " " + methodName;
    }
}
