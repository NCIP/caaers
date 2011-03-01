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
          margin: 15px auto;
      }
      
      .box {
          width: 30em;
          margin: 0 auto;
      }
     
      
      .forgot {
          float: left;
          margin-top: 1em;
      }
      
      .forgot a {
          color: #fff;
      }
      
      body {
                background:#02307f url(../../images/blue/top_texture_bg.png) top center no-repeat;
                color: #fff;
                font-family:"Lucida Grande",sans-serif;
            }
      
      #header {
          visibility: hidden
      }
      
      #all {
          background: none;
          width: 700px;
      }
      
      #build-name {
                color: #8db0eb;
                font-weight: normal;
                padding: 15px 0px;
                background: url(../../images/footer_divider.png) no-repeat top center;
                bottom: -175px;
                text-shadow: 0 1px 1px #000;
                font-size: 10px;
                margin-top: 0px;
                text-align: center
            }
      
      h2 {
          color: #fff;
          font-size: 20px;
          font-weight: normal;
          margin-top: 50px;
          margin-bottom: 10px;
          text-align: center
      }
      
      h1 {
          visibility: hidden;
      }
      
      #logo {
          position: absolute;
          top: -114px;
          left: 172px;
      }

      #main {
          text-align: center;
          text-shadow: 0 2px 3px black;
      }

      input.required[type="text"], input.required[type="password"], select.required, textarea.required {
          -moz-box-shadow: 0 2px 4px black;
          -webkit-box-shadow: 0 2px 4px black;
          box-shadow: 0 2px 4px black;

      }

      div.row div.value {
          margin-left: 21em;
      }

      div.row div.label {
          width: 20em;
          padding-top: 4px;
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
<a href="/caaers/public/login"><img src="../../images/blue/login-logo.png" id="logo" alt="Cancer Adverse Event Reporting System"></a>
  <h2>Please enter your username: </h2>
  You will be sent an email to reset your password.
  <c:if test="${noSuchUser}">
  <br><br><img src="../../images/error-yellow.png" style="margin-right:10px"><span class="errors">Invalid username</span>
  </c:if>
  <c:url value="/public/user/resetPassword" var="action"/>
    <form:form action="${action}">
         <c:if test="${not empty param.login_error}">
             <p class="errors">
                  <img src="../../images/error-yellow.png" style="margin-right:10px">Password is too old. Please reset your password.
             </p>
         </c:if>
      <p class="errors">${reset_pwd_error}</p>
      <ui:row path="userName">
	 	<jsp:attribute name="label">
	 		<ui:label path="userName" text="Username"  required="true" />
	 	</jsp:attribute>
	 	<jsp:attribute name="value">
	 		<ui:text path="userName" required="true"  title="Username"/>
	 	</jsp:attribute>
	 </ui:row>
     <div class="row">
		<div class="submit">
			<tags:button type="submit" color="green" icon="reset" value="Reset Password"/>
		</div>
     </div>
    </form:form>
	
</body>
</html>