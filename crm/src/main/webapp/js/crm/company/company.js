$(function(){
	$(".selectall").click(function(){
		$(".checkbox").each(function(){
	    $(this).prop("checked",true);
		  })
		})
	$(".selectno").click(function(){
		$(".checkbox").each(function(){
	    $(this).prop("checked",false);
		  })
		})
		
	$(".pagination-dropdown").click(function(){
		$(this).children(".dropdown-menu").toggle();
	  })
	}) 
	//添加企业信息
	function to_add_company(page,limit) {
		layer.open({
			type : 2,
			title : [ '添加企业信息' ],
			area : [ '900px', '500px' ],
			shadeClose : false, //点击遮罩关闭
			content : "/crm_business/to_add_business.jr"
		});
	}
	//编辑企业信息
	function update_company_layer(page,company_id,limit){
		layer.open({
			type : 2,
			title : [ '编辑企业信息' ],
			area : [ '900px', '500px' ],
			shadeClose : false, //点击遮罩关闭
			content : "/crm_business/to_update_business.jr?company_id="+company_id
		});
	}
	//编辑联系人信息
	function to_update_customer(customer_id,company_id,limit){
		layer.open({
			type : 2,
			title : [ '编辑联系人信息' ],
			area : [ '700px', '700px' ],
			shadeClose : false, //点击遮罩关闭
			content : "/crm_business/to_update_customer.jr?customer_id="+customer_id
		});
	}
	//查看联系人详情
	function to_check_lianxiren(customer_id){
		layer.open({
			type : 2,
			title : [ '查看联系人信息' ],
			area : [ '1200px', '700px' ],
			shadeClose : true, //点击遮罩关闭
			content : "/crm_business/to_check_lianxiren.jr?customer_id="+customer_id+"&pageNumber=1&limit=15"
		});
	}
	//添加联系人弹窗
	function to_add_lianxiren(company_id,limit){
		layer.open({
			type : 2,
			title : [ '添加联系人信息' ],
			area : [ '700px', '700px' ],
			shadeClose : false, //点击遮罩关闭
			content : "/crm_business/to_add_lianxiren.jr?company_id="+company_id
		});
	}
	//企业信息列表翻页-上一页 下一页
	function company_jump_page(page,limit,employee_name,company_name) {
		jiazaidonghua();
		 $.post("/crm_business/get_business.jr", {
			'pageNumber' : page,
			'employee_name':employee_name,
			'limit' : limit
		}, function(data) {
			$("#conten_list").html(data);
		}) 
	}
	//输入页数
	function company_tiaozhuan_page(page,limit,employee_name,company_name) {
		var page_num=$("#page_num").val();
		if(!isNaN(page_num)){
			if(page<page_num||page_num<0){
				layer.msg("你输入的页数不存在！")
			}else{
				jiazaidonghua();
				 $.post("/crm_business/get_business.jr", {
					 'employee_name':employee_name,
						'pageNumber' : page_num,
						'limit' : limit
					}, function(data) {
						$("#conten_list").html(data);
					}) 
			}
		}else{
			layer.msg("请输入数字！")
		}
	}
	//删除联系人
	function delete_custome(customer_id,company_id){
		layer.confirm("提示：您好，确定要删除吗？",function(){
			layer.closeAll('dialog');
			jiazaidonghua();
			$.post("/crm_business/delete_customer.jr",{
				'customer_id':customer_id
			},function(data){
				if(data==1){
					tanchuang("删除成功！");
					customer_jump_page(1,company_id);
				}else{
					tanchuang("删除失败！");
				}
			})
			})
	}
	//企业详情翻页
	function customer_jump_page(page,company_id){
		jiazaidonghua();
		 $.post("/crm_business/check_business_detail.jr", {
			 'company_id':company_id,
			'pageNumber' : page,
			'limit' : 20
		}, function(data) {
			$("#conten_list").html(data);
		}) 
	}
	//查看企业信息详情
	function check_business_ditail(company_id){
		jiazaidonghua();
		 $.post("/crm_business/check_business_detail.jr", {
			 	'company_id':company_id,
				'pageNumber' : 1,
				'limit' : 20
			}, function(data) {
				$("#conten_list").html(data);
			}) 
	}
	//查询关键字
	function seacher_company(limit) {
		var drop_menu = $('#select_val').val(); //获取查询下拉菜单的值
		var drop_menu_val = $('#check_val').val(); //获取输入框里面的值
		if (drop_menu == "company_name") {
			jiazaidonghua();
			$.post("/crm_business/get_business.jr", {
				'company_name' : drop_menu_val,
				'pageNumber' : 1,
				'limit' : limit
			}, function(data) {
				if (data != null) {
					$('#conten_list').html(data);
				}
			});
		} else if (drop_menu == "employee_name") {
				jiazaidonghua();
				$.post("/crm_business/get_business.jr", {
					'employee_name' : drop_menu_val,
					'pageNumber' : 1,
					'limit' : limit
				}, function(data) {
					$("#conten_list").html(data);
				})
		}else if (drop_menu == "0") {
			layer.confirm("提示：您好，请选择你需要的查询条件！", function() {
				layer.closeAll('dialog');
				jiazaidonghua();
				$.post("/crm_business/get_business.jr", {
					'pageNumber' : 1,
					'limit' : limit
				}, function(data) {
					$("#conten_list").html(data);
				})
			});
		}
	}
	//删除企业信息
	function delete_company(company_id,limit) {
		layer.confirm("提示：您好，确定要删除吗？", function() {
			layer.closeAll('dialog');
			jiazaidonghua();
			$.post("/crm_business/delete_company.jr", {
				'company_id' : company_id
			}, function(data) {
				if (data == 1) {
					tanchuang("删除成功！");
					company_jump_page(1,limit);
				} else {
					tanchuang("删除失败！");
				}
			});
		})
	}
	//导入数据
	function upload_business(page,limit){
		layer.open({
			type : 2,
			title : [ '导入企业信息' ],
			area : [ '600px', '400px' ],
			shadeClose : false, //点击遮罩关闭
			content : "/crm_business/to_upload_business.jr"
		});
	}
	//导出企业信息
	function daochu_business() {
		location.href="/donwload/export_business_excel.jr"
	}
	
	//信息弹窗
	function tanchuang(content) {
		layer.alert(content, {
			icon : 1,
			skin : 'layer-ext-moon'
		})
	}
	//待选企业库列表
	function mycompany_jump_page(page,limit,company_name,company_province,company_city,suoshuhangye,employee_name) {
		jiazaidonghua();
		 $.post("/back_mybusiness/get_select_business.jr", {
			 'company_province':company_province,
			 'company_city':company_city,
			 'suoshuhangye':suoshuhangye,
			 'company_name':company_name,
			 'employee_name':employee_name,
			'pageNumber' : page,
			'limit' : limit
		}, function(data) {
			$("#conten_list").html(data);
		}) 
	}
	//加入到我的企业
	function add_my_company(obj,company_id,page,limit){
		$(obj).parent().html("--");
		$.post("/back_mybusiness/save_my_company.jr", {
			'company_id' : company_id
		}, function(data) {
			if (data == 1) {
				//$(obj).parent().html("--");
				tanchuang("添加成功！");
//				mycompany_jump_page(page,limit);
			} else if(data==2) {
				tanchuang("您添加的数量已经超过限额！");
			} else {
				tanchuang("系统发生错误，添加失败！");
			}
		});
	}
	//批量加入我的企业
	function add_my_company_piliang(page,limit,company_name ,company_province ,company_city ,suoshuhangye,employee_name ){
		var len = $("input:checkbox[name=companye_id]:checked").length;
		if(len>0){
		var str = "";
		$('input:checkbox[name=companye_id]:checked').each(function(i) {
			if (0 == i) {
				str = $(this).val();
			} else {
				str += ","+$(this).val();
			}
		});
		$.post("/back_mybusiness/save_my_company.jr", {
			'str' : str
		}, function(data) {
			if (data == 1) {
				tanchuang("添加成功！");
				mycompany_jump_page(page,limit,company_name ,company_province ,company_city ,suoshuhangye,employee_name);
			} else if(data==2) {
				tanchuang("您添加的数量已经超过限额！");
			} else {
				tanchuang("系统发生错误，添加失败！");
			}
		});
		}else{
			tanchuang("请选择至少一条记录！");
		}
	}
	//我的企业库跳转
	function my_company_tiaozhuan_page(page,limit,company_name,company_province,company_city,suoshuhangye,employee_name) {
		var page_num=$("#page_num").val();
		if(!isNaN(page_num)){
			if(page<page_num||page_num<0){
				layer.msg("你输入的页数不存在！")
			}else{
				jiazaidonghua();
				 $.post("/back_mybusiness/get_select_business.jr", {
					 'company_province':company_province,
					 'company_city':company_city,
					 'suoshuhangye':suoshuhangye,
					 'employee_name':employee_name,
					 	'company_name':company_name,
						'pageNumber' : page_num,
						'limit' : limit
					}, function(data) {
						$("#conten_list").html(data);
					}) 
			}
		}else{
			layer.msg("请输入数字！")
		}
	}
	
	//查询关键字
	function seacher_my_company(limit) {
		var drop_menu = $('#select_val').val(); //获取查询下拉菜单的值
		var drop_menu_val = $('#check_val').val(); //获取输入框里面的值
		if (drop_menu == "company_name") {
			jiazaidonghua();
			$.post("/back_mybusiness/get_select_business.jr", {
				'company_name' : drop_menu_val,
				'pageNumber' : 1,
				'limit' : limit
			}, function(data) {
				if (data != null) {
					$('#conten_list').html(data);
				}
			});
		} else if (drop_menu == "company_province") {
			jiazaidonghua();
			$.post("/back_mybusiness/get_select_business.jr", {
				'company_province' : drop_menu_val,
				'pageNumber' : 1,
				'limit' : limit
			}, function(data) {
				if (data != null) {
					$('#conten_list').html(data);
				}
			});
		} else if (drop_menu == "company_city") {
			jiazaidonghua();
			$.post("/back_mybusiness/get_select_business.jr", {
				'company_city' : drop_menu_val,
				'pageNumber' : 1,
				'limit' : limit
			}, function(data) {
				if (data != null) {
					$('#conten_list').html(data);
				}
			});
		} else if (drop_menu == "hangye") {
			jiazaidonghua();
			$.post("/back_mybusiness/get_select_business.jr", {
				'suoshuhangye' : drop_menu_val,
				'pageNumber' : 1,
				'limit' : limit
			}, function(data) {
				if (data != null) {
					$('#conten_list').html(data);
				}
			});
		} else if (drop_menu == "yuangong") {
			jiazaidonghua();
			$.post("/back_mybusiness/get_select_business.jr", {
				'employee_name' : drop_menu_val,
				'pageNumber' : 1,
				'limit' : limit
			}, function(data) {
				if (data != null) {
					$('#conten_list').html(data);
				}
			});
		}else if (drop_menu == "0") {
			layer.confirm("提示：您好，请选择你需要的查询条件！", function() {
				layer.closeAll('dialog');
				jiazaidonghua();
				$.post("/back_mybusiness/get_select_business.jr", {
					'pageNumber' : 1,
					'limit' : limit
				}, function(data) {
					$("#conten_list").html(data);
				})
			});
		}
	}