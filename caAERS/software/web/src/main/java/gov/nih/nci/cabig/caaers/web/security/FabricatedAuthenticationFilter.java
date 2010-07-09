package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.CurrentEntityHolder;
import gov.nih.nci.cabig.caaers.security.OriginalAuthenticationHolder;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.HttpSessionContextIntegrationFilter;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.context.SecurityContextImpl;
import org.acegisecurity.providers.AbstractAuthenticationToken;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections15.Predicate;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.GenericValidator;

public final class FabricatedAuthenticationFilter implements Filter {

	private static final String STUDY = "gov.nih.nci.cabig.caaers.domain.Study";
	private static final String INVESTIGATOR = "gov.nih.nci.cabig.caaers.domain.Investigator";
	public static final String RESEARCH_STAFF = "gov.nih.nci.cabig.caaers.ResearchStaff";
	private static final Log log = LogFactory
			.getLog(FabricatedAuthenticationFilter.class);
	private static final String FILTER_APPLIED = "gov.nih.nci.cabig.caaers.web.security.FabricatedAuthenticationFilter.FILTER_APPLIED";

	public static final String CAAERS_ORIGINAL_AUTHENTICATTION = "FabricatedAuthenticationFilter.CAAERS_ORIGINAL_AUTHENTICATTION";
	private static final String COLON = ":";

	private CaaersSecurityFacade securityFacade;

	private ResearchStaffRepository researchStaffRepository;

	private InvestigatorRepository investigatorRepository;

	private StudyRepository studyRepository;

	private Map<String, String> filterByURLAndEntityMap = new HashMap<String, String>();

	private Map<String, String> filterByURLAndRoleListMap = new HashMap<String, String>();

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest)) {
			throw new ServletException("Can only process HttpServletRequest");
		}
		if (!(response instanceof HttpServletResponse)) {
			throw new ServletException("Can only process HttpServletResponse");
		}

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		final SecurityContext contextBeforeExec = SecurityContextHolder
				.getContext();
		Authentication authBeforeExec = contextBeforeExec.getAuthentication();

		OriginalAuthenticationHolder.setAuthentication(authBeforeExec);

		try {
			if (request.getAttribute(FILTER_APPLIED) == null) {
				doProcessing(httpRequest, httpResponse, chain);
				request.setAttribute(FILTER_APPLIED, true);
			}
			prepareRolesCollections(httpRequest);
			chain.doFilter(httpRequest, httpResponse);
		} finally {
			request.removeAttribute(FILTER_APPLIED);
			SecurityContextHolder.setContext(contextBeforeExec);
			SecurityContextHolder.getContext()
					.setAuthentication(authBeforeExec);
			httpRequest
					.getSession()
					.setAttribute(
							HttpSessionContextIntegrationFilter.ACEGI_SECURITY_CONTEXT_KEY,
							SecurityContextHolder.getContext());
			OriginalAuthenticationHolder.setAuthentication(null);
			CurrentEntityHolder.setEntity(null);
		}
		return;
	}

	/**
	 * @param httpRequest
	 */
	private void prepareRolesCollections(HttpServletRequest httpRequest) {
		// START Roles
		Map<String, String> roles = new HashMap<String, String>();
		for (UserGroupType r : UserGroupType.values()) {
			roles.put(r.getCsmName(), r.getDisplayName());
		}

		List ol = new ArrayList();
		List cl = new ArrayList();

		Authentication oa = SecurityUtils.getOriginalAuthentication();
		if (oa != null && oa.getAuthorities() != null
				&& oa.getAuthorities().length > 0) {
			for (GrantedAuthority ga : oa.getAuthorities()) {
				ol.add(roles.get(ga.getAuthority()));
			}
		}

		Authentication ca = SecurityUtils.getAuthentication();
		if (ca != null && ca.getAuthorities() != null
				&& ca.getAuthorities().length > 0) {
			for (GrantedAuthority ga : ca.getAuthorities()) {
				cl.add(roles.get(ga.getAuthority()));
			}
		}

		ol = (List) CollectionUtils.subtract(ol, cl);
		httpRequest.setAttribute("cl", cl);
		httpRequest.setAttribute("ol", ol);

		// END Roles
	}

	/**
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 * @throws ClassNotFoundException
	 */
	private void doProcessing(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		Authentication authentication = SecurityUtils.getAuthentication();
		if (authentication != null) {
			GrantedAuthority[] authorities = authentication.getAuthorities();
			GrantedAuthority[] adjustedAuthorities = filterAuthorities(
					authorities, request);
			// do not set fabricated authentication if authorities are the same.
			if (!CollectionUtils.disjunction(Arrays.asList(authorities),
					Arrays.asList(adjustedAuthorities)).isEmpty()) {
				OriginalAuthenticationHolder.setAuthentication(authentication);
				FabricatedAuthentication fabricatedAuthentication = new FabricatedAuthentication(
						authentication, adjustedAuthorities);
				SecurityContextHolder.setContext(new SecurityContextImpl());
				SecurityContextHolder.getContext().setAuthentication(
						fabricatedAuthentication);
			}
		}
	}

	/**
	 * @param authorities
	 * @param request
	 * @return
	 * @throws ClassNotFoundException
	 */
	private GrantedAuthority[] filterAuthorities(
			GrantedAuthority[] authorities, HttpServletRequest request) {

		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		if (authorities != null) {
			list.addAll(Arrays.asList(authorities));
			URLToEntityIdMapEntry entry = getURLToEntityIdEntryFromRequest(request);
			if (entry != null) {
				// protection element info found. Possibly need to restrict the
				// set of granted authorities.
				if (RESEARCH_STAFF.equalsIgnoreCase(entry.getClassName())) {
					int staffId = entry.getObjectId();
					filterAuthoritiesByResearchStaff(list, staffId);
				} else if (INVESTIGATOR.equalsIgnoreCase(entry.getClassName())) {
					int investigatorId = entry.getObjectId();
					filterAuthoritiesByInvestigator(list, investigatorId);
				} else if (STUDY.equalsIgnoreCase(entry.getClassName())) {
					int studyId = entry.getObjectId();
					filterAuthoritiesByStudy(list, studyId);
				}
			} else {
				final URLToRoleListMapEntry rolesEntry = getURLToRolesEntryFromRequest(request);
				if (rolesEntry != null) {
					// simply return the disjunction of roles.
					org.apache.commons.collections15.CollectionUtils.filter(
							list, new Predicate<GrantedAuthority>() {
								public boolean evaluate(GrantedAuthority ga) {
									return rolesEntry.getRoleNames().contains(
											ga.getAuthority());
								}
							});
				}
			}
		}
		return list.toArray(new GrantedAuthority[0]);
	}

	private void filterAuthoritiesByStudy(List<GrantedAuthority> list,
			int studyId) {
		Study study = studyRepository.getById(studyId);
		if (study != null) {
			CurrentEntityHolder.setEntity(study);
			list.clear();
			for (String role : securityFacade.getRoles(SecurityUtils
					.getUserLoginName(), study)) {
				GrantedAuthority ga = new GrantedAuthorityImpl(role);
				if (!list.contains(ga)) {
					list.add(ga);
				}
			}

		}
	}

	private void filterAuthoritiesByResearchStaff(List<GrantedAuthority> list,
			int staffId) {
		// Authentication authentication = SecurityUtils.getAuthentication();
		ResearchStaff staff = researchStaffRepository.getById(staffId);
		if (staff != null) {
			CurrentEntityHolder.setEntity(staff);
			list.clear();
			List<SiteResearchStaff> siteStaffs = staff
					.getSiteResearchStaffsInternal();
			if (CollectionUtils.isNotEmpty(siteStaffs)) {
				for (SiteResearchStaff siteResearchStaff : siteStaffs) {
					//if (siteResearchStaff.isActive()) {
						Organization org = siteResearchStaff.getOrganization();
						Collection<String> roles = securityFacade.getRoles(
								SecurityUtils.getUserLoginName(), org);
						for (String role : roles) {
							GrantedAuthority ga = new GrantedAuthorityImpl(role);
							if (!list.contains(ga)) {
								list.add(ga);
							}
						}
					//}
				}
			}
		}
	}

	private void filterAuthoritiesByInvestigator(List<GrantedAuthority> list,
			int invId) {
		Investigator investigator = investigatorRepository.getById(invId);
		if (investigator != null) {
			CurrentEntityHolder.setEntity(investigator);
			list.clear();
			List<SiteInvestigator> siteInvestigators = investigator
					.getSiteInvestigatorsInternal();
			if (CollectionUtils.isNotEmpty(siteInvestigators)) {
				for (SiteInvestigator siteInvestigator : siteInvestigators) {
					//if (siteInvestigator.isActive()) {
						Organization org = siteInvestigator.getOrganization();
						Collection<String> roles = securityFacade.getRoles(
								SecurityUtils.getUserLoginName(), org);
						for (String role : roles) {
							GrantedAuthority ga = new GrantedAuthorityImpl(role);
							if (!list.contains(ga)) {
								list.add(ga);
							}
						}
					//}
				}
			}
		}
	}

	private URLToEntityIdMapEntry getURLToEntityIdEntryFromRequest(
			HttpServletRequest request) {
		String path = getPathFromRequest(request);
		String value = getFilterByURLAndEntityMap().get(path);
		if (value != null) {
			String className = value.split(COLON)[0];
			String parameterName = value.split(COLON)[1];
			String parameterValue = request.getParameter(parameterName);
			if (GenericValidator.isInt(parameterValue)) {
				URLToEntityIdMapEntry entry = new URLToEntityIdMapEntry();
				entry.setClassName(className);
				entry.setParameterName(parameterName);
				entry.setObjectId(Integer.parseInt(parameterValue));
				return entry;
			}
		}
		return null;
	}

	private URLToRoleListMapEntry getURLToRolesEntryFromRequest(
			HttpServletRequest request) {
		String path = getPathFromRequest(request);
		String value = getFilterByURLAndRoleListMap().get(path);
		if (StringUtils.isNotBlank(value)) {
			URLToRoleListMapEntry entry = new URLToRoleListMapEntry();
			entry.setRoleNames(Arrays.asList(value.split("\\s*,\\s*")));
			return entry;
		}
		return null;
	}

	/**
	 * @param request
	 * @return
	 */
	private String getPathFromRequest(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String context = request.getContextPath();
		String path = uri.length() > context.length() ? uri.substring(context
				.length()) : uri;
		return path;
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public CaaersSecurityFacade getSecurityFacade() {
		return securityFacade;
	}

	public void setSecurityFacade(CaaersSecurityFacade securityFacade) {
		this.securityFacade = securityFacade;
	}

	public Map<String, String> getFilterByURLAndEntityMap() {
		return filterByURLAndEntityMap;
	}

	public void setFilterByURLAndEntityMap(Map<String, String> urlMap) {
		this.filterByURLAndEntityMap = urlMap;
	}

	public Map<String, String> getFilterByURLAndRoleListMap() {
		return filterByURLAndRoleListMap;
	}

	public void setFilterByURLAndRoleListMap(
			Map<String, String> filterByURLAndRoleListMap) {
		this.filterByURLAndRoleListMap = filterByURLAndRoleListMap;
	}

	public ResearchStaffRepository getResearchStaffRepository() {
		return researchStaffRepository;
	}

	public void setResearchStaffRepository(
			ResearchStaffRepository researchStaffRepository) {
		this.researchStaffRepository = researchStaffRepository;
	}

	public InvestigatorRepository getInvestigatorRepository() {
		return investigatorRepository;
	}

	public void setInvestigatorRepository(
			InvestigatorRepository investigatorRepository) {
		this.investigatorRepository = investigatorRepository;
	}

	public StudyRepository getStudyRepository() {
		return studyRepository;
	}

	public void setStudyRepository(StudyRepository studyRepository) {
		this.studyRepository = studyRepository;
	}

	/**
	 * @author dkrylov
	 * 
	 */
	private static class URLToRoleListMapEntry {
		private Collection<String> roleNames = new ArrayList<String>();

		public Collection<String> getRoleNames() {
			return roleNames;
		}

		public void setRoleNames(Collection<String> roleNames) {
			this.roleNames = roleNames;
		}

	}

	/**
	 * @author dkrylov
	 * 
	 */
	private static class URLToEntityIdMapEntry {
		private String className;
		private String parameterName;
		private Integer objectId;

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public String getParameterName() {
			return parameterName;
		}

		public void setParameterName(String parameterName) {
			this.parameterName = parameterName;
		}

		public Integer getObjectId() {
			return objectId;
		}

		public void setObjectId(Integer objectId) {
			this.objectId = objectId;
		}
	}

	private static class FabricatedAuthentication extends
			AbstractAuthenticationToken {

		private Object credentials;
		private Object principal;

		/**
		 * 
		 */
		private static final long serialVersionUID = 9005585799426317557L;

		public FabricatedAuthentication(Authentication authentication,
				GrantedAuthority[] adjustedAuthorities) {
			super(adjustedAuthorities);
			setCredentials(authentication.getCredentials());
			setPrincipal(authentication.getPrincipal());
			setDetails(authentication.getDetails());
			setAuthenticated(authentication.isAuthenticated());

		}

		public Object getCredentials() {
			return credentials;
		}

		public void setCredentials(Object credentials) {
			this.credentials = credentials;
		}

		public Object getPrincipal() {
			return principal;
		}

		public void setPrincipal(Object principal) {
			this.principal = principal;
		}

	}

}
