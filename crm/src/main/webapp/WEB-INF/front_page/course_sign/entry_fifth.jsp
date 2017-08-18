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
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/modules/laydate/laydate.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>确认报考信息</title>
<script>
//返回第四步
function get_fourth(entryInfoId){
	location.href="/entry_info/get_entry_fourth.html?entryInfoId="+entryInfoId;
}
//进入第六步
function get_sixth(entryInfoId){
	location.href="/entry_info/get_entry_sixth.html?entryInfoId="+entryInfoId;
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
			<li class="step_now"><span></span>
				<div class="str">
					<h3>05</h3>
					<p>确认报考信息</p>
				</div></li>
			<li><span></span>
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
  <div class="content_info5">
    <table width="990" border="1" class="baokao">
      <tr>
        <td colspan="8"><h3>学员报考信息</h3></td>
      </tr>
      <tr>
        <td width="12%" class="align_center">姓名</td>
        <td width="21%" class="align_left">${entryInfo.entryUserName }</td>
        <td width="12%" class="align_center">性别</td>
        <td width="5%" class="align_left">${entryInfo.entryUserSex }</td>
        <td width="12%" class="align_center">文化程度</td>
        <td width="28%" colspan="2" class="align_left">${entryInfo.entryUserEducation }</td>
        <td width="28%" rowspan="4"><img src="${entryInfo.entryUserPhoto }" /></td>
      </tr>
      <tr>
        <td width="12%" class="align_center">${entryInfo.documentType }</td>
        <td colspan="3" class="align_left">${entryInfo.documentNumber }</td>
        <td width="12%" class="align_center">出生日期</td>
        <td width="28%" colspan="2" class="align_left">${entryInfo.entryUserBirthday }</td>
        </tr>
      <tr>
        <td width="12%" class="align_center">参加工作时间</td>
        <td width="21%" class="align_left">${entryInfo.inWorkTime }</td>
        <td width="12%" class="align_center">连续工龄</td>
        <td width="5%" class="align_left">${entryInfo.workYears }</td>
        <td width="12%" class="align_center">联系电话</td>
        <td width="28%" colspan="2" class="align_left">${entryInfo.entryUserPhone }</td>
        </tr>
      <tr>
        <td width="12%" class="align_center">所在单位</td>
        <td colspan="3" class="align_left">${entryInfo.entryUserUnit }</td>
        <td width="12%" class="align_center">电子邮箱</td>
        <td width="28%" colspan="2" class="align_left">${entryInfo.entryUserMail }</td>
        </tr>
      <tr>
        <td width="12%" class="align_center">考试类别</td>
        <td colspan="3" class="align_left">${entryInfo.entryCategory }</td>
        <td width="12%" class="align_center">用户名</td>
        <td colspan="3" width="28%" class="align_left">--</td>
        </tr>
      <tr>
        <td width="12%" class="align_center">申报职业</td>
        <td width="21%" class="align_left">${courseMenu.course_name }</td>
        <td width="12%" class="align_center">申报级别</td>
        <td colspan="2" class="align_left">${dictionary.dictionary_name }</td>
        <td width="12%" class="align_center">原证书号</td>
        <td colspan="3" class="align_center">--</td>
      </tr>
      <tr>
        <td width="12%" class="align_center">申报条件</td>
        <td colspan="7" class="align_left">${condition.entrycondition_content }</td>
      </tr>
    </table>
    <div class="photos">
      <div class="zjpic">
        <p>证件照片：</p>
        <c:if test="${entryInfo.documentType=='身份证' }">
        <img src="${entryInfo.userCardPositive }" width="250" height="160" />
        <img src="${entryInfo.userCardOpposite }" width="250" height="160" />
        </c:if>
          <c:if test="${entryInfo.documentType!='身份证' }">
        <img src="${entryInfo.document_photo }" width="250" height="160" />
        </c:if>
      </div>
      <div class="xlpic clear">
        <p>学历照片：</p>
        <img src="${entryInfo.certificatePic }" width="250" height="160" />
      </div>
    </div>
  </div> 
  <div class="operations clear">
    <input type="button" onclick="get_fourth(${entryInfo.entryInfoId})" value="上一步" /><input type="button" onclick="get_sixth(${entryInfo.entryInfoId})" value="下一步" />
  </div>
</div>
</body>
</html>