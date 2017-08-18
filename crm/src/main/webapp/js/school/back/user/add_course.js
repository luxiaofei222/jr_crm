//获取下级菜单
function get_sub_course(type,user_id){
	var course_id=$("#course_id").val();
	var sub_course=$("#sub_course").val();
	if(type==1){
		if(course_id!=0){
			$.post("/back_user/get_sub_course.jr",{
				'course_id':course_id
			},function(data){
				$("#sub_course").html(data);
			})
			
			$.post("/back_user/get_course_video.jr",{//视频列表
			'course_id':course_id,
			'type':type,
			'user_id':user_id
		},function(data){
			$("#video_list").html(data);
		})
		}else{//全部课程
			$.post("/back_user/get_course_video.jr",{//视频列表
				'type':3,
				'user_id':user_id
			},function(data){
				$("#video_list").html(data);
			})
			$("#sub_course").html("<option value='0'>请选择课程</option>");
		}
	}else{
		$.post("/back_user/get_course_video.jr",{//视频列表
			'course_id':sub_course,
			'type':type,
			'user_id':user_id
		},function(data){
			$("#video_list").html(data);
		})
	}
}
//关闭弹窗
function layer_close(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}