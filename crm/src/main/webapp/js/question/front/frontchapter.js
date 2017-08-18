//题库首页
function get_question_index(course_id){
	location.href="/questionindex/get_selest_question_index.html?course_id="+course_id;
}
//章节练习
function get_chapter_list(question_course_id){
	location.href="/front_chapter/get_chapter_list.html?question_course_id="+question_course_id;
}
//加载动画
function jiazai(){
	$("#chapter_list").html("<div id='preloader_4'><span></span><span></span><span></span><span></span><span></span></div>");
}
//练习记录
function get_chapter_record_main(question_course_id){
	location.href="/front_chapter/get_chapter_record_main.html?question_course_id="+question_course_id;
}
//练习记录分页
function chapter_jump_page(page){
	jiazai();
	$.post("/front_chapter/get_chapter_record_list.html",{
		'pageNumber':page,
		'limit':10
	},function(data){
		$("#chapter_list").html(data);
	})
}
//真题记录
function zhenti_jump_page(question_course_id){
	jiazai();
	$.post("/front_zhenti/get_zhenti_record_list.html",{
		'question_course_id':question_course_id
	},function(data){
		$("#chapter_list").html(data);
	})
}
//模拟考试记录
function monikaoshi_jump_page(question_course_id){
	jiazai();
	$.post("/front_zhenti/get_monikaoshi_record_list.html",{
		'question_course_id':question_course_id
	},function(data){
		$("#chapter_list").html(data);
	})
}

//跳转输入的页面
function to_jump_shuru_page(page){
	var page_number=$("#page_number").val();
	if(page_number!=null&&page_number!=""){
		if(isNaN(page_number)){
			layer.msg("请输入数字！")
		}else{
			if(page_number<=page&&page_number!=0){
				jiazai();
				$.post("/front_chapter/get_chapter_record_list.html",{
					'pageNumber':page_number,
					'limit':10
				},function(data){
					$("#chapter_list").html(data);
				})
			}else{
				layer.msg("您输入的页码 不存在！")
			}
		}
	}else{
		layer.msg("请输入您要跳转的页数！")
	}
}
//练习解析记录
function get_chapter_jiexi_jilu(question_course_id,chapter_exercises_id,question_order_number){
	location.href="/front_chapter/get_chapter_jiexi_jilu.html?question_course_id="+question_course_id+"&chapter_exercises_id="+chapter_exercises_id+"&question_order_number="+question_order_number;
}
//再做一次-章节练习
function get_reset_chapter_question(question_course_id,chapter_exercises_id,question_order_number){
	location.href="/front_chapter/to_reset_chapter_question.html?question_course_id="+question_course_id+"&chapter_exercises_id="+chapter_exercises_id+"&question_order_number="+question_order_number;
}
//能力评估报告
function get_nengli_main(question_course_id){
	location.href="/front_zhenti/get_nengli_main.html?question_course_id="+question_course_id;
}
//错题记录
function get_cuoti_record(question_course_id){
	$.post("/front_chapter/get_cuoti_record.html",{
		'question_course_id':question_course_id
	},function(data){
		$("#cuoti_shoucang_list").html(data);
	})
}
//查看解析
function chakan_jiexi(obj){
	$(obj).parent().children(".answer_inline").toggle(200);
	$(obj).parent().next(".fold-bd:first").toggle(200);
	if($.trim($(obj).text())=="查看解析"){
		$(obj).html("隐藏解析<i class='fa fa-angle-double-up'></i>");
	}else{
		$(obj).html("查看解析<i class='fa fa-angle-double-down'></i>");
	}
//	$(obj).find("i").toggleClass("tubiaoz");
}
//收藏列表
function get_user_collection(question_course_id){
	location.href="/front_chapter/get_user_collection_question.html?question_course_id="+question_course_id;
}
//初始化调用layui的layer
$(function(){
	layui.use('layer', function(){
	  	var layer = layui.layer;
		  //点击添加弹出层事件
	});  
});
//本题纠错
function get_correction(chapter_recourd_id){
	 layer.open({
		  type: 2,
		  title: ['试题纠错反馈'],
		  area: ['630px', '400px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/front_correction/get_correction.html?chapter_recourd_id="+chapter_recourd_id
		  });
}
//历年真题
function get_zhenti_year(question_course_id){
	location.href="/front_zhenti/get_zhenti_year_main.html?question_course_id="+question_course_id;
}
//模拟考试
function get_moni_kaoshi(question_course_id){
	location.href="/front_zhenti/get_moni_kaoshi_main.html?question_course_id="+question_course_id;
}
//历年真题-保存进度
function baocunjindu_zhenti(obj,tishu,zhenti_id,question_course_id){
	
	var shengyu_number=$("#shengyu_number").text();
	if(tishu>shengyu_number){
		$(".coverfull").show();
		var str = "";
		var str_duo = "";
		var jineng_op="";
		var jineng_id="";
		//技能选择题
		$('.exam-main ul').each(function(i){
			var qid=$(this).attr("jinengqid");
			if(typeof(qid) != "undefined"){
			
			if (0 == i) {
				var str_duo_op = "";
				if($(this).attr("class")=="multi_choices"){//多选
					$('input:checkbox[name='+qid+']:checked').each(function(i) {
							if (0 == i) {
								str_duo_op = $(this).val();
							} else {
								str_duo_op += "."+$(this).val();
							}
					});
				}else{
					$('input:radio[name='+qid+']:checked').each(function(i) {
							if (0 == i) {
								str_duo_op = $(this).val();
							}
					});
				}
				
				if(str_duo_op==""||str_duo_op==null){
					str_duo_op="无";
				}
				jineng_op=str_duo_op;
				jineng_id = qid;
			} else {
				var str_duo_op = "";
				if($(this).attr("class")=="multi_choices"){//多选
					$('input:checkbox[name='+qid+']:checked').each(function(i) {
							if (0 == i) {
								str_duo_op = $(this).val();
							} else {
								str_duo_op += "."+$(this).val();
							}
					});
					if(str_duo_op==""||str_duo_op==null){
						str_duo_op="无";
					}
					jineng_op+=","+str_duo_op
				}else{
					$('input:radio[name='+qid+']:checked').each(function(i) {
							if (0 == i) {
								str_duo_op =$(this).val();
							} 
					});
					if(str_duo_op==""||str_duo_op==null){
						str_duo_op="无";
					}
					jineng_op+=","+str_duo_op
				}
				jineng_id += ","+qid;
			}
		}
		})
			//案例简答题
		$('.anlijiandakuang').each(function(i){
			var qid=$(this).attr("jinengqid");
			var str_duo_op=$(this).val().replace(",", "，");
			if(str_duo_op==""||str_duo_op==null){
				str_duo_op="无";
			}
			jineng_op+=","+str_duo_op
			jineng_id += ","+qid;
		})
		$('.exam-main ul').each(function(i){
			var qid=$(this).attr("qid");
			if (0 == i) {
				var str_duo_op = "";
				if($(this).attr("class")=="multi_choices"){//多选
					$('input:checkbox[name='+qid+']:checked').each(function(i) {
							if (0 == i) {
								str_duo_op = $(this).val();
							} else {
								str_duo_op += "."+$(this).val();
							}
					});
				}else{
					$('input:radio[name='+qid+']:checked').each(function(i) {
							if (0 == i) {
								str_duo_op = $(this).val();
							}
					});
				}
				if(str_duo_op==""||str_duo_op==null){
					str_duo_op="无";
				}
				str_duo=str_duo_op;
				str = qid;
			} else {
				var str_duo_op = "";
				if($(this).attr("class")=="multi_choices"){//多选
					$('input:checkbox[name='+qid+']:checked').each(function(i) {
							if (0 == i) {
								str_duo_op = $(this).val();
							} else {
								str_duo_op += "."+$(this).val();
							}
					});
					if(str_duo_op==""||str_duo_op==null){
						str_duo_op="无";
					}
					str_duo+=","+str_duo_op
				}else{
					$('input:radio[name='+qid+']:checked').each(function(i) {
							if (0 == i) {
								str_duo_op =$(this).val();
							} 
					});
					if(str_duo_op==""||str_duo_op==null){
						str_duo_op="无";
					}
					str_duo+=","+str_duo_op
				}
				str += ","+qid;
			}
			})
		$('.anlikuang').each(function(i){
			var qid=$(this).attr("qid");
			var str_duo_op=$(this).val().replace(",", "，");
			if(str_duo_op==""||str_duo_op==null){
				str_duo_op="无";
			}
			str_duo+=","+str_duo_op
			str += ","+qid;
		})
		  $.post("/front_zhenti/save_zhenti_jindu.html",{
				'question_course_id':question_course_id,
				'zhenti_id':zhenti_id,
				'recourd_state':2,
				'answer_length':miao-1,
				'str':str,//题目ID
				'str_duo':str_duo,//选项
				'jineng_op':jineng_op,
				'jineng_id':jineng_id
			},function(data){
				if(data==1){
					get_zhenti_year(question_course_id);//跳转历年真题目录
				}else if(data==2){
					get_moni_kaoshi(question_course_id);//跳转模拟考试
				}else{
					layer.msg("提交失败！")
				}
			}) 
	}else{
		layer.msg("您还没有做题！");
	}
}