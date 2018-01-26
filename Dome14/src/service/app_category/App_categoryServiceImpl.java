package service.app_category;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.app_category.App_categoryMapper;

import pojo.App_category;

@Service("app_categoryService")
public class App_categoryServiceImpl implements App_categoryService {
	@Resource
	private App_categoryMapper app_categoryMapper;

	public List<App_category> getCategory(int parentId) {
		// TODO Auto-generated method stub
		return app_categoryMapper.getCategory(parentId);
	}

}
