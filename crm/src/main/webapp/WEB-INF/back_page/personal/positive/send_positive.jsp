<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
	//提交离职申请
	function tijiao_zhuanzheng_shenqing(obj, employee_id) {
		var bumen_id = $("#bumen_id").val();
		var gangwei = $("#gangwei").val();
		var positive_time_str = $("#positive_time").val();
		var shenpi_id = $("#jingli_id").val();//审批人的ID
		var positive_content = $("#positive_content").val();
		if (positive_content != null && positive_content != "") {
			$(obj).attr("disabled", "disabled");
			$(obj).val("提交中");
			$.post("/positive/send_positive_content.jr", {
				'employee_id' : employee_id,
				'bumen_id' : bumen_id,
				'gangwei' : gangwei,
				'positive_time_str' : positive_time_str,
				'shenpi_id' : shenpi_id,
				'positive_content' : positive_content
			}, function(data) {
				if (data == 1) {
					layer.msg("提交成功");
					$(obj).removeAttr("disabled");
					$(obj).val("提交");
				} else if (data == 2) {
					layer.msg("您已经在申请转正了，去审批进度查看吧！");
					$(obj).removeAttr("disabled");
					$(obj).val("提交");
				} else {
					layer.msg("提交失败");
					$(obj).removeAttr("disabled");
					$(obj).val("提交");
				}
			})
		} else {
			layer.msg("请输入您要转正的理由！")
		}

	}
</script>
<c:if test="${not empty oaEmployee.zhuanzheng_time }">
<c:if test="${not empty positive }">
	<div
		style="margin: 0 auto; text-align: center; font-size: 18px; color: #ea592d; margin-top: 178px; line-height: 30px;">
		您已经提交过转正申请了，请耐心等待审批!<br />可在审批进度菜单查询结果！
	</div>
</c:if>
<c:if test="${empty positive }">
	<div
		style="margin: 0 auto; text-align: center; font-size: 18px; color: #ea592d; margin-top: 178px; line-height: 30px;">
		您转正的时间比本系统创建的时间还早！还申请什么？逗我呢……<br />可在审批进度菜单查询结果！
	</div>
	</c:if>
</c:if>
<c:if test="${empty oaEmployee.zhuanzheng_time }">
<c:if test="${not empty positive }">
	<div
		style="margin: 0 auto; text-align: center; font-size: 18px; color: #ea592d; margin-top: 178px; line-height: 30px;">
		您已经提交过转正申请了，请耐心等待审批!<br />可在审批进度菜单查询结果！
	</div>
</c:if>
<c:if test="${empty positive }">
	<ul class="lizhi_condi">
		<li class="lizhi_depar">
			<h4>部门：</h4> <select id="bumen_id" name="bumen_id">
				<option value="${bumen.organization_id }">${bumen.organization_name }</option>
		</select>
		</li>
		<li class="lizhi_post">
			<h4>岗位：</h4> <select id="gangwei" name="gangwei">
				<option value="${gangwei.organization_name }">${gangwei.organization_name }</option>
		</select>
		</li>
		<c:if test="${not empty oaEmployee }">
			<li class="lizhi_leave">
				<h4>入职日期：</h4> <span class="leave_time"><i
					class="icon iconfont icon-riqi"></i> <input class="layui-input"
					disabled="disabled"
					value='<fmt:formatDate pattern="yyyy年MM月dd日" value="${oaEmployee.ruzhi_time }" />'></span>
			</li>
		</c:if>
		<li class="lizhi_leave">
			<h4>预期转正日期：</h4> <span class="leave_time"><i
				class="icon iconfont icon-riqi"></i> <input class="layui-input"
				id="positive_time" placeholder="请输入转正日期"
				onclick="layui.laydate({elem: this, festival: true})"></span>
		</li>
	</ul>
	<h4>转正申请正文：</h4>
	<textarea id="positive_content" placeholder="请详细描述您转正申请的理由"></textarea>
	<h4>请假提交至：</h4>
	<div class="receptions">
		<select id="jingli_id" name="jingli_id">
			<c:forEach items="${employees }" var="employee_o">
				<option value="${employee_o.employee_id }">${employee_o.employee_name }</option>
			</c:forEach>
		</select>
		<h5 style="color: #ea592d; margin-top: 5px;">注：请提交至你的直接领导，如果你是领导请提交给自己,否则无效!</h5>
	</div>
	<div class="send1">
		<input type="button" onclick="tijiao_zhuanzheng_shenqing(this,${employee.employee_id})" value="提交" /> <input type="button"  onclick="get_zhuanzheng()" value="取消" />
	</div>

</c:if>
</c:if>