<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
	<title>Change Password</title>
	<style type="text/css">
        	.box {
        	    width: 30em;
        	    margin: 0 auto;
        	}
        	.submit {
        	    float: right;
        	    margin-top: 1em;
        	}
		.label {
		    width: 30em;
		}
	</style>
</head>
<body>
<chrome:box title="Please enter your credentials" autopad="true">
   <form method="POST" id="changePassword" action="<c:url value=""/>">
	<c:if test="${not empty param.change_pwd_error}">
		<p class="errors">Bad password.</p>
	</c:if>	
	<div class="row">
		<div class="label">Username</div>
		<div class="value">
			<input type="text" name="email_address" 
				value="${param.username}"/>
		</div>
	</div>
	<div class="row">
		<div class="label">New Password</div>
		<div class="value">
			<input type="text" name="new_password" 
				value=""/>
		</div>
	</div>
	<div class="row">
		<div class="label">Confirm Password</div>
		<div class="value">
			<input type="text" name="new_password_confirm" 
				value=""/>
		</div>
	</div>
	<div class="row">
		<div class="submit">
			<input type="submit" value="Change Password"/>
		</div>
	</div>
   </form>
</chrome:box>
</body>
</html>