<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/js/question/question-common.js"></script>
<script>
 //跳转做题页面
 function to_chapter_question(question_course_id,chapter_exercises_id){
	var question_type= $(".on_type").attr("value");
	var question_number= $(".on_number").attr("value");
	location.href="/front_chapter/to_chapter_question.html?question_course_id="+question_course_id+"&question_type="+question_type+"&question_number="+question_number+"&chapter_exercises_id="+chapter_exercises_id;
 }
 </script>
<div class="zhangjie_zuoti">
	<div class="zhangjie_zuoti_title">
		<h1>${question_course_name }
			<c:if test="${type=='zhang' }">   
      第${chapterExercises.chapter_number_str }章
  </c:if>
			<c:if test="${type=='jie' }">   
      第${chapterExercises.chapter_number_str }节
  </c:if>
			${chapterExercises.chapter_name } 练习
		</h1>
		<div class="moni mo_ni">
			<ul>
				<span>练习模拟：</span>
				<li value="all"><img src="/images/question/chapter/ico-yes.png" />全部试题</li>
				<li value="weizuo" class="on_type"><img
					src="/images/question/chapter/ico-yes.png" />未做试题</li>
				<li value="cuowu"><img
					src="/images/question/chapter/ico-yes.png" />错误试题</li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="moni shiti">
			<ul>
				<span>试题数量：</span>
				<li value="5"><img src="/images/question/chapter/ico-yes.png" />5</li>
				<li class="on_number" value="10"><img
					src="/images/question/chapter/ico-yes.png" />10</li>
				<li value="15"><img src="/images/question/chapter/ico-yes.png" />15</li>
				<li value="20"><img src="/images/question/chapter/ico-yes.png" />20</li>
				<li value="25"><img src="/images/question/chapter/ico-yes.png" />25</li>
				<li value="30"><img src="/images/question/chapter/ico-yes.png" />30</li>
				<li value="35"><img src="/images/question/chapter/ico-yes.png" />35</li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="str_zuoti">
			<a href="javascript:void(0)"
				onclick="to_chapter_question(${chapterExercises.question_course_id},${chapterExercises.chapter_exercises_id })">开始做题</a>
		</div>
		<div class="tixing_jieshao">
			<h2>
				<i class="icon iconfont icon-dingzi"></i>题型介绍
			</h2>
			<div class="tips">
				<h3>小贴士</h3>
				<p>未做试题：筛选本章节下未做的试题</p>
				<p>已做试题：复习本章节下已经做过了的试题，复习时会清除掉上一次练习的答案纪录</p>
				<p>错误试题：复习本章节下做错了的试题，复习时会清除掉上一次练习的答案纪录</p>
			</div>
		</div>
	</div>
</div>
