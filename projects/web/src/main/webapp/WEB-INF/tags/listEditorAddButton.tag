<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="divisionClass" required="true"%>
<%@attribute name="label" required="true"%>
<%@attribute name="onClick" required="false"%>
<%@attribute name="buttonCssClass"  required="false"%>
<tags:indicator id="add-${divisionClass}-indicator"/>
<input type="button" value="${label}" onClick="${onClick}" id="add-${divisionClass}-button" class="${empty buttonCssClass ? '' : buttonCssClass }"/>
