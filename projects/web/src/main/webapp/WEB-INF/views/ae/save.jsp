<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}">
        <jsp:attribute name="instructions">
            Please review your entered report by clicking on the tabs along the top
            of the form.  When you have entered as much data as you would like, please
            click save (below) to store the new report.
        </jsp:attribute>
        <jsp:attribute name="singleFields">
            <input type="hidden" name="_finish"/>
        </jsp:attribute>
    </tags:tabForm>
</body>
</html>