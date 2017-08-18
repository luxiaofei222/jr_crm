$(function() {
	$.post("/retrieval/course_tuijian.html", function(data) {
		$("#course_tuijian_list").html(data);
	})
	$("#across").click(
			function() {
				// $("#across").css(background-image,url)
				$("#lisp").attr("src",
						"/images/school/front/retrieval/hengxiang.png");
				$("#lisp2").attr("src",
						"/images/school/front/retrieval/zongxiang.png");
			});
	$("#vertical").click(
			function() {
				$("#lisp").attr("src",
						"/images/school/front/retrieval/zongxiang2.png");
				$("#lisp2").attr("src",
						"/images/school/front/retrieval/hengxiang2.png");
			});
	// $("#jiage_jiangxu").hover(function() {
	// // alert();
	// $("#p1").attr("src", "/images/school/front/retrieval/price1.png");
	// $("#p2").attr("src", "/images/school/front/retrieval/price3.png");
	// }, function() {
	// $("#p1").attr("src", "/images/school/front/retrieval/price.png");
	// $("#p2").attr("src", "/images/school/front/retrieval/price2.png");
	// });
	// $("#jiage_shengxu").hover(function() {
	// // alert();
	// $("#p1").attr("src", "/images/school/front/retrieval/price1.png");
	// $("#p2").attr("src", "/images/school/front/retrieval/price3.png");
	// }, function() {
	// $("#p1").attr("src", "/images/school/front/retrieval/price.png");
	// $("#p2").attr("src", "/images/school/front/retrieval/price2.png");
	// });

});
//默认翻页
function moren_jump_page(page,course_id){
	location.href="/retrieval/get_retrieval_main.html?course_id="+course_id+"&pageNumber="+page
}

// 获取列表式
function get_liebiaoshi(course_id, dictionary_id, banxing) {
	$("#lisp").attr("src", "/images/school/front/retrieval/zongxiang2.png");
	$("#lisp2").attr("src", "/images/school/front/retrieval/hengxiang2.png");
	jiazai_donghua();// 加载动画
	var pageNumber = 1;
	var limit = 10;
	if (dictionary_id != null) {
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'liebiao',
			'course_id' : course_id,
			'banxing' : banxing,
			'dictionary_id' : dictionary_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	} else {
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'liebiao',
			'course_id' : course_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	}
}
// 一级菜单列表形式
function get_yiji_liebiaoshi(course_id) {
	$("#lisp").attr("src", "/images/school/front/retrieval/zongxiang2.png");
	$("#lisp2").attr("src", "/images/school/front/retrieval/hengxiang2.png");
	jiazai_donghua();// 加载动画
	var pageNumber = 1;
	var limit = 10;
	$.post("/retrieval/get_yijifenleishipin_list.html", {
		'type' : 'liebiao',
		'course_parent_id' : course_id,
		'pageNumber' : pageNumber,
		'limit' : limit
	}, function(data) {
		$("#list_content").html(data);
	})
}
// 获取表格式
function get_biaogeshi(course_id, dictionary_id,banxing) {
	$("#lisp").attr("src", "/images/school/front/retrieval/hengxiang.png");
	$("#lisp2").attr("src", "/images/school/front/retrieval/zongxiang.png");
	jiazai_donghua();// 加载动画
	var pageNumber = 1;
	var limit = 15;
	if (dictionary_id != null) {
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'biaoge',
			'course_id' : course_id,
			'banxing' : banxing,
			'dictionary_id' : dictionary_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	} else {
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'biaoge',
			'course_id' : course_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	}

}
// 获取一级表格形式
function get_yiji_biaogeshi(course_id) {
	$("#lisp").attr("src", "/images/school/front/retrieval/hengxiang.png");
	$("#lisp2").attr("src", "/images/school/front/retrieval/zongxiang.png");
	jiazai_donghua();// 加载动画
	var pageNumber = 1;
	var limit = 15;
	$.post("/retrieval/get_yijifenleishipin_list.html", {
		'type' : 'biaoge',
		'course_parent_id' : course_id,
		'pageNumber' : pageNumber,
		'limit' : limit
	}, function(data) {
		$("#list_content").html(data);
	})
}
// 一级价格排序
function paixu_yiji_jiage_jiangxu(course_id, dictionary_id, banxing, paixu, obj) {
	$(obj).parent().children("a").removeClass("green3");

	var value = $('.content').length;// 判断表格样式是否存在
	jiazai_donghua();// 加载动画
	if (value > 0) {// 展示表格
		var pageNumber = 1;
		var limit = 15;
		$.post("/retrieval/get_yijifenleishipin_list.html", {
			'type' : 'biaoge',
			'course_parent_id' : course_id,
			'jiage_paixu' : paixu,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
			if (paixu == 'jiangxu') {
				$("#jiage_jiangxu").hide();
				$("#jiage_shengxu").show();
				$("#jiage_shengxu").addClass("green3");
				change_pic_jiage();
			} else {
				$("#jiage_shengxu").hide();
				$("#jiage_jiangxu").show();
				$("#jiage_jiangxu").addClass("green3");
				change_pic_jiangxu();
			}
		})
	} else {// 展示列表
		var pageNumber = 1;
		var limit = 10;
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'liebiao',
			'course_parent_id' : course_id,
			'jiage_paixu' : paixu,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
			if (paixu == 'jiangxu') {
				$("#jiage_jiangxu").hide();
				$("#jiage_shengxu").show();
				change_pic_jiage();
			} else {
				$("#jiage_shengxu").hide();
				$("#jiage_jiangxu").show();
				change_pic_jiangxu();
			}
		})
	}
}

// 点击切换一级菜单
function get_yijifenlei_list() {
	$("#opt_id").hide();
	var course_par_id = $("#course_par_id").val();
	jiazai_donghua();// 加载动画
	$.post("/retrieval/get_shaixuan.html", {
		'course_par_id' : course_par_id
	}, function(data) {
		$("#shaixuantiaojian").html(data);
	})
	// 视频内容
	$.post("/retrieval/get_yijifenleishipin_list.html", {
		'course_parent_id' : course_par_id
	}, function(data) {
		$("#list_content").html(data);
	})
	// 转换的列表或者表格按钮
	$.post("/retrieval/get_zhuanhuan_page.html", {
		'course_id' : course_par_id,
		'type' : 'yiji'
	}, function(data) {
		$("#zhuanhuan_liebiao_biaoge").html(data);
	})
}
// 点击二级菜单获取二级筛选条件
function get_erji_neironglist(course_id, obj) {
	$(obj).parent().children("a").removeClass("green1");
	$(obj).addClass("green1");
	jiazai_donghua();// 加载动画
	$.post("/retrieval/get_erji_shaixuan.html", {
		'course_id' : course_id
	}, function(data) {
		$("#erji_shaixuan").html(data);
	})
	// 获取表格内容
	var pageNumber = 1;
	var limit = 15;
	$.post("/retrieval/get_liebiaoshi_list.html", {
		'type' : 'biaoge',
		'course_id' : course_id,
		'pageNumber' : pageNumber,
		'limit' : limit
	}, function(data) {
		$("#list_content").html(data);
	})

	// 转换的列表或者表格按钮
	$.post("/retrieval/get_zhuanhuan_page.html", {
		'course_id' : course_id,
		'type' : 'erji'
	}, function(data) {
		$("#zhuanhuan_liebiao_biaoge").html(data);
	})
}
// 点击等级条件时的筛选条件
function get_erji_dengji_biaogelist(course_id, dictionary_id, obj) {
	$(obj).parent().children("a").removeClass("green1");
	$(obj).addClass("green1");
	var banxing = $(".banxing").children(".green1").attr("pid");
	jiazai_donghua();// 加载动画
	// 获取表格内容
	var pageNumber = 1;
	var limit = 15;
	// 转换的列表或者表格按钮
	if (banxing != 0) {
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'biaoge',
			'course_id' : course_id,
			'banxing' : banxing,
			'dictionary_id' : dictionary_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
		// 转换的列表或者表格按钮
		$.post("/retrieval/get_zhuanhuan_page.html", {
			'course_id' : course_id,
			'dictionary_id' : dictionary_id,
			'banxing' : banxing,
			'type' : 'dengji'
		}, function(data) {
			$("#zhuanhuan_liebiao_biaoge").html(data);
		})
	} else {
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'biaoge',
			'course_id' : course_id,
			'dictionary_id' : dictionary_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
		// 转换的列表或者表格按钮
		$.post("/retrieval/get_zhuanhuan_page.html", {
			'course_id' : course_id,
			'dictionary_id' : dictionary_id,
			'type' : 'dengji'
		}, function(data) {
			$("#zhuanhuan_liebiao_biaoge").html(data);
		})
	}
}
// 获取等级全部内容
function get_dengji_quanbu_neironglist(course_id, obj) {
	$(obj).parent().children("a").removeClass("green1");
	$(obj).addClass("green1");
	var banxing = $(".banxing").children(".green1").attr("pid");
	jiazai_donghua();// 加载动画
	// 获取表格内容
	var pageNumber = 1;
	var limit = 15;
	if (banxing != 0) {
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'biaoge',
			'course_id' : course_id,
			'banxing' : banxing,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})

		// 转换的列表或者表格按钮
		$.post("/retrieval/get_zhuanhuan_page.html", {
			'course_id' : course_id,
			'banxing' : banxing,
			'type' : 'dengji'
		}, function(data) {
			$("#zhuanhuan_liebiao_biaoge").html(data);
		})
	} else {
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'biaoge',
			'course_id' : course_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
		// 转换的列表或者表格按钮
		$.post("/retrieval/get_zhuanhuan_page.html", {
			'course_id' : course_id,
			'type' : 'dengji'
		}, function(data) {
			$("#zhuanhuan_liebiao_biaoge").html(data);
		})
	}

}
// 获取班型全部内容信息
function get_banxing_quanbu_neironglist(course_id, obj) {
	$(obj).parent().children("a").removeClass("green1");
	$(obj).addClass("green1");
	var dictionary_id = $(".dengji").children(".green1").attr("pid");
	jiazai_donghua();// 加载动画
	// 获取表格内容
	var pageNumber = 1;
	var limit = 15;
	if (dictionary_id != 0) {
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'biaoge',
			'course_id' : course_id,
			'dictionary_id' : dictionary_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	} else {
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'biaoge',
			'course_id' : course_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	}

	// 转换的列表或者表格按钮
	$.post("/retrieval/get_zhuanhuan_page.html", {
		'course_id' : course_id,
		'type' : 'dengji',
		'dictionary_id' : dictionary_id
	}, function(data) {
		$("#zhuanhuan_liebiao_biaoge").html(data);
	})
}

// 获取班型的内容列表
function get_erji_banxing_biaogelist(course_id, banxing, obj) {
	$(obj).parent().children("a").removeClass("green1");
	$(obj).addClass("green1");
	var dictionary_id = $(".dengji").children(".green1").attr("pid");
	jiazai_donghua();// 加载动画
	// 获取表格内容
	var pageNumber = 1;
	var limit = 15;
	if (dictionary_id != 0) {
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'biaoge',
			'course_id' : course_id,
			'banxing' : banxing,
			'dictionary_id' : dictionary_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	} else {
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'biaoge',
			'course_id' : course_id,
			'banxing' : banxing,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	}

	// 转换的列表或者表格按钮
	$.post("/retrieval/get_zhuanhuan_page.html", {
		'course_id' : course_id,
		'type' : 'banxing',
		'banxing' : banxing,
		'dictionary_id' : dictionary_id
	}, function(data) {
		$("#zhuanhuan_liebiao_biaoge").html(data);
	})
}
// 一级点击全部按钮
function yiji_quanbu(course_parent_id, obj) {
	$(obj).parent().children("a").removeClass("green1");
	$(obj).addClass("green1");
	$("#opt_id").hide();
	$.post("/retrieval/get_shaixuan.html", {
		'course_par_id' : course_parent_id
	}, function(data) {
		$("#shaixuantiaojian").html(data);
	})
	jiazai_donghua();// 加载动画
	// 视频内容
	$.post("/retrieval/get_yijifenleishipin_list.html", {
		'course_parent_id' : course_parent_id
	}, function(data) {
		$("#list_content").html(data);
	})
	// 转换的列表或者表格按钮
	$.post("/retrieval/get_zhuanhuan_page.html", {
		'course_id' : course_parent_id,
		'type' : 'yiji'
	}, function(data) {
		$("#zhuanhuan_liebiao_biaoge").html(data);
	})
}
// 加载动画
function jiazai_donghua() {
	$("#list_content")
			.html(
					"<div class='main3'><div class='loadEffect3'><span></span><span></span><span></span><span></span><span></span><span></span><span></span><span></span></div></div>")
}
// 价格升降序排列
function paixu_jiage_jiangxu(course_id, dictionary_id, banxing, paixu, obj) {
	$(obj).parent().children("a").removeClass("green3");
	var value = $('.content').length;// 判断表格样式是否存在
	jiazai_donghua();// 加载动画
	if (dictionary_id != null) {
		if (value > 0) {// 展示表格
			var pageNumber = 1;
			var limit = 15;
			$.post("/retrieval/get_liebiaoshi_list.html", {
				'type' : 'biaoge',
				'course_id' : course_id,
				'banxing' : banxing,
				'jiage_paixu' : paixu,
				'dictionary_id' : dictionary_id,
				'pageNumber' : pageNumber,
				'limit' : limit
			}, function(data) {
				$("#list_content").html(data);
				if (paixu == 'jiangxu') {
					$("#jiage_jiangxu").hide();
					$("#jiage_shengxu").show();
					$("#jiage_shengxu").addClass("green3");
					change_pic_jiage();
				} else {
					$("#jiage_shengxu").hide();
					$("#jiage_jiangxu").show();
					$("#jiage_jiangxu").addClass("green3");
					change_pic_jiangxu();
				}
			})
		} else {
			var pageNumber = 1;
			var limit = 10;
			$.post("/retrieval/get_liebiaoshi_list.html", {
				'type' : 'liebiao',
				'course_id' : course_id,
				'banxing' : banxing,
				'jiage_paixu' : paixu,
				'dictionary_id' : dictionary_id,
				'pageNumber' : pageNumber,
				'limit' : limit
			}, function(data) {
				$("#list_content").html(data);
				if (paixu == 'jiangxu') {
					$("#jiage_jiangxu").hide();
					$("#jiage_shengxu").show();
					$("#jiage_shengxu").addClass("green3");
					change_pic_jiage();
				} else {
					$("#jiage_shengxu").hide();
					$("#jiage_jiangxu").show();
					$("#jiage_jiangxu").addClass("green3");
					change_pic_jiangxu();
				}
			})
		}
	} else {
		if (value > 0) {// 展示表格
			var pageNumber = 1;
			var limit = 15;
			$.post("/retrieval/get_liebiaoshi_list.html", {
				'type' : 'biaoge',
				'course_id' : course_id,
				'jiage_paixu' : paixu,
				'pageNumber' : pageNumber,
				'limit' : limit
			}, function(data) {
				$("#list_content").html(data);
				if (paixu == 'jiangxu') {
					$("#jiage_jiangxu").hide();
					$("#jiage_shengxu").show();
					$("#jiage_shengxu").addClass("green3");
					change_pic_jiage();
				} else {
					$("#jiage_shengxu").hide();
					$("#jiage_jiangxu").show();
					$("#jiage_jiangxu").addClass("green3");
					change_pic_jiangxu();
				}
			})
		} else {// 展示列表
			var pageNumber = 1;
			var limit = 10;
			$.post("/retrieval/get_liebiaoshi_list.html", {
				'type' : 'liebiao',
				'course_id' : course_id,
				'jiage_paixu' : paixu,
				'pageNumber' : pageNumber,
				'limit' : limit
			}, function(data) {
				$("#list_content").html(data);
				if (paixu == 'jiangxu') {
					$("#jiage_jiangxu").hide();
					$("#jiage_shengxu").show();
					$("#jiage_shengxu").addClass("green3");
					change_pic_jiage();
				} else {
					$("#jiage_shengxu").hide();
					$("#jiage_jiangxu").show();
					$("#jiage_jiangxu").addClass("green3");
					change_pic_jiangxu();
				}
			})
		}
	}
}
// 一级默认排序
function get_yiji_zonghe_paixu(course_id, dictionary_id, banxing, obj) {
	$(obj).parent().children("a").removeClass("green3");
	$(obj).addClass("green3");
	var value = $('.content').length;// 判断表格样式是否存在
	jiazai_donghua();// 加载动画
	if (value > 0) {// 展示表格
		var pageNumber = 1;
		var limit = 15;
		$.post("/retrieval/get_yijifenleishipin_list.html", {
			'type' : 'biaoge',
			'course_parent_id' : course_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	} else {// 展示列表
		var pageNumber = 1;
		var limit = 10;
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'liebiao',
			'course_parent_id' : course_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	}
}
// 默认排序
function get_zonghe_paixu(course_id, dictionary_id, banxing, obj) {
	$(obj).parent().children("a").removeClass("green3");
	$(obj).addClass("green3");
	var value = $('.content').length;// 判断表格样式是否存在
	jiazai_donghua();// 加载动画
	if (dictionary_id != null) {
		if (value > 0) {// 展示表格
			var pageNumber = 1;
			var limit = 15;
			$.post("/retrieval/get_liebiaoshi_list.html", {
				'type' : 'biaoge',
				'course_id' : course_id,
				'banxing' : banxing,
				'dictionary_id' : dictionary_id,
				'pageNumber' : pageNumber,
				'limit' : limit
			}, function(data) {
				$("#list_content").html(data);
			})
		} else {
			var pageNumber = 1;
			var limit = 10;
			$.post("/retrieval/get_liebiaoshi_list.html", {
				'type' : 'liebiao',
				'course_id' : course_id,
				'banxing' : banxing,
				'dictionary_id' : dictionary_id,
				'pageNumber' : pageNumber,
				'limit' : limit
			}, function(data) {
				$("#list_content").html(data);
			})
		}
	} else {
		if (value > 0) {// 展示表格
			var pageNumber = 1;
			var limit = 15;
			$.post("/retrieval/get_liebiaoshi_list.html", {
				'type' : 'biaoge',
				'course_id' : course_id,
				'pageNumber' : pageNumber,
				'limit' : limit
			}, function(data) {
				$("#list_content").html(data);
			})
		} else {// 展示列表
			var pageNumber = 1;
			var limit = 10;
			$.post("/retrieval/get_liebiaoshi_list.html", {
				'type' : 'liebiao',
				'course_id' : course_id,
				'pageNumber' : pageNumber,
				'limit' : limit
			}, function(data) {
				$("#list_content").html(data);
			})
		}
	}
}
// 一级最热排序
function get_yiji_zuire_paixu(course_id, dictionary_id, banxing, obj) {
	$(obj).parent().children("a").removeClass("green3");
	$(obj).addClass("green3");
	var value = $('.content').length;// 判断表格样式是否存在
	jiazai_donghua();// 加载动画
	if (value > 0) {// 展示表格
		var pageNumber = 1;
		var limit = 15;
		$.post("/retrieval/get_yijifenleishipin_list.html", {
			'type' : 'biaoge',
			'zuire' : "zuire",
			'course_parent_id' : course_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	} else {// 展示列表
		var pageNumber = 1;
		var limit = 10;
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : 'liebiao',
			'zuire' : "zuire",
			'course_parent_id' : course_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	}
}

// 最热排序
function get_zuire_paixu(course_id, dictionary_id, banxing, obj) {
	$(obj).parent().children("a").removeClass("green3");
	$(obj).addClass("green3");
	var value = $('.content').length;// 判断表格样式是否存在
	jiazai_donghua();// 加载动画
	if (dictionary_id != null) {
		if (value > 0) {// 展示表格
			var pageNumber = 1;
			var limit = 15;
			$.post("/retrieval/get_liebiaoshi_list.html", {
				'type' : 'biaoge',
				'course_id' : course_id,
				'banxing' : banxing,
				'zuire' : "zuire",
				'dictionary_id' : dictionary_id,
				'pageNumber' : pageNumber,
				'limit' : limit
			}, function(data) {
				$("#list_content").html(data);
			})
		} else {
			var pageNumber = 1;
			var limit = 10;
			$.post("/retrieval/get_liebiaoshi_list.html", {
				'type' : 'liebiao',
				'course_id' : course_id,
				'banxing' : banxing,
				'zuire' : "zuire",
				'dictionary_id' : dictionary_id,
				'pageNumber' : pageNumber,
				'limit' : limit
			}, function(data) {
				$("#list_content").html(data);
			})
		}
	} else {
		if (value > 0) {// 展示表格
			var pageNumber = 1;
			var limit = 15;
			$.post("/retrieval/get_liebiaoshi_list.html", {
				'type' : 'biaoge',
				'course_id' : course_id,
				'zuire' : "zuire",
				'pageNumber' : pageNumber,
				'limit' : limit
			}, function(data) {
				$("#list_content").html(data);
			})
		} else {// 展示列表
			var pageNumber = 1;
			var limit = 10;
			$.post("/retrieval/get_liebiaoshi_list.html", {
				'type' : 'liebiao',
				'zuire' : "zuire",
				'course_id' : course_id,
				'pageNumber' : pageNumber,
				'limit' : limit
			}, function(data) {
				$("#list_content").html(data);
			})
		}
	}
}
// 默认翻页-跳转
function retrieval_jump_page(page, course_id) {
	var limit = 15;
	pageNumber = page;
	jiazai_donghua();// 加载动画
	$.post("/retrieval/get_liebiaoshi_list.html", {
		'type' : 'biaoge',
		'course_id' : course_id,
		'pageNumber' : pageNumber,
		'limit' : limit
	}, function(data) {
		$("#list_content").html(data);
	})
}
//跟一级有关的所有分页跳转
function retrieval_yiji_jump_page(page, course_parent_id, type) {
	pageNumber = page;
	jiazai_donghua();// 加载动画
	if (type == "liebiao") {
		var limit = 10;
		$.post("/retrieval/get_yijifenleishipin_list.html", {
			'type' : type,
			'course_parent_id' : course_parent_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	} else {
		var limit = 15;
		$.post("/retrieval/get_yijifenleishipin_list.html", {
			'type' : type,
			'course_parent_id' : course_parent_id,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	}
}
//其他所有条件时的翻页
function retrieval_other_jump_page(page,zuire ,jiage_paixu ,type ,banxing ,dictionary_id ,course_id ){
	pageNumber = page;
	jiazai_donghua();// 加载动画
	if (type == "liebiao") {
		var limit = 10;
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : type,
			'course_id' : course_id,
			'zuire' : zuire,
			'banxing' : banxing,
			'dictionary_id' : dictionary_id,
			'jiage_paixu' : jiage_paixu,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	}else{
		var limit = 15;
		$.post("/retrieval/get_liebiaoshi_list.html", {
			'type' : type,
			'course_id' : course_id,
			'zuire' : zuire,
			'banxing' : banxing,
			'dictionary_id' : dictionary_id,
			'jiage_paixu' : jiage_paixu,
			'pageNumber' : pageNumber,
			'limit' : limit
		}, function(data) {
			$("#list_content").html(data);
		})
	}
}

function change_pic_jiage() {
	$("#jiage_shengxu").children("#p1").attr("src",
			"/images/school/front/retrieval/price1.png");
	$("#jiage_shengxu").children("#p2").attr("src",
			"/images/school/front/retrieval/price3.png");
}

function change_pic_jiangxu() {
	$("#jiage_jiangxu").children("#p1").attr("src",
			"/images/school/front/retrieval/price1.png");
	$("#jiage_jiangxu").children("#p2").attr("src",
			"/images/school/front/retrieval/price3.png");
}

//页码

$(".search_fenye li").click(
		function() {
			var index1 = $(this).index();
			$(".search_fenye li").eq(index1).addClass("fenye_green").siblings()
					.removeClass("fenye_green");
		})