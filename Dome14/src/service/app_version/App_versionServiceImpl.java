package service.app_version;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.app_version.App_versionMapper;

import pojo.App_version;

@Service("app_versionService")
public class App_versionServiceImpl implements App_versionService{
	@Resource
	private App_versionMapper app_versionMapper;

	public List<App_version> getApp_version(int id) {
		// TODO Auto-generated method stub
		return app_versionMapper.getApp_version(id);
	}

	public int getAddversion(App_version app_version) {
		// TODO Auto-generated method stub
		return app_versionMapper.getAddversion(app_version);
	}

}
