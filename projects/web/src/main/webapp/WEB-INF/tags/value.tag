<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@attribute name="propertyName" required="true"%>
<%@attribute name="cssClass" %>
<span id="${propertyName}-id" class="${cssClass}"><caaers:value path="${propertyName}" /></span>