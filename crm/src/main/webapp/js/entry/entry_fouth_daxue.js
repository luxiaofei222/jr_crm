//获取城市名称
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
				ruleListTemp += ("<option value='" + item.id + "'>"
						+ item.name + "</option>");
			});
			$("#user_city").html(ruleListTemp);
			get_area();
		}
	});
}
//籍贯城市
function get_jiguan_city() {
	var id = $("#province_jiguan").val();
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
				ruleListTemp += ("<option value='" + item.id + "'>"
						+ item.name + "</option>");
			});
			$("#jiguan_city").html(ruleListTemp);
		}
	});
}
//获取地区
function get_area() {
	var id = $("#user_city").val();
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
			$("#entryUserArea").html(ruleListTemp);
		}
	});
}
//获取第三步
function get_third(dition,entry_infoid){
	location.href="/entry_info/get_entry_third.html?entrycondition_id="+dition+"&entryInfoId="+entry_infoid;
}
//检查证件照片
function check_zhengjian_pic(){
	var zhengmian_pic=$("#zhengmian_pic").val();
	var fanmian_pic=$("#fanmian_pic").val();
	if(zhengmian_pic!=""&&zhengmian_pic!=null){
		return true;
	}else if(fanmian_pic!=""&&fanmian_pic!=null){
		return true;
	}else{
		layer.msg("请上传身份证照片");
		return false;
	}
}
//获取文件名称
function get_file_name(obj){
	var fileName="";
	  var name = $(obj).val();
	  fileName = name.split("\\").pop(); 
	  $(obj).parent().siblings("input").val(fileName);
	  $(obj).parent().next("i").remove();
	  $(obj).parent().after("<i class='fa fa-check'></i> ");
}
//检查学历照片
function check_xueli_pic_name(){
	var xueli_pic=$("#xueli_pic").val();
	if(xueli_pic!=null&&xueli_pic!=""){
		return true;
	}else{
		layer.msg("请上传您的学历照片！");
		return false;
	}
}
//检查是否填写推广员账号
function check_job_number(){
	var job_number=$("#job_number").val();
	if(job_number!=null&&job_number!=""){
		if(job_number=="无"){
			return true;
		}else{
			$.post("/entry_info/panduan_job_number.html",{
				'job_number':job_number
			},function(data){
				if(data!=1){
					layer.msg("您填写的推广员账号未关联业务员！可忽略此提示信息！");
				}
			})
			return true;
		}
	}else{
		layer.msg("请填写您的推广员号码或者填写无！");
		return false;
	}
}
//检查用户免冠照片
function check_user_pic_name(){
	var user_pic=$("#user_pic").val();
	if(user_pic!=null&&user_pic!=""){
		return true;
	}else{
		layer.msg("请上传您的个人免冠照片！");
		return false;
	}
}
//第五步
function get_fifth(entry_infoid){
	if(check_zhengjian_pic()&&check_user_pic_name()&&check_documentNumber()
			&&check_entryUserName()&&check_entryUserMail()&&check_entryUserPhone()&&check_job_number()){
		var star_time="";//开始时间
		$('input[name=star_time_str]').each(function(i) {
			if (0 == i) {
				star_time = $(this).val();
			} else {
				star_time += ","+$(this).val();
			}
		});
		var end_time="";//开始时间
		$('input[name=end_time_str]').each(function(i) {
			if (0 == i) {
				end_time = $(this).val();
			} else {
				end_time += ","+$(this).val();
			}
		});
		
		var suozaidanwei="";//所在单位
		$('input[name=suozaidanwei_str]').each(function(i) {
			if (0 == i) {
				suozaidanwei = $(this).val();
			} else {
				suozaidanwei += ","+$(this).val();
			}
		});
		
		var zhengmingren="";//证明人
		$('input[name=zhengmingren_str]').each(function(i) {
			if (0 == i) {
				zhengmingren = $(this).val();
			} else {
				zhengmingren += ","+$(this).val();
			}
		});
		var xingming="";//姓名
		$('input[name=xingming_str]').each(function(i) {
			if (0 == i) {
				xingming = $(this).val();
			} else {
				xingming += ","+$(this).val();
			}
		});
		var guanxi="";//家庭关系
		$('input[name=guanxi_str]').each(function(i) {
			if (0 == i) {
				guanxi = $(this).val();
			} else {
				guanxi += ","+$(this).val();
			}
		});
		var nianling="";//年龄
		$('input[name=nianling_str]').each(function(i) {
			if (0 == i) {
				nianling = $(this).val();
			} else {
				nianling += ","+$(this).val();
			}
		});
		var family_danwei="";//所在单位
		$('input[name=family_danwei_str]').each(function(i) {
			if (0 == i) {
				family_danwei = $(this).val();
			} else {
				family_danwei += ","+$(this).val();
			}
		});
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/entry_info/save_fourth_info.html?entryInfoId="+entry_infoid,
			data:{
				'star_time':star_time,
				'end_time':end_time,
				'suozaidanwei':suozaidanwei,
				'zhengmingren':zhengmingren,
				'xingming':xingming,
				'guanxi':guanxi,
				'nianling':nianling,
				'family_danwei':family_danwei
			},
			success : function(data) {
				if(data==1){
					location.href="/entry_info/get_entry_fifth.html?entryInfoId="+entry_infoid;
				}else{
					tanchuang('很遗憾，系统发生错误，报名失败！');
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，系统发生错误，报名失败！');
			}
		});
	}
}
//检查证件号码不能为空
function check_documentNumber(){
	var documentNumber=$("#documentNumber").val();
	if(documentNumber!=null&&documentNumber!=""){
		isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
		if(isIDCard2.test(documentNumber)){
			var year = documentNumber.substr(6,4);
			var month = documentNumber.substr(10,2);
			var day = documentNumber.substr(12,2);
			$("#entryUserBirthday").val(year+"-"+month+"-"+day);
			var sexStr = parseInt(documentNumber.substring(17, 1),10) % 2 ? "男" : "女";
			$("#entryUserSex").val(sexStr);
			return true;
		}else{
			layer.msg("请输入正确的身份证号码！");
			return false;
		}
	}else{
		layer.msg("请输入证件号码！");
		return false;
	}
}
//检查用户姓名
function check_entryUserName(){
	var entryUserName=$("#entryUserName").val();
	if(entryUserName!=null&&entryUserName!=""){
		$("#entryUserName").next("i").remove();
		$("#entryUserName").after("<i class='fa fa-check'></i> ");
		return true;
	}else{
		layer.msg("请输入您的姓名！");
		return false;
	}
}
//检查出生日期
function check_entryUserBirthday(){
	var entryUserBirthday=$("#entryUserBirthday").val();
	if(entryUserBirthday!=null&&entryUserBirthday!=""){
		$("#entryUserBirthday").next("i").remove();
		$("#entryUserBirthday").after("<i class='fa fa-check'></i> ");
		return true;
	}else{
		layer.msg("请选择您的出生日期！");
		return false;
	}
}
//字符串转时间格式  
function getDate(strDate) {    
      var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,    
       function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');    
      return date;    
  } 
//检查邮箱
function check_entryUserMail(){
	var entryUserMail=$("#entryUserMail").val();
	 var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
	 if(entryUserMail!=null&&entryUserMail!=""){
		 if(!search_str.test(entryUserMail)){       
			 layer.msg("请输入正确格式的邮箱！");
		     return false;
		 }else{
			 $("#entryUserMail").next("i").remove();
				$("#entryUserMail").after("<i class='fa fa-check'></i> ");
				return true; 
		 }
		}else{
			layer.msg("请输入您的电子邮箱！");
			return false;
		}
}
//检查用户手机号
function check_entryUserPhone(){
	var entryUserPhone=$("#entryUserPhone").val();
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/; 
	 if(entryUserPhone!=null&&entryUserPhone!=""){
		 if(!myreg.test(entryUserPhone)){ 
				layer.msg('请输入有效的手机号码！'); 
			    return false; 
			}else{
			 $("#entryUserPhone").next("i").remove();
				$("#entryUserPhone").after("<i class='fa fa-check'></i> ");
				return true; 
		 }
		}else{
			layer.msg("请输入您的手机号！");
			return false;
		}
}