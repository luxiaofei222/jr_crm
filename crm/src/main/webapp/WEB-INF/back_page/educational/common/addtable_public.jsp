<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table border="1" class="tianjia_xueyuan">
	<tr>
		<td colspan="8"><strong>基本信息</strong></td>
	</tr>
	<tr>
		<td width="12%"><strong>学员姓名</strong></td>
		<td width="13%"><input type="text" onblur="check_entryUserName()"
			placeholder="请输入姓名" name="entryUserName" id="entryUserName" /></td>
		<td width="12%"><strong>性别</strong></td>
		<td width="10%"><select id="entryUserSex" name="entryUserSex">
				<option value="男">男</option>
				<option value="女">女</option>
		</select></td>
		<td width="12%"><strong>民族</strong></td>
		<td width="13%"><select id="entryUserNation"
			name="entryUserNation">
				<c:forEach items="${nations }" var="na">
					<option value="${na.nation }">${na.nation }</option>
				</c:forEach>
		</select></td>
		<td width="12%"><strong>文化程度</strong></td>
		<td width="16%"><select id="entryUserEducation"
			name="entryUserEducation">
				<option value="研究生">研究生</option>
				<option value="本科">本科</option>
				<option value="专科">专科</option>
				<option value="专科以下">专科以下</option>
		</select></td>
	</tr>
	<tr>
		<td width="10%"><strong>出生日期</strong></td>
		<td><input type="text" class="layui-input"
			name="entryUserBirthday" id="entryUserBirthday" readonly="readonly" /></td>
		<td><strong>证件类型</strong></td>
		<td><select id="documentType" name="documentType">
				<option value="身份证">身份证</option>
				<option value="护照">护照</option>
		</select></td>
		<td><strong>证件号码</strong></td>
		<td colspan="3"><input type="text" placeholder="请输入证件号码"
			id="documentNumber" name="documentNumber" onblur="check_documentNumber()" /></td>
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
				<option value="党员">党员</option>
				<option value="团员">团员</option>
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
		<td colspan="2"><input type="text" placeholder="请输入邮编"
			id="zipCode" name="zipCode" /></td>
		<td><strong>现住址</strong></td>
		<td colspan="4"><input type="text" placeholder="请输入现居住地"
			id="entryUserAddress" name="entryUserAddress" /></td>
	</tr>
	<tr>
		<td width="12%"><strong>所在单位</strong></td>
		<td colspan="4" width="38%"><input type="text"
			oninput="get_company()" placeholder="请输入所在单位" id="entryUserUnit"
			name="entryUserUnit" />
			<div id="tip">
				<!-- 提示框 -->
			</div></td>
		<td width="12%"><strong>所在职位</strong></td>
		<td colspan="2" width="13%"><input type="text"
			placeholder="请输入职位" id="entryUserPosition" name="entryUserPosition" /></td>
	</tr>
	<tr>
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
//					document.getElementById('entryUserBirthday').onclick = function() {
//						start.elem = this;
//						laydate(start);
//					}
					document.getElementById('inWorkTime').onclick = function() {
						start.elem = this;
						laydate(start);
					}
				});
			})
			</script>
		<td width="12%"><strong>参加工作时间</strong></td>
		<td width="13%"><input type="text" class="layui-input"  onblur="check_wordyears()"
					name="inWorkTime" id="inWorkTime" placeholder="点击获取日期" /></td>
		<td width="12%"><strong>连续工龄</strong></td>
		<td width="13%"><input type="text" readonly="readonly"
			id="workYears" name="workYears" /></td>
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
		<td colspan="8" width="100%"><strong>课程信息</strong></td>
	</tr>
	<tr>
		<td width="12%"><strong>类别</strong></td>
		<td width="10%"><select id="entryCategory" name="entryCategory">
				<option value="非定向">非定向</option>
				<option value="定向">定向</option>
				<option value="委培">委培</option>
				<option value="自筹">自筹</option>
		</select></td>
		<td width="12%"><strong>属性</strong></td>
		<td width="10%"><select>
				<option>统考</option>
				<option>自考</option>
		</select></td>
		<td width="12%"><strong>班型</strong></td>
		<td width="38%" colspan="3"><select id="mianshoubanxing"
			name="mianshoubanxing">
				<option value="精讲班">精讲班</option>
				<option value="普通班">普通班</option>
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
		<td width="13%">
			<select id="zhuanshufuwuka" name="zhuanshufuwuka">
				<option value="">请选择</option>
				<option value="录取">录取</option>
				<option value="未录取">未录取</option>
		</select>
			</td>
			<td width="12%"><strong>报考班次</strong></td>
				<td width="13%"><input type="text" id="baokao_banci"
					name="baokao_banci"onblur="check_baokao_banci()" /></td>
				<td colspan="2"></td>
	</tr>
		<%-- <c:if test="${entryCondition.course_parent_id==9 }">
			<tr class="lunwen">
				<td colspan="8" width="100%"><strong>职称论文</strong></td>
			</tr>
			<tr class="lunwen" >
			    <td><strong>论文题目</strong></td>
			    <td colspan="7"><input type="text" id="lunwen_timu" name="lunwen_timu" /></td>
			</tr>
			<tr class="lunwen" >
			    <td><strong>毕业证书编号</strong></td>
			    <td colspan="5"><input type="text" id="zhengshu_num" name="zhengshu_num" /></td>
			    <td><strong>著作</strong></td>
				<td><select id="lunwen_duzhu" name="lunwen_duzhu">
				<option value="独著">独著</option>
				<option value="合著">合著</option>
				</select></td>
			</tr>
			<tr class="lunwen" >
			    <td><strong>论文期刊</strong></td>
			    <td colspan="3"><input type="text" id="lunwen_qikan" name="lunwen_qikan" /></td>
			    <td><strong>论文日期</strong></td>
			    <td colspan="3"><input type="text" id="lunwen_time" onclick="layui.laydate({elem: this, festival: true})" name="lunwen_time" /></td>
			</tr>
			<tr class="lunwen" >
			    <td><strong>摘要</strong></td>
			    <td colspan="7"><input type="text" id="lunwen_zhaiyao" name="lunwen_zhaiyao" /></td>
			</tr>
		</c:if> --%>
</table>
<div class="caozuo">
			  <input type="button" class="btn btn-success btn-xm" onclick="save_entry_info(this)" value="提交"> 
			  <input type="button" class="btn btn-danger btn-xm" onclick="close_layer()" value="取消">
		</div>