package edu.hw2.task4;

public record CallingInfo(String className, String methodName) {
    public static String callingInfo() {
        try {
            throw new MyException();
        } catch (MyException e) {
            CallingInfo callingInfo = new CallingInfo(
                e.getStackTrace()[0].getClassName(),
                e.getStackTrace()[0].getMethodName()
            );
            return callingInfo.toString();
        }
    }

    @Override
    public String toString() {
        return "public record " + className + "(" + methodName + ")";
    }
}
