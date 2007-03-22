<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="authorRule"/>

    <script type="text/javascript">
			function nextTab(level) {
				    $('flowredirect-target').name = "_target" + "1";
				    $('flowredirect').submit();
			}

	</script>

  <title>Select Rule Level</title>

</head>

<body>

    <p id="instructions">
        You are creating Rules. You can create one or more rules at Sponsor, Institution or Study level.   </p>

    <chrome:division title="Select Rule Level">

        <form:form cssClass="standard">

            <tags:errors path="*"/>


            <div class="row">

								<a href="javascript:nextTab(1);"> Click here to create Rules at <b>Sponsor</b> Level </a>

            </div>

            <div class="row">

								<a href="javascript:nextTab(1);"> Click here to create Rules at <b>Institution</b> Level </a>

            </div>
            
            <div class="row">

								<a href="javascript:nextTab(1);"> Click here to create Rules at <b>Study Level</b> </a>

            </div>            

            <c:if test="${empty tab}">
						    <tags:tabControls tabNumber="${0}" isLast="${false}"/>
            </c:if>
            
        </form:form>

		</chrome:division>

</body>
</html>