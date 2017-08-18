<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
//获取专业学费
function get_zhuanye_xuefei(){
	var zhuanye_id=$("#zhuanye_id").val();
	if(zhuanye_id!=null&&zhuanye_id!=""){
		$.post("/edu_condition/get_zhuanye_xuefei.jr",{
			'university_id':zhuanye_id
		},function(data){
			$("#xuefei").html(data);
		})
	}
}
</script>
<table border="1" class="tianjia_xueyuan">
	<tr>
		<td colspan="8"><strong>基本信息</strong></td>
	</tr>
	<tr>
		<td width="12%"><strong>报考专业</strong></td>
		<td colspan="7"><select id="zhuanye_id" name="zhuanye_id" onchange="get_zhuanye_xuefei()">
				<option value="">请选择专业</option>
				<c:forEach items="${universities }" var="univer">
					<option value="${univer.university_id }">${univer.university_zhuanye }</option>
				</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td width="12%"><strong>学员姓名</strong></td>
		<td width="13%"><input type="text" onblur="check_entryUserName()"
			placeholder="请输入姓名" name="entryUserName" id="entryUserName" /></td>
		<td width="12%"><strong>曾用名</strong></td>
		<td width="13%"><input type="text" placeholder="请输入曾用名"
			id="before_name" name="before_name" /></td>
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
	</tr>
	<tr>
		<td><strong>证件类型</strong></td>
		<td><select id="documentType" name="documentType">
				<option value="身份证">身份证</option>
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
	<!-- 	<tr>
		<td><strong>紧急联系人</strong></td>
		<td colspan="2"><input type="text" placeholder="请输入紧急联系人"
			id="jinji_name" name="jinji_name" /></td>
		<td><strong>紧急联系人电话</strong></td>
		<td colspan="4"><input type="text" placeholder="请输入紧急联系人电话"
			id="jinji_phone" name="jinji_phone" /></td>
	</tr> -->
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
		<!-- <td><strong>学习形式</strong></td>
		<td>
		  <select id="" name="">
			<option value="面授">面授</option>
			<option value="远程">远程</option>
		  </select>
		</td> -->
	</tr>
	<tr>
		<td width="12%"><strong>籍贯</strong></td>
		<td colspan="3" width="38%"><select style="width: 95px;"
			onchange="get_jiguan_city()" name="province_jiguang"
			id="province_jiguan">
				<option value="0">请选择省份</option>
				<c:forEach items="${cities }" var="pro">
					<option value="${pro.id }">${pro.name }</option>
				</c:forEach>
		</select> <select style="width: 95px;" id="jiguan_city" name="jiguan_city">
				<option value="0">请选择城市</option>
		</select></td>
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
		<td width="12%"><strong>听课证</strong></td>
		<td width="13%"><select id="tingkezheng" name="tingkezheng">
				<option value="未发">未发</option>
				<option value="已发">已发</option>
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
		<td width="13%"><select id="wangluoxuexika" name="wangluoxuexika">
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
<div class="jianli_chengyuan">
	<div class="reg_item">
		<label>个人简历：</label>
		<div class="jianli">
			<div class="neirong">
				<div class="neirongs">
					<div class="jianli_item">
						<label>工作时间：</label>
						<div class="jianli_items">
							<input class="str_date" value="2017年01月" id="star_time_str"
								name="star_time_str" type="text" /> <span class="zhi">至</span>
							<input class="end_date" value="至今" id="end_time_str"
								name="end_time_str" type="text" />
						</div>
					</div>
					<div class="clear"></div>
					<div class="jianli_item">
						<label>所在单位：</label>
						<div class="jianli_items">
							<input type="text" value="暂无" name="suozaidanwei_str" />
						</div>
					</div>
					<div class="clear"></div>
					<div class="jianli_item">
						<label>证明人：</label>
						<div class="jianli_items">
							<input type="text" value="暂无" name="zhengmingren_str" />
						</div>
					</div>
					<div class="anniu">
						<i class="fa fa-plus" onclick="tjjl(this)"></i>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="reg_item">
		<label>家庭成员：</label>
		<div class="jiating">
			<div class="neirong">
				<div class="neirongs">
					<div class="jiating_item">
						<label>姓名：</label>
						<div class="jiating_items">
							<input type="text" value="暂无" name="xingming_str" />
						</div>
					</div>
					<div class="clear"></div>
					<div class="jiating_item">
						<label>家庭关系：</label>
						<div class="jiating_items">
							<input type="text" value="暂无" name="guanxi_str" />
						</div>
					</div>
					<div class="clear"></div>
					<div class="jiating_item">
						<label>年龄：</label>
						<div class="jiating_items">
							<input type="text" value="暂无" name="nianling_str" />
						</div>
					</div>
					<div class="clear"></div>
					<div class="jiating_item">
						<label>所在单位：</label>
						<div class="jiating_items">
							<input type="text" value="暂无" name="family_danwei_str" />
						</div>
					</div>
					<div class="anniu">
						<i class="fa fa-plus" onclick="tjjt(this)"></i>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div class="caozuo">
	<input type="button" class="btn btn-success btn-xm"
		onclick="save_entry_info_daxue(this)" value="提交"> <input type="button"
		class="btn btn-danger btn-xm" onclick="close_layer()" value="取消">
</div>
<script type="text/javascript">
	function tjjl(obj) {
		var val = $(obj).attr("class").replace(/\s+/g, "");
		if (val == "fafa-plus") {
			$(obj).removeClass("fa-plus");
			$(obj).addClass("fa-minus");
			$(obj)
					.parent()
					.parent()
					.after(
							'<div class="neirongs"><div class="jianli_item"><label>工作时间：</label><div class="jianli_items"><input value="2017年01月" id="star_time_str" name="star_time_str" class="str_date" placeholder="请输入X年X月" type="text" /><span class="zhi">至</span><input id="end_time_str" name="end_time_str"  class="end_date" value="至今" type="text" /></div></div><div class="clear"></div><div class="jianli_item"><label>所在单位：</label><div class="jianli_items"><input type="text" value="暂无" name="suozaidanwei_str"  /></div></div><div class="clear"></div><div class="jianli_item"><label>证明人：</label><div class="jianli_items"><input name="zhengmingren_str" value="暂无" type="text" /></div></div><div class="anniu"><i class="fa fa-plus" onclick="tjjl(this)"></i></div><div class="clear"></div></div>');
			$(obj).parent().css("background-color", "#999");
		} else {
			$(obj).parent().parent().remove();
		}
	}

	function tjjt(obj) {
		var val = $(obj).attr("class").replace(/\s+/g, "");
		if (val == "fafa-plus") {
			$(obj).removeClass("fa-plus");
			$(obj).addClass("fa-minus");
			$(obj)
					.parent()
					.parent()
					.after(
							'<div class="neirongs"><div class="jiating_item"><label>姓名：</label><div class="jianli_items"><input type="text" value="暂无" name="xingming_str" /></div></div><div class="clear"></div><div class="jiating_item"><label>家庭关系：</label><div class="jiating_items"><input name="guanxi_str" value="暂无" type="text" /></div></div><div class="clear"></div><div class="jiating_item"><label>年龄：</label><div class="jiating_items"><input name="nianling_str" type="text" value="暂无"/></div></div><div class="clear"></div><div class="jiating_item"><label>所在单位：</label><div class="jiating_items"><input type="text" name="family_danwei_str" value="暂无" /></div></div><div class="anniu"><i class="fa fa-plus" onclick="tjjt(this)"></i></div><div class="clear"></div></div>');
			$(obj).parent().css("background", "#999");
		} else {
			$(obj).parent().parent().remove();
		}
	}
</script>
<style type="text/css">
.clear {
	clear: both;
}

.jianli_chengyuan {
	margin-top: 30px;
}

.reg_item label {
	width: 78px;
	text-align: right;
	float: left;
	height: 30px;
	line-height: 30px;
	font-weight: bold;
}

.reg_item input {
	width: 243px;
	height: 28px;
	border: #b2b2b2 solid 1px;
	float: left;
	margin-left: 15px;
	padding-left: 5px;
}

.reg_item .jianli, .reg_item .jianli_item .jianli_items, .reg_item .jiating,
	.reg_item .jiating_item .jiating_items {
	float: left;
}

.reg_item .jianli .neirong, .reg_item .jiating .neirong {
	float: left;
	margin-left: 15px;
}

.reg_item .jianli .neirongs, .reg_item .jiating .neirongs {
	padding: 5px;
	width: 600px;
	border: #28A4F4 dashed 2px;
	position: relative;
	margin-bottom: 20px;
}

.reg_item .jianli_item, .reg_item .jiating_item {
	padding-bottom: 15px;
	height: 32px;
}

.reg_item .jianli_item .str_date, .reg_item .jianli_item .end_date {
	float: left;
	width: 96px;
}

.reg_item .jianli_item span.zhi {
	float: left;
	display: block;
	margin: 0px 0px 0px 15px;
	line-height: 30px;
}

.reg_item .jianli .anniu, .reg_item .jiating .anniu {
	float: left;
	width: 25px;
	height: 25px;
	border-radius: 50%;
	background-color: #28A4F4;
	color: #fff;
	text-align: center;
	position: absolute;
	right: -50px;
	top: 0px;
	cursor: pointer;
}

.reg_item .jianli .anniu i, .reg_item .jiating .anniu i {
	margin-top: 2px;
	font-size: 22px;
}
</style>