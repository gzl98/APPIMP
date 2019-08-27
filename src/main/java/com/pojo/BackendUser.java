package com.pojo;

import java.util.Date;

/**
 * 管理员类
 */
public class BackendUser {

    private Integer backednId;  //管理员id
    private String userCode;     //账号
    private String userName;     //管理员名称
    private String userPassword; //密码
    private String userType;    //管理员类别
    private Integer createdBy;  //创建者
    private Date creationDate;  //创建日期
    private Integer modifyBy;   //更新者
    private Date modifyDate;    //更新日期

    public Integer getBackednId() {
        return backednId;
    }

    public void setBackednId(Integer backednId) {
        this.backednId = backednId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
