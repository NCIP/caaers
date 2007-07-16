<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="hideErrorDetails" type="java.lang.Boolean" %>
<form:errors path="*">
    <c:if test="${not empty messages}">
        <p class="errors">
            There are problems with your submission.
            Please correct them before proceeding.
        </p>
         <c:if test="${not hideErrorDetails}">
        	<ul class="errors">
           	 <c:forEach items="${messages}" var="msg">
              	  <li>${msg}</li>
             </c:forEach>
        	</ul>
        </c:if>	
    </c:if>
</form:errors>