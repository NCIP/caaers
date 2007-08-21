<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<c:if test="${not empty studySummary }">
<div id="study-summary-pane" >
 <chrome:box title="Summary">
 <table width="100%">
 <tr>
  <c:forEach items="${studySummary}" var="summaryEntry">
  <td>
   <div class="row">
    <div class="label">${summaryEntry.key}</div>
    <div class="value">${empty summaryEntry.value ? '<em class="none">None</em>' : summaryEntry.value}</div>
   </div>
  </td>        
  </c:forEach>
 </tr>
 </table>
 </chrome:box>
</div>
</c:if>