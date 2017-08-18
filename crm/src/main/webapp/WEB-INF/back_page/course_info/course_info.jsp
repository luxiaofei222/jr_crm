<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/page/pages.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script>
$(function(){
$(".selectall").click(function(){
	$(".checkbox").each(function(){
    $(this).prop("checked",true);
	  })
	})
$(".selectno").click(function(){
	$(".checkbox").each(function(){
    $(this).prop("checked",false);
	  })
	})
}) 

//添加课程资讯信息
function to_add_course_info(page){
	 layer.open({
		  type: 2,
		  title: ['添加课程资讯'],
		  area: ['900px', '700px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_info/to_add_course_info.jr"
		  });
}
//查看资讯详情
function to_check_course_info(info_id){
	 layer.open({
		  type: 2,
		  title: ['查看课程资讯'],
		  area: ['1100px', '800px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/back_info/to_check_course_info.jr?info_id="+info_id
		  });
}
//课程资讯列表翻页-上一页 下一页
function course_info_jump_page(page,info_title,info_type,course_id,parent_course_id){
	jiazaidonghua();
	$.post("/back_info/get_info_main.jr",{
		'info_type':info_type,
		'course_id':course_id,
		'parent_course_id':parent_course_id,
		'info_title':info_title,
		'pageNumber' : page,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//删除课程资讯信息
function delete_course_info(){
	var len = $("input:checkbox[name=course_info_check]:checked").length;
	if(len>0){
		var str = "";
		$('input:checkbox[name=course_info_check]:checked').each(function(i) {
			if (0 == i) {
				str = $(this).val();
			} else {
				str += ","+$(this).val();
			}
		});
		layer.confirm("提示：您好，确定要删除吗？",function(){
			layer.closeAll('dialog');
			$.post("/back_info/delete_course_info.jr",{
				'str':str
			},function(data){
				if(data==1){
					tanchuang("删除成功！");
				}else{
					tanchuang("删除失败！");
				}
			});
			})
	}else{
		tanchuang("请选择至少一条记录删除！");
	}
}
//修改meta信息
function update_meta(course_info_id,page){
	 layer.open({
		  type: 2,
		  title: ['修改课程咨询页面META'],
		  area: ['900px', '300px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_info/to_update_meta.jr?info_id="+course_info_id
		  });
}
//修改课程资讯弹窗
 function update_course_info(course_info_id,page){
	 layer.open({
		  type: 2,
		  title: ['修改课程咨询'],
		  area: ['1100px', '900px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_info/to_update_course_info.jr?info_id="+course_info_id
		  });
 }
//信息弹窗
function tanchuang(content){
	layer.alert(content, {
		  icon: 1,
		  skin: 'layer-ext-moon' 
		})
}
//是否设置热点推荐
function change_ishot(course_info_id,type,page){
	$.post("/back_info/update_info_ishot.jr",{
		'info_id':course_info_id,
		'is_hot':type
	},function(data){
		if(data==1){
			tanchuang("设置成功！");
		}else{
			tanchuang("设置失败！");
		}
	});
}
//跳转页面
function info_tiaozhuan_page(page,limit,info_title,info_type,course_id,parent_course_id){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			 $.post("/back_info/get_info_main.jr", {
				 'info_type':info_type,
					'course_id':course_id,
					'parent_course_id':parent_course_id,
					'info_title':info_title,
					'pageNumber' : page_num,
					'limit' : limit
				}, function(data) {
					$("#conten_list").html(data);
				}) 
		}
	}else{
		layer.msg("请输入数字！")
	}
}
//获取二级分类
function get_sub_course(){
	var course_parent_id=$("#parent_id").val();
	$.post("/edu_entry_plan/get_sub_course.jr",{
		'course_id':course_parent_id
	},function(data){
		$("#course_id").html(data);
	})
	
}
//筛选条件
function shaixuan_tioajian(obj){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("筛选中");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/back_info/get_info_main.jr",
			data:{
				'pageNumber':1,
				'limit':20
			},
			success : function(data) {
				$("#conten_list").html(data);
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('查询失败');
			}
		});
}
</script>
<style>
.operation #add_course_info {
	margin-right: 20px;
}
</style>
<div class="back_right" style="min-width: 900px;">
	<div class="back_r_top">
		<div class="left">课程资讯</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<div class="operation" style=" height: 95px;">
			<form class="form-horizontal" style="padding-left: 35px;" id="myform">
				<div class="form-group clearfix" style="margin-bottom: 5px;">
					<label class="left">类别：</label>
					<div class="col-xs-2">
						<select class="form-control" id="parent_id"
							name="parent_course_id" onchange="get_sub_course()"
							style="line-height: 35px; height: 35px; padding: 0px;">
							<option value="">请选择</option>
							<c:forEach items="${courseMenus }" var="course">
								<option value="${course.course_id }">${course.course_name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-xs-2">
						<select class="form-control" id="course_id" name="course_id"
							style="line-height: 35px; height: 35px; padding: 0px;">
							<option value="">请选择</option>
						</select>
					</div>
					<label class="left">新闻标题：</label>
					<div class="col-xs-2">
						<input class="form-control" id="info_title"
							name="info_title" placeholder="输入资讯标题">
					</div>
					<label class="left">新闻类型：</label>
					<div class="col-xs-2">
						<select class="form-control" id="info_type" name="info_type"
							style="line-height: 35px; height: 35px; padding: 0px;">
							<option value="">请选择</option>
							<option value="考试动态">考试动态</option>
							<option value="热点专题">热点专题</option>
							<option value="课程相关">课程相关</option>
							<option value="报考指南">报考指南</option>
							<option value="考试试题">试题辅导（考试试题）</option>
						</select>
					</div>
					<div class="col-xs-2">
						<button type="button" class="btn"
							onclick="shaixuan_tioajian(this)"
							style="background-color: #53C1FE; color: white;">筛选</button>
					</div>
				</div>
			</form>
			<div class="opera_left left">
				<!-- <button type="button" onclick="to_add_course_info()" class="btn left btn-primary" id="add_course_info"><i class="fa fa-plus"></i>添加</button> -->
				<button type="button" onclick="delete_course_info()"
					class="btn left btn-danger" id="delete_course_info">
					<i class="fa fa-minus"></i>删除
				</button>
			</div>
			<div class="opera_right right"></div>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th></th>
						<th>编号</th>
						<th>标题</th>
						<th>编辑</th>
						<th>类别</th>
						<th>添加时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:if test="${not empty courseInfos.list }">
					<tbody>
						<c:forEach items="${courseInfos.list }" var="course_info"
							varStatus="vs">
							<c:if test="${vs.count%2 == '0' }">
								<tr class="active">
							</c:if>
							<c:if test="${vs.count%2 != '0' }">
								<tr>
							</c:if>
							<td><label class="checkbox checkbox-inline"
								for="checkbox${course_info.info_id }"> <input
									type="checkbox" name="course_info_check" data-toggle="checkbox"
									value="${course_info.info_id }"
									id="checkbox${course_info.info_id }" class="checkbox" required>
							</label></td>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+courseInfos.begin }</label>
							</td>
							<td>${course_info.info_title }</td>
							<td>${course_info.info_user }</td>
							<td>${course_info.info_type }</td>
							<td><fmt:formatDate type="both"
									value="${course_info.info_time }" /></td>
							<td>
								<button type="button"
									onclick="update_course_info(${course_info.info_id },${courseInfos.pageNumber})"
									class="btn btn-warning btn-xs">编辑</button>
								<button type="button"
									onclick="update_meta(${course_info.info_id },${courseInfos.pageNumber})"
									class="btn btn-inverse btn-xs">修改META</button>
								<button type="button"
									onclick="to_check_course_info(${course_info.info_id},${courseInfos.pageNumber})"
									class="btn btn-success btn-xs">查看</button> <c:if
									test="${course_info.is_hot=='是' }">
									<button type="button"
										onclick="change_ishot(${course_info.info_id},'否',${courseInfos.pageNumber})"
										class="btn btn-default btn-xs">取消</button>
								</c:if> <c:if test="${course_info.is_hot=='否' }">
									<button type="button"
										onclick="change_ishot(${course_info.info_id},'是',${courseInfos.pageNumber})"
										class="btn btn-info btn-xs">推荐</button>
								</c:if>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ empty courseInfos.list }">
				<p class="zanwu">暂无新闻内容</p>
			</c:if>
		</div>
		<div class="pages">
			<div class="whole left">
				<button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
				<button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
			</div>
			<div class="whole left">
				<div class="go_page">
					<span>跳转到第</span><input type="text" id="page_num" /> <span>页</span>
					<a href="javascript:void(0)"
						onclick="info_tiaozhuan_page(${courseInfos.pages},20,'${info_title }','${info_type }','${course_id }','${parent_course_id }')"
						class="btn_go" style="background-color: #06c1ae;">GO</a>
				</div>
			</div>
			<ul class="pagination right">
				<c:if test="${courseInfos.hasPreviousPage==true}">
					<li class="previous"
						onclick="course_info_jump_page(${courseInfos.pageNumber-1},'${info_title }','${info_type }','${course_id }','${parent_course_id }')"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${courseInfos.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${courseInfos.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li onclick="course_info_jump_page(${page},'${info_title }','${info_type }','${course_id }','${parent_course_id }')"><a
								href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${courseInfos.hasNextPage==true}">
					<li class="next"
						onclick="course_info_jump_page(${courseInfos.pageNumber+1},'${info_title }','${info_type }','${course_id }','${parent_course_id }')"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>