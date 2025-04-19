package org.isdb.StudentCRUD.controller;

import org.isdb.StudentCRUD.annotation.CurrentUser;
import org.isdb.StudentCRUD.model.CustomUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public CustomUser user(@CurrentUser CustomUser currentUser) {
        return currentUser;
    }
}
