<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript" src="/caaers/js/extremecomponents.js"></script>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>

<c:set var="ctcTermGroup">ctcTerm${index}</c:set>
<c:set var="ctcOtherGroup">ctcOther${index}</c:set>
<c:set var="mainGroup">main${index}</c:set>

<c:set var="title">
    <c:choose>
        <c:when test="${index == 0}">${command.aeReport.adverseEvents[index].adverseEventCtcTerm.ctcTerm.term} (Primary)</c:when>
        <c:otherwise>${command.aeReport.adverseEvents[index].adverseEventCtcTerm.ctcTerm.term}</c:otherwise>
    </c:choose>
</c:set>

<chrome:division title="${title}" id="ae-section-${index}" cssClass="ae-section" style="${style}" collapsed="${index > 0}" collapsable="true">
    <div id="aeReport.adverseEvents[${index}].ctc-details" class="ctc-details">
        <div class="row">
           <div class="label">CTC version</div>
           <div class="value">${command.assignment.studySite.study.aeTerminology.ctcVersion.name}</div>
        </div>
        <div class="row">
            <div class="label"><label for="aeReport.adverseEvents[${index}].ctc-category">CTC category</label></div>
            <div class="value">

              <div class="ctcCategoryValueDiv">
                <select id="aeReport.adverseEvents[${index}].ctc-category" class="ctcCategoryClass" onchange="javascript:enableDisableAjaxTable(${index})" >
                    <option value="">Any</option>
                    <c:forEach items="${ctcCategories}" var="cat">
                        <option value="${cat.id}">${cat.name}</option>
                    </c:forEach>
                </select>

              </div>
            </div>
        </div>

        <tags:renderRow field="${fieldGroups[ctcTermGroup].fields[0]}" extraParams="<a id=\"showAllTerm${index}\" href=\"javascript:showAjaxTable(this,$F('aeReport.adverseEvents[${index}].ctc-category'),'table${index}','table${index}-outer')\">Show All</a>" />
        
		<div id="table${index}-outer" style="position: absolute; display: none;width:400px; left: 520px;  ">
		<table width="100%" class="eXtremeTable" frame="border" border-color="blue" bgcolor="white">
		<tbody>
            <tr class="titleRow">
              <td align="left" class="title">Select CTC term:</td><td width="20px"><a href="javascript:hideShowAllTable('table${index}-outer')">
                   <img src="/caaers/images/rule/window-close.gif" id="close-image"/>
                  </a>
              </td>
            </tr>
            <tr><td colspan="2"><div id="table${index}" /></td></tr>
		</tbody>
		</table>
		</div>

        <tags:renderRow field="${fieldGroups[ctcOtherGroup].fields[0]}" style="display: none">
        <jsp:attribute name="label">
            <label>
                <input id="select-meddra-${index}" name="meddraOrVerbatim${index}" type="radio"/>
                ${fieldGroups[ctcOtherGroup].fields[0].displayName}
            </label>
        </jsp:attribute>
   		</tags:renderRow>
   		
   		<tags:renderRow field="${fieldGroups[ctcOtherGroup].fields[1]}" style="display: none">
        <jsp:attribute name="label">
            <label>
                <input id="select-other-${index}" name="meddraOrVerbatim${index}" type="radio"/>
                ${fieldGroups[ctcOtherGroup].fields[1].displayName}
            </label>
        </jsp:attribute>
    </tags:renderRow>
        
    </div>

    <div id="main-fields-${index}" class="main-fields">
		<c:set var="len" value="${fn:length(fieldGroups[mainGroup].fields)}" />
        <c:forEach items="${fieldGroups[mainGroup].fields}" var="field" begin="0" end="${len - 2}">
            <tags:renderRow field="${field}"/>
        </c:forEach>
		<ae:oneOutcome index="${index}" />
		<tags:renderRow field="${fieldGroups[mainGroup].fields[len - 1]}"/>
    </div>
</chrome:division>
