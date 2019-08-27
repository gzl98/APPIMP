package com.controller;

import com.service.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/DevUser")
public class DevUserController {

    private final DevUserService devUserService;

    @Autowired
    public DevUserController(DevUserService devUserService) {
        this.devUserService = devUserService;
    }

}
