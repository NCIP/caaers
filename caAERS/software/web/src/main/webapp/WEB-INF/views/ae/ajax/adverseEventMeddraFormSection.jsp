<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae"%>
<tags:noform>
    <ae:oneAdverseEventMeddra index="${param.index}" 
        adverseEvent="${command.aeReport.adverseEvents[param.index]}"
    style="display: none" collapsed="false"/>
</tags:noform>

