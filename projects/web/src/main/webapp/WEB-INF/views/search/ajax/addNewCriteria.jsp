<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="search" tagdir="/WEB-INF/tags/search"%>

<search:oneCriteriaRow index="${fn:length(command.criteriaParameters) - 1}" dependentObject="${dependentObject}"/> 