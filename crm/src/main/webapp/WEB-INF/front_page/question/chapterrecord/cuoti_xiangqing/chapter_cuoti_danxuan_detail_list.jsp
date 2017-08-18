<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="records_right">
			<c:forEach items="${chapterRecords.list }" var="records" varStatus="vs">
				<div class="collect_wai">
					<div class="collect_nei">
						<div class="collect_title">
							<span class="num">${vs.index+1+chapterRecords.begin }</span> <span class='line'></span> <em
								class='type'>[${records.question_type }]</em>
							<p>${records.chapterQuestion.question_name }</p>
						</div>
						<c:if test="${records.user_answer=='无' }">
						<ul class="single_choices">
							<c:forEach items="${records.chapterQuestion.chapterQuerstionOptions }" var="option">
							<c:if test="${records.chapterQuestion.answer==option.option_number }">
							<li><span class="l collect_select">${option.option_number }</span>
								<div class='r'>${option.option_content }</div></li>
							</c:if>
							<c:if test="${records.chapterQuestion.answer!=option.option_number }">
							<li><span class="l">${option.option_number }</span>
								<div class='r'>${option.option_content }</div></li>
							</c:if>
							</c:forEach>
						</ul>
						</c:if>
						<c:if test="${records.user_answer!='无' }">
						<ul class="single_choices">
						<c:forEach items="${records.chapterQuestion.chapterQuerstionOptions }" var="option">
							<c:if test="${records.chapterQuestion.answer==option.option_number }">
							<li><span class="l collect_select_wrong">${option.option_number }</span>
								<div class='r'>${option.option_content }</div></li>
							</c:if>
							<c:if test="${records.chapterQuestion.answer!=option.option_number }">
							<li><span class="l">${option.option_number }</span>
								<div class='r'>${option.option_content }</div></li>
							</c:if>
							</c:forEach>
							</ul>
						</c:if>
						<div class="jiucuo">
							<div class="jiucuo_answer">
								<span class="fold-toggle" onclick="chakan_jiexi(this)" >查看解析<i
									class="fa fa-angle-double-down"></i>
								</span>	<c:if test="${records.is_collection==1 }">
								<span class="collect collect_active" onclick="quxiao_question_collection(this,${records.chapter_recourd_id})"> <i
									class="icon-star2 collect_active"></i><em>取消收藏</em>
								</span>
								</c:if>
								<c:if test="${records.is_collection==0}">
								<span class="collect collect_active"  onclick="collection_question(this,${records.chapter_recourd_id})"> <i
									class="icon-star2"></i><em>收藏本题</em>
								</span>
								</c:if> <span class="btn-report-error"onclick="get_correction(${records.chapter_recourd_id})"> <i class="icon-star3"></i>
									<em>本题纠错</em>
								</span>
								<div class="answer_inline">
									<span class="as-title">参考答案：</span>
									<div class="as-cont hl-green">${records.chapterQuestion.answer }</div>
								</div>
								<div class="answer_inline">
									<span class="as-title">我的答案：</span>
									<c:if test="${records.user_answer=='无' }">
									<div class="as-cont hl-red">未作答</div>
									</c:if>
									<c:if test="${records.user_answer!='无' }">
									<div class="as-cont hl-red">${records.user_answer }</div>
									</c:if>
								</div>
							</div>
							<div class='fold-bd'>
								<ul class="list-analyze">
									<li><span class='l'>试题难度：</span>
										<div class="r">
											<i class="icon-pen pen-${records.chapterQuestion.difficulty }"> <span></span>
											</i>
										</div></li>
									<li><span class="l">
											统&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计： </span>
										<div class='r'>
											该题您答过 <b class="hl-orange">${records.user_answer_number }</b>次，正确率为 <b class="hl-green"><fmt:formatNumber type="number" value="${records.user_answer_right_number/records.user_answer_number*100 }" pattern="0.00" maxFractionDigits="2"/>%</b>
										</div></li>
									<li><span class="l"> 参考解析： </span>
										<div class="r">
											${records.chapterQuestion.analysis }
										</div></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				</c:forEach>
					<!--练习记录-章节练习的分页   -->
					<div class="pagination">
						<c:if test="${chapterRecords.hasPreviousPage==true}">
							<a
								onclick="chapter_cuoti_jump_page(${chapterRecords.pageNumber-1})">上一页</a>
						</c:if>
						<c:forEach items="${chapterRecords.navigatePageNumbers }"
							var="page">
							<c:choose>
								<c:when test="${chapterRecords.pageNumber==page}">
									<a class="active">${page}</a>
								</c:when>
								<c:otherwise>
									<a
										onclick="chapter_cuoti_jump_page(${page})">${page}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${chapterRecords.hasNextPage==true}">
							<a onclick="chapter_cuoti_jump_page(${chapterRecords.pageNumber+1})">下一页</a>
						</c:if>
						<span class="page-num">${chapterRecords.pageNumber}/${chapterRecords.pages }页</span>
						<span class="input"> <input type="text" id="page_number">
						</span> <a onclick="to_chapter_cuoti_jump_page(${chapterRecords.pages })">跳转</a>
					</div>
			</div> 