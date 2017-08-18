//刷新通话记录
function refresh_business_record(){
	jiazaidonghua();
	$.post("/crm_call/get_bumen_business_call_list.jr",{
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
			url : "/crm_call/get_bumen_business_call_list.jr",
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
//输入页码并跳转
function record_tiaozhuan_page(page,limit,start_time,end_time ,beijiao,zhujiao ,record_state,record_type ,start_shichang ,end_shichang){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			$.post("/crm_call/get_bumen_business_call_list.jr",{
				'pageNumber' : page_num,
				'limit' : limit,
				'start_time' : start_time,
				'end_time' : end_time,
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
function record_jump_page(page,limit,start_time,end_time ,beijiao,zhujiao ,record_state,record_type ,start_shichang ,end_shichang){
	$.post("/crm_call/get_bumen_business_call_list.jr",{
		'pageNumber' : page,
		'limit' : limit,
		'start_time' : start_time,
		'end_time' : end_time,
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