<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/page/pages.css" />
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/application.js"></script>
<style>
.user_numbers {
	margin-left: 20px;
	line-height: 30px;
}

.user_numbers em {
	margin-left: 3px;
	font-weight: bold;
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
	border: 2px solid #28A4F4;
}

.shaixuan {
	width: 100px;
	height: 35px;
	line-height: 35px;
	background: #18d1bb;
	border: none;
	color: white;
	float: left;
	border-radius: 5px;
	margin-left: 40px;
}
</style>
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script>
//添加用户信息
function to_check_user(user_id){
	 layer.open({
		  type: 2,
		  title: ['网站用户详情'],
		  area: ['1000px', '600px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/back_user/get_user_detail.jr?user_id="+user_id
		  });
}
//添加我的课程
function add_mycourse(user_id){
	 layer.open({
		  type: 2,
		  title: ['添加用户课程'],
		  area: ['900px', '550px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/back_user/get_my_course.jr?user_id="+user_id
		  });
}
//查看学习记录
function to_check_study_record(user_id){
	layer.open({
		  type: 2,
		  title: ['查看用户学习记录'],
		  area: ['1000px', '700px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/back_video_record/get_user_study_record.jr?user_id="+user_id
		  });
}
//添加网站用户
function to_save_user(){
	 layer.open({
		  type: 2,
		  title: ['添加网校用户'],
		  area: ['800px', '450px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/back_user/to_save_user.jr"
		  });
}
//用户列表翻页-上一页 下一页
function user_jump_page(page,user_sex,user_mail,user_phone,user_nickname,user_state){
	jiazaidonghua();
	$.post("/back_user/get_user_main.jr",{
		'user_state':user_state,
		'user_sex' : user_sex,
		 'user_mail' : user_mail,
		 'user_phone' : user_phone,
		 'user_nickname' : user_nickname,
		'pageNumber' : page,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//筛选条件
function shaixuan_tioajian(obj,limit){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("筛选中");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/back_user/get_user_main.jr",
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
//信息弹窗
function tanchuang(content){
	layer.alert(content, {
		  icon: 1,
		  skin: 'layer-ext-moon' 
		})
}
//设置用户权限
function jinyong_user(user_id,type){
	$.post("/back_user/update_user_permision.jr", {
		'user_id':user_id,
		'type' : type
	}, function(data) {
		if (data== 1) {
			tanchuang("设置成功！");
			user_jump_page(1);
		}else{
			tanchuang("设置失败，系统发生问题！");
		}
	});
}
//绑定业务员
function to_bangding_employee(user_id){
	 layer.open({
		  type: 2,
		  title: ['绑定账号'],
		  area: ['500px', '300px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_user/to_bangding_zhanghao.jr?user_id="+user_id
		  });
}
</script>
<div class="back_right">
	<div class="back_r_top">
		<div class="left">用户管理</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal">
			<div class="form-group clearfix"
				style="padding-top: 10px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">昵称：</label>
				<div class="col-xs-2">
					<input class="form-control" id=user_nickname name="user_nickname">
				</div>
				<label class="col-xs-1 right_wz">电话：</label>
				<div class="col-xs-2">
					<input class="form-control" id=user_phone name="user_phone">
				</div>
				<label class="col-xs-1 right_wz">邮箱：</label>
				<div class="col-xs-2">
					<input class="form-control" id=user_mail name="user_mail">
				</div>
			</div>
			<div class="form-group clearfix"
				style="padding-top: 10px; margin-bottom: 5px;">
				<label class="col-xs-1 right_wz">性别：</label>
				<div class="col-xs-2">
					<select class="form-control" name="user_sex"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</div>
				<label class="col-xs-1 right_wz">账号状态：</label>
				<div class="col-xs-2">
					<select class="form-control" name="user_state"
						style="line-height: 35px; height: 35px; padding: 0px;">
						<option value="">请选择</option>
						<option value="正常">正常</option>
						<option value="限制">被限制</option>
					</select>
				</div>
				<button class="shaixuan" onclick="shaixuan_tioajian(this)">筛选</button>
			</div>
		</form>
		<div class="operation">
			<div class="opera_left left">
				<button type="button" class="btn left btn-primary" id="add_user"
					onclick="to_save_user()">
					<i class="fa fa-plus"></i>添加用户
				</button>
				<span class="user_numbers">总注册用户<em style="color: #415b76;">${all_user_number }</em>位
				</span> <span class="user_numbers">当前条件用户数量<em
					style="color: #e74c3c;">${user_number }</em>位
				</span>
			</div>
			<div class="opera_right right"></div>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<!-- <th></th> -->
						<th>编号</th>
						<th>昵称</th>
						<th>性别</th>
						<th>出生日期</th>
						<th>电话</th>
						<th>邮箱</th>
						<th>qq</th>
						<th>学习状态</th>
						<th>所属业务</th>
						<th>注册时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty users.list }">
						<tbody>
							<c:forEach items="${users.list }" var="user" varStatus="vs">
								<c:if test="${vs.count%2 == '0' }">
									<tr class="active">
								</c:if>
								<c:if test="${vs.count%2 != '0' }">
									<tr>
								</c:if>
								<td><label class="label label-success btn-primary"
									for="minimal-checkbox-1">${vs.index+1+users.begin }</label></td>
								<td>${user.user_nickname }</td>
								<td>${user.user_sex }</td>
								<td><fmt:formatDate value="${user.user_birthday }" /> <c:if
										test="${empty user.user_birthday }">
										<span style="color: orange;">暂无</span>
									</c:if></td>
								<td>${user.user_phone }<c:if
										test="${empty user.user_phone }">
										<span style="color: orange;">暂无</span>
									</c:if>
								</td>
								<td>${user.user_mail }<c:if test="${empty user.user_mail }">
										<span style="color: orange;">暂无</span>
									</c:if></td>
								<td>${user.user_qq }<c:if test="${empty user.user_qq }">
										<span style="color: orange;">暂无</span>
									</c:if>
								</td>
								<td><c:if test="${user.is_study==1 }">
									已学习
								</c:if> <c:if test="${user.is_study==0 }">
									未学习
								</c:if></td>
								<td>
								<c:if test="${empty user.employee_name }">
								--
								</c:if>
								${user.employee_name }
								</td>
								<td><fmt:formatDate type="both" value="${user.user_time }" /></td>
								<td>
									<button type="button" onclick="to_check_user(${user.user_id})"
										class="btn btn-success btn-xs">查看</button>
									<button type="button"
										onclick="to_bangding_employee(${user.user_id})"
										class="btn btn-info btn-xs">绑定业务</button>
									<button type="button"
										onclick="to_check_study_record(${user.user_id})"
										class="btn btn-primary btn-xs">学习记录</button>
									<button type="button" onclick="add_mycourse(${user.user_id})"
										class="btn btn-warning btn-xs">添加课程</button> <c:if
										test="${user.user_state=='正常'}">
										<button type="button"
											onclick="jinyong_user(${user.user_id},'限制')"
											class="btn btn-danger btn-xs">禁用</button>
									</c:if> <c:if test="${user.user_state=='限制'}">
										<button type="button"
											onclick="jinyong_user(${user.user_id},'正常')"
											class="btn btn-info btn-xs">解除</button>
									</c:if>
								</td>
								</tr>
							</c:forEach>
						</tbody>
					</c:if>
			</table>
		</div>
		<div class="pages">
			<!-- 		<div class="whole left">
				<button type="button" class="btn btn-success btn-sm selectall">全部选中</button>
				<button type="button" class="btn btn-danger btn-sm selectno">取消全选</button>

			</div> -->
			<div class="whole left">
				<div class="go_page">
					<span>跳转到第</span><input type="text" id="page_num" /> <span>页</span>
					<a href="javascript:void(0)"
						onclick="user_tiaozhuan_page(${users.pages},'${user_sex }','${user_mail }','${user_phone }','${user_nickname }','${user_state }')"
						class="btn_go">GO</a>
				</div>
			</div>
			<script>
				function user_tiaozhuan_page(page,user_sex,user_mail,user_phone,user_nickname,user_state){
					var page_num=$("#page_num").val();
					if(!isNaN(page_num)){
						if(page<page_num||page_num<0){
							layer.msg("你输入的页数不存在！")
						}else{
							jiazaidonghua();
							 $.post("/back_user/get_user_main.jr", {
								 'user_state':user_state,
								 'user_sex' : user_sex,
								 'user_mail' : user_mail,
								 'user_phone' : user_phone,
								 'user_nickname' : user_nickname,
									'pageNumber' : page_num,
									'limit' : 20
								}, function(data) {
									$("#conten_list").html(data);
								}) 
						}
					}else{
						layer.msg("请输入数字！")
					}
				}
				</script>
			<ul class="pagination right">
				<c:if test="${users.hasPreviousPage==true}">
					<li class="previous"
						onclick="user_jump_page(${users.pageNumber-1},'${user_sex }','${user_mail }','${user_phone }','${user_nickname }','${user_state }')"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${users.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${users.pageNumber==page}">
							<li class="active"
								onclick="user_jump_page(${page},'${user_sex }','${user_mail }','${user_phone }','${user_nickname }','${user_state }')"><a
								href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li
								onclick="user_jump_page(${page},'${user_sex }','${user_mail }','${user_phone }','${user_nickname }','${user_state }')"><a
								href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${users.hasNextPage==true}">
					<li class="next"
						onclick="user_jump_page(${users.pageNumber+1},'${user_sex }','${user_mail }','${user_phone }','${user_nickname }','${user_state }')"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>