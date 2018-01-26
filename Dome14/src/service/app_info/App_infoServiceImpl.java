package service.app_info;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import dao.app_info.App_infoMapper;
import pojo.App_info;

@Service("app_infoService")
public class App_infoServiceImpl implements App_infoService {
	@Resource
	private App_infoMapper app_infoMapper;
	public List<App_info> getInfo(App_info app_info,int ye,int tiao) {
		// TODO Auto-generated method stub
		return app_infoMapper.getInfo(app_info,ye,tiao);
	}
	public int getInfoCount(App_info app_info) {
		// TODO Auto-generated method stub
		return app_infoMapper.getInfoCount(app_info);
	}
	public int getAdd(App_info app_info) {
		// TODO Auto-generated method stub
		return app_infoMapper.getAdd(app_info);
	}
	public App_info getDan(int id) {
		// TODO Auto-generated method stub
		return app_infoMapper.getDan(id);
	}
	public int getUpdate(App_info app_info) {
		// TODO Auto-generated method stub
		return app_infoMapper.getUpdate(app_info);
	}
	public int getQing(String kong, int id) {
		// TODO Auto-generated method stub
		return app_infoMapper.getQing(kong, id);
	}

}
