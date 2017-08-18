<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" href="/css/school/back/common/reset.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="/dist/summernote.css" />
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script type="text/javascript" src="/dist/summernote.min.js"></script>
<script type="text/javascript" src="/dist/lang/summernote-zh-CN.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style>
input[type="text"] {
	border: none;
	width: 100%;
	height: 38px;
	line-height: 38px;
	border: 2px solid #bdc3c7;
	border-radius: 5px;
	padding-left: 5px;
}

.tianjia_xuexiao form .col-xs-10 select {
	width: 100%;
	border: 2px solid #bdc3c7;
	height: 42px;
	border-radius: 5px;
}

.tianjia_xuexiao input[type="button"], input[type="reset"] {
	width: 76px;
	height: 35px;
	display: block;
	margin: 25px auto;
	background-color: #8bc34a;
	color: #fff;
	border: none;
	border-radius: 3px;
	cursor: pointer;
	float: left;
	margin-right: 20px;
}

input[type="reset"] {
	background-color: orange;
}

.tianjia_xuexiao h4 {
	text-align: center;
	color: #313131;
	font-size: 18px;
	margin-top: 20px;
	font-weight: bold;
}
</style>
<script>
//添加学校介绍
function save_jiaocai(obj,material_id){
	var kecheng_name=$("#kecheng_name").val();
	if(kecheng_name!=null&&kecheng_name!=""){
		$(obj).attr("disabled","disabled"); 
		$("#myform").ajaxSubmit({
			type : 'POST',
			data:{'parent_id':material_id},
			url : "/material/save_jiaocai.jr",
			success : function(data) {
				if(data==1){
					//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
	                 layer.msg("添加成功！");
	                 $(obj).removeAttr("disabled");
	                 $('#myform')[0].reset();
				}else{
					layer.msg('很遗憾，添加失败');
					$(obj).removeAttr("disabled");
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				layer.msg('很遗憾，添加失败');
				$(obj).removeAttr("disabled");
			}
		});
	}else{
		layer.msg("请输入课程名称！")
	}
}
</script>
<div class="tianjia_xuexiao">
	<h4>${xuexiao_name }-${material.cengci }-${material.zhuanye_name }专业教材信息添加</h4>
	<form class="form-horizontal" enctype="multipart/form-data" id="myform"
		style="padding: 20px;">
		<div class="form-group">
			<label class="col-xs-2 control-label">课程名称：</label>
			<div class="col-xs-10">
				<input type="text" id="kecheng_name" name="kecheng_name" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">是否包含电子辅导材料：</label>
			<div class="col-xs-10">
				<select id="is_dianzi" name="is_dianzi">
					<option value="0">包含</option>
					<option value="1">不包含</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">课程类别：</label>
			<div class="col-xs-10">
				<select id="kecheng_leibie" name="kecheng_leibie">
					<option value="必修课">必修课</option>
					<option value="选修课">选修课</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">书名：</label>
			<div class="col-xs-10">
				<input type="text" id="shuming" name="shuming" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">作者：</label>
			<div class="col-xs-10">
				<input type="text" id="zuozhe" name="zuozhe" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">选修类型：</label>
			<div class="col-xs-10">
				<select id="xuanxiu_leixing" name="xuanxiu_leixing">
					<option value="">请选择</option>
					<option value="专业选修课">专业选修课</option>
					<option value="公共选修课">公共选修课</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">商品分类：</label>
			<div class="col-xs-10">
				<select id="shangpin_fenlei" name="shangpin_fenlei">
					<option value="">请选择</option>
					<option value="电子资料">电子资料</option>
					<option value="电子教材">电子教材</option>
					<option value="合作发行">合作发行</option>
					<option value="普通教材">普通教材</option>
					<option value="统考教材">统考教材</option>
					<option value="网院、作者包销教材">网院、作者包销教材</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">备注：</label>
			<div class="col-xs-10">
				<input type="text" id="beizhu" name="beizhu" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">课程学分：</label>
			<div class="col-xs-10">
				<input type="text" id="kecheng_xuefen" value="0" name="kecheng_xuefen" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">课程属性：</label>
			<div class="col-xs-10">
				<select id="kechengshuxing" name="kechengshuxing">
					<option value="专业课">专业课</option>
					<option value="专业基础课">专业基础课</option>
					<option value="专业选修课">专业选修课</option>
					<option value="公共基础课">公共基础课</option>
					<option value="公共选修课">公共选修课</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">&nbsp;</label>
			<div class="col-xs-10">
				<input type="button"
					onclick="save_jiaocai(this,${material.material_id})" value="添加">
				<input type="reset" value="重置">
			</div>
		</div>
	</form>
</div>