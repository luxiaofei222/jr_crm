<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/crm/index/crm.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/crm/company/company.js"></script>
<style>
.select2-chosen {
	color: white !important;
}
.right_content .operation {
	width:100%;
	height:auto!important;
	padding:14px;
	background:#e6e9f0;
	}
</style>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">企业管理</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<div class="operation">
			<div class="tablewai">
				<table border='1'>
					<tr>
						<td width="12.5%" class="grey">企业名称</td>
						<td colspan="3" width="37.5%" class="kong">${company.company_name }</td>
						<td width="12.5%" class="grey">所属行业</td>
						<td colspan="3" width="37.5%" class="kong">${company.suoshuhangye }</td>
					</tr>
					<tr>
						<td width="12.5%" class="grey">企业规模</td>
						<td width="12.5%" class="kong">${company.company_guimo }</td>
						<td width="12.5%" class="grey">企业类型</td>
						<td width="12.5%" class="kong">${company.company_type }</td>
						<td width="12.5%" class="grey">经营范围</td>
						<td width="12.5%" class="kong">${company.company_jingying }</td>
						<td width="12.5%" class="grey">所属集团</td>
						<td width="12.5%" class="kong">${company.company_jituan }</td>
					</tr>
					<tr>
						<td width="12.5%" class="grey">省</td>
						<td width="12.5%" class="kong">${company.company_province }</td>
						<td width="12.5%" class="grey">市</td>
						<td width="12.5%" class="kong">${company.company_city }</td>
						<td width="12.5%" class="grey">区号</td>
						<td width="12.5%" class="kong">${company.company_quhao }</td>
						<td width="12.5%" class="grey">邮编</td>
						<td width="12.5%" class="kong">${company.company_youbian }</td>
					</tr>
					<tr>
						<td width="12.5%" class="grey">地址</td>
						<td colspan="3" width="37.5%" class="kong">${company.company_addr }</td>
						<td width="12.5%" class="grey">网址</td>
						<td colspan="3" width="37.5%" class="kong"><a
							href="${company.company_web }" target="_blank">${company.company_web }</a></td>
					</tr>
					<tr>
						<td width="12.5%" class="grey">联系电话</td>
						<td colspan="3" width="37.5%" class="kong">${company.company_zongjingli_one_phone }</td>
						<td width="12.5%" class="grey">负责人</td>
						<td colspan="3" width="37.5%" class="kong">${company.company_zongjingli_one }</td>
					</tr>
					<c:if test="${not empty customerPlates }">
						<tr>
							<c:forEach items="${customerPlates }" var="plate">
							<td width="12.5%" class="grey">${plate.palte_title }</td>
							<td width="12.5%" class="kong">${plate.plate_content }</td>
							</c:forEach>
						</tr>
					</c:if>
				</table>
			</div>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<!--  <th></th> -->
						<th>编号</th>
						<th>部门</th>
						<th>职务</th>
						<th>联系人</th>
						<th>性别</th>
						<th>办公电话</th>
						<th>手机</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:if test="${not empty customers.list }">
					<tbody>
						<c:forEach items="${customers.list }" var="customer"
							varStatus="vs">
							<c:if test="${vs.count%2 == '0' }">
								<tr class="active">
							</c:if>
							<c:if test="${vs.count%2 != '0' }">
								<tr>
							</c:if>
							<!--    <td>
              <label class="checkbox checkbox-inline" for="checkbox1">
                <input type="checkbox" data-toggle="checkbox" value="" id="checkbox1" class="checkbox" required>
              </label>
            </td> -->
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+customers.begin }</label></td>
							<td>${customer.customer_depart }</td>
							<td>${customer.customer_position }</td>
							<td>${customer.customer_name }</td>
							<td>${customer.customer_sex }</td>
							<td>
							<c:if test="${not empty customer.customer_officephone }">
							<i class="fa fa-phone-square"
								style="font-size: 20px; color: #94CE6E;"></i>${customer.customer_officephone }
							</c:if>
							</td>
							<td>
							<i class="fa fa-phone-square"
								style="font-size: 20px; color: #94CE6E;"></i>${customer.customer_phone }</td>
							<td>${customer.customer_note }</td>
							<td><i class="fa fa-eye one" onclick="to_check_lianxiren(${customer.customer_id })" title="查看联系人信息"></i>
							 <i class="fa fa-edit two" onclick="to_update_customer(${customer.customer_id },${customer.company_id })" title="编辑联系人信息"></i>
								<i class="fa fa-minus-circle three" onclick="delete_custome(${customer.customer_id },${customer.company_id })" title="删除联系人信息"></i></td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ empty customers.list }">
				<p class="zanwu" style="text-align:center;color:#94CE6E;">暂无联系人信息内容</p>
				</c:if>
		</div>
		<div class="pages">
			<!--  <div class="whole left">
        <button type="button" class="btn btn-info btn-sm selectall">全部选中</button>
        <button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
      </div> -->
			<ul class="pagination right">
				<c:if test="${customers.hasPreviousPage==true}">
						<li class="previous"
							onclick="customer_jump_page(${customers.pageNumber-1},${company.company_id })"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${customers.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${customers.pageNumber==page}">
								<li class="active" onclick="customer_jump_page(${page},${company.company_id })"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="customer_jump_page(${page},${company.company_id })"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${customers.hasNextPage==true}">
						<li class="next" onclick="customer_jump_page(${customers.pageNumber+1},${company.company_id })">><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
			</ul>
		</div>
		<div class="form-group" style="margin-top:15px;">
      		<div class="col-xs-12" style="text-align:center;">
      		<button type="button" class="btn btn-warning btn-lg" onclick="company_jump_page(1,20)" style="width:100px;">返回</button>
      		</div>
      	</div>
	</div>