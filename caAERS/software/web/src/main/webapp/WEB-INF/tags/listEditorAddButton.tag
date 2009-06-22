<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="divisionClass" required="true"%>
<%@attribute name="label" required="true"%>
<%@attribute name="onClick" required="false"%>
<%@attribute name="buttonCssClass"  required="false"%>
<tags:indicator id="add-${divisionClass}-indicator"/>
<tags:button icon="add" size="small" value="${label}" type="button" color="blue" id="add-${divisionClass}-button" onclick="${onClick}"/>
