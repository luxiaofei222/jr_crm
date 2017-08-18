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
      <form class="form-horizontal import_xueyuanshuju_dialog"
		enctype="multipart/form-data" id="myform" style="padding: 20px;">
        <table class="table table-striped table-bordered table-hover" style="width:60%;margin:0 auto;">
          <tr>
            <th>证书编号</th>
            <td><input type="text" value="${entryInfo.zhengshu_num }" id="zhengshu_num" name="zhengshu_num" class="form-control"></td>
          </tr>
           <tr>
            <th>论文题目</th>
            <td><input type="text" value="${entryInfo.lunwen_timu }" id="lunwen_timu" name="lunwen_timu" class="form-control"></td>
          </tr>
          <tr>
            <th>期刊</th>
            <td><input type="text" value="${entryInfo.lunwen_qikan }" id="lunwen_qikan" name="lunwen_qikan" class="form-control"></td>
          </tr>
          <tr>
            <th>论文日期</th>
            <td>
              <input placeholder="选择论文日期" value="<fmt:formatDate value="${entryInfo.lunwen_time }" />" id="lunwen_time" name="lunwen_time" onclick="layui.laydate({elem: this, festival: true})" class="form-control">
            </td>
          </tr>
          <tr>
            <th>著作</th>
            <td><select id="lunwen_duzhu" name="lunwen_duzhu" class="select form-control">
            	<option value="独著">独著</option>
            	<option value="合著">合著</option>
            </select> </td>
          </tr>
          
          <tr>
            <th>摘要</th>
            <td>
              <input placeholder="请输入说明" value="${entryInfo.lunwen_zhaiyao }"  id="lunwen_zhaiyao" name="lunwen_zhaiyao"  class="form-control">
            </td>
          </tr>
        </table>
        <div style="width:60%;text-align:center;margin:0 auto;margin-top:20px;">
          <button type="button" onclick="update_info_lunwen(${entryInfo.entryInfoId})" class="btn btn-success btn-md">提交</button>
          <button type="reset" class="btn btn-danger btn-md" style="margin-left:10%;">重置</button>
        </div>
        </form>
      <div>
</body>
</html>
<script type="text/javascript">
function update_info_lunwen(entryInfoId){
	var lunwen_timu=$("#lunwen_timu").val();
	var lunwen_time=$("#lunwen_time").val();
	if(lunwen_timu!=null&&lunwen_timu!=""){
		if(lunwen_time!=null&&lunwen_time!=""){
			$("#myform").ajaxSubmit({
				type : 'POST',
				url : "/crm_entryinfo/update_lunwen.jr",
				data:{
					'entryInfoId':entryInfoId
				},
				success : function(data) {
					if(data==1){
						tanchuang('恭喜，添加成功');
					}else{
						tanchuang('很遗憾，添加失败');
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					tanchuang('很遗憾，添加失败');
				}
			});
		}else{
			layer.msg("请选择论文时间！")
		}
	}else{
		layer.msg("请输入论文题目！")
	}
}
  layui.use('laydate', function(){
  var laydate = layui.laydate;})
</script>