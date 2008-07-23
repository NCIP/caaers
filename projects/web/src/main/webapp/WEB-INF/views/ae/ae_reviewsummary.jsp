<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" formName="review">
<jsp:attribute name="singleFields">
	<input type="hidden" name="_finish" value="true"/>
	<chrome:division title="Report Details">
		The report details go here.
	</chrome:division>
	<chrome:division title="Solicited adverse event(s)">
		<c:if test='${command.adverseEventReportingPeriod != null}'>
    			<table id="solicitedTable" width="100%" class="tablecontent">
    				<tr>
    					<th scope="col" align="left"><b>Select</b></th>
    					<th scope="col" align="left" width="30%"><b>Term</b> </th>
    					<th scope="col" align="left"><b>Grade</b> </th>
    					<th scope="col" align="left"><b>Attribution</b> </th>
   						<th scope="col" align="left"><b>Hospitalization</b> </th>
    					<th scope="col" align="left"><b>Expected</b> </th>
    				</tr>
    				<tr id="solicitedBlankRow" />
       				<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
       					<c:if test="${ae.solicited == true}">
	       					<ae:oneSaeRow index="${status.index}"/>
	       				</c:if>
       				</c:forEach>
       			</table>
       		</c:if>	
	</chrome:division>
	<chrome:division title="Observed adverse event(s)">
		<c:if test='${command.adverseEventReportingPeriod != null}'>
        	<table id="observedTable" width="100%" class="tablecontent">
    			<tr>
    				<th scope="col" align="left"><b>Select</b></th>
    				<th scope="col" align="left" width="30%"><b><tags:requiredIndicator/>Term</b> </th>
    				<th scope="col" align="left"><b><tags:requiredIndicator/>Grade</b> </th>
    				<th scope="col" align="left"><b><tags:requiredIndicator/>Attribution</b> </th>
    				<th scope="col" align="left"><b><tags:requiredIndicator/>Hospitalization</b> </th>
    				<th scope="col" align="left"><b><tags:requiredIndicator/>Expected</b> </th>
    			</tr>
    			<tr id="observedBlankRow" />
    			<c:forEach items="${command.adverseEventReportingPeriod.adverseEvents}" varStatus="status" var="ae">
            		<c:if test="${ae.solicited == false}">
	            		<ae:oneSaeRow index="${status.index}"/>
	            	</c:if>
            	</c:forEach>
            </table>
        
        </c:if> 
	</chrome:division>
</jsp:attribute>	
</tags:tabForm>
</body>
</html>