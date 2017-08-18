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

//添加消息信息
function to_add_message(){
	 layer.open({
		  type: 2,
		  title: ['发送系统通知'],
		  area: ['1000px', '600px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_message/to_add_message.jr"
		  });
}
//消息列表翻页-上一页 下一页
function message_jump_page(page){
	jiazaidonghua();
	$.post("/back_message/get_fajian_page.jr",{
		'pageNumber' : page,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//查看信息详情
function check_message(message_id){
	layer.open({
		  type: 2,
		  title: ['查看详情'],
		  area: ['1000px', '650px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_message/check_message.jr?message_id="+message_id
        });
}
//查询关键字
function seacher_message(){
	var drop_menu=$('#select_val').val();//获取查询下拉菜单的值
	var drop_menu_val=$('#check_val').val(); //获取输入框里面的值
	if(drop_menu=="user_name"){
		jiazaidonghua();
		$.post("/back_message/get_fajian_page.jr", {
			'user_nickname':drop_menu_val,
			'pageNumber' : 1,
			'limit' : 20
		}, function(data) {
			if (data != null) {
				$('#conten_list').html(data);
			}
		});
	}else if(drop_menu=="user_phone"){
		jiazaidonghua();
		$.post("/back_message/get_fajian_page.jr", {
			'user_phone':drop_menu_val,
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
			$.post("/back_message/get_fajian_page.jr",{
				'pageNumber' : 1,
				'limit' : 20
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
    <div class="left">发件箱</div>
        <div class="right" id="timer">
</div>
  </div>
  <div class="right_content">
    <div class="operation">
       <div class="opera_left left">
        <button type="button" onclick="to_add_message()" class="btn left btn-primary" id="add_news"><i class="fa fa-plus"></i>发送通知</button>
        <!-- <button type="button" onclick="delete_message()" class="btn left btn-danger" id="delete_message"><i class="fa fa-minus"></i>删除信息</button> -->
      </div>
       <div class="opera_right right">
        <select class="form-control select select-primary left top_select" id="select_val" data-toggle="select">
            <option value="0">请选择查询条件</option>
            <option value="user_phone">手机号</option>
            <option value="user_name">收件人昵称</option>
          </select>
          <input type="text" id="check_val" placeholder="请输入查询内容" class="form-control left top_search" />
          <button type="button" onclick="seacher_message()" class="btn left btn-primary">查询</button>
      </div>
    </div>
    <div class="content_message">
      <table class="table table-hover">
        <thead>
          <tr class="tr_bgcolor warning">
          <th></th>
          <th>编号</th>
          <th>收件人</th>
          <th>标题</th>
          <th>类型</th>
          <th>状态</th>
          <th>发送时间</th>
          <th>操作</th>
          </tr>
        </thead>
        <c:if test="${not empty messages.list }">
				<tbody>
				<c:forEach items="${messages.list }" var="message" varStatus="vs">
                    <c:if test="${vs.count%2 == '0' }">
                    <tr class="active">
                    </c:if>
                    <c:if test="${vs.count%2 != '0' }">
                    <tr>
                    </c:if>
            <td>
              <%-- <label class="checkbox checkbox-inline" for="checkbox${message.message_id }">
                <input type="checkbox" name="message_check" data-toggle="checkbox" value="${message.message_id }" id="checkbox${message.message_id }" class="checkbox" required>
              </label> --%>
            </td>
            <td>
              <label class="label label-success btn-primary" for="minimal-checkbox-1">${vs.index+1+messages.begin }</label>
            </td>
            <td>${message.user.user_nickname }</td>
              <td>${message.message_title }</td>
               <td>${message.message_type }</td>
               <c:if test="${message.is_read=='是' }">
               <td style="color: gray;">已读</td>
               </c:if>
               <c:if test="${message.is_read=='否' }">
               <td style="color: orange;">未读</td>
               </c:if>
            <td><fmt:formatDate type="both" value="${message.message_time }" /></td>           
            <td>
              <button type="button" onclick="check_message(${message.message_id })" class="btn btn-inverse btn-xs">查看</button>
            </td>
          </tr>
          </c:forEach>
        </tbody>
        </c:if>
	  </table>
	   <c:if test="${ empty messages.list }">
				<p class="zanwu">暂无新闻内容</p>
				</c:if>
    </div>
    <div class="pages">
   <!--    <div class="whole left">
        <button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
        <button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
      </div>  -->
      <ul class="pagination right">
        <c:if test="${messages.hasPreviousPage==true}">
						<li class="previous"
							onclick="message_jump_page(${messages.pageNumber-1})"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${messages.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${messages.pageNumber==page}">
								<li class="active"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="message_jump_page(${page})"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${messages.hasNextPage==true}">
						<li class="next" onclick="message_jump_page(${messages.pageNumber+1})"><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
      </ul>
    </div>
  </div>  
</div>