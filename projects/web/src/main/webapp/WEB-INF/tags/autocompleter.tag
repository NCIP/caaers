<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="propertyName"  required="true" %>
<%@attribute name="displayName" required="true" %>
<%@attribute name="required" type="java.lang.Boolean" %>
<%@attribute name="size"%>
<%@attribute name="disabled" type="java.lang.Boolean" %>
<%@attribute name="enableClear" type="java.lang.Boolean" %>

 <input size="${empty size ?  '50' : size}" type="text" id="${propertyName}-input" title="${displayName}" ${disabled ? 'disabled' : ''} class="autocomplete ${required ? 'validate-NOTEMPTY' : ''}"/>
 <tags:indicator id="${propertyName}-indicator"/>
 <c:if test="${enableClear and not disabled}"><input type="button" id="${propertyName}-clear" name="C" value="Clear" onClick="javascript:$('${propertyName}-input').clear();$('${propertyName}').clear();" /></c:if>
 <div id="${propertyName}-choices" class="autocomplete" style="display: none"></div>
 <form:hidden path="${propertyName}"/>