package com.example.application.data;

import java.time.LocalTime;
import java.util.Set;

public class OrderConfirmation {
    private Set<String> methods;
    private LocalTime time;

    public void setMethods(Set<String> methods) {
        this.methods = methods;
    }

    public Set<String> getMethods() {
        return this.methods;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return this.time;
    }
}
