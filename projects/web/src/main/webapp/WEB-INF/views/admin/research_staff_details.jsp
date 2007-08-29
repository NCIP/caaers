<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
<title>${tab.longTitle}</title>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}"  formName="researchStaffForm">

    
		 <jsp:attribute name="singleFields">
            <div>
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
		</div>
		       <c:if test="${(empty command.id) or ( command.id le 0) }"><input type="hidden" name="_finish" value="true"/></c:if>
		
		
    				<c:forEach  items="${fieldGroups.researchStaff.fields}" var="field">
                    <tags:renderRow field="${field}"/>
                	</c:forEach>
             </jsp:attribute>
    
    
</tags:tabForm>

 </body>
</html>
