<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/page/pages.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/common/time.js"></script>
<style>
.select2-chosen {
	color: white !important;
}

.form-group .right_wz {
	text-align: right;
	line-height: 35px;
}

.form-control {
	height: 35px;
}

.form-group {
	margin-bottom: 10xp !important;
}

.form-control:focus {
	border: 2px solid #06c1ae;
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
}) 

//查看资讯详情
function to_check_baoming(baoming_id){
	 layer.open({
		  type: 2,
		  title: ['查看学历报名'],
		  area: ['1100px', '500px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/xueli_baoming/to_check_baoming.jr?baoming_id="+baoming_id
		  });
}
//学历报名列表翻页-上一页 下一页
function baoming_jump_page(page,yuanxiao,cnegci,type,user_name,user_phone){
	jiazaidonghua();
	$.post("/xueli_baoming/get_baoming_list.jr",{
		'yuanxiao' : yuanxiao,
		'cnegci' : cnegci,
		'type' : type,
		'user_name' : user_name,
		'user_phone' : user_phone,
		'pageNumber' : page,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//删除学历报名信息
function delete_baoming(){
	var len = $("input:checkbox[name=baoming_check]:checked").length;
	if(len>0){
		var str = "";
		$('input:checkbox[name=baoming_check]:checked').each(function(i) {
			if (0 == i) {
				str = $(this).val();
			} else {
				str += ","+$(this).val();
			}
		});
		layer.confirm("提示：您好，确定要删除吗？",function(){
			layer.closeAll('dialog');
			$.post("/xueli_baoming/delete_baoming.jr",{
				'str':str
			},function(data){
				if(data==1){
					tanchuang("删除成功！");
				}else{
					tanchuang("删除失败！");
				}
			});
			})
	}else{
		tanchuang("请选择至少一条记录删除！");
	}
}
//信息弹窗
function tanchuang(content){
	layer.alert(content, {
		  icon: 1,
		  skin: 'layer-ext-moon' 
		})
}
//跳转页面
function xueli_tiaozhuan_page(page,limit,yuanxiao,cnegci,type,user_name,user_phone){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			 $.post("/xueli_baoming/get_baoming_list.jr", {
				 'yuanxiao' : yuanxiao,
					'cnegci' : cnegci,
					'type' : type,
					'user_name' : user_name,
					'user_phone' : user_phone,
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
//查询学历报名
function chaxun_xueli_baoming(obj){
	$(obj).attr({"disabled":"disabled"});
	$(obj).html("筛选中");
	$("#myform").ajaxSubmit({
		type : 'POST',
		url : "/xueli_baoming/get_baoming_list.jr",
		data:{
			'pageNumber':1,
			'limit':20
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
<style>
.operation #add_baoming {
	margin-right: 20px;
}
</style>
<div class="back_right" style="min-width: 900px;">
	<div class="back_r_top">
		<div class="left">学历报名</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group clearfix"
				style="margin-top: 0px; padding-top: 10px;">
				<label class="col-xs-1 right_wz">学校：</label>
				<div class="col-xs-2">
					<input class="form-control" type="text" name="yuanxiao" id="yuanxiao"
						placeholder="学校名称">
				</div>
				<label class="col-xs-1 right_wz">层次：</label>
				<div class="col-xs-2">
					<select id="cnegci" name="cnegci"
						style="line-height: 35px; height: 35px; width: 100%;">
						<option value="">请选择</option>
						<option value="高起专">高起专</option>
						<option value="高起本">高起本</option>
						<option value="专升本">专升本</option>
					</select>
				</div>
				<label class="col-xs-1 right_wz">类型：</label>
				<div class="col-xs-2">
					<select  id="type" name="type"
						style="line-height: 35px; height: 35px; width: 100%;">
						<option value="">请选择</option>
						<option value="网教">网教</option>
						<option value="成考">成考</option>
					</select>
				</div>
			</div>
			<div class="form-group clearfix" style="margin-top: 0px;">
				<label class="col-xs-1 right_wz">姓名：</label>
				<div class="col-xs-2">
					<input class="form-control" type="text" name="user_name" id="user_name"
						placeholder="姓名">
				</div>
				<label class="col-xs-1 right_wz">电话：</label>
				<div class="col-xs-2">
					<input class="form-control" type="text" name="user_phone" id="user_phone"
						placeholder="电话">
				</div>
				<div class="col-xs-2">
					<button type="button" onclick="chaxun_xueli_baoming(this)"
						class="btn btn-primary btn-lg"
						style="width: 100px; height: 35px; background-color: #06c1ae; line-height: 15px; margin-left: 70px;">查询</button>
				</div>
			</div>
		</form>
		<div class="operation">
			<div class="opera_left left">
				<button type="button" onclick="delete_baoming()"
					class="btn left btn-danger" id="delete_baoming">
					<i class="fa fa-minus"></i>删除
				</button>
			</div>
			<div class="opera_right right"></div>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th></th>
						<th>编号</th>
						<th>学校</th>
						<th>层次</th>
						<th>专业</th>
						<th>姓名</th>
						<th>身份证号</th>
						<th>电话</th>
						<th>类型</th>
						<th>添加时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:if test="${not empty xueLiBaoMings.list }">
					<tbody>
						<c:forEach items="${xueLiBaoMings.list }" var="baoming"
							varStatus="vs">
							<c:if test="${vs.count%2 == '0' }">
								<tr class="active">
							</c:if>
							<c:if test="${vs.count%2 != '0' }">
								<tr>
							</c:if>
							<td><label class="checkbox checkbox-inline"
								for="checkbox${baoming.baoming_id }"> <input
									type="checkbox" name="baoming_check" data-toggle="checkbox"
									value="${baoming.baoming_id  }"
									id="checkbox${baoming.baoming_id  }" class="checkbox" required>
							</label></td>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+xueLiBaoMings.begin }</label>
							</td>
							<td>${baoming.yuanxiao }</td>
							<td>${baoming.cnegci }</td>
							<td>${baoming.zhuanye }</td>
							<td>${baoming.user_name }</td>
							<td>${baoming.card_number }</td>
							<td>${baoming.user_phone }</td>
							<td>${baoming.type }</td>
							<td><fmt:formatDate type="both"
									value="${baoming.baoming_time }" /></td>
							<td>
								<button type="button"
									onclick="to_check_baoming(${baoming.baoming_id })"
									class="btn btn-success btn-xs">查看</button>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ empty xueLiBaoMings.list }">
				<p class="zanwu">暂无学历报名内容</p>
			</c:if>
		</div>
		<div class="pages">
			<div class="whole left">
				<button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
				<button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>
			</div>
			<div class="whole left">
				<div class="go_page">
					<span>跳转到第</span><input type="text" id="page_num" /> <span>页</span>
					<a href="javascript:void(0)"
						onclick="xueli_tiaozhuan_page(${xueLiBaoMings.pages},20,'${yuanxiao }','${cnegci }','${type }','${user_name }','${user_phone }')"
						class="btn_go" style="background-color: #06c1ae;">GO</a>
				</div>
			</div>
			<ul class="pagination right">
				<c:if test="${xueLiBaoMings.hasPreviousPage==true}">
					<li class="previous"
						onclick="baoming_jump_page(${xueLiBaoMings.pageNumber-1},'${yuanxiao }','${cnegci }','${type }','${user_name }','${user_phone }')"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${xueLiBaoMings.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${xueLiBaoMings.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li
								onclick="baoming_jump_page(${page},'${yuanxiao }','${cnegci }','${type }','${user_name }','${user_phone }')"><a
								href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${xueLiBaoMings.hasNextPage==true}">
					<li class="next"
						onclick="baoming_jump_page(${xueLiBaoMings.pageNumber+1},'${yuanxiao }','${cnegci }','${type }','${user_name }','${user_phone }')"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>