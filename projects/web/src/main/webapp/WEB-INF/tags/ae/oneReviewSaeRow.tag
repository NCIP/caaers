<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="isSolicitedAE" type="java.lang.Boolean" required="true"  description="Should be set to true, when we are rendering the row for solicited adverse events"%>
<%@attribute name="isAETermOtherSpecify" type="java.lang.Boolean" required="true" description="Should be true, when the CTC term is otherspecify" %>
<%@attribute name="adverseEvent" type="gov.nih.nci.cabig.caaers.domain.AdverseEvent" required="true" description="The adverse event that is being rendered" %>
<%@attribute name="hideDeleteCtrl" type="java.lang.Boolean" description="If true, will not display the column containing delete button" %>
<%@attribute name="aeTermIndex" type="java.lang.Integer" required="true" description="The index of aeTerm, explicitly set this to 0 or 1, this is to support checkbox in confirmation page" %>
<%@attribute name="renderNotes" type="java.lang.Boolean" required="true" description="Display the render Notes section if this is set to true" %>
<%@attribute name="renderSubmittedFlag" type="java.lang.Boolean" required="true" description="Determines whether to display the image indicating the adverse event has been successfully submitted" %>


<c:set var="mainGroup">main${index}</c:set>

<tr class="ae-section ${index % 2 gt 0 ? 'odd' : 'even'}" id="ae-section-${index}" >

<%--<c:if test="${aeTermIndex gt 0}">
	<td><tags:renderInputs field="${fieldGroups[mainGroup].fields[0]}" cssClass="cb${adverseEvent.adverseEventTerm.term.id} aeChk"/></td>
</c:if> --%>	


<c:if test="${renderSubmittedFlag}">
	<c:if test="${adverseEvent.submitted == true}">
		<td><img id="ae-section-${index}-submitted-image" src="<chrome:imageUrl name="../aeSubmitted.png" />" alt="This adverse event has already been reported. An ammendment will be required if a change is made to this adverse event." title="Submitted" style="border:0" /></td>
	</c:if>
	<c:if test="${adverseEvent.submitted == false}">
		<td/>
	</c:if>
</c:if>

<c:if test="${isAETermOtherSpecify}">
	<td>
        <tags:renderInputs field="${fieldGroups[mainGroup].fields[aeTermIndex]}" cssClass="aeTerm"/>
        <div class="divOtherMeddra">
        	<c:if test="${not isSolicitedAE}">
           		${fieldGroups[mainGroup].fields[aeTermIndex + 1].displayName}
            	<tags:renderInputs field="${fieldGroups[mainGroup].fields[aeTermIndex + 1]}" cssClass="aeOtherMeddra om${adverseEvent.adverseEventTerm.term.id}" />
        	</c:if>
        </div>
    </td>
    <c:forEach items="${fieldGroups[mainGroup].fields}" var="field" begin="${aeTermIndex + 2 }" varStatus="lpIdx">
        <caaers:renderFilter elementID="${field.propertyName}">
            <td>
                <div class="${lpIdx.index gt 2 ? 'shortselectdiv' : 'selectdiv'}">
                    <tags:renderInputs field="${field}" cssClass="${lpIdx.index gt 2 ? 'shortselectbox' : 'selectbox'}" />
                </div>
            </td>
        </caaers:renderFilter>
    </c:forEach>
</c:if>

<c:if test="${not isAETermOtherSpecify}">
	<td>
		<tags:renderInputs field="${fieldGroups[mainGroup].fields[aeTermIndex]}" cssClass="aeTerm"/>
	</td>
	<c:forEach items="${fieldGroups[mainGroup].fields}" var="field" begin="${aeTermIndex + 1}" varStatus="lpIdx">
		<caaers:renderFilter elementID="${field.propertyName}">
			<td>
            	<div class="${lpIdx.index gt 1 ? 'shortselectdiv' : 'selectdiv'}">
					<tags:renderInputs field="${field}" cssClass="${lpIdx.index gt 1 ? 'shortselectbox' : 'selectbox'}"/>
				</div>
			</td>
		</caaers:renderFilter>
	</c:forEach>
</c:if>

</tr>