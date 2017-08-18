//刷新通话记录
function refresh_business_record(){
	jiazaidonghua();
	$.post("/crm_call/get_business_call_list.jr",{
		'pageNumber' : 1,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}
$(function(){
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  var start = {
			festival: true
		    ,istoday: true 
		  };
		 document.getElementById('end_time').onclick = function(){
		    start.elem = this;
		    laydate(start);
		  }
		  document.getElementById('start_time').onclick = function(){
			    start.elem = this;
			    laydate(start);
			  }
		});
})

//验证时长的输入类型
function check_start_shichang(){
	var start_shichang=$("#start_shichang").val();
	if(start_shichang!=""&&start_shichang!=null){
		if(isNaN(start_shichang)){
			layer.msg("时长请输入数字！")
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}
function check_end_shichang(){
	var end_shichang=$("#end_shichang").val();
	if(end_shichang!=""&&end_shichang!=null){
		if(isNaN(end_shichang)){
			layer.msg("时长请输入数字！")
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}
//查询通话记录
function chaxun_call_record(obj,limit){
	if(check_start_shichang()&&check_end_shichang()){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("查询中");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/crm_call/get_business_call_list.jr",
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
}
//导出通话记录
function down_load_excel(obj){
	if(check_start_shichang()&&check_end_shichang()){//导出通话记录
	$(obj).html("<i class='fa fa-cloud-download'></i>导出通话记录中");
	$("#myform").ajaxSubmit({
		type : 'POST',
		url : "/crm_call/get_down_load_record_url.jr",
		success : function(data) {
			location.href=data;
			$(obj).html("<i class='fa fa-cloud-download'></i>导出通话记录");
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			tanchuang('查询失败');
		}
	});
	}
}
//输入页码并跳转
function record_tiaozhuan_page(page,limit,start_time,end_time,employee_id ,beijiao,zhujiao ,record_state,record_type ,start_shichang ,end_shichang){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			$.post("/crm_call/get_business_call_list.jr",{
				'pageNumber' : page_num,
				'limit' : limit,
				'start_time' : start_time,
				'end_time' : end_time,
				'employee_id' : employee_id,
				'beijiao' : beijiao,
				'zhujiao' : zhujiao,
				'record_state' : record_state,
				'record_type' : record_type,
				'start_shichang' : start_shichang,
				'end_shichang' : end_shichang
			},function(data){
				$("#conten_list").html(data);
			})
		}
	}else{
		layer.msg("请输入数字！")
	}
}
//点击进入下一页
function record_jump_page(page,limit,start_time,end_time,employee_id ,beijiao,zhujiao ,record_state,record_type ,start_shichang ,end_shichang){
	$.post("/crm_call/get_business_call_list.jr",{
		'pageNumber' : page,
		'limit' : limit,
		'start_time' : start_time,
		'end_time' : end_time,
		'employee_id' : employee_id,
		'beijiao' : beijiao,
		'zhujiao' : zhujiao,
		'record_state' : record_state,
		'record_type' : record_type,
		'start_shichang' : start_shichang,
		'end_shichang' : end_shichang
	},function(data){
		$("#conten_list").html(data);
	})
}
//试听录音文件  
function listen_the_tape(record_id,uid,time,is_ip){
	if(is_ip==1){
		layer.open({
			type : 2,
			title : [ '试听录音' ],
			area : [ '500px', '200px' ],
			shadeClose : false, //点击遮罩关闭
			content : "/crm_call/listen_the_tape.jr?uniqueid="+uid+"&date="+time+"&record_id="+record_id
		});
	}else{
		layer.msg("您不在公司哦，无法试听！")
	}
}
//查看通话记录详情
function check_record_detail(record_id){
	layer.open({
		type : 2,
		title : [ '查看通话记录' ],
		area : [ '700px', '500px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/crm_call/check_record_detail.jr?record_id="+record_id
	});
}

//导出通话录音
function down_load_tage(){
	layer.open({
		type : 2,
		title : [ '导出通话录音' ],
		area : [ '550px', '400px' ],
		shadeClose : false, //点击遮罩关闭
		content : "/crm_call/down_load_tage.jr"
	});
}
//同步信息
function tongbu_call_record(obj,record_id,uid,time,is_ip){
	if(is_ip==1){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("同步中");
		$.ajax({
	        type: 'GET',  //这里用GET
	        url: 'http://192.168.1.251/api.php', //获取通话记录
	        dataType: 'jsonp',  //类型
	        data: {'f':'getCallInfo','uniqueid':uid,'pop_time':time},//上线后更改为 phone
	        jsonp: 'callback', //jsonp回调参数，必需
	        async: false,
	        success: function(result) {//返回的json数据
	       	var str = unescape(result.replace(/\\u/g, "%u")); 
	       	if(str.length>=2){
	       		var json = eval('('+str+')');
		        	$.post("/crm_call/update_business_call.jr",{
		        		'record_id':record_id,
		        		'record_time':json.billsec,
		        		'sound_file':json.userfield,
		        		'call_state':json.disposition
		        	},function(data){
		        		if(data==1){
		        			$(obj).html("同步信息");
							$(obj).removeAttr("disabled");
		        			layer.msg("同步成功！")
		        		}else{
		        			$(obj).html("同步信息");
							$(obj).removeAttr("disabled");
		        			layer.msg("同步失败");
		        		}
		        	})
	       	}else{
	       		$(obj).html("同步信息");
				$(obj).removeAttr("disabled");
				layer.msg("同步失败");
	       	}
	        }
	    })
	}else{
		layer.msg("您不在公司哦，无法操作！");
	}
}