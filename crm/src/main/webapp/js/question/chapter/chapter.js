//跳转章节列表页面
function to_add_chapter_question(question_course_id){
	$.post("/back_chapter/to_add_chapter_question.jr",{
		'question_course_id':question_course_id
			},function(data){
		$("#conten_list").html(data);
	})
}
//添加章弹窗
function to_add_zhang(question_course_id){
	layer.open({
		  type: 2,
		  title: ['添加章节练习章标题'],
		  area: ['700px', '450px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_chapter/to_add_chapter_zhang.jr?question_course_id="+question_course_id,
		  end: function () {
			  to_add_chapter_question(question_course_id);
      }
		  });
}
//插入章名称
function to_insert_zhang(question_course_id){
	layer.open({
		  type: 2,
		  title: ['添加章节练习章标题'],
		  area: ['700px', '450px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_chapter/to_insert_zhang.jr?question_course_id="+question_course_id,
		  end: function () {
			  to_add_chapter_question(question_course_id);
    }
		  });
}
//修改章节名称弹窗
function to_update_name(chapter_exercises_id,question_course_id){
	layer.open({
		  type: 2,
		  title: ['修改章节名称'],
		  area: ['700px', '450px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_chapter/to_update_name.jr?chapter_exercises_id="+chapter_exercises_id,
		  end: function () {
			  to_add_chapter_question(question_course_id);
  }
		  });
}
//设置教材版本
function to_update_version(question_course_id){
	layer.open({
		  type: 2,
		  title: ['设置教材版本'],
		  area: ['700px', '350px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_chapter/to_update_version.jr?question_course_id="+question_course_id,
		  end: function () {
			  to_add_chapter_question(question_course_id);
    }
		  });
}
//查看该课程所有题目
function to_check_option(question_course_id){
	layer.open({
		  type: 2,
		  title: ['查看题库-章节练习'],
		  area: ['1000px', '800px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_chapter/to_check_option.jr?question_course_id="+question_course_id
		  });
}
//添加小节弹窗
function to_add_jie(chapter_exercises_id,question_course_id){
	layer.open({
		  type: 2,
		  title: ['添加章节练习小节标题'],
		  area: ['700px', '450px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_chapter/to_add_chapter_jie.jr?chapter_exercises_id="+chapter_exercises_id,
		  end: function () {
			  to_add_chapter_question(question_course_id);
    }
		  });
}
//添加课目弹窗
function to_add_question(chapter_exercises_id,question_course_id){
	layer.open({
		  type: 2,
		  title: ['添加章节练习课目标题'],
		  area: ['700px', '450px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_chapter/to_add_chapter_questionmenu.jr?chapter_exercises_id="+chapter_exercises_id,
		  end: function () {
			  to_add_chapter_question(question_course_id);
    }
		  });
}

//添加题目弹窗
function to_add_question_option(chapter_exercises_id,question_course_id){
	layer.open({
		  type: 2,
		  title: ['添加章节练习题目'],
		  area: ['900px', '750px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_chapter/to_add_question_option.jr?chapter_exercises_id="+chapter_exercises_id,
		  end: function () {
			  to_add_chapter_question(question_course_id);
    }
		  });
}
//删除章
function delete_zhang(chapter_exercises_id,question_course_id){
	layer.confirm("提示：删除章后，章的下级所有单位都将删除！",function(){
		layer.closeAll('dialog');
		$.post("/back_chapter/delete_zhang.jr",{
			'chapter_exercises_id':chapter_exercises_id
		},function(data){
			if(data==1){
				tanchuang("删除成功！");
				to_add_chapter_question(question_course_id);
			}else{
				tanchuang("删除失败！");
			}
		});
		})
}
//删除节
function delete_jie(chapter_exercises_id,question_course_id){
	layer.confirm("提示：删除节后，节的下级所有单位都将删除！",function(){
		layer.closeAll('dialog');
		$.post("/back_chapter/delete_jie.jr",{
			'chapter_exercises_id':chapter_exercises_id
		},function(data){
			if(data==1){
				tanchuang("删除成功！");
				to_add_chapter_question(question_course_id);
			}else{
				tanchuang("删除失败！");
			}
		});
		})
}
//删除课
function delete_question(chapter_exercises_id,question_course_id){
	layer.confirm("提示：删除课后，课的下级所有单位都将删除！",function(){
		layer.closeAll('dialog');
		$.post("/back_chapter/delete_question.jr",{
			'chapter_exercises_id':chapter_exercises_id
		},function(data){
			if(data==1){
				tanchuang("删除成功！");
				to_add_chapter_question(question_course_id);
			}else{
				tanchuang("删除失败！");
			}
		});
		})
}
//以章查看
function to_check_zhang_option(chapter_exercises_id){
	layer.open({
		  type: 2,
		  title: ['查看题库-本章题目'],
		  area: ['900px', '800px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_chapter/to_check_zhang_option.jr?chapter_exercises_id="+chapter_exercises_id
		  });
}
//以节查看
function to_check_jie_option(chapter_exercises_id){
	layer.open({
		  type: 2,
		  title: ['查看题库-本节题目'],
		  area: ['1000px', '800px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_chapter/to_check_jie_option.jr?chapter_exercises_id="+chapter_exercises_id
		  });
}

//以课目查看
function to_check_question_option(chapter_exercises_id){
	layer.open({
		  type: 2,
		  title: ['查看题库-本节题目'],
		  area: ['1000px', '800px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_chapter/to_check_question_option.jr?chapter_exercises_id="+chapter_exercises_id
		  });
}