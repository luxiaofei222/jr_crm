<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/course_sign/reset.css" />
<link rel="stylesheet" type="text/css" href="/font-awesome-4.6.3/css/font-awesome.min.css" />
<link rel="stylesheet" href="/emoji/lib/css/jquery.mCustomScrollbar.min.css" />
<link rel="stylesheet" href="/emoji/dist/css/jquery.emoji.css" />
<link rel="stylesheet" type="text/css" href="/css/employee/index_center.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/emoji/lib/script/jquery.mousewheel-3.0.6.min.js"></script>
<script src="/emoji/lib/script/jquery.mCustomScrollbar.min.js"></script>
<script src="/emoji/dist/js/jquery.emoji.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script src="/js/employeecenter/center.js" type="text/javascript"></script>
<!-- <script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js"></script> -->
<title>个人中心</title>
 <script type="text/javascript">                        
     /*  var goEasy = new GoEasy({
                            appkey: 'BC-5341e251aab445a192f79c538832335c'
                        });
      goEasy.subscribe({
          channel: 'gonggao',
          onMessage: function(message){  //自动接收推送信息                           
          	$("#push_content").html(message.content);
                setTimeout(function () {
                Jihua_Cnblogs_Com();
            }, 1000)
          }
 		}); */
</script>
</head>
<c:if test="${empty employee.beijing }">
<body style="background:url(/images/employee/bg.jpg) no-repeat fixed center top;">
</c:if>
<c:if test="${not empty employee.beijing }">
<body style="background:url(${employee.beijing}) no-repeat fixed center top;">
</c:if>
	<!-- 换肤按钮start -->
	<div class="huanfu"></div>
	<!-- 换肤按钮end -->
	<div class="top">
		<div class="top_nav">
			<div class="left">京人教育集团欢迎您~</div>
			<div class="right">
				<ul>
					<li onclick="get_dongtai()"><i class="fa fa-language"></i>全部动态</li>
					<c:if test="${message_number!=0 }">
					<li onclick="location.href='/private_message/get_private_message.jr'" ><i class="fa fa-envelope-o"><span style="visibility: visible;"></span></i>消息</li>
					</c:if>
					<c:if test="${message_number==0 }">
					<c:if test="${message_number_all==0 }">
					<li><i class="fa fa-envelope-o"><span></span></i>消息</li>
					</c:if>
					<c:if test="${message_number_all!=0 }">
					<li onclick="location.href='/private_message/get_private_message.jr'" ><i class="fa fa-envelope-o"><span></span></i>消息</li>
					</c:if>
					</c:if>
					<li onclick="location.href='/admin.jr'"><i class="fa fa-sign-out"></i>进入系统</li>
					<li onclick="location.href='/em_log/get_back_log.jr?employee_id=${employee.employee_id}'"><i class="fa fa-cog"></i>个人中心</li>
				</ul>
			</div>
		</div>
	</div>
	<c:if test="${empty employee.fengmian }">
	<div class="person" style="background-image:url(/images/employee/top.jpg);">
	</c:if>
	<c:if test="${not empty employee.fengmian }">
	<div class="person" style="background-image:url(${employee.fengmian});">
	</c:if>
		<c:if test="${empty employee.employee_pic }">
			<img src="/images/employee/picture.jpg" />
		</c:if>
		<c:if test="${not empty employee.employee_pic }">
			<img src="${employee.employee_pic }" />
		</c:if>
		<input type="hidden" id="employee_id" value="${employee.login_name }"/>
		<script>
		 /*  var employee_id=$("#employee_id").val();
	      goEasy.subscribe({
	          channel: employee_id,
	          onMessage: function(message){  //自动接收推送信息                           
	          	$("#push_content").html(message.content);
	                setTimeout(function () {
	                Jihua_Cnblogs_Com();
	            }, 1000)
	          }
	 		}); */
		</script>
		<h5>${employee.employee_name }</h5>
		<div class="grade" title="您的积分:${employee.jifen }">
		<c:if test="${not empty apple &&apple!=0 }">
		<c:forEach begin="1" end="${apple }">
		<img src="/images/employee/apple.png" />
		</c:forEach>
		</c:if>
		<c:if test="${flower!=0 }">
		<c:forEach begin="1" end="${flower }">
		<img src="/images/employee/flowers.png" />
		</c:forEach>
		</c:if>
		<c:forEach begin="1" end="${ye }">
		<img src="/images/employee/selvef.png" />
		</c:forEach>
		</div>
  <div class="clear"></div>
		<p>
			<c:if test="${empty employee.employee_trades }">
 您还没有添加个性签名
 </c:if>
			${employee.employee_trades}
		</p>
	</div>
	<div class="center">
		<div class="message left">
			<div class="dongtai">
				<div class="dynamic left" style="cursor: pointer;" onclick="get_my_dongtai()">
					<h5>${donttai }</h5>
					<p>动态</p>
				</div>
				<div class="thumbs-up left">
					<h5>${zan }</h5>
					<p>赞</p>
				</div>
			</div>
			<!-- 动态结束 -->
			<div class="notice">
				<h2>公司公告</h2>
				<img src="/images/employee/notice.png" />
				<c:forEach items="${notice }" var="note">
				<p>${note.notice_content }</p>
				<span class="time"><fmt:formatDate  value="${note.notice_time }" /></span>
				</c:forEach>
			</div>
			<!-- 公告结束 -->
			<div class="file" id="file_list">
				<!--公司文件列表  -->
			</div>
			<!-- 公司文件结束 -->
		</div>
		<div class="content left">
			<form enctype="multipart/form-data" id="myform"
				class="form-horizontal">
				<div class="fabu" id="fabu">
					<img src="/images/employee/fabu.png" />
					<div id="editor" style="width: 670px; height: 70px;"
						contenteditable="true"></div>
					<!-- <i id="btn" class="fa fa-meh-o"></i><i  class="fa fa-image"></i> -->
					<div class="btn_img">
						<input type="button" id="btn" class="expression left" /><span
							class="left">表情</span><input type="button" class="img left" /><span
							class="left">图片</span>
					</div>
					<div class="clear"></div>
					<a href="javascript:void(0)" class="announce"
						onclick="add_say_say()">发布</a>
					<div class="clear"></div>
					<input id="file_upload" name="file_upload" type="file" />
					<div class="image_container">
						<p>图片预览</p>
						<img id="preview" width="60" height="60">
					</div>
					<div class="yulan"></div>
				</div>
			</form>
			<div id="say_list">
				<!-- 说说列表 -->
				<div id="loading">
					<div id="loading-center">
						<div id="loading-center-absolute">
							<div class="object" id="object_one"></div>
							<div class="object" id="object_two"></div>
							<div class="object" id="object_three"></div>
							<div class="object" id="object_four"></div>
							<div class="object" id="object_five"></div>
							<div class="object" id="object_six"></div>
							<div class="object" id="object_seven"></div>
							<div class="object" id="object_eight"></div>
							<div class="object" id="object_big"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="qiandao right">
			<div class="sign">
				<span class="day left">
					<h5>${new_time }</h5>
					<p>${zhouji }</p>
				</span> 
				<c:if test="${is_qiandao==0 }">
				<span class="qian left" onclick="qiandao()" id="weiqian"> <img class="left"
					src="/images/employee/qiandao.png" /> <span class="left">签到</span>
				</span> 
				<span class="qian left" id="yiqian" style="display: none"> <span
					class="left">今日已签到</span>
				</span>
				</c:if>
				<c:if test="${is_qiandao==1 }">
				<span class="qian left" id="yiqian"> <span
					class="left">今日已签到</span>
				</span>
				</c:if>
			</div>
			<!-- 签到结束 -->
			<div class="member" id="employee_list">
				<span class="title"><i class="fa fa-users"></i>系统成员</span>
				<c:forEach items="${employees.list }" var="employ">
					<dl>
						<dt onclick="get_employee_card(${employ.employee_id})">
							<c:if test="${empty employ.employee_pic }">
								<img src="/images/employee/picture.jpg" />
							</c:if>
							<c:if test="${not empty employ.employee_pic }">
								<img src="${employ.employee_pic }" />
							</c:if>
						</dt>
						<dd class="name">${employ.employee_name }</dd>
						<dd class="job">${employ.organization.organization_name }</dd>
						<c:if test="${employ.employee_id!=sessionScope.employee_session.employee_id }">
						<i class="fa fa-envelope" onclick="send_private_message(${employ.employee_id})"></i>
						</c:if>
					</dl>
				</c:forEach>
				<div class="clear"></div>
				<div class="pages right">
					<c:if test="${employees.hasPreviousPage==true}">
						<a href="javascript:void(0)"
							onclick="get_employee_page(${employees.pageNumber-1})"
							title="上一页" class="left"><i class="fa fa-fast-backward"></i></a>
					</c:if>
					<c:if test="${employees.hasPreviousPage!=true}">
						<a href="javascript:void(0)" class="left huise" title="上一页"
							class="left"><i class="fa fa-fast-backward"></i></a>
					</c:if>
					<a class="left"><span>${employees.pageNumber }</span>&nbsp;/&nbsp;${employees.pages }</a>
					<c:if test="${employees.hasNextPage==true}">
						<a href="javascript:void(0)"
							onclick="get_employee_page(${employees.pageNumber+1})"
							title="下一页" class="left"><i class="fa fa-fast-forward"></i></a>
					</c:if>
					<c:if test="${employees.hasNextPage!=true}">
						<a href="javascript:void(0)" class="left huise" title="下一页"
							class="left"><i class="fa fa-fast-forward"></i></a>
					</c:if>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
			<!-- 成员列表 -->
		</div>
	</div>
	<!-- 换肤列表 -->
	<div class="huanlist">
		<div class="huan_title_wrapper">
			<ul class="huan_title">
				<li class="huan_select">封面</li>
				<li>背景</li>
				<span class="huan_close">X</span>
			</ul>
		</div>
		<div class="huan_pic_wrapper">
			<div class="huan_tab" style="display:block;">
				<ul class="huan_pic">
					<li>
						<img src="/images/employee/dao_xiao.png" eid="${employee.employee_id }" pid="/images/employee/dao_feng.png"/>
					</li>
					<li>
						<img src="/images/employee/sa_xiao.gif" eid="${employee.employee_id }" pid="/images/employee/sa_feng.png"/>
					</li>
					<li>
						<img src="/images/employee/money_xiao.png" eid="${employee.employee_id }" pid="/images/employee/money_feng.png"/>
					</li>
					<li>
						<img src="/images/employee/moon_xiao.gif" eid="${employee.employee_id }" pid="/images/employee/moon_feng.png"/>
					</li>
					<li style="margin-right:0px;">
						<img src="/images/employee/dog_xiao.gif" eid="${employee.employee_id }" pid="/images/employee/dog_feng.png"/>
					</li>
				</ul>
				<div class="huan_btn">
					 <button class="btn_confirm">确认</button>
					 <button class="btn_cancel">取消</button>
				 </div>
			 </div>
			<div class="huan_tab">
				<ul class="huan_pic1">
					<li>
						<img src="/images/employee/huanbg1.jpg" eid="${employee.employee_id }" pid="/images/employee/huanbg1.jpg"/>
					</li>
					<li>
						<img src="/images/employee/huanbg2.jpg" eid="${employee.employee_id }" pid="/images/employee/huanbg2.jpg"/>
					</li>
					<li>
						<img src="/images/employee/huanbg3.png" eid="${employee.employee_id }" pid="/images/employee/huanbg3.png"/>
					</li>
					<li>
						<img src="/images/employee/huanbg4.jpg" eid="${employee.employee_id }" pid="/images/employee/huanbg4.jpg"/>
					</li>
					<li style="margin-right:0px;">
						<img src="/images/employee/huanbg5.jpg" eid="${employee.employee_id }" pid="/images/employee/huanbg5.jpg"/>
					</li>
				</ul>
				<!-- <div class="huan_btn">
					 <button class="btn_confirm">确认</button>
					 <button class="btn_cancel">取消</button>
				 </div> -->
			 </div>
		</div>
	</div>
	<div id="jihuaslide">
	    <div class="title">
	        <a href="javaScript:void(0)" id="close">关闭</a>
            <h3>温馨提示</h3>
	    </div>
        <div class="content">
          <dl>
            <dt>京人提示</dt>
            <dd id="push_content">您有新的消息，您有新的消息</dd>
          </dl>
        </div>
    </div>
</body>
</html>