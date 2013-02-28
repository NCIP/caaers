<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par"%>
<tags:noform>

    <par:parIdentifier  title="Subject Identifier ${index + 1}" disableDelete="${index lt 1}"
			sectionClass="${type eq 1 ? 'system-section-row' : 'organization-section-row'}" 
			removeButtonAction="removeIdentifier" index="${index}"  identifier="${command.participant.identifiers[index]}"
			 style="display:none"  />
			
</tags:noform>

