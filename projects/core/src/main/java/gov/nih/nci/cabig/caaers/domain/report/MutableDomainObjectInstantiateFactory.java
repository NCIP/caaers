package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import org.apache.commons.collections15.functors.InstantiateFactory;

public class MutableDomainObjectInstantiateFactory <T extends AbstractMutableDomainObject> extends InstantiateFactory<T>{

	public MutableDomainObjectInstantiateFactory(Class<T> classToCreate){
		super(classToCreate);
	}
	
	@Override
	public T create() {
		return super.create();
	}
	
}
