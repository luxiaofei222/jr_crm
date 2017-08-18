//系统联系人翻页
function get_employee_page(page) {
	$.post("/center/get_employee_list.jr", {
		'pageNumber' : page
	}, function(data) {
		$("#employee_list").html(data);
	})
}
$(function() {
	// 加载标签
	$("#editor").emoji({
		button : "#btn",
		showTab : false,
		animation : 'slide',
		icons : [ {
			name : "QQ表情",
			path : "/emoji/dist/img/tieba/",
			maxNum : 48,
			excludeNums : [ 41, 45, 54 ],
			file : ".jpg"
		} ]
	});

	$("#file_upload").click(function() {
		$(this).next(".image_container").show(1000);
	})

})

// 发表说说
function add_say_say() {
	var text = $("#editor").html();
	var file_upload = $("#file_upload").val();
	var fileType = file_upload.substring(file_upload.lastIndexOf('.') + 1);
	if (fileType == 'jpg' || fileType == 'bmp' || fileType == 'gif'
			|| fileType == 'png' || fileType == 'jpeg' || file_upload == ""
			|| file_upload == null) {
		$("#myform").ajaxSubmit({
			type : 'POST',
			url : "/center/save_saysay.jr",
			data : {
				'say_content' : text
			},
			success : function(data) {
				if (data == 1) {
					layer.msg('发表成功');
					$("#editor").html("");
					$("#preview").attr("src", "");
					$(".image_container").hide();
				} else if (data == 4) {
					layer.msg('随便说点什么吧');
				} else if (data == 3) {
					location.href = "/login.jr"
				} else {
					tanchuang('很遗憾，发布失败');
				}
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				tanchuang('很遗憾，发布失败');
			}
		});
	} else {
		layer.msg("请上传图片，不要弄些乱七八糟的东西！");
	}
}
// 签到
function qiandao() {
	$("#weiqian").hide();
	$("#yiqian").show();
	$.post("/center/save_leveone_saysay.jr", {
		'say_content' : "今日已签到！",
		'content_type' : "签到"
	}, function(data) {
		if (data == 1) {
			layer.msg('签到成功');
		} else {
			tanchuang('签到失败');
		}
	})
}
// 回复一级评论
function huifu_say(say_id, page) {
	var text = $("#one_content" + say_id).val();
	if (text != null && text != "") {
		$.post("/center/save_leveone_saysay.jr", {
			'parent_id' : say_id,
			'say_content' : text,
			'say_level' : 1
		}, function(data) {
			if (data == 1) {
				layer.msg('回复成功');
				$("#one_content" + say_id).parent().addClass("none");
				$("#one_content" + say_id).val("");
				jump_say_page(page)
			} else if (data == 4) {
				layer.msg('随便说点什么吧');
			} else if (data == 3) {
				location.href = "/login.jr"
			} else {
				tanchuang('很遗憾，发布失败');
			}
		})
	} else {
		layer.msg("请输入您要回复的内容！")
	}
}
function huifu_say_my(say_id, page) {
	var text = $("#one_content" + say_id).val();
	if (text != null && text != "") {
		$.post("/center/save_leveone_saysay.jr", {
			'parent_id' : say_id,
			'say_content' : text,
			'say_level' : 1
		}, function(data) {
			if (data == 1) {
				layer.msg('回复成功');
				$("#one_content" + say_id).parent().addClass("none");
				$("#one_content" + say_id).val("");
				jump_my_say_page(page)
			} else if (data == 4) {
				layer.msg('随便说点什么吧');
			} else if (data == 3) {
				location.href = "/login.jr"
			} else {
				tanchuang('很遗憾，发布失败');
			}
		})
	} else {
		layer.msg("请输入您要回复的内容！")
	}
}
// 二级评论回复
function huifu_say_two(say_id, employee_id, page) {
	var text = $("#one_content" + say_id).val();
	if (text != null && text != "") {
		$.post("/center/save_leveone_saysay.jr", {
			'parent_id' : say_id,
			'say_content' : text,
			'parent_employee_id' : employee_id,
			'say_level' : 2
		}, function(data) {
			if (data == 1) {
				layer.msg('回复成功');
				$("#one_content" + say_id).parent().addClass("none");
				$("#one_content" + say_id).val("");
				jump_say_page(page)
			} else if (data == 4) {
				layer.msg('随便说点什么吧');
			} else if (data == 3) {
				location.href = "/login.jr"
			} else {
				tanchuang('很遗憾，发布失败');
			}
		})
	} else {
		layer.msg("请输入您要回复的内容！")
	}
}
function huifu_say_two_my(say_id, employee_id, page) {
	var text = $("#one_content" + say_id).val();
	if (text != null && text != "") {
		$.post("/center/save_leveone_saysay.jr", {
			'parent_id' : say_id,
			'say_content' : text,
			'parent_employee_id' : employee_id,
			'say_level' : 2
		}, function(data) {
			if (data == 1) {
				layer.msg('回复成功');
				$("#one_content" + say_id).parent().addClass("none");
				$("#one_content" + say_id).val("");
				jump_my_say_page(page)
			} else if (data == 4) {
				layer.msg('随便说点什么吧');
			} else if (data == 3) {
				location.href = "/login.jr"
			} else {
				tanchuang('很遗憾，发布失败');
			}
		})
	} else {
		layer.msg("请输入您要回复的内容！")
	}
}
// 第三级回复评论
function huifu_say_three(say_id, parent_id, employee_id, page) {
	var text = $("#one_content" + say_id).val();
	if (text != null && text != "") {
		$.post("/center/save_leveone_saysay.jr", {
			'parent_id' : parent_id,
			'say_content' : text,
			'parent_employee_id' : employee_id,
			'say_level' : 2
		}, function(data) {
			if (data == 1) {
				layer.msg('回复成功');
				$("#one_content" + say_id).parent().addClass("none");
				$("#one_content" + say_id).val("");
				jump_say_page(page)
			} else if (data == 4) {
				layer.msg('随便说点什么吧');
			} else if (data == 3) {
				location.href = "/login.jr"
			} else {
				tanchuang('很遗憾，发布失败');
			}
		})
	} else {
		layer.msg("请输入您要回复的内容！")
	}
}
function huifu_say_three_my(say_id, parent_id, employee_id, page) {
	var text = $("#one_content" + say_id).val();
	if (text != null && text != "") {
		$.post("/center/save_leveone_saysay.jr", {
			'parent_id' : parent_id,
			'say_content' : text,
			'parent_employee_id' : employee_id,
			'say_level' : 2
		}, function(data) {
			if (data == 1) {
				layer.msg('回复成功');
				$("#one_content" + say_id).parent().addClass("none");
				$("#one_content" + say_id).val("");
				jump_my_say_page(page);
			} else if (data == 4) {
				layer.msg('随便说点什么吧');
			} else if (data == 3) {
				location.href = "/login.jr"
			} else {
				tanchuang('很遗憾，发布失败');
			}
		})
	} else {
		layer.msg("请输入您要回复的内容！")
	}
}
// 分页跳转
function jump_say_page(page) {
	$.post("/center/get_say_list.jr", {
		'pageNumber' : page
	}, function(data) {
		$("#say_list").html(data);
	})
}
//我的动态分页
function jump_my_say_page(page){
	$.post("/center/get_my_say_list.jr", {
		'pageNumber' : page
	}, function(data) {
		$("#say_list").html(data);
	})
}

//获取我的动态
function get_my_dongtai(){
	loading();
	$.post("/center/get_my_say_list.jr", {
			'pageNumber' : 1
		}, function(data) {
			$("#say_list").html(data);
		})
}

function get_dongtai(){
	loading();
	$.post("/center/get_say_list.jr", {
			'pageNumber' : 1
		}, function(data) {
			$("#say_list").html(data);
		})
}
function loading(){
	$("#say_list").html("<div id='loading'><div id='loading-center'><div id='loading-center-absolute'><div class='object' id='object_one'></div><div class='object' id='object_two'></div><div class='object' id='object_three'></div><div class='object' id='object_four'></div><div class='object' id='object_five'></div><div class='object' id='object_six'></div><div class='object' id='object_seven'></div><div class='object' id='object_eight'></div><div class='object' id='object_big'></div></div></div></div>");
}
$(function() {
	$("#file_upload")
			.change(
					function() {
						var $file = $(this);
						var fileObj = $file[0];
						var windowURL = window.URL || window.webkitURL;
						var dataURL;
						var $img = $("#preview");

						if (fileObj && fileObj.files && fileObj.files[0]) {
							dataURL = windowURL
									.createObjectURL(fileObj.files[0]);
							$img.attr('src', dataURL);
						} else {
							dataURL = $file.val();
							var imgObj = document.getElementById("preview");
							// 两个坑:
							// 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
							// 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
							imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
							imgObj.filters
									.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

						}
					});
	// 说说列表
	$.post("/center/get_say_list.jr", {
		'pageNumber' : 1
	}, function(data) {
		$("#say_list").html(data);
	})
	// 文件列表
	$.post("/center/get_file_list.jr", {
		'pageNumber' : 1
	}, function(data) {
		$("#file_list").html(data);
	})
});
// 文件列表分页
function get_file_page(page) {
	$.post("/center/get_file_list.jr", {
		'pageNumber' : page
	}, function(data) {
		$("#file_list").html(data);
	})
}
// 员工名片
function get_employee_card(employee_id) {
	layer.open({
		type : 2,
		title : [ '查看名片' ],
		area : [ '540px', '380px' ],
		shadeClose : true, // 点击遮罩关闭
		content : "/center/get_employee_card.jr?employee_id=" + employee_id
	});
}
// 下载次数
function xiazai_time(file_id) {
	$.post("/notice/update_file.jr", {
		'file_id' : file_id
	}, function(data) {
		if (data == 1) {
			layer.msg("下载成功！")
		}
	})
}

// 换肤
$(function() {
	$(".huanfu").click(function() {
		$(".huanlist").slideDown();
	})
	$(".huan_close").click(function() {
		$(".huanlist").slideUp();
	})
	$(".btn_cancel").click(function() {
		$(".huanlist").slideUp();
	})
	$(".btn_confirm").click(function() {
		$(".huanlist").slideUp();
	})
	$(".huan_title li").click(
			function() {
				var index = $(this).index();
				$(".huan_pic_wrapper .huan_tab").eq(index).css("display",
						"block").siblings(".huan_tab").css("display", "none");
				$(".huan_title li").eq(index).addClass("huan_select")
						.siblings().removeClass("huan_select");
			})
	// 封面
	$(".huan_pic li img").click(function() {
		var fengmian = $(this).attr("pid");
		var employee_id = $(this).attr("eid");
		var fengmianjia = "url(" + fengmian + ")";
		$.post("/center/update_employee.jr", {
			'employee_id' : employee_id,
			'fengmian' : fengmian
		}, function(data) {
			if (data == 1) {
				$(".person").css("background-image", fengmianjia);
			} else {
				layer.msg("系统发生错误，请稍后再试！")
			}
		})
	})
	// 背景
	$(".huan_pic1 li img").click(function() {
		var fengmian = $(this).attr("pid");
		var fengmianjia = "url(" + fengmian + ")";
		var employee_id = $(this).attr("eid");
		$.post("/center/update_employee.jr", {
			'employee_id' : employee_id,
			'beijing' : fengmian
		}, function(data) {
			if (data == 1) {
				$("body").css("background-image", fengmianjia);
			} else {
				layer.msg("系统发生错误，请稍后再试！")
			}
		})
	})
})

function Jihua_Cnblogs_Com() {
	$("#jihuaslide").slideDown("slow");
	setTimeout(function(){
		$("#jihuaslide").slideUp("slow");},3000); //延时1秒
}
$(document).ready(function() {
	$("#close").click(function() {
		$("#jihuaslide").slideUp("slow");
	})
})
//发送私信
function send_private_message(employee_id){
	layer.open({
		type : 2,
		title : [ '发送私信' ],
		area : [ '600px', '300px' ],
		shadeClose : true, // 点击遮罩关闭
		content : "/private_message/get_send_message_page.jr?employee_id=" + employee_id
	});
}