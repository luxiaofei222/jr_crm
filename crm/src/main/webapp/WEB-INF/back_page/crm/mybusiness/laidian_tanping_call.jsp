<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="/css/crm/index/laidian.css">
   <script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script>
//修改备注
function update_customer_note(obj,customer_id){
	var customer_note=$("#customer_note").val();
	if(customer_note!=null&&customer_note!=""){
		$(obj).attr('disabled',"true");
		 $.post("/crm_business/update_lianxiren.jr", {
			 'customer_id':customer_id,
			'customer_note' : customer_note,
			'calltype' : 0
		}, function(data) {
			if(data==1){
				//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
                tanchuang('恭喜您，添加成功');
                $(obj).removeAttr('disabled');
			}else{
				tanchuang('很遗憾，添加失败');
			}
		})
	}
}
//检查企业名称
function check_company_name(){
	var company_name=$("#company_name").val();
	if(company_name!=null&&company_name!=""){
		return true;
	}else{
		layer.msg('请输入企业名称！');
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
//添加企业信息和联系人信息
function save_customer_note(){
	if(check_company_name()&&check_customer_name()){
		$(obj).attr('disabled',"true");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/crm_call/save_company_customer.jr",
			success : function(data) {
				if(data==1){
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
                    tanchuang('恭喜您，添加成功');
                    $(obj).removeAttr('disabled');
				}else{
					tanchuang('很遗憾，添加失败');
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，添加失败');
			}
		});
	}
}
</script>
<form enctype="multipart/form-data" id="myform" class="form-horizontal">
<div class="phone">来电号码：<span style="color:#94CE6E;">${phone }</span></div>
   <div class="phone phone_left">企业信息：</div>
    <c:if test="${not empty customer }">
    <table width="100%">
      <tr>
        <td width="10%" class="td_bg">企业名称</td>
        <td width="23%">&nbsp;${company.company_name }</td>
        <td width="10%" class="td_bg">企业类型</td>
        <td width="23%">&nbsp;${company.company_type }</td>
        <td width="10%" class="td_bg">所属行业</td>
        <td width="23%">&nbsp;${company.suoshuhangye }</td>
        </tr>
      <tr>
        <td width="10%" class="td_bg">地址</td>
        <td width="90%" colspan="5" class="td_bg">&nbsp;${company.company_addr }</td>
      </tr>
    </table>
    </c:if>
    <c:if test="${ empty customer }">
    <table width="100%" class="qiye">
      <tr>
        <td width="10%" class="td_bg">企业名称</td>
        <td width="23%"><input type="text" onblur="check_company_name()" id="company_name"  name="company_name" class="qiye_input"></td>
        <td width="10%" class="td_bg">企业类型</td>
        <td width="23%">
        <select id="company_type" name="company_type" class="qiye_select">
            <option value="央企">央企</option>
            <option value="国企">国企</option>
            <option value="政府">政府</option>
            <option value="事业单位">事业单位</option>
            <option value="私营">私营</option>
            <option value="民营">民营</option>
            <option value="其他">其他</option>
          </select>
        </td>
        <td width="10%" class="td_bg">所属行业</td>
        <td width="23%">
        <select id="suoshuhangye" name="suoshuhangye" class="qiye_select">
            <option value="财务审计">财务审计</option>
            <option value="教育科研培训">教育科研培训</option>
            <option value="人力资源服务">人力资源服务</option>
            <option value="通信类">通信类</option>
            <option value="钢铁/机械/设备/重工业">钢铁/机械/设备/重工业</option>
            <option value="金融类">金融类</option>
            <option value="娱乐/休闲/餐饮服务">娱乐/休闲/餐饮服务</option>
            <option value="能源(电力/水利/矿产)">能源(电力/水利/矿产)</option>
            <option value="医疗/医药/美容类>医疗/医药/美容类">医疗/医药/美容类>医疗/医药/美容类</option>
            <option value="建筑工程类">建筑工程类</option>
            <option value="消防检测维保类">消防检测维保类</option>
            <option value="其他">其他</option>
          </select>
        </td>
        </tr>
      <tr>
        <td width="10%" class="td_bg">地址</td>
        <td width="90%" colspan="5" class="td_bg"><input type="text" id="company_addr" name="company_addr" class="qiye_input"></td>
      </tr>
    </table>
    </c:if>
   <div class="phone phone_left">联系人信息：</div>
   <c:if test="${not empty customer }">
    <table width="100%">
      <tr>
        <td width="10%" class="td_bg">姓名</td>
        <td width="23%">&nbsp;${customer.customer_name }</td>
        <td width="10%" class="td_bg">所属部门</td>
        <td width="23%">&nbsp;${customer.customer_depart }</td>
        <td width="10%" class="td_bg">职务</td>
        <td width="23%">&nbsp;${customer.customer_position }</td>
        </tr>
      <tr>
       <td width="10%" class="td_bg">办公电话</td>
        <td width="23%">&nbsp;${customer.customer_officephone }</td>
        <td width="10%" class="td_bg">手机号</td>
        <td width="23%">&nbsp;${customer.customer_phone }</td>
        <td width="10%" class="td_bg">备注</td>
        <td width="23%" ><input type="text" id="customer_note" name="customer_note" class="qiye_input"></td>
      </tr>
    </table>
    </c:if>
    <c:if test="${empty customer }">
    <table width="100%" class="qiye">
      <tr>
        <td width="10%" class="td_bg">姓名</td>
        <td width="23%"><input type="text" onblur="check_customer_name()" id="customer_name" name="customer_name" class="qiye_input"></td>
        <td width="10%" class="td_bg">所属部门</td>
        <td width="23%"><input type="text" id="customer_depart" name="customer_depart" class="qiye_input"></td>
        <td width="10%" class="td_bg">职务</td>
        <td width="23%"><input type="text" id="customer_position" name="customer_position" class="qiye_input"></td>
        </tr>
      <tr>
       <td width="10%" class="td_bg">办公电话</td>
        <td width="23%"><input type="text" id="customer_officephone" name="customer_officephone" class="qiye_input"></td>
        <td width="10%" class="td_bg">手机号</td>
        <td width="23%"><input type="text" id="customer_phone" name="customer_phone" class="qiye_input"></td>
        <td width="10%" class="td_bg">备注</td>
        <td width="23%"><input type="text" id="customer_note" name="customer_note" class="qiye_input"></td>
      </tr>
    </table>
    </c:if>
     <div class="form-group" style="margin-top:15px;">
          <div class="col-xs-12" style="text-align:center;">
           <c:if test="${not empty customer }">
          <button type="button" class="btn btn-lg" onclick="update_customer_note(this,${customer.customer_id })" style="width:100px;background-color:#94CE6E;color:white;">确定</button>
         	</c:if>
         	 <c:if test="${empty customer }">
          <button type="button" class="btn btn-lg" onclick="save_customer_note(this)" style="width:100px;background-color:#94CE6E;color:white;">确定</button>
         	</c:if>
          </div>
    </div>
    <c:if test="${not empty customer }">
    <div class="phone phone_left">通话记录：</div>
    <c:if test="${empty businessCallRecords }">
    <div class="laidian_zanwu">暂无该客户通话记录！</div>
    </c:if>
    <table class="person_right table table-hover">
        <thead>
          <tr class="tr_bgcolor">
          <th>时间</th>
          <th>通话类型</th>
          <th>联系电话</th>
          <th>座席号</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${businessCallRecords }" var="record">
          <tr>
            <td><fmt:formatDate type="both" value="${record.call_time }" /></td>
            <td>${record.call_type }</td>
            <c:if test="${record.call_type=='呼出' }">
            <td>${record.called_phone }</td> 
            <td>${record.zuoxi }</td>
            </c:if>
            <c:if test="${record.call_type=='呼入' }">
            <td>${record.zuoxi }</td>
            <td>${record.called_phone }</td> 
            </c:if>
          </tr>
          </c:forEach>
        </tbody>
    </table>
    </c:if>
</form>
<script type="text/javascript">
$(function(){
  $(".qiye_input").focus(function(){
    $(this).removeClass("qiye_input");
    $(this).addClass("qiye_input1");
  })
  $(".qiye_input").blur(function(){
    $(this).removeClass("qiye_input1");
    $(this).addClass("qiye_input");
  })
  })
</script>