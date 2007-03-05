<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter" %>
<%@ page import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.acegisecurity.AuthenticationException" %>

<html>
<head>
    <title>Account login</title>
    <style type="text/css">
        body {
            background-color: #666;
        }
        div#body {
            width: 24em;
            border: 2px outset #000;
            background-color: #fff;
            padding: 0;
            margin: 3em auto;
        }
        h1 {
            background-color: #000;
            color: #fff;
            padding: 0.5em 2em;
            text-align: center;
        }
        form#login {
            margin: 1em 2em;
        }
    </style>
</head>
<body>
<h1>Please log in</h1>

<form method="POST" id="login" action="<c:url value="j_acegi_security_check"/>">
    
    <c:if test="${not empty param.login_error}">
	  <p class="error">Incorrect username and/or password.  Please try again.</p>      
    </c:if>    
    
    <div class="row">
        <div class="label">
            Username
        </div>
        <div class="value">
        	<input type="text" name="j_username" 
        		<c:if test="${not empty param.login_error}">
        			value="<%= session.getAttribute(AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY) %>"
        		</c:if>
        	/>        
        </div>
    </div>      
    <div class="row">
        <div class="label">
            Password
        </div>
        <div class="value">
            <input type="password" name="j_password"/>
        </div>
    </div>
    <div class="row">
        <div class="submit">
            <input type="submit" value="Log in"/>
        </div>
    </div>
    
</form>




</body>
</html>
