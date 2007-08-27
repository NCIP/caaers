<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
    	var reportIndex = ${empty command.reportIndex ? 'null' : command.reportIndex}
      
    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section11reporterinformation">
    <jsp:attribute name="instructions">
           		<p>
                    The Report will be delivered to 
                </p>

    </jsp:attribute>
    
    <jsp:attribute name="singleFields">
    	<c:forEach items="${command.aeReport.reports[command.reportIndex].reportDeliveries}" varStatus="status" var="report">
    		${status.index+1} - ${report.endPoint}
    	</c:forEach>
    
    <input type="hidden" name="_finish"/>
    </jsp:attribute>

</tags:tabForm>
</body>
</html>
