<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<html>
<head>
  <title>Change Password</title>
  <style type="text/css">
    .box {
          width: 30em;
          margin: 0 auto;
      }
      a {
      	color:#fff;
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
	  .errors, div.errors, ul.errors {
                color: #FFCC00;
				border:none;
				margin:0;
            }
  </style>
</head>
<body>
	<a href="/caaers/public/login"><img src="/caaers/images/blue/login-logo.png" id="logo" alt="Cancer Adverse Event Reporting System"></a>
  <c:if test="${updated}">
    <c:url value="/public/login" var="login"/>
    <h2>Password changed successfully.</h2><br/>You can now <a href="${login}">login</a>.
  </c:if>
  <c:if test="${not updated}">
      <c:url value="/public/user/changePassword" var="action"/>
      <form:form action="${action}">
      <h2>Please enter your new password</h2>
	  Your password must contain at least one lower case letter, one numeral, and one special character, and be at least 7 characters long.
	  <br/><br/>
	 <tags:hasErrorsMessage />
	 <ui:row path="userName">
	 	<jsp:attribute name="label">
	 		<ui:label path="userName" text="Username"  required="true" />
	 	</jsp:attribute>
	 	<jsp:attribute name="value">
	 		<ui:text path="userName" required="true"  title="Username"/>
	 	</jsp:attribute>
	 </ui:row>
	 <ui:row path="passwordNew">
	 	<jsp:attribute name="label">
	 		<ui:label path="passwordNew" text="New Password"  required="true" />
	 	</jsp:attribute>
	 	<jsp:attribute name="value">
	 		<form:password path="passwordNew" cssClass="validate-NOTEMPTY" title="New Password" />
	 	</jsp:attribute>
	 </ui:row>
	 <ui:row path="passwordConfirm">
	 	<jsp:attribute name="label">
	 		<ui:label path="passwordConfirm" text="Confirm Password"  required="true" />
	 	</jsp:attribute>
	 	<jsp:attribute name="value">
	 		<form:password path="passwordConfirm" cssClass="validate-NOTEMPTY" title="Confirm Password" />
	 	</jsp:attribute>
	 </ui:row>

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