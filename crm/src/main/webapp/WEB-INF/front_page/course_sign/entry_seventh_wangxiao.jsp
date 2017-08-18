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
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript"
	src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>恭喜您报考成功</title>
<script type="text/javascript">
	function to_person() {
		location.href = "/person/get_person_index.html";
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
			<li><span></span>
				<div class="str">
					<h3>06</h3>
					<p>报名费缴付</p>
				</div></li>
			<li class="step_now"><span></span>
				<div class="str">
					<h3>07</h3>
					<p>报名成功</p>
				</div></li>
		</ul>
	</div>
	<!--  reg_nav   end   fa-plus-square  fa-minus-square-->
	<div class="reg_content">
		<div class="content_info7">
			<p>您的报考已经成功提交。</p>
			<table width="990" border="1" class="baoming">
				<tr>
					<td colspan="8" width="100%"><h3>基本信息</h3></td>
				</tr>
				<tr>
					<td width="12%">学员姓名</td>
					<td width="13%">${entryInfo.entryUserName }</td>
					<td width="12%">曾用名</td>
					<td width="13%">${entryInfo.before_name }</td>
					<td width="12%">性别</td>
					<td width="13%">${entryInfo.entryUserSex }</td>
					<td width="12%">民族</td>
					<td width="13%">${entryInfo.entryUserNation }</td>
				</tr>
				<tr>
					<td width="12%">出生日期</td>
					<td width="13%">${entryInfo.entryUserBirthday }</td>
					<td width="12%">证件类型</td>
					<td width="13%">身份证</td>
					<td width="12%">证件号码</td>
					<td colspan="3" width="30%">${entryInfo.documentNumber }</td>
				</tr>
				<tr>
					<td width="12%">手机号</td>
					<td width="13%">${entryInfo.entryUserPhone }</td>
					<td width="12%">联系QQ</td>
					<td width="13%">${entryInfo.user_qq }</td>
					<td width="12%">电子邮箱</td>
					<td colspan="3" width="38%">${entryInfo.entryUserMail }</td>
				</tr>
				<tr>
					<td width="12%">政治面貌</td>
					<td width="13%">${entryInfo.entryPolitical }</td>
					<td width="12%">籍贯</td>
					<td colspan="5" width="38%">${entryInfo.jiguan }</td>
				</tr>
				<tr>
					<td width="12%">健康状况</td>
					<td width="13%">${entryInfo.health }</td>
					<td width="12%">邮编</td>
					<td width="13%">${entryInfo.zipCode }</td>
					<td width="12%">户口所在地</td>
					<td colspan="3" width="38%">${entryInfo.entryUserProvince }${entryInfo.entryUserCity }${entryInfo.entryUserArea }</td>
				</tr>
				<tr>
					<td width="12%">报名计划</td>
					<td width="10%">${entryPlan.entryplan_content }-${condition.kaoshi_pici }</td>
					<td width="12%">报考学校</td>
					<td width="10%">${university.university_name }</td>
					<td width="12%">报考层次</td>
					<td colspan="3" width="44%">${condition.baokao_cengci }</td>
				</tr>
				<tr>
					<td width="12%">报考地点</td>
					<td width="10%">${entryInfo.entryProvince }-${entryInfo.entryCity }-${entryInfo.entrySchool }</td>
					<td width="12%">学习形式</td>
					<td width="10%">${courseMenu.course_name }</td>
					<td width="12%">报考专业</td>
					<td colspan="3" width="44%">${university2.university_zhuanye }</td>
				</tr>
				<tr>
					<td width="12%">学员资料</td>
					<td colspan="7" width="88%">照片 <c:if
							test="${not empty entryInfo.entryUserPhoto }">     
        （已交）
    </c:if> <c:if test="${empty entryInfo.entryUserPhoto }">     
        （未交）
    </c:if>身份证照片<c:if test="${not empty entryInfo.userCardPositive }">     
        （已交）
    </c:if> <c:if test="${empty entryInfo.userCardPositive }">     
        （未交）
    </c:if>证书照片<c:if test="${not empty entryInfo.certificatePic }">     
        （已交）
    </c:if> <c:if test="${empty entryInfo.certificatePic }">     
        （未交）
    </c:if></td>
				</tr>
				<tr>
					<td colspan="8" width="100%"><h3>财务信息</h3></td>
				</tr>
				<tr>
					<td width="12%">收费项目</td>
					<td colspan="1" width="18%">应缴</td>
					<td colspan="1" width="18%">实缴</td>
					<td colspan="3" width="40%">支付方式</td>
					<td colspan="2" width="12%">欠费</td>
				</tr>
				<tr>
					<td width="12%">培训费</td>
					<td colspan="1" width="18%">
					<c:if test="${empty entryInfo.peixunfei }">
					--
					</c:if>
					${entryInfo.peixunfei }
					</td>
					<td colspan="1" width="18%">--</td>
					<td colspan="3" width="40%">${entryInfo.payType }</td>
					<td colspan="2" width="12%">0</td>
				</tr>
				<tr>
					<td width="12%">报名费</td>
					<td colspan="1" width="18%">${entryInfo.kaoshimoey }</td>
					<td colspan="1" width="18%">--</td>
					<td colspan="3" width="40%">${entryInfo.payType }</td>
					<td colspan="2" width="12%">0</td>
				</tr>
				<tr>
					<td width="12%">教材费</td>
					<td colspan="1" width="18%">${entryInfo.jiaocaofei }</td>
					<td colspan="1" width="18%">--</td>
					<td colspan="3" width="40%">${entryInfo.payType }</td>
					<td colspan="2" width="12%">0</td>
				</tr>
				<tr>
					<td width="12%">总额</td>
					<td colspan="1" width="18%">${entryInfo.ying_pay }</td>
					<td colspan="1" width="18%"><c:if
							test="${entryInfo.payType =='报名处'}">
					待审核
					</c:if> <c:if test="${entryInfo.payType !='报名处'}">
					${entryInfo.payMoney }
					</c:if></td>
					<td colspan="3" width="40%">${entryInfo.payType }</td>
					<td colspan="2" width="12%">--</td>
				</tr>
				<tr>
					<td colspan="8" width="100%"><h3>个人简历</h3></td>
				</tr>
				<tr>
					<td colspan="8">
			 <ul class="ul_jianli">
              <li><span>何年何月至何年何月</span><span>在何学校或单位做何工作</span><span>证明人</span></li>
              <c:forEach items="${entryViates }" var="vitae">
              <li><span>${vitae.time_qujian }</span><span>${vitae.danwei_xuexiao }</span><span>${vitae.zhengmingren }</span></li>
              </c:forEach>
            </ul>
						</td>
				</tr>
				<tr>
					<td colspan="8" width="100%"><h3>家庭成员</h3></td>
				</tr>
				<tr>
					<td colspan="8">	<ul class="ul_jiating">
              <li><span>姓名</span><span>关系</span><span>年龄</span><span>工作单位</span></li>
              <c:forEach items="${entryFamilies }" var="family">
              <li><span>${family.family_name }</span><span>${family.guanxi }</span><span>${family.nianling }</span><span>${family.gongzuodanwei }</span></li>
              </c:forEach>
            </ul></td>
				</tr>
				<tr>
					<td colspan="8" width="100%"><h3>资料领取</h3></td>
				</tr>
				<tr>
					<td width="12%">听课证</td>
					<td width="13%">${entryInfo.tingkezheng }</td>
					<td width="12%">教材</td>
					<td width="13%">${entryInfo.jiaocai }</td>
					<td width="12%">教辅</td>
					<td width="13%">${entryInfo.jiaofu }</td>
					<td width="12%">${entryInfo.jiaofulingqu }</td>
					<td width="13%"></td>
				</tr>
				<tr>
					<td width="12%">网络学习卡</td>
					<td width="13%">${entryInfo.wangluoxuexika }</td>
					<td width="12%">专属服务卡</td>
					<td width="13%">${entryInfo.zhuanshufuwuka }</td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td colspan="8" width="100%"><h3>服务承诺</h3></td>
				</tr>
				<tr>
					<td colspan="8" width="100%" class="promise">
						<p>尊敬的学员：</p>
						<p>在阁楼的深处，有着一片碎石广场，广场周围都是一些颇为繁华的商铺，各种灵药，武器盔甲甚至武学，妖晶都是能够看见。</p>
						<p>这里算是交易坊会中较为高端的地方，能够来到这里的，大多都是在青阳镇有着一些身份的人，而此刻，倒是有着不少人围绕在广场外围，望着里面的两批人马。</p>
						<p>双方的人马年龄都并不大，但那种尖锐的气氛，却是显得格外的浓烈。</p>
						<p>谢家与林家，在青阳镇都算是大家势力，而且大多数人也都知道雷谢两家对于林家有些打压，因此在见到两家的小辈如此针锋相对时，也并不感到意外。</p>
						<p>“林霞，把那狐晶链给我吧，不然的话，今天你们可走不掉。”在谢家小辈领头处，一名身着华丽的锦衣的高挑少女傲然而立，白皙的瓜子脸上透着些许嘲讽，略显纤薄的嘴唇，看上去透着一丝刻薄的味道。</p>
						<p>“给你？”在此女对面，林霞也是一脸的冷笑，在其脸颊上，还有着一个浅浅的红印，正是先前面前的臭女人趁她被拦住时下的手。</p>
						<p>冷笑声落下，林霞直接是将手中的一条雪白色的链子生生扯断，她其实心中也明白，这东西只不过是一种饰品而已，以谢婷的身份，怎么会太过在意这东西，今日的事，只不过是对方故意找茬而已。</p>
					</td>
				</tr>
			</table>
		</div>
		<div class="operations">
			<input type="button" onclick="to_person()" class="btn_pay"
				value="返回个人中心" />
		</div>
	</div>
</body>
</html>