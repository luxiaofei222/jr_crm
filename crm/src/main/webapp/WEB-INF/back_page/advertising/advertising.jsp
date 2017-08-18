<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css"/>
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/school/back/adver/adver.js"></script>
<div class="back_right" style="min-width:900px;">
  <div class="back_r_top">
    <div class="left">广告管理</div>
        <div class="right" id="timer">
</div>
  </div>
  <div class="right_content">
    <div class="operation">
      <div class="opera_left left">
        <button type="button" onclick="to_add_adver()" class="btn left btn-primary" id="add_adver"><i class="fa fa-plus"></i>&nbsp;添加广告</button>  
      </div>
      <div class="opera_right right">
        <select onchange="seacher_adver()" class="form-control select select-primary left top_select" id="select_val" data-toggle="select">
            <option value="0">请选择位置查询</option>
            <option value="首页">首页</option>
          </select>
          <!-- <input type="text" id="check_val" placeholder="请输入查询内容" class="form-control left top_search" /> -->
          <!-- <button type="button" onclick="seacher_adver()" class="btn left btn-primary">查询</button> -->
      </div>
    </div>
    <div class="content_message">

      <table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
                       <!--  <th></th> -->
                        <th >编号</th>
						<!--<th>编号</th>-->
						<th>名称</th>
						<th>位置</th>
						<th>添加时间</th>
                        <th width="25%">操作</th>
					</tr>
				</thead>
				<c:if test="${not empty advertisings.list }">
				<tbody>
				<c:forEach items="${advertisings.list }" var="adver" varStatus="vs">
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
                          <label class="label label-success btn-primary" for="minimal-checkbox-1">${vs.index+1+advertisings.begin }</label>
                        </td>
                        <c:if test="${not empty adver.adver_name }">
						<td>${adver.adver_name }</td>
						</c:if>
						 <c:if test="${empty adver.adver_name }">
						<td style="color: orange;">暂无</td>
						</c:if>
                        <td>${adver.adver_type }</td>
						<td><fmt:formatDate type="both" value="${adver.adver_time }" /></td>
						<td>
						<c:if test="${adver.is_show=='是' }">
                          <button type="button" onclick="update_isshow(${adver.adver_id },'否')" class="btn btn-success btn-xs">下架</button>
                          </c:if>
                          <c:if test="${adver.is_show=='否' }">
                          <button type="button" onclick="update_isshow(${adver.adver_id },'是')" class="btn btn-warning btn-xs">上架</button>
                          </c:if>
                          <button type="button" onclick="delete_adver(${adver.adver_id})" class="btn btn-danger btn-xs">删除</button>
                          <button type="button" onclick="to_update_adver(${adver.adver_id})" class="btn btn-info btn-xs">修改</button>
                        </td>
                        </td>
					</tr>
					</c:forEach>
				</tbody>
				</c:if>
			</table>
			    <c:if test="${ empty advertisings.list }">
				<p class="zanwu">暂无广告内容</p>
				</c:if>
    </div>
    <div class="pages">
     <!--  <div class="whole left">
        <button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
        <button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
          
      </div> -->
      <ul class="pagination right">
        <c:if test="${advertisings.hasPreviousPage==true}">
						<li class="previous"
							onclick="adver_jump_page(${advertisings.pageNumber-1})"><a
							href="#fakelink" class="fa fa-angle-left"></a></li>
					</c:if>
					<c:forEach items="${advertisings.navigatePageNumbers }" var="page">
						<c:choose>
							<c:when test="${advertisings.pageNumber==page}">
								<li class="active" onclick="adver_jump_page(${page})"><a
									href="#fakelink">${page}</a></li>
							</c:when>
							<c:otherwise>
								<li onclick="adver_jump_page(${page})"><a href="#fakelink">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${advertisings.hasNextPage==true}">
						<li class="next" onclick="adver_jump_page(${advertisings.pageNumber+1})">><a
							href="#fakelink" class="fa fa-angle-right"></a></li>
					</c:if>
      </ul>
    </div>
  </div>  
</div>