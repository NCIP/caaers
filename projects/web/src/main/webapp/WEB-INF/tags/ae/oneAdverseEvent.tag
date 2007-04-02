<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<c:set var="ctcTermGroup">ctcTerm${index}</c:set>
<c:set var="ctcOtherGroup">ctcOther${index}</c:set>
<c:set var="mainGroup">main${index}</c:set>

<chrome:division title="Adverse Event ${index + 1}" id="ae-section-${index}" cssClass="ae-section" style="${style}">
    <div id="ctc-details-${index}" class="ctc-details">
        <div class="row">
            <div class="label"><label for="ctc-category-${index}">CTC category</label></div>
            <div class="value">
                <select id="ctc-category-${index}"></select>
            </div>
        </div>
        <tags:renderRow field="${fieldGroups[ctcTermGroup].fields[0]}"/>
        <tags:renderRow field="${fieldGroups[ctcOtherGroup].fields[0]}" style="display: none"/>
    </div>

    <div id="main-fields-${index}" class="main-fields">
        <c:forEach items="${fieldGroups[mainGroup].fields}" var="field">
            <tags:renderRow field="${field}"/>
        </c:forEach>
    </div>
</chrome:division>
