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
//订单列表翻页-上一页 下一页
function order_jump_page(page){
	jiazaidonghua();
	$.post("/back_order/get_order_list.jr",{
		'pageNumber' : page,
		'limit' : 15
	},function(data){
		$("#conten_list").html(data);
	})
}
//查询关键字
function seacher_order(){
	var drop_menu=$('#select_val').val();//获取查询下拉菜单的值
	var drop_menu_val=$('#check_val').val(); //获取输入框里面的值
	if(drop_menu=="order_number"){
		jiazaidonghua();
		$.post("/back_order/get_order_list.jr", {
			'order_number':drop_menu_val,
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
			$.post("/back_order/get_order_list.jr",{
				'pageNumber' : 1,
				'limit' : 15
			},function(data){
				$("#conten_list").html(data);
			})
		});
	}
}
//查看订单详情
function get_order_detail(order_id){
	 layer.open({
		  type: 2,
		  title: ['查看订单详情'],
		  area: ['930px', '820px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/back_order/to_check_order.jr?order_id="+order_id
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
<div class="back_right" style="min-width:900px;">
  <div class="back_r_top">
    <div class="left">订单管理</div>
        <div class="right" id="timer">
</div>
  </div>
  <div class="right_content">
    <div class="operation">
      <div class="opera_right right">
        <select class="form-control select select-primary left top_select" id="select_val" data-toggle="select">
            <option value="0">请选择查询条件</option>
            <option value="order_number">订单号</option>
          </select>
          <input type="text" id="check_val" placeholder="请输入查询内容" class="form-control left top_search" />
          <button type="button" onclick="seacher_order()" class="btn left btn-primary">查询</button>
      </div>
    </div>
    <div class="content_message">

      <table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
                        <th>编号</th>
						<th>订单号</th>
						<th>课程名称</th>
						<th>付款人</th>
						<th>订单类型</th>
						<th>订单状态</th>
						<th>下单时间</th>
						<th>用户状态</th>
                        <th width="25%">操作</th>
					</tr>
				</thead>
				<c:if test="${not empty courseOrders.list }">
				<tbody>
				<c:forEach items="${courseOrders.list }" var="order" varStatus="vs">
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
                          <label class="label label-success btn-primary" for="minimal-checkbox-1">${vs.index+1+courseOrders.begin }</label>
                        </td>
						<td>${order.order_number }</td>
                        <td>${order.courseVideo.video_name }</td>
                        <td>${order.user.user_nickname }</td>
                        <td>${order.pay_type }</td>
                        <td>${order.order_state }</td>
						<td><fmt:formatDate type="both" value="${order.order_time }" /></td>
						<c:if test="${order.is_show=='删除' }">
						 <td style="color: orange;"> 已被用户删除</td>
						 </c:if>
						 <c:if test="${order.is_show=='显示' }">
						 <td> 正常显示</td>
						 </c:if>
						<td>
                          <button type="button" onclick="get_order_detail('${order.order_id}')" class="btn btn-success btn-xs">查看详情</button>
                        </td>
                        </td>
					</tr>
					</c:forEach>
				</tbody>
				</c:if>
			</table>
			    <c:if test="${ empty courseOrders.list }">
				<p class="zanwu">暂无订单内容</p>
				</c:if>
    </div>
    <div class="pages">
     <!--  <div class="whole left">
        <button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
        <button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
          
      </div> -->
      <ul class="pagination right">
        <c:if test="${courseOrders.hasPreviousPage==true}">
						<li class="previous"
							onclick="order_jump_page(${courseOrders.pageNumber-1})"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${courseOrders.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${courseOrders.pageNumber==page}">
								<li class="active"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="order_jump_page(${page})"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${courseOrders.hasNextPage==true}">
						<li class="next" onclick="order_jump_page(${courseOrders.pageNumber+1})"><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
      </ul>
    </div>
  </div>  
</div>