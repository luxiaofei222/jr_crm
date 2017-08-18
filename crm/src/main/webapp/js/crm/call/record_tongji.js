//刷新通话记录
function refresh_business_record(page,limit,start_time,end_time ,employee_id,record_type,bumen_id){
	jiazaidonghua();
	$.post("/crm_call/get_record_tongji.jr",{
		'pageNumber' : page_num,
		'limit' : limit,
		'start_time' : start_time,
		'end_time' : end_time,
		'employee_id' : employee_id,
		'record_type' : record_type,
		'bumen_id':bumen_id
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

//查询通话记录
function chaxun_call_record(obj,limit){
		$(obj).attr({"disabled":"disabled"});
		$(obj).html("查询中");
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/crm_call/get_record_tongji.jr",
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
//输入页码并跳转
function record_tiaozhuan_page(page,limit,start_time,end_time ,employee_id,record_type,bumen_id){
	var page_num=$("#page_num").val();
	if(!isNaN(page_num)){
		if(page<page_num||page_num<0){
			layer.msg("你输入的页数不存在！")
		}else{
			jiazaidonghua();
			$.post("/crm_call/get_record_tongji.jr",{
				'pageNumber' : page_num,
				'limit' : limit,
				'start_time' : start_time,
				'end_time' : end_time,
				'employee_id' : employee_id,
				'record_type' : record_type,
				'bumen_id':bumen_id
			},function(data){
				$("#conten_list").html(data);
			})
		}
	}else{
		layer.msg("请输入数字！")
	}
}
//点击进入下一页
function record_jump_page(page,limit,start_time,end_time ,employee_id,record_type,bumen_id){
	jiazaidonghua();
	$.post("/crm_call/get_record_tongji.jr",{
		'pageNumber' : page,
		'limit' : limit,
		'start_time' : start_time,
		'end_time' : end_time,
		'employee_id' : employee_id,
		'record_type' : record_type,
		'bumen_id':bumen_id
	},function(data){
		$("#conten_list").html(data);
	})
}
//图标排序
function to_check_echart(page,limit,start_time,end_time ,employee_id,record_type,bumen_id){
	$.post("/crm_call/to_check_echart.jr",{
		'pageNumber' : page,
		'limit' : limit,
		'start_time' : start_time,
		'end_time' : end_time,
		'employee_id' : employee_id,
		'record_type' : record_type,
		'bumen_id':bumen_id
	},function(data){
		$("#conten_list").html(data);
	})
}