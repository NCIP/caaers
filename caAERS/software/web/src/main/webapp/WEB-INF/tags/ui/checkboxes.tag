<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>

<%@attribute name="path" description="The path to store" required="true"%>
<%@attribute name="items" type="java.util.Collection" description="The dynamic list of items to display" required="true"%>
<%@attribute name="itemLabelProperty" description="The method to invoke on an ITEM to generate the display label" %>
<%@attribute name="itemValueProperty" description="The method to invoke on an ITEM to generate the value" %>
<%@attribute name="wrappingElementTag" description="defaults to span tag" %>
<%@attribute name="delimiter" description="The delimiter to put between every item" %>

<%@attribute name="cssClass" description="The 'class' attribute in HTML" %>
<%@attribute name="validationJSClass" description="The classes required for validation framework" %>
<%@attribute name="readonly" description="Specifies the readonly attribute" %>
<%@attribute name="required" type="java.lang.Boolean" description="Tells that this field is a required (red asterisk)"%>
<%@attribute name="displayNamePath" description="This path is used to display the text, when the field is readOnly, if not specified 'path' is used as default " %>
<%@attribute name="title" description="Specifies the alternate or tooltip title" %>
<%@attribute name="disabled" type="java.lang.Boolean" description="(Deprecated) Specifies whether the field to be displayed in disabled mode" %>
<%@attribute name="embededJS" description="A piece of javascript, that if specified will be embeded along with this input"%>
<%@attribute name="onclick" description="A piece of javascript, that should be executed on onClick" %>

<ui:fieldWrapper path="${path}" cssClass="${cssClass}"
                 validationJSClass="${validationJSClass}" readonly="${readonly}"  required="${required}"
                 displayNamePath="${displayNamePath}" title="${title}" embededJS="${embededJS}">
<jsp:attribute name="field">
<form:checkboxes id="${path}" path="${path}" items="${items}" itemLabel="${itemLabelProperty}" itemValue="${itemValueProperty}" disabled="${disabled}" cssClass="${cssClass} ${validationCss} checkBoxes"  title="${title}" onclick="${onclick}" delimiter="<br />"/>
</jsp:attribute>
</ui:fieldWrapper>