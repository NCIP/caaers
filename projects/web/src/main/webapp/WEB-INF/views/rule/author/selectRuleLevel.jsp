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
    <title>Select Rule Level</title>

</head>

<body">

    <p id="instructions">
        You are creating Rules. You can create one or more rules at Sponsor, Institution or Study level.   </p>

    <chrome:division title="Select Rule Level">

        <form:form cssClass="standard">

            <tags:errors path="*"/>
            
            <tags:tabFields tab="${tab}"/>


            <div class="row">
                <label><form:radiobutton path="level" value="Sponsor"/>Create Rules at <b>Sponsor</b> Level</label>
            </div>

            <div class="row">
                <label><form:radiobutton path="level" value="Institution"/>Create Rules at <b>Institution</b> Level</label>
            </div>
            
            <div class="row">
                <label><form:radiobutton path="level" value="Study"/>Create Rules at <b>Study</b> Level</label>
            </div>            
            
        </form:form>

		</chrome:division>

</body>
</html>