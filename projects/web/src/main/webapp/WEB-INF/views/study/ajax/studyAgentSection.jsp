<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<tags:noform>
   <study:aStudyChild title="Study Agent ${index + 1}" sectionClass="sa-section"
			 removeButtonAction="removeStudyAgent" enableDelete="true" index="${index}" style="display: none">
			 <jsp:attribute name="localButtons">
			 	<input type="button" value="Add IND #" onClick="insertINDRow(${index})" />
			 </jsp:attribute>
   </study:aStudyChild>
</tags:noform>