package com.czareg.controller;

import lombok.Data;
import lombok.NonNull;

@Data
public class Error {
    @NonNull
    private String message;

    private final String advice = "See logs for stacktrace or contact Cezary Witkowski.";
}