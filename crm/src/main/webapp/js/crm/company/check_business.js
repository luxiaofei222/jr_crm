//返回企业库列表
function company_jump_page(page,limit,company_name,company_province,company_city,suoshuhangye) {
	jiazaidonghua();
	 $.post("/back_mybusiness/get_my_business_list.jr", {
		 'company_province':company_province,
		 'company_city':company_city,
		 'suoshuhangye':suoshuhangye,
		 'company_name':company_name,
		'pageNumber' : page,
		'limit' : limit
	}, function(data) {
		$("#conten_list").html(data);
	}) 
}

//企业详情翻页
function customer_jump_page(page,company_id,limit,company_page,company_limit,company_name,company_province,company_city,suoshuhangye){
	jiazaidonghua();
	 $.post("/back_mybusiness/check_business_detail.jr", {
		 'company_province':company_province,
		 'company_city':company_city,
		 'suoshuhangye':suoshuhangye,
		 'company_name':company_name,
		 'company_id':company_id,
		'pageNumber' : page,
		'limit' : limit,
		'company_page':company_page,
		'company_limit':company_limit
	}, function(data) {
		$("#conten_list").html(data);
	}) 
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
//编辑联系人信息
function to_update_customer(page,customer_id,company_id,limit,type){
	layer.open({
		type : 2,
		title : [ '编辑联系人信息' ],
		area : [ '700px', '700px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/crm_business/to_update_customer.jr?customer_id="+customer_id+"&type="+type
	});
}
//删除联系人
function delete_customer(page,customer_id,limit){
	layer.confirm("提示：您好，确定要删除这个联系人吗？",function(){
		layer.closeAll('dialog');
		$.post("/crm_business/delete_customer.jr",{
			'customer_id':customer_id
		},function(data){
			if(data==1){
				tanchuang("删除成功！");
			}else{
				tanchuang("移除失败！");
			}
		});
		})
}
//判断当前坐席的状态
function panduan_now_zuoxi_status(obj,customer_id,phone,zuoxi,is_ip){//单独查询话机状态
	if(is_ip==1){
	 if(zuoxi!=0){
	 $(obj).attr("disabled","disabled"); 
	 $.ajax({
         type: 'GET',  //这里用GET
         url: 'http://192.168.1.251/api.php',
         dataType: 'jsonp',  //类型
         data: {'f':'getExtenStatus','strAgent':zuoxi},
         jsonp: 'callback', //jsonp回调参数，必需
         async: false,
         success: function(result) {//返回的json数据
        	var str = unescape(result.replace(/\\u/g, "%u")); 
        	var json = eval('('+str+')');
        	var state=$.trim(json.data.status);
        	if(state==0){
        		call_phone(customer_id,phone,zuoxi);
        		$(obj).removeAttr("disabled");
        	}else if(state==8){
        		layer.msg("您的状态为振铃中，请挂机后重拨");
        	}else if(state==4){
        		layer.msg("您的状态为不在线");
        	}else if(state==1){
        		layer.msg("您的状态为通话中，请挂机后再拨！");
        	}else if(state==11){
        		layer.msg("您的状态为示忙");
        	}else{
        		layer.msg("系统发生错误！");
        	}
         }
     })
	 }else{
		 layer.msg("您还没有外呼的权限，请联系技术人员进行设置！");
	 }
	}else{
		layer.msg("你没有在公司上网哦！不能进行呼叫！");
	}
}
//手动输入外呼
function shoudongshuru_waihu(obj,company_id,zuoxi,is_ip){
	var phone=$("#waihu_phone").val();
	if(phone!=null&&phone!=""){
		if(is_ip==1){
			if(zuoxi!=0){
				 $(obj).attr("disabled","disabled"); 
				 $("#waihu").html("<i class='fa fa-phone'></i>通话中");
				 $.ajax({
			         type: 'GET',  //这里用GET
			         url: 'http://192.168.1.251/api.php',
			         dataType: 'jsonp',  //类型
			         data: {'f':'getExtenStatus','strAgent':zuoxi},
			         jsonp: 'callback', //jsonp回调参数，必需
			         async: false,
			         success: function(result) {//返回的json数据
			        	var str = unescape(result.replace(/\\u/g, "%u")); 
			        	var json = eval('('+str+')');
			        	var state=$.trim(json.data.status);
			        	if(state==0){
			        		$.ajax({
			        			type : "POST",// 请求方式
			        			data:{'company_id':company_id,'phone':phone},
			        			url : "/crm_call/get_customer_id.jr",// 地址，就是action请求路径
			        			dataType : "json",// 数据类型text xml json script jsonp
			        			success : function(msg) { // 返回的参数就是 action里面所有的有get和set方法的参数
			        				call_phone(msg.customer_id,phone,zuoxi);
			        				$("#waihu").removeAttr("disabled");
	        	        			$("#waihu").html("<i class='fa fa-phone'></i>外呼");
			        			}
			        		});
			        	}else if(state==8){
			        		layer.msg("您的状态为振铃中，请挂机后重拨");
			        	}else if(state==4){
			        		layer.msg("您的状态为不在线");
			        	}else if(state==1){
			        		layer.msg("您的状态为通话中，请挂机后再拨！");
			        	}else if(state==11){
			        		layer.msg("您的状态为示忙");
			        	}else{
			        		layer.msg("系统发生错误！");
			        	}
			         }
			     })
			}else{
				 layer.msg("您还没有外呼的权限，请联系技术人员进行设置！");
			 }
		}else{
			layer.msg("你没有在公司上网哦！不能进行呼叫！");
		}
	}else{
		layer.msg("请输入您要拨打的电话！");
	}
}
//点击拨号
var oTimer = null;
function call_phone(customer_id,phone,zuoxi){
		oTimer = null;
	 //var j = $("form").serializeArray();//序列化name/value
	 	dengdai();
		 $.ajax({
	         type: 'GET',  //这里用GET
	         url: 'http://192.168.1.251/api.php',
	         dataType: 'jsonp',  //类型
	         data: {'f':'OnClick','src':zuoxi,'dst':phone},//上线后更改为 phone
	         jsonp: 'callback', //jsonp回调参数，必需
	         async: false,
	         success: function(result) {//返回的json数据
	        	var str = unescape(result.replace(/\\u/g, "%u")); 
	        	var json = eval('('+str+')');
	        	if($.trim(json.status)=='true'){
	        		//如果电话接通开始记录通话记录
	        		 oTimer = setInterval("huoqu_tanping_api('"+zuoxi+"',"+customer_id+")", 1000);
	        	}else{
	        		 $("#dengdai").empty();
	        	}
	         }
	     }) 
}
//添加联系人弹窗
function to_add_lianxiren(company_id){
	layer.open({
		type : 2,
		title : [ '添加联系人信息' ],
		area : [ '700px', '700px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/crm_business/to_add_lianxiren.jr?company_id="+company_id
	});
}

function dengdai(){
	 $("#dengdai").html('<div class="loader-inner ball-spin-fade-loader"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><p style="color: #f15151; position: absolute; top: 45px; left: -35px;">等待电话接听</p></div>');
} 
//获取弹屏api
function huoqu_tanping_api(zuoxi,customer_id){
	 $.ajax({
         type: 'GET',  //这里用GET
         url: 'http://192.168.1.251/api.php',
         dataType: 'jsonp',  //类型
         data: {'f':'getExtenStatus','strAgent':zuoxi},
         jsonp: 'callback', //jsonp回调参数，必需
         async: false,
         success: function(result) {//返回的json数据
        	var str = unescape(result.replace(/\\u/g, "%u")); 
        	var json = eval('('+str+')');
        	var state=$.trim(json.data.status);
        	if(state!=0){
        		$("#dengdai").empty();
        		 $.ajax({
        	         type: 'GET',  //这里用GET
        	         url: 'http://192.168.1.251/api.php', //获取弹屏接口
        	         dataType: 'jsonp',  //类型
        	         data: {'f':'popEvent','strAgent':zuoxi},//上线后更改为 phone
        	         jsonp: 'callback', //jsonp回调参数，必需
        	         async: false,
        	         success: function(result) {//返回的json数据
        	        	var str = unescape(result.replace(/\\u/g, "%u")); 
        	        	var json = eval('('+str+')');
        	        		if(json.status){
        	        			var aToStr=JSON.stringify(json.data); 
        	        			$.post("/crm_call/save_business_call.jr",{
        	        				'str':aToStr,
        	        				'customer_id':customer_id
        	        			},function(data){
        	        				if(data!=0){
        	        					if(data!=null&&data!=""){
        	        						clearInterval(oTimer);
        	        						layer.open({
            	        	        			type : 2,
            	        	        			title : [ '联系人弹屏' ],
            	        	        			maxmin: true, //开启最大化最小化按钮
            	        	        			area : [ '1200px', '650px' ],
            	        	        			shadeClose : false, //点击遮罩关闭
            	        	        			shade: false,
            	        	        			content : "/crm_business/to_call_tanping.jr?customer_id="+customer_id+"&pageNumber=1&limit=15&record_id="+data,
            	        	        			end: function () {
            	        	        				baocun_recall();
            	        	        	          }
            	        	        		});
        	        					}else{
        	        						clearInterval(oTimer);
        	        						layer.msg("通话记录保存失败,无法弹屏");
        	        					}
        	        				}else{
        	        					clearInterval(oTimer);
        	        					layer.msg("通话记录保存失败，系统发生错误");
        	        				}
        	        			})
        	        	}
        	         }
        	     })
        	}else{
        		$("#dengdai").empty();
        	}
         }
     })
}
//保存通话记录
function baocun_recall(){
	//$("#dengdai").empty();
	//clearInterval(oTimer);//停止
	$.ajax({  
             url: '/crm_call/get_uucall_id.jr',  
             type: 'GET',  
             dataType: 'json', 
             success:function(data){
		if(typeof(data.uid)!="undefined"&&data.uid!=null){
			 $.ajax({
    	         type: 'GET',  //这里用GET
    	         url: 'http://192.168.1.251/api.php', //获取通话记录
    	         dataType: 'jsonp',  //类型
    	         data: {'f':'getCallInfo','uniqueid':data.uid,'pop_time':data.time},//上线后更改为 phone
    	         jsonp: 'callback', //jsonp回调参数，必需
    	         async: false,
    	         success: function(result) {//返回的json数据
    	        	var str = unescape(result.replace(/\\u/g, "%u")); 
    	        	if(str.length>=2){
    	        		var json = eval('('+str+')');
        	        	$.post("/crm_call/update_business_call.jr",{
        	        		'record_id':data.record_id,
        	        		'record_time':json.billsec,
        	        		'sound_file':json.userfield,
        	        		'call_state':json.disposition,
        	        		'call_time_str':json.calldate
        	        	},function(data){
        	        		if(data==1){
        	        			layer.msg("通话结束！");
        	        			$("#waihu").removeAttr("disabled");
        	        			$("#waihu").html("<i class='fa fa-phone'></i>外呼");
        	        		}else{
        	        			layer.msg("该电话无法接通，通话失败！");
        	        		}
        	        	})
    	        	}else{
    	        		layer.msg("电话未接通，请稍后再试！");
    	        	}
    	         }
    	     })
		}else{
			layer.msg("这个问题需要刷新页面，不要再点了！");
		}
	}
	  })
}