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
<link rel="stylesheet" type="text/css"
	href="/css/edu/entryinfo/e_tankuang.css" />
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
.jiaowu_shenhe_dialog {
    border-bottom: #545454 solid 1px;
}
</style>
<div class="jiaowu_shenhe_dialog">
	<table border="1" class="jiaowu_shenhe">
		<tr>
			<td colspan="8" class="align_center"><strong>报考信息</strong></td>
		</tr>
		<tr>
			<td width="14%"><strong>姓名</strong></td>
			<td width="21%" class="align_left">${entryInfo.entryUserName }</td>
			<td width="12%"><strong>性别</strong></td>
			<td width="5%" class="align_left">${entryInfo.entryUserSex }</td>
			<td width="12%"><strong>出生日期</strong></td>
			<td width="26%" colspan="2" class="align_left">${entryInfo.entryUserBirthday }</td>
			<td width="28%" rowspan="4"><img
				src="${entryInfo.entryUserPhoto }" width="160" height="180" /></td>
		</tr>
		<tr>
			<td width="14%"><strong>证件类型</strong></td>
			<td colspan="3" class="align_left">${entryInfo.documentType }</td>
			<td width="12%"><strong>证件号码</strong></td>
			<td width="26%" colspan="2" class="align_left">${entryInfo.documentNumber }</td>
		</tr>
		<tr>
			<td width="14%"><strong>民族</strong></td>
			<td width="12%" class="align_left">${entryInfo.entryUserNation }</td>
			<td width="12%"><strong>政治面貌</strong></td>
			<td width="12%" class="align_left">${entryInfo.entryPolitical }</td>
			<td width="12%"><strong>户口所在地</strong></td>
			<td width="26%" colspan="2" class="align_left">${entryInfo.entryUserProvince }${entryInfo.entryUserCity }${entryInfo.entryUserArea }</td>
		</tr>
		<tr>
			<td width="14%"><strong>现住地址</strong></td>
			<td colspan="3" class="align_left">${entryInfo.entryUserAddress }</td>
			<td width="12%"><strong>邮编</strong></td>
			<td colspan="2" width="26%" class="align_left">${entryInfo.zipCode }</td>
		</tr>
		<tr>
			<td width="14%"><strong>文化程度</strong></td>
			<td width="12%" class="align_left">${entryInfo.entryUserEducation }</td>
			<td width="12%"><strong>手机号</strong></td>
			<td colspan="2" class="align_left">${entryInfo.entryUserPhone }</td>
			<td width="12%"><strong>电子邮箱</strong></td>
			<td colspan="2" class="align_left">${entryInfo.entryUserMail }</td>
		</tr>
		<tr>
			<td width="14%"><strong>参加工作时间</strong></td>
			<td colspan="3" class="align_left">${entryInfo.inWorkTime }</td>
			<td width="12%"><strong>连续工龄</strong></td>
			<td colspan="3" width="28%" class="align_left">${entryInfo.workYears }年</td>
		</tr>
		<tr>
			<td width="14%"><strong>所在单位</strong></td>
			<td colspan="3" class="align_left">${entryInfo.entryUserUnit }</td>
			<td width="12%"><strong>所在职位</strong></td>
			<td colspan="3" width="28%" class="align_left">${entryInfo.entryUserPosition }</td>
		</tr>
		<tr>
			<td width="14%"><strong>报名计划</strong></td>
			<td class="align_left">${entryInfo.entryPlan.entryplan_content }</td>
			<td><strong>报考类别</strong></td>
			<td colspan="2" class="align_left">${entryInfo.courseMenu.course_name }</td>
			<td><strong>报考层次</strong></td>
			<td colspan="2" class="align_left">${entryInfo.condition.baokao_cengci }</td>
		</tr>
		<tr>
			<td width="14%"><strong>报考学校</strong></td>
			<td colspan="3" class="align_left">${xuexiao }</td>
			<td><strong>报考专业</strong></td>
			<td colspan="3" class="align_left">${zhuanye }</td>
		</tr>
		<tr>
			<td width="14%"><strong>报名点</strong></td>
			<td colspan="4" class="align_left">${entryInfo.entryProvince }${entryInfo.entryCity }${entryInfo.entrySchool }</td>
			<td><strong>是否录取</strong></td>
	  		<td colspan="3">${entryInfo.zhuanshufuwuka }</td>
		</tr>
		<tr>
			<td width="14%"><strong>报名条件</strong></td>
			<td colspan="7" class="align_left">${entryInfo.condition.entrycondition_content }</td>
		</tr>
		<tr>
			<td width="14%"><strong>报考资料</strong></td>
			<td colspan="7">
				<div class="ziliao_pic">
					<span>证件照片：</span>
					<div>
						<c:if test="${entryInfo.documentType=='身份证' }">
							<img src="${entryInfo.userCardPositive}" width="160" height="180" />
							<img src="${entryInfo.userCardOpposite}" width="160" height="180" />
						</c:if>
						<c:if test="${entryInfo.documentType!='身份证' }">
							<img src="${entryInfo.document_photo}" width="160" height="180" />
						</c:if>
					</div>
					<span>学历照片：</span>
					<div>
						<img src="${entryInfo.certificatePic}" width="160" height="180" />
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="8" class="align_center"><strong>课程信息</strong></td>
		</tr>
		<tr>
			<td width="14%"><strong>课程班型</strong></td>
			<td colspan="3" class="align_left">${entryInfo.mianshoubanxing }</td>
			<td width="14%"><strong>报考班次</strong></td>
			<td colspan="3" class="align_left">${entryInfo.baokao_banci }</td>
		</tr>
		<tr>
			<td width="14%"><strong>审核状态</strong></td>
			<td colspan="7" class="align_left"><c:if test="${entryInfo.entryInfoState==0 }">
								未提交
							</c:if> <c:if test="${entryInfo.entryInfoState==1 }">
								财务审核中
							</c:if> <c:if test="${entryInfo.entryInfoState==2 }">
								教务审核中
							</c:if> <c:if test="${entryInfo.entryInfoState==3 }">
								等待提交考试中心
							</c:if> <c:if test="${entryInfo.entryInfoState==4 }">
								财务审核失败
							</c:if> <c:if test="${entryInfo.entryInfoState==5 }">
								教务审核失败
							</c:if> <c:if test="${entryInfo.entryInfoState==6 }">
								已上报考试中心
							</c:if> <c:if test="${entryInfo.entryInfoState==7}">
								部门经理审核中
							</c:if> <c:if test="${entryInfo.entryInfoState==8 }">
								市场总监审核中
							</c:if> <c:if test="${entryInfo.entryInfoState==9 }">
								总经理审核中
							</c:if></td>
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
	</table>
</div>