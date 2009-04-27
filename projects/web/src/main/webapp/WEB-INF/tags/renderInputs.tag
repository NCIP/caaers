<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ctmsfn" uri="http://gforge.nci.nih.gov/projects/ctmscommons/taglibs/functions" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.fields.InputField" %>
<%@attribute name="size" %>
<%@attribute name="cssClass" %>
<%@attribute name="disabled" type="java.lang.Boolean" %>
<caaers:renderFilter elementID="${field.propertyName}">
<c:choose>
    <c:when test="${field.categoryName == 'text'}">
        <form:input path="${field.propertyName}" disabled="${disabled}"
                    size="${empty size ? field.attributes.size : size}"
                    title="${field.displayName}"
                    cssClass="${field.validatorClassName}"/>
    </c:when>

    <c:when test="${field.categoryName == 'date'}">
        <tags:dateInput path="${field.propertyName}"
                        title="${field.displayName}"
                        cssClass="${field.validatorClassName}"/>
    </c:when>

    <c:when test="${field.categoryName == 'split_date'}">
        <tags:splitDateInput
                cssClass="${field.validatorClassName} ${cssClass}" dayRequired="${field.attributes.ddRequired}"
                monthRequired="${field.attributes.mmRequired}" yearRequired="${field.attributes.yyRequired}"
                required="${field.required}" path="${field.propertyName}"/>
    </c:when>

    <c:when test="${field.categoryName == 'textarea'}">
        <form:textarea path="${field.propertyName}"
                       disabled="${disabled}"
                       cols="${not empty field.attributes.cols ? field.attributes.cols : ''}"
                       rows="${not empty field.attributes.rows ? field.attributes.rows : ''}"
                       title="${field.displayName}"
                       cssClass="${field.validatorClassName}"/>
    </c:when>

    <c:when test="${field.categoryName == 'checkbox'}">
        <form:checkbox id="${field.propertyName}"
                       path="${field.propertyName}"
                       disabled="${disabled}"
                       cssClass="${cssClass}"/>
    </c:when>

    <c:when test="${field.categoryName == 'inplace_text'}">
        <ui:inplaceTextField
                path="${field.propertyName}"/>
    </c:when>

    <c:when test="${field.categoryName == 'label'}">
        <ui:value propertyName="${field.propertyName}"/>
    </c:when>

    <c:when test="${field.categoryName == 'image'}">
        <img
                src="<c:url value="/images/chrome/spacer.gif" />"/>
    </c:when>
    <c:when test="${field.categoryName == 'radio'}">
        <form:radiobutton path="${field.propertyName}"
                          disabled="${disabled}"
                          cssClass="${field.required ? 'validate-NOTEMPTY' : ''} ${cssClass}"
                          value="${field.attributes.defaultValue}"/>
    </c:when>

    <c:when test="${field.categoryName == 'select'}">
        <form:select path="${field.propertyName}" items="${field.attributes.options}" disabled="${disabled}"
                     title="${field.displayName}"
                     cssClass="${cssClass} ${field.validatorClassName}"/>
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
		<ui:autocompleter path="${field.propertyName}" 
						size="${empty size ? empty field.attributes.size ? '50' : field.attributes.size : size}"
						title="${field.displayName}"
						disabled="${disabled}"
						enableClearButton="${field.attributes.enableClear}" 
						initialDisplayValue="Begin typing here..."
						cssClass="${field.validatorClassName}"/>
		
       
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
<c:if test="${not empty field.attributes.help and field.categoryName ne 'autocompleter'}">
    <tags:hoverHelp path="${field.propertyName}" code="${field.attributes.help}" />
</c:if>
<tags:errors path="${field.propertyName}"/>
</caaers:renderFilter>
