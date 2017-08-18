
//翻页，上一页，下一页
function video_jump_page(page){
	jiazaidonghua();
	$.post("/back_video/get_course_video_main.jr",{
		'pageNumber' : page,
		'limit' : 20
	},function(data){
		$("#conten_list").html(data);
	})
}

//查询关键字
function seacher_video(){
	var drop_menu=$('#select_val').val();//获取查询下拉菜单的值
	var drop_menu_val=$('#check_val').val(); //获取输入框里面的值
	if(drop_menu=="name"){
		jiazaidonghua();
		$.post("/back_video/get_course_video_main.jr", {
			'video_name':drop_menu_val,
			'pageNumber' : 1,
			'limit' : 20
		}, function(data) {
			if (data != null) {
				$('#conten_list').html(data);
			}
		});
	}else if(drop_menu=="0"){
		layer.confirm("提示：您好，请选择你需要的查询条件！",function(){
			jiazaidonghua();
			$.post("/back_video/get_course_video_main.jr",{
				'pageNumber' : 1,
				'limit' : 20
			},function(data){
				$("#conten_list").html(data);
				layer.closeAll('dialog');
			})
		});
	}
}
//展开章
function zhankai_zhang(video_id){
	var type=$("#tubiao"+video_id).attr("class");
	if(type=="fa fa-plus"){//切换符号
		$("#tubiao"+video_id).removeClass("fa-plus");
		$("#tubiao"+video_id).addClass("fa-minus");
		$.get("/back_video/get_zhang_list.jr",{
			'video_id':video_id,
			'type':2
		},function(data){
			$("#zhang"+video_id).after(data);
		})
	}else{
		$("#tubiao"+video_id).removeClass("fa-minus");
		$("#tubiao"+video_id).addClass("fa-plus");
		$("#zhang"+video_id).nextAll(".success").remove();
		$("#zhang"+video_id).nextAll(".info").remove();
		$("#zhang"+video_id).next(".zanwu").remove();
	}
}
//展开节
function zhankai_jie(video_id){
	var type=$("#tubiao"+video_id).attr("class");
	if(type=="fa fa-plus"){//切换符号
		$("#tubiao"+video_id).removeClass("fa-plus");
		$("#tubiao"+video_id).addClass("fa-minus");
		$.get("/back_video/get_zhang_list.jr",{
			'video_id':video_id,
			'type':3
		},function(data){
			$("#jie"+video_id).after(data);
		})
	}else{
		$("#tubiao"+video_id).removeClass("fa-minus");
		$("#tubiao"+video_id).addClass("fa-plus");
		$("#jie"+video_id).nextAll(".info").remove();
		$("#jie"+video_id).next(".zanwu").remove();
	}
}
//添加章的内容
function add_zhang(video_id){
	 layer.open({
		  type: 2,
		  title: ['添加章节名称'],
		  area: ['500px', '250px'],
		  shadeClose: true, //点击遮罩关闭
		  content: "/back_video/to_add_zhang.jr?video_id="+video_id
		  });
}
//是否推荐到首页
function video_tuijian(is_tuijian,video_id,page){
	$.post("/back_video/tuijian.jr",{
		'is_tuijian':is_tuijian,
		'video_id':video_id
	},function(data){
		if(data==1){
			tanchuang("设置成功!");
			video_jump_page(page);
		}else{
			tanchuang("设置失败!");
		}
	})
}
//设置是否显示在资讯二级页面
function video_info_shangjia(type,video_id,page){
	$.post("/back_video/update_zhang.jr",{
		'is_info':type,
		'video_id':video_id
	},function(data){
		if(data==1){
			tanchuang("设置成功!");
			video_jump_page(page);
		}else{
			tanchuang("设置失败!");
		}
	})
}
//课程下架
function video_xiajia(video_id,page){
	$.post("/back_video/shangxiajia.jr",{
		'video_type':'下架',
		'video_id':video_id
	},function(data){
		if(data==1){
			tanchuang("设置成功!");
			video_jump_page(page);
		}else{
			tanchuang("设置失败!");
		}
	})
}
//课程上架
function video_shangjia(video_id,page){
	$.post("/back_video/shangxiajia.jr",{
		'video_type':'上架',
		'video_id':video_id
	},function(data){
		if(data==1){
			tanchuang("设置成功!");
			video_jump_page(page);
		}else{
			tanchuang("设置失败!");
		}
	})
}
//修改章
function update_zhang(video_id){
	layer.open({
		  type: 2,
		  title: ['修改章节名称'],
		  area: ['500px', '250px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_video/to_update_zhang.jr?video_id="+video_id
		  });
}
//修改节名称
function update_jie(video_id){
	layer.open({
		  type: 2,
		  title: ['修改章节名称'],
		  area: ['500px', '250px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_video/to_update_jie.jr?video_id="+video_id
		  });
}
//去修改课程信息
function to_update_video(video_id){
	layer.open({
		  type: 2,
		  title: ['修改课程信息'],
		  area: ['900px', '800px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_video/to_update_video.jr?video_id="+video_id
		  });
}
//删除课程信息
function delete_video(video_id,page){
	layer.confirm("提示：您确定要删除本条课程记录吗？",function(){
		layer.closeAll('dialog');
		$.post("/back_video/delete_video.jr",{
			'video_id' : video_id
		},function(data){
			tanchuang("删除成功!");
			video_jump_page(page);
		})
	});
}
//删除课程信息
function delete_jie(video_id){
	layer.confirm("提示：您确定要删除本节课程记录吗？",function(){
		layer.closeAll('dialog');
		$.post("/back_video/delete_video.jr",{
			'video_id' : video_id
		},function(data){
			tanchuang("删除成功!");
		})
	});
}
//删除章信息
function delete_zhang(video_id){
	layer.confirm("提示：您确定要删除本章记录吗？",function(){
		layer.closeAll('dialog');
		$.post("/back_video/delete_video.jr",{
			'video_id' : video_id
		},function(data){
			tanchuang("删除成功!");
		})
	});
}
//上传视频弹窗
function to_add_jie(video_id){
	// to_add_jie 备注单独上传视频页面
	layer.open({
		  type: 2,
		  title: ['上传视频'],
		  area: ['700px', '400px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_video/get_file_list.jr?video_id="+video_id+"&type="+0
		  });
}
//修改上传视频
function update_jie_video(video_id){
	layer.open({
		  type: 2,
		  title: ['重新上传视频'],
		  area: ['700px', '400px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_video/get_file_list.jr?video_id="+video_id+"&type="+1
		  });
}
//信息弹窗
function tanchuang(content){
	layer.alert(content, {
		  icon: 1,
		  skin: 'layer-ext-moon' 
		})
}
//查看视频
function chakan_video(video_id){
	jiazaidonghua();
	$.post("/back_video/get_video_detail.jr",{
		'video_id':video_id
	},function(data){
		$("#conten_list").html(data);
	    var windowh=$(window).height();
	    var lefth3=$(".ht_left").height();
	    var righth=$(".back_right").height();
	    if(righth>windowh){
	    	$(".ht_left").css("height",righth);
	    	$(".sidebar_info").css("height",righth-188);
	    }
	})
}

//上传课件弹窗
function upload_ware(video_id){
	layer.open({
		  type: 2,
		  title: ['上传课件'],
		  area: ['700px', '400px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_video/to_add_course_ware.jr?video_id="+video_id
		  });
}
//查看课件窗口
function check_ware(video_id){
	layer.open({
		  type: 2,
		  title: ['课程课件'],
		  area: ['700px', '400px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_video/to_check_course_ware.jr?video_id="+video_id
		  });
}
//向上一级
function xiangshang(video_id){
	$.post("/back_video/xiangshang.jr",{
		'video_id':video_id
	},function(data){
		if(data!=0){
			tanchuang("设置成功!");
			zhankai_zhang(data);
		}else{
			tanchuang("设置失败!");
		}
	})
}
//禁止或者解除按钮
function update_is_jinzhi(video_id,is_jinzhi){
	$.post("/back_video/update_zhang.jr",{
		'video_id':video_id,
		'is_jinzhi':is_jinzhi
	},function(data){
		if(data!=0){
			tanchuang("设置成功!");
		}else{
			tanchuang("设置失败!");
		}
	})
}
//一键禁止，禁止点击课程
function jinzhi_dianji(){
	$.post("/back_video/jinzhi_dianji.jr",{
		'video_level':3,
		'is_jinzhi':1
	},function(data){
		if(data==1){
			tanchuang("设置成功!");
		}else{
			tanchuang("设置失败!");
		}
	})
}
