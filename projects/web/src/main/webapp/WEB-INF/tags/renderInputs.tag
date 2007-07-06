<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ctmsfn" uri="http://gforge.nci.nih.gov/projects/ctmscommons/taglibs/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.fields.InputField"%>
<%@attribute name="size"%>
<c:choose>
    <c:when test="${field.categoryName == 'text'}"><form:input path="${field.propertyName}" size="${size}"/></c:when>
    <c:when test="${field.categoryName == 'date'}"><tags:dateInput path="${field.propertyName}"/></c:when>
    <c:when test="${field.categoryName == 'textarea'}"><form:textarea path="${field.propertyName}"/></c:when>
    <c:when test="${field.categoryName == 'checkbox'}"><form:checkbox path="${field.propertyName}"/></c:when>
    <c:when test="${field.categoryName == 'select'}">
        <form:select path="${field.propertyName}" items="${field.attributes.options}"/>
    </c:when>
    <c:when test="${field.categoryName == 'composite'}">
        <c:forEach items="${field.attributes.subfields}" var="subfield">
            <label>
                ${subfield.displayName}${empty subfield.displayName ? '' : ':'}
                <tags:renderInputs field="${subfield}"/>
            </label>
        </c:forEach>
    </c:when>
    <c:when test="${field.categoryName == 'autocompleter'}">
        <input size="${empty size ? '50' : size}" type="text" id="${field.propertyName}-input"/>
        <tags:indicator id="${field.propertyName}-indicator"/>
        <div id="${field.propertyName}-choices" class="autocomplete" style="display: none"></div>
        <form:hidden path="${field.propertyName}"/>
    </c:when>
    <c:when test="${field.categoryName == 'longselect'}">
        <div class="longselect" id="${field.propertyName}-longselect">
            <c:forEach items="${field.attributes.options}" var="option" varStatus="stat">
                <label id=${field.propertyName}-row-${stat.index}>
                    <form:radiobutton path="${field.propertyName}" value="${option.key}"
                        id="${field.propertyName}-radio-${stat.index}" cssClass="longselect-radio"/>
                    <span id="${field.propertyName}-text-${stat.index}">${ctmsfn:nl2br(option.value)}</span>
                </label>
            </c:forEach>
        </div>
    </c:when>
    <c:otherwise>
        UNIMPLEMENTED FIELD TYPE ${field.categoryName} for ${field.propertyName}
    </c:otherwise>
</c:choose>
<tags:errors path="${field.propertyName}"/>
<tags:errors path="${field.propertyName}.*"/>
