package service.app_info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.App_info;

public interface App_infoService {
	/**
	 * 查询列表
	 * @return
	 */
	List<App_info> getInfo(App_info app_info,int ye,int tiao);
	/**
	 * 查询总条数
	 */
	int getInfoCount(@Param("appinfo")App_info app_info);
	/**
	 * 用户新增
	 * @param app_info
	 * @return
	 */
	int getAdd(@Param("app_info")App_info app_info);
	/**
	 * 查看单个应用
	 * @param id
	 * @return
	 */
	App_info  getDan(@Param("id")int id);
	/**
	 * 用户修改
	 * @param app_info
	 * @return
	 */
	int getUpdate(@Param("app_info")App_info app_info);
	/**
	 * 清空照片路径
	 * @param kong
	 * @param id
	 * @return
	 */

	int getQing(@Param("kong")String kong,@Param("id")int id);
}
