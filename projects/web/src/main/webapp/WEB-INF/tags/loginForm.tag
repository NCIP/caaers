<%@tag%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="failed" type="java.lang.Boolean"%>
<%@attribute name="ajax" type="java.lang.Boolean"%>

<form:form method="post" id="login">
    <c:if test="${failed}">
        <p class="error"> Incorrect username and/or password.  Please try again. </p>
    </c:if>
    <div class="row">
        <div class="label">
            <form:label path="username">Username</form:label>
        </div>
        <div class="value">
            <form:input path="username"/>
        </div>
    </div>
    <div class="row">
        <div class="label">
            <form:label path="password">Password</form:label>
        </div>
        <div class="value">
            <form:password path="password"/>
        </div>
    </div>
    <div class="row">
        <div class="submit">
            <input type="submit" value="Log in"/>
        </div>
    </div>
</form:form>
