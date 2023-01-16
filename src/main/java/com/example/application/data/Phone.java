package com.example.application.data;

public class Phone {
    private final String code;
    private final String number;

    public Phone(String code, String number) {
        this.code = code;
        this.number = number;
    }

    public String getCode() {
        return this.code;
    }

    public String getNumber() {
        return this.number;
    }
}
