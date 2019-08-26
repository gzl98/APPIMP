package com.dao;

import com.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    User getUserByPhone(@Param("user_phone")String userPhone);

}
