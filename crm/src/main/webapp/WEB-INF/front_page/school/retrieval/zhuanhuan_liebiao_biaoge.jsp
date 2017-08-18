<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${type=='banxing' }">
	<div class="tab" id="paixu_list">
		<a href="javascript:void(0)" onclick="get_zonghe_paixu(${course_id },${dictionary_id },'${banxing }',this)" class="green3">综合排序</a> 
		 <a href="javascript:void(0)" onclick="get_zuire_paixu(${course_id },${dictionary_id },'${banxing }',this)">最热</a> 
		<a href="javascript:void(0)"id="jiage_jiangxu" onclick="paixu_jiage_jiangxu(${course_id },${dictionary_id },'${banxing }','jiangxu',this)">价格 <img
			src="/images/school/front/retrieval/price.png"  id="p1" class="oneimg"><img
			src="/images/school/front/retrieval/price2.png" id="p2" class="twoimg"/></a>
			<a href="javascript:void(0)"id="jiage_shengxu" style="display: none;" onclick="paixu_jiage_jiangxu(${course_id },${dictionary_id },'${banxing }','shengxu',this)">价格 <img
			src="/images/school/front/retrieval/price.png"  id="p1" class="oneimg"><img
			src="/images/school/front/retrieval/price2.png" id="p2" class="twoimg"/></a>
	</div>
	<div class="list">
		<div style="float: left">
			<a id="across"
				onclick="get_biaogeshi(${course_id },${dictionary_id },'${banxing }')"><img
				src="/images/school/front/retrieval/hengxiang.png" id="lisp"></a>
			<a id="vertical"
				onclick="get_liebiaoshi(${course_id },${dictionary_id },'${banxing }')">
				<img src="/images/school/front/retrieval/zongxiang.png" id="lisp2" />
			</a>
		</div>
	</div>
</c:if>
<c:if test="${type=='dengji' }">
	<div class="tab" id="paixu_list">
			<a href="javascript:void(0)" onclick="get_zonghe_paixu(${course_id },${dictionary_id },'${banxing }',this)" class="green3">综合排序</a>
			  <a href="javascript:void(0)" onclick="get_zuire_paixu(${course_id },${dictionary_id },'${banxing }',this)">最热</a> 
			<a href="javascript:void(0)"id="jiage_jiangxu"  onclick="paixu_jiage_jiangxu(${course_id },${dictionary_id },'${banxing }','jiangxu',this)">价格 <img
			src="/images/school/front/retrieval/price.png"  id="p1" class="oneimg"><img
			src="/images/school/front/retrieval/price2.png" id="p2" class="twoimg"/></a>
			<a href="javascript:void(0)"id="jiage_shengxu" style="display: none;" onclick="paixu_jiage_jiangxu(${course_id },${dictionary_id },'${banxing }','shengxu',this)">价格 <img
			src="/images/school/front/retrieval/price.png"  id="p1" class="oneimg"><img
			src="/images/school/front/retrieval/price2.png" id="p2" class="twoimg"/></a>
	</div>
	<div class="list">
		<div style="float: left">
			<a id="across"
				onclick="get_biaogeshi(${course_id },${dictionary_id },'${banxing }')"><img
				src="/images/school/front/retrieval/hengxiang.png" id="lisp"></a>
			<a id="vertical"
				onclick="get_liebiaoshi(${course_id },${dictionary_id },'${banxing }')">
				<img src="/images/school/front/retrieval/zongxiang.png" id="lisp2" />
			</a>
		</div>
	</div>
</c:if>
<c:if test="${type=='erji' }">
	<div class="tab" id="paixu_list">
			<a href="javascript:void(0)" onclick="get_zonghe_paixu(${course_id },${dictionary_id },'${banxing }',this)" class="green3">综合排序</a>  
			<a href="javascript:void(0)" onclick="get_zuire_paixu(${course_id },${dictionary_id },'${banxing }',this)">最热</a> 
			<a href="javascript:void(0)" onclick="paixu_jiage_jiangxu(${course_id },null,null,'jiangxu',this)" id="jiage_jiangxu">价格 <img
			src="/images/school/front/retrieval/price.png"  id="p1" class="oneimg"><img
			src="/images/school/front/retrieval/price2.png" id="p2" class="twoimg" /></a>
			<a href="javascript:void(0)" style="display: none;" onclick="paixu_jiage_jiangxu(${course_id },null,null,'shengxu',this)" id="jiage_shengxu">价格 <img
			src="/images/school/front/retrieval/price.png"  id="p1"class="oneimg"><img
			src="/images/school/front/retrieval/price2.png" id="p2" class="twoimg"/></a>
	</div>
	<div class="list">
		<div style="float: left">
			<a id="across" onclick="get_biaogeshi(${course_id })"><img
				src="/images/school/front/retrieval/hengxiang.png" id="lisp"></a>
			<a id="vertical" onclick="get_liebiaoshi(${course_id })"> <img
				src="/images/school/front/retrieval/zongxiang.png" id="lisp2" />
			</a>
		</div>
	</div>
</c:if>
<c:if test="${type=='yiji' }">
	<div class="tab" id="paixu_list">
			<a href="javascript:void(0)" onclick="get_yiji_zonghe_paixu(${course_id },null,null,this)" class="green3">综合排序</a> 
			 <a href="javascript:void(0)" onclick="get_yiji_zuire_paixu(${course_id },null,null,this)">最热</a>
			 <a href="javascript:void(0)"  onclick="paixu_yiji_jiage_jiangxu(${course_id },null,null,'jiangxu',this)" id="jiage_jiangxu">价格 <img
			src="/images/school/front/retrieval/price.png"  id="p1"class="oneimg"><img
			src="/images/school/front/retrieval/price2.png" id="p2"  class="twoimg"/></a>
		 <a href="javascript:void(0)" style="display: none" onclick="paixu_yiji_jiage_jiangxu(${course_id },null,null,'shengxu',this)" id="jiage_shengxu">价格 <img
			src="/images/school/front/retrieval/price.png" id="p1" class="oneimg"><img
			src="/images/school/front/retrieval/price2.png" id="p2" class="twoimg"/></a>
	</div>
	<div class="list">
		<div style="float: left">
			<a id="across" onclick="get_yiji_biaogeshi(${course_id })"><img
				src="/images/school/front/retrieval/hengxiang.png" id="lisp"></a>
			<a id="vertical" onclick="get_yiji_liebiaoshi(${course_id })"> <img
				src="/images/school/front/retrieval/zongxiang.png" id="lisp2" />
			</a>
		</div>
	</div>
</c:if>