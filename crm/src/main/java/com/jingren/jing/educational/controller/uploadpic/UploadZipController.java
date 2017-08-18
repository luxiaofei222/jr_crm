package com.jingren.jing.educational.controller.uploadpic;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.school.entrysystem.bean.entryinfo.EntryInfo;
import com.jingren.jing.school.entrysystem.service.entryinfo.EntryInfoService;
import com.jingren.jing.util.DeleteFile;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;

/**
* @Title: UploadZipController.java 
* @Package com.jingren.jing.educational.controller.uploadpic 
* @Description: TODO 解压并读取zip文件
* @author 鲁晓飞 MR.Lu   
* @date 2017年1月6日 上午11:40:21 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("uload_zip")
public class UploadZipController {

	@Resource
	private EntryInfoService entryInfoService;
	private  final String UP_FRONT_FILE ="images/entry";
	private  final String UP_ZIP_FILE ="zip";
	/**
	* @Title: UploadZipController.java 
	* @Package com.jingren.jing.educational.controller.uploadpic 
	* @Description: TODO 导入图片弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月6日 上午11:42:02 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_upload_zip.jr")
	public String to_upload_zip(){
		
		return "/educational/upload/uploadentryinfouserpic";
	}
	/**
	* @Title: UploadZipController.java 
	* @Package com.jingren.jing.educational.controller.uploadpic 
	* @Description: TODO 导入图片
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月6日 下午1:25:26 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/upload_zip_pic.jr")
	public void upload_zip_pic(HttpSession session,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="upfile",required=false) MultipartFile upfile){
	String filePath=request.getServletContext().getRealPath("/");
		String zippath=UploadAddress.getUploadDate(upfile, request, UP_ZIP_FILE);
		Map<String, Object> map=zipFileRead(request,request.getServletContext().getRealPath("")+zippath, filePath+UP_FRONT_FILE+"/");
	Integer suc=(Integer) map.get("suc");
	Integer fail=(Integer) map.get("fail");
	ResponseUtils.renderText(response, "<p style='color: orange;'>恭喜您导入成功，共计成功导入"+suc+"张,失败"+fail+"张，失败原因命名格式错误！</p>");
	System.out.println(zippath);
	DeleteFile.deleteFile1(zippath+"", request);//删除压缩包
	}
	/**
	* @Title: UploadZipController.java 
	* @Package com.jingren.jing.educational.controller.uploadpic 
	* @Description: TODO 读取压缩包里面的文件
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月6日 下午2:29:36 
	* @version 网校+CRM系统 V1.0
	 */
	private  Map<String, Object> zipFileRead(HttpServletRequest request,String file,String saveRootDirectory) {
		int suc=0;
		int fail=0;
        try {
            ZipFile zipFile = new ZipFile(file);
            @SuppressWarnings("unchecked")
            Enumeration<ZipEntry> enu = (Enumeration<ZipEntry>) zipFile.entries();
            while (enu.hasMoreElements()) {
                ZipEntry zipElement = (ZipEntry) enu.nextElement();
                InputStream read = zipFile.getInputStream(zipElement);
                String fileName = zipElement.getName();
                if (fileName != null && fileName.indexOf(".") != -1) {//是否为文件 （文件带有路径如：/images/a.jpg）
                	 Map<String, Object> map_info= execute(request,zipElement,read,saveRootDirectory);
                	 suc+=(Integer) map_info.get("suc");
                	fail+=(Integer) map_info.get("fail");
                }
            }
            zipFile.close();//必须关闭流文件否则无法删除临时zip包
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> map=new HashMap<>();
        map.put("suc", suc);
        map.put("fail", fail);
		return map;
    }
	
	 private  Map<String, Object> execute(HttpServletRequest request,ZipEntry ze, InputStream read,String saveRootDirectory)
	            throws FileNotFoundException, IOException {
	    	DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String format1 = df.format(new Date());
			Random t = new Random();
			for (int i = 0; i < 3; i++) {
				format1 += t.nextInt(10);
			}
			Integer fail=0;
			Integer suc=0;
			String prefix = ze.getName().substring(ze.getName().lastIndexOf("."));
			//如果只读取图片，自行判断就OK.
	        //String fileName = ze.getName();
//	        if(fileName.lastIndexOf(".jpg")!= -1 || fileName.lastIndexOf(".bmp")!= -1 
//	            || fileName.lastIndexOf(".jpeg")!= -1){//指定要解压出来的文件格式（这些格式可抽取放置在集合或String数组通过参数传递进来，方法更通用）
	            File file = new File(saveRootDirectory + format1+prefix);
	        	//File afile = new File(Path + filePath + "/" + filename);
				/************************保存证件照片开始*******************************/
				EntryInfo entryInfo=new EntryInfo();
				String fileName = ze.getName().substring(0,ze.getName().lastIndexOf("."));
				String card_type=fileName.substring(0, 3);//获得图片类型
				String carnumber=fileName.substring(3,fileName.length());
				entryInfo.setDocumentNumber(carnumber);
				String addr = "/" + UP_FRONT_FILE + "/" + format1 + prefix;
				switch (card_type) {
				case "XLZ":
					entryInfo.setCertificatePic(addr);
					suc=1;
					break;
				case "SFZ":
					entryInfo.setUserCardPositive(addr);
					suc=1;
					break;
				case "SFB":
					entryInfo.setUserCardOpposite(addr);
					suc=1;
					break;
				case "XYP":
					entryInfo.setEntryUserPhoto(addr);
					suc=1;
					break;
				case "QTZ":
					entryInfo.setDocument_photo(addr);
					suc=1;
					break;
				default:
					fail=1;
					break;
				}
				/************************保存证件照片结束*******************************/
	    		if (!file.exists()) {
	                File rootDirectoryFile = new File(file.getParent());
	                //创建目录
	                if (!rootDirectoryFile.exists()) {
	                    boolean ifSuccess = rootDirectoryFile.mkdirs();
	                    if (ifSuccess) {
	                        System.out.println("文件夹创建成功!");
	                    } else {
	                        System.out.println("文件创建失败!");
	                    }
	                }
	                //创建文件
	                try {
	                    file.createNewFile();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            //写入文件
	            BufferedOutputStream write = new BufferedOutputStream(new FileOutputStream(file));
	            int cha = 0;
	            while ((cha = read.read()) != -1) {
	                write.write(cha);
	            }
	            //要注意IO流关闭的先后顺序
	            write.flush();
	            write.close();
	            read.close();
//	        }
	            try {
					entryInfoService.updateEntryInfoPic(entryInfo);
					suc=1;
				} catch (Exception e) {
					DeleteFile.deleteFile1(addr, request);
					fail=1;
				}
	            Map<String, Object> map=new HashMap<>();
	            map.put("suc", suc);
	            map.put("fail", fail);
				return map;
	    }
}
