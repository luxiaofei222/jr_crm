<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>京人体系</title>
<link rel="stylesheet" type="text/css" href="/css/school/front/index/reset.css">
<link rel="stylesheet" type="text/css" href="/css/school/front/system/system.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".system").click(function() {
		var index=$(this).index();
		$(".system_info").eq(index).css("display","block").siblings(".system_info").css("display","none");
		$(this).addClass("on").siblings().removeClass("on");
		
	})
    $(".system").mouseover(function(){
        var index=$(this).index();
        var s=index*180+"px";
        $(".item_bottom").show();
        $(".item_bottom").animate({left:s},100);        
   })
   $(".system").mouseout(function(){
        $(".item_bottom").hide();        
   }) 
})
</script>
</head>
<body bgcolor="#e6e6e6">
	<!--头部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/header.jsp"></jsp:include>
<div class="bgimg">
	<img src="/images/school/front/common/bg.png">
</div>
<div class="systems">
  <ul class="system_nav">
  <c:if test="${on==1 }">
    <li class="system on">服务体系</li>
  </c:if>
  <c:if test="${on!=1 }">
    <li class="system">服务体系</li>
  </c:if>
   <c:if test="${on==3 }">
    <li class="system on">低价体系</li>
    </c:if>
     <c:if test="${on!=3 }">
    <li class="system">低价体系</li>
    </c:if>
    <c:if test="${on==2 }">
    <li class="system on">名师体系</li>
    
    </c:if>
     <c:if test="${on!=2 }">
    <li class="system">名师体系</li>
    </c:if>
    <p class="item_bottom"></p>
  </ul>
  <div class="system_content">
   <c:if test="${on==1 }">
    <div class="system_info service">
   </c:if>
    <c:if test="${on!=1 }">
    <div class="system_info service" style="display: none;">
   </c:if>
      <table border=0 width=1160px>
		<tr height=50px><td align=center><font style="font-size:20px" color="000000">《京人教育用户协议》</font></td></tr>
		<tr><td>
		 以下是京人网校为您提供服务的条款。我们希望您遵守以下的规定，加入会员之前请仔细阅读下列条款.，如果您同意请按 "同意条款"按钮。本协议详述您使用我们的服务所须遵守的条款和条件。如您有任何疑问，请及时联系我们。
		<br>　<br>　
		<font style="font-size:18px" color="FC7878">一、服务条款的确认和接纳</font><br>　 
		京人网校的各项电子服务的所有权和运作权归属京人网校。用户必须完全同意以下所有服务条款才能成为京人网校的正式用户。<br>　
		　<br>　
		<font style="font-size:18px" color="FC7878">二、用户的权利和义务</font><br>　 
		1、所有用户必须遵循：<br>　 
		- 遵守所有使用网络服务的网络协议、规定、程序和惯例； <br>　
		- 不得使用本网站从事违法活动； <br>　
		- 不得干扰或侵犯本网站的正常运行和其他用户的正常使用； <br>　
		- 从中国境内向境外传输技术性资料时不得违反中国有关法律、法规。 <br>　
		2、同时用户承诺： <br>　
		- 不得传输任何非法的、骚扰性的、中伤他人的、辱骂性的、恐吓性的、伤害性的、庸俗的、淫秽的信息资料； - 不得传输任何教唆他人进行违法犯罪行为的资料；<br>　
		- 不得传输不利于国内团结和社会安定的资料；<br>　
		- 不得传输任何不符合国家法律、法规规定的资料、信息； <br>　
		- 不得未经许可而非法进入其它个人或组织电脑系统；<br>　 
		- 法律规定的其他义务。　　 <br>　
		如果用户的行为违背上述两款规定，京人网校可以做出独立判断并有权立即取消用户资格。用户应对自己在网上的违法行为承担全部法律责任。 <br>　
		3、基于网络服务的特殊性，用户同意： <br>　
		- 在注册时按照注册提示提供准确用户名和密码以及详尽的个人资料； <br>　
		- 在个人的注册信息发生变化时，用户应及时更新自己的注册信息，保证其个人资料的详尽和准确。所有用户输入的个人信息将被视作准确表明了用户的身份，并作为本网站提供所有服务的有效身份依据。<br>　 
		- 用户自行配备上网的所需设备，包括个人电脑、调制解调器或其他必备上网装置； <br>　
		- 用户应自行负担因使用这种接入方式而产生的电话费、上网费。 <br>　
		4、用户的用户名、密码 - 用户一旦注册成功，成为网络课堂的正式用户后，应当对自己的用户名、密码的安全性负全部责任。用户的密码只能由用户自己掌握。 - 对于用户因帐号或密码泄露造成的各种损失，京人网校不承担任何责任。用户发现任何非法使用用户帐号的情况，应立即通知京人网校。 <br>　
		- 用户应对以其用户名进行的所有活动和事件负责,不得允许多人使用同一账户学习和辱骂网校老师及工作人员，一经发现立即关闭其课堂，并不退还费 。 <br>　
		- 用户付费后网络课堂一旦开通，如服务端不出现问题，不予退款、换课。 <br>　
		5、网络课堂学习期限<br>　 
		- 京人网校的网络课程自上传完备后，学员可以在任何时间点、任何地点进行反复学习。可以自行安排学习时间和学习进度。(但学员每天课堂的登录、课件下载次数不能超过10次)。 <br>　
		- 京人网校网络教育考试类课程学习从报名开课起至该年度考试结束,考试后网络课堂将关闭,学员将不能再登录课堂学习。<br>　
		-京人网校网络教育非考试类课程学习从报名开课起课堂开通期限为一年,一年后课堂将关闭,学员将不能再登录课堂学习。 <br>　
		　<br>　
		<font style="font-size:18px" color="FC7878">三、京人网校的权利和义务</font><br>　 
		1、京人网校应本着诚实信用的原则向用户提供远程教育服务，不得随意中断或停止提供该项服务。但由于不可抗力或者其它非人为因素造成的服务的中断或停止，京人网校不承担任何相应的责任。 <br>　
		2、京人网校网站内以任何形式表现的作品（包括但不限于文字、软件、声音、图片、录像、表格、电子邮件等）的著作权，由作品的著作权人和京人网校共同享有，用户未经许可，不得擅自对本网站的包括电子课件在内的任何作品进行任何形式的翻录、复制或从事其他任何违反著作权法等相关法律、法规的活动。对侵犯本网站知识产权的个人和组织，京人网校和作品的著作权人将依法追究其民事或刑事责任。课堂中的学习内容（课件声音、讲义文本、习题及答案）用户下载后仅只能做为自己学习之用，不得转发给他人共享使用或进行销售，一经发现用户有此行为立即关闭其课堂(不退还学费)、并追究其法律责任。<br>　 
		3、本网站享有对用户网上活动的监督和指导权，对凡是从事网上非法活动的用户，有权终止所有服务。 <br>　
		4、本网站对用户在网上发表的供学习交流的作品，享有独家的出版、发行和复制的权利。<br>　
		　<br>　
		<font style="font-size:18px" color="FC7878">四、保密责任</font><br>　
		本网站将严格履行用户个人隐私保密义务，承诺不公开、编辑或透露用户个人信息，但以下情况除外：<br>　
		- 用户授权京人网校透露这些信息； <br>　
		- 相应的法律及法律程序要求京人网校提供用户的个人资料；<br>　
		- 在紧急情况下，竭力维护用户个人、其他社会个体和社会大众的安全需要。 <br>　
		　<br>　
		<font style="font-size:18px" color="FC7878">五、服务条款的修改和完善</font><br>　 
		1、京人网校将根据互连网的发展和法律、法规的变化，在认为必要时可以单方面修改服务条款。<br>　
		2、本服务条款一旦发生变动，将会在重要页面上提示修改内容。如果不同意所改动的内容， 用户可以主动放弃获得的网络服务。如果用户继续享用网络服务，则视为接受服务条款的变动。当发生争执时，应以最新服务条款的内容为准。<br>　
		　<br>　
		<font style="font-size:18px" color="FC7878">六、法律</font><br>　
		本服务条款根据现行中华人民共和国法律法规制定。如发生京人网校服务条款与中华人民共和国法律相抵触时，则这些条款将完全按法律规定重新解释，本服务条款的其它条款仍对京人网校和用户具有法律约束力。<br>　</td>
	</tr>
</table>
    </div>
    <c:if test="${on!=3 }">
    <div class="system_info price" style="display: none">
    </c:if>
    <c:if test="${on==3 }">
    <div class="system_info price">
    </c:if>
    	<ul class='low_list'>
    	<c:if test="${empty priceSys }">
    		<li>暂无低价体系内容</li>
    	</c:if>
    		<c:forEach items="${priceSys }" var="price">
    		<li>
    			<div class="low_left">
    				<img src="/images/school/front/common/gift.png">
    			</div>
    			<div class="low_right">
    				<div class="low_title">
    					${price.price_sys_title }
    				</div>
    				<div class="low_info">
    					${price.price_sys_content }
    				</div>
    			</div>
    		</li>
    		</c:forEach>
    	</ul>	
    </div>
     <c:if test="${on!=2 }">
    <div class="system_info teacher" style="display: none">
    </c:if>
    <c:if test="${on==2 }">
    <div class="system_info teacher">
    </c:if>
    	<ul class="teacher">
			<c:forEach items="${teachers }" var="teacher">
			<li>
			<div class="imgholder">
				<img src="${teacher.teacher_pic }">
			</div>
			<strong>张老师</strong>
			<p>
				【${teacher.teacher_course }】${teacher.teacher_dis }
			</p>
			</li>
			</c:forEach>
		</ul>
    </div>
  </div>
</div>
<!--登录注册窗口  -->
	<jsp:include
		page="/WEB-INF/front_page/school/common/dengluzhuce_common.jsp"></jsp:include>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/footer.jsp"></jsp:include>
</body>
</html>