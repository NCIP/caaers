<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@attribute name="field" type="gov.nih.nci.cabig.caaers.web.fields.InputField"%>

   <c:set var="propertyName" value="${field.propertyName}" ></c:set>
   <span id="${field.propertyName}-indicator">
       <c:if test="${propertyName=='aeReport.adverseEvents[0].startDate'}">
          <tags:requiredIndicator/>
       </c:if>   
   </span>