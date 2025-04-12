package org.isdb.StudentCRUD.model;

public record LoginRequest(
        String username,
        String password
) {
}
