package com.jingren.jing.goeasy;

import com.uwantsoft.goeasy.client.goeasyclient.GoEasy;

/**
* @Title: GoEasyPush.java 
* @Package com.jingren.jing.goeasy 
* @Description: TODO 后台管理系统推送
* @author 鲁晓飞 MR.Lu    
* @date 2017年5月12日 上午10:44:12 
* @version 网校+CRM系统 V1.0
 */
public class GoEasyPush {

	public static void main(String[] args) {
		GoEasy goEasy = new GoEasy("BC-5341e251aab445a192f79c538832335c");
		goEasy.publish("luxiaofei","测试推送");
	}
	
	/**
	* @Title: GoEasyPush.java 
	* @Package com.jingren.jing.goeasy 
	* @Description: TODO 发送系统通知
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月12日 上午10:45:36 
	* @version 网校+CRM系统 V1.0
	 */
	public static int push_message(String channel ,String content){
		GoEasy goEasy = new GoEasy("BC-5341e251aab445a192f79c538832335c");
		goEasy.publish(channel,content);
		return 0;
	}
}
