<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/school/back/common/right_content.css" />
<link rel="stylesheet" type="text/css" href="/css/jiaowu/index/jiaowu.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script>
//展开二级菜单
function zhankai_menu(course_id,id){
	var type=$("#tubiao"+course_id).attr("class");
	if(type=="fa fa-plus"){//切换符号
		$("#tubiao"+course_id).removeClass("fa-plus");
		$("#tubiao"+course_id).addClass("fa-minus");
		$.get("/back_question_course/get_sub_menu_list.jr",{
			'course_id':course_id,
			'id':id
		},function(data){
			$("#menu"+course_id).after(data);
		})
	}else{
		$("#tubiao"+course_id).removeClass("fa-minus");
		$("#tubiao"+course_id).addClass("fa-plus");
		$("#menu"+course_id).nextAll("#success"+id).remove();
	}
}
//刷新当前页面
function question_course_menu(){
	jiazaidonghua();
	$.post("/back_question_course/get_question_main.jr"
			,function(data){
		$("#conten_list").html(data);
	})
}
//信息弹窗
function tanchuang(content){
	layer.alert(content, {
		  icon: 1,
		  skin: 'layer-ext-moon' 
		})
}
//添加题库课程分类信息
function to_add_question_course(){
	layer.open({
		  type: 2,
		  title: ['添加题库课程分类信息'],
		  area: ['800px', '450px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_question_course/to_add_question_course.jr",
		  end: function () {
			  question_course_menu();
        }
		  });
}
//单独添加题库课程分类
function to_add_question_course_single(course_id,course_parent_id){
	layer.open({
		  type: 2,
		  title: ['添加题库课程分类信息'],
		  area: ['800px', '350px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_question_course/to_add_question_course_single.jr?course_id="+course_id+"&course_parent_id="+course_parent_id,
		  end: function () {
			  question_course_menu();
      }
		  });
}
//修改题库信息课程
function to_update_question_course(question_course_id){
	layer.open({
		  type: 2,
		  title: ['修改题库课程分类信息'],
		  area: ['800px', '450px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_question_course/to_update_question_course.jr?question_course_id="+question_course_id,
		  end: function () {
			  question_course_menu();
    }
		  });
}
//设置题库课程分类是否显示
function update_coursemenu_show(type,question_course_id){
	$.post("/back_question_course/update_question_course.jr",{
		'is_show' : type,
		'question_course_id' : question_course_id
	},function(data){
		if(data==1){
			tanchuang("设置成功!");
			question_course_menu();
		}else{
			tanchuang("设置失败!");
		}
	})
}
</script>
<div class="back_right" style="min-width: 900px;">
	<div class="back_r_top">
		<div class="left">分类管理</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<div class="operation">
			<div class="opera_left left">
				<button type="button" onclick="to_add_question_course()"
					class="btn left btn-primary" id="add_employee">
					<i class="fa fa-plus"></i>添加分类
				</button>
			</div>
			<div class="opera_right right"></div>
		</div>

		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>菜单名称</th>
						<th>分类数量</th>
						<th>分类描述</th>
						<th>添加时间</th>
						<th>操作</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty courseMenus }">
						<tbody>
							<c:forEach items="${courseMenus}" var="coursemenu" varStatus="vs">
								<c:if test="${vs.count%2 == '0' }">
									<tr class="active" id="menu${coursemenu.course_id}">
								</c:if>
								<c:if test="${vs.count%2 != '0' }">
									<tr id="menu${coursemenu.course_id}">
								</c:if>
								<td><label class="label label-success btn-primary"
									for="minimal-checkbox-1">${vs.index+1 }</label></td>
								<td>${coursemenu.course_name }</td>
								<td>${coursemenu.question_number }</td>
								<td>--</td>
								<td>--</td>
								<td>
									<button type="button"
										onclick="to_add_question_course_single(${coursemenu.course_id},${coursemenu.course_parent_id})"
										class="btn btn-default btn-xs">添加分类</button>
								</td>
								<td><button onclick="zhankai_menu(${coursemenu.course_id},${vs.index+1})"
										type="button" class="btn btn-inverse btn-xs zhang_down">
										<i style="font-size:10px" id="tubiao${coursemenu.course_id}" class="fa fa-plus"></i>
									</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</c:if>
			</table>
			<c:if test="${ empty courseMenus }">
				<p class="zanwu">暂无题库课程分类信息</p>
			</c:if>
		</div>
	</div>
</div>