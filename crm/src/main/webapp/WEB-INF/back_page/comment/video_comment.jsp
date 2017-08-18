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

//查看评论
function check_commentance(v_comment_id){
	 layer.open({
		  type: 2,
		  title: ['查看评论'],
		  area: ['500px', '400px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/back_comment/to_check_comment.jr?v_comment_id="+v_comment_id
		  });
}
//评论列表翻页-上一页 下一页
function comment_jump_page(page){
	jiazaidonghua();
	$.post("/back_comment/get_comment_list.jr",{
		'pageNumber' : page,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//删除评论信息
function delete_commentance(page){
	var len = $("input:checkbox[name=commentance_check]:checked").length;
	if(len>0){
		var str = "";
		$('input:checkbox[name=commentance_check]:checked').each(function(i) {
			if (0 == i) {
				str = $(this).val();
			} else {
				str += ","+$(this).val();
			}
		});
		layer.confirm("提示：您好，确定要删除吗？",function(){
			layer.closeAll('dialog');
			$.post("/back_comment/delete_comment.jr",{
				'str':str
			},function(data){
				if(data==1){
					tanchuang("删除成功！");
					comment_jump_page(page);
				}else{
					tanchuang("删除失败！");
				}
			});
			})
	}else{
		tanchuang("请选择至少一条记录删除！");
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
      .operation #add_course_info {
		  margin-right:20px;
		  }
    </style>
<div class="back_right" style="min-width:900px;">
  <div class="back_r_top">
    <div class="left">课程评论</div>
        <div class="right" id="timer">
</div>
  </div>
  <div class="right_content">
    <div class="operation">
       <div class="opera_left left">
        <button type="button" onclick="delete_commentance(${comments.pageNumber})" class="btn left btn-danger" id="delete_commentance"><i class="fa fa-minus"></i>删除</button>
      </div>
        <div class="opera_right right">
      </div>
    </div>
    <div class="content_message">
      <table class="table table-hover">
        <thead>
          <tr class="tr_bgcolor warning">
          <th></th>
          <th>编号</th>
          <th>所属课程</th>
          <th>评论用户</th>
          <th>用户IP</th>
          <th>评论时间</th>
          <th>操作</th>
          </tr>
        </thead>
        <c:if test="${not empty comments.list }">
				<tbody>
				<c:forEach items="${comments.list }" var="comment" varStatus="vs">
                    <c:if test="${vs.count%2 == '0' }">
                    <tr class="active">
                    </c:if>
                    <c:if test="${vs.count%2 != '0' }">
                    <tr>
                    </c:if>
            <td>
              <label class="checkbox checkbox-inline" for="checkbox${comment.v_comment_id }">
                <input type="checkbox" name="commentance_check" data-toggle="checkbox" value="${comment.v_comment_id }" id="checkbox${comment.v_comment_id }" class="checkbox" required>
              </label>
            </td>
            <td>
              <label class="label label-success btn-primary" for="minimal-checkbox-1">${vs.index+1+comments.begin }</label>
            </td>
            <td>${comment.courseVideo.video_name }</td>
              <td>${comment.user.user_nickname }</td>
              <td>${comment.v_comment_ip }</td>
            <td><fmt:formatDate type="both" value="${comment.v_comment_time }" /></td>           
            <td>
              <button type="button" onclick="check_commentance(${comment.v_comment_id })" class="btn btn-success btn-xs">查看</button>
            </td>
          </tr>
          </c:forEach>
        </tbody>
        </c:if>
	  </table>
	   <c:if test="${ empty comments.list }">
			<p class="zanwu">暂无评论内容</p>
	 	</c:if>
    </div>
    <div class="pages">
      <div class="whole left">
        <button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
        <button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
      </div> 
      <ul class="pagination right">
        <c:if test="${comments.hasPreviousPage==true}">
						<li class="previous"
							onclick="comment_jump_page(${comments.pageNumber-1})"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${comments.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${comments.pageNumber==page}">
								<li class="active"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="comment_jump_page(${page})"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${comments.hasNextPage==true}">
						<li class="next" onclick="comment_jump_page(${comments.pageNumber+1})"><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
      </ul>
    </div>
  </div>  
</div>