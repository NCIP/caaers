<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title>E-mail Sent</title>
<style type="text/css">

    .box {
        width: 30em;
        margin: 0 auto;
    }

     #main {
          text-align: center;
          text-shadow: 0 2px 3px black;
      background: none;-moz-box-shadow: none; -webkit-box-shadow: none; box-shadow: none;
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
        background: #02307f url(<c:url value="/images/blue/top_texture_bg.png" /> ) top center no-repeat;
        color: #fff;
        font-family: "Lucida Sans Unicode", sans-serif;
    }

    #header {
        visibility: hidden;
    }

    #all {
        background: none;
        width: 700px;
    }

    #build-name {
        color: #2e3257
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
        top: -175px;
        left: 170px;
    }

    .errors {
        color: #FFCC00;
    }

    #main {
        margin-top: 90px;
        margin-bottom: 40px;
        text-align: center;
    }

    .wide-header {
        display: none;
    }

    #build-name {
        color: #8db0eb;
        font-weight: normal;
        padding: 15px 0px;
        background: url(<c:url value="/images/footer_divider.png" />) no-repeat top center;
        bottom: -175px;
        text-shadow: 0 1px 1px #000;
        font-size: 10px;
        margin-top: 0px;
        text-align: center;
    }

</style>
    <link href="../../images/caaers.ico" rel="icon"/>
</head>
<body>

<a href="<c:url value="/public/login" />"><img src="<c:url value="/images/blue/login-logo.png" />" id="logo" alt="Cancer Adverse Event Reporting System"></a>
An email containing instructions for reseting the password has been sent to the specified user.
</body>
</html>