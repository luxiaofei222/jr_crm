<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
	<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
	<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
    <link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
    <script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
form .form-group label {
	font-size:15px;
	color:#444;
	}
form .form-group input:focus,form .form-group textarea:focus {
	border-color:#e74c3c;
	}
form .form-group .btn {
	margin-right:30px;
	}
</style>
<script>
//添加联系人信息信息
function update_lianxiren(customer_id,type){
	$("#myform").ajaxSubmit({
		type : 'POST',
		url : "/crm_business/update_lianxiren.jr",
		data:{
			'customer_id':customer_id
		},
		success : function(data) {
			if(data==1){
				//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
                tanchuang('恭喜您，修改成功');
				if(type==1){//我的联系人编辑联系人的时候自动关闭弹窗
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				    parent.layer.close(index);
				}
			}else{
				tanchuang('很遗憾，修改失败');
			}
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			tanchuang('很遗憾，添加失败');
		}
	});
}
//检查部门信息是否为空
function check_customer_depart(){
	var customer_depart=$("#customer_depart").val();
	if(customer_depart!=null&&customer_depart!=""){
		
		return true;
	}else{
		layer.msg('请输入联系人部门！');
		return false;
	}
}
//检查联系人姓名
function check_customer_name(){
	var customer_name=$("#customer_name").val();
	if(customer_name!=null&&customer_name!=""){
		
		return true;
	}else{
		layer.msg('请输入联系人姓名！');
		return false;
	}
}
//检查手机号
function check_customer_phone(){
	var customer_phone=$("#customer_phone").val();
	if(customer_phone!=null&&customer_phone!=""){
		
		return true;
	}else{
		layer.msg('请输入联系人的手机号！');
		return false;
	}
}
//切换二级课程以及等级
function get_sub_course(){
	var course_parent_id=$("#course_parent_id").val();
	$.post("/back_user/get_sub_course.jr",{
		'course_id':course_parent_id
	},function(data){
		$("#course_id").html(data);
	})
}
</script>
<form class="form-horizontal add_qiye_dialog" enctype="multipart/form-data" id="myform" style="padding:21px;">
     <div class="form-group">
      <label class="col-xs-2 control-label">部门：</label>
      <div class="col-xs-9">
        <input type="text" onblur="check_customer_depart()" value="${customer.customer_depart }" class="form-control" name="customer_depart" id="customer_depart" placeholder="部门">  
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">职务：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" name="customer_position" value="${customer.customer_position }" id="customer_position" placeholder="职务">
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">联系人：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" value="${customer.customer_name }" onblur="check_customer_name()" name="customer_name" id="customer_name" placeholder="联系人">   
      </div>
    </div>    
    <div class="form-group">
      <label class="col-xs-2 control-label">性别：</label>
      <div class="col-xs-9">
        <select data-toggle="select" class="form-control select select-primary"  id="customer_sex" name="customer_sex">
              <c:if test="${customer.customer_sex =='男' }">
              <option selected="selected" value="男">男</option>
              <option value="女">女</option>
              </c:if>
               <c:if test="${customer.customer_sex =='女' }">
              <option value="男">男</option>
              <option selected="selected" value="女">女</option>
              </c:if>
          </select>
      </div>
    </div>
     <%-- <div class="form-group">
      <label class="col-xs-2 control-label">报考课程：</label>
      <div class="col-xs-4">
        <select data-toggle="select" class="form-control select select-primary" id="course_parent_id" onchange="get_sub_course()" name="course_parent_id">
        <c:forEach items="${courseMenus }" var="course_menu">
         <c:if test="${customer.course_parent_id ==course_menu.course_id  }">
        <option selected="selected" value="${course_menu.course_id }">${course_menu.course_name }</option>
        </c:if>
        <c:if test="${customer.course_parent_id !=course_menu.course_id}">
        <option value="${course_menu.course_id }">${course_menu.course_name }</option>
        </c:if>
       </c:forEach>
        </select>
       </div>
       <div class="col-xs-4">
        <select id="course_id" name="course_id" data-toggle="select" class="form-control select select-primary">
        	<option  value="${courseMenu.course_id }">${courseMenu.course_name }</option>
        </select>
      </div>
    </div> --%>
    <div class="form-group">
      <label class="col-xs-2 control-label">办公电话：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" value="${customer.customer_officephone }" name="customer_officephone" id="customer_officephone" placeholder="办公电话"> 
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">手机：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" onblur="check_customer_phone()" value="${customer.customer_phone }" name="customer_phone" id="customer_phone" placeholder="手机"> 
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">QQ：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" name="customer_qq" value="${customer.customer_qq }" id="customer_qq" placeholder="QQ"> 
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">微信：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" value="${customer.customer_weixin }" name="customer_weixin" id="customer_weixin" placeholder="微信"> 
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">邮箱：</label>
      <div class="col-xs-9">
        <input type="text" class="form-control" value="${customer.customer_mail }" name="customer_mail" id="customer_mail" placeholder="邮箱"> 
      </div>
    </div>
    <div class="form-group">
      <label class="col-xs-2 control-label">备注：</label>
      <div class="col-xs-9">
        <textarea class="form-control" name="customer_note" id="customer_note">${customer.customer_note }</textarea> 
      </div>
    </div>
    <div class="form-group">
       <label for="inputPassword1" class="col-xs-2 control-label">&nbsp;</label>
      <div class="col-xs-9">
        <button type="button" onclick="update_lianxiren(${customer.customer_id},'${type }')" class="btn btn-success btn-xm">提交</button>
        <button type="reset" class="btn btn-danger btn-xm">重置</button> 
      </div> 
    </div>
	</form>	