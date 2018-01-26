package dao.devuser;

import org.apache.ibatis.annotations.Param;

import pojo.Dev_user;

public interface Dev_userMapper {

	Dev_user Landing(@Param("userCode")String userCode,@Param("password")String password);

}
