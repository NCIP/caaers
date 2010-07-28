<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="_today" class="java.util.Date" />
<c:redirect url="/pages/task?rand=${_today.time}"/>