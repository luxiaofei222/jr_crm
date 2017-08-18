<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="collection_right">
	<c:if test="${empty questionCollections.list }">
		<div>暂无内容</div>
	</c:if>
	<c:forEach items="${questionCollections.list }" var="collection"
		varStatus="vs">
		<div class="collect_wai">
			<div class="collect_nei">
				<div class="collect_title">
					<span class="num">${vs.index+1+questionCollections.begin }</span> <span
						class='line'></span> <em class='type'>[${collection.question_type }]</em>
					<p>${collection.chapterQuestion.question_name }</p>
				</div>
				<c:if test="${collection.chapterQuestion.question_type=='单选题' }">
					<ul class="single_choices">
						<c:forEach
							items="${collection.chapterQuestion.chapterQuerstionOptions }"
							var="option">
							<c:if
								test="${collection.chapterQuestion.answer==option.option_number }">
								<li><span class="l collect_select">${option.option_number }</span>
									<div class='r'>${option.option_content }</div></li>
							</c:if>
							<c:if
								test="${collection.chapterQuestion.answer!=option.option_number }">
								<li><span class="l">${option.option_number }</span>
									<div class='r'>${option.option_content }</div></li>
							</c:if>
						</c:forEach>
					</ul>
				</c:if>
				<c:if test="${collection.chapterQuestion.question_type=='多选题' }">
					<ul class="multi_choices">
						<c:forEach
							items="${collection.chapterQuestion.chapterQuerstionOptions }"
							var="option">
							<c:if test="${option.duoxuan_type_jiexi==1 }">
								<li><span class="l collect_select">${option.option_number }</span>
									<div class='r'>${option.option_content }</div></li>
							</c:if>
							<c:if test="${option.duoxuan_type_jiexi==0 }">
								<li><span class="l">${option.option_number }</span>
									<div class='r'>${option.option_content }</div></li>
							</c:if>
						</c:forEach>
					</ul>
				</c:if>
				<div class="jiucuo">
					<div class="jiucuo_answer">
						<span class="fold-toggle" onclick="chakan_jiexi(this)">查看解析<i
							class="fa fa-angle-double-down"></i>
						</span> <span class="collect collect_active" onclick="quxiao_question_collection(this,${collection.chapter_recourd_id})"> <i
							class="icon-star2 collect_active"></i><em>取消收藏</em>
						</span> <span class="btn-report-error" onclick="get_correction(${collection.chapter_recourd_id})"> <i class="icon-star3"></i>
							<em>本题纠错</em>
						</span>
						<div class="answer_inline">
							<span class="as-title">参考答案：</span>
							<div class="as-cont hl-green">${collection.chapterQuestion.answer }</div>
						</div>
						<div class="answer_inline">
							<span class="as-title">我的答案：</span>
							<c:if test="${collection.chapterRecord.user_answer=='无' }">
								<div class="as-cont hl-red">未作答</div>
							</c:if>
							<c:if test="${collection.chapterRecord.user_answer!='无' }">
								<div class="as-cont hl-red">${collection.chapterRecord.user_answer }</div>
							</c:if>
						</div>
					</div>
					<div class='fold-bd'>
						<ul class="list-analyze">
							<li><span class='l'>试题难度：</span>
								<div class="r">
									<i
										class="icon-pen pen-${collection.chapterQuestion.difficulty }">
										<span></span>
									</i>
								</div></li>
							<li><span class="l">
									统&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计： </span>
								<div class='r'>
									该题您答过 <b class="hl-orange">${collection.user_answer_number }</b>次，正确率为
									<b class="hl-green"><fmt:formatNumber type="number"
											value="${collection.user_answer_right_number/collection.user_answer_number*100 }"
											pattern="0.00" maxFractionDigits="2" />%</b>
								</div></li>
							<li><span class="l"> 参考解析： </span>
								<div class="r">${collection.chapterQuestion.analysis }</div></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<!--练习记录-章节练习的分页   -->
	<div class="pagination">
		<c:if test="${questionCollections.hasPreviousPage==true}">
			<a
				onclick="chapter_cuoti_jump_page(${questionCollections.pageNumber-1})">上一页</a>
		</c:if>
		<c:forEach items="${questionCollections.navigatePageNumbers }"
			var="page">
			<c:choose>
				<c:when test="${questionCollections.pageNumber==page}">
					<a class="active">${page}</a>
				</c:when>
				<c:otherwise>
					<a onclick="chapter_cuoti_jump_page(${page})">${page}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${questionCollections.hasNextPage==true}">
			<a
				onclick="chapter_cuoti_jump_page(${questionCollections.pageNumber+1})">下一页</a>
		</c:if>
		<span class="page-num">${questionCollections.pageNumber}/${questionCollections.pages }页</span>
		<span class="input"> <input type="text" id="page_number">
		</span> <a
			onclick="to_chapter_cuoti_jump_page(${questionCollections.pages })">跳转</a>
	</div>
</div>
