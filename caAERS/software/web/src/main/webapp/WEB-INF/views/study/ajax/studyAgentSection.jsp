<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tags:noform>
 <c:forEach items="${indexes}" var="_index" varStatus="i">
   <c:if test="${!command.study.studyAgents[_index].retired}">
 	<study:oneStudyAgent index="${_index}" studyAgent="${command.study.studyAgents[_index]}" />
   </c:if>
 </c:forEach>
</tags:noform>

<%-- 
 BJ : To be removed after verifying the fixes
<tags:noform>
   <study:oneStudyAgent title="..." sectionClass="sa-section"  index="${index}" 
   		readOnly="false"
   		style="display: none"
   		collapsed="false"
   		enableDelete="true"
		removeButtonAction="removeStudyAgent"
   		>
	</study:oneStudyAgent>		
</tags:noform>
--%>