<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script>
//添加成考学校招生信息
function to_add_material(zhaosheng_id){
	 layer.open({
		  type: 2,
		  title: ['添加计划'],
		  area: ['900px', '600px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/material/to_add_material.jr?zhaosheng_id="+zhaosheng_id
		  });
}
//修改教材计划
function to_update_material(material_id){
	layer.open({
		  type: 2,
		  title: ['修改计划'],
		  area: ['900px', '600px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/material/to_update_material.jr?material_id="+material_id
		  });
}

//教材计划翻页-上一页 下一页
function material_jump_page(page,zhaosheng_id,ckpage){
	jiazaidonghua();
	$.post("/material/get_material_list.jr",{
		'ckpage':ckpage,
		'zhaosheng_id':zhaosheng_id,
		'pageNumber' : page,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//刷新当前页面
function refresh_material(zhaosheng_id,pageNumber,ckpage){
	jiazaidonghua();
	$.post("/material/get_material_list.jr",{
		'ckpage':ckpage,
		'zhaosheng_id':zhaosheng_id,
		'pageNumber' : pageNumber,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}

//返回上一个页面
function fanhui_ck_zhaosheng(page){
	jiazaidonghua();
	$.post("/ck_zhaosheng/get_chengkao_zhaosheng_list.jr",{
		'pageNumber' : page,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//添加教材信息
function to_add_jiaocai(material_id,xuexiao_name){
	layer.open({
		  type: 2,
		  title: ['添加计划详情'],
		  area: ['900px', '650px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/material/to_add_jiaocai.jr?material_id="+material_id+"&xuexiao_name="+xuexiao_name
		  });
}
//查看教材信息
function to_check_jiaocai(material_id){
	layer.open({
		  type: 2,
		  title: ['查看计划详情'],
		  area: ['1400px', '600px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/material/to_check_jiaocai.jr?material_id="+material_id
		  });
}
//删除计划
function to_delete_material(material_id){
	layer.confirm("提示：您好，确定要删除吗？",function(){
		layer.closeAll('dialog');
		$.post("/material/delete_material.jr",{
			'material_id':material_id
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
<div class="back_right">
	<div class="back_r_top">
		<div class="left">${ckZhaoSheng.xuexiao_name }教材计划</div>
		<div class="right" id="timer">
		</div>
	</div>
	<div class="right_content">
		<div class="operation">
			<div class="opera_left left">
				 <button type="button" onclick="to_add_material('${ckZhaoSheng.zhaosheng_id}')" class="btn left btn-primary" id="add_role">
					<i class="fa fa-plus"></i>添加计划
				</button>
				 <button type="button" onclick="fanhui_ck_zhaosheng(${ckpage})" class="btn left btn-primary" id="add_role">
					<i class="fa fa-reply"></i>返回
				</button>
				 <button type="button" onclick="refresh_material(${ckZhaoSheng.zhaosheng_id},${materials.pageNumber},${ckpage})" class="btn left btn-primary" id="add_role">
					<i class="fa fa-refresh"></i>刷新
				</button>
			</div>
			<div class="opera_right right">
			</div>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<!-- <th></th> -->
						<th>编号</th>
						<th>教材计划</th>
						<th>入学批次</th>
						<th>层次</th>
						<th>专业名称</th>
						<th>添加时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty materials.list }">
						<tbody>
							<c:forEach items="${materials.list }" var="material" varStatus="vs">
								<c:if test="${vs.count%2 == '0' }">
									<tr class="active">
								</c:if>
								<c:if test="${vs.count%2 != '0' }">
									<tr>
								</c:if>
								<td><label class="label label-success btn-primary"
									for="minimal-checkbox-1">${vs.index+1+materials.begin }</label></td>
								<td>${material.material_jihua }</td>
								<td>${material.ruxue_pici }</td>
								<td>${material.cengci}</td>
								<td>${material.zhuanye_name}</td>
								<td><fmt:formatDate type="both" value="${material.tijiao_time }" /></td>
								<td>
									<button type="button" onclick="to_update_material(${material.material_id})" class="btn btn-success btn-xs">修改</button>
									<button type="button" onclick="to_add_jiaocai(${material.material_id},'${ckZhaoSheng.xuexiao_name}')" class="btn btn-danger btn-xs">添加详情</button>
									<button type="button" onclick="to_check_jiaocai(${material.material_id})" class="btn btn-info btn-xs">查看</button>
									<button type="button" onclick="to_delete_material(${material.material_id})" class="btn btn-primary btn-xs">删除</button>
								</td>
								</tr>
							</c:forEach>
						</tbody>
					</c:if>
			</table>
			<c:if test="${ empty materials.list }">
				<p class="zanwu">本校暂无教材计划</p>
				</c:if>
		</div>
		<div class="pages">
			<!-- 		<div class="whole left">
				<button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
				<button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>

			</div> -->
			<ul class="pagination right">
				<c:if test="${materials.hasPreviousPage==true}">
					<li class="previous"
						onclick="material_jump_page(${materials.pageNumber-1},${ckZhaoSheng.zhaosheng_id},${ckpage})"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${materials.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${materials.pageNumber==page}">
							<li class="active" onclick="material_jump_page(${page},${ckZhaoSheng.zhaosheng_id},${ckpage})"><a
								href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li onclick="material_jump_page(${page},${ckZhaoSheng.zhaosheng_id},${ckpage})"><a href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${materials.hasNextPage==true}">
					<li class="next" onclick="material_jump_page(${materials.pageNumber+1},${ckZhaoSheng.zhaosheng_id},${ckpage})"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>