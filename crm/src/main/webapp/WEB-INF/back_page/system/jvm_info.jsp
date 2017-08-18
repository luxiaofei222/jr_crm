<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/js/question/echarts.js"></script>
<script>
	$(function() {
		$.post("/back_system/get_cpu_info.jr", function(data) {
			$("#cpu_info").html(data);
		})
	})
</script>
<style>
body {
	background-color: #f5f5f5 !important;
}

.ht_right {
	background-color: #f1f1f1;
}

.hq_yonghu {
	width: 900px;
	float: left;
}

.hq_yonghu li {
	float: left;
	width: 440px;
	line-height: 35px;
	color: #fff;
	font-size: 14px;
	border-bottom: #fff solid 1px;
	border-right: #f1f1f1 solid 1px;
	padding: 0px 15px;
}

.hq_yonghu li:nth-child(1), .hq_yonghu li:nth-child(2) {
	border-top: #f1f1f1 solid 1px;
}

.hq_yonghu li:nth-child(odd) {
	border-left: #f1f1f1 solid 1px;
	background-color: #0781e8;
}

.hq_yonghu li:nth-child(even) {
	background-color: orange;
}

strong {
	font-weight: bold;
	margin-right: 10px;
}

.huoqu_yonghu {
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	height: 500px;
	min-width: 1642px;
}

.chart_totle {
	background-color: #f5f5f5;
	border-radius: 10px;
	min-width: 1642px;
	margin-top: 10px;
	overflow: hidden;
}

.charts {
	float: left;
	margin-left: 10px;
	background-color: oldlace !important;
	padding: 10px;
	border-radius: 25px;
}

.charts:nth-child(1) {
	margin-left: 0px !important;
}

.huoqu_yonghu h2 {
	color: #666;
	font-weight: normal;
	border-left: #06c1ae solid 5px;
	padding-left: 10px;
	font-size: 18px;
	margin-bottom: 20px;
}
</style>
<div style="padding: 20px;">
	<div class="huoqu_yonghu">
		<h2>系统信息</h2>
		<div style="overflow: hidden;">
			<ul class="hq_yonghu">
				<c:forEach items="${sigarInfos }" var="info">
					<li><strong>${info.name }:</strong>${info.value }</li>
				</c:forEach>
			</ul>
			<div id="cpu_info" style="float: right;">
				<!-- cpu监控 -->
			</div>
		</div>
	</div>
	<div class="chart_totle">
		<div id="chart_one" style="width: 400px; height: 400px;"
			class="charts">
			<script type="text/javascript">
			$(function(){
				$.ajax({
					type : "POST",
					url : "/back_system/get_jvm_neicun_info.jr",
					async : true,
					success : function(data) {
						// 基于准备好的dom，初始化echarts实例
						var myChart = echarts
								.init(document.getElementById('chart_one'));

						// 指定图表的配置项和数据
						option = {
							title : {
								text : 'JVM内存监测',
								x : 'center'
							},
							tooltip : {
								trigger : 'item',
								formatter : "{a} <br/>{b} : {c} ({d}%)"
							},
							legend : {
								orient : 'vertical',
								left : 'left',
								data : [ '已用内存', '剩余内存' ]
							},
							series : [ {
								name : 'JVM内存(单位:MB)',
								type : 'pie',
								radius : '55%',
								center : [ '50%', '60%' ],
								data : [ {
									value : data.yishiyongnum,
									name : '已用内存'
								}, {
									value : data.shengyunum,
									name : '剩余内存'
								} ],
								itemStyle : {
									emphasis : {
										shadowBlur : 10,
										shadowOffsetX : 0,
										shadowColor : 'rgba(0, 0, 0, 0.5)'
									},
								}
							} ],
							color: ['rgb(254,67,101)','rgb(252,157,154)','rgb(131,175,155)']
						};
						// 使用刚指定的配置项和数据显示图表。
						myChart.setOption(option);
						}
					});
			})
			</script>
		</div>

		<div id="chart_two" style="width: 400px; height: 400px;"
			class="charts">
			<script type="text/javascript">
			$(function(){
				$.ajax({
					type : "POST",
					url : "/back_system/get_cpu_neicun_info.jr",
					async : true,
					success : function(data) {
						// 基于准备好的dom，初始化echarts实例
						var myChart = echarts
								.init(document.getElementById('chart_two'));

						// 指定图表的配置项和数据
						option = {
							title : {
								text : '物理内存监测',
								x : 'center'
							},
							tooltip : {
								trigger : 'item',
								formatter : "{a} <br/>{b} : {c} ({d}%)"
							},
							legend : {
								orient : 'vertical',
								left : 'left',
								data : [ '已用内存', '剩余内存' ]
							},
							series : [ {
								name : '物理内存(单位：MB)',
								type : 'pie',
								radius : '55%',
								center : [ '50%', '60%' ],
								data : [ {
									value : data.yishiyongnum,
									name : '已用内存'
								}, {
									value : data.shengyunum,
									name : '剩余内存'
								} ],
								itemStyle : {
									emphasis : {
										shadowBlur : 10,
										shadowOffsetX : 0,
										shadowColor : 'rgba(0, 0, 0, 0.5)'
									}
								}
							} ]
						};
						// 使用刚指定的配置项和数据显示图表。
						myChart.setOption(option);
					}
				});
			})
			</script>
		</div>
		<div id="chart_three" style="width: 400px; height: 400px;"
			class="charts">
			<script type="text/javascript">
			$(function(){
				$.ajax({
					type : "POST",
					url : "/back_system/get_jiaohuan_neicun_info.jr",
					async : true,
					success : function(data) {
				// 基于准备好的dom，初始化echarts实例
				var myChart = echarts.init(document
						.getElementById('chart_three'));

				// 指定图表的配置项和数据
				option = {
					title : {
						text : '交换区内存监测',
						x : 'center'
					},
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					},
					legend : {
						orient : 'vertical',
						left : 'left',
						data : [ '已用内存', '剩余内存' ]
					},
					formatter:function(val){    return val.split("-").join("\n");},
					series : [ {
						name : '交换区内存(单位:MB)',
						type : 'pie',
						radius : '55%',
						center : [ '50%', '60%' ],
						data : [ {
							value : data.yishiyongnum,
							name : '已用内存'
						}, {
							value : data.shengyunum,
							name : '剩余内存'
						} ],
						itemStyle : {
							emphasis : {
								shadowBlur : 10,
								shadowOffsetX : 0,
								shadowColor : 'rgba(0, 0, 0, 0.5)'
							}
						}
					} ],
					color:['#19AE54','#FDBF2E']
				};

				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
					}
					});
				})
			</script>
		</div>
		<div id="chart_four" style="width: 400px; height: 400px;"
			class="charts">
			<script type="text/javascript">
			$(function(){
				$.ajax({
					type : "POST",
					url : "/back_system/get_file_neicun_info.jr",
					async : true,
					success : function(data) {
				// 基于准备好的dom，初始化echarts实例
				var myChart = echarts.init(document
						.getElementById('chart_four'));

				// 指定图表的配置项和数据
				option = {
					title : {
						text : '文件内存监测',
						x : 'center'
					},
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					},
					legend : {
						orient : 'vertical',
						left : 'left',
						data : [ '已用内存', '剩余内存' ]
					},
					series : [ {
						name : '数据盘内存(单位:GB)',
						type : 'pie',
						radius : '55%',
						center : [ '50%', '60%' ],
						data : [ {
							value : data.yiyong,
							name : '已用内存'
						}, {
							value : data.shengyu,
							name : '剩余内存'
						} ],
						itemStyle : {
							emphasis : {
								shadowBlur : 10,
								shadowOffsetX : 0,
								shadowColor : 'rgba(0, 0, 0, 0.5)'
							}
						}
					} ],
					color:['#00B8C0','#8ED5E7']
				};
				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
					}
				});
			})
			</script>
		</div>
	</div>
</div>