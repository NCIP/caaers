package gov.nih.nci.cabig.caaers.tools.perf;
import net.sf.infrared.aspects.aj.AbstractApiAspect;

	public aspect DomainLayerAspect extends AbstractApiAspect {
	// This pointcut matches execution of all public APIs in
	// com.my.app.layer1 and subpackages
	public pointcut apiExecution():
	execution(public * gov.nih.nci.cabig.caaers.domain.*..*(..) );
//	execution(public * gov.nih.nci.cabig.caaers.domain.*(..) ) ||
//	execution(public * edu.duke.cabig.c3pr.dao.HealthcareSiteDao.*(..) ) ;

	// InfraRED will record executions of all public APIs in
	// com.my.app.layer1 and subpackages as Layer One.
	
	public String getLayer() {
	return "caAERS domain layer";
	}
	}

