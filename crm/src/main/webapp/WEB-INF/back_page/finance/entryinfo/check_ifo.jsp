<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/f_tankuang.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script>
function doPrint() {  
	window.print(); 
}  

$(function () {  
    //打印  
    $("#btnPrint").bind("click", function (event) {  
        doPrint();  
    });  
}); 
</script>
<style>
tr.yijian td.align_left {
	padding-left:0px; 
}
tr.yijian table {
	width: 100%;
}
tr.yijian table tr {
	border-bottom: #000 solid 1px;
}
tr.yijian table tr:last-child {
	border-bottom: none;
}
tr.yijian table tr td:first-child {
	border-right: #000 solid 1px;
}
tr.yijian .suggestion {
	width: 105px;
}
tr.yijian textarea {
	width:100%;
	height:100%;
	border:none;
	line-height:35px;
	}
</style>
<div class="shenhe" style="padding: 12px">
	<table width="750" border="1" class="baoming_feiyong">
		<tr>
			<td width="15%"><strong>姓名</strong></td>
			<td width="35%" colspan="2">${entryInfo.entryUserName }</td>
			<td width="15%"><strong>身份证号</strong></td>
			<td width="35%" colspan="3">${entryInfo.documentNumber }</td>
		</tr>
		<c:if test="${entryInfo.courseMenu.course_id !=20&&entryInfo.courseMenu.course_id !=19}">
		<tr>
			<td width="15%"><strong>报考类别</strong></td>
			<td width="35%" colspan="3">${entryInfo.courseMenu.course_name }</td>
			<td width="15%"><strong>等级</strong></td>
			<td width="35%" colspan="2">${entryInfo.dictionary.dictionary_name }</td>
		</tr>
		</c:if>
		<c:if test="${entryInfo.courseMenu.course_id ==19}">
		<tr>
			<td width="15%"><strong>报考学校</strong></td>
			<td width="35%" colspan="2">${entryInfo.xuexiao }-${condition.baokao_cengci }</td>
			<td width="15%"><strong>专业</strong></td>
			<td width="35%" colspan="3">${entryInfo.zhuanye }</td>
		</tr>
		</c:if>
		<c:if test="${entryInfo.courseMenu.course_id ==20}">
		<tr>
			<td width="15%"><strong>报考学校</strong></td>
			<td width="35%" colspan="2">${entryInfo.xuexiao }-${condition.baokao_cengci }</td>
			<td width="15%"><strong>专业</strong></td>
			<td width="35%" colspan="3">${entryInfo.zhuanye }</td>
		</tr>
		</c:if>
		<tr>
			<td width="15%"><strong>报考班次</strong></td>
			<td width="35%" colspan="6">${entryInfo.mianshoubanxing }</td>
		</tr>
		<tr>
			<td width="15%"><strong>相关业务员</strong></td>
			<td width="35%" colspan="3">
			<c:if test="${empty entryInfo.employee_id }">
			<span style="float:left; margin-left:10px;">暂无相关业务员信息</span>
			</c:if>
			<c:if test="${not empty entryInfo.employee_id }">
			${entryInfo.employee.employee_name }
			</c:if>
			</td>
			<td width="15%"><strong>组织费</strong></td>
			<td width="35%" colspan="2">${entryInfo.zuzhifei }</td>
		</tr>
		<tr>
			<td width="100%" colspan="7"><strong>财务信息</strong>
			</td>
		</tr>
		<tr>
		    <td><strong>费用说明</strong></td>
			<td colspan="6">${entryInfo.feiyong_shuoming }</td>
		</tr>
		<tr>
			<td width="13%"><strong>收费项目</strong></td>
			<td width="11%"><strong>应缴</strong></td>
			<td width="11%"><strong>实缴</strong></td>
			<td width="11%"><strong>降费</strong></td>
			<td width="11%"><strong>欠费</strong></td>
			<td width="13%"><strong>付款方式</strong></td>
			<td width="30%"><strong>付款时间</strong></td>
		</tr>
		<tr>
			<td width="13%">总金额</td>
			<td width="11%">${entryInfo.ying_pay }</td>
			<td width="11%"><c:if test="${not empty entryInfo.payMoney }">
				<input type="text" value="${entryInfo.payMoney }" id="payMoney" name="payMoney">
			</c:if> <c:if test="${empty entryInfo.payMoney }">
				未付款
			</c:if></td>
			<td width="11%">${entryInfo.fan_fei }</td>
			<td width="11%">${entryInfo.qianfei }</td>
			<td width="13%">${entryInfo.payType}</td>
			<td width="30%" colspan="3"><c:if
					test="${not empty entryInfo.pay_time }">
					<fmt:formatDate  value="${entryInfo.pay_time }" />
				</c:if> <c:if test="${empty entryInfo.pay_time }">
      			--
      </c:if></td>
		</tr>
		<!-- 其他费用 -->
		<tr>
			<td width="100%" colspan="7"><strong>详细费用</strong></td>
		</tr>
		<tr>
			<td colspan="1" width="20%"><strong>培训费</strong></td>
			<td colspan="1" width="20%"><strong>报名费</strong></td>
			<td colspan="1" width="20%"><strong>教材费</strong></td>
			<td colspan="2" width="20%"><strong>材料费</strong></td>
			<td colspan="2" width="20%"><strong> <c:if
						test="${entryInfo.courseMenu.course_id ==20||entryInfo.courseMenu.course_id ==19 }">
			学费
			</c:if> <c:if
						test="${entryInfo.courseMenu.course_id !=20&&entryInfo.courseMenu.course_id !=19 }">
			--
			</c:if>
			</strong></td>
		</tr>
		<tr>
			<td colspan="1" width="20%">${entryInfo.peixunfei }</td>
			<td colspan="1" width="20%">${entryInfo.kaoshimoey }</td>
			<td colspan="1" width="20%">${entryInfo.jiaocaofei }</td>
			<td colspan="2" width="20%">${entryInfo.cailiaofei }</td>
			<td colspan="2" width="20%"><c:if
					test="${entryInfo.courseMenu.course_id ==20||entryInfo.courseMenu.course_id ==19 }">
			${entryInfo.xuefei }
			</c:if> <c:if
					test="${entryInfo.courseMenu.course_id !=20&&entryInfo.courseMenu.course_id !=19 }">
			--
			</c:if></td>
		</tr>
		<!-- 补缴费用 -->
		<c:if test="${not empty repairMoneys }">
		<tr>
			<td width="100%" colspan="7"><strong>补费信息</strong></td>
		</tr>
		<tr>
			<td colspan="1" width="14%"><strong>补费项目</strong></td>
			<td colspan="1" width="14%"><strong>补缴费用</strong></td>
			<td colspan="2" width="28%"><strong>付款时间</strong></td>
			<td colspan="3" width="42%"><strong>补费说明</strong></td>
		</tr>
		<c:forEach items="${repairMoneys }" var="repair">
		<tr>
			<td colspan="1" width="14%">${repair.repair_name }</td>
			<td colspan="1" width="14%">${repair.pay_money }</td>
			<td colspan="2" width="28%"><fmt:formatDate value="${repair.pay_time }" /></td>
			<td colspan="3" width="42%">${repair.note }</td>
		</tr>
		</c:forEach>
		</c:if>
		<!-- 学位英语  -->
		<c:if test="${entryInfo.courseMenu.course_id ==20}">
			<!-- 远程教育 -->
			<tr>
				<td width="13%" colspan="1"><strong>学位英语</strong></td>
				<td width="13%" colspan="1">${entryInfo.xueweiyingyu }</td>
				<td width="20%" colspan="1"><strong>公共英语三级</strong></td>
				<td width="13%" colspan="1">${entryInfo.yingyusanji }</td>
				<td width="17%" colspan="2"><strong>计算机一级</strong></td>
				<td width="22%" colspan="1">${entryInfo.jisuanjiyiji }</td>
			</tr>
		</c:if>
		<c:if test="${entryInfo.courseMenu.course_id ==19}">
			<tr>
				<td width="20%" colspan="1"><strong>学位英语</strong></td>
				<td width="80%" colspan="6">${entryInfo.xueweiyingyu }</td>
			</tr>
		</c:if>
		<tr>
			<td width="20%" colspan="1"><strong>费用说明</strong></td>
			<td width="80%" colspan="6">${entryInfo.feiyong_shuoming }</td>
		</tr>
		<tr>
    <td rowspan="5">审核意见</td>
    <td>财务</td>
    <td colspan="6">${entryInfo.caiwushenhe_message }</td>
  </tr>
  <tr>
    <td>教务</td>
    <td colspan="6">${entryInfo.jiaowushenhe_message }</td>
  </tr>
  <tr>
    <td>部门经理</td>
    <td colspan="6">${entryInfo.bumen_yijian }</td>
  </tr>
  <tr>
    <td>市场总监</td>
    <td colspan="6">${entryInfo.zongjian_yijian }</td>
  </tr>
  <tr>
    <td>总经理</td>
    <td colspan="6">${entryInfo.boss_yijian }</td>
  </tr>
		<%-- <tr class="yijian">
			<td width="14%" rowspan="5"><strong>审核意见</strong></td>
			<td colspan="7" rowspan="5" class="align_left">
			  <table>
			    <tr>
			      <td class="suggestion">财务</td>
			      <td>${entryInfo.caiwushenhe_message }</td>
			    </tr>
			    <tr>
			      <td class="suggestion">教务</td>
			      <td>${entryInfo.jiaowushenhe_message }</td>
			    </tr>
			    <tr>
			      <td class="suggestion">部门经理</td>
			      <td>${entryInfo.bumen_yijian }</td>
			    </tr>
			    <tr>
			      <td class="suggestion">市场总监</td>
			      <td>${entryInfo.zongjian_yijian }</td>
			    </tr>
			    <tr>
			      <td class="suggestion">总经理</td>
			      <td>${entryInfo.boss_yijian }</td>
			    </tr>
			  </table> 
			</td>
		</tr> --%>
	</table>
	<OBJECT id="wb" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" name="wb"></OBJECT>  
		<button type="button" id="btnPrint"
		class="btn btn-success tijiao">打印</button>
</div>
