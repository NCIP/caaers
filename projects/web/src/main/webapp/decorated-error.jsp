<!-- BEGIN decorated-error.jsp -->
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@page language="java" isErrorPage="true"%>
<%@ page isErrorPage="true" %>
<%@ page language="java" %>
<%
    Object statusCode = request.getAttribute("javax.servlet.error.status_code");
    Object exceptionType = request.getAttribute("javax.servlet.error.exception_type");
    Object message = request.getAttribute("javax.servlet.error.message");
%>
<%@page import="java.io.PrintStream"%>
<%@page import="java.io.PrintWriter"%>
<page:applyDecorator  name="standard">
  <html>
  <head>
  <style>
div.row div.label {
	font-size:36px;
	margin-left:50px;
	text-align:left;
}
div.row div.error div.value {
	position: absolute;
	left: 152px;
	top: 112px;
}
div.row div.value {
	position: absolute;
	left: 152px;
	top: 148px;
}
table.errortd {
	border-width: 1px 1px 1px 1px;
	border-spacing: 0px;
	border-style: solid solid solid solid;
	border-color: gray gray gray gray;
	border-collapse: separate;
	background-color: white;
	margin-bottom:30px;
	margin-top:15px;
}
table.errortd th {
	border-width: 1px 1px 1px 1px;
	padding: 1px 1px 1px 1px;
	border-style: solid solid solid solid;
	border-color: gray gray gray gray;
	background-color: white;
	-moz-border-radius: 0px 0px 0px 0px;
}
table.errortd td {
	border-width: 1px 1px 1px 1px;
	padding: 1px 1px 1px 1px;
	border-style: solid solid solid solid;
	border-color: gray gray gray gray;
	background-color: white;
	-moz-border-radius: 0px 0px 0px 0px;
}
</style>
  <tags:includeScriptaculous />
  <title>Error</title>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <script type="text/javascript" language="JavaScript">
        function PanelCombo(element) {
            panelDiv = $(element+"-interior");
            imageId= element+'-image';
            imageSource=document.getElementById(imageId).src;
            if (panelDiv.style.display == 'none') {
                new Effect.OpenUp(panelDiv, arguments[1] || {});
                document.getElementById(imageId).src=imageSource.replace('minimize','maximize');
            } else {
                new Effect.CloseDown(panelDiv, arguments[1] || {});
                document.getElementById(imageId).src=imageSource.replace('maximize','minimize');
            }
        }
        Effect.OpenUp = function(element) {
            element = $(element);
            new Effect.BlindDown(element, arguments[1] || {});
        }

        Effect.CloseDown = function(element) {
            element = $(element);
            new Effect.BlindUp(element, arguments[1] || {});
        }

        Effect.Combo = function(element) {
            element = $(element);
            if (element.style.display == 'none') {
                new Effect.OpenUp(element, arguments[1] || {});
            } else {
                new Effect.CloseDown(element, arguments[1] || {});
            }
        }
    </script>
  </head>
  <body>
  <img src="/caaers/images/blue/error.png" style="margin-left:100px; float:left;">
  <div class="row">
  <div class="error">
    <div class="label"> ERROR </div>
    <div class="value"> The system encountered an error. Please contact your system administrator. </div>
  </div>
  <div class="row">
    <div class="value"> <a href="<c:url value="/"/>">Return Home</a> </div>
  </div>
  <!-- header -->
  <div class="header" style="margin-top:100px;">
    <div class="background-L">
      <div class="background-R">
        <table width="100%">
          <tr>
            <td style="border-bottom:solid 1px #ccc"><h2 style="float:left">Detailed Error</h2>
              <a href="javascript:PanelCombo('error');"><img id="error-image" src="/caaers/images/chrome/minimize.gif" border="0" align="right"/></a> </td>
          </tr>
        </table>
      </div>
    </div>
    <!-- end header -->
    <!-- end header -->
    <!-- inner border -->
    <div id="error-interior" class="interior" style="display:none;">
      <div class="content">
        <TABLE class="errortd" WIDTH="50%">
          <TR>
            <TD WIDTH="20%" bord><B>Status Code</B></TD>
            <TD WIDTH="80%"><%= statusCode %></TD>
          </TR>
          <TR>
            <TD WIDTH="20%"><B>Exception Type</B></TD>
            <TD WIDTH="80%"><%= exceptionType %></TD>
          </TR>
          <TR>
            <TD WIDTH="20%"><B>Message</B></TD>
            <TD WIDTH="80%"><%= message %></TD>
          </TR>
        </TABLE>
        <b> Header List: </b>
        <table class="errortd" width="100%">
          <tr>
            <td>Name</td>
            <td>Value</td>
          </tr>
          <%
                    String name  = "";
                    String value = "";

                    java.util.Enumeration headers = request.getHeaderNames();
                    while(headers.hasMoreElements())
                    {
                        name  = (String) headers.nextElement();
                        value = request.getHeader(name);
                %>
          <tr>
            <td><%=name%></td>
            <td><%=value%></td>
          </tr>
          <%
                    }
                %>
        </table>
        <b>Attribute List:</b>
        <table class="errortd" width="100%">
          <%
                    java.util.Enumeration attributes = request.getAttributeNames();
                    while(attributes.hasMoreElements())
                    {
                        name  = (String) attributes.nextElement();

                        if (request.getAttribute(name) == null)
                        {
                            value = "null";
                        }
                        else
                        {
                            value = request.getAttribute(name).toString();
                        }
                %>
          <tr>
            <td><%=name%></td>
            <td><%=value%></td>
          </tr>
          <%
                    }
                %>
          <tr>
            <td colspan="2"><b>StackTrace :</b><br />
              <pre>
                  	   <%
                  	   	exception.printStackTrace(new PrintWriter(out));
                  	   %>
                      </pre>
            </td>
          </tr>
        </table>
      </div>
      <!-- end inner border -->
    </div>
  </div>
  </body>
  </html>
</page:applyDecorator>
<!-- END decorated-error.jsp -->