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
//添加角色信息
function to_add_role(){
	 layer.open({
		  type: 2,
		  title: ['添加角色'],
		  area: ['500px', '450px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_role/to_add_role.jr",
		  end: function () {
			  role_jump_page(1);
          }
		  });
}
//设置权限弹窗
function set_permission(role_id){
	 layer.open({
		  type: 2,
		  title: ['设置权限'],
		  area: ['800px', '700px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_role/get_permission_page.jr?role_id="+role_id
		  });
}
//角色列表翻页-上一页 下一页
function role_jump_page(page){
	jiazaidonghua();
	$.post("/back_role/get_role_main.jr",{
		'pageNumber' : page,
		'limit' : 8
	},function(data){
		$("#conten_list").html(data);
	})
}
//查询关键字
function seacher_role(){
	var drop_menu=$('#select_val').val();//获取查询下拉菜单的值
	var drop_menu_val=$('#check_val').val(); //获取输入框里面的值
	if(drop_menu=="role_name"){
		jiazaidonghua();
		$.post("/back_role/get_role_main.jr", {
			'role_name':drop_menu_val,
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
			$.post("/back_role/get_role_main.jr",{
				'pageNumber' : 1,
				'limit' : 8
			},function(data){
				$("#conten_list").html(data);
			})
		});
	}
}
//删除角色信息
function delete_role(role_id){
	layer.confirm("提示：您好，确定要删除吗？",function(){
	layer.closeAll('dialog');
	jiazaidonghua();
	$.post("/back_role/delete_role.jr",{
		'role_id':role_id
	},function(data){
		if(data==1){
			tanchuang("删除成功！");
			role_jump_page(1);
		}else{
			tanchuang("删除失败！");
		}
	});
	})
}
//信息弹窗
function tanchuang(content){
	layer.alert(content, {
		  icon: 1,
		  skin: 'layer-ext-moon' 
		})
}
</script>
<div class="back_right" style="min-width:900px;">
  <div class="back_r_top">
    <div class="left">角色管理</div>
        <div class="right" id="timer">
</div>
  </div>
  <div class="right_content">
    <div class="operation">
      <div class="opera_left left">
        <button type="button" onclick="to_add_role()" class="btn left btn-primary" id="add_role"><i class="fa fa-plus"></i>&nbsp;添加角色</button>  
      </div>
      <div class="opera_right right">
        <select class="form-control select select-primary left top_select" id="select_val" data-toggle="select">
            <option value="0">请选择查询条件</option>
            <option value="role_name">角色名称</option>
          </select>
          <input type="text" id="check_val" placeholder="请输入查询内容" class="form-control left top_search" />
          <button type="button" onclick="seacher_role()" class="btn left btn-primary">查询</button>
      </div>
    </div>
    <div class="content_message">

      <table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
                       <!--  <th></th> -->
                        <th >编号</th>
						<!--<th>编号</th>-->
						<th>角色名称</th>
						<th>角色描述</th>
						<th>添加时间</th>
                        <th width="25%">操作</th>
					</tr>
				</thead>
				<c:if test="${not empty roles.list }">
				<tbody>
				<c:forEach items="${roles.list }" var="role" varStatus="vs">
                    <c:if test="${vs.count%2 == '0' }">
                    <tr class="active">
                    </c:if>
                    <c:if test="${vs.count%2 != '0' }">
                    <tr>
                    </c:if>
                       <!--  <td>
                          <label class="checkbox checkbox-inline" for="checkbox2">
                            <input type="checkbox" data-toggle="checkbox" value="" id="checkbox2" class="checkbox" required>
                          </label>
                        </td> -->
						<td>
                          <label class="label label-success btn-primary" for="minimal-checkbox-1">${vs.index+1+roles.begin }</label>
                        </td>
						<td>${role.role_name }</td>
                        <td>${role.role_dis }</td>
						<td><fmt:formatDate type="both" value="${role.role_time }" /></td>
						<td>
                          <button type="button" onclick="set_permission(${role.role_id})" class="btn btn-success btn-xs">设置权限</button>
                          <button type="button" onclick="delete_role(${role.role_id})" class="btn btn-danger btn-xs">删除</button>
                        </td>
                        </td>
					</tr>
					</c:forEach>
				</tbody>
				</c:if>
			</table>
			    <c:if test="${ empty roles.list }">
				<p class="zanwu">暂无角色内容</p>
				</c:if>
    </div>
    <div class="pages">
     <!--  <div class="whole left">
        <button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
        <button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
          
      </div> -->
      <ul class="pagination right">
        <c:if test="${roles.hasPreviousPage==true}">
						<li class="previous"
							onclick="role_jump_page(${roles.pageNumber-1})"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${roles.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${roles.pageNumber==page}">
								<li class="active" onclick="role_jump_page(${page})"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="role_jump_page(${page})"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${roles.hasNextPage==true}">
						<li class="next" onclick="role_jump_page(${roles.pageNumber+1})"><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
      </ul>
    </div>
  </div>  
</div>