<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/conult/jiazai.css" />
<style>
.pages .go_page span {
	float: left;
	font-size: 14px;
	color: #4444;
	height: 36px;
	line-height: 36px;
	display: inline-block;
	margin-left: 15px;
	margin-right: 15px;
}

.pages .go_page input {
	height: 34px;
	border: #d2d2d2 solid 1px;
	line-height: 34px;
	width: 80px;
	float: left;
}

.pages .go_page a {
	width: 36px;
	height: 36px;
	border-radius: 50%;
	display: block;
	background-color: #e74c3c;
	color: #fff;
	font-size: 14px;
	line-height: 36px;
	text-align: center;
	float: left;
}

.pages .go_page a:hover {
	background-color: #FF6666;
}
</style>
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script src="/js/conult/conult_record.js" type="text/javascript"></script>
<script>
	$(function() {
		$(".selectall").click(function() {
			$(".checkbox").each(function() {
				$(this).prop("checked", true);
			})
		})
		$(".selectno").click(function() {
			$(".checkbox").each(function() {
				$(this).prop("checked", false);
			})
		})
	})

	//查看报名信息
	function check_net_conult(consult_id) {
		layer.open({
			type : 2,
			title : [ '查看客户信息详情' ],
			area : [ '950px', '700px' ],
			shadeClose : true, //点击遮罩关闭
			content : "/crm_conult/to_check_net_conult.jr?consult_id="
					+ consult_id
		});
	}
	//去添加回访记录
	function to_add_track(consult_id){
		layer.open({
			type : 2,
			title : [ '查看客户回访记录' ],
			area : [ '950px', '700px' ],
			shadeClose : true, //点击遮罩关闭
			content : "/crm_conult/to_add_track.jr?consult_id="
					+ consult_id
		});
	}
	//评论列表翻页-上一页 下一页
	function conult_jump_page(page, limit, user_sex, user_name, user_phone,
			consult_type, search_word, hope_sc, zhuanye, city, course_id,
			course_parent_id, review_id,
			zixun_start_time, zixun_end_time) {
		jiazaidonghua();
		$.post("/crm_conult/get_crm_net_conult_main.jr", {
			'user_sex' : user_sex,
			'user_name' : user_name,
			'user_phone' : user_phone,
			'consult_type' : consult_type,
			'search_word' : search_word,
			'hope_sc' : hope_sc,
			'zhuanye' : zhuanye,
			'city' : city,
			'course_id' : course_id,
			'course_parent_id' : course_parent_id,
			'review_id' : review_id,
			'zixun_start_time' : zixun_start_time,
			'zixun_end_time' : zixun_end_time,
			'pageNumber' : page,
			'limit' : limit
		}, function(data) {
			$("#conten_list").html(data);
		})
	}
	//页面跳转
	function conult_tiaozhuan_page(page, limit, user_sex, user_name,
			user_phone, consult_type, search_word, hope_sc, zhuanye, city,
			course_id, course_parent_id, review_id,
			zixun_start_time, zixun_end_time) {
		jiazaidonghua();
		$.post("/crm_conult/get_crm_net_conult_main.jr", {
			'user_sex' : user_sex,
			'user_name' : user_name,
			'user_phone' : user_phone,
			'consult_type' : consult_type,
			'search_word' : search_word,
			'hope_sc' : hope_sc,
			'zhuanye' : zhuanye,
			'city' : city,
			'course_id' : course_id,
			'course_parent_id' : course_parent_id,
			'review_id' : review_id,
			'zixun_start_time' : zixun_start_time,
			'zixun_end_time' : zixun_end_time,
			'pageNumber' : page,
			'limit' : limit
		}, function(data) {
			$("#conten_list").html(data);
		})
	}

	//获取二级分类
	function get_sub_course() {
		var course_parent_id = $("#course_parent_id").val();
		$.post("/edu_entry_plan/get_sub_course.jr", {
			'course_id' : course_parent_id
		}, function(data) {
			$("#course_id").html(data);
		})

	}
	//获取职称
	function get_review_list() {
		var course_id = $("#course_id").val();
		var course_parent_id = $("#course_parent_id").val();
		if (course_parent_id == 9) {//显示职称评审3级
			$.post("/edu_entry/get_sub_review.jr", {
				'course_id' : course_id
			}, function(data) {
				$("#review_id").html(data);
			})
		} else {
			$("#review_id").html("<option value=''>请选择</option>");
		}
	}

	//筛选条件
	function shaixuan_tioajian(obj) {
		$(obj).attr({
			"disabled" : "disabled"
		});
		$(obj).html("筛选中");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/crm_conult/get_crm_net_conult_main.jr",
			data : {
				'pageNumber' : 1,
				'limit' : 20
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
.operation #add_course_info {
	margin-right: 20px;
}
</style>
<div class="back_right" style="min-width: 900px;">
	<div class="back_r_top">
		<div class="left">客户信息</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<form enctype="multipart/form-data" id="myform"
			class="form-horizontal" style='padding: 20px;'>
			<div class="form-group">
				<label class="control-label col-xs-1">一级类别：</label>
				<div class=" col-xs-2">
					<select class="form-control" name="course_parent_id"
						id="course_parent_id" onchange="get_sub_course()">
						<option value="">请选择</option>
						<c:forEach items="${courseMenus }" var="course">
							<option value="${course.course_id }">${course.course_name }</option>
						</c:forEach>
					</select>
				</div>
				<label class="control-label col-xs-1">二级类别：</label>
				<div class=" col-xs-2">
					<select name="course_id" id="course_id"
						onchange="get_review_list()" class="form-control">
						<option value="">请选择</option>
					</select>
				</div>
				<label class="control-label col-xs-1">三级类别：</label>
				<div class="col-xs-2">
					<select name="review_id" id="review_id" class="form-control">
						<option value="">请选择</option>
					</select>
				</div>
				<label class="control-label col-xs-1">咨询方式：</label>
				<div class='col-xs-2'>
					<select name="consult_type" class="form-control">
						<option value="">请选择</option>
						<option value="53客服">53客服</option>
						<option value="百度商桥">百度商桥</option>
						<option value="离线宝">离线宝</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-1">姓名：</label>
				<div class="col-xs-2">
					<input type="text" name="user_name" class="form-control"
						placeholder="请输入姓名">
				</div>
				<label class="control-label col-xs-1">手机号：</label>
				<div class="col-xs-2">
					<input type="text" name="user_phone" class="form-control"
						placeholder="请输入手机号">
				</div>
				<label class="control-label col-xs-1">性别：</label>
				<div class="col-xs-2">
					<select class="form-control col-xs-2">
						<option value="">请选择</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</div>
				<label
					class="control-label col-xs-1">关键词：</label>
				<div class="col-xs-2">
					<input type="text" name="search_word" class="form-control" placeholder="请输入关键词">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-1">意向学校：</label>
				<div class="col-xs-2">
					<input type="text" name="hope_sc" class="form-control" placeholder="请输入意向学校">
				</div>
				<label class="control-label col-xs-1">专业：</label>
				<div class="col-xs-2">
					<input type="text" name="zhuanye" class="form-control" placeholder="请输入专业">
				</div>
				<label class="control-label col-xs-1">所在城市：</label>
				<div class="col-xs-2">
					<input type="text" name="city" class="form-control" placeholder="请输入所在城市">
				</div>
			</div>
			<div class="form-group">
			<label class="control-label col-xs-1">咨询日期开始：</label>
				<div class="col-xs-2">
					<input class="form-control" name="zixun_start_time" placeholder="开始日" id="LAY_demorange_s">
				</div>
				<label class="control-label col-xs-1">咨询日期截止：</label>
				<div class="col-xs-2">
					<input class="form-control" name="zixun_end_time" placeholder="截止日" id="LAY_demorange_e">
				</div>
				<div class="col-xs-2">
						<button type="button" class="btn"
							onclick="shaixuan_tioajian(this)"
							style="background-color: #53C1FE; color: white;">筛选</button>
				</div>
			</div>
			<script type="text/javascript">
			layui.use('laydate', function(){
			  var laydate = layui.laydate;
			  
			  var start = {
			    max: '2099-06-16 23:59:59'
			    ,istoday: false
			    ,choose: function(datas){
			      end.min = datas; //开始日选好后，重置结束日的最小日期
			      end.start = datas //将结束日的初始值设定为开始日
			    }
			  };
			  
			  var end = {
			    max: '2099-06-16 23:59:59'
			    ,istoday: false
			    ,choose: function(datas){
			      start.max = datas; //结束日选好后，重置开始日的最大日期
			    }
			  };
			  
			  document.getElementById('LAY_demorange_s').onclick = function(){
			    start.elem = this;//设置目标元素
			    laydate(start);
			  }
			  document.getElementById('LAY_demorange_e').onclick = function(){
			    end.elem = this;//设置目标元素
			    laydate(end);
			  }
			  
			});
			</script>
		</form>
		<div class="operation">
			<div class="opera_left left">
			</div>
			<div class="opera_right right"></div>
		</div>
		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>所属分类</th>
						<th>报考类别/学校</th>
						<th>报考等级/专业</th>
						<th>咨询渠道</th>
						<th>姓名</th>
						<th>手机号</th>
						<th>用户性别</th>
						<th>咨询顾问</th>
						<th>回访状态</th>
						<th>咨询时间</th>
						<th>录入时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:if test="${not empty netConults.list }">
					<tbody>
						<c:forEach items="${netConults.list }" var="conult" varStatus="vs">
							<c:if test="${vs.count%2 == '0' }">
								<tr class="active">
							</c:if>
							<c:if test="${vs.count%2 != '0' }">
								<tr>
							</c:if>
							<td><label class="label label-success btn-primary"
								for="minimal-checkbox-1">${vs.index+1+netConults.begin }</label>
							</td>
							<td><c:if test="${empty conult.course_name }">
							--
							</c:if>${conult.course_name }</td>
							<td><c:if test="${empty conult.course_parent_name && empty conult.hope_sc }">
							--
							</c:if>${conult.course_parent_name }<c:if test="${not empty conult.hope_sc }">-${conult.hope_sc }</c:if></td>
							<td><c:if test="${empty conult.dictionary_name && empty conult.zhuanye}">
							--
							</c:if>${conult.dictionary_name }${conult.zhuanye }</td>
							<td><c:if test="${empty conult.consult_type }">
							--
							</c:if>${ conult.consult_type}</td>
							<td><c:if test="${empty conult.user_name }">
							--
							</c:if>${conult.user_name }</td>
							<td>
							<c:if test="${empty conult.user_phone }">
							--
							</c:if>
									<c:if test="${not empty conult.user_phone && conult.conult_state!=2}">
									<button id="waihu" style="border:none;" onclick="panduan_now_zuoxi_status(this,${conult.consult_id },'${conult.user_phone }','${sessionScope.employee_session.zuoxi }','${is_ip }')"><i class="fa fa-phone-square"
										style="font-size: 20px; color: #2ECC71;"></i></button>${conult.user_phone }
										</c:if>
										</td>
							<td>${conult.user_sex }</td>
							<td>${conult.employee_name }</td>
							<td><c:if test="${conult.conult_state==0 }">
              	未回访
              </c:if> <c:if test="${conult.conult_state==1 }">
              	正在联系
              </c:if> <c:if test="${conult.conult_state==2 }">
              	已拒绝
              </c:if> <c:if test="${conult.conult_state==3 }">
              	成交
              </c:if></td>
							<td><fmt:formatDate value="${conult.zixun_time }" /></td>
							<td><fmt:formatDate type="both"
									value="${conult.consult_time }" /></td>
							<td>
								<button type="button"
									onclick="check_net_conult(${conult.consult_id  })"
									class="btn btn-success btn-xs">查看</button>
								<c:if test="${conult.conult_state!=2 }">
								<button type="button"
									onclick="to_add_track(${conult.consult_id  })"
									class="btn btn-info btn-xs">回访记录</button>
								</c:if>
							</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>
			</table>
			<c:if test="${ empty netConults.list }">
				<p class="zanwu">还没有录入过客户信息，赶紧去录吧！</p>
			</c:if>
		</div>
		<div class="pages">
			<div class="whole left">
				<div class="go_page">
					<span>跳转</span><input type="text" id="page_num" /> <span>页</span>
					<a href="javascript:void(0)"
						onclick="conult_tiaozhuan_page(${netConults.pages},${limit},'${user_sex }','${user_name }','${user_phone }','${consult_type }','${search_word }','${hope_sc }','${zhuanye }','${city }','${course_id }','${course_parent_id }','${review_id }','${zixun_start_time }','${zixun_end_time }')"
						class="btn_go">GO</a>
				</div>
			</div>
			<ul class="pagination right">
				<li><a href="javascript:void(0)"
					onclick="conult_jump_page(1,${limit },'${user_sex }','${user_name }','${user_phone }','${consult_type }','${search_word }','${hope_sc }','${zhuanye }','${city }','${course_id }','${course_parent_id }','${review_id }','${zixun_start_time }','${zixun_end_time }')"
					class="fa fa-fast-backward"></a></li>
				<c:if test="${netConults.hasPreviousPage==true}">
					<li class="previous"
						onclick="conult_jump_page(${netConults.pageNumber-1},${limit },'${user_sex }','${user_name }','${user_phone }','${consult_type }','${search_word }','${hope_sc }','${zhuanye }','${city }','${course_id }','${course_parent_id }','${review_id }','${zixun_start_time }','${zixun_end_time }')"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${netConults.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${netConults.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li
								onclick="conult_jump_page(${page},${limit },'${user_sex }','${user_name }','${user_phone }','${consult_type }','${search_word }','${hope_sc }','${zhuanye }','${city }','${course_id }','${course_parent_id }','${review_id }','${zixun_start_time }','${zixun_end_time }')"><a
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
							onclick="conult_jump_page(${netConults.pageNumber},20,'${user_sex }','${user_name }','${user_phone }','${consult_type }','${search_word }','${hope_sc }','${zhuanye }','${city }','${course_id }','${course_parent_id }','${review_id }','${zixun_start_time }','${zixun_end_time }')">20</a></li>
						<li><a href="#fakelink"
							onclick="conult_jump_page(${netConults.pageNumber},50,'${user_sex }','${user_name }','${user_phone }','${consult_type }','${search_word }','${hope_sc }','${zhuanye }','${city }','${course_id }','${course_parent_id }','${review_id }','${zixun_start_time }','${zixun_end_time }')">50</a></li>
						<li><a href="#fakelink"
							onclick="conult_jump_page(${netConults.pageNumber},100,'${user_sex }','${user_name }','${user_phone }','${consult_type }','${search_word }','${hope_sc }','${zhuanye }','${city }','${course_id }','${course_parent_id }','${review_id }','${zixun_start_time }','${zixun_end_time }')">100</a></li>
					</ul></li>
				<c:if test="${netConults.hasNextPage==true}">
					<li class="next"
						onclick="conult_jump_page(${netConults.pageNumber+1},${limit },'${user_sex }','${user_name }','${user_phone }','${consult_type }','${search_word }','${hope_sc }','${zhuanye }','${city }','${course_id }','${course_parent_id }','${review_id }','${zixun_start_time }','${zixun_end_time }')"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
				<li
					onclick="conult_jump_page(${netConults.pages},${limit },'${user_sex }','${user_name }','${user_phone }','${consult_type }','${search_word }','${hope_sc }','${zhuanye }','${city }','${course_id }','${course_parent_id }','${review_id }','${zixun_start_time }','${zixun_end_time }')"><a
					href="javascript:void(0)" class="fa fa-fast-forward"></a></li>
			</ul>
		</div>
	</div>
</div>
<div id="dengdai">
	<!-- 加载动画 -->
</div>