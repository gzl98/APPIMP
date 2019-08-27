package com.service.impl;

import com.dao.BackendUserDao;
import com.service.BackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackendUserServiceImpl implements BackendUserService {

    private final BackendUserDao backendUserDao;

    @Autowired
    public BackendUserServiceImpl(BackendUserDao backendUserDao) {
        this.backendUserDao = backendUserDao;
    }

}
