package com.jingren.jing.crm.controller.call;


import java.util.List;
import javax.xml.namespace.QName;     
import javax.xml.rpc.encoding.XMLType; 
import org.apache.axis.client.Call;  
import org.apache.axis.client.Service;
  

/**
 * @功能： UNCALLCC API java客户端实例
 * @作者： 罗杰
 * @日期: 2013 09 09
 */
public class OnClickCall { 
	
	public static void main(String[] args) { 
		MakeCallOnclick("826","13373413381","122");   	
		
	}	
	
	/**
	 * @功能：点击拨号接口
	 * @作者：罗杰
018681471812
	 * 
	 * @param strExten 主叫
	 * @param strToTel 被叫
	 * @return
	 */
	public static void MakeCallOnclick(String strExten, String str_tel_num, String strActionID){ 
        try { 
        	//接口地址 <SOAP:address location="http://192.168.18.176:6083"/>
            String endpoint = "http://192.168.1.251/uncall_lib/";   
            Service service = new Service();   
            Call call = (Call)service.createCall();   
            call.setTargetEndpointAddress(endpoint);   
            call.setOperationName(new QName("urn:Uncall", "OnClickCall"));   //WSDL里面描述的接口名称如点击拨号
            call.addParameter("strExten", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);//接口的参数 
            call.addParameter("strToTel", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);//接口的参数
            call.addParameter("strActionID", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);//接口的参数
            call.setReturnType(XMLType.XSD_STRING);   
            call.setUseSOAPAction( true );  
            String result = (String)call.invoke(new Object[]{strExten,str_tel_num,strActionID}); 
            System.out.println("response:"+result);    
            if(result.equals("0")){
                List Values = call.getOutputValues(); 
                System.out.println("call-id"+Values.get(0).toString());               	
            }  
	     }
	     catch (Exception e) {  
	        System.out.println(e.toString());    
	     }
	} 
	
}
