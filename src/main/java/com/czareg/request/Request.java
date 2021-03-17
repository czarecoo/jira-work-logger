package com.czareg.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class Request {
    @NonNull
    private String url;
    @NonNull
    private String email;
    @NonNull
    private String token;
    @NonNull
    private String issueKey;
}