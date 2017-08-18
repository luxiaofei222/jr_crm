<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${courseVideo.video_name }-课程视频播放</title>
<link rel="shortcut icon" href="/images/school/front/index/logo2.png"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="/css/school/front/index/index.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/course_video/course.css" />
<link href="/css/school/front/course_video/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="/css/school/front/course_video/normalize.css" />
<!-- <link rel="stylesheet" type="text/css" href="/css/school/front/course_video/default.css"> -->
<link rel="stylesheet"
	href="/css/school/front/course_video/jquery.mCustomScrollbar.css">
<link href="/css/school/front/course_video/all.css" rel="stylesheet">
<script src="/js/school/front/course_video/jquery-1.11.1.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script
	src="/js/school/front/course_video/jquery.mCustomScrollbar.concat.min.js"></script>
<script type="text/javascript" src="/js/school/front/index/index.js"></script>
<script type="text/javascript"
	src="/js/school/front/course_video/icheck.js"></script>
<script type="text/javascript"
	src="/js/school/front/course_video/jquery.page.js"></script>
<script type="text/javascript" src="/ckplayer/ckplayer/ckplayer.js"
	charset="utf-8"></script>
<script type="text/javascript" src="/js/school/front/video/video.js"></script>
</head>
<script>
$(document).ready(function(){  
    $(document).bind("contextmenu",function(e){   
          return false;   
    });
});
</script>
<body>
	<!--头部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/header.jsp"></jsp:include>
	<input type="hidden" value="${is_shoufei }" id="is_shoufei" />
	<div class="breadcrumb">
		<div class="home">
			<a href="javascript:void(0)"><i class="fa fa-home"></i></a> <a
				href="/index.jsp">首页&gt;</a> <a href="javascript:void(0)">${courseVideo.video_name }</a>
		</div>
	</div>
	<div class="clear"></div>
	<input type="hidden" id="course_id" value="${courseVideo.course_id }" />
	<!-- ****************************video************************** -->
	<div class="course-video">
	<c:if test="${course_num>0 }">
		 <div class="videos">
			<div class="vleft" id="video">
				<!-- 视频 -->
			</div>
			<div class="finish">
				<span>尊敬的用户：<br />试看已结束，如果您想继续学习，请购买该课程。
				</span>
				<c:if test="${empty sessionScope.user_session}">
					<div class="f_buy" style="cursor: pointer;" onclick="get_login()">立即购买</div>
				</c:if>
				<c:if test="${not empty sessionScope.user_session}">
					<div class="f_buy" style="cursor: pointer;"
						onclick="video_pay('${des_video_id}')">立即购买</div>
				</c:if>
			</div>
			<div class="vright">
				<div class="vright_title">
					<ul>
						<li onclick="get_video_list(this)" class="now">课程列表</li>
						<li onclick="get_ware(this)">课件下载</li>
					</ul>
				</div>

				<div class="content mCustomScrollbar">
					<div class="vright_tab_box">
						<div class="kc_lb mCustomScrollbar">
							<ul class="xiangxi">
								<c:forEach items="${courseVideos }" var="cour_video"
									varStatus="vs">
									<li class="chapter"><span class="zhangjie"><span><%-- 第${vs.index+1 }章&nbsp; --%>${cour_video.video_name }</span><i
											class="fa fa-chevron-down"></i></span>
										<div class="chapters" sid="${vs.index+1 }" id="sub_course_list${vs.index+1 }">
											<ul>
												<c:if test="${not empty cour_video.courseVideos_sanji }">
													<c:forEach items="${cour_video.courseVideos_sanji }"
														var="cour_video_sanji" varStatus="vs">
														<c:if test="${ empty myCourse }"><!-- 如果没有加入我得课程 -->
														<c:if test="${cour_video_sanji.is_jinzhi==1 }">
															<li class="kj_mc" pid="${vs.index+1}" jid="${cour_video_sanji.is_jinzhi }"
															id="xiaojie${cour_video_sanji.video_id }" bid="0"
															value="${cour_video_sanji.video_id }"><span
															class="s-playing">
																${vs.index+1}、${cour_video_sanji.video_name }<div
																	id="playing${cour_video_sanji.video_id }"
																	class="playing hide">
																</div>
														</span></li>
														</c:if>
														<c:if test="${cour_video_sanji.is_jinzhi==0 }">
														<li class="kj_mc" pid="${vs.index+1}" bid="0"
															id="xiaojie${cour_video_sanji.video_id }" jid="${cour_video_sanji.is_jinzhi }"
															onclick="get_video_player_url(${cour_video_sanji.video_id },${cour_video_sanji.is_jinzhi })"
															value="${cour_video_sanji.video_id }"><span
															class="s-playing">
																${vs.index+1}、${cour_video_sanji.video_name }<div
																	id="playing${cour_video_sanji.video_id }"
																	class="playing hide">
																	<div class="music active">
																		<i></i><i></i><i></i><i></i><i></i>
																	</div>
																</div>
														</span></li>
														</c:if>
														</c:if>
														<c:if test="${not empty myCourse }"><!-- 如果已经加入我的课程 -->
														<c:if test="${bofang_id==cour_video_sanji.video_id  }">
														<li class="kj_mc" pid="${vs.index+1}" bid="${bofang_id }"
															id="xiaojie${cour_video_sanji.video_id }" jid="${cour_video_sanji.is_jinzhi }"
															onclick="get_video_player_url(${cour_video_sanji.video_id },0)"
															value="${cour_video_sanji.video_id }"><span
															class="s-playing">
																${vs.index+1}、${cour_video_sanji.video_name }<div
																	id="playing${cour_video_sanji.video_id }"
																	class="playing hide">
																	<div class="music active">
																		<i></i><i></i><i></i><i></i><i></i>
																	</div>
																</div>
														</span></li>
														</c:if>
														<c:if test="${bofang_id!=cour_video_sanji.video_id  }">
														<li class="kj_mc" pid="${vs.index+1}" bid="0"
															id="xiaojie${cour_video_sanji.video_id }" jid="${cour_video_sanji.is_jinzhi }"
															onclick="get_video_player_url(${cour_video_sanji.video_id },0)"
															value="${cour_video_sanji.video_id }"><span
															class="s-playing">
																${vs.index+1}、${cour_video_sanji.video_name }<div
																	id="playing${cour_video_sanji.video_id }"
																	class="playing hide">
																	<div class="music active">
																		<i></i><i></i><i></i><i></i><i></i>
																	</div>
																</div>
														</span></li>
														</c:if>
														</c:if>
													</c:forEach>
												</c:if>
												<c:if test="${empty cour_video.courseVideos_sanji }">
													<li class="kj_mc play"><span>该章节暂无课程内容！</span></li>
												</c:if>
											</ul>
										</div></li>
								</c:forEach>
							</ul>
						</div>
						<div class="kc_xz hide">
							<ul>
								<c:if test="${empty courseWares }">
									<li>暂无课件</li>
								</c:if>
								<c:forEach items="${courseWares }" var="ware" varStatus="vs">
								<c:if test="${not empty myCourse }">
									<li>${vs.index+1 }.<a href="${ware.courseware_file }"
										download="${ware.courseware_name }.pdf">${ware.courseware_name }</a></li>
								</c:if>
								<c:if test="${empty myCourse }">
									<li>${vs.index+1 }.<a href="javascript:void(0)">${ware.courseware_name }</a></li>
								</c:if>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div> 
			</div>
			</c:if>
			<c:if test="${course_num==0 }">
			<div class="video_zanwu">
				<img src="/images/school/front/course_video/video_zanwu.png">
				<p class="video_zanwuwz">近期推出，敬请期待</p>
			</div>
			</c:if>
		</div>
	</div>
	<!-- ****************************content************************** -->
	<div class="centers">
		<!-- ****************************join us************************** -->
		<div class="join">
			<div class="join-left left">
				<div class="title">${courseVideo.video_name }&nbsp;${courseVideo.banxing }</div>
				<div class="message">${courseVideo.video_dis }</div>
				<div class="share">
					<ul>
						<li>价格： ${courseVideo.video_money_now }<!-- 0.00 -->元</li>
						<li>课时：${courseVideo.keshi_number }课时</li>
						<li>播放次数：${courseVideo.play_times }次</li>
						<li><img src="/images/school/front/course_video/share.jpg" />
							<img src="/images/school/front/course_video/qq.png" /> <img
							src="/images/school/front/course_video/weixin.png" /> <img
							src="/images/school/front/course_video/weibo.png" /></li>
					</ul>
				</div>
			</div>
			<div class="join-right right">
				<%-- <div class="jiage left">￥${courseVideo.video_money_now }</div> --%>
				<c:if test="${courseVideo.video_type=='免费' }">
					<c:if test="${not empty sessionScope.user_session}">
						<div class="btn_buy right">
							<c:if test="${not empty myCourse }">
								<input type="button" id="yijiaru" readonly="readonly"
									value="已加入" class="addkc">
							</c:if>
							<input type="button" id="yijiaru" style="display: none;"
								readonly="readonly" value="已加入" class="addkc">
							<c:if test="${empty myCourse}">
								<input type="button"
									onclick="save_my_course(${courseVideo.video_id },this)"
									value="加入我的课程" class="addkc">
							</c:if>
						</div>
					</c:if>

					<c:if test="${empty sessionScope.user_session}">
						<div class="btn_buy right">
							<input type="button" onclick="get_login()" value="加入我的课程"
								class="addkc">
						</div>
					</c:if>
				</c:if>
				<c:if test="${courseVideo.video_type=='付费' }">
					<div class="btn_buy right">
						<c:if test="${empty sessionScope.user_session}">
							<input type="button" onclick="get_login()" value="立即购买"
								class="addbuy">
							<input type="button" onclick="get_login()" value="加入购物车"
								class="addcar">
						</c:if>
						<c:if test="${not empty sessionScope.user_session}">
							<c:if test="${not empty myShoping }">
								<c:if test="${not empty myCourse }">
									<input type="hidden" value="1" id="is_my_course">
									<input type="button" id="yijiaru" readonly="readonly"
										value="已加入" class="addkc">
								</c:if>
								<c:if test="${empty myCourse }">
									<input type="button"
										onclick="video_pay('${des_video_id}')" value="立即购买"
										class="addbuy">
									<input type="button" onclick="to_go_shoping()" value="查看已加入购物车"
										class="addcar">
								</c:if>
							</c:if>
							<c:if test="${empty myShoping }">
								<c:if test="${empty myCourse }">
									<input type="button" id="gouwuche" style="display: none"
										value="查看已加入购物车" onclick="to_go_shoping()" class="addcar">
									<input type="button"
										onclick="save_my_shoping(${courseVideo.video_id },this)"
										value="加入购物车" class="addcar">
								</c:if>
								<c:if test="${not empty myCourse }">
									<input type="hidden" value="1" id="is_my_course">
									<input type="button" id="yijiaru" readonly="readonly"
										value="已加入" class="addkc">
								</c:if>
							</c:if>
						</c:if>
					</div>
				</c:if>
			</div>
		</div>
		<!-- ****************************xiangqing************************** -->
		<div class="clear"></div>
		<div class="xiangqing">
			<!-- ****************************xiangqing-l************************** -->
			<div class="xq-left">
				<div class="tab_menu">
					<ul class="sub-nav">
						<li class="on">课程详情</li>
						<li>课程目录</li>
						<li>常见问题</li>
						<li onclick="get_comment(${courseVideo.video_id })">学员评价</li>
					</ul>
				</div>
				<div class="tab_box">
					<div class="kecheng">
						<!-- 课程介绍 -->
						<c:if test="${empty courseVideo.course_jieshao }">
							<span>暂无课程介绍</span>
						</c:if>
						<c:if test="${not empty courseVideo.course_jieshao }">
							${courseVideo.course_jieshao }
						</c:if>
					</div>
					<div class="mulu hide">
						<ul>
							<c:forEach items="${courseVideos }" var="cour_video"
								varStatus="vs">
								<li class="mulu_zhangjie"
									onclick="get_mulu_course_video(${cour_video.video_id},this)">
									<span class="mulu-name"><i class="fa fa-mortar-board"></i>
										<p><%-- 第${vs.index+1 }章  --%>${cour_video.video_name }</p> <i
										class="fa fa-chevron-down"></i></span>
									<div class="clear"></div>
									<div class="line"></div>
									<div class="kejian hide" id="mulu_xiaojie_list${cour_video.video_id}">
										<!-- 目录章节列表 -->
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="wenti hide">
						<ul class="changjian">
							<li class="q1">怎样注册京人网校账号</li>
							<li class="q2">怎样学习</li>
							<li class="q3">怎样查看我的课程</li>
							<li class="q4">怎样搜索课程</li>
							<li class="q5">怎样修改密码</li>
						</ul>
						<div class="clear"></div>
						<div class="question">
							<div class="questions">
								<div class="title">
									<i class="fa fa-question-circle"></i>
									<p>问题一：怎样注册京人网校账号？</p>
								</div>
								<div class="clear"></div>
								<div>
									<img alt="如何购课" src="/images/school/front/course_video/howtobuy.png" style="width:100%;border:1px solid #ccc;">
								</div>
							</div>
							<div class="questions">
								<div class="title">
									<i class="fa fa-question-circle"></i>
									<p>问题二：怎样学习？</p>
								</div>
								<div class="clear"></div>
								<div>
									<img alt="怎样学习" src="/images/school/front/course_video/howtostudy.jpg" style="width:100%;border:1px solid #ccc;">
								</div>
							</div>
							<div class="questions">
								<div class="title">
									<i class="fa fa-question-circle"></i>
									<p>问题三：怎样查看我的课程？</p>
								</div>
								<div class="clear"></div>
								<div>
									<img alt="我的课程" src="/images/school/front/course_video/checkmycourse.png" style="width:100%;border:1px solid #ccc;">
								</div>
							</div>
							<div class="questions">
								<div class="title">
									<i class="fa fa-question-circle"></i>
									<p>问题四：怎样更换头像</p>
								</div>
								<div class="clear"></div>
								<div>
									<img alt="更换头像" src="/images/school/front/course_video/checkmycourse.png" style="width:100%;border:1px solid #ccc;">
								</div>
							</div>
							<div class="questions">
								<div class="title">
									<i class="fa fa-question-circle"></i>
									<p>问题五：怎样修改密码？</p>
								</div>
								<div class="clear"></div>
								<div>
									<img alt="修改密码" src="/images/school/front/course_video/howtopsd.png" style="width:100%;border:1px solid #ccc;">
								</div>
							</div>
						</div>
					</div>
					<div class="pingjia" id="comment_list">
						<!--视频评论  -->
					</div>
				</div>
			</div>
			<!-- ****************************xiangqing-r************************** -->
			<div class="xq-right">
				<!-- ****************************教师************************** -->
				<div class="r-teacher">
					<div class="title">
						<img src="/images/school/front/course_video/teacher.png" /> <span>讲师介绍</span>
					</div>
					<div class="clear"></div>
					<div class="t-message">
						<img width="84px" height="84px" src="${teacher.teacher_pic }" />
						<p>${teacher.teacher_name }</p>
						<div class="text">${teacher.teacher_dis }</div>
					</div>
				</div>
				<!-- ****************************推荐************************** -->
				<div class="r-recommend" id="course_recommed_list">
					<!-- 课程推荐 -->
				</div>

			</div>
			<!-- ****************************xiangqing-r    end************************** -->
		</div>
		<!-- ****************************xiangqing    end************************** -->
		<div class="clear"></div>
	</div>
<!-- <script type="text/javascript">
//左侧菜单的滚动第二种
$(function(){
    $(window).scroll(function(){
       var top = $(document).scrollTop();          //定义变量，获取滚动条的高度
       var menu = $(".changjian");                      //定义变量，抓取#menu
       var items = $(".question").find(".questions");    //定义变量，查找.item
       // var menuli=menu.find("li");
       var curId = "";                             //定义变量，当前所在的楼层item #id 

       items.each(function(){
           var m = $(this);                        //定义变量，获取当前类
           var itemsTop = m.offset().top;        //定义变量，获取当前类的top偏移量
           if(top > itemsTop-100){
               curId = "#" + m.attr("id");
           }else{
               return false;
           }
       });
           
       //给相应的楼层设置cur,取消其他楼层的cur
       var curLink = menu.find(".cur");
       if( curId && curLink.attr("href") != curId ){
           curLink.removeClass("cur");
           menu.find( "[href=" + curId + "]" ).addClass("cur");

       }
   });
       function SideBar(opt){
         var self=this;
           this.opt={
               sidebar:'',
               items:''
           };
           this.opt=opt;
           this.proboxTop=[];//每个详细模块的偏移量
           this.opt.sidebar.find('li').eq(0).addClass('cur');
           $.each(self.opt.items,function(i){//循环将每个模块对应的偏移量存入数组中
               self.proboxTop[i]=self.opt.items.eq(i).offset().top;
           });

           this.init();//执行init()函数；核心函数
       }
       SideBar.prototype={
           init:function(){
               this.clickTo();
               this.scrollTo();

           },
           //点击跳转到某个模块
           clickTo:function(){
               var self=this;
               this.opt.sidebar.find('li').finish().click(function(){
                   var i=$(this).index();//获取点击的li对应的索引
                  $('html,body').finish().animate({
                      scrollTop:self.proboxTop[i]//移动到当前与当前索引相同的item上
                  },200);
                   //当前添加类cur其他的兄弟元素去掉cur这个类
                   $(this).addClass('cur').siblings().removeClass('cur');
               });
           },
           //滚动到对应位置显示高亮
           scrollTo:function() {
               var self = this;
               //刷新页面时获取当前对应的高亮处的位置
               this.change($(window).scrollTop());
               //滑动滚动条的变化对应左侧导航栏高亮的变化
               $(window).on('scroll',function(){
                 self.change($(this).scrollTop());
               });
           },
           //左侧高亮变化函数
           change:function(scrollTop){
               //第一张到倒数第二张的判断
                   var self=this;
               var el=self.opt.sidebar;
               for(var i=0;i<self.proboxTop.length-1;i++){
                   if(self.proboxTop[i]<=scrollTop&&self.proboxTop[i+1]>scrollTop){
                      el.find('li').eq(i).addClass('cur').siblings().removeClass('cur');
                   }
               }
               //最后一张的判断
               if(self.proboxTop[self.proboxTop.length-1]<=scrollTop){
                  el.find('li').eq(self.proboxTop.length-1).addClass('cur').siblings().removeClass('cur');
               }
           }
       }
       var scroll=new SideBar({
           sidebar:$('.changjian'),
           items:$(".question").find(".questions")
       });
   });
</script> -->
	<!--登录注册窗口  -->
	<jsp:include
		page="/WEB-INF/front_page/school/common/dengluzhuce_common.jsp"></jsp:include>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/videofoot.jsp"></jsp:include>
</body>
</html>