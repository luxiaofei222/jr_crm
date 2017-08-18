//添加员工信息
function to_add_employee(){
	 layer.open({
		  type: 2,
		  title: ['添加员工账号'],
		  area: ['650px', '600px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_employee/to_add_employee.jr"
		  });
}
//去修改员工信息
function to_update_employee(employee_id){
	 layer.open({
		  type: 2,
		  title: ['修改员工账号'],
		  area: ['650px', '600px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_employee/to_update_employee.jr?employee_id="+employee_id
		  });
}
//员工列表翻页-上一页 下一页
function employee_jump_page(page,limit,employee_name ,employee_sex ,employee_state ,role_id ,bumen_id ){
	jiazaidonghua();
	$.post("/back_employee/get_employee_main.jr",{
		'employee_name':employee_name,
		'employee_sex':employee_sex,
		'employee_state':employee_state,
		'role_id':role_id,
		'bumen_id':bumen_id,
		'pageNumber' : page,
		'limit' : limit
	},function(data){
		$("#conten_list").html(data);
	})
}
//筛选条件
function shaixuan_tioajian(obj,limit){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("筛选中");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/back_employee/get_employee_main.jr",
			data:{
				'pageNumber':1,
				'limit':limit
			},
			success : function(data) {
				$("#conten_list").html(data);
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('查询失败');
			}
		});
}
//删除员工信息
function delete_employee(employee_id,page,limit){
	layer.confirm("提示：您好，确定要删除吗？",function(){
	layer.closeAll('dialog');
	jiazaidonghua();
	$.post("/back_employee/delete_employee.jr",{
		'employee_id':employee_id
	},function(data){
		if(data==1){
			tanchuang("删除成功！");
			employee_jump_page(page,limit);
		}else{
			tanchuang("删除失败！");
		}
	})
	})
}
//信息弹窗
function tanchuang(content){
	layer.alert(content, {
		  icon: 1,
		  skin: 'layer-ext-moon' 
		})
}
$(function(){
	$('#s2id_role_select_val').hide();//根据角色搜索
	$('#s2id_organ_select_val').hide();//根据岗位搜索
})
//切换搜索条件
function change_select(){
	var drop_menu=$('#select_val').val();//获取查询下拉菜单的值
	if(drop_menu=="name"){
		$('#check_val').show(); 
		$('#s2id_role_select_val').hide();//根据角色搜索
		$('#s2id_organ_select_val').hide();//根据岗位搜索
	}else if(drop_menu=="role"){
		$('#s2id_role_select_val').show();//根据角色搜索
		$('#check_val').hide(); 
		$('#s2id_organ_select_val').hide();//根据岗位搜索
	}else if(drop_menu=="organ"){
		$('#check_val').hide(); 
		$('#s2id_role_select_val').hide();//根据角色搜索
		$('#s2id_organ_select_val').show();//根据岗位搜索
	}else{
		$('#check_val').show(); 
		$('#s2id_role_select_val').hide();//根据角色搜索
		$('#s2id_organ_select_val').hide();//根据岗位搜索
	}
}
//禁用或者启用账号
function jinyong_employee(employee_id,type){
	$.post("/back_employee/jinyong_employee.jr",{
		'employee_id':employee_id,
		'type':type
	},function(data){
		if(data==1){
			tanchuang("修改成功！");
//			employee_jump_page(1);
		}else{
			tanchuang("修改失败！");
		}
	})
}
//设置坐席
function to_set_zuoxi(employee_id,is_ip){
	if(is_ip!=0){
 	  $("#zuoxi_list").css("display","block");
 	 $("#zuoxi_list").html('<div class="loader-inner ball-spin-fade-loader"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><p style="color: #fff; position: absolute; top: 45px; left: -35px;">正在打开...</p></div>');
	  $.ajax({
	         type: 'GET',  //这里用GET
	         url: 'http://192.168.1.251/api.php',
	         dataType: 'jsonp',  //类型
	         data: {'f':'getAllExtenStatus'},//获取所有分机号码以及状态
	         jsonp: 'callback', //jsonp回调参数，必需
	         async: false,
	         success: function(result) {//返回的json数据
	        	// var str = unescape(result.replace(/\\u/g, "%u")); 
	        	var json = eval('('+result+')');
	        	var aToStr=JSON.stringify(json.list); 
	        	if($.trim(json.status)=='true'){
	        		$.post("/back_employee/get_zuoxi_list.jr",{
	        			'employee_id':employee_id,
	        			'zuoxi_list':aToStr
	        		},function(data){
	        			$("#zuoxi_list").html(data);
	        		})
	        	}else{
	        		layer.msg("第三方系统发生错误，请联系技术部！");
	        	}
	         }
	     })
	}else{
		layer.msg("您没有在公司上网，无法设置！");
	}
}