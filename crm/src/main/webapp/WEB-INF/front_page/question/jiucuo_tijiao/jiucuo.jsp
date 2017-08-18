<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/question/index/reset.css" />
<link rel="stylesheet" type="text/css"
	href="/css/question/select_question/question-common.css" />
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/css/school/back/common/tankuang.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
	<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<script>
//提交纠错
function save_correction(obj,chapter_recourd_id,type){
	 var len = $("input:checkbox[name=type]:checked").length;
	if(len>0){
		var str = "";
		$('input:checkbox[name=type]:checked').each(function(i) {
			if (0 == i) {
				str = $(this).val();
			} else {
				str += ","+$(this).val();
			}
		});
		 $(obj).attr('disabled',"true");
		 $("#myform").ajaxSubmit({
				type : 'POST',
				url : "/front_correction/save_correction.html",
				data:{'str':str,
					'chapter_recourd_id':chapter_recourd_id,
					'question_type':type},
				success : function(data) {
					if(data==1){
						//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
	                    tanchuang('恭喜您，提交成功');
	                    $(obj).attr('disabled',"false");
	                    $('#myform')[0].reset();
	                    $("#tishi").html(150);
					}else{
						tanchuang('很遗憾，提交失败');
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					tanchuang('很遗憾，提交失败');
				}
			});
	}else{
		layer.msg("请选择错误类型！")
	}
}
//提示信息
function tishi_cotent(){
	var correction_content=$("#correction_content").val();
	if(correction_content!=""&&correction_content!=null){
		$("#tishi").html(150-correction_content.length);
	}else{
		$("#tishi").html(150);
	}
}
</script>
<style>
.form-horizontal .form-group .wz_right{
	text-align:right;
}
</style>
	<form enctype="multipart/form-data" id="myform" class="form-horizontal">
     <div class="jiucuofankui">
              <div class="fankui_info">
                <p>为方便我们排查错误，请您详细描述本体错误，例如：</p>
                <ul class="list">
                  <li>
                    <label> 
                      <input type="checkbox" name="type" value="含有错别字" >含有错别字
                    </label>
                  </li>
                  <li>
                    <label> 
                      <input type="checkbox" name="type" value="题目不完整" >题目不完整
                    </label>
                  </li>
                  <li>
                    <label> 
                      <input type="checkbox" name="type" value="解析不正确" >解析不正确
                    </label>
                  </li>
                  <li>
                    <label> 
                      <input type="checkbox" name="type" value="答案不正确">答案不正确
                    </label>
                  </li>
                  <li>
                    <label> 
                      <input type="checkbox" name="type" value="图片不存在">图片不存在
                    </label>
                  </li>
                  <li>
                    <label> 
                      <input type="checkbox" name="type" value="其他错误">其他错误
                    </label>
                  </li>
                </ul>
                <div class="textarea">
                  <textarea maxlength="150" class="textarea2" oninput="tishi_cotent()" name="correction_content" id="correction_content"  placeholder="请输入内容"></textarea>
                </div>
                <p class="tips">还可以输入<span class="num" id="tishi">150</span>个字</p>
              </div>
              <div class="tijiao">
                <input type="button" onclick="save_correction(this,${chapter_recourd_id},'${type }')" value="提交错误"></input>
              </div>
            </div>
	</form>	
