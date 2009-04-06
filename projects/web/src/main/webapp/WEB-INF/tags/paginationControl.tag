<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<%@attribute name="isFirstPage" required="true" type="java.lang.Boolean" description="Will tell whether the first page of result set is being displayed" %>
<%@attribute name="isLastPage" required="true" type="java.lang.Boolean" description="Will tell whether the last page of result set is being displayed" %>

<tags:dwrJavascriptLink objects="routingAndReview"/>

<script type="text/javascript">
	function firePaginationAction(action){
		var form= document.getElementById('command');
		form.paginationAction.value = action;
		form.numberOfResultsPerPage.value = $('resultsPerPage').value;
		form.submit();
	}
</script>
<input type="hidden" name="paginationAction"/>
<input type="hidden" name="study" value="${command.study.id }"/>
<input type="hidden" name="participant" value="${command.participant.id}"/>
<input type="hidden" name="studySite" value="${command.studySite.id }"/>
<input type="hidden" name="reviewStatus" value="${command.reviewStatus.code }"/>
<input type="hidden" name="numberOfResultsPerPage"/>
<table class="toolbar" cellspacing="1" cellpadding="0" border="0">
	<tbody>
		<tr>
			<td>
				<c:if test="${isFirstPage == false}">
					<a href="javascript:firePaginationAction('firstPage')">
						<img alt="First" src="<c:url value="/images/table/firstPage.gif"/>" style="border:0pt none;"/>
					</a>
				</c:if>
				<c:if test="${isFirstPage == true}">
					<img alt="First" src="<c:url value="/images/table/firstPageDisabled.gif"/>" style="border:0pt none;"/>
				</c:if>
			</td>
			<td>
				<c:if test="${isFirstPage == false}">
					<a href="javascript:firePaginationAction('prevPage')">
						<img alt="Prev" src="<c:url value="/images/table/prevPage.gif"/>" style="border:0pt none;"/>
					</a>
				</c:if>
				<c:if test="${isFirstPage == true}">
					<img alt="First" src="<c:url value="/images/table/prevPageDisabled.gif"/>" style="border:0pt none;"/>
				</c:if> 
			</td>
			<td>
				<c:if test="${isLastPage == false}">
					<a href="javascript:firePaginationAction('nextPage')">
						<img alt="Next" src="<c:url value="/images/table/nextPage.gif"/>" style="border:0pt none;"/>
					</a>
				</c:if>
				<c:if test="${isLastPage == true}">
					<img alt="First" src="<c:url value="/images/table/nextPageDisabled.gif"/>" style="border:0pt none;"/>
				</c:if>
			</td>
			<td>
				<c:if test="${isLastPage == false}">
					<a href="javascript:firePaginationAction('lastPage')">
						<img alt="Last" src="<c:url value="/images/table/lastPage.gif"/>" style="border:0pt none;"/>
					</a>
				</c:if>
				<c:if test="${isLastPage == true}">
					<img alt="First" src="<c:url value="/images/table/lastPageDisabled.gif"/>" style="border:0pt none;"/>
				</c:if>
			</td>
			<td>
				<img alt="Separator" src="<c:url value="/images/table/separator.gif"/>" style="border:0pt none;"></img>
			</td>
			<td style="width:20px;">
				<select id="resultsPerPage" onChange="javascript:firePaginationAction('numberOfResultsPerPage')">
					<option value="15" <c:if test="${numberOfResultsPerPage == 15}">selected</c:if>>15</option>
					<option value="50" <c:if test="${numberOfResultsPerPage == 50}">selected</c:if>>50</option>
					<option value="100" <c:if test="${numberOfResultsPerPage == 100}">selected</c:if>>100</option>
				</select>
				<img alt="Rows Displayed" src="<c:url value="/images/rowsDisplayed.gif"/>" style="border:0pt none;"></img>
			</td>
		</tr>
	</tbody>
</table>