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
function to_add_wangjiao_xuexiao(){
	 layer.open({
		  type: 2,
		  title: ['网教招生'],
		  area: ['1000px', '700px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/wj_zhaosheng/get_add_wangjiao_info.jr"
		  });
}
//修改学校信息
function to_update_wj_xuexiao(zhaosheng_id){
	 layer.open({
		  type: 2,
		  title: ['修改网教招生信息'],
		  area: ['1000px', '700px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/wj_zhaosheng/to_update_wj_xuexiao.jr?zhaosheng_id="+zhaosheng_id
		  });
}
//学校列表翻页-上一页 下一页
function wjxuexiao_jump_page(page){
	jiazaidonghua();
	$.post("/wj_zhaosheng/get_wangjiao_zhaosheng_list.jr",{
		'pageNumber' : page,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//设置院校是否展示
function change_state(zhaosheng_id,is_show,page){
	$.post("/ck_zhaosheng/change_state.jr", {
		'zhaosheng_id':zhaosheng_id,
		'is_show' : is_show
	}, function(data) {
		if (data== 1) {
			layer.msg("设置成功！");
			wjxuexiao_jump_page(page);
		}else{
			layer.msg("设置失败，系统发生问题！");
		}
	});
}
//跳转教材计划页面
function to_add_material(zhaosheng_id,ckpage){
	jiazaidonghua();
	$.post("/material/get_wangjiao_material_list.jr",{
		'ckpage':ckpage,
		'zhaosheng_id':zhaosheng_id,
		'pageNumber' : 1,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//编辑院历
function to_add_yuanli(zhaosheng_id){
	layer.open({
		  type: 2,
		  title: ['编辑文件信息'],
		  area: ['1000px', '700px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/ck_zhaosheng/to_add_yuanli.jr?zhaosheng_id="+zhaosheng_id
		  });
}
//添加院校常见问题
function to_add_help_info(zhaosheng_id){
	layer.open({
		  type: 2,
		  title: ['添加院校常见问题'],
		  area: ['1000px', '700px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/help_center/to_add_help_yuanxiao_info.jr?zhaosheng_id="+zhaosheng_id
		  });
}

function to_update_help_info(help_id){
	layer.open({
		  type: 2,
		  title: ['编辑院校常见问题'],
		  area: ['1000px', '700px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/help_center/to_update_help_yuanxiao_info.jr?help_id="+help_id
		  });
}

//删除招生信息
function to_delete_ck_xuexiao(zhaosheng_id){
	layer.confirm("提示：如果删除招生信息，相关信息会全部删除！",function(){
		layer.closeAll('dialog');
		$.post("/ck_zhaosheng/delete_zhaosheng.jr",{
			'zhaosheng_id':zhaosheng_id
		},function(data){
			if(data==1){
				tanchuang("删除成功！");
			}else{
				tanchuang("删除失败！");
			}
		});
		})
}
//去添加招生简章
function to_add_zs_jianzhang(zhaosheng_id){
	layer.open({
		  type: 2,
		  title: ['添加招生简章'],
		  area: ['1000px', '700px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/zs_jianzhang/to_add_zsjianzhang.jr?zhaosheng_id="+zhaosheng_id
		  });
}
//上传招生简章
function to_upload_zs_jianzhang(zhaosheng_id){
	layer.open({
		  type: 2,
		  title: ['上传招生简章'],
		  area: ['650px', '500px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/zs_jianzhang/to_upload_zs_jianzhang.jr?zhaosheng_id="+zhaosheng_id
		  });
}
//查看招生简章
function to_check_zs_jianzhang(zhaosheng_id){
	layer.open({
		  type: 2,
		  title: ['查看招生简章'],
		  area: ['1000px', '700px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/zs_jianzhang/to_check_zs_jianzhang.jr?zhaosheng_id="+zhaosheng_id
		  });
}
</script>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">成考招生</div>
		<div class="right" id="timer">
		</div>
	</div>
	<div class="right_content">
		<div class="operation">
			<div class="opera_left left">
				 <button type="button" onclick="to_add_wangjiao_xuexiao()" class="btn left btn-primary" id="add_role">
					<i class="fa fa-plus"></i>添加学校
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
						<th>学校</th>
						<th>招生状态</th>
						<th>学历类型</th>
						<th>提交时间</th>
						<th>文件（院历、手册、费用说明、模拟题）</th>
						<th>（数量）《教材计划、教学计划》</th>
						<th>招生简章</th>
						<th>院校常见问题</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty ckZhaoShengs.list }">
						<tbody>
							<c:forEach items="${ckZhaoShengs.list }" var="ckzs" varStatus="vs">
								<c:if test="${vs.count%2 == '0' }">
									<tr class="active">
								</c:if>
								<c:if test="${vs.count%2 != '0' }">
									<tr>
								</c:if>
								<td><label class="label label-success btn-primary"
									for="minimal-checkbox-1">${vs.index+1+ckZhaoShengs.begin }</label></td>
								<td>${ckzs.xuexiao_name }</td>
								<td>
								<c:if test="${ckzs.zhaosheng_state==0 }">
								正在招生
								</c:if>
								<c:if test="${ckzs.zhaosheng_state==1 }">
								停止招生
								</c:if>
								<c:if test="${ckzs.zhaosheng_state==2 }">
								即将招生
								</c:if>
								</td>
								<td>${ckzs.xuexiao_type}</td>
								<td><fmt:formatDate type="both" value="${ckzs.tijiao_time }" /></td>
								<td><button type="button" onclick="to_add_yuanli(${ckzs.zhaosheng_id})" class="btn btn-warning btn-xs">编辑</button></td>
								<td>(${ckzs.jiaocaijihuanum })<button type="button" onclick="to_add_material(${ckzs.zhaosheng_id},${ckZhaoShengs.pageNumber})" class="btn btn-warning btn-xs">进入</button></td>
								<td><button type="button" onclick="to_add_zs_jianzhang(${ckzs.zhaosheng_id})" class="btn btn-warning btn-xs">添加</button>
								<button type="button" onclick="to_upload_zs_jianzhang(${ckzs.zhaosheng_id})" class="btn btn-success btn-xs">文件</button>
								<button type="button" onclick="to_check_zs_jianzhang(${ckzs.zhaosheng_id})" class="btn btn-info btn-xs">查看</button>
								</td>
								<td>
								<c:if test="${empty ckzs.center }">
								<button type="button" onclick="to_add_help_info(${ckzs.zhaosheng_id})" class="btn btn-warning btn-xs">添加</button>
								</c:if>
								<c:if test="${not empty ckzs.center }">
								<button type="button" onclick="to_update_help_info(${ckzs.center.help_id})" class="btn btn-warning btn-xs">编辑</button>
								</c:if>
								</td>
								<td>
									<button type="button" onclick="to_update_wj_xuexiao(${ckzs.zhaosheng_id})" class="btn btn-success btn-xs">修改</button>
									<button type="button" onclick="to_delete_ck_xuexiao(${ckzs.zhaosheng_id})" class="btn btn-warning btn-xs">删除</button>
									<c:if test="${ckzs.is_show==0}">
									<button type="button" onclick="change_state(${ckzs.zhaosheng_id},'1',${ckZhaoShengs.pageNumber})" class="btn btn-danger btn-xs">禁用</button>
									</c:if>
									<c:if test="${ckzs.is_show==1}">
									<button type="button" onclick="change_state(${ckzs.zhaosheng_id},'0',${ckZhaoShengs.pageNumber})" class="btn btn-info btn-xs">显示</button>
									</c:if>
								</td>
								</tr>
							</c:forEach>
						</tbody>
					</c:if>
			</table>
			<c:if test="${ empty ckZhaoShengs.list }">
				<p class="zanwu">暂无网络教育招生信息</p>
				</c:if>
		</div>
		<div class="pages">
			<!-- 		<div class="whole left">
				<button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
				<button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>

			</div> -->
			<ul class="pagination right">
				<c:if test="${ckZhaoShengs.hasPreviousPage==true}">
					<li class="previous"
						onclick="wjxuexiao_jump_page(${ckZhaoShengs.pageNumber-1})"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${ckZhaoShengs.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${ckZhaoShengs.pageNumber==page}">
							<li class="active" onclick="wjxuexiao_jump_page(${page})"><a
								href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li onclick="wjxuexiao_jump_page(${page})"><a href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${ckZhaoShengs.hasNextPage==true}">
					<li class="next" onclick="wjxuexiao_jump_page(${ckZhaoShengs.pageNumber+1})"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>