<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
    <title>Enter caAERS</title>
    <style type="text/css">
        .box {
            width: 30em;
            margin: 0 auto;
        }
        .submit {
            float: right;
            margin-top: 1em;
        }
        .forgot {
            float: left;
            margin-top: 1em;
        }
    </style>
</head>
<body>
<chrome:box title="Please log in" autopad="true">
    <form method="POST" id="login" action="<c:url value="/j_acegi_security_check"/>">

        <c:if test="${not empty param.login_error}">
            <p class="errors">Incorrect username and/or password.  Please try again.</p>
        </c:if>

        <div class="row">
            <div class="label">
                Username
            </div>
            <div class="value">
                <input type="text" name="j_username"
                    value="${sessionScope['ACEGI_SECURITY_LAST_USERNAME']}"
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
	    <div class="forgot">
                <a href="/caaers/public/user/resetPassword">Forgot Password?</a>
            </div>
            <div class="submit">
                <input type="submit" value="Log in"/>
            </div>
        </div>
    </form>
</chrome:box>




</body>
</html>
