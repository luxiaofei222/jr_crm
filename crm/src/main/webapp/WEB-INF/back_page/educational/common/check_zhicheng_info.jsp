<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/reset.css">
<link rel="stylesheet" href="/css/school/back/common/bootstrap.min.css" />
<link rel="stylesheet"
	href="/css/school/back/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="/css/school/back/common/f_tankuang.css" />
<link rel="stylesheet" type="text/css"
	href="/css/school/back/layui/css/layui.css">
<link rel="stylesheet" type="text/css"
	href="/css/edu/entryinfo/e_tankuang.css" />
<script type="text/javascript" src="/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/js/school/back/common/bootstrap.min.js"></script>
<script src="/js/common/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript" src="/css/school/back/layui/layui.js"></script>
<script src="/js/school/back/common/tanchuang.js" type="text/javascript"></script>
<style type="text/css">
table tr td textarea {
  width:100%;
  padding:5px;
  resize:none;
  border:none;
  line-height:20px;
  min-height:150px;
  font-size: 14px;
  }
table tr td textarea.chengguo {
  min-height:500px;
  }
table tr td textarea.zhaiyao {
  min-height:300px;
  }
.jiaowu_shenhe_dialog {
    border-bottom: #545454 solid 1px;
}
</style>
<div class="jiaowu_shenhe_dialog">
	<table border="1" class="jiaowu_shenhe">
		<tr>
			<td colspan="8" class="align_center"><strong>报考信息</strong></td>
		</tr>
		<tr>
			<td width="14%"><strong>姓名</strong></td>
			<td width="21%" class="align_left">${entryInfo.entryUserName }</td>
			<td width="12%"><strong>性别</strong></td>
			<td width="5%" class="align_left">${entryInfo.entryUserSex }</td>
			<td width="12%"><strong>出生日期</strong></td>
			<td width="26%" colspan="2" class="align_left">${entryInfo.entryUserBirthday }</td>
			<td width="28%" rowspan="4"><img
				src="${entryInfo.entryUserPhoto }" width="160" height="180" /></td>
		</tr>
		<tr>
			<td width="14%"><strong>证件类型</strong></td>
			<td colspan="3" class="align_left">${entryInfo.documentType }</td>
			<td width="12%"><strong>证件号码</strong></td>
			<td width="26%" colspan="2" class="align_left">${entryInfo.documentNumber }</td>
		</tr>
		<tr>
			<td width="14%"><strong>民族</strong></td>
			<td width="12%" class="align_left">${entryInfo.entryUserNation }</td>
			<td width="12%"><strong>政治面貌</strong></td>
			<td width="12%" class="align_left">${entryInfo.entryPolitical }</td>
			<td width="12%"><strong>户口所在地</strong></td>
			<td width="26%" colspan="2" class="align_left">${entryInfo.entryUserProvince }${entryInfo.entryUserCity }${entryInfo.entryUserArea }</td>
		</tr>
		<tr>
			<td width="14%"><strong>现住地址</strong></td>
			<td colspan="3" class="align_left">${entryInfo.entryUserAddress }</td>
			<td width="12%"><strong>邮编</strong></td>
			<td colspan="2" width="26%" class="align_left">${entryInfo.zipCode }</td>
		</tr>
		<tr>
			<td width="14%"><strong>文化程度</strong></td>
			<td width="12%" class="align_left">${entryInfo.entryUserEducation }</td>
			<td width="12%"><strong>手机号</strong></td>
			<td colspan="2" class="align_left">${entryInfo.entryUserPhone }</td>
			<td width="12%"><strong>电子邮箱</strong></td>
			<td colspan="2" class="align_left">${entryInfo.entryUserMail }</td>
		</tr>
		<tr>
			<td width="14%"><strong>参加工作时间</strong></td>
			<td colspan="3" class="align_left">${entryInfo.inWorkTime }</td>
			<td width="12%"><strong>连续工龄</strong></td>
			<td colspan="3" width="28%" class="align_left">${entryInfo.workYears }年</td>
		</tr>
		<tr>
			<td width="14%"><strong>所在单位</strong></td>
			<td colspan="3" class="align_left">${entryInfo.entryUserUnit }</td>
			<td width="12%"><strong>所在职位</strong></td>
			<td colspan="3" width="28%" class="align_left">${entryInfo.entryUserPosition }</td>
		</tr>
		<tr>
			<td width="14%"><strong>报名计划</strong></td>
			<td class="align_left">${entryInfo.entryPlan.entryplan_content }</td>
			<td><strong>报考类别</strong></td>
			<td colspan="2" class="align_left">${entryInfo.courseMenu.course_name }-${review.review_name }</td>
			<td><strong>报考级别</strong></td>
			<td colspan="2" class="align_left">${entryInfo.dictionary.dictionary_name }</td>
		</tr>
		<tr>
			<td width="14%"><strong>报名点</strong></td>
			<td colspan="7" class="align_left">${entryInfo.entryProvince }${entryInfo.entryCity }${entryInfo.entrySchool }</td>
		</tr>
		<tr>
			<td width="14%"><strong>报名条件</strong></td>
			<td colspan="7" class="align_left">${entryInfo.condition.entrycondition_content }</td>
		</tr>
		<tr>
		<td colspan="8" width="100%"><strong>职称评审</strong></td>
	</tr>
	<tr>
        <td colspan="2"><strong>现专业技术职务任职资格</strong></td>
        <td colspan="4">${entryInfo.now_zhuanye_zhicheng}</td>
        <td><strong>取得时间</strong></td>
        <td>${entryInfo.qude_time}</td>
      </tr>
      <tr>
        <td colspan="8" width="100%"><strong>申报人符合申报评审条件情况</strong></td>
      </tr>
      <tr>
        <td rowspan="4"><strong>第一学历</strong></td>
        <td><strong>毕业院校</strong></td>
        <td colspan="6">${entryInfo.biye_sc}</td>
      </tr>
      <tr>
        <td colspan="2"><strong>毕业证书编号</strong></td>
        <td colspan="2">${entryInfo.zhengshu_num}</td>
        <td><strong>学历程度</strong></td>
        <td colspan="2">${entryInfo.diyi_xueli}</td>
      </tr>
      <tr>
        <td><strong>所学专业</strong></td>
        <td colspan="3">${entryInfo.suoxue_zhuanye }</td>
        <td><strong>学位</strong></td>
        <td colspan="2">${entryInfo.xuewei }</td>
      </tr>
      <tr>
        <td><strong>学制</strong></td>
        <td>${entryInfo.xuezhi }</td>
        <td><strong>毕业时间</strong></td>
        <td colspan="2">${entryInfo.biye_time }</td>
        <td><strong>学习形式</strong></td>
        <td>${entryInfo.studry_type }</td>
      </tr>
      <tr>
        <td rowspan="4"><strong>最高学历</strong></td>
        <td><strong>毕业院校</strong></td>
        <td colspan="6">${entryInfo.zuigao_xuexiao }</td>
      </tr>
      <tr>
        <td colspan="2"><strong>毕业证书编号</strong></td>
        <td colspan="2">${entryInfo.zuigao_bianhao }</td>
        <td><strong>学历程度</strong></td>
        <td colspan="2">${entryInfo.zuigao_xueli }</td>
      </tr>
      <tr>
        <td><strong>所学专业</strong></td>
        <td colspan="3">${entryInfo.zuigao_zhuanye }</td>
        <td><strong>学位</strong></td>
        <td colspan="2">${entryInfo.zuigao_xuewei }</td>
      </tr>
      <tr>
        <td><strong>学制</strong></td>
        <td>${entryInfo.zuigaoxuezhi }</td>
        <td><strong>毕业时间</strong></td>
        <td colspan="2">${entryInfo.zuigao_biye_time }</td>
        <td><strong>学习形式</strong></td>
        <td>${entryInfo.zuigao_xuexi_type }</td>
      </tr>
      <tr>
        <td rowspan="3"><strong>职称外语计算机应用能力考试</strong></td>
        <td><strong>职称外语</strong></td>
        <td colspan="6">${entryInfo.zhicheng_yingyu }</td>
      </tr>
      <tr>
        <td><strong>计算机应用能力</strong></td>
        <td colspan="6">${entryInfo.jisuanjinengli }</td>
      </tr>
      <tr>
        <td><strong>参考、免考理由</strong></td>
        <td colspan="6">${entryInfo.can_mian_liyou }</td>
      </tr>
      <tr>
        <td colspan="8" width="100%"><strong>业绩成果</strong></td>
      </tr>
      <tr>
        <td><strong>荣誉称号</strong></td>
        <td colspan="7">${entryInfo.rongyuchenghao }</td>
      </tr>
      <tr>
        <td><strong>科研成果</strong></td>
        <td colspan="7">${entryInfo.keyanchengguo }</td>
      </tr>
      <tr>
        <td colspan="8" width="100%"><strong>论文著作</strong></td>
      </tr>
      <tr>
        <td><strong>论文题目</strong></td>
        <td colspan="7">${entryInfo.lunwen_timu }</td>
      </tr>
      <tr>
        <td><strong>论文期刊</strong></td>
        <td colspan="2">${entryInfo.lunwen_qikan }</td>
        <td><strong>著作</strong></td>
        <td>${entryInfo.lunwen_duzhu }</td>
        <td><strong>发表时间</strong></td>
        <td colspan="2">${entryInfo.lunwen_time }</td>
      </tr>
      <tr>
        <td><strong>摘要</strong></td>
        <td colspan="7">${entryInfo.lunwen_zhaiyao }</td>
      </tr>
		<tr>
			<td width="14%"><strong>报考资料</strong></td>
			<td colspan="7">
				<div class="ziliao_pic">
					<span>证件照片：</span>
					<div>
						<c:if test="${entryInfo.documentType=='身份证' }">
							<img src="${entryInfo.userCardPositive}" width="160" height="180" />
							<img src="${entryInfo.userCardOpposite}" width="160" height="180" />
						</c:if>
						<c:if test="${entryInfo.documentType!='身份证' }">
							<img src="${entryInfo.document_photo}" width="160" height="180" />
						</c:if>
					</div>
					<span>学历照片：</span>
					<div>
						<img src="${entryInfo.certificatePic}" width="160" height="180" />
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td width="14%"><strong>上传资料</strong></td>
			<td colspan="7">
			<script>
			function xuexin_tip_huaguo() {
				layer.tips('学信网电子学籍备案表', "#xuexin_tip", {
					tips : [ 3, '#8BC34A' ],
					time : 3000
				});
			}

			function ercun_tip_huaguo() {
				layer.tips('小二寸白底，蓝底高清电子版照片各1份，压缩包', "#ercun_tip", {
					tips : [ 3, '#8BC34A' ],
					time : 3000
				});
			}
			</script>
				<ul class="zhicheng_upload" style="padding:25px 0px;">
				<c:if test="${not empty entryInfo.xuexin_beian }">
					<li style="margin-right: 170px;margin-left:30px;">
						<p class="upload_filename" id="xuexin_info"></p> <img
						src="/images/crm/business/tips.png"
						onmouseover="xuexin_tip_huaguo()" id="xuexin_tip"> <span
						class="zhiceng_wz">学信网电子学籍备案表</span> <a href="${entryInfo.xuexin_beian }"
						class="liulanbtn" download="学信网电子学籍备案表.${xuexin_houzhui }"> 下载 </a>
					</li>
					</c:if>
					<c:if test="${not empty entryInfo.ercunzhao }">
					<li>
						<p class="upload_filename" id="ercunzhao_info"></p> <img
						src="/images/crm/business/tips.png" id="ercun_tip"
						onmouseover="ercun_tip_huaguo()"> <span class="zhiceng_wz">两寸打包照片</span>
						<a href="${entryInfo.ercunzhao }" class="liulanbtn"> 下载 
						</a> 
					</li>
					</c:if>
					<c:if test="${entryInfo.zhengshu_dabao }">
					<script>
						function zhengshu_tip_huaguo() {
							layer
									.tips(
											'包括荣誉证书、各种奖项、职称证(员级、初级、中级)的扫描件(电子版1份) ,压缩包',
											"#zhengshu_tip", {
												tips : [ 3, '#8BC34A' ],
												time : 3000
											});
						}
					</script>
					<li style="margin-left:64px;">
						<p class="upload_filename" id="zhengshu_dabao_info"></p> <img
						src="/images/crm/business/tips.png"
						onmouseover="zhengshu_tip_huaguo()" id="zhengshu_tip"><span
						class="zhiceng_wz">证书资料打包上传</span> <a href="${entryInfo.zhengshu_dabao }"
						class="liulanbtn"> 下载 </a>
					</li>
					</c:if>
					<c:if test="${not empty entryInfo.zhichenglunwen }">
					 <script>
						function lunwen_tip_huaguo() {
							layer
									.tips(
											'论文1篇，工作总结电子版，工作业绩(招标合同、中标合同、施工合同、验收报告)电子版各一份，压缩包',
											"#lunwen_tip", {
												tips : [ 3, '#8BC34A' ],
												time : 3000
											});
						}
					</script>
					<li style="margin-left: 146px;">
						<p class="upload_filename" id="zhichenglunwen_info"></p> <img
						src="/images/crm/business/tips.png"
						onmouseover="lunwen_tip_huaguo()" id="lunwen_tip"><span
						class="zhiceng_wz">文件资料打包上传</span> <a href="${entryInfo.zhichenglunwen }"
						class="liulanbtn"> 下载</a> <script>
					</script>
					</li>
					</c:if>
				</ul>
			</td>
		</tr>
		<tr>
			<td colspan="8" class="align_center"><strong>课程信息</strong></td>
		</tr>
		<tr>
			<td width="14%"><strong>课程班型</strong></td>
			<td colspan="3" class="align_left">${entryInfo.mianshoubanxing }</td>
			<td width="14%"><strong>报考班次</strong></td>
			<td colspan="3" class="align_left">${entryInfo.baokao_banci }</td>
		</tr>
		<tr>
			<td width="14%"><strong>审核状态</strong></td>
			<td colspan="7" class="align_left"><c:if test="${entryInfo.entryInfoState==0 }">
								未提交
							</c:if> <c:if test="${entryInfo.entryInfoState==1 }">
								财务审核中
							</c:if> <c:if test="${entryInfo.entryInfoState==2 }">
								教务审核中
							</c:if> <c:if test="${entryInfo.entryInfoState==3 }">
								等待提交考试中心
							</c:if> <c:if test="${entryInfo.entryInfoState==4 }">
								财务审核失败
							</c:if> <c:if test="${entryInfo.entryInfoState==5 }">
								教务审核失败
							</c:if> <c:if test="${entryInfo.entryInfoState==6 }">
								已上报考试中心
							</c:if> <c:if test="${entryInfo.entryInfoState==7}">
								部门经理审核中
							</c:if> <c:if test="${entryInfo.entryInfoState==8 }">
								市场总监审核中
							</c:if> <c:if test="${entryInfo.entryInfoState==9 }">
								总经理审核中
							</c:if></td>
		</tr>
		<tr>
    <td rowspan="5">审核意见</td>
    <td>财务</td>
    <td colspan="6">${entryInfo.caiwushenhe_message }</td>
  </tr>
  <tr>
    <td>教务</td>
    <td colspan="6">${entryInfo.jiaowushenhe_message }</td>
  </tr>
  <tr>
    <td>部门经理</td>
    <td colspan="6">${entryInfo.bumen_yijian }</td>
  </tr>
  <tr>
    <td>市场总监</td>
    <td colspan="6">${entryInfo.zongjian_yijian }</td>
  </tr>
  <tr>
    <td>总经理</td>
    <td colspan="6">${entryInfo.boss_yijian }</td>
  </tr>
	</table>
</div>