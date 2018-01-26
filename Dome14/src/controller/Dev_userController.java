package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pojo.App_category;
import pojo.App_info;
import pojo.App_version;
import pojo.Data_dictionary;
import pojo.Dev_user;

import service.app_category.App_categoryService;
import service.app_info.App_infoService;
import service.app_version.App_versionService;
import service.data_dictionary.Data_dictionaryService;
import service.devuser.Dev_userService;
import tools.PageSupport;

@Controller
@ControllerAdvice
public class Dev_userController {

	@Resource
	private Dev_userService dev_userService;
	@Resource
	private App_infoService app_infoService;
	@Resource
	private App_categoryService app_categoryService;
	@Resource
	private Data_dictionaryService data_dictionaryService;
	@Resource
	private App_versionService app_versionService;
	// 创建分类实体
	App_info appinfo = new App_info();

	/**
	 * 登陆界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/devlogin.html")
	public String getLanding() {
		return "../../devlogin";
	}

	/**
	 * 主页界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "sys/devmain.html")
	public String getTheMain() {
		return "developer/main";
	}

	/**
	 * 用户登陆的具体方法
	 * 
	 * @param userCode
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/denglu.html", method = RequestMethod.POST)
	public String setLanding(@RequestParam(value = "devCode") String userCode,
			@RequestParam(value = "devPassword") String password,
			HttpSession session) {
		Dev_user devuser = dev_userService.Landing(userCode, password);
		if (devuser != null) {
			session.setAttribute("devUserSession", devuser);
			session.setAttribute("categoryLevel1List",
					app_categoryService.getCategory(0));
			session.setAttribute("statusList",
					data_dictionaryService.getDictionary("APP_STATUS"));
			session.setAttribute("flatFormList",
					data_dictionaryService.getDictionary("APP_FLATFORM"));
			return "redirect:sys/devmain.html";
		}
		return "redirect:/devlogin.html";
	}

	/**
	 * app维护界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "sys/list")
	public String maintenanceInterface(
			@RequestParam(value = "pageIndex", required = false) String pageIndex,
			@RequestParam(value = "querySoftwareName", required = false) String softwareName,
			@RequestParam(value = "queryStatus", required = false) String queryStatus,
			@RequestParam(value = "queryFlatformId", required = false) String queryFlatformId,
			@RequestParam(value = "queryCategoryLevel1", required = false) String queryCategoryLevel1,
			@RequestParam(value = "queryCategoryLevel2", required = false) String queryCategoryLevel2,
			@RequestParam(value = "action", required = false) String action,
			Model model) {
		if (pageIndex == null) {
			pageIndex = "1";
		} else if (null == queryCategoryLevel1
				|| queryCategoryLevel1.equals("")) {
			queryCategoryLevel1 = "0";
		} else if (null == queryCategoryLevel2
				|| "".equals(queryCategoryLevel2)) {
			queryCategoryLevel2 = "0";
		}
		if (action != null) {
			appinfo.setSoftwareName(softwareName);
			appinfo.setStatus(Integer.parseInt(queryStatus));
			appinfo.setFlatformId(Integer.parseInt(queryFlatformId));
			appinfo.setCategoryLevel1(Integer.parseInt(queryCategoryLevel1));
			appinfo.setCategoryLevel2(Integer.parseInt(queryCategoryLevel2));
		}
		PageSupport page = new PageSupport();
		page.setPageSize(3);
		page.setPageCountNo(app_infoService.getInfoCount(appinfo));
		page.setCurrentPageNo(Integer.parseInt(pageIndex));
		page.setAll(app_infoService.getInfoCount(appinfo));
		page.setPageShi((page.getCurrentPageNo() - 1) * page.getPageSize());
		List<App_info> list = app_infoService.getInfo(appinfo,
				page.getPageShi(), page.getPageSize());
		model.addAttribute("appInfoLists", list);
		model.addAttribute("appInfoList", page);
		return "developer/appinfolist";
	}

	/**
	 * 二三级菜单连动
	 */
	@RequestMapping(value = "sys/categorylevellist.json", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object Classification(
			@RequestParam(value = "pid", required = false) String yi) {
		List<App_category> list = new ArrayList<App_category>();
		if (null != yi && !"".equals(yi)) {
			list = app_categoryService.getCategory(Integer.parseInt(yi));
		} else if (yi == null || "".equals(yi)) {
			list = app_categoryService.getCategory(0);
		}
		return JSONArray.toJSONString(list);
	}

	@RequestMapping(value = "sys/datadictionarylist.json", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object datadictionarylist() {
		List<Data_dictionary> list = data_dictionaryService
				.getDictionary("APP_FLATFORM");
		return JSONArray.toJSONString(list);
	}

	@RequestMapping(value = "sys/add")
	public String getAddapp_info() {
		return "developer/appinfoadd";
	}

	@RequestMapping(value = "sys/appinfoaddsave", method = RequestMethod.POST)
	public String setAddapp_info(
			App_info app_info,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "a_logoPicPath", required = false) MultipartFile attach) {
		String idPicPath = "";
		if (!attach.isEmpty()) {
			String path = request.getSession().getServletContext()
					.getRealPath("statics" + File.separator + "Uploadfiles");
			System.out
					.println("===========================================Path:"
							+ path);
			String oldFileName = attach.getOriginalFilename();
			System.out
					.println("===========================================oldFileName:"
							+ oldFileName);
			idPicPath = path + File.separator + oldFileName;
			System.out
					.println("===========================================idPicPath:"
							+ idPicPath);
		}
		app_info.setLogoPicPath(idPicPath);
		if (app_infoService.getAdd(app_info) >= 1) {
			return "redirect:list";
		}
		return "sys/add";
	}

	@RequestMapping(value = "sys/appview/{appinfoid}")
	public String Toview(@PathVariable String appinfoid, Model model) {
		App_info app_info = app_infoService.getDan(Integer.parseInt(appinfoid));
		String[] zhu = app_info.getLogoPicPath().split("\\\\");
		app_info.setLogoPicPath("/Dome14/statics/images/" + zhu[zhu.length - 1]);
		List<App_version> list = app_versionService.getApp_version(Integer
				.parseInt(appinfoid));
		model.addAttribute("appInfo", app_info);
		model.addAttribute("appVersionList", list);
		return "developer/appinfoview";
	}

	/**
	 * 进入修改页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sys/appinfomodify")
	public String modify(@RequestParam(value = "id") Integer id, Model model) {
		App_info app_info = app_infoService.getDan(id);
		String[] zhu = app_info.getLogoPicPath().split("\\\\");
		app_info.setLogoPicPath("../statics/images/" + zhu[zhu.length - 1]);
		model.addAttribute("appInfo", app_info);
		return "developer/appinfomodify";
	}

	@RequestMapping(value = "sys/appinfomodifysave")
	public String setModify(
			App_info app_info,
			@RequestParam(value = "attach", required = false) MultipartFile attach,
			HttpServletRequest request, HttpSession session) {
		String idPicPath = "";
		if (!attach.isEmpty()) {
			String path = request.getSession().getServletContext()
					.getRealPath("statics" + File.separator + "Uploadfiles");
			System.out
					.println("===========================================Path:"
							+ path);
			String oldFileName = attach.getOriginalFilename();
			System.out
					.println("===========================================oldFileName:"
							+ oldFileName);
			idPicPath = path + File.separator + oldFileName;
			System.out
					.println("===========================================idPicPath:"
							+ idPicPath);
			app_info.setLogoPicPath(idPicPath);
		}
		int i = app_infoService.getUpdate(app_info);
		if (i >= 1) {
			System.out.println("====================================>修改成功");
			return "redirect:list";
		} else {
			return "sys/appinfomodify";
		}
	}

	@RequestMapping(value = "sys/delfile.json")
	@ResponseBody
	public Object getDelectTu(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "flag") String flag) {
		String kong = "";
		int i = app_infoService.getQing(kong, id);
		if (i >= 1) {
			kong = "success";
		} else {
			kong = "failed";
		}
		return JSON.toJSONString(kong);
	}
	@RequestMapping(value="sys/appversionadd")
	public String getAddversion(@RequestParam(value="id")Integer id,Model model){
		List<App_version> list = app_versionService.getApp_version(id);
		model.addAttribute("appVersionList", list);
		return "developer/appversionadd";
	}
}
