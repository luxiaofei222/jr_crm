var oTimer = null;
$(function() {
	$.get("/crm_call/get_now_zuoxi.jr", function(data) {
		if (data.zuoxi != 0 && data.is_ip != 0) {// 上线应改成is_ip！=0
			// 如果当前坐席有分机号就2秒查询一次弹屏信息
			oTimer = setInterval("check_zuoxi_state('" + data.zuoxi + "')",
					2000);
		}
	})
})
// 查询坐席状态
function check_zuoxi_state(zuoxi) {
	$.ajax({
		type : 'GET', // 这里用GET
		url : 'http://192.168.1.251/api.php',
		dataType : 'jsonp', // 类型
		data : {
			'f' : 'getExtenStatus',
			'strAgent' : zuoxi
		},
		jsonp : 'callback', // jsonp回调参数，必需
		async : false,
		success : function(result) {// 返回的json数据
			 var str = unescape(result.replace(/\\u/g, "%u"));
			 var json = eval('(' + str + ')');
			var state = $.trim(json.data.status);
			if (state == 8) {
				get_call_in_tanping(zuoxi);
				// layer.msg("您的状态为振铃中，请挂机后重拨");
			}
		}
	})
}
// 获取来电弹屏
function get_call_in_tanping(zuoxi) {
	$.ajax({
		type : 'GET', // 这里用GET
		url : 'http://192.168.1.251/api.php', // 获取弹屏接口
		dataType : 'jsonp', // 类型
		data : {
			'f' : 'popEvent',
			'strAgent' : zuoxi
		},// 上线后更改为 phone
		jsonp : 'callback', // jsonp回调参数，必需
		async : false,
		success : function(result) {// 返回的json数据
			var str = unescape(result.replace(/\\u/g, "%u"));
			var json = eval('(' + str + ')');
			if (json.status) {
				var aToStr = JSON.stringify(json.data);
				var keyHex = CryptoJS.enc.Utf8.parse("record_data");
				var encrypted = CryptoJS.DES.encrypt(aToStr, keyHex, {
					mode : CryptoJS.mode.ECB,
					padding : CryptoJS.pad.Pkcs7
				});
				var s = encrypted.toString() + "";
				var message = s.replace(/\+/g, "%2B");
				layer.open({
							type : 2,
							title : [ '用户来电' ],
							area : [ '900px', '700px' ],
							shadeClose : false, // 点击遮罩关闭
							content : "/crm_call/get_laidian_tanping.jr?str="
									+ message,
									shade: false,
							maxmin: true, //开启最大化最小化按钮
							end : function() {// 关闭弹窗后保存通话记录
								save_call_in_record(zuoxi);
							}
						});
			}
		}
	})
}
// 关闭弹窗后保存通话记录
function save_call_in_record(zuoxi) {
	clearInterval(oTimer);// 停止
	$.ajax({
		url : '/crm_call/get_uucall_id.jr',
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			if (typeof(data.uid)!="undefined"&&data.uid!=null) {
				$.ajax({
					type : 'GET', // 这里用GET
					url : 'http://192.168.1.251/api.php', // 获取通话记录
					dataType : 'jsonp', // 类型
					data : {
						'f' : 'getCallInfo',
						'uniqueid' : data.uid,
						'pop_time' : data.time
					},// 上线后更改为 phone
					jsonp : 'callback', // jsonp回调参数，必需
					async : false,
					success : function(result) {// 返回的json数据
						var str = unescape(result.replace(/\\u/g, "%u"));
						var json = eval('(' + str + ')');
						$.post("/crm_call/update_business_call.jr", {
							'record_id' : data.record_id,
							'record_time' : json.billsec,
							'sound_file' : json.userfield,
							'call_state' : json.disposition
						}, function(data) {
							if (data == 1) {
								clearInterval(oTimer);// 停止
								layer.msg("通话结束！");
								oTimer = setInterval("check_zuoxi_state('"
										+ zuoxi + "')", 2000);// 重新开始
							} else if (data == 3) {
								layer.msg("系统发生问题,通话失败！");
							}
						})
					}
				})
			} else {
				layer.msg("这个问题需要刷新页面，不要再点了！");
			}
		}
	})
}