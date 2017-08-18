<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
$(function(){
	$(".input_style").click(function(){
		if($(this).attr("pid")=="off"){
			$(this).attr("src",'/images/personal/checked.jpg');
			$(this).attr("pid","on");
			$(this).parent().children("input").prop("checked","true");
		}else{
			$(this).attr("src",'/images/personal/check.jpg');
			$(this).attr("pid","off");
			 $(this).parent().children("input").attr('checked',false);
		}
	})
 });
 //保存忘记打卡
 function send_punch(obj){
	 var str = "";
		$('input:checkbox[name=punch_check]:checked').each(function(i) {
			if (0 == i) {
				str = $(this).val();
			} else {
				str += ","+$(this).val();
			}
		});
		var riqi_str=$("#riqi_str").val();
		var daka_info=$.trim($("#daka_info").val());
		if(riqi_str!=null&&riqi_str!=""){
			if(str!=null&&str!=""){
				 $(obj).attr({"disabled":"disabled"});
				$.post("/punch/save_punch_employee.jr",{
					'riqi_str':riqi_str,
					'shijianduan':str,
					'punch_info':daka_info
				},function(data){
					if(data==1){
						layer.msg("提交成功！");
						get_punch();
					}else{
						layer.msg("提交失败！");
						$(obj).removeAttr("disabled");
					}
				})
			}else{
				layer.msg("请选择忘记打卡时间段！");
			}
		}else{
			layer.msg("请选择忘记打卡的日期！");
		}
 }
 //打卡记录分页
 function punch_jump_page(page){
	 $.post("/punch/get_punch_list.jr",{
		 'limit':5,
		 'pageNumber':page
	 },function(data){
		 $("#daka_list").html(data);
	 })
 }
 
 function delete_daka(punch_id,page){
	 layer.confirm("提示：您好，确定要删除吗？",function(){
			layer.closeAll('dialog');
			$.post("/punch/delete_daka.jr",{
				'punch_id':punch_id
			},function(data){
				if(data==1){
					layer.msg("删除成功！");
					punch_jump_page(page);
				}else{
					layer.msg("删除失败！");
				}
			});
			})
 }
</script>
<div class="wj_riqi">
	<h4>忘打卡日期：</h4>
	<div class="time_input">
		<span class="str_time"><i class="icon iconfont icon-riqi"></i><input
			type="text" placeholder="请选择日期" id="riqi_str"
			onclick="layui.laydate({elem: this, format: 'YYYY-MM-DD'})" /></span>
	</div>
</div>
<!-- 日期 -->
<div class="wj_time">
	<h4>忘打卡时间：</h4>
	<div class="wj_times">
		<ul>
			<li><input type="checkbox" name="punch_check" value="上午上班" /><img
				src="/images/personal/check.jpg" class="input_style" pid="off"
				id="img1" />
				<p>上午上班</p></li>
			<li><input type="checkbox" name="punch_check" value="上午下班" /><img
				src="/images/personal/check.jpg" class="input_style" pid="off"
				id="img2" />
				<p>上午下班</p></li>
			<li><input type="checkbox" name="punch_check" value="下午上班" /><img
				src="/images/personal/check.jpg" class="input_style" pid="off"
				id="img3" />
				<p>下午上班</p></li>
			<li><input type="checkbox" name="punch_check" value="下午下班" /><img
				src="/images/personal/check.jpg" class="input_style" pid="off"
				id="img4" />
				<p>下午下班</p></li>
		</ul>
	</div>
	<div class="clear"></div>
</div>
<!-- 请假类别 -->
<div class="reason">
	<h4>忘打卡原因：</h4>
	<textarea id="daka_info" placeholder="请输入忘记打卡原因"></textarea>
</div>
<!-- 请假事由 -->
<div class="send">
	<input type="button" onclick="send_punch(this)" value="提交" />
	<!--<a href="javascript:void(0)">取消</a>-->
</div>
<div class="clear"></div>
<hr />
<div class="jilu" id="daka_list">
	<h4>忘打卡记录</h4>
	<c:if test="${empty punchs.list }">
		<span>暂无忘记打卡记录</span>
	</c:if>
	<c:if test="${not empty punchs.list }">
		<table class="layui-table" lay-even lay-skin="nob">
			<thead>
				<tr>
					<th>应打卡日期</th>
					<th>时间段</th>
					<th>原因</th>
					<th>人事状态</th>
					<th>人事意见</th>
					<th>提交时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${punchs.list }" var="punch">
					<tr>
						<td><fmt:formatDate value="${punch.daka_time }" /></td>
						<td>${punch.shijianduan }</td>
						<td>${punch.punch_info }</td>
						<td><c:if test="${punch.renshi_state==0 }">
					未查看
				</c:if> <c:if test="${punch.renshi_state==1 }">
					已记录
				</c:if> <c:if test="${punch.renshi_state==2 }">
					未批准
				</c:if></td>
						<td><c:if test="${punch.renshi_state!=0 }">
				${punch.renshi_info }
				</c:if> <c:if test="${punch.renshi_state==0 }">
				--
				</c:if></td>
						<td><fmt:formatDate type="both" value="${punch.punch_time }" /></td>
						<td>
						<c:if test="${punch.renshi_state!=1 }">
						<button onclick="delete_daka(${punch.punch_id },${punchs.pageNumber })"
								class="layui-btn layui-btn-normal layui-btn-small">删除</button>
						</c:if>
						<c:if test="${punch.renshi_state==1 }">
							--
						</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul class="pagination">
			<c:if test="${punchs.hasPreviousPage==true}">
				<li class="previous" onclick="punch_jump_page(${punchs.pageNumber-1})"><a href="#fakelink" class="fui-arrow-left"></a></li>
			</c:if>
			<c:forEach items="${punchs.navigatePageNumbers }" var="page">
				<c:choose>
					<c:when test="${punchs.pageNumber==page}">
						<li class="active" onclick="punch_jump_page(${page})"><a
							href="#fakelink">${page}</a></li>
					</c:when>
					<c:otherwise>
						<li onclick="punch_jump_page(${page})"><a href="#fakelink">${page}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${punchs.hasNextPage==true}">
				<li class="next" onclick="punch_jump_page(${punchs.pageNumber+1})"><a
					href="#fakelink" class="fa fa-angle-right"></a></li>
			</c:if>
		</ul>
	</c:if>
    <div class="clear"></div>
</div>