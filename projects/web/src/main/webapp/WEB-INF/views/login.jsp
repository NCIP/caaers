<!-- BEGIN views\login.jsp -->
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
		#taskbar{width:10px;}

		#all{
		background:none;
		width:400px;
		}
		#build-name{color:#2e3257}
		h2{
		color:#fff;
		font-size:30px;
		font-weight:normal;
		margin-top:20px;
		}
		h1{visibility:hidden;}
		#logo{
	position:absolute;
	top:-135px;
		}
		.errors{color:#FFCC00;}
input{outline:none;}
    </style>
    <link href="../images/caaers.ico" rel="icon"/>
</head>
<body>

<SCRIPT language="JavaScript">
    upImage = new Image();
    upImage.src = "/caaers/images/blue/power-btn-up.jpg";
    downImage = new Image();
    downImage.src = "/caaers/images/blue/power-btn-down.jpg"
    hoverImage = new Image();
    hoverImage.src = "/caaers/images/blue/power-btn-hover.jpg";
    var loginimg = document.getElementById("power_btn");
    
    function changeImage()
    {
        document.getElementById("power_btn").src = "/caaers/images/blue/power-btn-hover.jpg";
        return true;
    }
    function changeImageBack()
    {
        document.getElementById("power_btn").src = "/caaers/images/blue/power-btn-up.jpg";
        return true;
    }
    function handleMDown()
    {
        document.getElementById("power_btn").src = "/caaers/images/blue/power-btn-down.jpg";
        return true;
    }
    function handleMUp()
    {
        changeImage();
        return true;
    }
</SCRIPT>

<img src="/caaers/images/blue/login-logo.png" id="logo" alt="Cancer Adverse Event Reporting System">
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
                <input type="password" name="j_password" value=""/>
            </div>
        </div>
        <div class="row">
	    <div class="forgot">
			
                <a href='<c:url value="/public/user/resetPassword" />'>Forgot Password?</a>
            </div>
            <div class="submit">
                <input type="image" src="/caaers/images/blue/power-btn-up.jpg" value="Log in" alt="Log in" height="350px" width="350px" id="power_btn" onMouseOver="return changeImage()" onMouseOut= "return changeImageBack()" onMouseDown="return handleMDown()" onMouseUp="return handleMUp()"/>
            </div>
        </div>
    </form>

</body>
</html>
<!-- END views\login.jsp -->