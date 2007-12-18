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
  <c:if test="${updated}">
    <c:url value="/public/login" var="login"/>
    <p class="label">Changed password successfully, you should now be able to <a href="${login}">login</a>.</p>
  </c:if>
  <c:if test="${not updated}">
    <chrome:box title="Please enter your credentials" autopad="true">
      <c:url value="/public/user/changePassword" var="action"/>
      <form:form action="${action}">
	<p class="errors">${change_pwd_error.message}</p>
	<div class="row">
	<div class="label">Username</div>
	<div class="value">
	  <form:input path="userName"/>
	</div>
	</div>
	<div class="row">
	  <div class="label">New Password</div>
	  <div class="value">
	    <form:password path="password"/>
	  </div>
	</div>
	<div class="row">
	  <div class="label">Confirm Password</div>
	  <div class="value">
	    <form:password path="passwordConfirm"/>
	  </div>
	</div>
	<div class="row">
	  <div class="submit">
	    <input type="submit" value="Change Password"/>
	  </div>
	</div>
	<form:hidden path="token"/>
      </form:form>
    </chrome:box>
  </c:if>
</body>
</html>