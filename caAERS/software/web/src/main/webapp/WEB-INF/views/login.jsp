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

        #all {
            margin-top: 50px;
			background: none;
            width: 850px;
        }

        .right {
            position: absolute;
            right: 50px;
            top: -145px;
            margin-left: 25px;
            margin-top: 1em;
            text-align: center;
        }
		.left {
			margin-left:50px;
		}

        .forgot {

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
		.wide-header {
			display:none;
		}

        #taskbar {
            width: 10px;
        }

        #build-name {
            color: #2e3257;
            padding: 0px;
            margin-left: 17px;
            bottom: -175px;
        }

        h2 {
            color: #fff;
            font-size: 30px;
            font-weight: normal;
            margin-top: 20px;
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

        div.row div.label {
            float: left;
            font-weight: normal;
            margin-left: 0.5em;
            text-align: right;
            width: 10em;
        }
    </style>
<!--[if lte IE 6]>
	<style>
		#all {
			margin-top:145px;
		}
		#ie6 {
			height:165px;
			width:100%;
			background-color:#c2c9df;
			position:absolute;
			top:-310px;
		}
		a.get-browser {
			display:block;
			float:left;
			margin-top:10px;
			font-size:18px;
			text-decoration:none;
			color:black;
		}
		a.get-browser img {
			margin-right:8px;
		}
		a.get-browser:hover {
			text-decoration:underline;
		}
	</style>
<![endif]-->
    <link href="../images/caaers.ico" rel="shortcut icon"/>
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
<!--[if lte IE 6]>
<div id="ie6">
	<img src="/caaers/images/blue/no-ie-warning.png" alt="Internet Explorer" style="position:absolute; top:20px; left:20px;">
	<div style="position:absolute; top:20px; left:160px; color:black;">
		<div style="font-size:20px; margin-bottom:5px;">You are using an outdated web browser.</div>
		<div>We cannot guarantee that caAERS will function completely in this browser.</div>
		<div>Please upgrade (or ask your systems administrator to upgrade) to one of the following FREE browsers:</div>
		<a href="http://www.mozilla.com/firefox/" target="_blank" class="get-browser" style="margin-left:5px"><img src="/caaers/images/blue/FF3-logo.png" alt="" />Firefox 3</a>
		<a href="http://www.microsoft.com/windows/internet-explorer/default.aspx" target="_blank" class="get-browser" style="margin-left:40px"><img src="/caaers/images/blue/ie7-logo.png" alt="" />Internet Explorer 8</a>
	</div>
	<img src="/caaers/images/blue/ie-warning-BL.png" alt="" style="position:absolute; bottom:-1px; left:0;">
	<img src="/caaers/images/blue/ie-warning-BR.png" alt="" style="position:absolute; bottom:-1px; right:0;">
</div>
<![endif]-->
<div class="left"><img src="/caaers/images/blue/login-logo.png" id="logo" alt="Cancer Adverse Event Reporting System">

    <h2>Please Log in</h2>

    <form method="POST" id="login" action='<c:url value="/j_acegi_security_check"/>'>

        <c:if test="${not empty param.login_error}">
            <p class="errors"><img src="/caaers/images/error-yellow.png" style="margin-right:10px">Could not login. Valid authentication credentials were not provided.</p>
        </c:if>
        <div class="row" style="margin-top:20px;">
            <div class="label"> Username</div>
            <div class="value"><input type="text" name="j_username" value="${sessionScope['ACEGI_SECURITY_LAST_USERNAME']}" /></div>
        </div>
        <div class="row">
            <div class="label"> Password</div>
            <div class="value"><input type="password" name="j_password" value=""/></div>
        </div>
        <div class="forgot"><a href='<c:url value="/public/user/resetPassword" />'>Forgot Password?</a></div>

        <div class="right">
            <input type="image" src="/caaers/images/blue/power-btn-up.jpg" value="Log in" alt="Log in" height="250px"
                   width="250px" id="power_btn" onMouseOver="return changeImage()" onMouseOut="return changeImageBack()"
                   onMouseDown="return handleMDown()" onMouseUp="return handleMUp()"/>

            <h2>Enter</h2>
        </div>

    </form>
</div>

</body>
</html>
<!-- END views\login.jsp -->
