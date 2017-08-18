<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/f_tankuang.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script>
//添加联系人信息信息
function tijiao_shenhe(entryInfoId){
	var  tuifei_shuoming=$("#tuifei_shuoming").val();//退费说明
	var  tuifei=$("#tuifei").val();//退费
	if(tuifei!=null&&tuifei!=""){
		$.post("/crm_tuifei/tuifei_shenqing.jr",{
			'entryInfoId':entryInfoId,
			'tuifei_shuoming':tuifei_shuoming,
			'tuifei':tuifei,
			'tuifei_state':1
		},function(data){
			if(data==1){
				tanchuang("申请成功！");
			}else{
				tanchuang("申请失败！");
			}
		})
	}else{
		layer.msg("请输入退费的数目！");
	}
}
</script>
<!--startprint-->
<div class="shenhe" style="padding: 12px">
	<table width="750" border="1" class="baoming_feiyong">
		<tr>
			<td width="15%"><strong>姓名</strong></td>
			<td width="35%" colspan="2">${entryInfo.entryUserName }</td>
			<td width="15%"><strong>身份证号</strong></td>
			<td width="35%" colspan="3">${entryInfo.documentNumber }</td>
		</tr>
		<c:if test="${entryInfo.courseMenu.course_id !=20}">
			<tr>
				<td width="15%"><strong>报考类别</strong></td>
				<td width="35%" colspan="2">${entryInfo.courseMenu.course_name }</td>
				<td width="15%"><strong>等级</strong></td>
				<td width="35%" colspan="3">${entryInfo.dictionary.dictionary_name }</td>
			</tr>
		</c:if>
		<c:if test="${entryInfo.courseMenu.course_id ==20}">
			<tr>
				<td width="15%"><strong>报考学校</strong></td>
				<td width="35%" colspan="2">${entryInfo.xuexiao }-${condition.kaoshi_pici }</td>
				<td width="15%"><strong>专业</strong></td>
				<td width="35%" colspan="3">${entryInfo.zhuanye }</td>
			</tr>
		</c:if>
		<tr>
			<td width="15%"><strong>报考班型</strong></td>
			<td width="35%" colspan="6">${entryInfo.mianshoubanxing }</td>
		</tr>
		<tr>
			<td width="15%"><strong>相关业务员</strong></td>
			<td width="35%" colspan="2"><c:if
					test="${empty entryInfo.employee_id }">
					<span style="float: left; margin-left: 10px;">暂无相关业务员信息</span>
				</c:if> <c:if test="${not empty entryInfo.employee_id }">
			${entryInfo.employee.employee_name }
			</c:if></td>
			<td width="15%"><strong>组织费</strong></td>
			<td width="35%" colspan="3">${entryInfo.zuzhifei }</td>
		</tr>
		<tr>
			<td width="100%" colspan="7"><strong>财务信息</strong></td>
		</tr>
		<tr>
			<td width="13%"><strong>收费项目</strong></td>
			<td width="9%"><strong>应缴</strong></td>
			<td width="9%"><strong>实缴</strong></td>
			<td width="9%"><strong>降费</strong></td>
			<td width="9%"><strong>欠费</strong></td>
			<td width="9%"><strong>付款方式</strong></td>
			<td width="40%" colspan="2"><strong>付款时间</strong></td>
		</tr>
		<tr>
			<td width="13%">总金额</td>
			<td width="9%">${entryInfo.ying_pay }</td>
			<td width="9%"><c:if test="${not empty entryInfo.payMoney }">
					${entryInfo.payMoney }
				</c:if> <c:if test="${empty entryInfo.payMoney }">
				未付款
			</c:if></td>
			<td width="9%">${entryInfo.fan_fei }</td>
			<td width="9%">${entryInfo.qianfei }</td>
			<td width="9%">${entryInfo.payType}</td>
			<td width="40%" colspan="2"><c:if
					test="${not empty entryInfo.pay_time }">
					<fmt:formatDate value="${entryInfo.pay_time }" />
				</c:if> <c:if test="${empty entryInfo.pay_time }">
      			--
      </c:if></td>
		</tr>
		<!-- 其他费用 -->
		<c:if test="${entryInfo.courseMenu.course_parent_id!=1 }">
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
		</c:if>
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
				<td width="20%" colspan="2"><strong>公共英语三级</strong></td>
				<td width="13%" colspan="1">${entryInfo.yingyusanji }</td>
				<td width="13%" colspan="1"><strong>计算机一级</strong></td>
				<td width="28%" colspan="1">${entryInfo.jisuanjiyiji }</td>
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
			<td width="15%"><strong>上次退费费用</strong></td>
			<td width="85%" colspan="6">
			${entryInfo.tuifei }
			</td>
		</tr>
		<tr class="yijian">
			<td width="15%"><strong>上次退费说明</strong></td>
			<td colspan="6">${entryInfo.tuifei_shuoming }</td>
		</tr>
		<c:if test="${not empty entryInfo.jingli_tuifei_infeo }">
		<tr class="yijian">
			<td width="15%"><strong>经理审核意见</strong></td>
			<td colspan="6">${entryInfo.jingli_tuifei_infeo }</td>
		</tr>
		</c:if>
		<c:if test="${not empty entryInfo.zongjian_tuifei_infeo }">
		<tr class="yijian">
			<td width="15%"><strong>总监审核意见</strong></td>
			<td colspan="6">${entryInfo.zongjian_tuifei_infeo }</td>
		</tr>
		</c:if>
		<c:if test="${not empty entryInfo.boss_tuifei_infeo }">
		<tr class="yijian">
			<td width="15%"><strong>总经理审核意见</strong></td>
			<td colspan="6">${entryInfo.boss_tuifei_infeo }</td>
		</tr>
		</c:if>
		<c:if test="${not empty entryInfo.caiwu_tuifei_infeo }">
		<tr class="yijian">
			<td width="15%"><strong>财务审核意见</strong></td>
			<td colspan="6">${entryInfo.caiwu_tuifei_infeo }</td>
		</tr>
		</c:if>
		<tr>
			<td width="15%"><strong>退费</strong></td>
			<td width="85%" colspan="6" class="shenhezt">
			<input type="text" name="tuifei" id="tuifei" />
			</td>
		</tr>
		<tr class="yijian">
			<td width="15%"><strong>退费说明</strong></td>
			<td colspan="6"><textarea id="tuifei_shuoming"></textarea></td>
		</tr>
	</table>
	<!--endprint-->
	<div class="caozuo">
		<input class="btn btn-success btn-xm"
			onclick="tijiao_shenhe(${entryInfo.entryInfoId })" value="确定"
			type="button">
		<%--  <input  class="btn btn-info btn-xm" onclick="tijiaoshangji(${entryInfo.entryInfoId })" value="提交上级" type="button"> --%>
	</div>
	<%-- <button type="button"
		onclick="tijiao_shenhe(${entryInfo.entryInfoId })"
		class="btn btn-success tijiao">提交</button>
		<OBJECT id="wb" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" name="wb"></OBJECT>  
		<button type="button" id="btnPrint"
		class="btn btn-success tijiao">打印</button> --%>
</div>
