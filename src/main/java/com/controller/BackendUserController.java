package com.controller;

import com.service.BackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/BackendUser")
public class BackendUserController {

    private final BackendUserService backendUserService;

    @Autowired
    public BackendUserController(BackendUserService backendUserService) {
        this.backendUserService = backendUserService;
    }

}
