package com.mgunawardhana.s3.constant;

public enum StatusCode {
    SUCCESS("0"),

    FAILURE("1");

    private final String value;

    public String valueOf() {
        return this.value;
    }

    StatusCode(String value) {
        this.value = value;
    }
}
