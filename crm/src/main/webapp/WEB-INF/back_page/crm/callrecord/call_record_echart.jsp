<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet"
	href="/font-awesome-4.6.3/css/font-awesome.min.css" />
<link rel="stylesheet" href="/css/crm/index/qiyeku.css" />
<link rel="stylesheet" href="/css/page/pages.css" />
<link rel="stylesheet" type="text/css" href="/css/crm/index/crm.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script src="/js/crm/call/record_tongji.js"></script>
<script src="/js/question/echarts.js"></script>

<style>
.select2-chosen {
	color: white !important;
}

.form-group .right_wz {
	text-align: right;
	line-height: 35px;
}

.form-control {
	height: 35px;
}

.form-group {
	margin-bottom: 10xp !important;
}

.form-control:focus {
	border: 2px solid #94CE6E;
}
p.jl_all {
    text-align:center;
    color: #94CE6E;
    font-size: 16px;
    margin: 20px auto;
}
</style>
<script type="text/javascript">
$(function(){
	$.post("/crm_call/get_all_call_echart.jr",function(data){
		baobiao(data);
		$("#jilu").html(data.length);
	})
})
//查询
function chaxun_call_chart(obj,page,limit){
	$(obj).attr({"disabled":"disabled"});
	$(obj).html("查询中");
	$("#myform").ajaxSubmit({
		type : 'POST',
		url : "/crm_call/get_all_call_echart.jr",
		data:{
			'pageNumber':page,
			'limit':limit
		},
		success : function(data) {
			baobiao(data);
			$("#jilu").html(data.length);
			$(obj).removeAttr("disabled");
			$(obj).html("查询");
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			tanchuang('查询失败');
		}
	});
}
function baobiao(data){
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('shichang'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            /* text: 'ECharts 入门示例' */
        },
        tooltip: {//提示框组件
    trigger: 'axis', //'item'数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。'axis'坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用。
    textStyle:{  //提示框浮层样式
		color:"#ffffff", 
             fontSize:14  
		},
			},
		toolbox: {   //工具栏
        show : true,
        left:'260px',
		top:'1%',
		itemSize:'18',//工具栏 icon 的大小。
        feature : { //各工具配置项。
           mark : {show: true},
           dataView : {show: true, readOnly: false,iconStyle:{
		normal:{
			borderColor:'#94CE6E',//工具栏 icon 的颜色
			}}},//数据视图工具，可以展现当前图表所用的数据，编辑后可以动态更新。
           /*magicType : {show: true, type: ['line', 'bar']},*///动态类型切换
           /*restore : {show: true},*///配置项还原。
           saveAsImage : {show: true,iconStyle:{
		normal:{
			borderColor:'#69a5eb',//工具栏 icon 的颜色
			}}}//保存为图片。
        }
},
        legend: { //图例组件
		    selectedMode: false,
		    left:'50',
			top:'1%',
            data:['通话时长(分钟)'],
			textStyle:{
				fontSize:14,
				},
			/*backgroundColor:'#69a5eb',*///图例部分背景色
			Color:'#94CE6E',//图例文字颜色
        },
        xAxis: {
			type : 'value',
			axisTick:{ 
            show:false  //禁用刻度线
       }
        },
        yAxis: [
    {
		type: 'category',//类目轴
		axisTick:{ 
            show:false  //禁用刻度线
       },
	   	axisLabel:{
			  interval:0,// 信息全部显示
		   },
        type : 'category',
        data: data.name,
    }
], 
        series: [{
            name: '通话时长(分钟)',
            type: 'bar',
            barMaxWidth:'20%',//柱条的最大宽度，不设时自适应。支持设置成相对于类目宽度的百分比。
            data: data.fenzhong,
			label: {
            normal: {
                show: true,//数据文本是否显示
                position: 'right',//数据文本位置
				textStyle: {
                       color: '#333',//数据文本颜色
					   fontSize:16,
                     }
            }
        },
			markPoint : {//图标标注
            data : [
                {type : 'max', name: '最大值'},
                {type : 'min', name: '最小值'}
            ],
			label:{ 
				   normal:{
					   textStyle:{
						   color:'#fff'
						   }
						}
				},
				itemStyle:{ 
				   normal:{
						   color:'#69a5eb'
						}
				}
        },
		itemStyle:{  //柱状图的背景色颜色
           normal:{
           color:'#94CE6E',
         }
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
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
    </script>
<div class="back_right" style="height: 100%;">
	<div class="back_r_top">
		<div class="left">通话统计</div>
		<div class="right" id="timer"></div>
	</div>

	<div class="right_content" style="height: 100%;">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group clearfix" style="padding-top: 10px;">
				<label class="col-xs-1 right_wz">通话时间：</label>
				<div class="col-xs-2">
					<input class="layui-input" placeholder="选择开始日期" name="start_time"
						id="start_time">
				</div>
				<label class="col-xs-1 right_wz">到：</label>
				<div class="col-xs-2">
					<input class="layui-input" placeholder="选择结束日期" name="end_time"
						id="end_time">
				</div>
			</div>

			<div class="form-group clearfix" style="margin-top: 0px;">
				<label class="col-xs-1 right_wz">通话类型：</label> <select
					class="col-xs-2" name="record_type"
					style="line-height: 35px; height: 35px;">
					<option value="">请选择</option>
					<option value="呼入">呼入</option>
					<option value="呼出">呼出</option>
				</select>
				<label class="col-xs-1 right_wz">部门：</label>
				<select class="col-xs-2" name="bumen_id" style="line-height:35px;height:35px;">
					<option value="">请选择</option>
					<option value="6">市场一部</option>
					<option value="7">市场二部</option>
					<option value="8">市场三部</option>
					<option value="9">市场四部</option>
					<option value="10">市场五部</option>
					<option value="11">市场六部</option>
					<option value="12">市场七部</option>
				</select>
				<div class="col-xs-2">
					<button type="button"
						onclick="chaxun_call_chart(this,${pageNumber},${limit})"
						class="btn btn-primary btn-lg"
						style="width: 100px; height: 35px; background-color: #94CE6E; line-height: 15px;">查询</button>
				</div>
			</div>
		</form>
		<div class="operation">
			<div class="opera_left left">
				<button type="button"
					onclick="to_check_echart(${pageNumber},${limit },'${start_time }','${end_time }','${employee_id }','${record_type }','${bumen_id }')" class="btn left">
					<i class="fa fa-refresh"></i>刷新
				</button>
				<button type="button"
					onclick="record_jump_page(${pageNumber},${limit },'${start_time }','${end_time }','${employee_id }','${record_type }','${bumen_id }')"
					class="btn left">
					<i class="fa fa-mail-reply"></i>返回
				</button>
			</div>
		</div>
		<p class="jl_all">总记录数：<span id="jilu"></span>人</p>
		<div class="content_message" style="height: 100%;">
			<div id="shichang" style="height: 100%; border-top:#E6E9F0 solid 3px;">
				<div class="loader-inner ball-spin-fade-loader">
					<div></div>
					<div></div>
					<div></div>
					<div></div>
					<div></div>
					<div></div>
					<div></div>
					<div></div>
					<p
						style="color: #94CE6E; position: absolute; top: 45px; left: -35px;">正在加载...</p>
				</div>
			</div>
		</div>
	</div>