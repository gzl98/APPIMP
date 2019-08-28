package com.service.impl;

import com.dao.BackendUserDao;
import com.pojo.APPCategory;
import com.pojo.APPInfo;
import com.pojo.BackendUser;
import com.service.BackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackendUserServiceImpl implements BackendUserService {

    private final BackendUserDao backendUserDao;

    @Autowired
    public BackendUserServiceImpl(BackendUserDao backendUserDao) {
        this.backendUserDao = backendUserDao;
    }


    /**
     * 通过账号获取管理员信息
     *
     * @param userCode 账号
     * @return 管理员对象
     */
    @Override
    public BackendUser getBackendUserByCode(String userCode) {
        return backendUserDao.getBackendUserByCode(userCode);
    }

    /**
     * 通过目录名获取下一级目录的列表
     *
     * @param categoryName 目录名
     * @return 下一级目录的List
     */
    public List<APPCategory> getChildAPPCategoryList(String categoryName) {
        return backendUserDao.getChildAPPCategoryList(categoryName);
    }


    /**
     * 综合查询APP信息
     *
     * @param appInfo 查询条件的包装对象
     * @return 符合条件的集合
     */
    public List<APPInfo> getAPPListByAttr(APPInfo appInfo) {
        return backendUserDao.getAPPListByAttr(appInfo);
    }

}
