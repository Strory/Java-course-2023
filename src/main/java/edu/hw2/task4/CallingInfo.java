package edu.hw2.task4;

public record CallingInfo(String className, String methodName) {
    public static String callingInfo() {
        try {
            throw new MyException();
        } catch (MyException error) {
            CallingInfo callingInfo = new CallingInfo(
                error.getStackTrace()[0].getClassName(),
                error.getStackTrace()[0].getMethodName()
            );
            return callingInfo.toString();
        }
    }

    @Override
    public String toString() {
        return "public record " + className + "(" + methodName + ")";
    }
}
