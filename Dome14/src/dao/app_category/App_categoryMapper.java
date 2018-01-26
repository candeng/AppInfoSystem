package dao.app_category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.App_category;

public interface App_categoryMapper {
	List<App_category> getCategory(@Param("parentId")int parentId);
}
