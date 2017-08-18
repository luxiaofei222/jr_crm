<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/school/front/index/reset.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script src="/js/school/front/course_video/jquery-1.11.1.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="/js/school/front/course_video/jquery.raty.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>查看任务详情</title>
<style type="text/css">
.left {
	float: left;
}

.right {
	float: right;
}

.clear {
	clear: both;
}

ul {
	width: 650px;
	margin: 30px auto;
}

ul li {
	width: 650px;
	min-height: 35px;
	margin-top: 20px;
}

label {
	width: 90px;
	line-height: 35px;
	text-align: right;
	font-size: 16px;
}

input {
	width: 215px;
	height: 35px;
	line-height: 35px;
	border: #CCC solid 2px;
	border-radius: 5px;
	padding: 0px 5px;
}

textarea {
	width: 545px;
	height: 80px;
	border: #CCC solid 2px;
	border-radius: 5px;
	padding: 5px;
	line-height: 20px;
}

a {
	width: 80px;
	height: 35px;
	line-height: 35px;
	text-align: center;
	background-color: #3498db;
	color: #fff;
	display: block;
	border-radius: 5px;
	margin-right: 15px;
}

a.send {
	margin-left: 90px;
}

a.guan {
	background-color: #d9534f;
}

a:hover {
	color: #fff;
}

em {
	display: block;
	width: 12px;
	height: 12px;
	border-radius: 50%;
	background-color: #1abc9c;
	margin-top: 12px;
}

em.no {
	background-color: #F9D749;
}

em.end {
	background-color: #f1c40f;
}

span {
	line-height: 35px;
	margin-left: 10px;
}
#scoreName img {
    float: left;
    margin-top: 5px;
    margin-right: 5px;
}

</style>
<script>
	$(function() {
		$.fn.raty.defaults.path = '/images/school/front/course_video';
		$('#scoreName').raty({
			 number: 10,
			width : 320,
			hints: ['1分', '2分', '3分', '4分', '5分','6分', '7分', '8分', '9分', '10分'],
			scoreName : 'entity[score]',
			size : 24,
			click : function(score, evt) {
				$("#pingfen").val(score);
			}
		// 星级评分
		});
	});
	//关闭弹窗
	function close_layer(){
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		parent.layer.close(index);
	}
	//评价任务
	function pingjia_task(task_id,task_type){
		if(task_type=="进行中"){
			layer.msg("进行中的任务不能进行评价！");
		}else{
			var pingfen=$("#pingfen").val();
			var pingjia_content=$("#pingjia_content").val();
			$.post("/crm_task/pingfen_task.jr",{
				'task_id':task_id,
				'pingfen':pingfen,
				'pingjia_content':pingjia_content
			},function(data){
				if(data==1){
					layer.msg("恭喜您评价成功！");
				}else{
					layer.msg("很遗憾评价失败！");
				}
			})
		}
	}
</script>
</head>
<body>
	<ul>
		<li>
			<div class="item left">
				<label class="left">发布人：</label>
				<div class="info left">
					<input type="text" value="${faburen.employee_name }" disabled="disabled" />
				</div>
			</div>
			<div class="item right">
				<label class="left">发布日期：</label>
				<div class="info left">
					<input disabled="disabled" value="<fmt:formatDate type="both"
									value="${oaTask.task_time }" />" type="text" />
				</div>
			</div>
			<div class="clear"></div>
		</li>
		<li>
			<div class="item left">
				<label class="left">接收人：</label>
				<div class="info left">
					<input type="text" value="${jieshou.employee_name }" disabled="disabled" />
				</div>
			</div>
			<div class="item right">
				<label class="left">完成日期：</label>
				<div class="info left">
					<input type="text" value="<fmt:formatDate pattern="yyyy-MM-dd"
									value="${oaTask.task_finish_time }" />" disabled="disabled" />
				</div>
			</div>
			<div class="clear"></div>
		</li>
		<li>
			<div class="item">
				<label class="left">任务内容：</label>
				<div class="info left">
					<textarea disabled="disabled">${oaTask.task_content }</textarea>
				</div>
			</div>
			<div class="clear"></div>
		</li>
		<li>
			<div class="item">
				<label class="left">任务状态：</label>
				<div class="info left">
				<c:if test="${oaTask.zhongyao_state==0 }">
					<em class="ing left"></em>
				</c:if>	
				<c:if test="${oaTask.zhongyao_state==1 }">
					<em class="no left"></em>
				</c:if>	
				<c:if test="${oaTask.zhongyao_state==2 }">
					<em class="end left"></em>
				</c:if>	
					<span class="left">${oaTask.task_type }</span>
				</div>
			</div>
			<div class="clear"></div>
		</li>
		<li>
			<div class="item">
				<label class="left">任务反馈：</label>
				<div class="info left">
					<textarea disabled="disabled">${oaTask.task_jianyi }</textarea>
				</div>
			</div>
			<div class="clear"></div>
		</li>
		<li>
			<div class="item">
				<label class="left">任务评分：</label>
				<div class="info left">
					<input type="hidden" id="pingfen" value="0" />
					<div id="scoreName"></div>
				</div>
			</div>
			<div class="clear"></div>
		</li>
		<li>
			<div class="item">
				<label class="left">任务评价：</label>
				<div class="info left">
					<textarea id="pingjia_content"></textarea>
				</div>
			</div>
			<div class="clear"></div>
		</li>
		<li><a class="left send" onclick="pingjia_task(${oaTask.task_id},'${oaTask.task_type}')" href="javascript:void(0)">提交</a> <a
			class="left guan" onclick="close_layer()" href="javascript:void(0)">关闭</a></li>
	</ul>
</body>
</html>