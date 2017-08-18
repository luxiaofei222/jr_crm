<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css"/>
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
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

//添加教师信息
function to_add_teacher(){
	 layer.open({
		  type: 2,
		  title: ['添加教师信息'],
		  area: ['900px', '700px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_teacher/to_add_teacher.jr"
		  });
}
//教师列表翻页-上一页 下一页
function teacher_jump_page(page){
	jiazaidonghua();
	$.post("/back_teacher/get_teahcer_main.jr",{
		'pageNumber' : page,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//删除教师信息
function delete_teacher(){
	var len = $("input:checkbox[name=news_check]:checked").length;
	if(len>0){
		var str = "";
		$('input:checkbox[name=news_check]:checked').each(function(i) {
			if (0 == i) {
				str = $(this).val();
			} else {
				str += ","+$(this).val();
			}
		});
		layer.confirm("提示：您好，确定要删除吗？",function(){
			layer.closeAll('dialog');
			$.post("/back_teacher/delete_teacher.jr",{
				'str':str
			},function(data){
				if(data==1){
					tanchuang("删除成功！");
					teacher_jump_page(1);
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
function update_teacher(teacher_id){
	 layer.open({
		  type: 2,
		  title: ['修改教师信息'],
		  area: ['900px', '700px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_teacher/to_update_teacher.jr?teacher_id="+teacher_id
		  });
}

//查询关键字
function seacher_teacher(){
	var drop_menu=$('#select_val').val();//获取查询下拉菜单的值
	var drop_menu_val=$('#check_val').val(); //获取输入框里面的值
	if(drop_menu=="name"){
		jiazaidonghua();
		$.post("/back_teacher/get_teahcer_main.jr", {
			'teacher_name':drop_menu_val,
			'pageNumber' : 1,
			'limit' : 20
		}, function(data) {
			if (data != null) {
				$('#conten_list').html(data);
			}
		});
	}else if(drop_menu=="0"){
		layer.confirm("提示：您好，请选择你需要的查询条件！",function(){
			layer.closeAll('dialog');
			jiazaidonghua();
			$.post("/back_teacher/get_teahcer_main.jr",{
				'pageNumber' : 1,
				'limit' : 10
			},function(data){
				$("#conten_list").html(data);
			})
		});
	}
}
//信息弹窗
function tanchuang(content){
	layer.alert(content, {
		  icon: 1,
		  skin: 'layer-ext-moon' 
		})
}
</script>
  <style>
      .operation #add_news {
		  margin-right:20px;
		  }
    </style>
<div class="back_right" style="min-width:900px;">
  <div class="back_r_top">
    <div class="left">教师管理</div>
        <div class="right" id="timer">
</div>
  </div>
  <div class="right_content">
    <div class="operation">
       <div class="opera_left left">
        <button type="button" onclick="to_add_teacher()" class="btn left btn-primary" id="add_news"><i class="fa fa-plus"></i>添加教师</button>
        <button type="button" onclick="delete_teacher()" class="btn left btn-danger" id="delete_teacher"><i class="fa fa-minus"></i>删除教师</button>
      </div>
       <div class="opera_right right">
        <select class="form-control select select-primary left top_select" id="select_val" data-toggle="select">
            <option value="0">请选择查询条件</option>
            <option value="name">姓名</option>
          </select>
          <input type="text" id="check_val" placeholder="请输入查询内容" class="form-control left top_search" />
          <button type="button" onclick="seacher_teacher()" class="btn left btn-primary">查询</button>
      </div>
    </div>
    <div class="content_message">
      <table class="table table-hover">
        <thead>
          <tr class="tr_bgcolor warning">
          <th></th>
          <th>编号</th>
          <th>姓名</th>
          <th>性别</th>
          <th>电话</th>
          <th>授课类别</th>
          <th>添加时间</th>
          <th>操作</th>
          </tr>
        </thead>
        <c:if test="${not empty teachers.list }">
				<tbody>
				<c:forEach items="${teachers.list }" var="teacher" varStatus="vs">
                    <c:if test="${vs.count%2 == '0' }">
                    <tr class="active">
                    </c:if>
                    <c:if test="${vs.count%2 != '0' }">
                    <tr>
                    </c:if>
            <td>
              <label class="checkbox checkbox-inline" for="checkbox${teacher.teacher_id }">
                <input type="checkbox" name="news_check" data-toggle="checkbox" value="${teacher.teacher_id }" id="checkbox${teacher.teacher_id }" class="checkbox" required>
              </label>
            </td>
            <td>
              <label class="label label-success btn-primary" for="minimal-checkbox-1">${vs.index+1+teachers.begin }</label>
            </td>
            <td>${teacher.teacher_name }</td>
              <td>${teacher.teacher_sex }</td>
              <c:if test="${not empty teacher.teacher_phone }">
               <td>${teacher.teacher_phone }</td>
               </c:if>
                <c:if test="${empty teacher.teacher_phone }">
               <td style="color: orange;">暂无电话</td>
               </c:if>
               <td>${teacher.courseMenu.course_name }</td>
            <td><fmt:formatDate type="both" value="${teacher.teacher_time }" /></td>           
            <td>
              <button type="button" onclick="update_teacher(${teacher.teacher_id })" class="btn btn-inverse btn-xs">修改</button>
            </td>
          </tr>
          </c:forEach>
        </tbody>
        </c:if>
	  </table>
	   <c:if test="${ empty teachers.list }">
				<p class="zanwu">暂无新闻内容</p>
				</c:if>
    </div>
    <div class="pages">
      <div class="whole left">
        <button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
        <button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
      </div> 
      <ul class="pagination right">
        <c:if test="${teachers.hasPreviousPage==true}">
						<li class="previous"
							onclick="teacher_jump_page(${teachers.pageNumber-1})"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${teachers.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${teachers.pageNumber==page}">
								<li class="active"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="teacher_jump_page(${page})"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${teachers.hasNextPage==true}">
						<li class="next" onclick="teacher_jump_page(${teachers.pageNumber+1})"><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
      </ul>
    </div>
  </div>  
</div>