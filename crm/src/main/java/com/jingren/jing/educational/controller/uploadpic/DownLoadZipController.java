package com.jingren.jing.educational.controller.uploadpic;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: DownLoadZipController.java 
* @Package com.jingren.jing.educational.controller.uploadpic 
* @Description: TODO 下载图片并导出
* @author 鲁晓飞 MR.Lu   
* @date 2017年1月19日 上午9:34:09 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.entrysystem.bean.entryinfo.EntryInfo;
import com.jingren.jing.school.entrysystem.service.entryinfo.EntryInfoService;
import com.jingren.jing.util.DeleteFile;
import com.jingren.jing.util.FileCopyCutUtil;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.ZipCompressorUtil;

@Controller
@RequestMapping("down_pic")
public class DownLoadZipController {
	@Resource
	private EntryInfoService entryInfoService;
	private  final String COPY_FRONT_FILE = "/images/copy/";
	private  final String DOWN_FRONT_FILE = "/images/copy";

	/**
	 * @Title: DownLoadZipController.java
	 * @Package com.jingren.jing.educational.controller.uploadpic
	 * @Description: TODO 下载图片
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月19日 上午9:53:50
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/down_entry_info_pic_zip.jr")
	public String down_entry_info_pic_zip(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "entryplanId", required = false) Integer entryplanId,
			@RequestParam(value = "str", required = false) String str,
			@RequestParam(value = "entryinfoid", required = false) Integer entryinfoid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (entryplanId != null) {
			map.put("entryplanId", entryplanId);
		}
		if (entryinfoid != null) {
			map.put("entryInfoId", entryinfoid);
		}
		String base_path = request.getServletContext().getRealPath("/");

		List<EntryInfo> entryInfos = entryInfoService.getEntryInfoList(map);
		if (entryInfos.size() > 0) {
			String[] strarray = str.split(",");
			for (EntryInfo entryInfo : entryInfos) {
				String filename = entryInfo.getEntryUserName() + entryInfo.getDocumentNumber();
				for (String string : strarray) {
					switch (string) {
					case "userpic":
						if (entryInfo.getEntryUserPhoto() != null) {// 免冠照片
							String copy1 = base_path + entryInfo.getEntryUserPhoto();
							String prefix = entryInfo.getEntryUserPhoto()
									.substring(entryInfo.getEntryUserPhoto().lastIndexOf("."));
							String copy2 = base_path + COPY_FRONT_FILE + "MG" + filename + prefix;
							FileCopyCutUtil.copyFile(copy1, copy2);
						}
						break;
					case "sfz":
						if (entryInfo.getUserCardPositive() != null) {// 身份证正面
							String copy1 = base_path + entryInfo.getUserCardPositive();
							String prefix = entryInfo.getUserCardPositive()
									.substring(entryInfo.getUserCardPositive().lastIndexOf("."));
							String copy2 = base_path + COPY_FRONT_FILE + "SFZ" + filename + prefix;
							FileCopyCutUtil.copyFile(copy1, copy2);
						}
						break;
					case "sfb":
						if (entryInfo.getUserCardOpposite() != null) {// 身份证背面
							String copy1 = base_path + entryInfo.getUserCardOpposite();
							String prefix = entryInfo.getUserCardOpposite()
									.substring(entryInfo.getUserCardOpposite().lastIndexOf("."));
							String copy2 = base_path + COPY_FRONT_FILE + "SFB" + filename + prefix;
							FileCopyCutUtil.copyFile(copy1, copy2);
						}
						break;
					case "xl":
						if (entryInfo.getCertificatePic() != null) {// 学历照片
							String copy1 = base_path + entryInfo.getCertificatePic();
							String prefix = entryInfo.getCertificatePic()
									.substring(entryInfo.getCertificatePic().lastIndexOf("."));
							String copy2 = base_path + COPY_FRONT_FILE + "XL" + filename + prefix;
							FileCopyCutUtil.copyFile(copy1, copy2);
						}
						break;
					default:
						break;
					}
				}
//				if (entryInfo.getDocument_photo() != null) {// 证件照片
//					String copy1 = base_path + entryInfo.getDocument_photo();
//					String prefix = entryInfo.getDocument_photo()
//							.substring(entryInfo.getDocument_photo().lastIndexOf("."));
//					String copy2 = base_path + COPY_FRONT_FILE + "ZJ" + filename + prefix;
//					FileCopyCutUtil.copyFile(copy1, copy2);
//				}
			}
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String format1 = df.format(new Date());
			Random t = new Random();
			for (int i = 0; i < 3; i++) {
				format1 += t.nextInt(10);
			}
			boolean flag = ZipCompressorUtil.fileToZip(base_path + DOWN_FRONT_FILE, base_path + DOWN_FRONT_FILE,
					format1);
			if (flag) {// 压缩成功后导出zip
				String filepath = base_path + COPY_FRONT_FILE + format1 + ".zip";
				InputStream in = new FileInputStream(filepath); // 获取文件的流
				OutputStream os = response.getOutputStream();
				String filename = format1 + ".zip";
				int len = 0;
				byte buf[] = new byte[1024];// 缓存作用
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream; charset=UTF-8");
				response.addHeader("Content-Disposition",
						"attachment; filename=\"" + new String(filename.getBytes("GB2312"), "ISO8859-1") + "\";");//
				os = response.getOutputStream();// 输出流
				while ((len = in.read(buf)) > 0) // 切忌这后面不能加 分号 ”;“
				{
					os.write(buf, 0, len);// 向客户端输出，实际是把数据存放在response中，然后web服务器再去response中读取
				}
				in.close();
				os.close();
				DeleteFile.deleteFile1(COPY_FRONT_FILE + filename, request);
			}
		}
		return null;
	}

	/**
	 * @Title: DownLoadZipController.java
	 * @Package com.jingren.jing.educational.controller.uploadpic
	 * @Description: TODO 判断学员信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月19日 下午1:56:26
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_info_pic.jr")
	public void check_info_pic(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "entryplanId", required = false) Integer entryplanId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (entryplanId != null) {
			map.put("entryplanId", entryplanId);
		}
		List<EntryInfo> entryInfos = entryInfoService.getEntryInfoList(map);
		if (entryInfos.size() > 0) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}
}
