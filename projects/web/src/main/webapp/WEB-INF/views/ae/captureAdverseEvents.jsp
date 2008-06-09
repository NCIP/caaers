<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/extremecomponents.css"/>">
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
 <head>
 </head>
 <body>
	 <tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section2enteraes">
        <jsp:attribute name="instructions">
        <tags:instructions code="instruction_ae_enterBasics" />
        </jsp:attribute>
      		
      	<jsp:attribute name="singleFields">
      		<c:forEach items="${fieldGroups.reportingPeriod.fields}" var="field">
      			<tags:renderRow field="${field}" />
      		</c:forEach>
		</jsp:attribute>
      
        <jsp:attribute name="repeatingFields">
        	<chorme:division title="Solicited adverse event(s)">
        	</chorme:division>
        	<chrome:division title="Observed adverse event(s)">
        		<tags:aeTermQuery version="3" isMeddra="false" callbackFunctionName="abcd">
        		</tags:aeTermQuery>
        	</chrome:division>
          
        </jsp:attribute>

    </tags:tabForm>
 </body>
</html>