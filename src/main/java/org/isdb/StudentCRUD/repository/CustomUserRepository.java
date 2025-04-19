package org.isdb.StudentCRUD.repository;

import org.isdb.StudentCRUD.model.CustomUser;

public interface CustomUserRepository {
    CustomUser findCustomUserByEmail(String email);
}
