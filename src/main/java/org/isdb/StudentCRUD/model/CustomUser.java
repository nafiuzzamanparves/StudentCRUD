package org.isdb.StudentCRUD.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CustomUser {
    private final long id;

    private final String email;

    @JsonIgnore
    private final String password;

    @JsonCreator
    public CustomUser(
            @JsonProperty("id") long id,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

}
