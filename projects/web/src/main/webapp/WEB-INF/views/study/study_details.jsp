<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
<style type="text/css">
        .label { width: 12em; text-align: right; padding: 4px; }
    </style>
</head>
<body>
<!-- MAIN BODY STARTS HERE -->
<chrome:body title="${flow.name}: ${tab.longTitle}">
	   <form:form method="post" cssClass="standard">
	   		<tags:errors path="*"/>
			<tags:tabFields tab="${tab}"/>
			<div>
			<chrome:division id="study-details">
			 <table  width="75" border="0" cellspacing="0" cellpadding="0">
			 <tr>
			 <td>
				 <table  width="40%" border="0" cellspacing="3" cellpadding="0">
				  <tr>
				      <td class="label"> <form:label path="shortTitle">Short Title:</form:label> </td>
				      <td> <form:textarea path="shortTitle" rows="1" cols="50"/> </td>        			  
				  </tr>			                        

				  <tr>
				      <td class="label"> <form:label path="longTitle"><span class="red">*</span>Long Title:</form:label> </td>
				      <td> <form:textarea path="longTitle" rows="3" cols="50"/> </td>        			  
				  </tr>

				  <tr>
				      <td class="label"> <form:label path="precis">Precis:</form:label> </td>
				      <td> <form:textarea path="precis" rows="3" cols="50"/> </td>        			  
				  </tr>

				  <tr>
				      <td class="label"> <form:label path="description">Description:</form:label> </td>
				      <td> <form:textarea path="description" rows="3" cols="50"/> </td>        			  
				  </tr>

                 </table>
                </td>
				<td valign="top">
				<table width="60%" border="0" cellspacing="10" cellpadding="3">
				  
					<tr>				 
        			  <td class="label"><form:label path="phaseCode"><span class="red">*</span><em></em>Phase:</form:label></td>
                    
					  <td>	<form:select path="phaseCode">
							<form:options items="${phaseCodeRefData}" itemLabel="desc"
									itemValue="code"/>
						</form:select>	</td>					
					</tr>
				
					<tr>				 
        			  <td class="label"><form:label path="primarySponsorCode"><span class="red">*</span><em></em>Sponsor:</form:label></td>
					 <td>
						<form:select path="primarySponsorCode">
							<form:options items="${sponsorCodeRefData}" itemLabel="desc"
									itemValue="code"/>
						</form:select>												
					</td>
					</tr>									

				 </table>
                 </td>
				 </tr>
                 
				</table>
					</chrome:division>			
				</div>
        </form:form>
        </chrome:body>
<!-- MAIN BODY ENDS HERE -->
</body>
</html>
