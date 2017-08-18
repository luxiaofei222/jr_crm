//判断当前坐席的状态
function panduan_now_zuoxi_status(obj,consult_id,phone,zuoxi,is_ip){//单独查询话机状态
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
        		call_phone(consult_id,phone,zuoxi);
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
//点击拨号
var oTimer = null;
function call_phone(consult_id,phone,zuoxi){
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
	        		 oTimer = setInterval("huoqu_tanping_api('"+zuoxi+"',"+consult_id+")", 1000);
	        	}else{
	        		 $("#dengdai").empty();
	        	}
	         }
	     }) 
}

function dengdai(){
	 $("#dengdai").html('<div class="loader-inner ball-spin-fade-loader"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><p style="color: #f15151; position: absolute; top: 45px; left: -35px;">等待电话接听...</p></div>');
} 
//获取弹屏api
function huoqu_tanping_api(zuoxi,consult_id){
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
        	        			$.post("/crm_conult/save_net_conult_call.jr",{
        	        				'str':aToStr,
        	        				'consult_id':consult_id
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
            	        	        			content : "/crm_conult/to_call_tanping.jr?consult_id="+consult_id+"&pageNumber=1&limit=15&record_id="+data,
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
    	        	console.log(str);
    	        	if(str.length>=2){
    	        		var json = eval('('+str+')');
        	        	$.post("/crm_conult/update_conult_call.jr",{
        	        		'record_id':data.record_id,
        	        		'record_time':json.billsec,
        	        		'sound_file':json.userfield,
        	        		'call_state':json.disposition,
        	        		'call_time_str':json.calldate
        	        	},function(data){
        	        		if(data==1){
        	        			layer.msg("通话结束！");
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