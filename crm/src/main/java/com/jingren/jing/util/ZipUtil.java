package com.jingren.jing.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtil {
	 public static void main(String args[]) {
	        String file = "D://hehe.zip";
	        String saveRootDirectory="D://test/";
	        zipFileRead(file,saveRootDirectory);
	        List<String> filenames=  get_filename(file);
	        System.out.println(filenames.toString());
	    }
	private static void zipFileRead(String file,String saveRootDirectory) {
        try {
            ZipFile zipFile = new ZipFile(file);
            @SuppressWarnings("unchecked")
            Enumeration<ZipEntry> enu = (Enumeration<ZipEntry>) zipFile.entries();
            while (enu.hasMoreElements()) {
                ZipEntry zipElement = (ZipEntry) enu.nextElement();
                InputStream read = zipFile.getInputStream(zipElement);
                String fileName = zipElement.getName();
                if (fileName != null && fileName.indexOf(".") != -1) {//是否为文件 （文件带有路径如：/images/a.jpg）
                    execute(zipElement,read,saveRootDirectory);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	/**
	* @Title: ZipUtil.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO 获取文件明列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月6日 上午11:10:04 
	* @version 网校+CRM系统 V1.0
	 */
	public static List<String> get_filename(String file){
		List<String> filenames=new ArrayList<>();
	    try {
            ZipFile zipFile = new ZipFile(file);
            @SuppressWarnings("unchecked")
            Enumeration<ZipEntry> enu = (Enumeration<ZipEntry>) zipFile.entries();
            while (enu.hasMoreElements()) {
                ZipEntry zipElement = (ZipEntry) enu.nextElement();
                InputStream read = zipFile.getInputStream(zipElement);
                String fileName = zipElement.getName().substring(0,zipElement.getName().lastIndexOf("."));
                filenames.add(fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return filenames;
	}
	
    private static void execute(ZipEntry ze, InputStream read,String saveRootDirectory)
            throws FileNotFoundException, IOException {
    	DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String format1 = df.format(new Date());
		Random t = new Random();
		for (int i = 0; i < 3; i++) {
			format1 += t.nextInt(10);
		}
		String prefix = ze.getName().substring(ze.getName().lastIndexOf("."));
    	//如果只读取图片，自行判断就OK.
        //String fileName = ze.getName();
//        if(fileName.lastIndexOf(".jpg")!= -1 || fileName.lastIndexOf(".bmp")!= -1 
//            || fileName.lastIndexOf(".jpeg")!= -1){//指定要解压出来的文件格式（这些格式可抽取放置在集合或String数组通过参数传递进来，方法更通用）
            File file = new File(saveRootDirectory + format1+prefix);
        	//File afile = new File(Path + filePath + "/" + filename);
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
//        }
    }
}
