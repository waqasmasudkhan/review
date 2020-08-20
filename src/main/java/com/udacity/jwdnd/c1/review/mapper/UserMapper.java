package com.udacity.jwdnd.c1.review.mapper;

import com.udacity.jwdnd.c1.review.model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM users WHERE username=#{userName};")
    Users getUser(String username);

    @Select("SELECT * from users WHERE username=#{username};")
    String getUsername(String username);

    @Insert("INSERT INTO users (userName, salt, password, firstname, lastname) VALUES(#{userName}, #{salt}, #{password}, #{firstName}, #{lastName})")
    int addUser(Users user);

}
