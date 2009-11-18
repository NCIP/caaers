<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<%@attribute name="path" description="The path to bind" required="true"%>
<%@attribute name="cssClass" description="The 'class' attribute in HTML" %>
<%@attribute name="validationJSClass" description="The classes required for validation framework" %>
<%@attribute name="populatorJS" description="The Javascript to be used as populator" %>
<%@attribute name="selectorJS" description="The javascript to be used as selector" %>
<%@attribute name="optionsJS" description="The javascript that will be sent in as the options to auto completer" %>
<%@attribute name="embededJS" description="The javascript snippent , if specified will be embeded in this element" %>

<%@attribute name="readonly" description="Specifies the readonly attribute" %>
<%@attribute name="required" type="java.lang.Boolean" description="Tells that this field is a required (red asterisk)"%>
<%@attribute name="mandatory" type="java.lang.Boolean" description="Tells whether this field is mandatory (will put the cssClass)"%>
<%@attribute name="displayNamePath" description="This path is used to display the text, when the field is readOnly, if not specified 'path' is used as default " %>
<%@attribute name="initialDisplayValue" description="This is the initial text that is displayed in the UI control (eg: 'Begin typing here')" %>
<%@attribute name="title" description="Specifies the alternate or tooltip title" %>

<%@attribute name="size" description="Specifies the display size of the text field" %>
<%@attribute name="disabled" type="java.lang.Boolean" description="(Deprecated) Specifies whether the field to be displayed in disabled mode" %>
<%@attribute name="enableClearButton" type="java.lang.Boolean" description="If true, will enable the clear button" %>
<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.fields.InputField"%>

<c:set var="_required" value="${required or (not empty field and field.required)}" />
<c:set var="_mandatory" value="${mandatory or (not empty field and field.attributes.mandatory)}" />
<c:set var="fieldValue"><jsp:attribute name="value"><caaers:value path="${path}" /></jsp:attribute></c:set>

<c:if test="${empty fieldValue and _required}"><c:set var="cssValue" value="required" /></c:if>
<c:if test="${empty fieldValue and _mandatory}"><c:set var="cssValue" value="mandatory" /></c:if>
<c:if test="${empty fieldValue and _mandatory and _required}"><c:set var="cssValue" value="mandatory required" /></c:if>
<c:if test="${not empty fieldValue and (_mandatory or _required)}"><c:set var="cssValue" value="valueOK" /></c:if>

<ui:fieldWrapper path="${path}" cssClass="${cssClass}" validationJSClass="${validationJSClass}" readonly="${readonly}"  required="${required}" displayNamePath="${displayNamePath}" title="${title}">
<jsp:attribute name="field">
  <input size="${empty size ? '50' : size}" type="text" id="${path}-input" name="${path}-input" title="${title}" ${disabled ? 'disabled' : ''} value="${initialDisplayValue}" 
	class="autocomplete ${cssValue} ${cssClass} ${validationCss}" onkeydown="suppressEnter(event)"/>
  <c:if test="${enableClearButton and not disabled}"><a id="${path}-clear" onclick="javascript:$('${path}-input').clear();$('${path}').clear();" style="cursor:pointer"><img src="<chrome:imageUrl name="../clear-left-button.png" />" alt="Clear" /></a></c:if>
  <tags:indicator id="${path}-indicator"/>
  <div id="${path}-choices" class="autocomplete" style="display: none"></div>
  <form:hidden path="${path}"/>
</jsp:attribute>
    
<jsp:attribute name="embededJS">
	<c:if test="${(not readonly) and (not empty populatorJS) and (not empty selectorJS)}">
	AE.createStandardAutocompleter('${path}', ${populatorJS}, ${selectorJS}, ${not empty optionsJS ? optionsJS : '{}'});
    </c:if>
	<c:if test="${not readonly}">
   	AE.hash.set('${path}' , '1');
    $('${path}-input').observe('focus', function() {
        if($('${path}').value == '' && AE.hash.get('${path}') == '1'){
             var el = $('${path}-input');
             el.removeClassName('pending-search');
             el.clear();
			 AE.hash.set('${path}' , '0');
		}
    });

    $('${path}-input').observe('blur', function() {
            var el = $('${path}-input');
            if (el.value == '') ValidationManager.setInvalidState(el);
            else
                if (el.hasClassName('validField')) ValidationManager.setNormalState(el);
                else ValidationManager.setInvalidState(el);  
            if(AE.hash.get('${path}') == '0'){
                if (el.value == '') {
                    el.value = '${initialDisplayValue}';
                    el.addClassName('pending-search');
                    AE.hash.set('${path}' , '1');
			}
        };
    });

    if($('${path}').value == ''){
		$('${path}-input').addClassName('pending-search');
	}
	</c:if>
	${embededJS}
</jsp:attribute>
</ui:fieldWrapper>