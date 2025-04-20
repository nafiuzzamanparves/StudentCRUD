package org.isdb.StudentCRUD.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.isdb.StudentCRUD.constants.Role;

@Getter
@Setter
public class UserUpdateRequest {
    @Email(message = "Email should be valid")
    private String email;

    private Role role;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}