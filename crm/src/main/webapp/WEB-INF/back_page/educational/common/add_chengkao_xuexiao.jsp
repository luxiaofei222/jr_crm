<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="/css/school/front/course_video/css/font-awesome.min.css" rel="stylesheet">
<script>
//获取成考专业
function get_kelei(){
	var zhuanye_id=$("#zhuanye_id").val();
	if(zhuanye_id!=null&&zhuanye_id!=""&&zhuanye_id!=0){
		$.post("/edu_condition/get_zhuanye_kelei.jr",{
			'university_id':zhuanye_id
		},function(data){
			$("#kelei").val(data);
		})
	}
}
$(function(){
	$(".fa-question-circle").mouseover(function(){
		$(this).next().css("display","block");
	});
	$(".fa-question-circle").mouseout(function(){
		$(this).next().css("display","none");
	})
})
</script>
<style>
<!--
.fa-question-circle {
    color: #8bc34a;
    cursor: pointer;
    font-size: 15px;
    position: absolute;
    right: 3px;
    bottom: 0px;
}
td.question {
    position: relative;
} 
td.question p {
    font-size: 11px;
    color: #fff;
    width: 180px;
	background-color: #8bc34a;
	border-radius: 5px;
	position: absolute;
	z-index: 9;
	top: 35px;
	left: 10px;
	display: none;
	padding: 5px;
	text-align: left; 
}
#feiyong ul li:first-child {
    width: 100px;
}
#feiyong ul li:nth-child(2) {
    width: 212px;
}
#feiyong ul li:nth-child(3) {
    width: 110px;
}
#feiyong ul li:nth-child(4),#feiyong ul li:nth-child(6) {
    width: 103px;
}
#feiyong ul li:nth-child(5) {
    width: 136px;
}
#feiyong ul li:nth-child(7) {
    width: 135px;
}
-->
</style>
<table border="1" class="tianjia_xueyuan">
	<tr>
		<td colspan="8"><strong>基本信息</strong></td>
	</tr>
	<tr>
		<td width="12%"><strong>学员姓名</strong></td>
		<td width="13%"><input type="text" onblur="check_entryUserName()" placeholder="请输入姓名" name="entryUserName" id="entryUserName" /></td>
		<td width="12%"><strong>性别</strong></td>
		<td width="10%"><select id="entryUserSex" name="entryUserSex">
				<option value="男">男</option>
				<option value="女">女</option>
		</select></td>
		<td width="12%"><strong>民族</strong></td>
		<td width="16%"><select id="entryUserNation"
			name="entryUserNation">
				<c:forEach items="${nations }" var="na">
					<option value="${na.nation }">${na.nation }</option>
				</c:forEach>
		</select></td>
		<td width="12%"><strong>文化程度</strong></td>
		<td width="16%">
		  <select id="entryUserEducation" name="entryUserEducation">
				<option value="职业高中">职业高中</option>
				<option value="中专">中专</option>
				<option value="普通高中">普通高中</option>
				<option value="本科以上">本科以上</option>
				<option value="专科">专科</option>
				<option value="同等学历（高中）">同等学历（高中）</option>
				<option value="技工学校">技工学校</option>
		  </select>
		</td>
	</tr>
	<tr>
		<td><strong>证件类型</strong></td>
		<td><select id="documentType" name="documentType">
				<option value="身份证">身份证</option>
				<!-- <option value="护照">护照</option>
				<option value="士兵证">士兵证</option> -->
		</select></td>
		<td><strong>证件号码</strong></td>
		<td colspan="3"><input type="text"
			onblur="check_documentNumber()" placeholder="请输入证件号码"
			id="documentNumber" name="documentNumber" /></td>
		<td width="10%"><strong>出生日期</strong></td>
		<td><input type="text" class="layui-input"
			name="entryUserBirthday" id="entryUserBirthday" readonly="readonly" /></td>
	</tr>
	<tr>
	  <td><strong>填报专业</strong></td>
	  <td colspan="3">
	    <select id="zhuanye_id" name="zhuanye_id" onchange="get_kelei()">
				<option value="0">请选择专业</option>
		</select>
	  </td>
	  <td><strong>科类</strong></td>
	  <td>
	    <input type="text" class="layui-input" id="kelei" readonly="readonly" />
	  </td>
	  <td><strong>学习形式</strong></td>
	  <td>
	    <select id="studry_type" name="studry_type">
				<option value="函授">函授</option>
				<option value="业余">业余</option>
				<option value="脱产">脱产</option>
		</select>
	  </td>
	</tr>
   <tr>
	  <td><strong>毕业类别</strong></td>
	  <td>
	    <select id="biye_leibie" name="biye_leibie">
		  <option value="成人高中">成人高中</option>
		  <option value="职业高中">职业高中</option>
		  <option value="普通高中">普通高中</option>
		  <option value="成人中专">成人中专</option>
		  <option value="职业中专">职业中专</option>
		  <option value="普通中专">普通中专</option>
		  <option value="技校">技校</option>
		  <option value="普通高校">普通高校</option>
		  <option value="成人高校">成人高校</option>
		</select>
	  </td>
	  <td><strong>职业类别</strong></td>
	  <td colspan="3">
	    <select id="zhiye_leibie" name="zhiye_leibie">
		  <option value="军人">军人</option>
		  <option value="商业服务业人员">商业服务业人员</option>
		  <option value="办事人员和有关人员">办事人员和有关人员</option>
		  <option value="农林牧渔水产业生产人员">农林牧渔水产业生产人员</option>
		  <option value="不便分类的其他从业人员">不便分类的其他从业人员</option>
		  <option value="生产运输设备操作人员及有关人员">生产运输设备操作人员及有关人员</option>
		  <option value="国家机关党群组织企业事业单位负责人">国家机关党群组织企业事业单位负责人</option>
		  <option value="专业技术人员">专业技术人员</option>
		</select>
	  </td>
	  <td class="question"><strong>农民工考生</strong><i class="fa fa-question-circle"></i><p>农民工考生指经工会系统审验合格的在本地乡镇企业或进入城镇务工的我省农村户籍人员，与企业签订1年及以上劳动合同。</p></td>
	  <td>
	    <select id="is_nongmin" name="is_nongmin">
				<option value="否">否</option>
				<option value="是">是</option>
		</select>
	  </td>
	</tr>
	<tr>
	  <td><strong>确认地点</strong></td>
	  <td colspan="7"><input type="text" placeholder="请输入确认地点" id="queendian" name="queendian" /></td>
	</tr>
	<tr>
		<td><strong>手机号</strong></td>
		<td><input type="text" onblur="check_entryUserPhone()"
			placeholder="请输入手机号" id="entryUserPhone" name="entryUserPhone" /></td>
		<td><strong>电子邮箱</strong></td>
		<td colspan="3"><input type="text" onblur="check_entryUserMail()"
			placeholder="请输入电子邮箱" id="entryUserMail" name="entryUserMail" /></td>
		<td width="12%"><strong>政治面貌</strong></td>
		<td width="13%"><select id="entryPolitical" name="entryPolitical">
				<option value="共产党员">共产党员</option>
				<option value="共青团员">共青团员</option>
				<option value="预备役党员">预备役党员</option>
				<option value="群众">群众</option>
		</select></td>
	</tr>
	<tr>
		<td><strong>紧急联系人</strong></td>
		<td colspan="2"><input type="text" placeholder="请输入紧急联系人"
			id="jinji_name" name="jinji_name" /></td>
		<td><strong>紧急联系人电话</strong></td>
		<td colspan="4"><input type="text" placeholder="请输入紧急联系人电话"
			id="jinji_phone" name="jinji_phone" /></td>
	</tr>
	<tr>
		<td><strong>邮编</strong></td>
		<td><input type="text" placeholder="请输入邮编" id="zipCode"
			name="zipCode" /></td>
		<td><strong>联系QQ</strong></td>
		<td colspan="2"><input type="text" placeholder="请输入联系QQ" id="user_qq"
			name="user_qq" /></td>
		<td><strong>健康状况</strong></td>
		<td colspan="2"><select id="health" name="health">
				<option value="健康">健康</option>
				<option value="疾病">疾病</option>
		</select></td>
	</tr>
	<tr>
		<td><strong>所在单位</strong></td>
		<td><input type="text" oninput="get_company()" placeholder="请输入所在单位" id="entryUserUnit"
			name="entryUserUnit" />
			<div id="tip">
				<!-- 提示框 -->
			</div></td>
		<td><strong>所在职位</strong></td>
		<td><input type="text" placeholder="请输入所在职位" id="entryUserPosition" name="entryUserPosition"  /></td>
		<td><strong>参加工作时间</strong></td>
		<script>
			$(function() {
				layui.use('laydate', function() {
					var laydate = layui.laydate;
					var start = {
						festival : true,
						istoday : true,
					    choose: function(datas){  
					    	check_work_time();
					    }
					};
					document.getElementById('inWorkTime').onclick = function() {
						start.elem = this;
						laydate(start);
					}
				});
			})
			</script>
		<td><input type="text" class="layui-input" onblur="check_wordyears()"
					name="inWorkTime" id="inWorkTime" placeholder="点击获取日期" /></td>
	    <td><strong>连续工龄</strong></td>
		<td><input type="text" placeholder="请输入连续工龄" readonly="readonly"
			id="workYears" name="workYears"/></td>
	</tr>
	<tr>
		<td width="12%"><strong>现住址</strong></td>
		<td colspan="3" width="38%"><input type="text" placeholder="请输入现住址" id="entryUserAddress" name="entryUserAddress" /></td>
		<td width="12%"><strong>户口所在地</strong></td>
		<td colspan="3" width="38%"><select style="width: 95px;"
			onchange="get_city()" name="province" id="province">
				<option value="0">请选择省份</option>
				<c:forEach items="${cities }" var="pro">
					<option value="${pro.id }">${pro.name }</option>
				</c:forEach>
		</select> <select style="width: 95px;" onchange="get_area()" id="user_city"
			name="user_city">
				<option value="0">请选择城市</option>
		</select> <select style="width: 95px;" id="entryUserArea" name="entryUserArea">
				<option value="0">请选择地区</option>
		</select></td>
	</tr>
	<tr>
		<td colspan="8" width="100%"><strong>财务信息</strong></td>
	</tr>
	<tr>
		<td><strong><strong>收费项目</strong></strong></td>
				<td colspan="2"><strong>应缴</strong></td>
				<td><strong>组织费</strong></td>
				<td><strong>降费</strong></td>
				<td><strong>实缴</strong></td>
				<td><strong>支付方式</strong></td>
				<td><strong>欠费</strong></td>
	</tr>
	<tr>
		<td colspan="8" width="100%">
			<div id="feiyong">
				<!--切换各种费用  -->
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="8" width="100%"><strong>资料领取</strong></td>
	</tr>
	<tr>
		<td width="12%"><strong>证书</strong></td>
		<td width="13%"><select id="tingkezheng" name="tingkezheng">
				<option value="未领取">未领取</option>
				<option value="已领取">已领取</option>
		</select></td>
		<td width="12%"><strong>教材</strong></td>
		<td width="13%"><select id="jiaocai" name="jiaocai">
				<option value="未发">未发</option>
				<option value="已发">已发</option>
		</select></td>
		<td width="12%"><strong>教辅</strong></td>
		<td width="13%"><select id="jiaofu" name="jiaofu">
				<option value="未发">未发</option>
				<option value="已发">已发</option>
		</select></td>
		<td width="12%"><select id="jiaofulingqu" name="jiaofulingqu">
				<option value="未领">未领取</option>
				<option value="已领">已领取</option>
		</select></td>
		<td width="16%"></td>
	</tr>
	<tr>
		<td width="12%"><strong>成绩</strong></td>
		<td width="13%">
		<select id="wangluoxuexika" name="wangluoxuexika">
				<option value="">请选择</option>
				<option value="合格">合格</option>
				<option value="不合格">不合格</option>
		</select></td>
		<td width="12%"><strong>是否录取</strong></td>
		<td width="13%"><select id="zhuanshufuwuka" name="zhuanshufuwuka">
				<option value="">请选择</option>
				<option value="录取">录取</option>
				<option value="未录取">未录取</option>
		</select></td>
			<td width="12%"><strong>报考班次</strong></td>
				<td width="13%"><input type="text" id="baokao_banci"
					name="baokao_banci" onblur="check_baokao_banci()" /></td>
				<td colspan="2"></td>
	</tr>
</table>
<div class="caozuo">
	<input type="button" class="btn btn-success btn-xm"
		onclick="save_chengkao_entry_info(this)" value="提交"> <input type="button"
		class="btn btn-danger btn-xm" onclick="close_layer()" value="取消">
</div>
