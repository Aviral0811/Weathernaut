package com.nexus.skyscan.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {
    private String status;
    private String description;
    private Object result;
}
