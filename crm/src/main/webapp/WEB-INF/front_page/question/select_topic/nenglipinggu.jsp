<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/question/index/reset.css" />
<link rel="stylesheet" type="text/css"
	href="/css/question/select_question/question-common.css" />
<link href="/css/school/back/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/css/iconfont/iconfont.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/js/question/question-common.js"></script>
<script type="text/javascript" src="/js/question/front/frontchapter.js"></script>
<script type="text/javascript" src="/js/question/echarts.js"></script>
<title>京人网校题库-练习记录</title>
</head>
<body>
	<div class="question_title">
		<div class="que_top">
			<a href="/index.jsp"><img
				src="/images/school/front/index/logo.png" /></a>
			<div class="question_tab">
				<h1>${courseMenu.course_name }</h1>
				<h2>${questionCourse.question_course_name }</h2>
				<div class="question_others">
					<a href="javascript:void(0)">[切换]</a>
					<div class="qiehuan">
						<ul>
							<i class="fa fa-caret-up"></i>
							<div class="top_qiehuan">
								<h3>${courseMenu.course_name }</h3>
								<a href="/questionindex/get_question_index.html">[切换考试]</a>
							</div>
							<c:forEach items="${questionCourses }" var="question">
								<li><a
									href="/front_chapter/get_chapter_list.html?question_course_id=${question.question_course_id }">${question.question_course_name }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="que_nav">
		<ul>
			<li onclick="get_question_index(${questionCourse.course_id})"><i
				class="icon iconfont icon-fangzi"></i>题库首页</li>
			<li onclick="get_chapter_list(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-shu"></i>章节练习</li>
				<li onclick="get_zhenti_year(${questionCourse.question_course_id})"><i
					class="icon iconfont icon-shitiguanli"></i>历年真题</li>
				<li onclick="get_moni_kaoshi(${questionCourse.question_course_id})"><i
					class="icon iconfont icon-kaoshi"></i>模拟考试</li>
			<li
				onclick="get_chapter_record_main(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-zhihebi"></i>练习记录</li>
			<li class="on"
				onclick="get_nengli_main(${questionCourse.question_course_id})"><i
				class="icon iconfont icon-baogaopinggu"></i>能力评估报告</li>
		</ul>
	</div>
	<input type="hidden" value="${questionCourse.question_course_id}" id="question_course_id" />
	<div class="que_content" style="border: none; min-height: 363px;">
		<div class="nengli_pinggu">
			<div class="nengli_pinggu_title">
				<ul class="nengli_shuju">
					<li><span class="wenzi">练习</span> <span class="number"><em>${chapter_record_number}</em>题</span>
						<span class="tubiao"><img
							src="/images/question/chapter/tubiao.png" /></span></li>
					<li><span class="wenzi">模考</span> <span class="number"><em>${moni_record_number }</em>次</span>
						<span class="tubiao"><img
							src="/images/question/chapter/tubiao.png" /></span></li>
					<li><span class="wenzi">正确率</span> <span class="number"><em><fmt:formatNumber
									type="number" value="${zhengquelv*100 }" pattern="0.00"
									maxFractionDigits="2" />%</em></span> <span class="tubiao"><img
							src="/images/question/chapter/tubiao.png" /></span></li>
					<li><span class="wenzi">预测得分</span> <span class="number"><em><fmt:formatNumber
									type="number" value="${(zhengquelv+0.05)*120 }"
									maxFractionDigits="0" /></em>分</span> <c:if test="${zhengquelv<0.6 }">
							<span class="tubiao">您目前的正确率<em>偏低</em>，需加长学习时间！
							</span>
						</c:if> <c:if test="${zhengquelv>0.6 }">
							<span class="tubiao">请保持您目前的学习效率哦！</span>
						</c:if></li>
				</ul>
				<div class="nengli_tuxiang">
					<p>最近15次考试得分曲线图</p>
					<span>得分情况</span>
				</div>
			</div>
			<div class="quxiantu" id="quxian" style="height: 400px"></div>
		</div>
	</div>
	<!--底部  -->
	<jsp:include page="/WEB-INF/front_page/school/common/videofoot.jsp"></jsp:include>
</body>
<script>
$(function(){
	var question_course_id=$("#question_course_id").val();
	$.ajax({
		type : "POST",// 请求方式
		url : "/front_zhenti/quxiantu_limit.html",// 地址，就是action请求路径
		dataType : "json",// 数据类型text xml json script jsonp
		data:{'question_course_id',question_course_id},
		success : function(msg) { // 返回的参数就是 action里面所有的有get和set方法的参数
			var myChart = echarts.init(document.getElementById('quxian')); 
			var option = {
			    tooltip : { //提示框组件
			        trigger: 'axis', //'item'数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。'axis'坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用。
				},
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : true,//坐标轴两边留白
						axisLine:{  //坐标轴轴线相关设置
			                   lineStyle:{  
			                       color:'#69a5eb' ,//线条颜色
			                    } ,
								
			           },
						axisTick:{
			               show:false  //禁用刻度线
			           },
					   axisLabel:{
						   show:false,
							   textStyle:{
			                 color:"#69a5eb", //X轴文字颜色
			                 fontSize:14  //X轴文字大小
			            }
						   },
			            data : msg.cishu
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
						splitLine:{
							lineStyle: {
			                  color: '#69a5eb', //网格线颜色设置
			              }
							},
			            axisLabel : {
							/*show:false,*/ //Y轴文字是否显示
			                formatter: '{value} 分',
							textStyle:{
			                 color:"#333", //Y轴文字颜色
			                 fontSize:16  //Y轴文字大小
			            }
			            },
						axisLine:{
			        show:false  //禁用x/y轴
			           },
						axisTick:{
			        show:false  //禁用刻度线
			           },
					   interval: 20,// 刻度间距
					   max:120,  //刻度最大值
			        }
			    ],
			    series : [
			        {
			            name:'分数',
			            type:'line',//统计图类型
						/*smooth:true,//平滑曲线*/
			            data:msg.defen,//数据文本
						label: {
			                normal: {
			                    show: true,//数据文本是否显示
			                    position: 'top',//数据文本位置
								textStyle: {
			                           color: '#333',//数据文本颜色
									   fontSize:16,
			                         }
			                }
			            },
						symbolSize: 10,//拐点大小设置
						itemStyle : {  
			                                normal : {  
										        
			                                    lineStyle:{  
			                                        color:'#69a5eb' ,//线条颜色
													width:3,//线条粗细
			                                    }, 
												 borderColor:'#69a5eb',//拐点颜色
												 
			                                }  
			                            }, 
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            },
			            markLine : {
							itemStyle : {  
			                                normal : {  
										        
			                                    lineStyle:{  
			                                        color:'#69a5eb' ,//线条颜色
													width:1,//线条粗细
			                                    },
												 
			                                }  
			                            }, 
			                data : [
			                    {type : 'average', name: '平均值'}
			                ]
			            }
			        }
			    ]
			};
			 // 为echarts对象加载数据 
			myChart.setOption(option); 
		}
	});
})
</script>
</html>