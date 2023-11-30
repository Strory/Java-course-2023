package edu.project3;

public enum ResponseCodes {
    OK(200, "OK"),
    PARTIAL_CONTENT(206, "Partial Content"),
    NOT_MODIFIED(304, "Not Modified"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    RANGE_NOT_SATISFIABLE(416, "Range Not Satisfiable"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String description;

    ResponseCodes(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
