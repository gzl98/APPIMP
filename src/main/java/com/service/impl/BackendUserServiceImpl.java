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
     * 通过目录Id取下一级目录的列表
     *
     * @param categoryId 目录Id
     * @return 下一级目录的List
     */
    public List<APPCategory> getChildAPPCategoryList(Integer categoryId) {
        return backendUserDao.getChildAPPCategoryList(categoryId);
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

    /**
     * 管理员审核APP
     *
     * @param appInfo 修改条件的包装对象
     * @return 审核是否成功（1：成功，0失败）
     */
    public Integer checkAPP(APPInfo appInfo){
        try {
            backendUserDao.checkAPP(appInfo);
        }catch (Exception e){
            return 0;
        }
        return 1;
    }
}
