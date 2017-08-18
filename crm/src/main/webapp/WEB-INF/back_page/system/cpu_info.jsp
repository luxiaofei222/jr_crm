<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
$(function () {                                                                     
	$(document).ready(function() {                                                  
		Highcharts.setOptions({                                                     
			global: {                                                               
				useUTC: false                                                       
			}                                                                       
		});                                                                         
																					
		var chart;                                                                  
		$('#container').highcharts({                                                
			chart: {                                                                
				type: 'spline',                                                     
				animation: Highcharts.svg, // don't animate in old IE               
				marginRight: 0,                                                    
				events: {                                                           
					load: function() {                                              
																					
						// set up the updating of the chart each second             
						var series = this.series[0];                                
						var series1 = this.series[1];                                
						var series2 = this.series[2];  
						setInterval(function() {
							 $.ajax({
									type : "POST",
									url : "/back_system/get_cpu_shiyong.jr",
									async : true,
									success : function(data) {
										var x = (new Date()).getTime(),
											y = parseFloat(data.cupuse);
											series.addPoint([x, y], true, true); 
										var x1 = (new Date()).getTime(),
											y1 = parseFloat(data.kongxian);
											series1.addPoint([x1, y1], true, true);
										var x2 = (new Date()).getTime(),
											y2 = parseFloat(data.zongshiyong);
											series2.addPoint([x2, y2], true, true);
										}
									});
						}, 15000); 
					}                                                               
				}                                                                   
			},
			credits: {
                enabled: false
            },                                                                   
			title: {                                                                
				text: 'CPU动态分析'                                            
			},                                                                      
			xAxis: {                                                                
				type: 'datetime',                                                   
				tickPixelInterval: 100                                              
			},                                                                      
			yAxis: {                                                                
				title: {                                                            
					text: 'CPU动态分析'                                                   
				},                                                                  
				plotLines: [{                                                       
					value: 0,                                                       
					width: 1,                                                       
					color: '#808080'                                                
				}]                                                                  
			},                                                                      
			tooltip: {                                                              
				formatter: function() {                                             
						return '<b>'+ this.series.name +'</b><br/>'+                
						Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
						Highcharts.numberFormat(this.y, 2);                         
				}                                                                   
			},                                                                      
			legend: {                                                               
				enabled: false                                                      
			},                                                                      
			exporting: {                                                            
				enabled: false                                                      
			},                                                                      
			series: [{                                                              
				name: 'CPU用户使用率',                                                
				data: (function() {                                                 
					// generate an array of random data                             
					var data = [],                                                  
						time = (new Date()).getTime(),                              
						i;                                                          
																					
					for (i = -6; i <= 0; i++) {                                    
						data.push({                                                 
							x: time+i*15000,                                     
							y: 0                                      
						});                                                         
					}                                                               
					return data;                                                    
				})()                                                                
			},{                                                              
				name: 'CPU空闲率',                                                
				data: (function() {                                                 
					// generate an array of random data                             
					var data = [],                                                  
						time = (new Date()).getTime(),                              
						i;                                                          
																					
					for (i = -6; i <= 0; i++) {                                    
						data.push({                                                 
							x: time+i*15000,                                     
							y: 0                                      
						});                                                         
					}                                                               
					return data;                                                    
				})()                                                                
			},{                                                              
				name: 'CPU总使用率',                                                
				data: (function() {                                                 
					// generate an array of random data                             
					var data = [],                                                  
						time = (new Date()).getTime(),                              
						i;                                                          
																					
					for (i = -6; i <= 0; i++) {                                    
						data.push({                                                 
							x: time+i*15000,                                     
							y: 0                                      
						});                                                         
					}                                                               
					return data;                                                    
				})()                                                                
			}]                                                                      
		});                                                                         
	});                                                                             
																					
	});               
</script>
<div id="container" style="width:700px;height:400px;margin:0 auto;border-left:1px solid #ccc;"></div>