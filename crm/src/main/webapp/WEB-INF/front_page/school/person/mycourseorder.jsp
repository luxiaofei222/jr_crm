<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <script>
 function delete_order(order_id){
		 layer.confirm("提示：您好，确定要删除吗？",function(){
				layer.closeAll('dialog');
				$.post("/person/delete_order.html",{
					'order_id':order_id
				},function(data){
					if(data==1){
						tanchuang("删除成功！");
					}else{
						tanchuang("删除失败！");
					}
				});
				})
 }
 </script>
 <h1>我的订单</h1> 
      <div class="person_list_title">
        <ul>
          <li class="li1">课程名称</li>
          <li class="li2">价格</li>
          <li class="li3">实付款</li>
          <li class="li4">交易状态</li>
          <li class="li5">交易操作</li>
        </ul>
      </div> 
      <div class="order">
    <c:if test="${empty courseOrders.list }">
     <p class="without"><img src="/images/school/front/index/user_moren.png"><span>暂无订单信息！</span></p><div class="clear"></div>
    </c:if>
    <c:if test="${not empty courseOrders.list }">
      <c:forEach items="${courseOrders.list }" varStatus="vs" var="order">
        <div class="list">
          <div class="list_top">
            <span>&nbsp;${vs.index+1 }.&nbsp;</span>
            <span class="top_time"><fmt:formatDate type="BOTH" value="${order.order_time }" /></span>
            <span class="list_number">订单号：${order.order_number }</span>
            <span class="delete" onclick="delete_order('${order.order_id }')"><i class="fa fa-trash"></i></span>
          </div>
          <ul>
            <li class="l1">
              <img src="${order.courseVideo.video_pic }" width="140" height="80" />
              <div class="mycourse_message">
                <p>${order.courseVideo.video_name }</p>
                <p class="course_number">${order.courseVideo.keshi_number }课时</p>  
              </div>
            </li>
            <li class="l2">￥ ${order.courseVideo.video_money_now }<!-- 0.00 --></li>
            <c:if test="${not empty order.pay_money }">
            <li class="l3">￥${order.pay_money }</li>
            </c:if>
             <c:if test="${empty order.pay_money }">
            <li class="l3">未支付</li>
            </c:if>
            <li class="l4">
              <p class="zhuangtai">${order.order_state }</p>
              <c:if test="${order.order_state =='待付款' }">
              <p class="order_message" onclick="get_xiangqing('${order.order_id }')" style="cursor: pointer;">订单详情</p>
              </c:if>
              <c:if test="${order.order_state =='已付款' }">
              <p class="order_message" onclick="get_xiangqing('${order.order_id }')" style="cursor: pointer;">订单详情</p>
              </c:if>
            </li>
            <li class="l5">
            <c:if test="${order.order_state =='待付款' }">
              <a href="javascript:void(0)">去支付</a>
              </c:if>
              <c:if test="${order.order_state =='关闭' }">
              <a href="javascript:void(0)">订单关闭</a>
              </c:if>
              <c:if test="${order.order_state =='已付款' }">
              <a href="javascript:void(0)">订单完成</a>
              </c:if>
            </li>
          </ul>
        </div>
        <div class="clear"></div>
        </c:forEach>
        </c:if>
      </div>
      <!------------------------------order  end--------------------------->
      <div class="person_list_bottom">
        <!-- <div class="caozuo">
          <span class="quanxuan">全选</span>
          <span class="esc">取消</span>
        </div> -->
        <div class="search_fenye">
        <ul>
           <c:if test="${courseOrders.hasPreviousPage==true}">
						<li class="fenyeda" onclick="mycourse_order_jump_page(${courseOrders.pageNumber-1})"><a href="javascript:void(0)"> 上一页 </a></li>
					</c:if>
					<c:forEach items="${courseOrders.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${courseOrders.pageNumber==page}">
								<li class="pageactive"><a href="javascript:void(0)">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="mycourse_order_jump_page(${page})"><a href="javascript:void(0)">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${courseOrders.hasNextPage==true}">
						<li onclick="mycourse_order_jump_page(${courseOrders.pageNumber+1})" class="fenyeda"><a href="javascript:void(0)"> 下一页 </a></li>
					</c:if>
        </ul>
       </div>
      </div>
      <div class="clear"></div>