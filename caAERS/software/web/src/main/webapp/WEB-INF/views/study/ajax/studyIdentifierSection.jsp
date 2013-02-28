<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="study" tagdir="/WEB-INF/tags/study"%>
<tags:noform>
<study:oneStudyChildRow
        cssClass="${type eq 1 ? 'system-section-row' : 'organization-section-row'}"
        index="${index}"
        style="display: none"
        exclusions="${type eq 1 ? 'Organization' : 'System Name'}"
        identifiers="true"
        idSuffix="${listEditorIndex}"/>
</tags:noform>
