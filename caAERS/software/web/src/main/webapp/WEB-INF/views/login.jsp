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
                background: none;
            } .right {
                position: absolute;
                right: 116px;
                top: 135px;
                margin-left: 25px;
                margin-top: 1em;
                text-align: center;
            } .left {
                margin-left: 0px;
            } .forgot {
                margin-top: 0; font-size: 11px;text-shadow: 0 2px 1px black; text-align: center;
            } .forgot a {
                color: #bdd1ff; text-decoration: none; border-bottom: 1px dotted #bdd1ff;
            }.forgot a:hover {
                color: #fff;border-bottom: 1px solid #fff;
            }
            
            body {
                background:#02307f url(../images/blue/top_texture_bg.png) top center no-repeat;
                color: #fff;
                font-family:"Lucida Sans Unicode",sans-serif;
            } #header {
                visibility: hidden
            } .wide-header {
                display: none;
            } #taskbar {
                width: 10px;
            } #build-name {
                color: #8db0eb;
                font-weight: normal;
                padding: 15px 0px;
                background: url(../images/footer_divider.png) no-repeat top center;
                bottom: -175px;
                text-shadow: 0 1px 1px #000;
                font-size: 10px;
                margin-top: 30px;
                text-align: center
            }

            .required-indicator {
                color: yellow
            }

            #main {background: none;-moz-box-shadow: none; -webkit-box-shadow: none; box-shadow: none;}
            
            h2 {
                color: #fff;
                font-size: 30px;
                font-weight: normal;
                margin: 30px 0;
                text-shadow: 0 2px 1px black;
                text-align: center;
            }
            
            h1 {
                visibility: hidden;
            } #logo {
                position: absolute;
                top: -135px;
                left: 184px;
            } .errors {
                color: #FFCC00;
            }
            
            input {
                outline: none;
            }
            
            div.row div.label {
                float: left;
                font-weight: normal;
                margin-left: 3.5em;
                text-align: right;
                width: 9.5em;
                padding-top: 4px;
                font-size: 16px
            }

            div.row div.value {
                margin-left: 16em;

            }

            ul.errors {
                color:#F05757;
                font-size: 11px;
            }

            input.required[type="text"], input.required[type="password"], select.required, textarea.required  {
                -moz-box-shadow: 0 2px 4px black;
                -webkit-box-shadow: 0 2px 4px black;
                box-shadow: 0 2px 4px black;
            }
            
            .container {
			padding: 10px 20px 20px 20px;
			background: #fff;
			-moz-border-radius:8px;
			-webkit-border-radius:8px;
			border-radius:8px;
			-moz-box-shadow:0 0 8px black;
			-webkit-box-shadow:0 0 8px black;
			box-shadow:0 0 8px black;
			color: #666;
			}
			#splash {
			width: 380px; 
			float:left;
			 border-right: 1px solid #ddd;
			 margin-top: 15px;
			}

			input:focus
			{
			  outline: none;
			}
			a:focus { 
			    outline: none; 
			}
			button:focus { 
			    outline: none; 
			}
			
			::selection {
			        background: #999; /* Safari */
					color: white;
					text-shadow: none;
			        }
			::-moz-selection {
			        background: #999; /* Firefox */
					color: white;
			}
			
			p, blockquote, ul, ol, dl, form, table, pre{margin:0 0 1.5em 0;}
				ul, ol, dl{padding:0;}
				ul ul, ul ol, ol ol, ol ul, dd{margin:0;}
				li{padding:0;display:list-item;list-style-position:outside;}	
				blockquote, dd{padding:0 0 0 2em;}
				pre, code, samp, kbd, var{font:100% mono-space,monospace;}
				pre{overflow:auto;}
				abbr, acronym{
					text-transform:uppercase;
					border-bottom:1px dotted #000;
					letter-spacing:1px;
					}
				abbr[title], acronym[title]{cursor:help;}
				small{font-size:.9em;}
				sup, sub{font-size:.8em;}
				em, cite, q{font-style:italic;}
				img{border:none;}			
				hr{display:none;}	
				table{width:100%;border-collapse:collapse;}
				th,caption{text-align:left;}
				.clear {clear: both;}
			
				label{display:block;}
				fieldset{margin:0;padding:0;border:none;}
				legend{font-weight:bold;}
				input[type="radio"],input[type="checkbox"], .radio, .checkbox{margin:0 .25em 0 0;}
			
				.cl {clear: both;}
			#wrapper {
			  margin: 0 auto;
			  width: 830px;
			  font-size: 13px;
			 
			}
			.emailSent {margin: 0 auto; width: 388px}
			 .logo {text-align: center; margin-bottom:20px;}
			
			
				
			 #forgotPassword form#login, .login #forgotPassword h1 {width: 388px; margin: 0 auto; float:none; margin-top: 15px}
			
			
			/* login form */	
			
			
			form#login {
				float: right;
				width:388px;
				margin-top:15px;
			}
			#splash p {margin-bottom: 8px; padding-right:25px;}
			.topDivider {background: url(../images/blue/top_divider.png) no-repeat top; width: 830px; height: 15px; }
			
			.footer {background: url(../images/blue/footer_divider.png) no-repeat top; width: 830px; padding: 20px; text-align:center;text-shadow: 0 2px 2px black; font-size: 10px; color:#8DB0EB;}
			
			
			
			h1 {font-size: 25px; color:#02307F; font-weight:normal;margin-bottom:10px; margin-top: 10px;
			}	
				
			 h1.signIn {width: 388px; float:right; margin:25px 0 0 0}
				
			 a,  a:visited{transition: all ease-in-out 0.2s;
				-moz-transition: all ease-in-out 0.2s;
				-webkit-transition: all ease-in-out 0.2s;
				color:#4D9AD6;font-size: 15px; text-decoration: none; font-family:Georgia, "Times New Roman", Times, serif; font-style:italic; border-bottom:1px solid white; padding-bottom:3px;}
			 a:hover{color:#9B7FBA; border-bottom: solid 1px #ccc }
			
			
			form#login p { position: relative; margin: 10px 0;}
			form#login p label { position: absolute; top: 0; left: 0;}
			form#login p br {display: none;}
			
			
			form#login form fieldset p input,
			form#login form fieldset p textarea {
				display: block;
				padding: 4px;
				width: 400px;
				margin: 0;
				
			}
			
			form#login fieldset p label {
				width: 350px;
				display: block;
				margin: 8px 5px 8px 30px;
				padding: 0;
				cursor:text;
				font-size: 16px;
				text-shadow:none;
			}
			
			form#login fieldset p textarea {
				padding: 2px;
				width: 404px;
			}
			
			form#login span {cursor: pointer}
			
			form#login fieldset p textarea,
			form#login fieldset p input {
				width: 350px;
				padding:9px 5px 9px 29px;
				border: 1px solid #ccc;
			    border-radius: 5px 5px 5px 5px;
				-moz-box-shadow: 0 1px 0px rgba(255,255,255,0.3), 0 2px 3px rgba(0,0,0,0.1) inset;
			    -webkit-box-shadow: 0 1px 0px rgba(255,255,255,0.3), 0 2px 3px rgba(0,0,0,0.1) inset;
				box-shadow: 0 1px 0px rgba(255,255,255,0.3), 0 2px 3px rgba(0,0,0,0.1) inset;
			    font-size: 16px;
				text-shadow:none;
				transition: all ease-in-out 0.2s;
				-moz-transition: all ease-in-out 0.2s;
				-webkit-transition: all ease-in-out 0.2s;
			}
			
			form#login fieldset p input:focus {border-color: #4D9AD6;
			-moz-box-shadow:0 0 6px #4D9AD6;
			box-shadow:0 0 6px #4D9AD6;
			
			}
			
			
			form#login fieldset p label {
				color: #777;
			}
			form#login fieldset p input.username {background:white url(../images/blue/username.png) no-repeat 7px 12px;}
			form#login fieldset p input.password {background:white url(../images/blue/key.png) no-repeat 7px 12px;} 
			form#login fieldset p input.email {background:white url(../images/blue/email.png) no-repeat 7px 10px;} 
				
				
			form#login .submitButton {float:right; position:relative;width: 130px;}	
			form#login .enter{
				color: #FFFFFF;
				background: #d6aded;
				background: -moz-linear-gradient(top,  #d6aded 0%, #a37ebc 50%, #9a71b6 50%, #82589e 100%);
				background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#d6aded), color-stop(50%,#a37ebc), color-stop(50%,#9a71b6), color-stop(100%,#82589e));
				background: -webkit-linear-gradient(top,  #d6aded 0%,#a37ebc 50%,#9a71b6 50%,#82589e 100%);
				background: -o-linear-gradient(top,  #d6aded 0%,#a37ebc 50%,#9a71b6 50%,#82589e 100%);
				background: -ms-linear-gradient(top,  #d6aded 0%,#a37ebc 50%,#9a71b6 50%,#82589e 100%);
				background: linear-gradient(top,  #d6aded 0%,#a37ebc 50%,#9a71b6 50%,#82589e 100%);
				filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#d6aded', endColorstr='#82589e',GradientType=0 );
				position: absolute;
				padding: 8px 35px;
				text-shadow: 0 -1px #518688;
				-moz-box-shadow: 0 1px 0px rgba(255,255,255,0.4) inset, 0 2px 3px #aaa;
				-webkit-box-shadow: 0 1px 0px rgba(255,255,255,0.4) inset, 0 2px 3px #aaa;
				box-shadow: 0 1px 0px rgba(255,255,255,0.4) inset, 0 2px 3px #aaa;
				font-size: 16px;
				-moz-border-radius: 30px;
				-webkit-border-radius: 30px;
				border-radius: 30px;
				text-shadow: 0 -1px #82589e;
				cursor:pointer;
				
				border:1px solid #82589E;;
				}
			form#login .enter:hover {
				background: #dbb5f1;
				background: -moz-linear-gradient(top,  #dbb5f1 0%, #b087cb 50%, #a87ac6 50%, #9460b6 100%);
				background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#dbb5f1), color-stop(50%,#b087cb), color-stop(50%,#a87ac6), color-stop(100%,#9460b6));
				background: -webkit-linear-gradient(top,  #dbb5f1 0%,#b087cb 50%,#a87ac6 50%,#9460b6 100%);
				background: -o-linear-gradient(top,  #dbb5f1 0%,#b087cb 50%,#a87ac6 50%,#9460b6 100%);
				background: -ms-linear-gradient(top,  #dbb5f1 0%,#b087cb 50%,#a87ac6 50%,#9460b6 100%);
				background: linear-gradient(top,  #dbb5f1 0%,#b087cb 50%,#a87ac6 50%,#9460b6 100%);
				filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#dbb5f1', endColorstr='#9460b6',GradientType=0 );	
			}
			
			form#login .enter:active {
				background: #82589e;
				background: -moz-linear-gradient(top,  #82589e 0%, #9a71b6 50%, #a37ebc 50%, #d6aded 100%);
				background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#82589e), color-stop(50%,#9a71b6), color-stop(50%,#a37ebc), color-stop(100%,#d6aded));
				background: -webkit-linear-gradient(top,  #82589e 0%,#9a71b6 50%,#a37ebc 50%,#d6aded 100%);
				background: -o-linear-gradient(top,  #82589e 0%,#9a71b6 50%,#a37ebc 50%,#d6aded 100%);
				background: -ms-linear-gradient(top,  #82589e 0%,#9a71b6 50%,#a37ebc 50%,#d6aded 100%);
				background: linear-gradient(top,  #82589e 0%,#9a71b6 50%,#a37ebc 50%,#d6aded 100%);
				filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#82589e', endColorstr='#d6aded',GradientType=0 );
				top: 2px;	
				-moz-box-shadow: 0 1px 0px rgba(255,255,255,0.4) inset, 0 2px 3px #fff;
				-webkit-box-shadow: 0 1px 0px rgba(255,255,255,0.4) inset, 0 2px 3px #fff;
				box-shadow: 0 1px 0px rgba(255,255,255,0.4) inset, 0 2px 3px #fff;
			}
			
			
			form#login .submit button img {
				margin-right: 8px;
				vertical-align: middle;	
			}
			form#login .remember {font-size: 13px; text-shadow: 0 3px 2px black;}
				
			form#login .forgot{text-align:right;font-size:11px;}
			form#login .back{padding:1em 0;border-top:1px solid #eee;text-align:right;font-size:11px;}
			form#login .error{
				float:left;
				position:absolute;
				right:406px;
				top:0;
				background: #ffe5e5;
				background: -moz-linear-gradient(top,  #ffe5e5 0%, #f4a8a8 100%);
				background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffe5e5), color-stop(100%,#f4a8a8));
				background: -webkit-linear-gradient(top,  #ffe5e5 0%,#f4a8a8 100%);
				background: -o-linear-gradient(top,  #ffe5e5 0%,#f4a8a8 100%);
				background: -ms-linear-gradient(top,  #ffe5e5 0%,#f4a8a8 100%);
				background: linear-gradient(top,  #ffe5e5 0%,#f4a8a8 100%);
				filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffe5e5', endColorstr='#f4a8a8',GradientType=0 );
				padding:6px 10px;	
				font-size:11px;
				color:#8D2A28;
				text-shadow:0 1px white;
				text-align:left;
				white-space:nowrap;
				border:1px solid #F8B7AA;
				-moz-border-radius:4px;
				-webkit-border-radius:4px;
				border-radius:4px;
				-moz-box-shadow: 0 1px 0px rgba(255,255,255,0.4) inset, 0 3px 8px rgba(0,0,0,0.35);
				-webkit-box-shadow: 0 1px 0px rgba(255,255,255,0.4) inset, 0 3px 8px rgba(0,0,0,0.35);
				box-shadow: 0 1px 0px rgba(255,255,255,0.4) inset, 0 3px 8px rgba(0,0,0,0.35);
				
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
    <body class="login">
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
            <div id="wrapper">

 <p align="center" style="margin-bottom:0"><img class="logo" src="../images/blue/login-logo.png" /></p>
 <div class="cl"></div>
 <div class="topDivider"></div>
 <div class="container">
 <div id="splash"> 
 <h1> A little about caAERS…</h1>
 <p>
The caBIG Adverse Event Reporting System (caAERS) is an open source software tool that is used to record and report adverse events that occur during clinical trials. This tool supports regulatory and protocol compliance for adverse event reporting and allows local collection, management, and querying of adverse event data. This tool also supports service based integration of data from other clinical trials management systems.</p>
 <p><a target="_blank" href="https://wiki.nci.nih.gov/display/caAERS/caAERS;jsessionid=4B63E7527DFDBF9DF7432C2FCDF7C660#caAERS-ToolOverview">Learn more</a></p>
 
 
 </div>
 <h1 class="signIn"> Please sign in...</h1>

<form id="login" method="post" action='<c:url value="/j_acegi_security_check"/>?rand=${_today.time}' name="login"> 
   <fieldset>
    			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
                    <c:if test="${not empty param.login_error}">
                        <p class="errors">
                             <img src="../images/error-yellow.png" style="margin-right:10px">${sessionScope['ACEGI_SECURITY_LAST_EXCEPTION'].message}
                        </p>
                    </c:if>
    
   			<p>
				<label for="username">Username</label><br />
				<input type="text" name="j_username" value="${sessionScope['ACEGI_SECURITY_LAST_USERNAME']}" id="username" class="validate-NOTEMPTY required" title="Username">
			</p>
			<p>
				<label for="password">Password</label><br />
				<input type="password" name="j_password" value="" id="password" class="validate-NOTEMPTY required" title="Password">
			</p>
            
            
            
        	<input type="checkbox" name="remember">
			<span onClick="document.login.remember.checked=(! document.login.remember.checked);">Remember me</span>

        	
             
            <div class="submitButton"><input type="submit" tabindex="4" value="Sign in" class="enter" name="login_submit" id="login_submit"></div>
    <br/>
              <a href='<c:url value="/public/user/resetPassword" />?rand=${_today.time}'>Forgot Password?</a>
     </fieldset>
  </form>	
    	


 <div class="cl"></div>  
 </div>  
  </div><!-- wrapper --> 
  </div>
 </body>
</html>
<!-- END views\login.jsp -->
