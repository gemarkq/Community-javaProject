package com.jiankun.community.dao;

import com.jiankun.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user); // 返回插入的数据的行数

    int updateStatus(int id, int status); //返回修改的条数

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);

}
