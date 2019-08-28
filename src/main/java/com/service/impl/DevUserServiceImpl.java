package com.service.impl;

import com.dao.DevUserDao;
import com.pojo.BackendUser;
import com.service.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevUserServiceImpl implements DevUserService {

    private final DevUserDao devUserDao;

    @Autowired
    public DevUserServiceImpl(DevUserDao devUserDao) {
        this.devUserDao = devUserDao;
    }

}
