<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="displayOnly" required="true" type="java.lang.Boolean" %>
<%@attribute name="eachRow" required="true" type="java.util.LinkedList"%>
<c:set var="terminologyVersionId" value="${empty command.otherMeddra.id ? 0 : command.otherMeddra.id}" />
<c:set var="initialDisplayValue" value="${eachRow[2] == null ? 'Begin typing here...' : eachRow[2].fullName}" />

<c:if test="${eachRow[2].class.name eq 'gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm'}">
    <c:set var="initialDisplayValue" value="${eachRow[2].meddraTerm}" />
</c:if>

                <tr id="tr-${eachRow[0]}" class="data" align="center">
    				<td align="left" style="padding-left:1em"><label  id='name-${eachRow[0]}'>${eachRow[1]}</label>
    				<input name="eachRowTermID" type="hidden" class="eachRowTermID" value="${eachRow[0]}" />
    				<c:if test="${eachRow[3]}">
 		   				<br><tags:requiredIndicator/>Other(MedDRA)
    					<tags:autocompleter displayName="abcd" propertyName="otherMeddra-${eachRow[0]}" size="30" initialDisplayValue="${initialDisplayValue}" initialValue="${eachRow[2] == null ? '' : eachRow[2].id}"/>
    					<script>
	    					AE.createStandardAutocompleter('otherMeddra-${eachRow[0]}',
								function(autocompleter, text) {
									createAE.matchLowLevelTermsByCode(${terminologyVersionId}, text, function(values) {
														autocompleter.setChoices(values)
												})
								},
								function(lowLevelTerm) { return lowLevelTerm.meddraTerm });
    					</script>
    				</c:if>
    				</td>
    			   <c:forEach varStatus="status" var="eachCheckBox" items="${eachRow}" begin="4" >
                     <td class="col-epoch-${status.index-4}"><input  ${displayOnly?'disabled':''} class="ck${status.index-4} ck-${eachRow[0]}" id="ck${status.index-4}-${eachRow[0]}" name="epoch[${status.index-4}]" value="${eachRow[0]}" type="checkbox" ${(eachRow[status.index] == true)?'checked':''}/></td>    			   
    			   </c:forEach>	
    			 <c:if test="${!displayOnly}">
        		  <td id="deletecol-${eachRow[0]}" class='deletecol' ><input  id="button-${eachRow[0]}" class="eachRowDeleteButton" type="button" value="Delete" /></td>
        		 </c:if>
    			</tr>
    		