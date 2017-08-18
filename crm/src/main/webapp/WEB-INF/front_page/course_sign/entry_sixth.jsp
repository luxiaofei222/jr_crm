<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/course_sign/reset.css" />
<link rel="stylesheet" type="text/css"
	href="/css/course_sign/registration-common.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/css/course_sign/green.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>支付报名费</title>
<script>
//计算应付金额
var baoming=0;
var jiaocai=0;
var banxing=0;
function get_moeny(){
	if($('#baoming').prop("checked")){
		baoming=$('#baoming').val();
	}else{
		baoming=0;
	}
	if($('#jiaocai').prop("checked")){
		jiaocai=$('#jiaocai').val();
	}else{
		jiaocai=0;
	}
	var banxingnum=$("input[name='radio']:checked").val();
	if(typeof(banxingnum)=='undefined'){
		banxing=0;
	}else{
		banxing=$("input[name='radio']:checked").val();
	}
	$(".allmoney").html(Number(baoming)+Number(jiaocai)+Number(banxing))
}
//去支付
function goto_pay(entryInfoId){
	var moeny=$(".allmoney").text();
	var pay_type=$(".selected").attr("value");
	var banxingnum=$("input[name='radio']:checked").val();
	var	banxingname=null;
	banxing=$("input[name='radio']:checked").val();
	jiaocai=$('#jiaocai').val();
	baoming=$('#baoming').val();
	if(typeof(banxingnum)=='undefined'){
		banxingname=null;
	}else{
		banxingname=$("input[name='radio']:checked").attr("pid");
	}
	$.post("/entry_info/get_entry_seventh.html",{
		'ying_pay':moeny,
		'payType':pay_type,
		'entryInfoId':entryInfoId,
		'mianshoubanxing':banxingname,
		'kaoshimoey':baoming,
		'peixunfei':banxing,
		'jiaocaofei':jiaocai
	},function(data){
		if(data==1){
			layer.msg(pay_type);
			location.href="/entry_info/pay_success.html?entryInfoId="+entryInfoId;
		}else{
			layer.msg("系统发生错误！")
		}
	})
}
</script>
</head>
<body>
	<div class="registration_title">
		<div class="reg_top">
			<a href="/index.jsp"><img src="/images/course_sign/logo.png" /></a><span>京人教育网上报名系统</span>
		</div>
	</div>
	<div class="reg_nav">
		<ul class="ul_nav">
			<li><span></span>
				<div class="str">
					<h3>01</h3>
					<p>选择计划</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>02</h3>
					<p>选择报名条件</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>03</h3>
					<p>选择报名点</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>04</h3>
					<p>填写报名信息</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>05</h3>
					<p>确认报考信息</p>
				</div></li>
			<li class="step_now"><span></span>
				<div class="str">
					<h3>06</h3>
					<p>报名费缴付</p>
				</div></li>
			<li><span></span>
				<div class="str">
					<h3>07</h3>
					<p>报名成功</p>
				</div></li>
		</ul>
	</div>
	<!--  reg_nav   end   fa-plus-square  fa-minus-square-->
	<div class="reg_content">
  <div class="content_info6">
    <div class="money">
      <p><input type="checkbox" onclick="get_moeny()" value="${entryPlan.baomingfei }" id="baoming" />报名费用：<span class="baoming">${entryPlan.baomingfei }</span>元</p>
      <p><input type="checkbox" onclick="get_moeny()" value="${entryPlan.jiaocaifei }" id="jiaocai" />教材费用：<span class="jiaocai">${entryPlan.jiaocaifei }</span>元</p>
      <p>选择面授班型：</p>
      <ul>
      <c:if test="${empty entryPlan.putongbanfei }">
      	暂无普通班面授班型
      </c:if>
      <c:if test="${empty entryPlan.jingjiangbanfei }">
      	暂无精讲班面授班型
      </c:if>
      <c:if test="${not empty entryPlan.putongbanfei }">
        <li><p><input type="radio" value="${entryPlan.putongbanfei }" pid="普通班" onclick="get_moeny()" name="radio" />普通班：<span class="banxing1">${entryPlan.putongbanfei }</span>元</p></li>
       </c:if>
        <c:if test="${not empty entryPlan.jingjiangbanfei }">
        <li><p><input type="radio" value="${entryPlan.jingjiangbanfei }" pid="精讲班" onclick="get_moeny()" name="radio"/>精讲班：<span class="banxing2">${entryPlan.jingjiangbanfei }</span>元</p></li>
      </c:if>
      </ul>
    </div> 
    <p class="allpay">共计费用：<span class="allmoney">0</span>元</p>
    <p class="away">支付方式：</p>
    <div class="aways">
      <ul>
        <li value="支付宝"><div><img src="/images/course_sign/zhifubao.png" /><span>支付宝支付</span></div></li>
        <li value="微信"><div><img src="/images/course_sign/weixin.png" /><span>微信支付</span></div></li>
        <!-- <li value="报名处"><div><img src="/images/course_sign/school.png" /><span>报名处支付</span></div></li> -->
      </ul>
    </div>
    <div class="clear"></div>
  </div>
  <div class="operations">
    <input type="button" class="btn_pay" onclick="goto_pay(${entryInfo.entryInfoId})" value="立即支付" />
  </div> 
</div>
</body>
<script type="text/javascript">
  $(function(){
	  $(".aways ul li").click(function(){
		  $(this).addClass("selected").siblings().removeClass("selected");
		  });
	  })
</script>
</html>