<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/css/edu/entryinfo/e_tankuang.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/css/school/back/layui/lay/modules/laydate.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>添加补费</title>
<style>
 .wrapper{
    padding:10px;
  }
  table tr th{
    text-align: center;
    vertical-align: middle!important;
  }
  table tr td{
    text-align: center;
    vertical-align: middle!important;
  }
</style>
</head>
<body>
  <div class="wrapper">
   <c:if test="${empty repairMoneys }">
     <p style="margin:20px auto;text-align:center;font-size:18px;color:#94CE6E;">暂无补费信息，请添加吧！</p>
   </c:if>
  <c:if test="${not empty repairMoneys }">
      <table class="table table-striped table-bordered table-hover">
        <tr class="success">
          <th>项目</th>
          <th>费用</th>
          <th>付款日期</th>
          <th>费用说明</th>
          <th>支付方式</th>
          <th>操作</th>
        </tr>
        <c:forEach items="${repairMoneys }" var="repair">
        <tr>
          <td>
            <input type="text" id="repair_name${repair.repair_id }" value="${repair.repair_name }" class="form-control">
          </td>
          <td>
            <input type="text" id="pay_money${repair.repair_id }" value="${repair.pay_money }" class="form-control">
          </td>
          <td>
            <input placeholder="选择付款日期" id="pay_time${repair.repair_id }" value="<fmt:formatDate value="${repair.pay_time }" />" onclick="layui.laydate({elem: this, festival: true})" class="form-control">
          </td>
           <td>
            <input type="text" id="note${repair.repair_id }" value="${repair.note }" class="form-control">
          </td>
          <td>
             <select id="paytype${repair.repair_id }" name="paytype">
             <option value="${repair.paytype }">${repair.paytype }</option>
			<option value="支付宝">支付宝</option>
			<option value="微信">微信</option>
			<option value="刷卡">刷卡</option>
			<option value="现金">现金</option>
			<option value="穆工行">穆工行</option>
			<option value="穆工行">穆工行</option>
			<option value="吕工行">吕工行</option>
			<option value="吕建行">吕建行</option>
			<option value="吕农行">吕农行</option>
			<option value="沧州对公">沧州对公</option>
			<option value="中国邮政">中国邮政</option>
			</select>
          </td>
          <td>
           <input type="button" onclick="update_repair_money(${repair.repair_id })" class="btn btn-success btn-md" value="修改" />
          </td>
        </tr>
        </c:forEach>
      </table>
   </c:if>   
      <h3 style="font-size:18px;margin-left:22%;">添加补费<strong style="font-weight:bolder;">↓<strong></h3>
      <form class="form-horizontal import_xueyuanshuju_dialog"
		enctype="multipart/form-data" id="myform" style="padding: 20px;">
        <table class="table table-striped table-bordered table-hover" style="width:60%;margin:0 auto;">
          <tr>
            <th>项目名称</th>
            <td><input type="text" id="repair_name" name="repair_name" class="form-control"></td>
          </tr>
          <tr>
            <th>费用</th>
            <td><input type="text" value="0" id="pay_money" name="pay_money" class="form-control"></td>
          </tr>
          <tr>
            <th>付款日期</th>
            <td>
              <input placeholder="选择付款日期" id="pay_time" name="pay_time_str" onclick="layui.laydate({elem: this, festival: true})" class="form-control">
            </td>
          </tr>
          <tr>
            <th>支付方式</th>
            <td>
              <select id="paytype" name="paytype">
			<option value="支付宝">支付宝</option>
			<option value="微信">微信</option>
			<option value="刷卡">刷卡</option>
			<option value="现金">现金</option>
			<option value="穆工行">穆工行</option>
			<option value="穆工行">穆工行</option>
			<option value="吕工行">吕工行</option>
			<option value="吕建行">吕建行</option>
			<option value="吕农行">吕农行</option>
			<option value="沧州对公">沧州对公</option>
			<option value="中国邮政">中国邮政</option>
			</select>
            </td>
          </tr>
          <tr>
            <th>备注说明</th>
            <td>
              <input placeholder="请输入说明" value="无" id="note" name="note"  class="form-control">
            </td>
          </tr>
        </table>
        <div style="width:60%;text-align:center;margin:0 auto;margin-top:20px;">
          <button type="button" onclick="add_repair(this,${entryInfoId})" class="btn btn-success btn-md">提交</button>
          <button type="reset" class="btn btn-danger btn-md" style="margin-left:10%;">重置</button>
        </div>
        </form>
      <div>
</body>
</html>
<script type="text/javascript">
function add_repair(obj,entryInfoId){
	var repair_name=$("#repair_name").val();
	var pay_time=$("#pay_time").val();
	if(repair_name!=null&&repair_name!=""){
		if(pay_time!=null&&pay_time!=""){
			 $(obj).attr({"disabled":"disabled"});
			$("#myform").ajaxSubmit({
				type : 'POST',
				url : "/repair/add_repair_money.jr",
				data:{
					'entry_info_id':entryInfoId
				},
				success : function(data) {
					if(data==1){
						tanchuang('恭喜，添加成功');
						location.reload();
					}else{
						tanchuang('很遗憾，添加失败');
						$(obj).removeAttr("disabled");
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					tanchuang('很遗憾，添加失败');
				}
			});
		}else{
			layer.msg("请选择费用的实际付款时间！")
		}
	}else{
		layer.msg("请输入您要补缴的费用名称！")
	}
}
//修改补费信息
function update_repair_money(repair_id){
	var repair_name=$("#repair_name"+repair_id).val();
	var pay_money=$("#pay_money"+repair_id).val();
	var pay_time=$("#pay_time"+repair_id).val();
	var note=$("#note"+repair_id).val();
	var paytype=$("#paytype"+repair_id).val();
	$.post("/repair/update_repair_money.jr",{
		'repair_id':repair_id,
		'repair_name':repair_name,
		'pay_money':pay_money,
		'pay_time_str':pay_time,
		'note':note,
		'paytype':paytype
	},function(data){
		if(data==1){
			layer.msg("修改成功！");
		}else{
			layer.msg("修改失败！");
		}
	})
	
}
  layui.use('laydate', function(){
  var laydate = layui.laydate;})
</script>