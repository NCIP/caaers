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
                font-family:"Lucida Sans Unicode",sans-serif;
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
      
      
      #logo {
          position: absolute;
          top: -83px;
          left: 172px;
      }

      #main {
          text-align: center;
          text-shadow: 0 2px 3px black;
      background: none;-moz-box-shadow: none; -webkit-box-shadow: none; box-shadow: none;
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
			form#login fieldset p input.username {background:white url(../../images/blue/username.png) no-repeat 7px 12px;}
			form#login fieldset p input.password {background:white url(../../images/blue/key.png) no-repeat 7px 12px;} 
			form#login fieldset p input.email {background:white url(../../images/blue/email.png) no-repeat 7px 10px;} 
				
				
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
  
          <script>
          jQuery(function(jQuery){
          		   
          	// simple jQuery validation script
          	jQuery('form').submit(function(){
          		
          		var valid = true;
          		var errormsg = 'This field is required!';
          		var errorcn = 'error';
          		
          		jQuery('.' + errorcn, this).remove();			
          		
          		jQuery('.required', this).each(function(){
          			var parent = jQuery(this).parent();
          			if( jQuery(this).val() == '' ){
          				var msg = jQuery(this).attr('title');
          				msg = (msg != '') ? msg : errormsg;
          				jQuery('<span class="'+ errorcn +'">'+ msg +'</span>')
          					.appendTo(parent)
          					.fadeIn('fast')
          					.click(function(){ jQuery(this).remove(); })
          				valid = false;
          			};
          		});
          		return valid;
          	});
          	
          })	
		</script>
		
		<script>
		(function(jQuery){		
		    jQuery.InFieldLabels = function(label,field, options){
		        // To avoid scope issues, use 'base' instead of 'this'
		        // to reference this class from internal events and functions.
		        var base = this;
		        
		        // Access to jQuery and DOM versions of each element
		        base.jQuerylabel = jQuery(label);
		        base.label = label;

		 		base.jQueryfield = jQuery(field);
				base.field = field;
		        
				base.jQuerylabel.data("InFieldLabels", base);
				base.showing = true;
		        
		        base.init = function(){
					// Merge supplied options with default options
		            base.options = jQuery.extend({},jQuery.InFieldLabels.defaultOptions, options);

					// Check if the field is already filled in
					if(base.jQueryfield.val() != ""){
						base.jQuerylabel.hide();
						base.showing = false;
					};
					
					base.jQueryfield.focus(function(){
						base.fadeOnFocus();
					}).blur(function(){
						base.checkForEmpty(true);
					}).bind('keydown.infieldlabel',function(e){
						// Use of a namespace (.infieldlabel) allows us to
						// unbind just this method later
						base.hideOnChange(e);
					}).change(function(e){
						base.checkForEmpty();
					}).bind('onPropertyChange', function(){
						base.checkForEmpty();
					});
		        };

				// If the label is currently showing
				// then fade it down to the amount
				// specified in the settings
				base.fadeOnFocus = function(){
					if(base.showing){
						base.setOpacity(base.options.fadeOpacity);
					};
				};
				
				base.setOpacity = function(opacity){
					base.jQuerylabel.stop().animate({ opacity: opacity }, base.options.fadeDuration);
					base.showing = (opacity > 0.0);
				};
				
				// Checks for empty as a fail safe
				// set blur to true when passing from
				// the blur event
				base.checkForEmpty = function(blur){
					if(base.jQueryfield.val() == ""){
						base.prepForShow();
						base.setOpacity( blur ? 1.0 : base.options.fadeOpacity );
					} else {
						base.setOpacity(0.0);
					};
				};
				
				base.prepForShow = function(e){
					if(!base.showing) {
						// Prepare for a animate in...
						base.jQuerylabel.css({opacity: 0.0}).show();
						
						// Reattach the keydown event
						base.jQueryfield.bind('keydown.infieldlabel',function(e){
							base.hideOnChange(e);
						});
					};
				};

				base.hideOnChange = function(e){
					if(
						(e.keyCode == 16) || // Skip Shift
						(e.keyCode == 9) // Skip Tab
					  ) return; 
					
					if(base.showing){
						base.jQuerylabel.hide();
						base.showing = false;
					};
					
					// Remove keydown event to save on CPU processing
					base.jQueryfield.unbind('keydown.infieldlabel');
				};
		      
				// Run the initialization method
		        base.init();
		    };
			
		    jQuery.InFieldLabels.defaultOptions = {
		        fadeOpacity: 0.5, // Once a field has focus, how transparent should the label be
				fadeDuration: 300 // How long should it take to animate from 1.0 opacity to the fadeOpacity
		    };
			

		    jQuery.fn.inFieldLabels = function(options){
		        return this.each(function(){
					// Find input or textarea based on for= attribute
					// The for attribute on the label must contain the ID
					// of the input or textarea element
					var for_attr = jQuery(this).attr('for');
					if( !for_attr ) return; // Nothing to attach, since the for field wasn't used
					
					
					// Find the referenced input or textarea element
					var jQueryfield = jQuery(
						"input#" + for_attr + "[type='text']," + 
						"input#" + for_attr + "[type='password']," + 
						"textarea#" + for_attr
						);
						
					if( jQueryfield.length == 0) return; // Again, nothing to attach
					
					// Only create object for input[text], input[password], or textarea
		            (new jQuery.InFieldLabels(this, jQueryfield[0], options));
		        });
		    };
			
		})(jQuery);
		</script>
		
		<script type="text/javascript" charset="utf-8">
			jQuery(function(){
				jQuery("label").inFieldLabels(); 
			});
		</script>
</head>
<body class="login">
<div id="wrapper">

 <p align="center"><img class="logo" src="/caaers/images/blue/login-logo.png" /></p>
 <div class="cl"></div>
 <div class="container">
 <div id="forgotPassword"> 
 
	<h1>Forgot your password?</h1> 

    <c:url value="/public/user/resetPassword" var="action"/>
     <form:form id="login" action="${action}" > 
     	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
         <c:if test="${not empty param.login_error}">
             <p class="errors">
                  <img src="../../images/error-yellow.png" style="margin-right:10px">Password is too old. Please reset your password.
             </p>
         </c:if>
         <p> Enter your email address to reset your password.</p>
  		 <fieldset> 
   			<p>
				<label for="email">Email</label><br />
				<input type="text" name="email" value="" id="email"  class="validate-NOTEMPTY required" title="Please provide a valid email address">
			</p>
	 
            <div class="submitButton"><input type="submit" tabindex="4" value="Submit" class="enter" name="login_submit" id="login_submit"></div>
   		 </fieldset>
    <br/>
  		<a href="../login"> <span style="font-size: 15px; border-bottom:none">&larr;</span> Sign in</a>
  </form:form>
    
    
  </div>

 <div class="cl"></div>  
 </div> 
 
</div><!-- wrapper -->    
	
</body>
</html>