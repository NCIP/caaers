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
<%@attribute name="optionsJS" description="The javascript that will be sent in as the options to auto completer" %>
<%@attribute name="embededJS" description="The javascript snippent , if specified will be embeded in this element" %>

<%@attribute name="readonly" description="Specifies the readonly attribute" %>
<%@attribute name="required" type="java.lang.Boolean" description="Tells that this field is a required (red asterisk)"%>
<%@attribute name="displayNamePath" description="This path is used to display the text, when the field is readOnly, if not specified 'path' is used as default " %>
<%@attribute name="initialDisplayValue" description="This is the initial text that is displayed in the UI control (eg: 'Begin typing here')" %>
<%@attribute name="title" description="Specifies the alternate or tooltip title" %>

<%@attribute name="size" description="Specifies the display size of the text field" %>
<%@attribute name="disabled" type="java.lang.Boolean" description="(Deprecated) Specifies whether the field to be displayed in disabled mode" %>
<%@attribute name="enableClearButton" type="java.lang.Boolean" description="If true, will enable the clear button" %>

<ui:fieldWrapper path="${path}" cssClass="${cssClass}" 
  validationJSClass="${validationJSClass}" readonly="${readonly}"  required="${required}" 
  displayNamePath="${displayNamePath}" title="${title}">
<jsp:attribute name="field">
  <input size="${empty size ? '50' : size}" type="text" id="${path}-input" name="${path}-input" title="${title}" ${disabled ? 'disabled' : ''} value="${initialDisplayValue}" 
	class="autocomplete ${cssClass} ${validationCss}" onkeydown="suppressEnter(event)"/>
  <tags:indicator id="${path}-indicator"/>
  <c:if test="${enableClearButton and not disabled}"><input type="button" id="${path}-clear" name="C" value="Clear" onClick="javascript:$('${path}-input').clear();$('${path}').clear();" /></c:if>
  <div id="${path}-choices" class="autocomplete" style="display: none"></div>
  <form:hidden path="${path}"/>
</jsp:attribute>
<jsp:attribute name="embededJS">
	<c:if test="${(not empty populatorJS) and (not empty selectorJS)}">
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
			if(AE.hash.get('${path}') == '0'){
                var el = $('${path}-input');
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