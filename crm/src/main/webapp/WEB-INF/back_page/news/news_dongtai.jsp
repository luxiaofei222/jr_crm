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

//添加新闻动态信息
function to_add_news(){
	 layer.open({
		  type: 2,
		  title: ['添加新闻动态'],
		  area: ['900px', '700px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_news/to_add_news.jr",
		  end: function () {
			  news_jump_page(1);
          }
		  });
}
//新闻动态列表翻页-上一页 下一页
function news_jump_page(page){
	jiazaidonghua();
	$.post("/back_news/get_news_main.jr",{
		'pageNumber' : page,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//删除新闻动态信息
function delete_news(){
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
			$.post("/back_news/delete_news.jr",{
				'str':str
			},function(data){
				if(data==1){
					tanchuang("删除成功！");
					news_jump_page(1);
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
function update_meta(news_id){
	 layer.open({
		  type: 2,
		  title: ['修改新闻页面META'],
		  area: ['900px', '300px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_news/to_update_meta.jr?news_id="+news_id,
		  end: function () {
			  news_jump_page(1);
         }
		  });
}
//查看新闻动态
function to_check_news(news_id){
	layer.open({
		  type: 2,
		  title: ['查看新闻动态'],
		  area: ['1100px', '800px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/back_news/to_check_news.jr?news_id="+news_id
		  });
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
    <div class="left">新闻动态</div>
        <div class="right" id="timer">
</div>
  </div>
  <div class="right_content">
    <div class="operation">
       <div class="opera_right pull-right">
        <button type="button" onclick="to_add_news()" class="btn left btn-primary" id="add_news"><i class="fa fa-plus"></i>添加</button>
        <button type="button" onclick="delete_news()" class="btn left btn-danger" id="delete_news"><i class="fa fa-minus"></i>删除</button>
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
        <c:if test="${not empty scNews.list }">
				<tbody>
				<c:forEach items="${scNews.list }" var="news" varStatus="vs">
                    <c:if test="${vs.count%2 == '0' }">
                    <tr class="active">
                    </c:if>
                    <c:if test="${vs.count%2 != '0' }">
                    <tr>
                    </c:if>
            <td>
              <label class="checkbox checkbox-inline" for="checkbox${news.news_id }">
                <input type="checkbox" name="news_check" data-toggle="checkbox" value="${news.news_id }" id="checkbox${news.news_id }" class="checkbox" required>
              </label>
            </td>
            <td>
              <label class="label label-success btn-primary" for="minimal-checkbox-1">${vs.index+1+scNews.begin }</label>
            </td>
            <td>${news.news_title }</td>
              <td>${news.news_eidit }</td>
               <td>${news.news_type }</td>
            <td><fmt:formatDate type="both" value="${news.news_time }" /></td>           
            <td>
              <button type="button" onclick="update_meta(${news.news_id })" class="btn btn-inverse btn-xs">修改META</button>
              <button type="button" onclick="to_check_news(${news.news_id })" class="btn btn-success btn-xs">查看</button>
            </td>
          </tr>
          </c:forEach>
        </tbody>
        </c:if>
	  </table>
	   <c:if test="${ empty scNews.list }">
				<p class="zanwu">暂无新闻内容</p>
				</c:if>
    </div>
    <div class="pages">
      <div class="whole left">
        <button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
        <button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
      </div> 
      <ul class="pagination right">
        <c:if test="${scNews.hasPreviousPage==true}">
						<li class="previous"
							onclick="news_jump_page(${scNews.pageNumber-1})"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${scNews.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${scNews.pageNumber==page}">
								<li class="active" onclick="news_jump_page(${page})"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="news_jump_page(${page})"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${scNews.hasNextPage==true}">
						<li class="next" onclick="news_jump_page(${scNews.pageNumber+1})"><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
      </ul>
    </div>
  </div>  
</div>