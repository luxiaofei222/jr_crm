<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" type="text/css"
	href="/js/common/bootstrap-select.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap-select.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>查看学员成绩</title>
<style>
table.basic tbody tr th,table tbody tr td{
	text-align:center;
	vertical-align:middle!important;
}
table.basic tbody tr td:nth-child(2n-1){
	font-weight:bold;
	width:75px;
}
table.basic tbody tr td:nth-child(2n){
	width:186px;
}
 table.basic tr td input:not(.xiugai),table tr td select{
 	width:100%;
 	height:100%;
 	line-height: 35px;
    text-align: center;
 }
 table.record tr th{
 	text-align:center;
 }
</style>
</head>
<script type="text/javascript">
function update_user_grade(grade_id){
	if(check_user_phone()&&check_user_card()){
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/user_grade/update_user_grade.jr",
			data:{'grade_id':grade_id},
			success : function(data) {
				if(data==1){
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
	                tanchuang('恭喜您，修改成功');
	                //$('#myform')[0].reset();
				}else{
					tanchuang('很遗憾，修改失败');
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，修改失败');
			}
		});
	}
}
</script>
<body>
<div style="padding:20px;">
<form class="form-horizontal add_qiye_dialog" id="myform" style="padding: 21px;">
<table class="table table-bordered basic">
<caption style='text-align:center;font-size:19px;color:#06C1AE;'>学员基本信息</caption>
	<tbody>
		<tr>
			<td>类别</td>
			<td>${userGrade.courseMenu.course_name }
			<c:if test="${not empty userGrade.review.review_name }">
			-${userGrade.review.review_name }
			</c:if>
			</td>
			<td>等级</td>
			<td>${userGrade.dictionary.dictionary_name }</td>
			<td>学校</td>
			<td><input type="text" class="form-control" id="xuexiao" value="${userGrade.xuexiao }" name="xuexiao"></td>
			<td rowspan="3" style="vertical-align:middle;">
				<input class="btn btn-danger xiugai" onclick="update_user_grade(${userGrade.grade_id})"  type="button" value="修改">
			</td>								
		</tr>
		<tr>
			<td>专业</td>
			<td><input type="text" id="zhuanye" class="form-control" value="${userGrade.zhuanye }" name="zhuanye"></td>
			<td>姓名</td>
			<td><input type="text" id="user_name" class="form-control" value="${userGrade.user_name }" name="user_name"></td>			
			<td>手机</td>
			<script type="text/javascript">
		function check_user_phone(){
			var user_phone = $("#user_phone").val();
			var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
			if (user_phone != null && user_phone != "") {
				if (!myreg.test(user_phone)) {
					layer.msg('请输入有效的手机号码！');
					return false;
				} else {
					return true;
				}
			} else {
				layer.msg("请输入您的手机号！");
				return false;
			}
		}
		</script>
			<td><input type="text" onblur="check_user_phone()" id="user_phone" class="form-control" value="${userGrade.user_phone }" name="user_phone"></td>
		</tr>
		<tr>	
			<td>身份证号</td>
			<script type="text/javascript">
		function check_user_card(){
			var user_card=$("#user_card").val();
			if(user_card!=null&&user_card!=""){
				isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
				if(isIDCard2.test(user_card)){
					return true;
				}else{
					layer.msg("请输入正确的身份证号码！");
					return false;
				}
			}else{
				layer.msg("请输入证件号码！");
				return false;
			}
		}
		</script>
			<td><input type="text" id="user_card" onblur="check_user_card()" class="form-control" value="${userGrade.user_card }" name="user_card"></td>
			<td>准考证号</td>
			<td><input type="text" id="user_zhunkao" class="form-control" value="${userGrade.user_zhunkao }" name="user_zhunkao"></td>
			<td>考生号</td>
			<td><input type="text" id="kaoshenghao" class="form-control" value="${userGrade.kaoshenghao }" name="kaoshenghao"></td>
		</tr>
	</tbody>
</table>
</form>
<table class="table table-hover record">
<caption style='text-align:center;font-size:19px;color:#06C1AE;'>学员成绩<button onclick="add_chengji(${userGrade.grade_id })" class="btn btn-info" style="float:right;"><i class="fa fa-plus"></i>添加成绩</button><div style='clear:both;'></div></caption>
<thead>
	<tr class="success">
		<th>科目</th>
		<th>成绩</th>
		<th>操作</th>
	</tr>
</thead>
	<tbody>
	<c:forEach items="${userGrades }" var="grade">
		<tr>
			<td><input type="text" id="kemu${grade.grade_id}" style="border: none;text-align: center;" class="form-control" value="${grade.kemu }" name="kemu"></td>
			<td><input type="text" id="grade${grade.grade_id}" style="border: none;text-align: center;" class="form-control" value="${grade.grade }" name="grade"></td>
			<td>
				<input class="btn btn-warning" onclick="update_user_grade_chengji(${grade.grade_id})" type="button" value="修改" />
				<input class="btn btn-danger" onclick="to_delete_grade(${grade.grade_id})" type="button" value="删除" />
			</td>				
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
<script type="text/javascript">
function update_user_grade_chengji(grade_id){
	var kemu=$("#kemu"+grade_id).val();
	var grade=$("#grade"+grade_id).val();
	$.post("/user_grade/update_user_grade.jr",{
		'grade_id':grade_id,
		'kemu':kemu,
		'grade':grade
	},function(data){
		if(data==1){
            tanchuang('恭喜您，修改成功');
		}else{
			tanchuang('很遗憾，修改失败');
		}
	})
}

//删除成绩
function to_delete_grade(grade_id){
	layer.confirm("提示：您好，确定要删除吗？",function(){
		layer.closeAll('dialog');
		$.post("/user_grade/delete_user_grade.jr",{
			'grade_id':grade_id
		},function(data){
			if(data==1){
				layer.msg("删除成功！");
			}else{
				tanchuang("删除失败！");
			}
		});
		})
}
//添加成绩
function add_chengji(grade_id){
	layer.open({
		type : 2,
		title : [ '添加学员成绩' ],
		area : [ '400px', '300px' ],
		shadeClose : true, //点击遮罩关闭
		content : "/user_grade/to_add_chengji.jr?grade_id="+grade_id
	});
}
</script>
</body>
</html>