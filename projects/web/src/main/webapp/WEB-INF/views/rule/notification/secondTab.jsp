<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <tags:stylesheetLink name="ae"/>
    <title>Not implemented</title>
		<style>
		#message {width:80%; height:200px}
		#subject,#to,#from {width : 81%}
		</style>
		
		<script type="text/javascript" src="/caaers/js/tiny_mce/tiny_mce_src.js"></script>
		<script language="javascript" type="text/javascript">
				tinyMCE.baseURL="http://localhost:8080/caaers/js/tiny_mce";
				tinyMCE.srcMode="_src";
				tinyMCE.init({
					mode : "textareas",
					theme : "advanced",
					theme_advanced_buttons1 : "bold,italic,underline,separator,strikethrough,justifyleft,justifycenter,justifyright, justifyfull,bullist,numlist,undo,redo,link,unlink",
					theme_advanced_buttons2 : "",
					theme_advanced_buttons3 : "",
					theme_advanced_toolbar_location : "top",
					theme_advanced_toolbar_align : "left",
					extended_valid_elements : "a[name|href|target|title|onclick],img[class|src|border=0|alt|title|hspace|vspace|width|height|align|onmouseover|onmouseout|name],hr[class|width|size|noshade],font[face|size|color|style],span[class|align|style]"
				});				
				

		</script>

</head>
<body>
    <p>Feature in progress.... Based on the Type of Notification selected this will allow the user to enter details of notification....</p>
        
    <chrome:division title="Create Trigger">

        <form:form cssClass="standard">


            <tags:errors path="*"/>
    
            <tags:tabFields tab="${tab}"/>

            <div id="ruleset-fields">
                <c:forEach items="${fieldGroups.ruleset.fields}" var="field">
                    <tags:row field="${field}"/>
                </c:forEach>
            </div>

            <c:if test="${empty tab}">
						    <tags:tabControls tabNumber="${0}" isLast="${false}"/>
            </c:if>
            
        </form:form>

		</chrome:division>
    
</body>


</html>