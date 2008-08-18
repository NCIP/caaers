<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<%@attribute name="path" description="The path to bind" required="true"%>
<%@attribute name="cssClass" description="The 'class' attribute in HTML" %>
<%@attribute name="validationJSClass" description="The classes required for validation framework" %>
<%@attribute name="readonly" description="Specifies the readonly attribute" %>
<%@attribute name="required" type="java.lang.Boolean" description="Tells that this field is a required (red asterisk)"%>
<%@attribute name="displayNamePath" description="This path is used to display the text, when the field is readOnly, if not specified 'path' is used as default " %>
<%@attribute name="title" description="Specifies the alternate or tooltip title" %>

<%@attribute name="cols" description="Specifies the display size of the text area field" %>
<%@attribute name="rows" description="Specifies the display rows of the text area field" %>

<%@attribute name="disabled" type="java.lang.Boolean" description="(Deprecated) Specifies whether the field to be displayed in disabled mode" %>
<ui:fieldWrapper path="${path}" cssClass="${cssClass}" 
  validationJSClass="${validationJSClass}" readonly="${readonly}"  required="${required}" 
  displayNamePath="${displayNamePath}" title="${title}" embededJS="${embededJS}">
<jsp:attribute name="field">
<form:textarea path="${path}" 
  disabled="${disabled}" 
   cols="${not empty cols ? cols : ''}" rows="${not empty rows ? rows : ''}" 
   title="${title}" 
   cssClass="${validationCss} ${cssClass}" />
</jsp:attribute>
</ui:fieldWrapper>