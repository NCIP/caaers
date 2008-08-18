<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<%@attribute name="path" description="The path to bind" required="true"%>
<%@attribute name="cssClass" description="The 'class' attribute in HTML" %>
<%@attribute name="validationJSClass" description="The classes required for validation framework" %>
<%@attribute name="populatorJS" description="The Javascript to be used as populator" %>
<%@attribute name="selectorJS" description="The javascript to be used as selector" %>
<%@attribute name="embededJS" description="The javascript snippent , if specified will be embeded in this element" %>

<%@attribute name="readonly" description="Specifies the readonly attribute" %>
<%@attribute name="required" type="java.lang.Boolean" description="Tells that this field is a required (red asterisk)"%>
<%@attribute name="displayNamePath" description="This path is used to display the text, when the field is readOnly, if not specified 'path' is used as default " %>
<%@attribute name="title" description="Specifies the alternate or tooltip title" %>

<%@attribute name="size" description="Specifies the display size of the text field" %>
<%@attribute name="disabled" type="java.lang.Boolean" description="(Deprecated) Specifies whether the field to be displayed in disabled mode" %>
<%@attribute name="enableClearButton" type="java.lang.Boolean" description="If true, will enable the clear button" %>
<ui:fieldWrapper path="${path}" cssClass="${cssClass}" 
  validationJSClass="${validationJSClass}" readonly="${readonly}"  required="${required}" 
  displayNamePath="${displayNamePath}" title="${title}">
<jsp:attribute name="field">
  <c:set var="displayText"><caaers:value path="${displayNamePath}" /></c:set>
  <input size="${empty size ? '50' : size}" type="text" id="${path}-input" title="${title}" ${disabled ? 'disabled' : ''} value="${displayText}" 
	class="autocomplete ${cssClass} ${validationCss}"/>
  <tags:indicator id="${path}-indicator"/>
  <c:if test="${enableClear and not disabled}"><input type="button" id="${path}-clear" name="C" value="Clear" onClick="javascript:$('${path}-input').clear();$('${path}').clear();" /></c:if>
  <div id="${path}-choices" class="autocomplete" style="display: none"></div>
  <form:hidden path="${path}"/>
  <c:remove var="displayText" />
</jsp:attribute>
<jsp:attribute name="embededJS">
	<c:if test="${(not empty populatorJS) and (not empty selectorJS)}">
	AE.createStandardAutocompleter('${path}',${populatorJS},${selectorJS});	
	</c:if>
	${embededJS}
</jsp:attribute>
</ui:fieldWrapper>