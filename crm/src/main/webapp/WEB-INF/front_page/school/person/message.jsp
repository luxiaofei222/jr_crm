<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script>
//检查标题
function check_title(){
	var  message_title=$("#message_title").val();
	if(message_title!=null&&message_title!=""){
		if(message_title.length>30){
			$(".error").html("标题的字数超过30！");
			return false;
		}else{
			$(".error").html("");
			return true;
		}
	}else{
		$(".error").html("请输入标题！");
		return false;
	}
}
//检查消息内容
function check_content(){
	var message_content=$("#message_content").val();
	if(message_content!=null&&message_content!=""){
		if(message_content.length>300){
			$(".error").html("标题的字数超过300！");
			return false;
		}else{
			$(".error").html("");
			return true;
		}
	}else{
		$(".error").html("请输入内容！");
		return false;
	}
}
//提示信息
function tishi_cotent(){
	var message_content=$("#message_content").val();
	if(message_content!=""&&message_content!=null){
		$("#tishi").html("您已经输入<font style='color:red;'>"+message_content.length+"</font>字");
	}else{
		$("#tishi").html("请输入您要评论的内容！")
	}
}
//发送消息
function send_save_message(){
	if(check_title()&&check_content()){
		 $("#myform").ajaxSubmit({
				type : 'POST',
				url : "/sc_message/save_message.html",
				success : function(data) {
					if(data==1){
						alert("恭喜您，发送成功！");
						$('#myform')[0].reset();
						$("#tishi").html("");
					}else{
						alert("提示：很遗憾，发送失败！");
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					alert("添加失败");
				}
			});
	}
	
}
</script>
<h1>请求与留言</h1> 
    <form  id="myform" class="form1">
      <div class="person_content_text">
        <div class="error"></div>
        <ul class="person_message">
          <li>
            <span>标题：</span>
            <input type="text" id="message_title" onblur="check_title()" name="message_title" placeholder="标题应将内容控制在30字以内" />
          </li>
          <div class="clear"></div>
          <li>
            <span>内容：</span>
            <textarea id="message_content" onblur="check_content()" oninput="tishi_cotent()" name="message_content" placeholder="应将内容控制在500字以内，详细说明您要提交的内容"></textarea>
            <p id="tishi" style="clear:left;margin-left:553px;"></p>
          </li>
          <div class="clear"></div>
          <li>
            <span>类型：</span>
            <ul>
              <li>
                <input type="radio" id="radio-1-1" value="档案变更" name="message_type" class="regular-radio" checked />
                <label for="radio-1-1"></label><span class="">考生档案变更（考生姓名、联系方式等信息变更）</span>
              </li>
              <li>
                <input type="radio" id="radio-1-2"value="档案管理" name="message_type" class="regular-radio" />
                <label for="radio-1-2"></label><span class="">考务档案管理（延期考试申请）</span>
              </li>
              <li>
                <input type="radio" id="radio-1-3" value="服务请求" name="message_type" class="regular-radio" />
                <label for="radio-1-3"></label><span class="">服务请求（服务大厅专属帐户登录失败、网络课程使用失败、职业测评使用失败等）</span>
              </li>
              <li>
                <input type="radio" id="radio-1-4" value="推荐学员" name="message_type" class="regular-radio" />
                <label for="radio-1-4"></label><span class="">推荐学员（提供学习需求，享受学费优惠）</span>
              </li>
              <li>
                <input type="radio" checked="checked" id="radio-1-5" value="投诉建议"  name="message_type" class="regular-radio" />
                <label for="radio-1-5"></label><span class="">学员投诉建议</span>
              </li>
            </ul>
          </li>
          <div class="clear"></div>
          <li>
            <span>&nbsp;</span>
            <input type="button" onclick="send_save_message()" value="发送信件" class="send" />
          </li>
          <div class="clear"></div>
        </ul>
        <div class="tips">注：我们将在一个工作日内通过学员服务大厅回复您的留言（周六日除外），请您仔细查看您的收件箱。</div>
      </div>
    </form> 