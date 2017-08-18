<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/font-awesome-4.6.3/css/font-awesome.min.css" />
<link rel="stylesheet" href="/css/crm/index/qiyeku.css" />
<link rel="stylesheet" href="/css/page/pages.css" />
<link rel="stylesheet" type="text/css" href="/css/crm/index/crm.css">
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script src="/js/crm/company/company.js"></script>
<style>
.select2-chosen{
	color:white!important;
}
</style>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">企业管理</div>
		<div class="right" id="timer"></div>
	</div>
		<div class="right_content">
			<div class="operation">
				<div class="opera_left left">
					<button type="button" onclick="add_my_company_piliang(${companies.pageNumber},${limit},'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${employee_name }')" class="btn left" id="add_company">
						<i class="fa fa-plus"></i>加入我的企业库
					</button>
				</div>
				<div class="opera_right right">
					<select class="form-control select left top_select"
						data-toggle="select" id="select_val">
						<option value="0">请选择查询条件</option>
						<option value="company_name">企业名称</option>
						<option value="company_province">企业所在省</option>
						<option value="company_city">企业所在市</option>
						<option value="hangye">行业类别</option>
						<option value="yuangong">添加人</option>
					</select> <input type="text" id="check_val" placeholder="请输入查询内容"
						class="form-control left top_search" />
					<button type="button" onclick="seacher_my_company(${limit})"
						class="btn left btn-primary">查询</button>
				</div>
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
							<th>是否加入</th>
							<th>添加时间</th>
							<th width="25%">操作</th>
						</tr>
					</thead>
							<c:if test="${not empty companies.list }">
				<tbody>
				<c:forEach items="${companies.list }" var="company" varStatus="vs">
                    <c:if test="${vs.count%2 == '0' }">
                    <tr class="active">
                    </c:if>
                    <c:if test="${vs.count%2 != '0' }">
                    <tr>
                    </c:if>
							<td>
							<c:if test="${company.is_jiaru_mybusiness=='是' }">
							<label class="checkbox checkbox-inline" for="checkbox${company.company_id }"> 
									<input type="checkbox" data-toggle="checkbox" disabled="disabled"
									id="checkbox${company.company_id }" class="checkbox" required>
							</label>
							</c:if>
							<c:if test="${company.is_jiaru_mybusiness=='否' }">
							<label class="checkbox checkbox-inline" for="checkbox${company.company_id }">
									<input type="checkbox" data-toggle="checkbox" value="${company.company_id }" name="companye_id"
									id="checkbox${company.company_id }" class="checkbox" required>
							</label>
							</c:if>
							</td>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${companies.total - ((companies.pageNumber - 1) * companies.limit + vs.index) }</label></td>
							<td>${company.company_name }</td>
							<td>${company.suoshuhangye }</td>
							<td>${company.employee.employee_name }</td>
							<c:if test="${company.is_jiaru_mybusiness=='是' }">
							<td style="color: orange;">已加入</td>
							</c:if>
							<c:if test="${company.is_jiaru_mybusiness=='否' }">
							<td style="color: green;">请选择</td>
							</c:if>
							<td><fmt:formatDate type="both" value="${company.company_time }" /></td>
							<td>
							<c:if test="${company.is_jiaru_mybusiness=='否' }">
							<i class="fa fa-plus-circle one" onclick="add_my_company(this,${company.company_id },${companies.pageNumber},${limit})" title="加入我得企业库"></i>
							</c:if>
							<c:if test="${company.is_jiaru_mybusiness=='是' }">
							--
							</c:if>
							</td>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</c:if>
				</table>
				<c:if test="${ empty companies.list }">
				<p class="zanwu" style="text-align:center;color:#94CE6E;">暂无企业信息内容</p>
				</c:if>
			</div>
			<div class="pages">
				<div class="whole left">
					<button type="button" class="btn btn-info btn-sm selectall">全部选中</button>
					<button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
					<div class="go_page">
					  <span>跳转到第</span><input type="text" id="page_num" />
					  <span>页</span>
					  <a href="javascript:void(0)" onclick="my_company_tiaozhuan_page(${companies.pages},${limit},'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${employee_name }')" class="btn_go">GO</a>
					</div>
					<div class="tongji">
					  <span>您已加入<em>${my_company_number }</em>条企业信息</span>
					</div>
				</div>
				<ul class="pagination right">
				    <li><a href="javascript:void(0)" onclick="mycompany_jump_page(1,${limit },'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${employee_name }')" class="fa fa-fast-backward"></a></li>
					<c:if test="${companies.hasPreviousPage==true}">
						<li class="previous"
							onclick="mycompany_jump_page(${companies.pageNumber-1},${limit },'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${employee_name }')"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${companies.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${companies.pageNumber==page}">
								<li class="active"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="mycompany_jump_page(${page},${limit },'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${employee_name }')"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					 <li class="pagination-dropdown dropup">
                <a href="#fakelink" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fui-triangle-up"></i>
                </a>
                <ul class="dropdown-menu">
                  <li><a href="#fakelink" onclick="mycompany_jump_page(${companies.pageNumber},20,'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${employee_name }')">20</a></li>
                  <li><a href="#fakelink" onclick="mycompany_jump_page(${companies.pageNumber},50,'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${employee_name }')">50</a></li>
                  <li><a href="#fakelink" onclick="mycompany_jump_page(${companies.pageNumber},100,'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${employee_name }')">100</a></li>
                </ul>
              </li>
					<c:if test="${companies.hasNextPage==true}">
						<li class="next" onclick="mycompany_jump_page(${companies.pageNumber+1},${limit },'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${employee_name }')"><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
					<li onclick="mycompany_jump_page(${companies.pages},${limit },'${company_name }','${company_province }','${company_city }','${suoshuhangye }','${employee_name }')"><a href="javascript:void(0)" class="fa fa-fast-forward"></a></li>
				</ul>
			</div>
		    <div class="clear"></div>
		</div>
	