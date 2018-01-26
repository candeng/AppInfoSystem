package service.devuser;

import org.apache.ibatis.annotations.Param;

import pojo.Dev_user;

public interface Dev_userService {
	
	Dev_user Landing(@Param("userCode")String userCode,@Param("password")String password);

}
