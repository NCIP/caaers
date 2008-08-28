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
		font-size:30px;
		font-weight:normal;
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
    <link href="../images/caaers.ico" rel="icon"/>
</head>
<body>
<img src="/caaers/images/blue/login-logo.png" id="logo" alt="Cancer Advers Event Reporting System">
<h2>Please Log in</h2>
    <form method="POST" id="login" action="<c:url value="/j_acegi_security_check"/>">

        <c:if test="${not empty param.login_error}">
            <p class="errors"><img src="/caaers/images/error-yellow.png" style="margin-right:10px">Incorrect username and/or password.  Please try again.</p>
        </c:if>

        <div class="row" style="margin-top:20px;">
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
			
                <a href='<c:url value="/public/user/resetPassword" />'>Forgot Password?</a>
            </div>
            <div class="submit">
                <input type="image" src="/caaers/images/blue/icons/configurationController_icon.png" value="Log in" alt="Log in" class="login"/>
            </div>
        </div>
    </form>





</body>
</html>
