<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/crm/index/qiyeku.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" href="/css/page/pages.css" />
<link rel="stylesheet" type="text/css" href="/css/jiaowu/index/jiaowu.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$(".pagination-dropdown").click(function(){
		$(this).children(".dropdown-menu").toggle();
	  })
})
//翻页上一页下一页
function info_jump_page(page,limit){
	jiazaidonghua();
	$.post("/edu_entry/get_entry_info_main.jr",{
		'pageNumber' : page,
		'limit' : limit
	},function(data){
		$("#conten_list").html(data);
	})
}
//学员审核
function to_examine_page(entryinfo_id){
	layer.open({
		type : 2,
		title : [ '报考信息审核' ],
		area : [ '1100px', '900px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/edu_entry/to_examine_page.jr?entryinfo_id="+entryinfo_id
	});
}
//查看学员信息
function to_check_page(entryinfo_id){
	layer.open({
		type : 2,
		title : [ '报考信息查看' ],
		area : [ '1100px', '900px' ],
		shadeClose : true, //点击遮罩关闭
		content : "/edu_entry/to_check_page.jr?entryinfo_id="+entryinfo_id
	});
}
//查询关键字
function seacher_info(limit) {
	var drop_menu = $('#select_val').val(); //获取查询下拉菜单的值
	var drop_menu_val = $('#check_val').val(); //获取输入框里面的值
	if (drop_menu == "order_number") {
		jiazaidonghua();
		$.post("/edu_entry/get_entry_info_main.jr", {
			'orderNumber' : drop_menu_val,
			'pageNumber' : 1,
			'limit' : limit
		}, function(data) {
			if (data != null) {
				$('#conten_list').html(data);
			}
		});
	} else if (drop_menu == "0") {
		layer.confirm("提示：您好，请选择你需要的查询条件！", function() {
			layer.closeAll('dialog');
			jiazaidonghua();
			$.post("/edu_entry/get_entry_info_main.jr", {
				'pageNumber' : 1,
				'limit' : limit
			}, function(data) {
				$("#conten_list").html(data);
			})
		});
	}
}
//跳转页面
function info_tiaozhuan_page(page,limit){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			 $.post("/edu_entry/get_entry_info_main.jr", {
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

//导出数据弹窗
function to_daochu_entry_info(){
	layer.open({
		type : 2,
		title : [ '导出学员信息' ],
		area : [ '800px', '400px' ],
		shadeClose : true, //点击遮罩关闭
		content : "/edu_entry/to_daochu_entry_info.jr?type='网校'"
	});
}
</script>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">网校学员</div>
		<div class="right" id="timer"></div>
	</div>
		<div class="right_content">
			<div class="operation">
				<div class="opera_left left">
				<!--左侧按钮  -->
				<button type="button" onclick="to_daochu_entry_info()" class="btn left">
						<i class="fa fa-cloud-download"></i>导出数据
					</button>
				</div>
				<div class="opera_right right">
				<select class="form-control select left top_select"
						data-toggle="select" id="select_val">
						<option value="0">请选择查询条件</option>
						<option value="order_number">订单号</option>
					</select> <input type="text" id="check_val" placeholder="请输入查询内容"
						class="form-control left top_search" />
					<button type="button" onclick="seacher_info(${limit})"
						class="btn left btn-primary">查询</button>
				</div>
			</div>
			<div class="content_message">
				<table class="table table-hover">
					<thead>
						<tr class="tr_bgcolor warning">
							<th>编号</th>
							<th>订单号</th>
							<th>业务员</th>
							<th>学员姓名</th>
							<th>学员手机号</th>
							<th>报考专业</th>
							<th>报考等级</th>
							<th>缴费状态</th>
							<th>审核状态</th>
							<th>报名时间</th>
							<th>操作</th>
						</tr>
					</thead>
				<c:if test="${not empty entryInfos.list }">
				<tbody>
				<c:forEach items="${entryInfos.list }" var="info" varStatus="vs">
                    <c:if test="${vs.count%2 == '0' }">
                    <tr class="active">
                    </c:if>
                    <c:if test="${vs.count%2 != '0' }">
                    <tr>
                    </c:if>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+entryInfos.begin }</label></td>
							<td>${info.orderNumber }</td>
							<td>
							<c:if test="${not empty info.employee.employee_name }">
							${info.employee.employee_name }
							</c:if>
							<c:if test="${empty info.employee.employee_name }">
							暂无业务员
							</c:if>
							</td>
							<td>
							${info.entryUserName }
							</td>
							<td>${info.entryUserPhone }</td>
							<td>${info.courseMenu.course_name }</td>
							<td>${info.dictionary.dictionary_name }</td>
							<td>
							<c:if test="${info.isPay==1 }">
							已付款
							</c:if>
							<c:if test="${info.isPay!=1 }">
							未缴费
							</c:if>
							</td>
							<td style="color: orange;">
							<c:if test="${info.entryInfoState==3 }">
							审核通过
							</c:if>
							<c:if test="${info.entryInfoState==5 }">
							审核失败
							</c:if>
							</td>
							<td><fmt:formatDate type="both" value="${info.entryInfoTime }" /></td>
							<td>
							<c:if test="${info.entryInfoState ==2}">
							<button type="button" onclick="to_examine_page(${info.entryInfoId })" class="btn btn-success btn-xs">审核</button>
							</c:if>
							<c:if test="${info.entryInfoState ==3}">
							<button type="button" onclick="to_check_page(${info.entryInfoId })" class="btn btn-success btn-xs">查看</button>
							</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</c:if>
				</table>
				<c:if test="${ empty entryInfos.list }">
				<p class="zanwu" style="text-align:center;color:#53C1FE;">暂无报考学员信息</p>
				</c:if>
			</div>
			<div class="pages">
			    <div class="whole left">
					<div class="go_page">
					  <span>跳转到第</span><input type="text" id="page_num" />
					  <span>页</span>
					  <a href="javascript:void(0)" onclick="info_tiaozhuan_page(${entryInfos.pages},${limit})" class="btn_go">GO</a>
					</div>
				</div>
				<ul class="pagination right">
				 <li><a href="javascript:void(0)" onclick="info_jump_page(1,${limit })" class="fa fa-fast-backward"></a></li>
					<c:if test="${entryInfos.hasPreviousPage==true}">
						<li class="previous"
							onclick="info_jump_page(${entryInfos.pageNumber-1},${limit })"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${entryInfos.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${entryInfos.pageNumber==page}">
								<li class="active"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="info_jump_page(${page},${limit })"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					 <li class="pagination-dropdown dropup">
                <a href="#fakelink" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fui-triangle-up"></i>
                </a>
                <ul class="dropdown-menu">
                  <li><a href="#fakelink" onclick="info_jump_page(${entryInfos.pageNumber},20)">20</a></li>
                  <li><a href="#fakelink" onclick="info_jump_page(${entryInfos.pageNumber},50)">50</a></li>
                  <li><a href="#fakelink" onclick="info_jump_page(${entryInfos.pageNumber},100)">100</a></li>
                </ul>
              </li>
					<c:if test="${entryInfos.hasNextPage==true}">
						<li class="next" onclick="info_jump_page(${entryInfos.pageNumber+1},${limit })"><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
					<li onclick="info_jump_page(${entryInfos.pages},${limit })"><a href="javascript:void(0)" class="fa fa-fast-forward"></a></li>
				</ul>
			</div>
		</div>
	