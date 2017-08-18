<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/tuiguang/xiaofang/reset.css">
	<link rel="stylesheet" type="text/css" href="/css/tuiguang/xiaofang/xiaofang.css">
	<script type="text/javascript" src="/tuiguang/xinli/jquery.min.js"></script>
	<script type="text/javascript" src="/tuiguang/xiaofang/kefu53.js"></script>
<title>消防工程师火热报名中</title>
</head>
<body>
<!-- 头部开始 -->
<div class="tops">
  <div class="header">
	  <img src="/images/tuiguang/xiaofang/logo.png" onclick="wangxiao()"/>
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
<div class="xiaofang1">
	<div class='xiaofang1_wrapper'>
		<div class="xiaofang1_title">2017年消防工程师考试</div>
		<img src="/images/tuiguang/xiaofang/xiaofang1-1.png" usemap="#map" class="xiaofang1-1">
		<img src="/images/tuiguang/xiaofang/xiaofang1-2.png" class="xiaofang1-2">
		<div class="xiaofang1_title2">通过近两年考试，市场对于消防工程师人员的需求越来越大<br />通过近两年考试，消防工程师的考试难度越来越大<br />一年8-10万的挂靠费，你需要什么理由</div>
		<div class='xiaofang1_zixun1' onclick="kefu53()">
			咨询考试情况
		</div>
	</div>
</div>
<div class='xiaofang2'>
	<div class="xiaofang2_title">
		2017年各省消防工程师报名时间公布
	</div>
	<div class="xiaofang2_title2">
		消防工程师报名时间变动，各省时间与往年差异较大，输入您的手机号，您将在3分钟内收到免费短信通知服务
	</div>
	<div class="xiaofang2_title3">
		<div class="shengfen">
			<span>省份：</span>
			<select id="yixiang_province"  onchange="get_city()">
		<option value ="0">请选择省份</option>
		<c:forEach items="${cities }" var="city">
		<option value ="${city.id }">
		${city.name }</option>
		</c:forEach>
		</select>
		<select name="city" id="city">
		<option value ="0">请选择市区</option>
		</select> 
		</div>
		<div class="shouji">
			<span>手机号：</span>
			<input type="text" id="phone">
		</div>
		<button class="xiaofang2_chaxun" onclick="save_intent(this)" id="xiaofang2_chaxun">立即查询</button>
	</div>
</div>
<div class="xiaofang3">
	<div class="xiaofang3_wrapper">
		<div class="xiaofang3_left">
			<ul class="xiaofang3_lul">
				<li onclick="kefu53()">报考条件</li>
				<li onclick="kefu53()">报考时间</li>
				<li onclick="kefu53()">考试大纲</li>
				<li onclick="kefu53()">模拟题库</li>
				<li onclick="kefu53()">历年真题</li>
				<li onclick="kefu53()">考试时间</li>
				<li onclick="kefu53()">成绩查询</li>
				<li onclick="kefu53()">合格标准</li>
			</ul>
		</div>
		<div class="xiaofang3_right">
			<img src="/images/tuiguang/xiaofang/xiaofang3-2.png">
		</div>
	</div>
</div>
<div class="xiaofang4">
	<div class="xiaofang4_title">
		2016年注册消防工程师考试介绍
	</div>
	<div class="xiaofang4_table">
		<img src="/images/tuiguang/xiaofang/xiaofang4_table.png" >
		<ul class="xiaofang4_table_an">
			<li onclick="kefu53()">查看合格标准</li>
			<li onclick="kefu53()">查看合格标准</li>
			<li onclick="kefu53()">查看合格标准</li>
			<li onclick="kefu53()">查看合格标准</li>
			<li onclick="kefu53()">查看合格标准</li>
		</ul>
		<h2>消防工程师执业资格考试的成绩为滚动管理</h2>
		<img src="/images/tuiguang/xiaofang/xiaofang4_list.png" class="xiaofang4_list">
		<div class="xiaofang4_btn">
			<button class="xiaofang4_btn1" onclick="kefu53()">2017年考试信息</button>
			<button class="xiaofang4_btn2" onclick="kefu53()">2017年审核标准</button>
		</div>
	</div>
</div>
<div class="xiaofang5">
	<div class='xiaofang5_wrapper'>
		<div class="xiaofang5_title">京人名师团队</div>
		<div class='xiaofang5_title2'>消防工程师的“黄埔军校“用时间铸造一批强大的师资力量！</div>
		<ul class="xiaofang5_ul">
			<li>
				<img src="/images/tuiguang/xiaofang/xiaofang5_teacher1.png">
				<div class="xiaofang5_name">王老师</div>
				<div class="xiaofang5_kemu">授课科目：消防安全技术实务</div>
			</li>
			<li>
				<img src="/images/tuiguang/xiaofang/xiaofang5_teacher2.png">
				<div class="xiaofang5_name">张老师</div>
				<div class="xiaofang5_kemu">授课科目：案例分析</div>
			</li>
			<li>
				<img src="/images/tuiguang/xiaofang/xiaofang5_teacher3.png">
				<div class="xiaofang5_name">李老师</div>
				<div class="xiaofang5_kemu">授课科目：消防综合能力</div>
			</li>
		</ul>
		<img src="/images/tuiguang/xiaofang/xiaofang5_img.png" class="xiaofang5_img">
	</div>
</div>
<div class="xiaofang6">
	<div class="xiaofang6_wapper">
		<ul class='xiaofang6_ul'>
			<li class="xiaofang6_li">
				<img src="/images/tuiguang/xiaofang/xiaofang6_img1.png">
			</li>
			<li class="xiaofang6_li">
				<img src="/images/tuiguang/xiaofang/xiaofang6_img2.png">
			</li>
			<li class="xiaofang6_li">
				<img src="/images/tuiguang/xiaofang/xiaofang6_img3.png">
			</li>
		</ul>
		<div class="xiaofang6_btn">
			<button class="xiaofang6_btn1" onclick="kefu53()">马上抢名额</button>
			<button class="xiaofang6_btn2" onclick="xiaofangzixun()">课程播放</button>
		</div>
	</div>
</div>
<div class="xiaofang7">
	<div class="xiaofang7_wrapper">
		<img src="/images/tuiguang/xiaofang/xiaofang7_title.png" class="xiaofang7_title">
		<div class="xiaofang7_info">
			<div class="xiaofang7_info_nei">
				<span  class="xiaofang7_day" id="day">
					00 
				</span>
				<span class="xiaofang7_hour" id="hour">
					00
				</span>
				<span class="xiaofang7_minutes" id ="mini">
					00
				</span>
				<span class="xiaofang7_second" id="sec">00</span>
			</div>
		</div>
	</div>
</div>
<div class="xiaofang8">
	<div class="xiaofang8_wrapper">
		<img src="/images/tuiguang/xiaofang/xiaofang8_title1.png" class="xiaofang8_title1">
		<button class="xiaofang8_btn1" onclick="kefu53()">报<br />名<br />学<br />习<br /></button>
		<button class="xiaofang8_btn2" onclick="kefu53()">报<br />名<br />学<br />习<br /></button>
		<img src="/images/tuiguang/xiaofang/xiaofang8_title2.png" class="xiaofang8_title2">
	</div>
</div>
<div class="xiaofang9">
	<div class='xiaofang9_wrapper'>
		<img src="/images/tuiguang/xiaofang/xiaofang9_title.png" class="xiaofang9_title">
		<img src="/images/tuiguang/xiaofang/xiaofang9_info.png" class="xiaofang9_info">
		<div style="clear:both;"></div>
	</div>
</div>
<div class="xiaofang10">
	<div class="xiaofang10_wrapper">
		<div class="xiaofang10_title">
			消防工程师备考资料库
		</div>
		<ul class="xiaofang10_info">
			<li class="xiaofang10_li">
				<div class='xiaofang10_info1'>
					<img src="/images/tuiguang/xiaofang/xiaofang10_icon1.png">
					<span class="xiaofang10_span">2016年真题</span>
					<button class="xiaofang10_btn" onclick="kefu53()">
						更多
					</button>
				</div>
				<hr />
				<ul class="xiaofang10_ulsmall">
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">2016年《消防安全技术实务》真题及答案</span>
						<a target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">2016年《安全技术综合能力》真题及答案</span>
						<a target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html"  class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">2016年《消防安全案例分析》真题及答案</span>
						<a  target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html"  class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">2016年消防工程师全套真题及答案</span>
						<a  target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html"  class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">2015年消防工程师全套真题及答案</span>
						<a  target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html"  class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
				</ul>
			</li>
			<li class="xiaofang10_li">
				<div class='xiaofang10_info1'>
					<img src="/images/tuiguang/xiaofang/xiaofang10_icon2.png">
					<span class="xiaofang10_span">视频课件</span>
					<button class="xiaofang10_btn" onclick="kefu53()">
						更多
					</button>
				</div>
				<hr />
				<ul class="xiaofang10_ulsmall">
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">京人网校：2016年《安全技术综合能力》</span>
						<a  target="_blank" href="http://www.jingrenedu.com/front_info/get_course_info.html?courseid=DVU7kqWDMjE=" class="xiaofang10_lismall_a">
							在线观看
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">京人网校：2015年《消防安全案例分析》</span>
						<a  target="_blank" href="http://www.jingrenedu.com/front_info/get_course_info.html?courseid=DVU7kqWDMjE=" class="xiaofang10_lismall_a">
							在线观看
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">京人网校：2016年《技术实务考点》</span>
						<a  target="_blank" href="http://www.jingrenedu.com/front_info/get_course_info.html?courseid=DVU7kqWDMjE=" class="xiaofang10_lismall_a">
							在线观看
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">京人网校：2016年《消防安全技术实务》</span>
						<a  target="_blank" href="http://www.jingrenedu.com/front_info/get_course_info.html?courseid=DVU7kqWDMjE=" class="xiaofang10_lismall_a">
							在线观看
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">京人网校：2016年《案例分析考点》</span>
						<a  target="_blank" href="http://www.jingrenedu.com/front_info/get_course_info.html?courseid=DVU7kqWDMjE=" class="xiaofang10_lismall_a">
							在线观看
						</a>
					</li>
				</ul>
			</li>
			<li class="xiaofang10_li">
				<div class='xiaofang10_info1'>
					<img src="/images/tuiguang/xiaofang/xiaofang10_icon3.png">
					<span class="xiaofang10_span">模拟试题</span>
					<button class="xiaofang10_btn" onclick="kefu53()">
						更多
					</button>
				</div>
				<hr />
				<ul class="xiaofang10_ulsmall">
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">新版《消防安全技术实务》模拟题</span>
						<a  target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">新版《法规安全技术综合能力》模拟题</span>
						<a  target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">强化知识点模拟题（全科）</span>
						<a  target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">消防工程师模拟试题（精华版）</span>
						<a  target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">《名师订制模拟试题》</span>
						<a  target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
				</ul>
			</li>
			<li class="xiaofang10_li">
				<div class='xiaofang10_info1'>
					<img src="/images/tuiguang/xiaofang/xiaofang10_icon4.png">
					<span class="xiaofang10_span">教材</span>
					<button class="xiaofang10_btn" onclick="kefu53()">
						更多
					</button>
				</div>
				<hr />
				<ul class="xiaofang10_ulsmall">
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">《消防安全技术实务》新版</span>
						<a target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">《消防安全技术综合能力》新版</span>
						<a target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">《消防安全案例分析》新版</span>
						<a target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">消防工程师全套2016年新版</span>
						<a target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
				</ul>
			</li>
			<li class="xiaofang10_li">
				<div class='xiaofang10_info1'>
					<img src="/images/tuiguang/xiaofang/xiaofang10_icon5.png">
					<span class="xiaofang10_span">2016年消防工程师《考试大纲》</span>
					<button class="xiaofang10_btn" onclick="kefu53()">
						更多
					</button>
				</div>
				<hr />
				<ul class="xiaofang10_ulsmall">
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">2016年消防工程师《考试大纲》</span>
						<a target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">消防工程师强化测试练习</span>
						<a target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">2016年消防工程师考点总结</span>
						<a target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">2016年消防工程师讲义（全科）</span>
						<a target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">2016年考点知识汇总（全科）</span>
						<a target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							注册下载
						</a>
					</li>
				</ul>
			</li>
			<li class="xiaofang10_li">
				<div class='xiaofang10_info1'>
					<img src="/images/tuiguang/xiaofang/xiaofang10_icon6.png">
					<span class="xiaofang10_span">京人资料</span>
					<button class="xiaofang10_btn" onclick="kefu53()">
						更多
					</button>
				</div>
				<hr />
				<ul class="xiaofang10_ulsmall">
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">2016年消防工程师密训卷（全科）</span>
						<a target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							点击索要
						</a>
					</li>
					<li class="xiaofang10_lismall">
						<span class="xiaofang10_lismall_span">《2016年消防工程师考前精讲卷（全科）</span>
						<a target="_blank" href="http://www.jingrenedu.com/person/get_person_index.html" class="xiaofang10_lismall_a">
							点击索要
						</a>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
<div class='xiaofang11'>
	<div class="xiaofang11_wrapper">
		<div class="xiaofang11_title">
			2017年注册消防工程师考试全国发布
		</div>
		<hr />
		<div class="xiaofang11_title2">
			2017年度一级注册消防工程师考试日期定于2017年11月中旬 
		</div>
		<img src="/images/tuiguang/xiaofang/xiaofang11_info.png">
	</div>
	<div class="xiaofang11_btn">
		<button class="xiaofang11_btn1" onclick="kefu53()">点击获3个月备考方案</button>
		<button class="xiaofang11_btn2" onclick="kefu53()">点击查看各省报考政策</button>
		<button class="xiaofang11_btn3" onclick="kefu53()">点击咨询备案详情</button>
	</div>
</div>
<div class="xiaofang12">
	<div class="xiaofang12_wrapper">
		<div class="xiaofang12_time">
			<div class="xiaofang12_time1">
				距离2017年考试还有
			</div>
			<div class="xiaofang12_time2">
				000
			</div>
			<div class="xiaofang12_time3">
				天
			</div>
		</div>
		<div class="xiaofang12_test">
			2017年考试时间:
			<span style="color:#ce3a3a;">11月12日、11月13日</span>
		</div>
	</div>
</div>
<div class="xiaofang13">
	<div class="xiaofang13_wrapper">
		<img src="/images/tuiguang/xiaofang/xiaofang13_info.png" class="xiaofang13_info">
		<button class="xiaofang13_btn" onclick="kefu53()">
			点击领取资料
		</button>
	</div>
</div>
<div class='xiaofang14'>
	<div class="xiaofang14_wrapper">
		<div class="xiaofang14_title1">
			2017注册消防工程师考试<span style="color:#ce3a3a;">相关专业已公布<span>
		</div>
		<div class="xiaofang14_title2">
			唯有满足相关专业信息的考生才能报考2017年注册消防工程师，目前官方已发布具体专业对照表 考生可根据专业对照表查看自己是否符合
		</div>
		<div class="xiaofang14_title3">
			对于部分擦边专业，可根据世纪情况酌情放宽条件进行进行报考，具体信息可咨询京人网校消防报考老师
		</div>
		<div class="xiaofang14_table">
			<img src="/images/tuiguang/xiaofang/xiaofang14_table.png" onclick="kefu53()">
			<button class="xiaofang14_btn" onclick="lixianbao()">点击在线咨询</button>
		</div>
	</div>
</div>
<div class="xiaofang15">
	<div class="xiaofang15_wrapper">
		<div class='xiaofang15_title1'>
			【<span style="color:#ce3a3a;">报名备案</span>】消防内部网报通道正式开通
		</div>
		<div class='xiaofang15_title2'>
			【<span style="color:#ce3a3a;">全国统一官网报名网址</span>：<a href="http://www.jingrenedu.com" target="_blank">www.jingrenedu.com</a>】
		</div>
		<div class='xiaofang15_title3'>
			为防止各省的正式报考时间过短，同一时间报考人数过多而导致官网出现报名拥堵状况，此次注册消防工程师考试可提前预约备案，考生可提前
		</div>
		<img src="/images/tuiguang/xiaofang/xiaofang15_info1.png" onclick="kefu53()">
		<img src="/images/tuiguang/xiaofang/xiaofang15_info.png" style="margin-top:50px;" usemap="#Map">
		<map name="Map">
          <area shape="rect" coords="150,328,373,372" href="http://www16.53kf.com/webCompany.php?style=1]&arg=10147174" target="_blank">
          <area shape="rect" coords="423,331,643,372" href="http://www16.53kf.com/webCompany.php?style=1]&arg=10147174" target="_blank">
          <area shape="rect" coords="560,716,782,758" onclick="donw_load()">
          <area shape="rect" coords="830,716,1055,756" href="http://www16.53kf.com/webCompany.php?style=1]&arg=10147174" target="_blank">
          <area shape="rect" coords="152,1200,371,1244" href="http://www16.53kf.com/webCompany.php?style=1]&arg=10147174" target="_blank">
          <area shape="rect" coords="421,1200,643,1240" href="http://www16.53kf.com/webCompany.php?style=1]&arg=10147174" target="_blank">
          <area shape="rect" coords="562,1598,781,1639" href="http://www16.53kf.com/webCompany.php?style=1]&arg=10147174" target="_blank">
          <area shape="rect" coords="830,1599,1052,1641" href="http://www.jingrenedu.com/tuiguang/get_firecontrol.html" target="_blank">
          <area shape="rect" coords="151,2107,372,2147" href="http://www16.53kf.com/webCompany.php?style=1]&arg=10147174" target="_blank">
          <area shape="rect" coords="421,2107,642,2148" href="http://www.jingrenedu.com/front_info/get_course_info.html?courseid=DVU7kqWDMjE=" target="_blank">
        </map>
	</div>
</div>
<div class="xiaofang16">
	<div class="xiaofang16_wrapper">
		<div class="xiaofang16_title1">
			2017年消防工程师挂靠 缺口高达27万
		</div>
		<hr />
		<div class="xiaofang16_title2">
			2017年，随着临时消防工程师的取消，注册消防工程师市场告急，挂靠人数是考核企业资质的重要指标，据不完全统计，15年挂靠需求达
		</div>
		<img src="/images/tuiguang/xiaofang/xiaofang16_info.png">
		<div class="xiaofang16_xiangqing">
			<button class="xiaofang16_xiangqing1" onclick="kefu53()">了解详情</button>
			<button class="xiaofang16_xiangqing2" onclick="kefu53()">了解详情</button>
			<button class="xiaofang16_xiangqing3" onclick="kefu53()">了解详情</button>
		</div>
	</div>
</div>
<div class="xiaofang17">
	<div class="xiaofang17_wrapper">
		<img src="/images/tuiguang/xiaofang/xiaofang17_title.png" class="xiaofang17_title">
		<img src="/images/tuiguang/xiaofang/xiaofang17_info.png" class="xiaofang17_info">
		<div class="xiaofang17_btn">
			<button class="xiaofang17_btn1" onclick="kefu53()">查看更多省市挂靠费用</button>
			<button class="xiaofang17_btn2" onclick="kefu53()">点击咨询在线客服</button>
		</div>
	</div>
</div>
<div class="xiaofang18">
	<div class="xiaofang18_wrapper">
		<div class="xiaofang18_title1">
			全方位了解考试政策助您快速通过考试
		</div>
		<div class="xiaofang18_title2">
			根据消息，2017年注册消防工程师考试定在11月份，想要树立快速学习方法
		</div>
		<div class="xiaofang18_title3">
			全方位了解学习方法必不可少，京人网校将在第一时间为您
		</div>
		<button class="xiaofang18_btn" onclick="kefu53()">点击咨询最新政策</button>
	</div>
</div>
<div class="xiaofang19">
	<div class="xiaofang19_wrapper">
		<div class='xiaofang19_left1'>
			<div class="xiaofang19_banner">
				<img src="/images/tuiguang/xiaofang/xiaofang19_banner.png">
				<span class="xiaofang19_left1_btn" onclick="kefu53()">点击报名</span>
			</div>
			<div class='xiaofang19_bell'>
				<img src="/images/tuiguang/xiaofang/xiaofang19_bell.png" class="xiaofang19_bellimg">
				<span class="xiaofang19_bell_title">考试提醒</span>
				<span class="xiaofang19_gengduo" onclick="kefu53()">更多</span>
			</div>
			<ul class="xiaofang19_left1_ul">
				<li class="xiaofang19_left1_li">
					<span class="red">报考时间：</span>详见人事考试网
				</li>
				<li class="xiaofang19_left1_li">
					<span class="red">领准考证：</span>考前一周
				</li>
				<li class="xiaofang19_left1_li">
					<span class="red">考试时间：</span>暂未公布
				</li>
				<li class="xiaofang19_left1_li">
					<span class="red">合格标准：</span>暂未公布
				</li>
				<li class="xiaofang19_left1_li">
					<span class="red">合格证书：</span>暂未公布
				</li>
			</ul>
		</div>
		<div class="xiaofang19_left2">
			<div class='xiaofang19_zixun'>
			<c:forEach items="${late_update }" var="late" varStatus="vs">
				<c:if test="${vs.index==0 }">
				<h2 class="xiaofang19_title">
					<a target="_blank" href="/front_info/get_course_info_detail.html?info_id=${ late.info_id}">资讯：${late.info_title }
					</a>
				</h2>
				</c:if>
				<c:if test="${vs.index!=0 }">
				<h5 class="xiaofang19_title1">
					<a target="_blank" href="/front_info/get_course_info_detail.html?info_id=${ late.info_id}">
					【${late.info_type }】${late.info_title }</a>
				</h5>
				</c:if>
			</c:forEach>
			</div>
			<div class='xiaofang19_zixun' style="margin-top:10px;">
			<c:forEach items="${exam_question }" var="exam" varStatus="vs">
				<c:if test="${vs.index==0 }">
				<h2 class="xiaofang19_title">
					<a target="_blank" href="/front_info/get_course_info_detail.html?info_id=${ exam.info_id}">
					${exam.info_title }
					</a>
				</h2>
				</c:if>
				<c:if test="${vs.index!=0 }">
				<h5 class="xiaofang19_title1">
					<a target="_blank" href="/front_info/get_course_info_detail.html?info_id=${ exam.info_id}">
					【${exam.info_type }】${exam.info_title }</a>
				</h5>
				</c:if>
			</c:forEach>
			</div>
			<div class="xiaofang19_textzixun">
				<ul class="xiaofang19_textzixunul">
					<li class="xuanzhong">考试资讯</li>
					<li>最新更新</li>
					<li>考试动态</li>
					<li>考试真题</li>
					<li>报考指南</li>
				</ul>
				<div class="xiaofang19_gengduo" onclick="kefu53()">
					更多》
				</div>
			</div>
			<ul class="xiaofang19_zixuninfo" style="display:block;">
			<c:forEach items="${denamic_test }" var="denamic">
				<li class="xiaofang19_zixuninfoli">
					<a target="_blank" href="/front_info/get_course_info_detail.html?info_id=${ denamic.info_id}">
					<div class="xiaofang19_zixuninfoli1">${denamic.info_type }|${denamic.info_title }</div>
					<div class="xiaofang19_zixundate"><fmt:formatDate pattern="MM-dd" value="${denamic.info_time }" /></div>
					</a>
				</li>
				</c:forEach>
			</ul>
		</div>
		<div class="xiaofang19_right">
			<div class="xiaofang19_right1">
				<div class="xiaofang19_right1_title">
					一级消防工程师报考指南
				</div>
				<ul>
					<li onclick="kefu53()">报名时间</li>
					<li onclick="kefu53()">报名条件</li>
					<li onclick="kefu53()">考试时间</li>
					<li onclick="kefu53()">考试科目</li>
					<li onclick="kefu53()">考试时长</li>
					<li onclick="kefu53()">免费规定</li>
					<li onclick="denglu()">成绩管理</li>
					<li onclick="denglu()">考核认定</li>
					<li onclick="denglu()">执业范围</li>
					<li onclick="denglu()">合格证书</li>
				</ul>
			</div>
			<div class="xiaofang19_right1" style="margin-top:50px;">
				<div class="xiaofang19_right1_title">
					二级消防工程师报考指南
				</div>
				<ul>
					<li onclick="kefu53()">报名时间</li>
					<li onclick="kefu53()">报名条件</li>
					<li onclick="kefu53()">考试时间</li>
					<li onclick="kefu53()">考试科目</li>
					<li onclick="kefu53()">考试时长</li>
					<li onclick="kefu53()">免费规定</li>
					<li onclick="denglu()">成绩管理</li>
					<li onclick="denglu()">考核认定</li>
					<li onclick="denglu()">执业范围</li>
					<li onclick="denglu()">合格证书</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="xiaofang20">
	<div class="xiaofang20_wrapper">
		<div class="xiaofang20_title">
			京人网校三大课程优势 助您快递通过消防
		</div>
		<div class="xiaofang20_title1">
			摸清问题才能对症下药，京人网校带您快速通过消防，绝不意外
		</div>
	</div>
</div>
<div class="xiaofang21"></div>
<div class="xiaofang22">
	<div class="xiaofang22_wrapper">
		<img src="/images/tuiguang/xiaofang/xiaofang22_info.png" class="xiaofang22_info">
	</div>
</div>
<!-- <div class="xiaofang23">
	<div class="xiaofang23_wrapper">
		<div class="xiaofang23_title">
			重要通知
		</div>
		<div class="xiaofang23_title1">
			不过退费协议已经停止签订
		</div>
	</div>
</div> -->
<div class="xiaofang24">
	<div class="xiaofang24_wrapper">
		<div class="xiaofang24_title">
			支付方式
		</div>
		<img src="/images/tuiguang/xiaofang/xiaofang24_info1.png" style="margin-top:38px;" onclick="kefu53()">
		<div class="xiaofang24_title1">
			购买流程
		</div>
		<img src="/images/tuiguang/xiaofang/xiaofang24_info2.png" style="margin-top:70px;" onclick="kefu53()">
	</div>
</div>
<!-- 猜你喜欢开始 -->
<div class="like">
	<div class="likes">
		<p class="biaoti">你想知道</p>
		<ul>
			<li>
				<a href="http://tb.53kf.com/code/client/10147174/1]" target="_blank">证书好考吗</a>
			</li>
			<li>
				<a href="http://tb.53kf.com/code/client/10147174/1]" target="_blank">考试报名时间</a>
			</li>
			<li>
				<a href="http://tb.53kf.com/code/client/10147174/1]" target="_blank">报名条件是什么</a>
			</li>
			<li>
				<a href="http://tb.53kf.com/code/client/10147174/1]" target="_blank">考试怎样才能过</a>
			</li>
			<li>
				<a href="http://tb.53kf.com/code/client/10147174/1]" target="_blank">申请优惠</a>
			</li>
			<li>
				<a href="http://tb.53kf.com/code/client/10147174/1]" target="_blank">学费是多少</a>
			</li>
			<li>
				<a href="http://tb.53kf.com/code/client/10147174/1]" target="_blank">问上课时间</a>
			</li>
			<li>
				<a href="http://tb.53kf.com/code/client/10147174/1]" target="_blank">考试时间</a>
			</li>
		</ul>
		<p class="close">
			<img src="/images/tuiguang/xiaofang/close.jpg">
		</p>
		<p class="cai_link">猜你喜欢</p>
	</div>
</div>
	<jsp:include page="/WEB-INF/front_page/tuiguang/common/baoming.jsp"></jsp:include>
<jsp:include page="/WEB-INF/front_page/tuiguang/common/tanchuang.jsp"></jsp:include>
</body>
<script>
function xiaofangzixun() {
	window.open('http://www.jingrenedu.com/front_info/get_course_info.html?courseid=DVU7kqWDMjE=');
}
function denglu() {
	window.open('http://www.jingrenedu.com/person/get_person_index.html');
}
</script>
</html>