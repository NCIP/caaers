<%@ include file="/WEB-INF/views/taglibs.jsp"%>

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
      
      .box {
          width: 30em;
          margin: 0 auto;
      }
      
      .submit {
          margin-left: 25px;
          margin-top: 1em;
      }
      
      .forgot {
          float: left;
          margin-top: 1em;
      }
      
      .forgot a {
          color: #fff;
      }
      
      body {
          background-image: none;
          color: #ccc;
      }
      
      #header {
          visibility: hidden
      }
      
      #all {
          background: none;
          width: 400px;
      }
      
      #build-name {
          color: #2e3257
      }
      
      h2 {
          color: #fff;
          font-size: 20px;
          font-weight: normal;
          margin-top: 50px;
          margin-bottom: 10px;
      }
      
      h1 {
          visibility: hidden;
      }
      
      #logo {
          position: absolute;
          top: -135px;
      }
      
      .errors {
          color: #FFCC00;
      }
      
      input {
          outline: none;
      }
	  .wide-header {
	  	display:none;
	  }
  </style>
</head>
<body>
	<a href="/caaers/public/login"><img src="/caaers/images/blue/login-logo.png" id="logo" alt="Cancer Adverse Event Reporting System"></a>
	<h2>Please enter your information</h2>
  <c:if test="${updated}">
    <c:url value="/public/login" var="login"/>
    <p class="label">Password changed successfully. You can now <a href="${login}">login</a>.</p>
  </c:if>
  <c:if test="${not updated}">
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
	    <tags:button type="submit" color="green" icon="save" value="Save"/>
	  </div>
	</div>
	<form:hidden path="token"/>
      </form:form>
  </c:if>
</body>
</html>