<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script>
$(function() {
	// setting custom defaults
	var user_birthday=$("#flatpickr-tryme").val();
	if(user_birthday!=null&&user_birthday!=""){
		Flatpickr.l10n.firstDayOfWeek = 1;
		document.getElementById("flatpickr-tryme").flatpickr({
			allowInput:true,
			defaultDate:user_birthday
		});
	}else{
		Flatpickr.l10n.firstDayOfWeek = 1;
		document.getElementById("flatpickr-tryme").flatpickr({
			allowInput:true,
			defaultDate:new Date()
		});
	}

})

// 获取城市名称
function get_city() {
	var id = $("#province").val();
	$.ajax({
		type : "POST",// 请求方式
		url : "/person/get_city_erji.html?id=" + id,// 地址，就是action请求路径
		data : {
			"id" : id
		},
		dataType : "json",// 数据类型text xml json script jsonp
		success : function(msg) {// 返回的参数就是 action里面所有的有get和set方法的参数
			var ruleListTemp = "";
			$.each(msg, function(i, item) {
				ruleListTemp += ("<option value='" + item.name + "'>"
						+ item.name + "</option>");
			});
			$("#user_city").html(ruleListTemp);
		}
	});
}


//修改用户信息
function update_user(user_id){
	var id=$("#province").val();//省份的id
	 $("#myform").ajaxSubmit({
			type : 'POST',
			data:{"id":id,'user_id':user_id},
			url : "/person/update_person.html",
			success : function(data) {
				if(data==1){
					location.href="/person/get_person_index.html";
				}else{
					alert("提示：很遗憾，添加失败！");
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				alert("添加失败");
			}
		});
}
</script>
<h1>个人信息</h1>
<div class="person_content_text">

	<ul class="information_update">
		<form enctype="multipart/form-data" id="myform" class="form1">
			<li><span>昵称：</span> <input type="text" id="user_nickname"
				name="user_nickname" value="${user.user_nickname }" /></li>
			<div class="clear"></div>
			<li><span>性别：</span> <select name="user_sex" id="user_sex">
					<option value="${user.user_sex }">${user.user_sex }</option>
					<option value="男">男</option>
					<option value="女">女</option>
			</select></li>

			<div class="clear"></div>
			<li><span>QQ号：</span> <input type="text" id="user_qq"
				name="user_qq" value="${user.user_qq }" /></li>
			<%--  <div class="clear"></div>
          <li>
            <span>邮箱：</span>
            <input type="text" name="user_mail" id="user_mail"  value="${user.user_mail }" />
          </li> --%>
			<div class="clear"></div>
			<li><span>出生年月：</span> <input id="flatpickr-tryme"
				name="birthday" value="${biethday }" placeholder="点击获取日期"
				type="text" /></li>
			<div class="clear"></div>
			<li><span>所在地：</span> <select onchange="get_city()"
				id="province">
					<c:if test="${not empty user.user_province }">
						<option value="1">${user.user_province }</option>
					</c:if>
					<option value="0">----请选择省份----</option>
					<c:forEach items="${cities }" var="pro">
						<option value="${pro.id }">${pro.name }</option>
					</c:forEach>
			</select> <select class="city" id="user_city" name="user_city">
					<c:if test="${not empty user.user_city }">
						<option value="${user.user_city }">${user.user_city }</option>
					</c:if>
					<option value="">----请选择城市----</option>
			</select></li>
			<div class="clear"></div>
			<li><span>职业：</span> <input type="text" name="user_zhiye"
				id="user_zhiye" value="${user.user_zhiye }" /></li>
			<div class="clear"></div>
			<li><span>公司名称：</span> <input type="text" name="user_bus"
				id="user_bus" value="${user.user_bus }" /></li>
			<div class="clear"></div>
			<li style="border: none;"><span>&nbsp;</span> <input
				type="button" value="保存" onclick="update_user(${user.user_id})"
				class="yes" /> <input type="button" onclick="get_person()"
				value="取消" class="no" /></li>
			<div class="clear"></div>
		</form>
	</ul>
</div>
