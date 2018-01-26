package service.devuser;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pojo.Dev_user;

import dao.devuser.Dev_userMapper;

@Service("dev_userService")
public class Dev_userServiceImpl implements Dev_userService {

	@Resource
	private Dev_userMapper dev_userMapper;

	public Dev_user Landing(String userCode, String password) {

		return dev_userMapper.Landing(userCode, password);
	}

}
