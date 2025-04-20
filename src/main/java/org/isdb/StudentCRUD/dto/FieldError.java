package org.isdb.StudentCRUD.dto;

public record FieldError(
        String field,
        String errorCode,
        String errorMessage
) {
}