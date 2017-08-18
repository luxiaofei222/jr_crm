package com.jingren.jing.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Title: ZipCompressorUtil.java
 * @Package com.jingren.jing.util
 * @Description: TODO 压缩文件
 * @author 鲁晓飞 MR.Lu
 * @date 2017年1月19日 上午9:57:58
 * @version 网校+CRM系统 V1.0
 */
public class ZipCompressorUtil {
	 private ZipCompressorUtil(){}  
     
	    /** 
	     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下 
	     * @param sourceFilePath :待压缩的文件路径 
	     * @param zipFilePath :压缩后存放路径 
	     * @param fileName :压缩后文件的名称 
	     * @return 
	     */  
	    public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName){  
	        boolean flag = false;  
	        File sourceFile = new File(sourceFilePath);  
	        FileInputStream fis = null;  
	        BufferedInputStream bis = null;  
	        FileOutputStream fos = null;  
	        ZipOutputStream zos = null;  
	          
	        if(sourceFile.exists() == false){  
	            System.out.println("待压缩的文件目录："+sourceFilePath+"不存在.");  
	        }else{  
	            try {  
	                File zipFile = new File(zipFilePath + "/" + fileName +".zip");  
	                if(zipFile.exists()){  
	                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件.");  
	                }else{  
	                    File[] sourceFiles = sourceFile.listFiles();  
	                    if(null == sourceFiles || sourceFiles.length<1){  
	                        System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");  
	                    }else{  
	                        fos = new FileOutputStream(zipFile);  
	                        zos = new ZipOutputStream(new BufferedOutputStream(fos));  
	                        byte[] bufs = new byte[1024*10];  
	                        for(int i=0;i<sourceFiles.length;i++){  
	                            //创建ZIP实体，并添加进压缩包  
	                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
	                            if(!zipEntry.getName().equals("wuyong")){
	                            	System.out.println("压缩："+zipEntry);
	                            	  zos.putNextEntry(zipEntry);  
	  	                            //读取待压缩的文件并写进压缩包里  
	  	                            fis = new FileInputStream(sourceFiles[i]);  
	  	                            bis = new BufferedInputStream(fis, 1024*10);  
	  	                            int read = 0;  
	  	                            while((read=bis.read(bufs, 0, 1024*10)) != -1){  
	  	                                zos.write(bufs,0,read);  
	  	                            } 
	  	                          zos.closeEntry();
	  	                          bis.close();
	                            }
	                            
	                        }  
	                        flag = true;  
	                    }  
	                    for(int i=0;i<sourceFiles.length;i++){
		                    File file= new File(sourceFilePath+"\\"+sourceFiles[i].getName());
		                    file.delete();
		                 }
		                  zos.closeEntry();
		                  zos.close();
		                  fos.close();
	                } 
	            } catch (FileNotFoundException e) {  
	                e.printStackTrace();  
	                throw new RuntimeException(e);  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	                throw new RuntimeException(e);  
	            }  
	           
	        }  
	        return flag;  
	    }  


	public static void main(String[] args) {
		  String sourceFilePath = "D:\\test";  
	        String zipFilePath = "D:\\test";  
	        String fileName = "12700153file";  
	        boolean flag = fileToZip(sourceFilePath, zipFilePath, fileName);  
	        if(flag){  
	            System.out.println("文件打包成功!");  
	        }else{  
	            System.out.println("文件打包失败!");  
	        }  
	}
}
