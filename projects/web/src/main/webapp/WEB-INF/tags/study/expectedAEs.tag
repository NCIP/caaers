<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study" %>

<style>
    #termsTable .even {
    }

    #termsTable .odd {
        /*background-color: #e8e8ff;*/
    }
</style>

<chrome:division title="" collapsable="true" id="studyTermsID">
    <c:if test="${not empty command.study.aeTerminology.meddraVersion}">
        <c:set var="terms" value="${command.study.expectedAEMeddraLowLevelTerms}" />
		<p><tags:instructions code="study_expectedaes_meddra"/></p>
    </c:if>

    <c:if test="${not empty command.study.aeTerminology.ctcVersion}">
        <c:set var="terms" value="${command.study.expectedAECtcTerms}" />
		<p><tags:instructions code="study_expectedaes_ctc"/></p>
    </c:if>
               <tags:aeTermQuery
                       isMeddra="${not empty command.study.aeTerminology.meddraVersion}"
                       noBackground="true"
                       callbackFunctionName="rpCreator.addStudyTerm"
                       ignoreOtherSpecify="false"
                       isAjaxable="true"
                       version="${not empty command.study.aeTerminology.meddraVersion ? command.study.aeTerminology.meddraVersion.id : command.study.aeTerminology.ctcVersion.id}"
                       title="">
               </tags:aeTermQuery>

        <tags:table bgColor="#AAAAAA" contentID="termsDiv">

               <table id="termsTable" width="100%" border="0" cellspacing="1" cellpadding="3">
                   <tr bgcolor="#E4E4E4">
                       <th scope="col" align="left" colspan="2"><b>Term</b></th>
                   </tr>
                   <c:forEach items="${terms}" var="studyTerm" varStatus="status">
                       <tr class="ae-section ${status.index % 2 gt 0 ? 'odd' : 'even'}" id="STUDY_TERM_-${status.index}" bgcolor="white">
                           <study:oneExpectedAE isOtherSpecify="${studyTerm.otherRequired}" index="${status.index}" studyTerm="${studyTerm}"/>
                           <td style="text-align:center;"><img src="<c:url value="/images/checkno.gif" />" id="DELETE_<c:out value="${status.index}" />" onclick="removeTerm(${status.index})" style="cursor:pointer;""></td>
                       </tr>
                   </c:forEach>
                <tr id="observedBlankRow" style="display:none;"><td></td></tr>
               </table>

        </tags:table>

    </chrome:division>
