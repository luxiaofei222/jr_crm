$(function() {

	show_time();

});

function show_time() {
	var time = $("#back_time").val();
	var time_start = new Date(time).getTime();// 设定开始时间
	var time_end = new Date().getTime(); // 设定结束时间(等于系统当前时间)
	// 计算时间差
	var time_distance = time_start - time_end;
	if (time_distance > 0) {
		// 天时分秒换算
		var int_day = Math.floor(time_distance / 86400000)
		time_distance -= int_day * 86400000;

		var int_hour = Math.floor(time_distance / 3600000)
		time_distance -= int_hour * 3600000;

		var int_minute = Math.floor(time_distance / 60000)
		time_distance -= int_minute * 60000;

		var int_second = Math.floor(time_distance / 1000)
		// 时分秒为单数时、前面加零
		if (int_day < 10) {
			int_day = "0" + int_day;
		}
		if (int_hour < 10) {
			int_hour = "0" + int_hour;
		}
		if (int_minute < 10) {
			int_minute = "0" + int_minute;
		}
		if (int_second < 10) {
			int_second = "0" + int_second;
		}
		// 显示时间
		$("#time_d").html(int_day);
		$("#time_h").html(int_hour);
		$("#time_m").html(int_minute);
		$("#time_s").html(int_second);

		setTimeout("show_time()", 1000);

	} else {
		$("#time_d").html('00');
		$("#time_h").html('00');
		$("#time_m").html('00');
		$("#time_s").html('00');
		location.href="/person/get_head_myorder.html";
	}
}