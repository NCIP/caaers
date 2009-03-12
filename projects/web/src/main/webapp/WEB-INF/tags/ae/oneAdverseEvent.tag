<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript" src="/caaers/js/extremecomponents.js"></script>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="collapsed" required="true" type="java.lang.Boolean" %>
<%@attribute name="style"%>

<c:set var="ctcTermGroup">ctcTerm${index}</c:set>
<c:set var="ctcOtherGroup">ctcOther${index}</c:set>
<c:set var="mainGroup">main${index}</c:set>

<c:set var="title">
    <c:choose>
        <c:when test="${index == 0}">${command.aeReport.adverseEvents[index].adverseEventCtcTerm.ctcTerm.term}</c:when>
        <c:otherwise>${command.aeReport.adverseEvents[index].adverseEventCtcTerm.ctcTerm.term}</c:otherwise>
    </c:choose>
</c:set>
<c:set var="v" value="aeReport.adverseEvents[${index}]" />
<chrome:division title="${title}" id="ae-section-${index}" cssClass="ae-section" style="${style}" collapsed="${!empties[v]}" collapsable="true">
	<jsp:attribute name="titleFragment">&nbsp;<span id="title-frag-${index}" class="primary-indicator">${ index gt 0 ? '' : '[Primary]' }</span> </jsp:attribute>
	<jsp:body>	
    <div id="aeReport.adverseEvents[${index}].ctc-details" class="ctc-details">
  
        <div class="row" style="display:none;">
            <div class="label"><label for="aeReport.adverseEvents[${index}].ctc-category">CTC category</label></div>
            <div class="value">

              <div class="ctcCategoryValueDiv">
                <select id="aeReport.adverseEvents[${index}].ctc-category" class="ctcCategoryClass" onchange="javascript:enableDisableAjaxTable(${index})" >
                    <option value="">Any</option>
                </select>

              </div>
            </div>
        </div>

        <tags:renderRow field="${fieldGroups[ctcTermGroup].fields[0]}" style="display:none;" extraParams="<a id=\"showAllTerm${index}\" href=\"javascript:showAjaxTable(this,$F('aeReport.adverseEvents[${index}].ctc-category'),'table${index}','table${index}-outer')\">Show All</a>" />
        <tags:renderRow field="${fieldGroups[ctcOtherGroup].fields[0]}" style="display: none">
        <jsp:attribute name="label">
            <label>
                ${fieldGroups[ctcOtherGroup].fields[0].displayName}
            </label>
        </jsp:attribute>
   		</tags:renderRow>
    </div>

    <div id="main-fields-${index}" class="main-fields">
		<c:set var="len" value="${fn:length(fieldGroups[mainGroup].fields)}" />
		<tags:renderRow field="${fieldGroups[mainGroup].fields[0]}"/>
		<tags:renderRow field="${fieldGroups[mainGroup].fields[1]}"/>
		<table border="0">
			<tr>
			<td><tags:renderRow field="${fieldGroups[mainGroup].fields[2]}" /></td>
			<td><tags:renderRow field="${fieldGroups[mainGroup].fields[3]}" /></td>
			</tr>
		</table>
			
			
        <c:forEach items="${fieldGroups[mainGroup].fields}" var="field" begin="4" end="${len - 2}">
            <tags:renderRow field="${field}"/>
        </c:forEach>
		<ae:oneOutcome index="${index}" />
		<tags:renderRow field="${fieldGroups[mainGroup].fields[len - 1]}"/>
    </div>
    </jsp:body>
</chrome:division>

<script>
    Event.observe($('aeReport.adverseEvents[${index}].adverseEventCtcTerm.ctcTerm-input'), "blur", function() {
        $('titleOf_ae-section-${index}').innerHTML = $('aeReport.adverseEvents[${index}].adverseEventCtcTerm.ctcTerm-input').value;
    });
</script>