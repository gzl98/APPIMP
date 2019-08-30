package com.service;

import com.pojo.APPCategory;
import com.pojo.APPInfo;
import com.pojo.BackendUser;

import java.util.List;

public interface BackendUserService {

    /**
     * 通过账号获取管理员信息
     *
     * @param userCode 账号
     * @return 管理员对象
     */
    BackendUser getBackendUserByCode(String userCode);

    /**
     * 通过目录Id取下一级目录的列表
     *
     * @param categoryId 目录Id
     * @return 下一级目录的List
     */
    List<APPCategory> getChildAPPCategoryList(Integer categoryId);

    /**
     * 综合查询APP信息
     *
     * @param appIds appId的集合
     * @param offset 分页的偏移量
     * @param limit 每页的限制条数，即数据库每次的查询条数
     * @return 符合条件的集合
     */
    List<APPInfo> getAPPListByAttr(Integer[] appIds, Integer offset, Integer limit);

    /**
     * 综合查询APP数量
     *
     * @param appInfo 修改条件的包装对象
     * @return 符合条件的APP ID集合
     */
    List<Integer> getAPPCountByAttr(APPInfo appInfo);

    /**
     * 管理员审核APP
     *
     * @param appInfo 修改条件的包装对象
     * @return 审核是否成功（1：成功，0失败）
     */
    Integer checkAPP(APPInfo appInfo);

}
