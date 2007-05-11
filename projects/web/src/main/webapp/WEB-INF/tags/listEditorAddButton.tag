<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="divisionClass" required="true"%>
<%@attribute name="label" required="true"%>
<tags:indicator id="add-${divisionClass}-indicator"/>
<input type="button" value="${label}" id="add-${divisionClass}-button"/>
