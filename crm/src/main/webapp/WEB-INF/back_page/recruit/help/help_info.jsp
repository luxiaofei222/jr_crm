<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css"/>
<link rel="stylesheet" href="/css/page/pages.css"/>
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

//添加帮助信息信息
function to_add_help_info(page){
	 layer.open({
		  type: 2,
		  title: ['添加帮助信息'],
		  area: ['1000px', '700px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/help_center/to_add_help_info.jr"
		  });
}
//查看资讯详情
function to_check_help_info(help_id,page){
	 layer.open({
		  type: 2,
		  title: ['查看帮助信息'],
		  area: ['1100px', '800px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/help_center/to_check_help_info.jr?help_id="+help_id
		  });
}
//帮助信息列表翻页-上一页 下一页
function help_info_jump_page(page,info_title){
	jiazaidonghua();
	$.post("/help_center/get_help_info_main.jr",{
		'info_title':info_title,
		'pageNumber' : page,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//删除帮助信息信息
function delete_help_info(){
	var len = $("input:checkbox[name=help_info_check]:checked").length;
	if(len>0){
		var str = "";
		$('input:checkbox[name=help_info_check]:checked').each(function(i) {
			if (0 == i) {
				str = $(this).val();
			} else {
				str += ","+$(this).val();
			}
		});
		layer.confirm("提示：您好，确定要删除吗？",function(){
			layer.closeAll('dialog');
			$.post("/help_center/delete_help_info.jr",{
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
//修改帮助信息弹窗
 function update_help_info(help_id,page){
	 layer.open({
		  type: 2,
		  title: ['修改课程咨询'],
		  area: ['1000px', '700px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/help_center/to_update_help_info.jr?help_id="+help_id
		  });
 }
//信息弹窗
function tanchuang(content){
	layer.alert(content, {
		  icon: 1,
		  skin: 'layer-ext-moon' 
		})
}
//跳转页面
function info_tiaozhuan_page(page,limit){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			 $.post("/help_center/get_help_info_main.jr", {
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
</script>
  <style>
      .operation #add_help_info {
		  margin-right:20px;
		  }
    </style>
<div class="back_right" style="min-width:900px;">
  <div class="back_r_top">
    <div class="left">帮助信息</div>
        <div class="right" id="timer">
</div>
  </div>
  <div class="right_content">
    <div class="operation">
       <div class="opera_left left">
       <button type="button" onclick="to_add_help_info()" class="btn left btn-primary" id="add_help_info"><i class="fa fa-plus"></i>添加</button>
        <button type="button" onclick="delete_help_info()" class="btn left btn-danger" id="delete_help_info"><i class="fa fa-minus"></i>删除</button>
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
          <th>标题</th>
          <th>编辑</th>
           <th>类别</th>
          <th>添加时间</th>
          <th>操作</th>
          </tr>
        </thead>
        <c:if test="${not empty helpCenters.list }">
				<tbody>
				<c:forEach items="${helpCenters.list }" var="help_info" varStatus="vs">
                    <c:if test="${vs.count%2 == '0' }">
                    <tr class="active">
                    </c:if>
                    <c:if test="${vs.count%2 != '0' }">
                    <tr>
                    </c:if>
            <td>
              <label class="checkbox checkbox-inline" for="checkbox${help_info.help_id }">
                <input type="checkbox" name="help_info_check" data-toggle="checkbox" value="${help_info.help_id  }" id="checkbox${help_info.help_id  }" class="checkbox" required>
              </label>
            </td>
            <td>
              <label class="label label-success btn-primary" for="minimal-checkbox-1">${vs.index+1+helpCenters.begin }</label>
            </td>
            <td>${help_info.title }</td>
              <td>${help_info.bianji }</td>
               <td>${help_info.help_type }</td>
            <td><fmt:formatDate type="both" value="${help_info.help_time }" /></td>           
            <td>
            <button type="button" onclick="update_help_info(${help_info.help_id  },${helpCenters.pageNumber})" class="btn btn-warning btn-xs">编辑</button>
              <button type="button" onclick="to_check_help_info(${help_info.help_id },${helpCenters.pageNumber})" class="btn btn-success btn-xs">查看</button>
            </td>
          </tr>
          </c:forEach>
        </tbody>
        </c:if>
	  </table>
	   <c:if test="${ empty helpCenters.list }">
				<p class="zanwu">暂无帮助信息内容</p>
				</c:if>
    </div>
    <div class="pages">
      <div class="whole left">
        <button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
        <button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
      </div> 
       <div class="whole left">
					<div class="go_page">
					  <span>跳转到第</span><input type="text" id="page_num" />
					  <span>页</span>
					  <a href="javascript:void(0)" onclick="info_tiaozhuan_page(${helpCenters.pages},20,'${info_title }')" class="btn_go" style="background-color:#06c1ae;">GO</a>
					</div>
				</div>
      <ul class="pagination right">
        <c:if test="${helpCenters.hasPreviousPage==true}">
						<li class="previous"
							onclick="help_info_jump_page(${helpCenters.pageNumber-1},'${info_title }')"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${helpCenters.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${helpCenters.pageNumber==page}">
								<li class="active"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="help_info_jump_page(${page},'${info_title }')"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${helpCenters.hasNextPage==true}">
						<li class="next" onclick="help_info_jump_page(${helpCenters.pageNumber+1},'${info_title }')"><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
      </ul>
    </div>
  </div>  
</div>