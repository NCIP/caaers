<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ctmsfn" uri="http://gforge.nci.nih.gov/projects/ctmscommons/taglibs/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<%@attribute name="path" description="The path to bind" required="true"%>
<%@attribute name="cssClass" description="The 'class' attribute in HTML" %>
<%@attribute name="validationJSClass" description="The classes required for validation framework" %>
<%@attribute name="readonly" description="Specifies the readonly attribute" %>
<%@attribute name="required" type="java.lang.Boolean" description="Tells that this field is a required (red asterisk)"%>
<%@attribute name="displayNamePath" description="This path is used to display the text, when the field is readOnly, if not specified 'path' is used as default " %>
<%@attribute name="title" description="Specifies the alternate or tooltip title" %>
<%@attribute name="embededJS" description="A piece of javascript, that if specified will be embeded along with this input"%>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.fields.InputField"%>

<c:if test="${field != null}"><c:set var="mandatory" value="${field.attributes.mandatory}" /></c:if>

<%@attribute name="options" type="java.util.Map" required="true" description="Specifies the options to be displayed in the select control" %>
<%@attribute name="maxlength" description="Specifies max allowed characters" %>
<%@attribute name="disabled" type="java.lang.Boolean" description="(Deprecated) Specifies whether the field to be displayed in disabled mode" %>
<ui:fieldWrapper path="${path}" cssClass="${cssClass}" 
  validationJSClass="${validationJSClass}" readonly="${readonly}"  required="${required}" 
  displayNamePath="${displayNamePath}" title="${title}" embededJS="${embededJS}">
<jsp:attribute name="field">
	<div class="longselect" id="${path}-longselect">
      <c:forEach items="${options}" var="option" varStatus="stat">
      <label id=${path}-row-${stat.index}>
        <form:radiobutton path="${path}" value="${option.key}" id="${path}-radio-${stat.index}" cssClass="longselect-radio ${cssClass} ${validationCss}" disabled="${disabled}" title="${title}"/>
        <span id="${path}-text-${stat.index}">${ctmsfn:nl2br(option.value)}</span>
      </label>
      </c:forEach>
	</div>
</jsp:attribute>
</ui:fieldWrapper>