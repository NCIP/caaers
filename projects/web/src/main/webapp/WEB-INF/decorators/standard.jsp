<%-- This is the standard decorator for all caAERS pages --%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <title>caAERS - <decorator:title/></title>
    <tags:stylesheetLink name="debug"/>
    <tags:stylesheetLink name="common"/>
    <tags:javascriptLink name="prototype"/>
    <tags:javascriptLink name="common"/>
    <style type="text/css" xml:space="preserve">
        #header, #footer {
            border: 0 solid #090;
            background-color: #999;
            color: #AAB;
            text-align: center;
            padding: 2em;
        }
        #header {
            border-bottom-width: 1px;
            margin-bottom: 1em;
        }
        #footer {
            border-top-width: 1px;
            margin-top: 1em;
        }
    </style>
    <decorator:head/>
  </head>
  <body>
    <div id="header">
        COMMON HEADER GOES HERE
    </div>
    <div id="body">
    <decorator:body/>
    </div>
    <div id="footer">
        COMMON FOOTER GOES HERE
    </div>
    <tags:debugInfo/>
  </body>
</html>
