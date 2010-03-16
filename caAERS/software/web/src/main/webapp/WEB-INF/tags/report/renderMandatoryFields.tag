<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="key" required="true" rtexprvalue="true" %>
<%@attribute name="tabular" type="java.lang.Boolean" %>
<%@attribute name="singleRow" type="java.lang.Boolean" %>
<%@attribute name="startIndex" type="java.lang.Integer"%>
<%@attribute name="endIndex" type="java.lang.Integer"%>
<%@attribute name="readOnly" type="java.lang.Boolean"  %> 
<%@attribute name="heading" %>

<c:if test="${not readOnly}">
    <c:set var="len">${fn:length(fieldGroups[key].fields)}</c:set>
    <c:set var="st">${empty startIndex ? 0 : startIndex }</c:set>
    <c:set var="en">${empty endIndex ? (len-1) : endIndex}</c:set>


<c:if test="${not tabular}">
	<c:forEach var="field" begin="${st}" end="${en}" items="${fieldGroups[key].fields}" varStatus="i">
        <c:set var="_curCode">
            <jsp:attribute name="value"><caaers:value path="${field.attributes.subfields[0].propertyName}.code" /></jsp:attribute>
        </c:set>
        <ui:row path="${field.attributes.subfields[0].propertyName}">
            <jsp:attribute name="label">
               <tags:renderLabel field="${field.attributes.subfields[0]}" />
            </jsp:attribute>
            <jsp:attribute name="value">
                <a name="${field.attributes.subfields[0].propertyName}"></a>
                <ui:select path="${field.attributes.subfields[0].propertyName}"
                 options="${field.attributes.subfields[0].attributes.options}"
                 title="${field.attributes.subfields[0].displayName}">
                    <jsp:attribute name="embededJS">
                         Event.observe('${field.attributes.subfields[0].propertyName}', 'change', function(evt){
                            selectFieldChanged('${field.attributes.subfields[0].propertyName}','${field.attributes.subfields[1].propertyName}','${field.attributes.subfields[2].propertyName}');
                        })
                    </jsp:attribute>
                </ui:select>
                <tags:renderInputs field="${field.attributes.subfields[1]}" />
                <tags:renderInputs field="${field.attributes.subfields[2]}" />
                <span id="${field.attributes.subfields[0].propertyName}-adiv" style="${_curCode eq 2 ? '' : 'display:none;'}">
                <a id="${field.attributes.subfields[0].propertyName}-a" href="#${field.attributes.subfields[0].propertyName}" onclick="showRulePicker('${field.attributes.subfields[0].propertyName}', '${field.attributes.subfields[1].propertyName}', '${field.attributes.subfields[2].propertyName}')">Show rules</a>
                </span>
            </jsp:attribute>
        </ui:row>
	</c:forEach>
</c:if>
<c:if test="${tabular}">
<c:if test="${singleRow}">
 <table>
	<tr>
	<td><div class="row"><div class="label">${heading}</div></div></td>
	<c:forEach var="field" items="${fieldGroups[key].fields}">

	<td><tags:renderLabel field="${field.attributes.subfields[0]}" /></td>
	<td>
        <c:set var="_curCode" >
           <jsp:attribute name="value">
               <caaers:value path="${field.attributes.subfields[0].propertyName}.code" />
           </jsp:attribute>
        </c:set>
        <a name="${field.attributes.subfields[0].propertyName}"></a>
        <ui:select path="${field.attributes.subfields[0].propertyName}"
             options="${field.attributes.subfields[0].attributes.options}"
             title="${field.attributes.subfields[0].displayName}">
             <jsp:attribute name="embededJS">
                 Event.observe('${field.attributes.subfields[0].propertyName}', 'change', function(evt){
                    selectFieldChanged('${field.attributes.subfields[0].propertyName}','${field.attributes.subfields[1].propertyName}','${field.attributes.subfields[2].propertyName}');
                })
             </jsp:attribute>
        </ui:select>
        <tags:renderInputs field="${field.attributes.subfields[1]}" />
        <tags:renderInputs field="${field.attributes.subfields[2]}" />
        <span id="${field.attributes.subfields[0].propertyName}-adiv" style="${_curCode eq 2 ? '' : 'display:none;'}">
        <a id="${field.attributes.subfields[0].propertyName}-a" href="#${field.attributes.subfields[0].propertyName}" onclick="showRulePicker('${field.attributes.subfields[0].propertyName}', '${field.attributes.subfields[1].propertyName}', '${field.attributes.subfields[2].propertyName}')">Show rules</a>
        </span>
    </td>

    </c:forEach>
	</tr>
 </table>
</c:if>
<c:if test="${not singleRow}">
<ui:row path="${fieldGroups[key].fields[0].propertyName}">
	<jsp:attribute name="label">${heading}</jsp:attribute>
	<jsp:attribute name="value">
		<table>
	  	<c:forEach var="field" items="${fieldGroups[key].fields}">
		<tr>
		  <td><tags:renderLabel field="${field.attributes.subfields[0]}" /></td>
		  <td>
              <c:set var="_curCode" >
                <jsp:attribute name="value">
                    <caaers:value path="${field.attributes.subfields[0].propertyName}.code" />
                </jsp:attribute>
              </c:set>
              <a name="${field.attributes.subfields[0].propertyName}"></a>
              <ui:select path="${field.attributes.subfields[0].propertyName}"
                   options="${field.attributes.subfields[0].attributes.options}"
                   title="${field.attributes.subfields[0].displayName}">
                   <jsp:attribute name="embededJS">
                        Event.observe('${field.attributes.subfields[0].propertyName}', 'change', function(evt){
                            selectFieldChanged('${field.attributes.subfields[0].propertyName}','${field.attributes.subfields[1].propertyName}','${field.attributes.subfields[2].propertyName}');
                        })
                   </jsp:attribute>
              </ui:select>
              <tags:renderInputs field="${field.attributes.subfields[1]}" />
              <tags:renderInputs field="${field.attributes.subfields[2]}" />
              <span id="${field.attributes.subfields[0].propertyName}-adiv" style="${_curCode eq 2 ? '' : 'display:none;'}">
                <a id="${field.attributes.subfields[0].propertyName}-a" href="#${field.attributes.subfields[0].propertyName}" onclick="showRulePicker('${field.attributes.subfields[0].propertyName}', '${field.attributes.subfields[1].propertyName}', '${field.attributes.subfields[2].propertyName}')">Show rules</a>
              </span>
          </td>
		</tr>
	  </c:forEach>
 	</table>
	</jsp:attribute>
</ui:row>

</c:if>
</c:if>
</c:if>
<c:if test="${readOnly}">
  <c:set var="fg" value="${FIELDS.mandatoryFields}" />
  <c:set var="sg" value="${fg[key]}" />
  <c:set var="flds" value="${sg.fields}" />

  <c:set var="len">${fn:length(flds)}</c:set>
  <c:set var="st">${empty startIndex ? 0 : startIndex }</c:set>
  <c:set var="en">${empty endIndex ? (len-1) : endIndex}</c:set>

  <c:forEach var="field" begin="${st}" end="${en}" items="${flds}" varStatus="i">
        <c:set var="_reqness">
            <jsp:attribute name="value"><caaers:value path="${field.attributes.subfields[0].propertyName}.name" /></jsp:attribute>
        </c:set>
        <ui:row path="${field.attributes.subfields[0].propertyName}" cssClass="mandatoryField">
            <jsp:attribute name="label">
               <tags:renderLabel field="${field.attributes.subfields[0]}" />
            </jsp:attribute>
            <jsp:attribute name="value">
                <caaers:value path="${field.attributes.subfields[0].propertyName}.displayName" />
            </jsp:attribute>
        </ui:row>
	</c:forEach>
</c:if>