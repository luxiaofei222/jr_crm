<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/school/back/common/right_content.css" />
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script>
//展开二级菜单
function zhankai_menu(id,course_id){
	var type=$("#tubiao"+course_id).attr("class");
	if(type=="fa fa-plus"){//切换符号
		$("#tubiao"+course_id).removeClass("fa-plus");
		$("#tubiao"+course_id).addClass("fa-minus");
		$.get("/back_course_menu/get_sub_menu_list.jr",{
			'course_id':course_id,
			'id':id
		},function(data){
			$("#menu"+course_id).after(data);
		})
	}else{
		$("#tubiao"+course_id).removeClass("fa-minus");
		$("#tubiao"+course_id).addClass("fa-plus");
		$("#menu"+course_id).nextAll("#success"+id).remove();
		$("#menu"+course_id).next(".zanwu").remove();
	}
}
//刷新菜单页面
function coursemenu_jump_page(page){
	jiazaidonghua();
	$.post("/back_course_menu/get_course_menu.jr",{
		'pageNumber' : page,
		'limit' : 10
	},function(data){
		$("#conten_list").html(data);
	})
}
//是否设置菜单显示
function coursemenu_show(type,course_id){
	$.post("/back_course_menu/update_isshow.jr",{
		'is_show' : type,
		'course_id' : course_id
	},function(data){
		if(data==1){
			tanchuang("设置成功!");
			coursemenu_jump_page(1);
		}else{
			tanchuang("设置失败!");
		}
	})
}
//信息弹窗
function tanchuang(content){
	layer.alert(content, {
		  icon: 1,
		  skin: 'layer-ext-moon' 
		})
}
//设置考试时间
function coursemenu_exam(course_id){
	layer.open({
		  type: 2,
		  title: ['设置考试时间'],
		  area: ['600px', '350px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_course_menu/to_set_time.jr?course_id="+course_id,
		  end: function () {
			  claerance_jump_page(1);
        }
		  });
}
</script>
<div class="back_right" style="min-width: 900px;">
	<div class="back_r_top">
		<div class="left">课程目录</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<div class="operation">
			<div class="opera_left left">
				<!-- <button type="button" onclick="to_add_employee()" class="btn left btn-primary" id="add_employee">
					<i class="fa fa-plus"></i>添加账号
				</button> -->
			</div>
			<div class="opera_right right">
			<!-- 	<select onchange="change_select()"
					class="form-control select select-primary left top_select"
					id="select_val" data-toggle="select">
					<option value="0">请选择查询条件</option>
					<option value="name">课程名称</option>
				</select> <input type="text" id="check_val" placeholder="请输入查询内容"
					class="form-control left top_search" />
				<button type="button" onclick="seacher_coursemenu()"
					class="btn left btn-primary">查询</button> -->
			</div>
		</div>

		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>菜单名称</th>
						<th>添加时间</th>
						<!-- <th>班型</th>
						<th>通关方案</th> -->
						<th>操作</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty courseMenus.list }">
						<tbody>
							<c:forEach items="${courseMenus.list }" var="coursemenu"
								varStatus="vs">
								<c:if test="${vs.count%2 == '0' }">
									<tr class="active" id="menu${coursemenu.course_id}">
								</c:if>
								<c:if test="${vs.count%2 != '0' }">
									<tr id="menu${coursemenu.course_id}">
								</c:if>
								<td><label class="label label-success btn-primary"
									for="minimal-checkbox-1">${vs.index+1+courseMenus.begin }</label></td>
								<td>${coursemenu.course_name }</td>
								<td><fmt:formatDate type="both"
										value="${coursemenu.course_time }" /></td>
								<!-- <td>--</td>
								<td>--</td> -->
								<td> <c:if
										test="${coursemenu.is_show=='否'  }">
										<button type="button"
											onclick="coursemenu_show('是',${coursemenu.course_id})"
											class="btn btn-info btn-xs">显示</button>
									</c:if> <c:if test="${coursemenu.is_show=='是'  }">
										<button type="button"
											onclick="coursemenu_show('否',${coursemenu.course_id})"
											class="btn btn-default btn-xs">隐藏</button>
									</c:if>
								</td>
								<td><button onclick="zhankai_menu(${vs.index+1+courseMenus.begin },${coursemenu.course_id})"
										type="button" class="btn btn-inverse btn-xs zhang_down">
										<i id="tubiao${coursemenu.course_id}" class="fa fa-plus"></i>
									</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</c:if>
			</table>
			<c:if test="${ empty courseMenus.list }">
				<p class="zanwu">暂无课程信息</p>
			</c:if>
		</div>
		<div class="pages">
			<!-- <div class="whole left">
				<button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
				<button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
			</div> -->
			<%-- <ul class="pagination right">
				<c:if test="${courseMenus.hasPreviousPage==true}">
					<li class="previous"
						onclick="coursemenu_jump_page(${courseMenus.pageNumber-1})"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${courseMenus.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${courseMenus.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li onclick="coursemenu_jump_page(${page})"><a href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${courseMenus.hasNextPage==true}">
					<li class="next" onclick="coursemenu_jump_page(${roles.pageNumber+1})"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
			</ul> --%>
		</div>
	</div>
</div>