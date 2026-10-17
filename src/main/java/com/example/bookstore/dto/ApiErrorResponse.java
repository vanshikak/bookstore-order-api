package com.example.bookstore.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public record ApiErrorResponse(Integer status,String errorCode,
                               String message, String path, Instant timestamp)
{}
