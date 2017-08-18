<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/course_sign/reset.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/common/flat-ui.min.css"/>
<link rel="stylesheet" type="text/css" href="/css/employee/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/font-awesome-4.6.3/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="/css/employee/shenqing.css" />
<link rel="stylesheet" type="text/css" href="/css/employee/top.css" />
<link rel="stylesheet" type="text/css" href="/css/school/back/layui/css/layui.css">
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script type="text/javascript" src="/js/school/back/common/flat-ui.min.js"></script>
<script type="text/javascript" src="/js/school/back/common/application.js"></script>
<script type="text/javascript" src="/js/employeecenter/cropbox.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<title>申请</title>
<script>
//退出系统
function logout_jr(){
	layer.confirm("提示：您好，确定要退出本系统吗？",function(){
		layer.closeAll('dialog');
		$.post("/logout.jr",function(data){
			if(data==1){
				location.href=("/login.jr");
			}
		})
		})
}
//检查请假事由
function check_content(){
	var leave_content=$("#leave_content").val();
	if(leave_content!=null&&leave_content!=""){
		return true;
	}else{
		layer.msg('请输入你要请假的事由！');
		return false;
	}
}
//提交请假申请
function save_leave(obj,employee_id,bumen_id){
	var leave_type=	$("input[type='radio']:checked").val();
	if(typeof(leave_type) == "undefined"){
		layer.msg('请输入你请假的类型！');
	}else{
		var LAY_demorange_s=$("#LAY_demorange_s").val();
		if(LAY_demorange_s!=null&&LAY_demorange_s!=""){
			var LAY_demorange_e=$("#LAY_demorange_e").val();
			if(LAY_demorange_e!=null&&LAY_demorange_e!=""){
				if(check_content()){
					$(obj).attr("disabled","disabled");
					$(obj).val("提交中");
					$("#employeeform").ajaxSubmit({
						type : 'POST',
						url : "/back_leave/save_leave.jr",
						data:{
							'employee_id':employee_id,
							'bumen_id':bumen_id,
							'leave_type':leave_type
						},
						success : function(data) {
							if(data==1){
								layer.msg("提交成功"); 
								$(obj).removeAttr("disabled");
								$(obj).val("提交");
								$('#employeeform')[0].reset();
							}
						},
						error : function(XmlHttpRequest, textStatus, errorThrown) {
							layer.msg("提交失败"); 
							$(obj).removeAttr("disabled");
							$(obj).val("确定");
						}
					});
				}
			}else{
				layer.msg('请选择请假开始的时间！');
			}
		}else{
			layer.msg('请选择请假结束的时间！');
		}
	}
	
}
//离职申请页面
function get_lizhi(){
	$.post("/desert/get_lizhi_page.jr",function(data){
		$("#lizhi_page").html(data);
	})
}
//转正申请
function get_zhuanzheng(){
	$.post("/positive/get_zhuanzheng_page.jr",function(data){
		$("#zhuanzheng_page").html(data);
	})
}
//进度页面
function get_leave_page(){
	$.post("/back_leave/get_employee_leave.jr",function(data){
		$("#jindu").html(data);
	})
}
//忘记打卡页面
function get_punch(){
	$.post("/punch/get_punch_main.jr",function(data){
		$("#daka").html(data);
	})
}
//切换进度项目
function get_progect_page(){
	var change_project=$("#change_project").val();
	if(change_project==0){
		get_leave_page();
	}else if(change_project==1){//离职申请
		$.post("/desert/get_employee_desert.jr",function(data){
			$("#jindu").html(data);
		})
	}else if(change_project==2){//转正申请
		$.post("/positive/get_employee_positive.jr",function(data){
			$("#jindu").html(data);
		})
	}
}
</script>
</head>
<body>
<div class="top">
		<div class="header">
			<span class="left"> <c:if
					test="${not empty employee.employee_pic }">
					<img src="${employee.employee_pic }" class="left" />
				</c:if> <c:if test="${empty employee.employee_pic }">
					<img src="/images/employee/picture.jpg" class="left" />
				</c:if>
				<div class="left">
					<p>${employee.employee_name }</p>
					 <c:if test="${empty employee.employee_trades }">
         <span>这个人很懒，还没有设置...</span>
     </c:if> <c:if test="${not empty employee.employee_trades }">
       <span title="${employee.employee_trades}">${employee.employee_trades}</span>
     </c:if>
					
				</div>
			</span> <span class="right">
				<ul class="left">
					<li onclick="location.href='/em_log/get_back_log.jr?employee_id=${employee.employee_id}'"><img src="/images/personal/li-1.png" />
					<p>日志</p></li>
					<li onclick="location.href='/back_task/get_back_task.jr?employee_id=${employee.employee_id}'"><img src="/images/personal/li-2.png" />
					<p>任务</p></li>
					<li onclick="location.href='/back_leave/get_back_leave.jr?employee_id=${employee.employee_id}'" style="color: #00b8ee"><img src="/images/personal/li-3.png" />
					<p>申请</p></li>
					<li onclick="location.href='/center/to_get_employee.jr?employee_id=${employee.employee_id}'"><img src="/images/personal/li-4.png" />
					<p>修改资料</p></li>
					<li onclick="location.href='/admin.jr'"><img src="/images/personal/li-5.png" />
					<p>进入系统</p></li>
				</ul>
				<div class="left">
					<span class="left">|</span><i
						class="left icon iconfont icon-shouyeshouye" onclick="location.href='/center/get_employee_center.jr'" ></i><i
						class="left icon iconfont icon-guanbi" onclick="logout_jr()"></i>
				</div>
			</span>
		</div>
		<div class="clear"></div>
	</div>

<div class="content">
  <div class="side_nav left">
    <ul>
      <li class="active" onclick="location.href='/back_leave/get_back_leave.jr?employee_id=${employee.employee_id}'"></li>
      <li onclick="get_lizhi()"></li>
      <li onclick="get_zhuanzheng()"></li>
      <li onclick="get_leave_page()"></li>
      <li onclick="get_punch()"></li>
    </ul>
  </div>
  <div class="side_cont left">
  <form enctype="multipart/form-data" id="employeeform"
						class="form-horizontal">
    <div class="cont qingjia_sq" style="display:block;">
      <div class="kind">
        <h4>请假类别：</h4>
        <div class="kinds">
          <ul>
            <li><input type="radio" value="1" name="leave_type_str" /><img src="/images/personal/check.jpg" class="input_style" /><p>事假</p></li>
            <li><input type="radio" value="2" name="leave_type_str" /><img src="/images/personal/check.jpg" class="input_style" /><p>病假</p></li>
            <li><input type="radio"  value="3" name="leave_type_str" /><img src="/images/personal/check.jpg" class="input_style" /><p>婚假</p></li>
            <li><input type="radio" value="4" name="leave_type_str" /><img src="/images/personal/check.jpg" class="input_style" /><p>产假</p></li>
            <li><input type="radio" value="5" name="leave_type_str" /><img src="/images/personal/check.jpg" class="input_style" /><p>丧假</p></li>
            <li><input type="radio" value="6" name="leave_type_str" /><img src="/images/personal/check.jpg" class="input_style" /><p>其他</p></li>
            <li><input type="radio" value="7" name="leave_type_str" /><img src="/images/personal/check.jpg" class="input_style" /><p>倒休</p></li>
            <li><input type="radio" value="8" name="leave_type_str" /><img src="/images/personal/check.jpg" class="input_style" /><p>年假</p></li>
          </ul>
        </div>
        <div class="clear"></div> 
      </div><!-- 请假类别 -->
      <div class="reason">
        <h4>请假事由：</h4> 
        <textarea placeholder="请输入请假事由..." onblur="check_content()" id="leave_content" name="leave_content"></textarea> 
      </div><!-- 请假事由 -->
      <div class="time">
        <h4>请假时间：</h4> 
        <div class="time_input">
          <span class="str_time"><i class="icon iconfont icon-riqi"></i><input type="text" name="start_time_str" placeholder="请选择开始时间" id="LAY_demorange_s" /></span>
          <span>至</span>
          <span class="end_time"><i class="icon iconfont icon-riqi"></i><input type="text" name="end_time_str" placeholder="请选择结束时间" id="LAY_demorange_e" /></span>
        </div> 
      </div><!-- 请假时间 -->
      <div class="reception">
        <h4>请假提交至：</h4> 
        <div class="receptions">
          <!-- <input type="text" disabled="disabled" value="所在部门" /> -->
          <select id="jingli_id" name="jingli_id">
          <c:forEach items="${employees }" var="employee_o">
            <option value="${employee_o.employee_id }">${employee_o.employee_name }</option>
            </c:forEach>
          </select>
           <h5 style="color:#ea592d;margin-top:5px;">注：请提交至你的直接领导，如果你是领导请提交给自己,否则无效!</h5> 
        </div>
      </div><!-- 请假给谁 -->
      <div class="send">
        <input type="button" onclick="save_leave(this,${employee.employee_id},${employee.bumen_id })" value="提交" />
        <input type="reset" value="取消" />
      </div>
    </div>
    </form>
    <div class="cont lizhi_sq" id="lizhi_page">
    <!-- 离职申请 -->
    </div>
    <div class="cont zhuanzheng_sq" id="zhuanzheng_page">
      <!-- 转正申请 -->
    </div>
    <div class="cont shenpi_jindu" id="jindu">
     <!--  审批进度 -->
    </div>
     <div class="cont wangji_daka" id="daka">
     <!--  忘记打卡 -->
    </div>
  </div>
  <div class="clear"></div> 
</div>
</body>
<script type="text/javascript">
$(function(){
	 $(".side_nav ul li").click(function(){
		  var index = $(this).index();
		  $(".cont").eq(index).css("display","block").siblings().css("display","none");
		  $(".side_nav ul li").eq(index).addClass("active").siblings().removeClass("active");
		  })
	 $(".kinds ul li").click(function(){
		  $(this).addClass("li_active").siblings().removeClass("li_active");
		  $(this).children(".input_style").attr("src", "/images/personal/checked.jpg");
		  $(this).children("input").attr("checked","checked");
		  $(".kinds ul li").not(this).children(".input_style").attr("src", "/images/personal/check.jpg");
		  })
})


layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  var start = {
	    max: '2099-06-16 23:59:59'
		,istime: true
		,format: 'YYYY-MM-DD hh:mm:ss'
	    ,choose: function(datas){
	      end.min = datas; //开始日选好后，重置结束日的最小日期
	      end.start = datas //将结束日的初始值设定为开始日
	    }
	  };
	  
	  var end = {
	   	max: '2099-06-16 23:59:59'
		,format: 'YYYY-MM-DD hh:mm:ss'
	    ,istime: true
	    ,choose: function(datas){
	      start.max = datas; //结束日选好后，重置开始日的最大日期
	    }
	  };
	  
	  document.getElementById('LAY_demorange_s').onclick = function(){
	    start.elem = this;
	    laydate(start);
	  }
	  document.getElementById('LAY_demorange_e').onclick = function(){
	    end.elem = this
	    laydate(end);
	  }
	  
	});
</script>
</html>