//选择申报条件
function get_shenbaotiaojian(obj) {
	var plan_id = $(obj).val();
	if (plan_id == 0) {
		$("#entrycondition_id").html("<option value='0'>请选择申报课程</option>");
	} else {
		$.post("/edu_entry/get_condition_list.jr", {
			'entryplan_id' : plan_id
		}, function(data) {
			$("#course_id").html(data)
		})

		$.post("/edu_entry/get_feiyong_info.jr", {
			'entryplan_id' : plan_id
		}, function(data) {
			$("#feiyong").html(data)
		})

	}
}
//检查报考班次
function check_baokao_banci(){
	var baokao_banci = $("#baokao_banci").val();
	if (baokao_banci!=null&&baokao_banci!="") {
		return true;
	} else {
		layer.msg("请输入报考班次！")
		return false;
	}
}
// 获取申报级别
function get_dic_list(obj) {
	var entrycondition_id = $(obj).val();
	if (entrycondition_id == 0) {
		$("#dictionary_id").html("<option value='0'>青选择申报级别</option>");
	} else {
		$.post("/edu_entry/get_tiaojianlist.jr", {
			'entrycondition_id' : entrycondition_id
		}, function(data) {
			$("#dictionary_id").html(data);
		});
		// 添加表格
		$.post("/edu_entry/to_add_entryinfo.jr", {
			'entrycondition_id' : entrycondition_id
		}, function(data) {
			$("#info_table").html(data);
			var plan_id = $("#entryplanId").val();// 显示费用
			$.post("/edu_entry/get_feiyong_info.jr", {
				'entryplan_id' : plan_id
			}, function(data) {
				$("#feiyong").html(data)
			})
		});
	}
}

function get_tiaojian_radio(obj) {
	var entrycondition_id = $(obj).val();

	if (entrycondition_id == 0) {
		$("#tiaojianlist").html("");
	} else {
		$.post("/edu_entry/get_tiaojian_radio.jr", {
			'entrycondition_id' : entrycondition_id
		}, function(data) {
			$("#tiaojianlist").html(data)
		})
	}
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
			$.each(msg,
					function(i, item) {
						ruleListTemp += ("<option value='" + item.id + "'>"
								+ item.name + "</option>");
					});
			$("#user_city").html(ruleListTemp);
			get_area();
		}
	});
}
// 获取地区
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
// 关闭弹窗
function close_layer() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}
// 检测是否选择申报条件
function check_tiaojian() {
	var id = $("#dictionary_id").val();
	if (id == 0) {
		layer.msg("请选择申报条件！");
		return false;
	} else {
		var entrycondition_id = $("input[type='radio']:checked").val();// 获取选中的值
		if (typeof (entrycondition_id) == 'undefined') {
			layer.msg("请选择申报条件！");
			return false;
		} else {
			return true;
		}
	}

}
// 检查连续工龄
function check_wordyears() {
	var inWorkTime = $("#inWorkTime").val();
	if (inWorkTime!=null&&inWorkTime!="") {
		check_work_time();
		return true;
	} else {
		layer.msg("请选择参加工作时间！")
		return false;
	}
}
function check_feiyong_shuoming(){
	var feiyong_shuoming = $("#feiyong_shuoming").val();
	if (feiyong_shuoming!=null&&feiyong_shuoming!="") {
		return true;
	} else {
		layer.msg("请输入费用说明，没有则填无！");
		return false;
	}
}
//验证连续工龄
function check_work_time(){
	var inWorkTime=$("#inWorkTime").val();
	var date=getDate(inWorkTime);
	var birthdayYear = parseInt(date.getFullYear());
	var currentDate = new Date();
	var currentYear = parseInt(currentDate.getFullYear()); 
	$("#workYears").val(currentYear-birthdayYear);
}
//字符串转时间格式  
function getDate(strDate) {    
      var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,    
       function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');    
      return date;    
  }
// 保存学员报名信息
function save_entry_info(obj) {
	if (check_tiaojian()&&xueli_upload_pic()&&xuexin_beian_str_add()&&ercunzhao_str_add()&&zhengshu_dabao_str_add()
			&&zhichenglunwen_str_add()&&check_documentNumber()&&check_payMoney()&&check_jiangfei()
			&& check_entryUserName() && check_entryUserPhone()
			&& check_entryUserMail()&&check_cailiaofei()&&check_feiyong_shuoming()&&check_baokao_banci()) {
		$(obj).attr("disabled", "disabled");
		$(obj).val("提交中");
		var entrycondition_id = $("input[type='radio']:checked").val();
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/edu_entry/save_entry_info.jr",
			data : {
				'entrycondition_id' : entrycondition_id
			},
			success : function(data) {
				if (data == 1) {
					tanchuang('报名学员添加成功！');
					$('#myform')[0].reset();
					$(obj).removeAttr("disabled");
					$(obj).val("提交");
				} else {
					tanchuang('很遗憾，系统发生错误，报名学员添加失败！');
					$('#myform')[0].reset();
					$(obj).removeAttr("disabled");
					$(obj).val("提交");
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，系统发生错误，报名学员添加失败！');
				$('#myform')[0].reset();
				$(obj).removeAttr("disabled");
			}
		});
	}
}
//保存成考
function save_chengkao_entry_info(obj) {
	if (check_tiaojian()&&xueli_upload_pic()&&xuexin_beian_str_add()&&ercunzhao_str_add()&&zhengshu_dabao_str_add()
			&&zhichenglunwen_str_add()&&check_documentNumber() && check_wordyears()&&check_payMoney()&&check_jiangfei()
			&& check_entryUserName() && check_entryUserPhone()
			&& check_entryUserMail()&&check_cailiaofei()&&check_feiyong_shuoming()&&check_baokao_banci()) {
		$(obj).attr("disabled", "disabled");
		$(obj).val("提交中");
		var entrycondition_id = $("input[type='radio']:checked").val();
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/edu_entry/save_entry_info.jr",
			data : {
				'entrycondition_id' : entrycondition_id
			},
			success : function(data) {
				if (data == 1) {
					tanchuang('报名学员添加成功！');
					$('#myform')[0].reset();
					$(obj).removeAttr("disabled");
					$(obj).val("提交");
				} else {
					tanchuang('很遗憾，系统发生错误，报名学员添加失败！');
					$('#myform')[0].reset();
					$(obj).removeAttr("disabled");
					$(obj).val("提交");
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，系统发生错误，报名学员添加失败！');
				$('#myform')[0].reset();
				$(obj).removeAttr("disabled");
			}
		});
	}
}
// 修改学员信息
function update_entry_info(entryInfoId) {
		//var entrycondition_id = $("input[type='radio']:checked").val();
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/edu_entry/update_entry_info.jr?entryInfoId=" + entryInfoId,
			/*data : {
				'entrycondition_id' : entrycondition_id
			},*/
			success : function(data) {
				if (data == 1) {
					tanchuang('报名学员修改成功！');
				} else {
					tanchuang('很遗憾，系统发生错误，报名学员添加失败！');
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，系统发生错误，报名学员添加失败！');
			}
		});
}

function update_zhicheng_entry_info(entryInfoId) {
	//var entrycondition_id = $("input[type='radio']:checked").val();
if(xueli_upload_pic()&&xuexin_beian_str_add()&&ercunzhao_str_add()&&zhengshu_dabao_str_add()
		&&zhichenglunwen_str_add()){
	$("#myform").ajaxSubmit({
		type : 'POST',
		url : "/edu_entry/update_entry_info.jr?entryInfoId=" + entryInfoId,
		/*data : {
			'entrycondition_id' : entrycondition_id
		},*/
		success : function(data) {
			if (data == 1) {
				tanchuang('报名学员修改成功！');
			} else {
				tanchuang('很遗憾，系统发生错误，报名学员添加失败！');
			}
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			tanchuang('很遗憾，系统发生错误，报名学员添加失败！');
		}
	});
}
}
//修改学校学院
function update_entry_xuexiao_info(entryInfoId) {
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
	//var entrycondition_id = $("input[type='radio']:checked").val();
	$("#myform").ajaxSubmit({
		type : 'POST',
		url : "/edu_entry/update_entry_info.jr?entryInfoId=" + entryInfoId,
		data : {
			//'entrycondition_id' : entrycondition_id,
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
			if (data == 1) {
				tanchuang('报名学员修改成功！');
			} else {
				tanchuang('很遗憾，系统发生错误，报名学员添加失败！');
			}
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			tanchuang('很遗憾，系统发生错误，报名学员添加失败！');
		}
	});
}
// 检查考试费
function check_kaoshimoey() {
	var course_id=$("#course_id_plan").val();
	var kaoshimoey = $("#kaoshimoey").val();
	var jiaocaofei = $("#jiaocaofei").val();
	if (isNaN(kaoshimoey)) {
		layer.msg("考试费请输入数字！")
		return false;
	} else {
		if(course_id==19||course_id==20){
			var xuefei=$("#xuefei").val();
			var cailiaofei = $("#cailiaofei").val();
			var a = parseInt(kaoshimoey) + parseInt(jiaocaofei)
			+ parseInt(xuefei)+ parseInt(cailiaofei);
			$("#ying_pay").val(a);
		}else{
			var peixunfei = $("#peixunfei").val();
			var cailiaofei = $("#cailiaofei").val();
			var a = parseInt(kaoshimoey) + parseInt(jiaocaofei)
			+ parseInt(peixunfei)+ parseInt(cailiaofei);
			$("#ying_pay").val(a);
		}
		return true;
	}
}
// 检查教材费
function check_jiaocaofei() {
	var course_id=$("#course_id_plan").val();
	var kaoshimoey = $("#kaoshimoey").val();
	var jiaocaofei = $("#jiaocaofei").val();
	if (isNaN(jiaocaofei)) {
		layer.msg("教材费请输入数字！")
		return false;
	} else {
		if(course_id==19||course_id==20){
			var xuefei=$("#xuefei").val();
			var cailiaofei = $("#cailiaofei").val();
			var a = parseInt(kaoshimoey) + parseInt(jiaocaofei)
			+ parseInt(xuefei)+ parseInt(cailiaofei);
			$("#ying_pay").val(a);
		}else{
			var peixunfei = $("#peixunfei").val();
			var cailiaofei = $("#cailiaofei").val();
			var a = parseInt(kaoshimoey) + parseInt(jiaocaofei)
			+ parseInt(peixunfei)+parseInt(cailiaofei);
			$("#ying_pay").val(a);
		}
		return true;
	}
}
// 切换培训班
function get_zonge() {
	var course_id=$("#course_id_plan").val();
	var kaoshimoey = $("#kaoshimoey").val();
	var jiaocaofei = $("#jiaocaofei").val();
	var fan_fei=$("#fan_fei").val();
	if(course_id==19||course_id==20){
		var xuefei=$("#xuefei").val();
		var cailiaofei = $("#cailiaofei").val();
		var a = parseInt(kaoshimoey) + parseInt(jiaocaofei)
		+ parseInt(xuefei)+parseInt(cailiaofei);
		$("#ying_pay").val(a);
		var payMoney = $("#payMoney").val();
		if (payMoney != null && payMoney != "") {
			var qianfei = a - parseInt(payMoney);
			$("#qianfei").val(qianfei);
		}
	}else{
		var cailiaofei = $("#cailiaofei").val();
		var peixunfei = $("#peixunfei").val();
		var a = parseInt(kaoshimoey) + parseInt(jiaocaofei)
		+ parseInt(peixunfei)+parseInt(cailiaofei);
		$("#ying_pay").val(a);
		var payMoney = $("#payMoney").val();
		if (payMoney != null && payMoney != "") {
			var qianfei = a - parseInt(payMoney);
			$("#qianfei").val(qianfei);
		}
	}
}
//学费
function get_zonge_xuefei(obj){
	//var ying_pay=$("#ying_pay").val();
	var fan_fei=$("#fan_fei").val();
	var xuefei=$(obj).val();
	var cailiaofei = $("#cailiaofei").val();
	var kaoshimoey = $("#kaoshimoey").val();
	var jiaocaofei = $("#jiaocaofei").val();
	var a = parseInt(kaoshimoey) + parseInt(jiaocaofei)+ parseInt(cailiaofei)+parseInt(xuefei);
	$("#ying_pay").val(a);
	var payMoney = $("#payMoney").val();
	if (payMoney != null && payMoney != "") {
		var qianfei = a -parseInt(payMoney) -parseInt(fan_fei);
		$("#qianfei").val(qianfei);
	}
}
// 检查实付金额
function check_payMoney() {
	var payMoney = $("#payMoney").val();
	var yingjiao = $("#ying_pay").val();
	var jiangfei = $("#fan_fei").val();
	if (isNaN(payMoney)) {
		layer.msg("实付金额请输入数字！")
		return false;
	} else {
		var cailiaofei = $("#cailiaofei").val();
		if (parseInt(payMoney)> parseInt(yingjiao)+parseInt(cailiaofei) ) {
			layer.msg("实付金额不能大于应付金额！")
			return false;
		} else {
			var a =parseInt(yingjiao)+parseInt(cailiaofei);
			var qianfei = a - parseInt(payMoney)-parseInt(jiangfei);
			$("#qianfei").val(qianfei);
			return true;
		}
	}
}
//检查材料费
function check_cailiaofei(){
//	var yingjiao = $("#ying_pay").val();
//	var jiangfei = $("#fan_fei").val();
	var cailiaofei = $("#cailiaofei").val();
	if(cailiaofei!=""&&cailiaofei!=null){
		if (isNaN(cailiaofei)) {
			layer.msg("材料费金额请输入数字！")
			return false;
		} else {
//			var yingjiao = parseInt(yingjiao) +parseInt(cailiaofei);
//			$("#ying_pay").val(yingjiao);
			return true;
		}
	}else{
		layer.msg("请输入材料费，没有请填0！")
		return false;
	}
}
//降费
function check_jiangfei(){
	var yingjiao = $("#ying_pay").val();
	var jiangfei = $("#fan_fei").val();
	var cailiaofei = $("#cailiaofei").val();
	if (isNaN(jiangfei)) {
		layer.msg("降费金额请输入数字！")
		return false;
	} else {
		if (jiangfei > parseInt(yingjiao)+parseInt(cailiaofei)) {
			layer.msg("降费金额不能大于应付金额！")
			return false;
		} else {
			var paymoney = parseInt(yingjiao)+parseInt(cailiaofei) - parseInt(jiangfei);
			$("#payMoney").val(paymoney);
			return true;
		}
	}
}
// 检查学员姓名是否为空
function check_entryUserName() {
	var entryUserName = $("#entryUserName").val();
	if (entryUserName != null && entryUserName != "") {
		return true;
	} else {
		layer.msg("请输入用户姓名！");
		return false;
	}
}
// 检查用户手机号
function check_entryUserPhone() {
	var entryUserPhone = $("#entryUserPhone").val();
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
	if (entryUserPhone != null && entryUserPhone != "") {
		if (!myreg.test(entryUserPhone)) {
			layer.msg('请输入有效的手机号码！');
			return false;
		} else {
			return true;
		}
	} else {
		layer.msg("请输入您的手机号！");
		return false;
	}
}
// 检查邮箱
function check_entryUserMail() {
	var entryUserMail = $("#entryUserMail").val();
	var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
	if (entryUserMail != null && entryUserMail != "") {
		if (!search_str.test(entryUserMail)) {
			layer.msg("请输入正确格式的邮箱！");
			return false;
		} else {
			return true;
		}
	} else {
		layer.msg("请输入您的电子邮箱！");
		return false;
	}
}

$(function() {
	var company_name = $("#entryUserUnit").val();
	if (company_name == null || company_name == "") {
		$("#tip").hide();
	}
})

// 选择提示信息
function change_hint(divid) {
	$("#entryUserUnit").val($("#" + divid).text());
	$("#tip div").css("background-color", "white");
	$("#" + divid).css("background-color", "#8bc34a");
}
function change_hint1(divid) {
	$("#" + divid).css("background-color", "#fff");
}
// 双击隐藏提示框
function hide_change() {
	$("#tip").hide();
}
// 获取企业名称
function get_company() {
	var company_name = $("#entryUserUnit").val();
	if (company_name != null && company_name != "") {
		$.post("/edu_entry/get_company_json.jr", {
			'company_name' : company_name
		}, function(data) {
			$("#tip").show();
			$("#tip").html(data);
		});
	} else {
		$("#tip").empty();
		$("#tip").hide();
	}
}
//保存报考远程教育的学员
function save_entry_info_daxue(obj){
	if(check_tiaojian()&&xueli_upload_pic()&&check_documentNumber()
			&&check_entryUserName()&& check_entryUserPhone()&&check_payMoney()&&check_jiangfei()
			&& check_entryUserMail()&&check_cailiaofei()&&check_feiyong_shuoming()&&check_baokao_banci()){
		$(obj).attr("disabled", "disabled");
		$(obj).val("提交中");
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
		var entrycondition_id = $("input[type='radio']:checked").val();
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/edu_entry/save_entry_info.jr",
			data:{
				'entrycondition_id' : entrycondition_id,
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
				if (data == 1) {
					tanchuang('报名学员添加成功！');
					$('#myform')[0].reset();
					$(obj).removeAttr("disabled");
					$(obj).val("提交");
				} else {
					tanchuang('很遗憾，系统发生错误，报名学员添加失败！');
					$('#myform')[0].reset();
					$(obj).removeAttr("disabled");
					$(obj).val("提交");
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，系统发生错误，报名失败！');
				$('#myform')[0].reset();
				$(obj).removeAttr("disabled");
			}
		});
	}
}