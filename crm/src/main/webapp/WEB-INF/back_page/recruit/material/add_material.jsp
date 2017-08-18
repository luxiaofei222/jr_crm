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
//添加学校教材计划
function save_chengkao_material(obj,zhaosheng_id,info){
	var zhuanye_id=$("#zhuanye_id").val();
	if(zhuanye_id!=0){
		 $(obj).attr("disabled","disabled"); 
			$("#myform").ajaxSubmit({
				type : 'POST',
				data:{'zhaosheng_id':zhaosheng_id,'info':info},
				url : "/material/save_chengkao_material.jr",
				success : function(data) {
					if(data==1){
						//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
						tanchuang('恭喜您，添加成功');
						$(obj).removeAttr("disabled");
					}else if(data==2){
						tanchuang('您已经添加过该专业了！');
						$(obj).removeAttr("disabled");
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
		layer.msg("请选择专业！");
	}
}
//获取专业
function get_zhuanye(chengkao_id,wangjiao_id){
	var cengci=$("#cengci").val();
		$.post("/material/get_chengkao_zhuanye.jr",{
			'chengkao_id':chengkao_id,
			'wangjiao_id':wangjiao_id,
			'cengci':cengci
		},function(data){
			$("#zhuanye_id").html(data);
		})
}
</script>
<div class="tianjia_xuexiao">
	<h4>${ckZhaoSheng.xuexiao_name }教材计划添加</h4>
	<form class="form-horizontal" enctype="multipart/form-data" id="myform"
		style="padding: 20px;">
		<div class="form-group">
			<label class="col-xs-2 control-label">选择层次：</label>
			<div class="col-xs-10">
				<select id="cengci" name="cengci" onchange="get_zhuanye('${ckZhaoSheng.chengkao_id }','${ckZhaoSheng.wangjiao_id }')">
					<option value="专升本">专升本</option>
					<option value="高起专">高起专</option>
					<option value="高起本">高起本</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">选择专业：</label>
			<div class="col-xs-10">
				<select id="zhuanye_id" name="zhuanye_id">
				<option value="0">请选择</option>
				<c:if test="${info==0 }">
				<c:forEach items="${chengkaoScs }" var="chengkao">
					<option value="${chengkao.chengkao_id }">${chengkao.chengkao_name }</option>
				</c:forEach>
				</c:if>
				<c:if test="${info==1 }">
				<c:forEach items="${universities }" var="wangjiao">
					<option value="${wangjiao.university_id }">${wangjiao.university_zhuanye }</option>
				</c:forEach>
				</c:if>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">教材计划：</label>
			<div class="col-xs-10">
				<select id="material_jihua" name="material_jihua">
					<option value="2017年春季">2017年春季</option>
					<option value="2017年秋季">2017年秋季</option>
					<option value="2018年春季">2018年春季</option>
					<option value="2018年秋季">2018年秋季</option>
					<option value="2019年春季">2019年春季</option>
					<option value="2019年秋季">2019年秋季</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">入学批次：</label>
			<div class="col-xs-10">
				<select id="ruxue_pici" name="ruxue_pici">
				<c:if test="${info==0 }">
					<option value="2017级招生">2017级招生</option>
					<option value="2018级招生">2018级招生</option>
					<option value="2019级招生">2019级招生</option>
				</c:if>
				<c:if test="${info==1 }">
				<option value="2017年春季招生">2017年春季招生</option>
				<option value="2017年秋季招生">2017年秋季招生</option>
				<option value="2018年春季招生">2018年春季招生</option>
				<option value="2018年秋季招生">2018年秋季招生</option>
				<option value="2019年春季招生">2019年春季招生</option>
				<option value="2019年秋季招生">2019年秋季招生</option>
				</c:if>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">总学分：</label>
			<div class="col-xs-10">
				<input type="text" id="zongxuefen" name="zongxuefen" value="0" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">必修课总学分：</label>
			<div class="col-xs-10">
				<input type="text" id="bixiuxuefen" name="bixiuxuefen" value="0" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">选修课总学分：</label>
			<div class="col-xs-10">
				<input type="text" id="xuanxiuxuefen" name="xuanxiuxuefen" value="0" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 control-label">&nbsp;</label>
			<div class="col-xs-10">
				<input type="button"
					onclick="save_chengkao_material(this,${ckZhaoSheng.zhaosheng_id},${info })"
					value="提交"> <input type="reset" value="重置">
			</div>
		</div>
	</form>
</div>