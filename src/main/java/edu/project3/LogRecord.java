package edu.project3;

import java.time.OffsetDateTime;

public record LogRecord(String address, OffsetDateTime date, String requestType,
                        String resource, String protocol, int code, long size, String userAgent) {
}
