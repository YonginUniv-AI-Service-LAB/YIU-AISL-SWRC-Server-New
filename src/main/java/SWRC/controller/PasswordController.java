package SWRC.controller;

import SWRC.service.UserService;  // <- UserService의 위치 확인
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    private UserService userService;
}
