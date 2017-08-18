<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<ul>
	<c:if test="${entryPlan.course_id!=20 && entryPlan.course_id!=19 }">
		<li><strong>培训费</strong></li>
	</c:if>
	<c:if test="${entryPlan.course_id==20 || entryPlan.course_id==19 }">
		<li><strong>学费</strong></li>
	</c:if>
	<c:if test="${entryPlan.course_id!=20 && entryPlan.course_id!=19  }">
		<li><select id="peixunfei" name="peixunfei"
			onchange="get_zonge()">
				<option value="0">请选择</option>
				<option value="${entryPlan.putongbanfei}">普通班￥${entryPlan.putongbanfei}元</option>
				<option value="${entryPlan.jingjiangbanfei}">精讲班￥${entryPlan.jingjiangbanfei}元</option>
		</select></li>
	</c:if>
	<c:if test="${entryPlan.course_id==20 }">
		<li><select id="xuefei" name="xuefei"
			onchange="get_zonge_xuefei(this)">
				<option value="0">请选择</option>
		</select></li>
	</c:if>
	<c:if test="${entryPlan.course_id==19 }">
		<li><select id="xuefei" name="xuefei" 
			onchange="get_zonge_xuefei(this)">
				<%-- <option value="${entryPlan.chengkao_nian}">一年缴￥${entryPlan.chengkao_nian}元</option> --%>
				<option value="0">请选择</option>
				<%-- <option value="${entryPlan.chengkao_zong}">全额缴￥${entryPlan.chengkao_zong}元</option> --%>
		</select></li>
	</c:if>
	<li>--</li>
	<li>--</li>
	<li>--</li>
	<li>--</li>
	<li>0</li>
</ul>
<div style="clear: both;"></div>
<ul>
	<li><strong>报名费</strong></li>
	<li><input type="text" placeholder="请输入考试费" readonly="readonly"
		onblur="check_kaoshimoey()" value="${entryPlan.baomingfei}"
		id="kaoshimoey" name="kaoshimoey" /></li>
	<li>--</li>
	<li>--</li>
	<li>--</li>
	<li>--</li>
	<li>0</li>
</ul>
<div style="clear: both;"></div>
<ul>
	<li><strong>教材费</strong></li>
	<li>
		<select id="jiaocaofei" name="jiaocaofei"
			onchange="check_jiaocaofei()">
				<option value="0">请选择</option>
				<option value="${entryPlan.jiaocaifei}">${entryPlan.jiaocaifei}</option>
		</select></li>
	<li>--</li>
	<li>--</li>
	<li>--</li>
	<li>--</li>
	<li>0</li>
</ul>
<div style="clear: both;"></div>
<ul>
	<li><strong>总额</strong></li>
	<li><input type="text" value="${entryPlan.baomingfei}" readonly="readonly"
		id="ying_pay" name="ying_pay" /></li>
	<li><input type="text" value="0" id="zuzhifei" name="zuzhifei" />
	</li>
	<li><input type="text" value="0" onblur="check_jiangfei()"
		id="fan_fei" name="fan_fei" /></li>
	<li><input type="text" value="0" onblur="check_payMoney()"
		id="payMoney" name="payMoney" /></li>
	<li><select id="payType" name="payType">
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
	</select></li>
	<li><input type="text" value="${entryPlan.baomingfei}" readonly="readonly"
		id="qianfei" name="qianfei" /></li>
</ul>
<c:if test="${entryPlan.course_id==20 }">
	<div style="clear: both;"></div>
	<ul>
		<li style="border-top: #808080 solid 1px;"><strong>学位英语</strong></li>
		<li style="width: 189px; border-top: #808080 solid 1px;"><select
			id="xueweiyingyu" name="xueweiyingyu">
				<option value="0">￥0</option>
				<option value="3000">￥3000</option>
		</select></li>
		<li style="border-top: #808080 solid 1px;"><strong>公共英语三级</strong></li>
		<li style="width: 189px; border-top: #808080 solid 1px;"><select
			id="yingyusanji" name="yingyusanji">
				<option value="0">￥0</option>
				<option value="2000">￥2000</option>
		</select></li>
		<li style="border-top: #808080 solid 1px;"><strong>计算机一级</strong></li>
		<li style="width: 189px; border-top: #808080 solid 1px;"><select
			id="jisuanjiyiji" name="jisuanjiyiji">
				<option value="0">￥0</option>
				<option value="1000">￥1000</option>
		</select></li>
	</ul>
</c:if>
<c:if test="${entryPlan.course_id==19 }">
	<ul>
		<li style="border-top: #808080 solid 1px;"><strong>学位英语</strong></li>
		<li style="width: 795px; border-top: #808080 solid 1px;"><select
			id="xueweiyingyu" name="xueweiyingyu">
				<option value="0">￥0</option>
				<option value="3000">￥3000</option>
		</select></li>
	</ul>
</c:if>
<ul>
	<li style="border-top: #808080 solid 1px;"><strong>材料费</strong></li>
	<li style="width: 795px; border-top: #808080 solid 1px;"><input
		type="text" value="0" id="cailiaofei" onblur="check_cailiaofei()"
		name="cailiaofei" /></li>
</ul>
<div style="clear: both;"></div>
<ul>
	<li style="border-top: #808080 solid 1px;"><strong>费用说明</strong></li>
	<li style="width: 437px; border-top: #808080 solid 1px;"><input
		type="text" onblur="check_feiyong_shuoming()" name="feiyong_shuoming"
		id="feiyong_shuoming" style="text-align: left; padding-left: 5px;" />
	</li>
	<li
		style="border-top: #808080 solid 1px; border-right: #808080 solid 1px; width: 115px;"><strong>缴费日期</strong></li>
	<li
		style="border-top: #808080 solid 1px; width: 245px; border-left: none;"><input
		type="text" style="line-height: 36px; height: 36px;"
		class="layui-input" name="pay_time_str" id="pay_time"
		placeholder="点击获取日期" /></li>
</ul>
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
			$("#pay_time").val(laydate.now());
		});
	})
</script>
<div style="clear: both;"></div>