<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}">
        <jsp:attribute name="instructions">
            Your expedited report has been saved as you moved from page to page in this
            flow.  To exit the flow and return the list of all adverse event collection
            data for this patient and study, please continue.
        </jsp:attribute>
        
    </tags:tabForm>
</body>
</html>