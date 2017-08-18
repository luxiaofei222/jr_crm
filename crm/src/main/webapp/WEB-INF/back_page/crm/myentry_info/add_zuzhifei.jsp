<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/css/edu/entryinfo/e_tankuang.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>试听录音</title>
<style>
.xiazai{
	width:100px;
	height:35px;
	line-height:35px;
	background-color:#94CE6E;
	border:none;
	margin:0 auto;
	text-align:center;
	display:block;
	color:white;
	border-radius:5px;
}
.audio{
    text-align: center;
    width: 80%;
    margin-left: 10%;
    margin-top: 15px;
}
.xiazai:hover{
	color:white;
}
.form-group {
    height: 42px;
}
</style>
<script>
function check_cailiaofei(){
	var ying_pay=$("#ying_pay").val();
	var cailiaofei=$("#cailiaofei").val();
	var pay_money=$("#pay_money").val();
	if(cailiaofei!=null&&cailiaofei!=""){
		if(isNaN(cailiaofei)){
			layer.msg("请输入数字！");
			return false;
		}else{
			var a =parseInt(ying_pay)+parseInt(cailiaofei);
			if(a>=parseInt(ying_pay)){
				return true;
			}else{
				layer.msg("支付金额不能大于应付金额！");
				return false;
			}
		}	
	}else{
		layer.msg("请输入材料费，没有则为0！");
	}
}
//修改费用
function add_zuzhifei(info_id,ying_pay){
	var zuzhifei=$("#zuzhifei").val();
	var pay_money=$("#pay_money").val();
	var fanfei=$("#fanfei").val();
	var pay_time=$("#pay_time").val();
	var feiyong_shuoming=$("#feiyong_shuoming").val();
	if(isNaN(zuzhifei)&&isNaN(pay_money)&&isNaN(fanfei)){
		layer.msg("请输入数字！");
	}else{
			if(check_cailiaofei()&&check_payMoney()){
				$("#myform").ajaxSubmit({
					type : 'POST',
					url : "/edu_entry/update_zuzhifei.jr",
					data:{
						'entryInfoId':info_id
					},
					success : function(data) {
						if(data==1){
							tanchuang('恭喜，设置成功');
						}else{
							tanchuang('很遗憾，设置失败');
						}
					},
					error : function(XmlHttpRequest, textStatus, errorThrown) {
						tanchuang('很遗憾，设置失败');
					}
				});
			}
	}

}
//计算欠费
function check_payMoney() {
	var payMoney = $("#pay_money").val();
	var yingjiao = $("#ying_pay").val();
	var jiangfei = $("#fanfei").val();
	var cailiaofei=$("#cailiaofei").val();
	if (isNaN(payMoney)) {
		layer.msg("实付金额请输入数字！")
		return false;
	} else {
		if (parseInt(payMoney)> parseInt(yingjiao)+parseInt(cailiaofei)) {
			layer.msg("实付金额不能大于应付金额！")
			return false;
		} else {
			var zong =parseInt(yingjiao)+parseInt(cailiaofei);
			var qianfei =parseInt(zong) - parseInt(payMoney)-parseInt(jiangfei);
			$("#qianfei").val(qianfei);
			return true;
		}
	}
}
</script>
</head>
<body>
<form class="form-horizontal import_xueyuanshuju_dialog"
		enctype="multipart/form-data" id="myform" style="padding: 20px;">
	<div style="padding: 20px;">
	 <div class="form-group">
      <label class="col-xs-3 control-label">应缴费费：</label>
      <div class="col-xs-8">
      <c:if test="${sessionScope.employee_session.employee_id==1 }">
       <input type="text" value="${entryInfo.ying_pay }" id="ying_pay" name="ying_pay" class="form-control">  
      </c:if>
      <c:if test="${sessionScope.employee_session.employee_id!=1 }">
     <%--  <c:if test="${entryPlan.parent_id!=9 }"> --%>
        <input type="text" value="${entryInfo.ying_pay }" id="ying_pay" name="ying_pay" class="form-control" readonly="readonly">  
    <%--   </c:if> --%>
     <%--  <c:if test="${entryPlan.parent_id==9 }">
        <input type="text" value="${entryPlan.baomingfei }" id="ying_pay" name="ying_pay" class="form-control" readonly="readonly">  
      </c:if> --%>
      </c:if>
      </div>
    </div>
     <div class="form-group">
      <label class="col-xs-3 control-label">教材费：</label>
      <div class="col-xs-8">
         <select id="jiaocaofei" name="jiaocaofei" onchange="get_jiaocai_fei()" data-toggle="select" class="form-control select select-primary">
		  <option value="${entryInfo.jiaocaofei }">￥${entryInfo.jiaocaofei }</option>
		  <option value="0">￥0</option>
		  <option value="${entryPlan.jiaocaifei }">￥${entryPlan.jiaocaifei }</option>
		</select>
      </div>
    </div>
     <div class="form-group">
      <label class="col-xs-3 control-label">组织费：</label>
      <div class="col-xs-8">
        <input type="text" value="${entryInfo.zuzhifei }" class="form-control" name="zuzhifei" id="zuzhifei">  
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-3 control-label">实缴费用：</label>
      <div class="col-xs-8">
        <input type="text" value="${entryInfo.payMoney }"  onblur="check_payMoney()" class="form-control" name="payMoney" id="pay_money">
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-3 control-label">降费：</label>
      <div class="col-xs-8">
        <input type="text" class="form-control" onblur="check_payMoney()" value="${entryInfo.fan_fei }" name=fan_fei id="fanfei">   
      </div>
    </div> 
    <div class="form-group">
      <label class="col-xs-3 control-label">欠费：</label>
      <div class="col-xs-8">
        <input type="text" value="${entryInfo.qianfei }" class="form-control" name="qianfei" id="qianfei">
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-3 control-label">材料费：</label>
      <div class="col-xs-8">
        <input type="text" value="${entryInfo.cailiaofei }" class="form-control" onblur="check_cailiaofei()" id="cailiaofei" name="cailiaofei">
      </div>
    </div>
    <c:if test="${condition.course_id==19 || condition.course_id==20}">
     <div class="form-group">
      <label class="col-xs-3 control-label">学位英语：</label>
      <div class="col-xs-8">
        <select id="xueweiyingyu" name="xueweiyingyu" data-toggle="select" class="form-control select select-primary">
		 <c:if test="${entryInfo.xueweiyingyu!=0 }">
		  <option value="${entryInfo.xueweiyingyu }">￥${entryInfo.xueweiyingyu }</option>
		  </c:if>
		  <option value="0">￥0</option>
		  <option value="3000">￥3000</option>
		</select>
      </div>
    </div> 
    </c:if>
     <c:if test="${condition.course_id==20}">
    <div class="form-group">
      <label class="col-xs-3 control-label">计算机一级：</label>
      <div class="col-xs-8">
        <select id="jisuanjiyiji" name="jisuanjiyiji" data-toggle="select" class="form-control select select-primary">
		 <c:if test="${entryInfo.jisuanjiyiji!=0 }">
		  <option value="${entryInfo.jisuanjiyiji }">￥${entryInfo.jisuanjiyiji }</option>
		  </c:if>
		  <option value="0">￥0</option>
		  <option value="1000">￥1000</option>
		</select>
      </div>
    </div> 
    <div class="form-group">
      <label class="col-xs-3 control-label">公共英语三级：</label>
      <div class="col-xs-8">
        <select id="yingyusanji" name="yingyusanji" data-toggle="select" class="form-control select select-primary">
		   <c:if test="${entryInfo.yingyusanji!=0 }">
		  <option value="${entryInfo.yingyusanji }">￥${entryInfo.yingyusanji }</option>
		  </c:if>
		  <option value="0">￥0</option>
		  <option value="2000">￥2000</option>
		</select>
      </div>
    </div>
    </c:if> 
    <div class="form-group">
      <label class="col-xs-3 control-label">费用说明：</label>
      <div class="col-xs-8">
        <input type="text" value="${entryInfo.feiyong_shuoming }" class="form-control" name="feiyong_shuoming" id="feiyong_shuoming">  
      </div>
    </div>
     <div class="form-group">
      <label class="col-xs-3 control-label">付款方式：</label>
      <div class="col-xs-8">
        <select id="payType" name="payType" data-toggle="select" class="form-control select select-primary">
        	<option value="${entryInfo.payType }">${entryInfo.payType }</option>
			<option value="支付宝">支付宝</option>
			<option value="微信">微信</option>
			<option value="刷卡">刷卡</option>
			<option value="现金">现金</option>
			<option value="穆工行">穆工行</option>
			<option value="穆工行">穆工行</option>
			<option value="吕工行">吕工行</option>
			<option value="吕建行">吕建行</option>
			<option value="吕农行">吕农行</option>
			<option value="沧州对公">沧州对公</option>
			<option value="中国邮政">中国邮政</option>
	</select>
      </div>
    </div>
     <div class="form-group">
      <label class="col-xs-3 control-label">付款日期：</label>
      <div class="col-xs-8">
        <input type="text" class="form-control" value="<fmt:formatDate  value="${entryInfo.pay_time }" />" name="pay_time_str" id="pay_time">   
      </div>
      <script>
			$(function() {
				layui.use('laydate', function() {
					var laydate = layui.laydate;
					var start = {
						festival : true,
						istoday : true
					};
					document.getElementById('pay_time').onclick = function() {
						start.elem = this;
						laydate(start);
					}
				});
			})
	</script>
    </div>
    <div class="form-group">
      <label class="col-xs-3 control-label">&nbsp;</label>
      <div class="col-xs-8">
        <a class="xiazai" onclick="add_zuzhifei(${info_id},'${entryInfo.ying_pay }')" href="javascript:void(0)">修改</a>  
      </div>
    </div>
    <p style="color: orange;">注：请严格核算费用，再提交</p>  
	</div>
	</form>
</body>
</html>