<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
    <title>Change Password</title>
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

          h1 {
              visibility: hidden;
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
      </style>
</head>
<body>
<a href="<c:url value="/public/login" />"><img src="<c:url value="/images/blue/login-logo.png" />" id="logo"></a>
<c:if test="${updated}">
    <c:url value="/public/login" var="login"/>
    <h2>Password changed successfully.</h2><br/>You can now <a href="${login}"><span style="color: #99FFCC;text-decoration: underline;text-transform: uppercase">login</span></a>.
</c:if>

<c:if test="${not updated}">
    <c:url value="/public/user/changePassword" var="action"/>
    <form:form action="${action}">
    	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
        <h2>Please enter your new password</h2>

        <div style="text-align: LEFT; padding-left: 177px;">
        Your password must:<br>
        <ul style="width: 400px;">
            <c:if test="${command.passwordPolicy.passwordCreationPolicy.combinationPolicy.upperCaseAlphabetRequired}"><li>contain at least one upper case letter;</c:if>
            <c:if test="${command.passwordPolicy.passwordCreationPolicy.combinationPolicy.lowerCaseAlphabetRequired}"><li>contain at least one lower case letter;</c:if>
            <c:if test="${command.passwordPolicy.passwordCreationPolicy.combinationPolicy.nonAlphaNumericRequired}"><li>contain at least one non alphanumeric character;</c:if>
            <li>have a length of minimum ${command.passwordPolicy.passwordCreationPolicy.minPasswordLength} characters;
            <li>not contain a substring of your username  longer than ${command.passwordPolicy.passwordCreationPolicy.combinationPolicy.maxSubstringLength} characters;
        </ul>
        </div>
        <br/><br/>
        <tags:hasErrorsMessage/>

        <ui:row path="userName">
            <jsp:attribute name="label">
                <ui:label path="userName" text="Username" required="true"/>
            </jsp:attribute>
            <jsp:attribute name="value">
                <ui:text path="userName" required="true" title="Username"/>
            </jsp:attribute>
        </ui:row>

        <ui:row path="passwordNew">
            <jsp:attribute name="label">
                <ui:label path="passwordNew" text="New Password" required="true"/>
            </jsp:attribute>
            <jsp:attribute name="value">
                <form:password id="passwordNew" path="passwordNew" cssClass="validate-NOTEMPTY required" title="New Password"/>
            </jsp:attribute>
        </ui:row>

        <ui:row path="passwordConfirm">
            <jsp:attribute name="label">
                <ui:label path="passwordConfirm" text="Confirm Password" required="true"/>
            </jsp:attribute>
            <jsp:attribute name="value">
                <form:password id="passwordConfirm" path="passwordConfirm" cssClass="validate-NOTEMPTY required" title="Confirm Password"/>
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