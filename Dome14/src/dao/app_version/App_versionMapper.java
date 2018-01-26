package dao.app_version;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.App_version;

public interface App_versionMapper {
	/**
	 * 查询单个应用的所有版本
	 * @param id
	 * @return
	 */
	List<App_version> getApp_version(@Param("appid")int id);
	/**
	 * 添加版本
	 * @param app_version
	 * @return
	 */
	int getAddversion(@Param("version")App_version app_version);

}
