<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
</head>
<h1>成绩查询</h1> 
<div class="grade_wrapper">
	<div class='person_info'>
		<div class='info_fen'>
			<span>姓名：</span>
			<input type="text" class="kaos_input">
		</div>
		<div class='info_fen'>
			<span>身份证号：</span>
			<input type="text" class="kaos_input">
		</div>
		<div class='info_fen'>
			<span>考号：</span>
			<select>
				<option value="准考证号">准考证号</option>
				<option value="考生号">考生号</option>
			</select>
			<input type="text" class="kaos_input2">
		</div>
		<button class="chaxun">查询</button>
	</div>
	<div class='grade_info'>
	<c:forEach items="${grades }" var="grade">
		<div class="grade_title">
			<div class="line"></div>
			<div class="grade_titlex">企业人力资源管理师(二级)</div>
		</div>
		<table class='grade_table_z'>
			<tr>
				<td>
					<img src='/images/school/front/person/per_info.png' style="margin:0 auto;">
					<span style="font-size:16px;">个人信息</span>
				</td>
				<td>
					<div class='grade_fenlei'>
						<span class="grade_name">姓名：</span>
						<span class="grade_result">李一</span>
					</div>
					<div class='grade_fenlei'>
						<span class="grade_name">身份证号：</span>
						<span class="grade_result">131180199110125446</span>
					</div>
					<div class='grade_fenlei'>
						<span class="grade_name">准考证号：</span>
						<span class="grade_result">431180199110125446</span>
					</div>
					<div class='grade_fenlei'>
						<span class="grade_name">考试科目：</span>
						<span class="grade_result">企业人力资源管理师(二级)</span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<img src='/images/school/front/person/apply.png' style="margin:0 auto;">
					<span style="font-size:16px;">个人成绩</span>
				</td>
				<td>
					<table class='grade_table_f'>
						<tr>
							<td>理论</td>
							<td>合格</td>
						</tr>
						<tr>
							<td>技能</td>
							<td>合格</td>
						</tr>
						<tr>
							<td>综合</td>
							<td>合格</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</c:forEach>
	</div>
</div>
