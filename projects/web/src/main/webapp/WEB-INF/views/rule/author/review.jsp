<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Review Rules</title>
</head>
<body>

    <p>
    	Please review the rules defined by clicking on the tabs along the top of the form. Please click save (below) to store the new set of rules.
    </p>
        <%--<form:form cssClass="standard">--%>
        <tags:tabForm tab="${tab}" flow="${flow}" >
	<jsp:attribute name="singleFields">
	        <input type="hidden" name="_finish"/>
	        
    <%--        </form:form>--%>
</jsp:attribute>
</tags:tabForm> 
    
</body>
</html>