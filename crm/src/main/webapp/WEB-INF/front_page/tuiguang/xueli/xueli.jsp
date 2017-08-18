<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/tuiguang/xiaofang/reset.css">
<link rel="stylesheet" type="text/css"
	href="/css/tuiguang/xueli/chengren_tuiguang.css">
<script type="text/javascript" src="/tuiguang/xinli/jquery.min.js"></script>
<script type="text/javascript" src="/tuiguang/xiaofang/kefu53.js"></script>
<title>学历提升，您的巅峰之路</title>
</head>
<body>
	<!-- 头部开始 -->
	<div class="tops">
		<div class="header">
			<img src="/images/tuiguang/xueli/logo.png" onclick="wangxiao()" />
			<div class="header_nav">
				<ul>
					<li onclick="wangxiao()">网校首页</li>
					<li onclick="kefu53()">班型与学费</li>
					<li onclick="kefu53()">考试与证书</li>
					<li onclick="kefu53()">行业导读</li>
					<li onclick="kefu53()">职业与发展</li>
				</ul>
			</div>
			<div class="phone" onclick="lixianbao()">
				<span>TEL:0311-80958918</span>
			</div>
		</div>
	</div>
	<!--  性价比 -->
	<div class="xingjiabi" onclick="kefu53()">
		<img src="/images/tuiguang/xueli/banner_wz.png"
			style="margin: 0 auto; margin-top: 96px;">
	</div>
	<!--  性价比 -->
	<!--  形式 -->
	<div class="xingshi">
		<p
			style="text-align: center; font-size: 45px; color: #444; margin-top: 53px;">上班族+零基础+久疏学堂=都能考</p>
		<div class="xingshi_s left">
			<ul class="xuanze_chakan">
				<li onclick="kefu53()">成人高考</li>
				<li onclick="kefu53()">自学考试</li>
				<li onclick="kefu53()">远程教育</li>
				<li onclick="kefu53()">高起专</li>
				<li onclick="kefu53()">&nbsp;&nbsp;&nbsp;专升本</li>
				<li onclick="kefu53()">&nbsp;&nbsp;&nbsp;专本连读</li>
				<li onclick="kefu53()">广播电大</li>
				<li onclick="kefu53()">函授夜大</li>
				<span class="clear" onclick="kefu53()">更多&gt;</span>
			</ul>
		</div>
		<div class="xingshi_s right">
			<ul class="xuanze_chakan">
				<li><a href="/person/get_person_index.html">考试大纲</a></li>
				<li><a href="/person/get_person_index.html">考试安排</a></li>
				<li><a href="/person/get_person_index.html">免考政策</a></li>
				<li><a
					href="/front_info/get_course_info.html?courseid=bW%2BdR0WyNnM=">热门专业</a></li>
				<li><a
					href="/front_info/get_course_info.html?courseid=bW%2BdR0WyNnM=">新生必读</a></li>
				<li><a
					href="/front_info/get_course_info.html?courseid=bW%2BdR0WyNnM=">成考排行</a></li>
				<li><a
					href="/front_info/get_course_info.html?courseid=bW%2BdR0WyNnM=">历年真题</a></li>
				<li><a href="/person/get_person_index.html">资料下载</a></li>
				<span class="clear"><a
					href="/front_info/get_course_info.html?courseid=bW%2BdR0WyNnM=">更多&gt;</a></span>
			</ul>
		</div>
		<div style="clear: both;"></div>
	</div>
	<!--  形式 -->
	<!--  取消 -->
	<!-- <div class="quxiao">
		<h2>2018年，取消211、985高校成人高考继续教育大专学历</h2>
	</div>  -->
	<!--  取消 -->
			<!--  活动 -->
	<div class="active">
		<img alt="活动" src="/images/tuiguang/xueli/active_img.png" onclick="active()">
	</div> 
	<!--  活动 -->
	<!--  查询入口 -->
	<div class="zizhu">
		<h2>2017年成人学历报考信息自助查询入口</h2>
		<div class="xinxi_fabu1">
			<ul>
				<li onclick="kefu53()">最新政策</li>
				<li onclick="kefu53()">报名时间</li>
				<li onclick="kefu53()">考试时间</li>
				<li onclick="kefu53()">报名条件</li>
				<li onclick="kefu53()">报考流程</li>
				<li onclick="kefu53()">报考费用</li>
				<li onclick="kefu53()">院校选择</li>
				<li onclick="kefu53()">专业选择</li>
				<li onclick="kefu53()">考试科目</li>
			</ul>
			<img src="/images/tuiguang/xueli/xinxi_img.png" class="xinxi_img">
		</div>
		<div style="overflow: hidden;">
			<div class="xinxi_zhinan zhinan">
				<h3>报名指南</h3>
				<ul class="zhinan">
					<c:forEach items="${denamic_test2 }" var="late" varStatus="vs">
						<li
							onclick="location.href='/front_info/get_course_info_detail.html?info_id=${ late.info_id}'">[动态]${late.info_title }</li>
					</c:forEach>
				</ul>
				<a href="javascript:void(0)" onclick="kefu53()">&gt;</a>
			</div>
			<div class="xinxi_zhinan diyu">
				<h3>地域查询</h3>
				<ul>
					<li onclick="kefu53()"><span>华北地区：</span><em>北京</em>、<em>天津</em>、<em>河北</em>、<em>山西</em>、<em>内蒙古</em></li>
					<li onclick="kefu53()"><span>东北地区：</span><em>辽宁</em>、<em>吉林</em>、<em>黑龙江</em>、<em>大连</em></li>
					<li onclick="kefu53()"><span>华东地区：</span><em>上海</em>、<em>江苏</em>、<em>浙江</em>、<em>安徽</em>、<em>福建</em>......</li>
					<li onclick="kefu53()"><span>中南地区：</span><em>河南</em>、<em>湖北</em>、<em>湖南</em>、<em>广东</em>、<em>广西</em>......</li>
					<li onclick="kefu53()"><span>西南地区：</span><em>重庆</em>、<em>四川</em>、<em>贵州</em>、<em>云南</em>、<em>西藏</em></li>
					<li onclick="kefu53()"><span>西北地区：</span><em>陕西</em>、<em>甘肃</em>、<em>青海</em>、<em>宁夏</em>、<em>新疆</em></li>
				</ul>
				<a href="javascript:void(0)" onclick="kefu53()">&gt;</a>
			</div>
			<div class="xinxi_zhinan zhuanye">
				<h3>热门专业</h3>
				<ul>
					<li onclick="kefu53()">汉语言文学</li>
					<li onclick="kefu53()">工商管理</li>
					<li onclick="kefu53()">金融管理</li>
					<li onclick="kefu53()">&nbsp;&nbsp;&nbsp;人力资源</li>
					<li onclick="kefu53()">法律</li>
					<li onclick="kefu53()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;会计</li>
				</ul>
				<a href="javascript:void(0)" onclick="kefu53()">&gt;</a>
			</div>
		</div>
	</div>
	<!--  查询入口 -->
	<div style="clear: both;"></div>
	<!--  查询入口 -->
	<!--  选择京人 -->
	<div class="clear"></div>
	<!-- <div class="xuanze">
		<h2>学历提升，为什么选择京人教育</h2>
		<p>无压力，自有名校老师坐镇</p>
		<p>想学就学，滚动，直播，录播，精彩重点无遗漏</p>
		<p>随心所欲，自有专业团队替你扫清障碍</p>
		<img src="/images/tuiguang/xueli/xuanze.jpg" onclick="kefu53()" />
	</div> -->
	<!--  选择京人 -->
	<!-- 倒数第十一板块 -->
	<div class="xuelilast12">
		<h2>京人教育集团合作院校</h2>
		<ul>
			<li onclick="kefu53()" style="margin-left:0px;">
				<img src="/images/tuiguang/xueli/hbjmdx.png"><br><h6>河北经贸大学</h6>
			</li>
			<li onclick="kefu53()">
				<img src="/images/tuiguang/xueli/hbgydx.png"><br><h6>河北工业大学</h6>
			</li>
			<li onclick="kefu53()">
				<img src="/images/tuiguang/xueli/ysdx.png"><br><h6>燕山大学</h6>
			</li>
			<li onclick="kefu53()">
				<img src="/images/tuiguang/xueli/hbkjdx.png"><br><h6>河北医科大学</h6>
			</li>
			<li onclick="kefu53()">
				<img src="/images/tuiguang/xueli/hbsfdx.png"><br><h6>河北师范大学</h6>
			</li>
			<li onclick="kefu53()">
				<img src="/images/tuiguang/xueli/cdyxy.png"><br><h6>承德医学院</h6>
			</li>
			<li onclick="kefu53()" style="margin-left:0px;">
				<img src="/images/tuiguang/xueli/hbkjdx.png"><br><h6>河北科技大学</h6>
			</li>
			<li onclick="kefu53()">
				<img src="/images/tuiguang/xueli/nkdx.png"><br><h6>南开大学</h6>
			</li>
			<li onclick="kefu53()">
				<img src="/images/tuiguang/xueli/tjdx.png"><br><h6>天津大学</h6>
			</li>
			<li onclick="kefu53()">
				<img src="/images/tuiguang/xueli/bjjtdx.png"><br><h6>北京交通大学</h6>
			</li>
			<li onclick="kefu53()">
				<img src="/images/tuiguang/xueli/bjdx.png"><br><h6>北京大学</h6>
			</li>
			<li onclick="kefu53()">
				<img src="/images/tuiguang/xueli/bjlg.png"><br><h6>北京理工大学</h6>
			</li>
			<li onclick="kefu53()" style="margin-left:0px;">
				<img src="/images/tuiguang/xueli/bjwgydx.png"><br><h6>北京外国语大学</h6>
			</li>
			<li onclick="kefu53()">
				<img src="/images/tuiguang/xueli/dbdx.png"><br><h6>东北大学</h6>
			</li>
			<li onclick="kefu53()">
				<img src="/images/tuiguang/xueli/dzkjdx.png"><br><h6>电子科技大学</h6>
			</li>
			<li onclick="kefu53()">
				<img src="/images/tuiguang/xueli/scnydx.png"><br><h6>四川农业大学</h6>
			</li>
			<li onclick="kefu53()">
				<img src="/images/tuiguang/xueli/jndx.png"><br><h6>江南大学</h6>
			</li>
			<li onclick="kefu53()">
				<span style="font-size:18px;color:white;">More</span>&nbsp;&nbsp;&nbsp;&nbsp;
				<img src="/images/tuiguang/xueli/more.png" class="more">
				<p style="font-size:18px;margin-top:20px;color:white;">点击查看更多院校</p>
			</li>
		</ul>
	</div>
	<div class="xuelilast11">
		<div class="xuelilast11_wrapper">
			<div class="xuelilast11_title1">78个专业，50多所大学（主考院校信息查询）</div>
			<ul class="xuelilast11_ul" onclick="kefu53()">
				<li class='xuelilast11_li1'></li>
				<li class='xuelilast11_li2'></li>
				<li class='xuelilast11_li3'></li>
				<li class='xuelilast11_li4'></li>
				<li class='xuelilast11_li5'></li>
				<li class='xuelilast11_li6'></li>
				<li class='xuelilast11_li7'></li>
				<li class='xuelilast11_li8'></li>
				<li class='xuelilast11_li9'></li>
				<li class='xuelilast11_li10'></li>
				<li class='xuelilast11_li11'></li>
				<li class='xuelilast11_li12'></li>
				<li class='xuelilast11_li13'></li>
				<li class='xuelilast11_li14'></li>
				<li class='xuelilast11_li15'></li>
				<li class='xuelilast11_li16'></li>
				<li class='xuelilast11_li17'></li>
				<li class='xuelilast11_li18'></li>
				<li class='xuelilast11_li19'></li>
				<li class='xuelilast11_li20'></li>
				<li class='xuelilast11_li21'></li>
				<li class='xuelilast11_li22'></li>
				<li class='xuelilast11_li23'></li>
				<li class='xuelilast11_li24'></li>
				<li class='xuelilast11_li25'></li>
				<li class='xuelilast11_li26'></li>
				<li class='xuelilast11_li27'></li>
			</ul>
		</div>
	</div>
	<!-- 倒数第十板块 -->
	<div class="xuelilast10">
		<div class="xuelilast10_wrapper">
			<div class="xuelilast10_title1">你的前（钱）途岂能被一纸文凭所困！！！<br />交给京人，统统搞定！</div>
			<img src="/images/tuiguang/xueli/xuelilast10_img.png"
				onclick="kefu53()">
		</div>
	</div>
	<!-- <div class="xuelijia">
		<div class="xuelijial">
			<img src="/images/tuiguang/xueli/xuelijia1l.png">
			<div class="xuelijiabtn">
				<button class="btnl" onclick="kefu53()">咨询新班课程</button>
				<button class="btnc" onclick="kefu53()">了解报名流程</button>
				<button class="btnr" onclick="kefu53()">查询热门专业</button>
			</div>
		</div>
		<div class="xuelijiar">
			<img src="/images/tuiguang/xueli/xuelijia1r.png">
			<button onclick="kefu53()">咨询退费流程</button>
		</div>
	</div> -->
	<div class="zhenggui">
		<h2>国家正规学历，学信网永久可查</h2>
		<img src="/images/tuiguang/xueli/zhenggui_img.png">
		<ul class="zhenggui_btn">
			<li onclick="kefu53()">资讯新班课程</li>
			<li onclick="kefu53()">了解报名流程</li>
			<li onclick="kefu53()">查询热门专业</li>
		</ul>
	</div>
		<div class="xuelilast6">
		<div class="xuelilast6_wrapper">
			<div class="xuelilast6_title1">五套学历提升方案</div>
			<div class="xuelilast6_title2">
				京人教育为您提供了五套学历解决方案<br /> 成就您的学历是我们一直努力在做的事，更是我们最擅长的事
			</div>
			<ul class="xuelilast6_wutao">
				<li class="xuelilast6_wutao1">
					<div class="xuelilast6_wutao1left">
						<span class="xuelilast6_wutao1leftimg"></span> <i
							class="xuelilast6_wutao1lefti" onclick="kefu53()">点击申请</i>
					</div> <img src="/images/tuiguang/xueli/xuelilast6_wutao1.png"
					class="wutao1" onclick="kefu53()">
					<div class="xuelilast6_wutao1right">专科学历升本科</div>
				</li>
				<li class="xuelilast6_wutao2">
					<div class="xuelilast6_wutao2left">
						<span class="xuelilast6_wutao2leftimg"></span> <i
							class="xuelilast6_wutao1lefti" onclick="kefu53()">点击申请</i>
					</div> <img src="/images/tuiguang/xueli/xuelilast6_wutao2.png"
					class="wutao1" onclick="kefu53()">
					<div class="xuelilast6_wutao1right">高中学历升本科</div>
				</li>
				<li class="xuelilast6_wutao3">
					<div class="xuelilast6_wutao3left">
						<span class="xuelilast6_wutao3leftimg"></span> <i
							class="xuelilast6_wutao1lefti" onclick="kefu53()">点击申请</i>
					</div> <img src="/images/tuiguang/xueli/xuelilast6_wutao3.png"
					class="wutao1" onclick="kefu53()">
					<div class="xuelilast6_wutao1right">高中学历升专科</div>
				</li>
				<li class="xuelilast6_wutao4">
					<div class="xuelilast6_wutao4left">
						<span class="xuelilast6_wutao4leftimg"></span> <i
							class="xuelilast6_wutao1lefti" onclick="kefu53()">点击申请</i>
					</div> <img src="/images/tuiguang/xueli/xuelilast6_wutao4.png"
					class="wutao1" onclick="kefu53()">
					<div class="xuelilast6_wutao1right">无学历升专科</div>
				</li>
				<li class="xuelilast6_wutao5">
					<div class="xuelilast6_wutao5left">
						<span class="xuelilast6_wutao5leftimg"></span> <i
							class="xuelilast6_wutao1lefti" onclick="kefu53()">点击申请</i>
					</div> <img src="/images/tuiguang/xueli/xuelilast6_wutao5.png"
					class="wutao2" onclick="kefu53()">
					<div class="xuelilast6_wutao1right">无学历升本科</div>
				</li>
			</ul>
			<img src="/images/tuiguang/xueli/xuelilast5_img6.png"
				class="xuelilast6_img6">
		</div>
	</div>
		<div class="xuelilast4">
		<div class="xuelilast4_wrapper">
			<div class="xuelilast4_title1">职场学历认可度排名</div>
			<div class="xuelilast4_title2">
				不同学历形式，含金量有很大差别，在职场的认可度也有区分，在职场中，自<br />考为含金量最高的成人学历形式；求职、升职、跳槽、公务员等均
			</div>
			<img src="/images/tuiguang/xueli/renkedu_img.jpg" style="margin:0 auto;margin-top:65px;margin-bottom:50px;">
		</div>
	</div>
	<!-- 倒数第九板块 -->
	<div class="xuelilast9">
		<div class="xuelilast9_wrapper">
			<div class="xuelilast9_title1">关于获取学历，您必须清楚的问题</div>
			<img src="/images/tuiguang/xueli/xuelilast9_img.png"
				onclick="kefu53()">
			<p style="font-size:25px;color:white;margin-top:50px;margin-bottom:20px;">在线获取老师专业指导&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;咨询热线:0311-80958918
</p>
		</div>
	</div>
	<!-- 倒数第八板块 -->
	<div class="xuelilast8">
		<div class="xuelilast8_wrapper">
			<div class="xuelilast8_title1">京人教育为您精细解读成人教育的照顾政策</div>
			<img src="/images/tuiguang/xueli/xuelilast8_img.png">
		</div>
	</div>
	<!-- 倒数第七板块 -->
	<div class="xuelilast7">
		<div class="xuelilast7_wrapper">
			<div class="xuelilast7_title1">工作读书两不误，211、985名牌大学文凭等您！</div>
			<img src="/images/tuiguang/xueli/xuelilast7_img.png"
				onclick="kefu53()">
		</div>
	</div>
	<!-- 倒数第六板块 -->

	<!-- 倒数第五板块 -->
	<div class="xuelilast5">
		<div class="xuelilast5_wrapper">
			<img src="/images/tuiguang/xueli/xuelilast5_img.png"
				class="xuelilast5_img">
		</div>
	</div>
	<!-- 倒数第四板块 -->

	<!-- 倒数第三板块 -->
	<div class="xuelilast3">
		<div class="xuelilast3_wrapper">
			<h2>京人教育助学金计划</h2>
			<img src="/images/tuiguang/xueli/xuelilast3_img.png">
		</div>
	</div>
	<!-- 倒数第二板块 -->
	<div class="xuelilast2">
		<div class="xuelilast2_wrapper">
			<div class="xuelilast2_title">学员放心服务</div>
			<ul class="xuelilast2_ul">
				<li class='xuelilast2_li' style="margin-left: 20px;">
					<div class="xuelilast2_li_left">
						<div class="xielilast2_ltitle">博，微复习手册</div>
						<div class="xielilast2_ltitle2">关键词：20页A4纸 不再背书</div>
						<p class="xielilast2_linfo">
							我们一贯反对照本宣科<br /> 坚持沉淀最具应试技巧的部分 <br /> 当每一页的考点变化开始被多次教研<br />
							透析时<br /> 好评率已经让同行望其项背了
						</p>
					</div> <img src="/images/tuiguang/xueli/xuelilast2_img1.png"
					class="xuelilast2_li_right">
					<div class="xuelilast2_li_btn" onclick="kefu53()">
						了解详情 <span>></span>
					</div>
				</li>
				<li class='xuelilast2_li'>
					<div class="xuelilast2_li_left">
						<div class="xielilast2_ltitle">全，微智能考题</div>
						<div class="xielilast2_ltitle2">关键词：3段式解析 题库制测评</div>
						<p class="xielilast2_linfo">
							如果觉得教室封闭，那就让课堂更<br /> 近一点 <br /> 如果觉得课本低效，那就扔掉 <br />
							我们让考题精炼，不全是为了应试<br /> 而是想让你养成一种适应轻松的习惯
						</p>
					</div> <img src="/images/tuiguang/xueli/xuelilast2_img2.png"
					class="xuelilast2_li_right">
					<div class="xuelilast2_li_btn" onclick="kefu53()">
						了解详情 <span>></span>
					</div>
				</li>
				<li class='xuelilast2_li' style="margin-left: 20px;">
					<div class="xuelilast2_li_left">
						<div class="xielilast2_ltitle">准，考试知识点预测</div>
						<div class="xielilast2_ltitle2">关键词：上千个知识点切片，切片预测</div>
						<p class="xielilast2_linfo">
							毫无疑问坚持重聘10年教研老师授课<br /> 鉴定了考生边上班边考试也能修的学<br /> 历的信念<br />
							当巡讲又一次场场爆满的时候,<br /> 2017年10月报考冬季注定会响彻非凡
						</p>
					</div> <img src="/images/tuiguang/xueli/xuelilast2_img3.png"
					class="xuelilast2_li_right">
					<div class="xuelilast2_li_btn" onclick="kefu53()">
						了解详情 <span>></span>
					</div>
				</li>
				<li class='xuelilast2_li'>
					<div class="xuelilast2_li_left">
						<div class="xielilast2_ltitle">爽，随时答疑</div>
						<div class="xielilast2_ltitle2">关键词：随时随答</div>
						<p class="xielilast2_linfo">
							我们重新规范了在线随时答疑，<br /> 有问必答<br /> 我们再次定义了实时答疑，一目了然<br />随时随地上课，全程随问随答<br />
							在这里，专业老师全天候为你服务
						</p>
					</div> <img src="/images/tuiguang/xueli/xuelilast2_img4.png"
					class="xuelilast2_li_right">
					<div class="xuelilast2_li_btn" onclick="kefu53()">
						了解详情 <span>></span>
					</div>
				</li>
				<li class='xuelilast2_li' style="margin-left: 20px;">
					<div class="xuelilast2_li_left">
						<div class="xielilast2_ltitle">稳，签学习协议</div>
						<div class="xielilast2_ltitle2">关键词：协议 正规</div>
						<p class="xielilast2_linfo">
							你是上班族还是在校生<br /> 周六有时间学习还是周日休息<br /> 这些都不重要，以为内考自考学历想要<br />稳<br />这一个协议就足够了<br />
						</p>
					</div> <img src="/images/tuiguang/xueli/xuelilast2_img5.png"
					class="xuelilast2_li_right">
					<div class="xuelilast2_li_btn" onclick="kefu53()">
						了解详情 <span>></span>
					</div>
				</li>
				<li class='xuelilast2_li'>
					<div class="xuelilast2_li_left">
						<div class="xielilast2_ltitle">快，知识飞进</div>
						<div class="xielilast2_ltitle2">关键词：简单好学</div>
						<p class="xielilast2_linfo">
							多年教育经验我们深刻意识到<br /> 既然我们已经付出了时间<br /> 用短时间获得有价值的学历<br />
							才是每一个学历考生的期望<br /> 给自己一个最好的回报
						</p>
					</div> <img src="/images/tuiguang/xueli/xuelilast2_img6.png"
					class="xuelilast2_li_right">
					<div class="xuelilast2_li_btn" onclick="kefu53()">
						了解详情 <span>></span>
					</div>
				</li>
			</ul>
			<div style="clear: both;"></div>
		</div>
	</div>
	<!-- 倒数第一板块 -->
	<div class="xuelilast1">
		<div class="xuelilast1_wrapper">
			<div class="xuelilast1_title">只有大机构 才会有这样的服务</div>
			<img src="/images/tuiguang/xueli/xuelilast1_info.png"
				onclick="kefu53()">
		</div>
	</div>
	<div class="buy_liucheng">
		<div class="buy_wrapper">
			<h2>购买流程</h2>
			<img src="/images/tuiguang/xueli/buy_liucheng.png">
			<div class="buy_list" style="text-align:left;">
				<p style="font-size:18px;color:#444;line-height:62px;display:inline-block;">支付方式</p>
				<img src="/images/tuiguang/xueli/pay_zhi.png">
				<img src="/images/tuiguang/xueli/pay_weixin.png">
				<img src="/images/tuiguang/xueli/pay_card.png">
				<img src="/images/tuiguang/xueli/pay_bao.png">
			</div>
		</div>
	</div>
	<!-- 猜你喜欢开始 -->
	<div class="like">
		<div class="likes">
			<p class="biaoti">你想知道</p>
			<ul>
				<li><a href="http://tb.53kf.com/code/client/10147174/1]"
					target="_blank">证书好考吗</a></li>
				<li><a href="http://tb.53kf.com/code/client/10147174/1]"
					target="_blank">考试报名时间</a></li>
				<li><a href="http://tb.53kf.com/code/client/10147174/1]"
					target="_blank">报名条件是什么</a></li>
				<li><a href="http://tb.53kf.com/code/client/10147174/1]"
					target="_blank">考试怎样才能过</a></li>
				<li><a href="http://tb.53kf.com/code/client/10147174/1]"
					target="_blank">申请优惠</a></li>
				<li><a href="http://tb.53kf.com/code/client/10147174/1]"
					target="_blank">学费是多少</a></li>
				<li><a href="http://tb.53kf.com/code/client/10147174/1]"
					target="_blank">问上课时间</a></li>
				<li><a href="http://tb.53kf.com/code/client/10147174/1]"
					target="_blank">考试时间</a></li>
			</ul>
			<p class="close">
				<img src="/images/tuiguang/xueli/close.jpg">
			</p>
			<p class="cai_link">猜你喜欢</p>
		</div>
	</div>
	<jsp:include page="/WEB-INF/front_page/tuiguang/common/baoming.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/front_page/tuiguang/common/tanchuang.jsp"></jsp:include>
</body>
</html>