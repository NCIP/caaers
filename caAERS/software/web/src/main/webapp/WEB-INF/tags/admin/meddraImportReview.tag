<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<chrome:division id="meddra-id">

		<tags:instructions code='LBL_Meddra_Review'/> 

	 <table id="test" width="70%" class="tablecontent">
    	<tr>
    		<th scope="col" align="left"><b>Concept</b> </th>
    		<th scope="col" align="left"><b>File name</b> </th>
    	</tr>
    	<tr class="results">
    		<td align="left">System organ classes
    		</td>
    		<td align="left">${command.socFile.originalFilename }
    		</td>
    	</tr>
    	<tr class="results">
    		<td align="left">High level group terms
    		</td>
    		<td align="left">${command.hlgtFile.originalFilename }
    		</td>
    	</tr>
    	<tr class="results">
    		<td align="left">High level terms
    		</td>
    		<td align="left">${command.hltFile.originalFilename }
    		</td>
    	</tr>
    	<tr class="results">
    		<td align="left">Preferred terms
    		</td>
    		<td align="left">${command.ptFile.originalFilename }
    		</td>
    	</tr>
    	<tr class="results">
    		<td align="left">Low level terms
    		</td>
    		<td align="left">${command.lltFile.originalFilename }
    		</td>
    	</tr>
    	<tr class="results">
    		<td align="left">SOC - HLGT relationship
    		</td>
    		<td align="left">${command.socHlgtFile.originalFilename }
    		</td>
    	</tr>
    	<tr class="results">
    		<td align="left">HLGT - HLT relationship
    		</td>
    		<td align="left">${command.hlgtHltFile.originalFilename }
    		</td>
    	</tr>
    	<tr class="results">
    		<td align="left">HLT - PT relationship
    		</td>
    		<td align="left">${command.hltPtFile.originalFilename }
    		</td>
    	</tr>
	</table>
</chrome:division>