<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/css/school/back/common/reset.css">
	<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
	<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
    <link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
    <link rel="stylesheet" href="/css/crm/index/qiyeku.css" />
    <script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script>

function tj_pro(obj){
	$(obj).after("<div class='pro'><input type='text' id='palte_title' name='palte_title' /><span>:</span><input type='text' id='plate_content' name='plate_content'/><i class='fa fa-minus-circle' onClick='sc_pro(this)'></i></div>");
	$(obj).nextAll(".pro").children(".fa-minus-circle").hide();
	$("div.pro:last").find(".fa-minus-circle").show();
  };
  
  function sc_pro(obj){
	$(obj).parent().prev().find(".fa-minus-circle").show();
	$(obj).parent().remove();
  };
//修改企业信息
function update_company(company_id){
	var str = "";
	$('input[name=palte_title]').each(function(i) {
		if (0 == i) {
			str = $(this).val();
		} else {
			str += ","+$(this).val();
		}
	});
	var str1 = "";
	$('input[name=plate_content]').each(function(i) {
		if (0 == i) {
			str1 = $(this).val();
		} else {
			str1 += ","+$(this).val();
		}
	});
	if(check_company_name()&&check_company_addr()){
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/crm_business/update_company.jr",
			data:{
				'str':str,
				'str1':str1,
				'company_id':company_id
			},
			success : function(data) {
				if(data==1){
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
                    tanchuang('恭喜您，修改成功');
				}else{
					tanchuang('很遗憾，修改失败');
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，修改失败');
			}
		});
	}
}
//检测单位名称
function check_company_name(){
	var bol = false;
	var duibicompanyname=$("#duibicompanyname").val();
	var company_name=$("#company_name").val();
	if(company_name!=null&&company_name!=""){
		if(duibicompanyname==company_name){
			bol = true;
		}else{
			$.ajax({
				type : "POST",
				url : "/crm_business/check_company_name.jr?company_name="
						+ company_name,
				async : false,
				success : function(data) {
					if (data == 1) {
						//单位名称可以用
						bol = true;
					} else {
						//单位名称重复
						layer.msg('你输入的企业名称已存在！');
						bol = false;
					}
				}
			});
		}
	}else{
		layer.msg('请输入企业名称！');
		bol = false;
	}
	return bol;
}
//获取城市列表
function get_city() {
	var id = $("#company_province").val();
	if(id==0){
		var ruleListTemp = "<option value='0'>请选择市</option>";
		$("#company_city").html(ruleListTemp);
	}else{
		$.ajax({
			type : "POST",// 请求方式
			url : "/crm_business/get_city_erji.jr?id=" + id,// 地址，就是action请求路径
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
				$("#company_city").html(ruleListTemp);
			}
		});
	}

}
//检查企业地址是否为空
function check_company_addr(){
	var company_addr=$("#company_addr").val();
	if(company_addr!=null&&company_addr!=""){
		
		return true;
	}else{
		layer.msg('请输入企业地址！');
		return false;
	}
}
</script>
<form class="form-horizontal add_qiye_dialog" enctype="multipart/form-data" id="myform" style="padding:21px;">
     <table width="800">
      <tr>
        <td width="80" class="td_bg">企业名称</td>
        <td colspan="3" width="320"><input type="text" value="${company.company_name}" onblur="check_company_name()" name="company_name" id="company_name" /></td>
        <td width="80" class="td_bg">所属行业</td>
        <td colspan="3" width="320">
         <select id="suoshuhangye"name="suoshuhangye">
             <option value="${company.suoshuhangye}">${company.suoshuhangye}</option>
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
        <td width="80" class="td_bg">企业规模</td>
        <td width="120">
        <select id="company_guimo"name="company_guimo">
        	<c:if test="${company.company_guimo=='0-50'}">
            <option selected="selected" value="0-50">0-50</option>
            <option value="50-100">50-100</option>
            <option value="100以上">100以上</option>
            </c:if>
            <c:if test="${company.company_guimo=='50-100'}">
            <option value="0-50">0-50</option>
            <option selected="selected" value="50-100">50-100</option>
            <option value="100以上">100以上</option>
            </c:if>
             <c:if test="${company.company_guimo=='100以上'}">
            <option value="0-50">0-50</option>
            <option value="50-100">50-100</option>
            <option selected="selected" value="100以上">100以上</option>
            </c:if>
          </select>
        </td>
        <td width="80" class="td_bg">企业类型</td>
        <td width="120"><input type="text" value="${company.company_type}" id="company_type" name="company_type"/></td>
        <td width="80" class="td_bg">所属集团</td>
        <td colspan="3" width="320"><input type="text" value="${company.company_jituan}" id="company_jituan" name="company_jituan" /></td>
        </tr>
      <tr>
        <td width="80" class="td_bg">省</td>
        <td width="120">
          <select id="company_province" onchange="get_city()" name="company_province_id">
            <option value="0">请选择省份</option>
            <c:forEach items="${cities }" var="city">
            <c:if test="${company.company_province== city.name}">
            <option selected="selected" value="${city.id }">${city.name }</option>
            </c:if>
             <c:if test="${company.company_province!= city.name}">
            <option value="${city.id }">${city.name }</option>
            </c:if>
            </c:forEach>
          </select>
        </td>
        <td width="80" class="td_bg">市</td>
        <td width="120">
          <select id="company_city" name="company_city">
          	<c:if test="${empty citiessub }">
          	 <option value="0">请选择</option>
          	</c:if>
           <c:forEach items="${citiessub }" var="city">
            <c:if test="${company.company_city== city.name}">
            <option selected="selected" value="${city.name }">${city.name }</option>
            </c:if>
             <c:if test="${company.company_city!= city.name}">
            <option value="${city.name }">${city.name }</option>
            </c:if>
            </c:forEach>
          </select>
        </td>
        <td width="80" class="td_bg">区号</td>
        <td width="120"><input type="text" value="${company.company_quhao}" id="company_quhao" name="company_quhao" /></td>
        <td width="80" class="td_bg">邮编</td>
        <td width="120"><input type="text" value="${company.company_youbian}" id="company_youbian" name="company_youbian"/></td>
      </tr>
      <tr>
        <td width="80" class="td_bg">地址</td>
        <td colspan="3" width="320"><input type="text" value="${company.company_addr}"  onblur="check_company_addr()" id="company_addr" name="company_addr" /></td>
        <td width="80" class="td_bg">企业网址</td>
        <td colspan="3" width="320"><input type="text" value="${company.company_web}" id="company_web" name="company_web" /></td>
        </tr>
         <tr>
        <td width="80" class="td_bg">联系电话</td>
        <td colspan="3" width="320"><input type="text" value="${company.company_zongjingli_one_phone}" id="company_zongjingli_one_phone" name="company_zongjingli_one_phone" /></td>
        <td width="80" class="td_bg">负责人</td>
        <td colspan="3" width="320"><input type="text" value="${company.company_zongjingli_one}" id="company_zongjingli_one" name="company_zongjingli_one" /></td>
        </tr>
      <tr>
        <td width="80" class="td_bg">经营范围</td>
        <td colspan="7" width="720"><input type="text" value="${company.company_jingying}"  id="company_jingying" name="company_jingying" /></td>
        </tr>
      <tr>
        <td width="80" class="td_bg">备注</td>
        <td colspan="7" width="720"><input type="text" value="${company.company_note}" id="company_note" name="company_note" /></td>
        </tr>
    </table>
    <div class="add_diy" onClick="tj_pro(this)"><i class="fa fa-plus-circle"></i><span>添加自定义项</span></div>
	<c:if test="${not empty customerPlates }">
	<c:forEach items="${customerPlates }" var="plate">
	<div class='pro'><input type='text' value="${plate.palte_title }" id='palte_title' name='palte_title' /><span>:
	</span><input value="${plate.plate_content }" type='text' id='plate_content' name='plate_content'/>
	<i class='fa fa-minus-circle' onClick='sc_pro(this)'></i></div>
	</c:forEach>
	</c:if>
    <div class="suc_diy clearfix">
    <input type="hidden" value="${company.company_name}" id="duibicompanyname">
        <button type="button" onclick="update_company(${company.company_id})"  class="btn btn-success btn-xm">提交</button>
        <button type="reset" class="btn btn-danger btn-xm">重置</button>  
    </div>
	</form>	