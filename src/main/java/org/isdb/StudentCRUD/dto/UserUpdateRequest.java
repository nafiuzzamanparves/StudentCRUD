package org.isdb.StudentCRUD.dto;

import jakarta.validation.constraints.Email;
import org.isdb.StudentCRUD.constants.Role;

public record UserUpdateRequest(
        @Email(message = "Email should be valid")
        String email,

        Role role,
        String firstName,
        String lastName,
        String phoneNumber
) {
}