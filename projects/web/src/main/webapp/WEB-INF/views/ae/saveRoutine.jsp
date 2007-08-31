<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}">
        <jsp:attribute name="instructions">
            
            To save the set of AEs that you entered, click on Save button. 
            To exit the current flow after saving, click on Save and Continue button. 
            This will return you to the list of AEs for the selected study and participant combination.
            
        </jsp:attribute>
        <jsp:attribute name="singleFields">
            <input type="hidden" name="_finish"/>
        </jsp:attribute>
    </tags:tabForm>
</body>
</html>