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

<c:set var="mainGroup">main${index}</c:set>
    	<%--
    		Logic : For Observed AE, the other Verbatim and sometimes based on the term, the OtherSpecify needs to be displayed
    			  : For Solicited AE, both Verbatim and Other specify will not be there.
    			  So the Term column will have 3 fields when the term is OtherSpecify (for observed AE)  
    	--%>
<tr>
<div class="ae-section ${index % 2 gt 0 ? 'odd' : 'even'}" id="boxholder" >
<c:if test="${aeTermIndex gt 0}">
	<div><tags:renderInputs field="${fieldGroups[mainGroup].fields[0]}" cssClass="cb${adverseEvent.adverseEventTerm.term.id} aeChk"/></div>
</c:if>	
<c:if test="${isSolicitedAE}">
<div class="thterm"><b>
			<tags:renderInputs field="${fieldGroups[mainGroup].fields[aeTermIndex]}" cssClass="aeTerm"/></b>
			
		</div>
                       <div id="gradehead"><tags:requiredIndicator/>Grade</div>
                       <div id="attributionhead">Attribution</div>
                       <div id="hospitalizationhead">Hospitalization</div>
                       <div id="expectedhead">Expected</div>
                       <caaers:renderFilter elementID="adverseEvents[].serious"><div id="serioushead">Serious</div></caaers:renderFilter>
	<c:forEach items="${fieldGroups[mainGroup].fields}" var="field" varStatus="lpstatus" begin="${aeTermIndex + 1}">
	<c:set var="nCss" value="${lpstatus.index - 1}" />
		<tags:renderInputs field="${field}" cssClass="selectbox${nCss}"/>
	</c:forEach>
</c:if>		
		
<c:if test="${not isSolicitedAE}">
	<c:if test="${isAETermOtherSpecify}">
		<div><b>
			<tags:renderInputs field="${fieldGroups[mainGroup].fields[aeTermIndex]}" cssClass="aeTerm"/></b>
			<div class="divOtherMeddra">
			<tags:requiredIndicator/>${fieldGroups[mainGroup].fields[aeTermIndex + 1].displayName}
			<tags:renderInputs field="${fieldGroups[mainGroup].fields[aeTermIndex + 1]}" cssClass="aeOtherMeddra om${adverseEvent.adverseEventTerm.term.id}" />
			</div>
			<div class="divNotes">
			${fieldGroups[mainGroup].fields[aeTermIndex + 2].displayName}
			<tags:renderInputs field="${fieldGroups[mainGroup].fields[aeTermIndex + 2]}" cssClass="aeNotes" />
			</div>
		</div>
		<div id="gradehead"><tags:requiredIndicator/>Grade</div>
                       <div id="attributionhead">Attribution</div>
                       <div id="hospitalizationhead">Hospitalization</div>
                       <div id="expectedhead">Expected</div>
                       <caaers:renderFilter elementID="adverseEvents[].serious"><div id="serioushead">Serious</div></caaers:renderFilter>
		<c:forEach items="${fieldGroups[mainGroup].fields}" var="field" begin="${aeTermIndex + 3 }" varStatus="lpIdx">
		<c:set var="nCss" value="${lpIdx.index - 3}" />
		<tags:renderInputs field="${field}" cssClass="selectbox${nCss}"/>
		</c:forEach>
	</c:if>
	<c:if test="${not isAETermOtherSpecify}">
		<div class="thterm"><b>
			<tags:renderInputs field="${fieldGroups[mainGroup].fields[aeTermIndex]}" cssClass="aeTerm"/></b>
			<div class="divNotes">
			${fieldGroups[mainGroup].fields[1].displayName}
			<tags:renderInputs field="${fieldGroups[mainGroup].fields[aeTermIndex + 1]}" cssClass="aeNotes" />
			</div>
		</div>
                       <div id="gradehead"><tags:requiredIndicator/>Grade</div>
                       <div id="attributionhead">Attribution</div>
                       <div id="hospitalizationhead">Hospitalization</div>
                       <div id="expectedhead">Expected</div>
                       <caaers:renderFilter elementID="adverseEvents[].serious"><div id="serioushead">Serious</div></caaers:renderFilter>
		<c:forEach items="${fieldGroups[mainGroup].fields}" var="field" begin="${aeTermIndex + 2}" varStatus="lpIdx">
		<c:set var="nCss" value="${lpIdx.index - 2}" />
		<tags:renderInputs field="${field}" cssClass="selectbox${nCss}"/>
		</c:forEach>
	</c:if>
	<c:if test="${not hideDeleteCtrl}">
	<div class="delete">
		<a href="#" onClick="rpCreator.deleteAdverseEvent(${index})"><img src="<chrome:imageUrl name="../checkno.gif" />"  alt="Delete" style="border:0" /></a>
	</div>
	</c:if>
</c:if>	
</div>
</tr>