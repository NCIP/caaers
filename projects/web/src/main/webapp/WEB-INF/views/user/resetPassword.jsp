<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
	<title>Reset Password</title>
	<style type="text/css">
        	.box {
        	    width: 30em;
        	    margin: 0 auto;
        	}
        	.submit {
        	    float: right;
        	    margin-top: 1em;
        	}
	</style>
</head>
<body>
<chrome:box title="Please enter your username" autopad="true">
   <form method="POST" id="resetPassword" action="<c:url value=""/>">
	<c:if test="${not empty param.reset_pwd_error}">
		<p class="errors">User not found in database.</p>
	</c:if>
	<div class="row">
		<div class="label">Username</div>
		<div class="value">
			<input type="text" name="email_address" 
				value=""/>
		</div>
		<div class="submit">
			<input type="submit" value="Reset Password"/>
		</div>
	</div>
   </form>
</chrome:box>
</body>
</html>