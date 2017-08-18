<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/flat-ui.min.css" />
<link rel="stylesheet" href="/css/school/back/common/right_content.css" />
<link rel="stylesheet" type="text/css"
	href="/css/jiaowu/index/jiaowu.css">
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<script src="/js/school/back/common/flat-ui.min.js"></script>
<script src="/js/school/back/common/application.js"></script>
<script src="/js/common/time.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script>
//跳转分页
function zhenti_jump_page(question_course_id,page){
	jiazaidonghua();
	$.post("/back_zhenti/get_zhenti_years.jr",{
		'question_course_id':question_course_id,
		'pageNumber' : page,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
//添加真题弹窗
function to_add_zhenti_year(question_course_id){
	layer.open({
		type : 2,
		title : [ '添加真题试卷' ],
		area : [ '700px', '550px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/back_zhenti/to_add_zhenti_year.jr?question_course_id="+question_course_id,
		end : function() {
			zhenti_jump_page(question_course_id,1);
		}
	});
}
//添加真题题型介绍
function to_add_zhenti_type(zhenti_id){
	layer.open({
		type : 2,
		title : [ '添加真题题型' ],
		area : [ '700px', '400px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/back_zhenti/to_add_zhenti_type.jr?zhenti_id="+zhenti_id
	});
}
//添加单选题
function to_save_danxuanti_option(zhenti_id,question_type){
	layer.open({
		type : 2,
		title : [ '添加真题单选题' ],
		area : [ '900px', '700px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/back_zhenti/to_save_danxuanti_option.jr?zhenti_id="+zhenti_id+"&question_type="+question_type
	});
}
//添加多选题
function to_save_duoxuanti_option(zhenti_id){
	layer.open({
		type : 2,
		title : [ '添加真题多选题' ],
		area : [ '900px', '700px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/back_zhenti/to_save_duoxuanti_option.jr?zhenti_id="+zhenti_id
	});
}
//添加案例题
function to_save_anliti_option(zhenti_id,question_type){
	layer.open({
		type : 2,
		title : [ '添加真题案例题' ],
		area : [ '900px', '700px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/back_zhenti/to_save_anli_option.jr?zhenti_id="+zhenti_id+"&question_type="+question_type
	});
}
//设置隐藏显示
function zhenti_show(type,zhenti_id,question_course_id){
	$.post("/back_zhenti/update_zhenti_year_show.jr",{
		'zhenti_id':zhenti_id,
		'is_show':type
	},function(data){
		if(data==1){
			//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
            tanchuang('恭喜您，设置成功');
            zhenti_jump_page(question_course_id,1);
		}else{
			tanchuang('很遗憾，设置失败');
		}
	})
}
//显示题型
function show_button(obj){
	$(obj).children("ul").toggle();
}
//查看历年真题
function to_check_zhenti_question(zhenti_id,zhentitype){
	$.post("/back_zhenti/check_zhenti_years.jr",{
		'zhenti_id':zhenti_id,
		'zhentitype':zhentitype
	},function(data){
		$("#conten_list").html(data);
	})
}
//删除真题
function delete_zhenti(zhenti_id){
	layer.confirm("提示：您好，确定要删除吗？",function(){
		layer.closeAll('dialog');
		$.post("/back_zhenti/delete_zhenti.jr",{
			'zhenti_id':zhenti_id
		},function(data){
			if(data==1){
				tanchuang("删除成功！请刷新页面！");
			}else{
				tanchuang("删除失败！");
			}
		});
		})
}
</script>
<div class="back_right" style="min-width: 900px;">
	<div class="back_r_top">
		<div class="left">历年真题</div>
		<div class="right" id="timer"></div>
	</div>
	<div class="right_content">
		<div class="operation">
			<div class="opera_left left">
				<button type="button"
					onclick="to_add_zhenti_year(${question_course_id})"
					class="btn left" style="background-color: #53C1FE; color: white;">
					<i class="fa fa-plus"></i>添加真题
				</button>
				<button type="button"
					onclick="zhenti_jump_page(${question_course_id},1)"
					class="btn left" style="background-color: #53C1FE; color: white;">刷新
				</button>
			</div>
			<div class="opera_right right"></div>
		</div>

		<div class="content_message">
			<table class="table table-hover">
				<thead>
					<tr class="tr_bgcolor warning">
						<th>编号</th>
						<th>试卷名称</th>
						<th>试卷类型</th>
						<th>考试时间</th>
						<th>最新价格</th>
						<th>添加时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty zhentiYears.list }">
						<tbody>
							<c:forEach items="${zhentiYears.list}" var="zhenti"
								varStatus="vs">
								<c:if test="${vs.count%2 == '0' }">
									<tr class="active">
								</c:if>
								<c:if test="${vs.count%2 != '0' }">
									<tr>
								</c:if>
								<td><label class="label label-success btn-primary"
									for="minimal-checkbox-1">${vs.index+1+zhentiYears.begin }</label></td>
								<c:if test="${zhenti.zhentitype=='历年真题' }">
									<td>${zhenti.zhenti_year}年<c:if
											test="${not empty zhenti.zhenti_yue }">
	（${zhenti.zhenti_yue }月）
	</c:if> ${zhenti.courseMenu.course_name }《${zhenti.questionCourse.question_course_name }》真题及答案解析
									</td>
								</c:if>
								<c:if test="${zhenti.zhentitype=='模拟考试' }">
									<td>${zhenti.zhenti_year}年<c:if
											test="${not empty zhenti.zhenti_yue }">
	（${zhenti.zhenti_yue }月）
	</c:if> ${zhenti.courseMenu.course_name }《${zhenti.questionCourse.question_course_name }》${zhenti.zhenti_name }
									</td>
								</c:if>
								<td>${zhenti.zhentitype }</td>
								<td>${zhenti.zhenti_kaoshi_time }分钟</td>
								<td>${zhenti.zhenti_new_price }</td>
								<td><fmt:formatDate type="both"
										value="${zhenti.zhenti_time }" /></td>
								<td><button type="button"
										onclick="to_add_zhenti_type(${zhenti.zhenti_id})"
										class="btn btn-success btn-xs">添加题型</button> <a type="button"
									class="btn btn-info btn-xs" style="position: relative;"
									onclick="show_button(this)" id="tanbtn">选择题型
										<ul
											style="position: absolute; top: 25px; left: 3px; display: none; z-index: 999">
											<c:forEach items="${zhenti.zhentiTypeIntroduce }" var="type">
												<li style="margin-top: 2px;"><c:if
														test="${type.question_type=='单选题' }">
														<button type="button"
															onclick="to_save_danxuanti_option(${zhenti.zhenti_id},'${type.question_type }')"
															class="btn btn-default btn-xs">添加${type.question_type }</button>
													</c:if> <c:if test="${type.question_type=='多选题' }">
														<button type="button"
															onclick="to_save_duoxuanti_option(${zhenti.zhenti_id})"
															class="btn btn-default btn-xs">添加${type.question_type }</button>
													</c:if> <c:if test="${type.question_type=='案例题' }">
														<button type="button"
															onclick="to_save_anliti_option(${zhenti.zhenti_id},'${type.question_type }')"
															class="btn btn-default btn-xs">添加${type.question_type }</button>
													</c:if> 
													<c:if test="${type.question_type=='简答题' }">
														<button type="button"
															onclick="to_save_anliti_option(${zhenti.zhenti_id},'${type.question_type }')"
															class="btn btn-default btn-xs">添加${type.question_type }</button>
													</c:if> 
													<c:if test="${type.question_type=='技能选择题' }">
														<button type="button"
															onclick="to_save_anliti_option(${zhenti.zhenti_id},'${type.question_type }')"
															class="btn btn-default btn-xs">添加${type.question_type }</button>
													</c:if> 
													<c:if test="${type.question_type=='案例简答题' }">
														<button type="button"
															onclick="to_save_anliti_option(${zhenti.zhenti_id},'${type.question_type }')"
															class="btn btn-default btn-xs">添加${type.question_type }</button>
													</c:if>
													<c:if test="${type.question_type=='职业道德' }">
														<button type="button"
															onclick="to_save_danxuanti_option(${zhenti.zhenti_id},'${type.question_type }')"
															class="btn btn-default btn-xs">添加${type.question_type }</button>
													</c:if></li>
											</c:forEach>
										</ul>
								</a>
									<button type="button"
										onclick="to_check_zhenti_question(${zhenti.zhenti_id},'${zhenti.zhentitype}')"
										class="btn btn-warning btn-xs">查看题目</button> <c:if
										test="${zhenti.is_show=='隐藏'  }">
										<button type="button"
											onclick="zhenti_show('显示',${zhenti.zhenti_id},${question_course_id})"
											class="btn btn-info btn-xs">显示</button>
									</c:if> <c:if test="${zhenti.is_show=='显示'  }">
										<button type="button"
											onclick="zhenti_show('隐藏',${zhenti.zhenti_id},${question_course_id})"
											class="btn btn-default btn-xs">隐藏</button>
									</c:if>
									<button type="button"
										onclick="delete_zhenti(${zhenti.zhenti_id})"
										class="btn btn-success btn-xs">删除</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</c:if>
			</table>
			<c:if test="${ empty zhentiYears.list }">
				<p class="zanwu1">暂无历年真题试卷，请添加！</p>
			</c:if>
		</div>
		<div class="pages">
			<ul class="pagination right">
				<c:if test="${zhentiYears.hasPreviousPage==true}">
					<li class="previous"
						onclick="zhenti_jump_page(${question_course_id},${zhentiYears.pageNumber-1})"><a
						href="#fakelink" class="fa fa-angle-left"></a></li>
				</c:if>
				<c:forEach items="${zhentiYears.navigatePageNumbers }" var="page">
					<c:choose>
						<c:when test="${zhentiYears.pageNumber==page}">
							<li class="active"><a href="#fakelink">${page}</a></li>
						</c:when>
						<c:otherwise>
							<li onclick="zhenti_jump_page(${question_course_id},${page})"><a
								href="#fakelink">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${zhentiYears.hasNextPage==true}">
					<li class="next"
						onclick="zhenti_jump_page(${question_course_id},${zhentiYears.pageNumber+1})"><a
						href="#fakelink" class="fa fa-angle-right"></a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>