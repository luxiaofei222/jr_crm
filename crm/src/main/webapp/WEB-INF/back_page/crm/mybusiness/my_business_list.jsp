<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet"
	href="/font-awesome-4.6.3/css/font-awesome.min.css" />
<link rel="stylesheet" href="/css/crm/index/qiyeku.css" />
<link rel="stylesheet" href="/css/page/pages.css" />
<link rel="stylesheet" type="text/css" href="/css/crm/index/crm.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
.select2-chosen {
	color: white !important;
}
.form-control{
	height:35px;
}
.right_wz {
    text-align: right;
}
.right_content .table tr td i.sign {
    color: #3498db;
}
.right_content .table tr td i.no_sign {
    color: #ccc;
}
.form-control:focus{
	border:2px solid #94CE6E;
}
</style>
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
		
	$(".pagination-dropdown").click(function(){
		$(this).children(".dropdown-menu").toggle();
	  })
	}) 
	
	//查询关键字
/* 	function seacher_company(limit) {
		var drop_menu = $('#select_val').val(); //获取查询下拉菜单的值
		var drop_menu_val = $('#check_val').val(); //获取输入框里面的值
		if (drop_menu == "company_name") {
			jiazaidonghua();
			$.post("/back_mybusiness/get_my_business_list.jr", {
				'company_name' : drop_menu_val,
				'pageNumber' : 1,
				'limit' : limit
			}, function(data) {
				if (data != null) {
					$('#conten_list').html(data);
				}
			});
		}else if (drop_menu == "company_province") {
			jiazaidonghua();
			$.post("/back_mybusiness/get_my_business_list.jr", {
				'company_province' : drop_menu_val,
				'pageNumber' : 1,
				'limit' : limit
			}, function(data) {
				if (data != null) {
					$('#conten_list').html(data);
				}
			});
		}else if (drop_menu == "company_city") {
			jiazaidonghua();
			$.post("/back_mybusiness/get_my_business_list.jr", {
				'company_city' : drop_menu_val,
				'pageNumber' : 1,
				'limit' : limit
			}, function(data) {
				if (data != null) {
					$('#conten_list').html(data);
				}
			});
		}else if (drop_menu == "hangye") {
			jiazaidonghua();
			$.post("/back_mybusiness/get_my_business_list.jr", {
				'suoshuhangye' : drop_menu_val,
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
				$.post("/back_mybusiness/get_my_business_list.jr", {
					'pageNumber' : 1,
					'limit' : limit
				}, function(data) {
					$("#conten_list").html(data);
				})
			});
		}
	}
 */	//标记企业
function to_flag_company(my_company_id,isflag,company_name){
	 $.post("/back_mybusiness/flag_company.jr", {
		 'my_company_id':my_company_id,
		 'is_flag':isflag,
		 'company_name':company_name
	}, function(data) {
		if(data==1){
			layer.msg("设置成功！");
		}else{
			layer.msg("系统发生错误！");
		}
	}) 
}
	
//企业信息列表翻页-上一页 下一页
function company_jump_page(page,limit,company_name,company_province,company_city,suoshuhangye,qiye_chaxun) {
	jiazaidonghua();
	 $.post("/back_mybusiness/get_my_business_list.jr", {
		 'company_province':company_province,
		 'company_city':company_city,
		 'suoshuhangye':suoshuhangye,
		 'company_name':company_name,
		'pageNumber' : page,
		'limit' : limit,
		'qiye_chaxun':qiye_chaxun
	}, function(data) {
		$("#conten_list").html(data);
	}) 
}

//输入页数
function company_tiaozhuan_page(page,limit,company_name,company_province,company_city,suoshuhangye,qiye_chaxun) {
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			 $.post("/back_mybusiness/get_my_business_list.jr", {
				 'company_province':company_province,
				 'company_city':company_city,
				 'suoshuhangye':suoshuhangye,
				 'company_name':company_name,
					'pageNumber' : page_num,
					'limit' : limit,
					'qiye_chaxun':qiye_chaxun
				}, function(data) {
					$("#conten_list").html(data);
				}) 
		}
	}else{
		layer.msg("请输入数字！")
	}
}
//移除企业信息
function delete_my_company(page,my_company_id,limit){
	layer.confirm("提示：您好，确定要移除吗？",function(){
		layer.closeAll('dialog');
		$.post("/back_mybusiness/delete_my_business_list.jr",{
			'my_company_id':my_company_id
		},function(data){
			if(data==1){
				tanchuang("移除成功！");
				company_jump_page(page,limit);
			}else{
				tanchuang("移除失败！");
			}
		});
		})
}
//批量移除企业信息
function delete_my_company_piliang(page,limit){
	var len = $("input:checkbox[name=my_company_id]:checked").length;
	if(len>0){
		var str = "";
		$('input:checkbox[name=my_company_id]:checked').each(function(i) {
			if (0 == i) {
				str = $(this).val();
			} else {
				str += ","+$(this).val();
			}
		});
		layer.confirm("提示：您好，确定要移除吗？",function(){
			layer.closeAll('dialog');
			$.post("/back_mybusiness/delete_my_business_list.jr",{
				'str':str
			},function(data){
				if(data==1){
					tanchuang("移除成功！");
					company_jump_page(page,limit);
				}else{
					tanchuang("移除失败！");
				}
			});
			})
	}else{
		tanchuang("请选择至少一条记录！");
	}
}
//查看企业信息详情
function check_business_ditail(company_id,page,limit,company_name,company_province,company_city,suoshuhangye,qiye_chaxun){
	jiazaidonghua();
	 $.post("/back_mybusiness/check_business_detail.jr", {
		 'company_province':company_province,
		 'company_city':company_city,
		 'suoshuhangye':suoshuhangye,
		 'company_name':company_name,
		 	'company_id':company_id,
			'pageNumber' : 1,
			'limit' : 20,
			'company_page':page,
			'company_limit':limit,
			'qiye_chaxun':qiye_chaxun
		}, function(data) {
			$("#conten_list").html(data);
		}) 
}
//添加联系人弹窗
function to_add_lianxiren(company_id,limit){
	layer.open({
		type : 2,
		title : [ '添加联系人信息' ],
		area : [ '700px', '700px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/crm_business/to_add_lianxiren.jr?company_id="+company_id,
		end : function() {
			company_jump_page(1,limit);
		}
	});
}
//添加企业信息
function to_add_company(limit){
	layer.open({
		type : 2,
		title : [ '添加企业信息' ],
		area : [ '900px', '500px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/crm_business/to_add_business.jr?type=mycom",
		end : function() {
			company_jump_page(1,limit);
		}
	});
}
//条件筛查
function chaxun_my_company(obj,limit){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("查询中");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/back_mybusiness/get_my_business_list.jr",
			data:{
				'pageNumber':1,
				'limit':limit
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
<div class="back_right">
	<div class="back_r_top">
		<div class="left">企业管理</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
	    <form enctype="multipart/form-data" id="myform" class="form-horizontal">
			<div class="form-group clearfix"  style="padding-top: 10px; margin-left: 0px; padding-left: 10px;">
				<select class="col-xs-1" name="qiye_chaxun" style="line-height:35px;height:35px;">
					<option value="jingque">企业精确查询</option>
					<option value="mohu">企业模糊查询</option>
				</select>
				<div class="col-xs-2">
					<input class="form-control" name="company_name" id="company_name" placeholder="企业名称">
				</div>
				<label class="col-xs-1 right_wz">企业省份：</label>
				<div class="col-xs-2">
					<input class="form-control" name="company_province" id="company_province" placeholder="企业省份">
				</div>
				<label class="col-xs-1 right_wz">企业城市：</label>
				<div class="col-xs-2">
					<input class="form-control" name="company_city" id="company_city" placeholder="企业城市">
				</div>
			</div>
			<div class="form-group clearfix"  style="margin-top: 0px; margin-left: 0px; padding-left: 10px;">
				<label class="col-xs-1 right_wz">行业类别：</label>
				<div class="col-xs-2">
					<input class="form-control" name="suoshuhangye" id="suoshuhangye" placeholder="行业类别">
				</div>
				<div class="col-xs-2">
			      <button type="button" onclick="chaxun_my_company(this,20)" class="btn btn-primary btn-lg" style="width:100px;height:35px;background-color:#94CE6E;line-height:15px;">查询</button>
			    </div>
			</div>
			</form>
		<div class="operation">
			<div class="opera_left left">
				<button type="button"
					onclick="delete_my_company_piliang(${companies.pageNumber},${limit})"
					class="btn left" id="add_company">
					<i class="fa fa-minus"></i>移除企业
				</button>
				<button type="button" onclick="to_add_company(${limit})"
					class="btn left" id="add_company">
					<i class="fa fa-plus"></i>企业开发
				</button>
			</div>
		<%-- 	<div class="opera_right right">
				<select class="form-control select left top_select"
					data-toggle="select" id="select_val">
					<option value="0">请选择查询条件</option>
					<option value="company_name">企业名称</option>
					<option value="company_province">企业所在省</option>
					<option value="company_city">企业所在市</option>
					<option value="hangye">行业类别</option>
				</select> <input type="text" id="check_val" placeholder="请输入查询内容"
					class="form-control left top_search" />
				<button type="button" onclick="seacher_company(${limit})"
					class="btn left btn-primary">查询</button>
			</div> --%>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th></th>
						<th>编号</th>
						<th>企业名称</th>
						<th>所属行业</th>
						<th>添加人</th>
						<th>联系人数量</th>
						<th>报名人数</th>
						<th>添加时间</th>
						<th width="25%">操作</th>
					</tr>
				</thead>
				<c:if test="${not empty companies.list }">
					<tbody>
						<c:forEach items="${companies.list }" var="company" varStatus="vs">
							<c:if test="${not empty company.company.company_name }">
								<c:if test="${vs.count%2 == '0' }">
									<tr class="active">
								</c:if>
								<c:if test="${vs.count%2 != '0' }">
									<tr>
								</c:if>
								<td><label class="checkbox checkbox-inline"
									for="checkbox${company.my_company_id }"> <input
										type="checkbox" data-toggle="checkbox"
										value="${company.my_company_id }"
										id="checkbox${company.my_company_id }" name="my_company_id"
										class="checkbox" required>
								</label></td>
								<td><label class="label label-success btn-primary"
									for="minimal-checkbox-1">${companies.total - ((companies.pageNumber - 1) * companies.limit + vs.index) }</label></td>
								<td
									onclick="check_business_ditail(${company.company.company_id },${companies.pageNumber},${limit },'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${qiye_chaxun }')"
									style="cursor: pointer;">${company.company.company_name }</td>
								<td>${company.company.suoshuhangye }</td>
								<td>${company.company.employee.employee_name }</td>
								<td>${company.company.lianxirennumber }</td>
								<td>${company.company.baokaonumber }</td>
								<td><fmt:formatDate type="both"
										value="${company.my_company_time }" /></td>
								<td><i class="fa fa-plus-circle one"
									onclick="to_add_lianxiren(${company.company_id },${limit})"
									title="添加联系人"></i> <i class="fa fa-minus-circle one"
									onclick="delete_my_company(${companies.pageNumber},${company.my_company_id },${limit})"
									title="移除企业信息"></i> <i class="fa fa-eye three"
									onclick="check_business_ditail(${company.company.company_id },${companies.pageNumber},${limit },'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${qiye_chaxun }')"
									title="查看企业详情"></i> <c:if test="${company.is_flag==1 }">
										<i class="fa fa-flag three sign"
											onclick="to_flag_company(${company.my_company_id },0)"
											title="查看企业详情"></i>
									</c:if> <c:if test="${company.is_flag==0 }">
										<i class="fa fa-flag three no_sign"
											onclick="to_flag_company(${company.my_company_id },1,'${company.company.company_name }')"
											title="标记企业"></i>
									</c:if></td>
								</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ empty companies.list }">
				<p class="zanwu" style="text-align: center; color: #94CE6E;">暂无企业信息内容</p>
			</c:if>
		</div>
		<div class="pages">
			<div class="whole left">
				<button type="button" class="btn btn-info btn-sm selectall">全部选中</button>
				<button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
				<div class="go_page">
					<span>跳转到第</span><input type="text" id="page_num" /> <span>页</span>
					<a href="javascript:void(0)"
						onclick="company_tiaozhuan_page(${companies.pages},${limit},'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${qiye_chaxun }')"
						class="btn_go">GO</a>
				</div>
				<div class="tongji">
					<span>您已加入<em>${my_company_number }</em>条企业信息
					</span>
				</div>
			</div>
			<ul class="pagination right">
				<li><a href="javascript:void(0)"
					onclick="company_jump_page(1,${limit },'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${qiye_chaxun }')"
					class="fa fa-fast-backward"></a></li>
				<c:if test="${companies.hasPreviousPage==true}">
					<li class="previous"
						onclick="company_jump_page(${companies.pageNumber-1},${limit },'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${qiye_chaxun }')"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${companies.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${companies.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li
								onclick="company_jump_page(${page},${limit },'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${qiye_chaxun }')"><a
								href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li class="pagination-dropdown dropup"><a href="#fakelink"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="fui-triangle-up"></i>
				</a>
					<ul class="dropdown-menu">
						<li><a href="#fakelink"
							onclick="company_jump_page(${companies.pageNumber},20,'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${qiye_chaxun }')">20</a></li>
						<li><a href="#fakelink"
							onclick="company_jump_page(${companies.pageNumber},50,'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${qiye_chaxun }')">50</a></li>
					</ul></li>
				<c:if test="${companies.hasNextPage==true}">
					<li class="next"
						onclick="company_jump_page(${companies.pageNumber+1},${limit },'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${qiye_chaxun }')"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
				<li
					onclick="company_jump_page(${companies.pages},${limit },'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${qiye_chaxun }')"><a
					href="javascript:void(0)" class="fa fa-fast-forward"></a></li>
			</ul>
		</div>
		<div class="clear"></div>
	</div>