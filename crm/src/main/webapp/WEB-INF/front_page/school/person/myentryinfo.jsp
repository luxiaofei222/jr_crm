<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h1>报名信息</h1>
<c:if test="${empty entryInfos }">
	<p class="without baoming">
		<img src="/images/school/front/index/user_moren.png"><span>没有报名信息，您还没有报过名！</span>
	</p>
	<div class="clear"></div>
</c:if>
<c:if test="${not empty entryInfos }">
	<c:forEach items="${entryInfos }" var="info">
		<c:if test="${info.courseMenu.course_id==20 }">
			<table>
				<tr>
					<td width="20%">考生姓名</td>
					<td width="80%">${info.entryUserName }</td>
				</tr>
				<tr>
					<td width="20%">报名日期</td>
					<td width="80%"><fmt:formatDate value="${info.entryInfoTime}"
							pattern="yyyy年MM月dd日 " /></td>
				</tr>
				<tr>
					<td width="20%">考试类别</td>
					<td width="80%">${info.courseMenu.course_name}</td>
				</tr>
				<tr>
					<td width="20%">报考学校</td>
					<td width="80%">${info.xuexiao}</td>
				</tr>
				<tr>
					<td width="20%">报考专业</td>
					<td width="80%">${info.zhuanye}</td>
				</tr>
				<tr>
					<td width="20%">考试期次</td>
					<td width="80%">${info.condition.kaoshi_qici}</td>
				</tr>
				<tr>
					<td width="20%">证件号码</td>
					<td width="80%">${info.documentNumber}</td>
				</tr>
				<tr>
					<td width="20%">报名表编号</td>
					<td width="80%">${info.orderNumber}</td>
				</tr>
				<tr>
					<td width="20%">联系方式</td>
					<td width="80%">${info.entryUserPhone}</td>
				</tr>
				<tr>
					<td width="20%">应缴费金额</td>
					<td width="80%">${info.ying_pay}元</td>
				</tr>
				<tr>
					<td width="20%">支付状态</td>
					<td width="80%"><c:if test="${info.isPay==0}">
        您还没有支付<a href="javascript:void(0)">点击此处付款</a>
						</c:if> <c:if test="${info.isPay==1}">
  		支付成功，等待审核！
        </c:if></td>
				</tr>
				<tr>
					<td width="20%">审核状态</td>
					<td width="80%"><c:if test="${info.isPay==1}">
							<c:if test="${info.entryInfoState==1}">
        	等待审核中……
        </c:if>
							<c:if test="${info.entryInfoState==2}">
  		缴费审核认证成功
        </c:if>
							<c:if test="${info.entryInfoState==3}">
  		成功通过教务审核，等待上报考试系统
        </c:if>
							<c:if test="${info.entryInfoState==6}">
  		报名成功
        </c:if>
							<c:if test="${info.entryInfoState==4}">
  			缴费认证失败
        </c:if>
							<c:if test="${info.entryInfoState==5}">
								<span style="color: red;">未审核通过，请重新报名！</span>
							</c:if>
						</c:if> <c:if test="${info.isPay==0}">
        	未付款
        </c:if></td>
				</tr>
				<c:if test="${info.entryInfoState==4}">
					<tr>
						<td width="20%">失败原因</td>
						<td width="80%">${info.caiwushenhe_message}</td>
					</tr>
				</c:if>
				<c:if test="${info.entryInfoState==5}">
					<tr>
						<td width="20%">失败原因</td>
						<td width="80%">${info.jiaowushenhe_message}</td>
					</tr>
				</c:if>
			</table>
		</c:if>
		<c:if test="${info.courseMenu.course_id!=20 }">
			<table>
				<tr>
					<td width="20%">考生姓名</td>
					<td width="80%">${info.entryUserName }</td>
				</tr>
				<tr>
					<td width="20%">报名日期</td>
					<td width="80%"><fmt:formatDate value="${info.entryInfoTime}"
							pattern="yyyy年MM月dd日 " /></td>
				</tr>
				<tr>
					<td width="20%">考试类别</td>
					<td width="80%">${info.courseMenu.course_name}</td>
				</tr>
				<tr>
					<td width="20%">级别</td>
					<td width="80%">${info.dictionary.dictionary_name}</td>
				</tr>
				<tr>
					<td width="20%">考试期次</td>
					<td width="80%">${info.condition.kaoshi_qici}</td>
				</tr>
				<tr>
					<td width="20%">证件号码</td>
					<td width="80%">${info.documentNumber}</td>
				</tr>
				<tr>
					<td width="20%">报名表编号</td>
					<td width="80%">${info.orderNumber}</td>
				</tr>
				<tr>
					<td width="20%">联系方式</td>
					<td width="80%">${info.entryUserPhone}</td>
				</tr>
				<tr>
					<td width="20%">应缴费金额</td>
					<td width="80%">${info.ying_pay}元</td>
				</tr>
				<tr>
					<td width="20%">支付状态</td>
					<td width="80%"><c:if test="${info.isPay==0}">
        您还没有支付<a href="javascript:void(0)">点击此处付款</a>
						</c:if> <c:if test="${info.isPay==1}">
  		支付成功，等待审核！
        </c:if></td>
				</tr>
				<tr>
					<td width="20%">审核状态</td>
					<td width="80%"><c:if test="${info.isPay==1}">
							<c:if test="${info.entryInfoState==1}">
        	等待审核中……
        </c:if>
							<c:if test="${info.entryInfoState==2}">
  		缴费审核认证成功
        </c:if>
							<c:if test="${info.entryInfoState==4}">
  			缴费认证失败
        </c:if>
							<c:if test="${info.entryInfoState==3}">
  		成功通过教务审核，等待上报考试系统
        </c:if>
							<c:if test="${info.entryInfoState==6}">
  		报名成功
        </c:if>
							<c:if test="${info.entryInfoState==5}">
								<span style="color: red;">未审核通过，请重新报名！</span>
							</c:if>
						</c:if> <c:if test="${info.isPay==0}">
        	未付款
        </c:if></td>
				</tr>
				<c:if test="${info.entryInfoState==4}">
					<tr>
						<td width="20%">失败原因</td>
						<td width="80%">${info.caiwushenhe_message}</td>
					</tr>
				</c:if>
				<c:if test="${info.entryInfoState==5}">
					<tr>
						<td width="20%">失败原因</td>
						<td width="80%">${info.jiaowushenhe_message}</td>
					</tr>
				</c:if>
			</table>
		</c:if>
	</c:forEach>
</c:if>