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
        <td width="12%" class="align_center">曾用名</td>
        <td width="5%" class="align_left">${entryInfo.before_name }</td>
        <td width="12%" class="align_center">性别</td>
        <td width="28%" colspan="2" class="align_left">${entryInfo.entryUserSex }</td>
        <td width="28%" rowspan="3"><img src="${entryInfo.entryUserPhoto }" /></td>
      </tr>
      <tr>
        <td width="12%" class="align_center">民族</td>
        <td width="21%" class="align_left">${entryInfo.entryUserNation }</td>
        <td width="12%" class="align_center">证件类型</td>
        <td width="5%" class="align_left">身份证</td>
        <td width="12%" class="align_center">身份证号码</td>
        <td width="28%" colspan="2" class="align_left">${entryInfo.documentNumber }</td>
        </tr>
      <tr>
        <td width="12%" class="align_center">出生日期</td>
        <td width="12%" class="align_left">${entryInfo.entryUserBirthday }</td>
        <td width="12%" class="align_center">政治面貌</td>
        <td width="21%" class="align_left">${entryInfo.entryPolitical }</td>
        <td width="12%" class="align_center">健康状况</td>
        <td width="28%" colspan="2" class="align_left">${entryInfo.health }</td>
      </tr>
      <tr>
        <td width="12%" class="align_center">籍贯</td>
        <td width="28%" colspan="3" class="align_left">${entryInfo.jiguan }</td>
        <td width="12%" class="align_center">户口所在地</td>
        <td width="28%" colspan="3" class="align_left">${entryInfo.entryUserProvince }${entryInfo.entryUserCity }${entryInfo.entryUserArea }</td>
        </tr>
      <tr>
        <td width="12%" class="align_center">手机号</td>
        <td width="12%" class="align_left">${entryInfo.entryUserPhone }</td>
        <td width="12%" class="align_center">联系QQ</td>
        <td width="12%" class="align_left">${entryInfo.user_qq }</td>
        <td width="12%" class="align_center">电子邮箱</td>
        <td width="16%" class="align_left">${entryInfo.entryUserMail }</td>
        <td width="12%" class="align_center">邮编</td>
        <td width="8%" class="align_left">${entryInfo.zipCode }</td>
      </tr>
      <tr>
        <td width="12%" class="align_center">报名计划</td>
        <td colspan="3" class="align_left">${entryPlan.entryplan_content }-${condition.kaoshi_pici }</td>
        <td width="12%" class="align_center">报考学校</td>
        <td colspan="3" width="28%" class="align_left">${university.university_name }</td>
      </tr>
      <tr>
        <td width="12%" class="align_center">报考层次</td>
        <td colspan="3" class="align_left">${condition.baokao_cengci }</td>
        <td width="12%" class="align_center">报考地点</td>
        <td colspan="3" width="28%" class="align_left">${entryInfo.entryProvince }-${entryInfo.entryCity }-${entryInfo.entrySchool }</td>
      </tr>
      <tr>
        <td width="12%" class="align_center">报考专业</td>
        <td colspan="3" class="align_left">${university2.university_zhuanye }</td>
        <td width="12%" class="align_center">学习形式</td>
        <td colspan="3" width="28%" class="align_left">${courseMenu.course_name }</td>
      </tr>
      <tr>
        <td width="12%" class="align_center">个人简历</td>
        <td colspan="7" class="align_left">
            <ul class="ul_jianli">
              <li><span>何年何月至何年何月</span><span>在何学校或单位做何工作</span><span>证明人</span></li>
              <c:forEach items="${entryViates }" var="vitae">
              <li><span>${vitae.time_qujian }</span><span>${vitae.danwei_xuexiao }</span><span>${vitae.zhengmingren }</span></li>
              </c:forEach>
            </ul>
        </td>
      </tr>
      <tr>
        <td width="12%" class="align_center">家庭关系</td>
        <td colspan="7" class="align_left">     
            <ul class="ul_jiating">
              <li><span>姓名</span><span>关系</span><span>年龄</span><span>工作单位</span></li>
              <c:forEach items="${entryFamilies }" var="family">
              <li><span>${family.family_name }</span><span>${family.guanxi }</span><span>${family.nianling }</span><span>${family.gongzuodanwei }</span></li>
              </c:forEach>
            </ul>
        </td>
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