<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="isSolicitedAE" type="java.lang.Boolean" required="true"  description="Should be set to true, when we are rendering the row for solicited adverse events"%>
<%@attribute name="isAETermOtherSpecify" type="java.lang.Boolean" required="true" description="Should be true, when the CTC term is otherspecify" %>
<%@attribute name="adverseEvent" type="gov.nih.nci.cabig.caaers.domain.AdverseEvent" required="true" description="The adverse event that is being rendered" %>

<c:set var="mainGroup">main${index}</c:set>
    	<%--
    		Logic : For Observed AE, the other Verbatim and sometimes based on the term, the OtherSpecify needs to be displayed
    			  : For Solicited AE, both Verbatim and Other specify will not be there.
    			  So the Term column will have 3 fields when the term is OtherSpecify (for observed AE)  
    	--%>
<tr class="division ae-section" id="ae-section-${index}" >
	
<c:if test="${isSolicitedAE}">
	<c:forEach items="${fieldGroups[mainGroup].fields}" var="field" varStatus="lpstatus">
		<td><tags:renderInputs field="${field}" cssClass="${lpstatus.index == 0 ? 'aeTerm' : 'selectbox'}"/></td>
	</c:forEach>
</c:if>		
		
<c:if test="${not isSolicitedAE}">
	<c:if test="${isAETermOtherSpecify}">
		<td>
			<tags:renderInputs field="${fieldGroups[mainGroup].fields[0]}" cssClass="aeTerm"/>
			<div class="divOtherMeddra">
			<tags:requiredIndicator/>${fieldGroups[mainGroup].fields[1].displayName}
			<tags:renderInputs field="${fieldGroups[mainGroup].fields[1]}" cssClass="aeOtherMeddra" />
			</div>
			<div class="divNotes">
			${fieldGroups[mainGroup].fields[2].displayName}
			<tags:renderInputs field="${fieldGroups[mainGroup].fields[2]}" cssClass="aeNotes" />
			</div>
		</td>
		<c:forEach items="${fieldGroups[mainGroup].fields}" var="field" begin="3">
		<td><tags:renderInputs field="${field}" cssClass="selectbox"/></td>
		</c:forEach>
	</c:if>
	<c:if test="${not isAETermOtherSpecify}">
		<td>
			<tags:renderInputs field="${fieldGroups[mainGroup].fields[0]}" cssClass="aeTerm"/>
			<div class="divNotes">
			${fieldGroups[mainGroup].fields[1].displayName}
			<tags:renderInputs field="${fieldGroups[mainGroup].fields[1]}" cssClass="aeNotes" />
			</div>
		</td>
		<c:forEach items="${fieldGroups[mainGroup].fields}" var="field" begin="2">
		<td><tags:renderInputs field="${field}" cssClass="selectbox"/></td>
		</c:forEach>
	</c:if>
	<td>
		<a href="#" onClick="rpCreator.deleteAdverseEvent(${index})"><img src="<chrome:imageUrl name="../checkno.gif" />" style="border:0" /></a>
	</td>
</c:if>		
</tr>