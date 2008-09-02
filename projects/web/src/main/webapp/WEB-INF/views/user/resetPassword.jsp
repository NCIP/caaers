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
	
	
	
	        .box {
            width: 30em;
            margin: 0 auto;
        }
        .submit {
            margin-left:25px;
            margin-top: 1em;
        }
        .forgot {
            float: left;
            margin-top: 1em;
        }
		.forgot a{color:#fff;}
		body{
		background-image:none;
		color:#ccc;
		}
		#header{visibility:hidden}
		
		#all{
		background:none;
		width:400px;
		}
		#build-name{color:#2e3257}
		h2{
		color:#fff;
		font-size:20px;
		font-weight:normal;
		margin-top:50px;
		margin-bottom:10px;
		}
		h1{visibility:hidden;}
		#logo{
	position:absolute;
	top:-135px;
		}
		.errors{color:#FFCC00;}
		.login{
	background-image:url(/caaers/images/blue/power-btn.jpg);
	height: 350px;
	width: 350px;
}
		.login:hover{
	background-image:url(/caaers/images/blue/power-btn.jpg);
	background-position: 0px -350px;
}
		.login:active{
	background-image:url(/caaers/images/blue/power-btn.jpg);
	background-position: 0px -700px;
}
input{outline:none;}
	
  </style>
</head>
<body>
<a href="/caaers/public/login"><img src="/caaers/images/blue/login-logo.png" id="logo" alt="Cancer Adverse Event Reporting System"></a>
  <h2>Please enter your username </h2>
  A new password will be e-mailed to you.
  <c:url value="/public/user/resetPassword" var="action"/>
    <form:form action="${action}">
      <p class="errors">${reset_pwd_error}</p>
      <div class="row">
	<div class="label">Username</div>
	<div class="value">
	  <form:input path="userName"/>
	</div>
	<div class="submit">
	  <input type="submit" value="Reset Password"/>
	</div>
      </div>
    </form:form>

</body>
</html>