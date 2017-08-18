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
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
.select2-chosen {
	color: white !important;
}
</style>
<script>
	$(function() {
		shuaxin();
	})
	//刷新当前页面
	function shuaxin(is_ip) {
		if(is_ip!=0){
			$.ajax({
				type : 'GET', //这里用GET
				url : 'http://192.168.1.251/api.php',
				dataType : 'jsonp', //类型
				data : {
					'f' : 'getAllExtenStatus'
				},//获取所有分机号码以及状态
				jsonp : 'callback', //jsonp回调参数，必需
				async : false,
				success : function(result) {//返回的json数据
					// var str = unescape(result.replace(/\\u/g, "%u")); 
					var json = eval('(' + result + ')');
					var aToStr = JSON.stringify(json.list);
					if ($.trim(json.status) == 'true') {
						$("#zuoxi_list").html("<div id='preloader_3'></div>");
						$.post("/back_zuoxi/get_zuoxi_list.jr", {
							'zuoxi_list' : aToStr
						}, function(data) {
							$("#zuoxi_list").html(data);
						})
					} else {
						layer.msg("第三方系统发生错误，请联系技术部！");
					}
				}
			})
		}else{
			layer.msg("您没有在公司上网！无法访问坐席！");
		}
	}
</script>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">坐席管理</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<div class="operation">
			<div class="opera_left left">
				<button type="button" onclick="shuaxin(${is_ip})" class="btn left"
					id="add_company">
					<i class="fa fa-refresh"></i>刷新
				</button>
			</div>
		</div>
		<div class="content_message" id="zuoxi_list"></div>