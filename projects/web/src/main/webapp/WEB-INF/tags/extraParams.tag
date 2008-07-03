<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="extraParam" description="There are some allowed type of extra params example phone-number" %>
<c:if test="${extraParam eq 'phone-number'}">
<span class="phone-number">###-###-####</span>
</c:if>
