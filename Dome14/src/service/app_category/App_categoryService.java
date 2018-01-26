package service.app_category;

import java.util.List;

import pojo.App_category;

public interface App_categoryService {
	
	List<App_category> getCategory(int parentId);
}
