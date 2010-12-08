<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<jsp:useBean id="_today" class="java.util.Date" />
<html>
    <head>
        <title>Enter caAERS</title>
        <style type="text/css">
            .box {
                width: 30em;
                margin: 0 auto;
            } #all {
                margin-top: 50px;
                background: none;
                width: 700px;
            } .right {
                position: absolute;
                right: 100px;
                top: 70px;
                margin-left: 25px;
                margin-top: 1em;
                text-align: center;
            } .left {
                margin-left: 50px;
            } .forgot {
                margin-top: 1em; font-size: 11px;text-shadow: 0 2px 1px black;
            } .forgot a {
                color: #fff; text-decoration: none;
            }.forgot a:hover {
                color: #bdd1ff;
            }
            
            body {
                background:#02307f url(../images/blue/top_texture_bg.png) top center no-repeat;
                color: #ccc;
                font-family: "Lucida Sans Unicode","Lucida Grande",sans-serif;
            } #header {
                visibility: hidden
            } .wide-header {
                display: none;
            } #taskbar {
                width: 10px;
            } #build-name {
                color: #5776aa;
                font-weight: normal;
                padding: 0px;
                margin-left: 68px;
                bottom: -175px;
                text-shadow: 0 -1px 1px #00112d;
            }

            #main {background-image: none}
            
            h2 {
                color: #fff;
                font-size: 30px;
                font-weight: normal;
                margin-top: 20px;
                text-shadow: 0 2px 1px black;
            }
            
            h1 {
                visibility: hidden;
            } #logo {
                position: absolute;
                top: -135px;
                left: 200px;
            } .errors {
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
            top:145px;
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
			.right{
			right: 160px;
                top: 200px;
			}
            </style>
        <![endif]-->
        <link href="../images/caaers.ico" rel="shortcut icon"/>
    </head>
    <body>
        <SCRIPT language="JavaScript">
        	AE.SESSION_TIME_OUT_ENABLED = false;
            upImage = new Image();
            upImage.src = "../images/blue/power-btn-up.png";
            downImage = new Image();
            downImage.src = "../images/blue/power-btn-down.png"
            hoverImage = new Image();
            hoverImage.src = "../images/blue/power-btn-hover.png";
            var loginimg = document.getElementById("power_btn");
            
            function changeImage(){
                document.getElementById("power_btn").src = "../images/blue/power-btn-hover.png";
                return true;
            }
            
            function changeImageBack(){
                document.getElementById("power_btn").src = "../images/blue/power-btn-up.png";
                return true;
            }
            
            function handleMDown(){
                document.getElementById("power_btn").src = "../images/blue/power-btn-down.png";
                return true;
            }
            
            function handleMUp(){
                changeImage();
                return true;
            }
        </SCRIPT>
        <!--[if lte IE 6]>
            <div id="ie6">
            <img src="../images/blue/no-ie-warning.png" alt="Internet Explorer" style="position:absolute; top:20px; left:20px;">
            <div style="position:absolute; top:20px; left:160px; color:black;">
            <div style="font-size:20px; margin-bottom:5px;">You are using an outdated web browser.</div>
            <div>We cannot guarantee that caAERS will function completely in this browser.</div>
            <div>Please upgrade (or ask your systems administrator to upgrade) to one of the following FREE browsers:</div>
            <a href="http://www.mozilla.com/firefox/" target="_blank" class="get-browser" style="margin-left:5px"><img src="../images/blue/FF3-logo.png" alt="" />Firefox 3.5</a>
            <a href="http://www.microsoft.com/windows/internet-explorer/default.aspx" target="_blank" class="get-browser" style="margin-left:40px"><img src="../images/blue/ie7-logo.png" alt="" />Internet Explorer 8</a>
            </div>
            <img src="../images/blue/ie-warning-BL.png" alt="" style="position:absolute; bottom:-1px; left:0;">
            <img src="../images/blue/ie-warning-BR.png" alt="" style="position:absolute; bottom:-1px; right:0;">
            </div>
        <![endif]-->
        <noscript>
            <style>
                #all-login {
                    display: none;
                } #nojs {
                    height: 165px;
                    background-color: #ffa8a8;
                    left: 60px;
                    position: absolute;
                    top: -170px;
                    width: 88%;
                } #jsBR {
                    position: absolute;
                    bottom: 0px;
                    right: 0px;
                } #jsBL {
                    position: absolute;
                    bottom: 0px;
                    left: 0;
                }
            </style>
            <!--[if lte IE 6]>
                <style>
				 #all {
	            top:105px;
	            }
                #nojs {
                height:165px;
                background-color:#ffa8a8;
                left:0px;
                position:absolute;
                top:-140px;
                width:100%;
                }
                #jsBR {
                bottom:-1px;
                }
                #jsBL {
                bottom:-1px;
                }
                </style>
            <![endif]-->
            <div id="nojs">
                <img src="../images/blue/no-js-warning.png" alt="JavaScript" style="position:absolute; top:20px; left:20px;">
                <div style="position:absolute; top:20px; left:160px; color:black;">
                    <div style="font-size:20px; margin-bottom:5px;">
                        caAERS requires JavaScript in order to function.
                    </div>
                    <div>
                        caAERS has detected that JavaScript is either disabled or unavailable in this web browser.
                    </div>
                    <div>
                        Please enable JavaScript or use a browser that has JavaScript functionality.
                    </div>
                </div>
                <img src="../images/blue/js-warning-TL.png" alt="" style="position:absolute; top:0px; left:0;"><img src="../images/blue/js-warning-TR.png" alt="" style="position:absolute; top:0px; right:0px;"><img id="jsBL" src="../images/blue/js-warning-BL.png" alt=""><img id="jsBR" src="../images/blue/js-warning-BR.png" alt="">
            </div>
        </noscript>
        <div id="all-login">
            <div class="left">
                <img src="../images/blue/login-logo.png" id="logo" alt="Cancer Adverse Event Reporting System"><h2>Please Log in</h2>
                <form method="POST" id="login" action='<c:url value="/j_acegi_security_check"/>?rand=${_today.time}'>
                    <c:if test="${not empty param.login_error}">
                        <p class="errors">
                             <img src="../images/error-yellow.png" style="margin-right:10px">${sessionScope['ACEGI_SECURITY_LAST_EXCEPTION'].message}
                        </p>
                    </c:if>
                    
                    
                    
                    
                    <div class="row" style="margin-top:20px;text-shadow: 0 2px 1px black;">
                        <div class="label">
                        <tags:requiredIndicator/>&nbsp;Username
                        </div>
                        <div class="value">
                            <input type="text" name="j_username" class="validate-NOTEMPTY required" value="${sessionScope['ACEGI_SECURITY_LAST_USERNAME']}" title="Username"/>
                        </div>
                    </div>
                    <div class="row" style="text-shadow: 0 2px 1px black;">
                        <div class="label">
                            <tags:requiredIndicator/>&nbsp;Password
                        </div>
                        <div class="value">
                            <input type="password" name="j_password" value="" class="validate-NOTEMPTY required" title="Password"/>
                        </div>
                    </div>            
                    <div class="forgot">
                        <a href='<c:url value="/public/user/resetPassword" />?rand=${_today.time}'>Forgot Password?</a>
                    </div>
                    <div class="right">
                        <input type="image" src="../images/blue/power-btn-up.png" value="Log in" alt="Log in" id="power_btn" onMouseOver="return changeImage()" onMouseOut="return changeImageBack()" onMouseDown="return handleMDown()" onMouseUp="return handleMUp()"/>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
<!-- END views\login.jsp -->
