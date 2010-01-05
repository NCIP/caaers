<%@ include file="/WEB-INF/views/taglibs.jsp"%>

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
	  .errors {
                color: #FFCC00;
            }
  </style>
</head>
<body>
<a href="/caaers/public/login"><img src="/caaers/images/blue/login-logo.png" id="logo" alt="Cancer Adverse Event Reporting System"></a>
  <h2>Please enter your username </h2>
  You will be sent an email to reset your password.
  <c:if test="${noSuchUser}">
  <br><br><img src="/caaers/images/error-yellow.png" style="margin-right:10px"><span class="errors">Invalid username</span>
  </c:if>
  <c:url value="/public/user/resetPassword" var="action"/>
    <form:form action="${action}">
         <c:if test="${not empty param.login_error}">
             <p class="errors">
                  <img src="/caaers/images/error-yellow.png" style="margin-right:10px">Password is too old. Please reset your password.
             </p>
         </c:if>
      <p class="errors">${reset_pwd_error}</p>
      <div class="row">
	<div class="label">Username</div>
	<div class="value">
	  <form:input path="userName"/>
	</div>
	<div class="submit">
		<tags:button type="submit" color="green" icon="reset" value="Reset Password"/>
	</div>
      </div>
    </form:form>
	
</body>
</html>