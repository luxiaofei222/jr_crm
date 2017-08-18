package com.jingren.jing.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hyperic.sigar.SigarException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingren.jing.util.RuntimeUtil;
import com.jingren.jing.util.SigarInfoEntity;

/**
 * @Title: SystemInfoController.java
 * @Package com.jingren.jing.http
 * @Description: TODO 系统数据统计
 * @author 鲁晓飞 MR.Lu
 * @date 2017年7月28日 下午4:13:26
 * @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_system")
public class SystemInfoController {

	/**
	 * @Title: SystemInfoController.java
	 * @Package com.jingren.jing.http
	 * @Description: TODO 获取虚拟机信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年7月28日 下午4:17:44
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_jvm_info.jr")
	public String get_jvm_info(Model model) {
		List<SigarInfoEntity> sigarInfos = new ArrayList<>();
		try {
			// 获取系统信息和jvm虚拟机信息
			sigarInfos.addAll(RuntimeUtil.getJvmInfos());
			model.addAttribute("sigarInfos", sigarInfos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/system/jvm_info";
	}
	/**
	* @Title: SystemInfoController.java 
	* @Package com.jingren.jing.http 
	* @Description: TODO cpu监控信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月31日 上午9:07:09 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_cpu_info.jr")
	public String get_cpu_info(){
		
		return "/system/cpu_info";
	}
	
	/**
	* @Title: SystemInfoController.java 
	* @Package com.jingren.jing.http 
	* @Description: TODO 获取cpu使用率
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月31日 上午11:03:57 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/get_cpu_shiyong.jr")
	public Map<String, Object> get_cpu_shiyong() throws SigarException{
		Map<String, Object> map=new HashMap<>();
		List<SigarInfoEntity> sigarInfos = new ArrayList<>();
		sigarInfos.addAll(RuntimeUtil.getCpuInfosUsepoint());
		for (SigarInfoEntity sigarInfoEntity : sigarInfos) {
			switch (sigarInfoEntity.getName()) {
			case "cupuse":
				map.put("cupuse", sigarInfoEntity.getValue());
				break;
			case "kongxian":
				map.put("kongxian", sigarInfoEntity.getValue());
				break;

			default:
				map.put("zongshiyong", sigarInfoEntity.getValue());
				break;
			}
		}
		return map;
	}
	
	/**
	* @Title: SystemInfoController.java 
	* @Package com.jingren.jing.http 
	* @Description: TODO 获取内存剩余
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月31日 下午4:10:38 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/get_jvm_neicun_info.jr")
	public Map<String, Object> get_jvm_neicun_info() throws Exception{
		Map<String, Object> map=new HashMap<>();
		List<SigarInfoEntity> sigarInfos = new ArrayList<>();
		sigarInfos.addAll(RuntimeUtil.getMemoryInfos_jvmneicun());
		for (SigarInfoEntity sigarInfoEntity : sigarInfos) {
			switch (sigarInfoEntity.getName()) {
			case "yishiyong":
				map.put("yishiyong", sigarInfoEntity.getValue());
				break;
			case "yishiyongnum":
				map.put("yishiyongnum", sigarInfoEntity.getValue());
				break;
			case "shengyunum":
				map.put("shengyunum", sigarInfoEntity.getValue());
				break;
			default:
				map.put("shengyu", sigarInfoEntity.getValue());
				break;
			}
		}
		return map;
	}
	/**
	* @Title: SystemInfoController.java 
	* @Package com.jingren.jing.http 
	* @Description: TODO CPU内存监测
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月31日 下午5:05:02 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/get_cpu_neicun_info.jr")
	public Map<String, Object> get_cpu_neicun_info() throws Exception{
		Map<String, Object> map=new HashMap<>();
		List<SigarInfoEntity> sigarInfos = new ArrayList<>();
		sigarInfos.addAll(RuntimeUtil.getMemoryInfos_neicun());
		for (SigarInfoEntity sigarInfoEntity : sigarInfos) {
			switch (sigarInfoEntity.getName()) {
			case "yiyong":
				map.put("yiyong", sigarInfoEntity.getValue());
				break;
			case "yishiyongnum":
				map.put("yishiyongnum", sigarInfoEntity.getValue());
				break;
			case "shengyunum":
				map.put("shengyunum", sigarInfoEntity.getValue());
				break;
			default:
				map.put("shengyu", sigarInfoEntity.getValue());
				break;
			}
		}
		return map;
	}
	/**
	* @Title: SystemInfoController.java 
	* @Package com.jingren.jing.http 
	* @Description: TODO 交换区内存监测
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月31日 下午5:44:46 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/get_jiaohuan_neicun_info.jr")
	public Map<String, Object> get_jiaohuan_neicun_info() throws Exception{
		Map<String, Object> map=new HashMap<>();
		List<SigarInfoEntity> sigarInfos = new ArrayList<>();
		sigarInfos.addAll(RuntimeUtil.getMemoryInfos_jiaohuanqu());
		for (SigarInfoEntity sigarInfoEntity : sigarInfos) {
			switch (sigarInfoEntity.getName()) {
			case "yishiyongnum":
				map.put("yishiyongnum", sigarInfoEntity.getValue());
				break;
			default:
				map.put("shengyunum", sigarInfoEntity.getValue());
				break;
			}
		}
		return map;
	}
	/**
	* @Title: SystemInfoController.java 
	* @Package com.jingren.jing.http 
	* @Description: TODO 文件剩余
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月31日 下午5:46:19 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/get_file_neicun_info.jr")
	public Map<String, Object> get_file_neicun_info() throws Exception{
		Map<String, Object> map=new HashMap<>();
		List<SigarInfoEntity> sigarInfos = new ArrayList<>();
		sigarInfos.addAll(RuntimeUtil.getFileInfos());
		for (SigarInfoEntity sigarInfoEntity : sigarInfos) {
			switch (sigarInfoEntity.getName()) {
			case "yiyong":
				map.put("yiyong", sigarInfoEntity.getValue());
				break;
			default:
				map.put("shengyu", sigarInfoEntity.getValue());
				break;
			}
		}
		return map;
	}
	/**
	* @Title: SystemInfoController.java 
	* @Package com.jingren.jing.http 
	* @Description: TODO 获取根路径
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月31日 下午7:10:27 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/get_system_path.jr")
	public Map<String, Object> get_system_path() throws Exception{
		Map<String, Object> map=new HashMap<>();
		String path = System.getProperty("java.library.path");
		map.put("path", path);
		return map;
	}
}
