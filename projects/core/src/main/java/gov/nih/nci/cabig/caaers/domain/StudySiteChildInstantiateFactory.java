package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections15.functors.InstantiateFactory;

public class StudySiteChildInstantiateFactory <T extends StudySiteChild> extends InstantiateFactory<T>{
	private StudySite studySite;
	public StudySiteChildInstantiateFactory(StudySite studySite, Class<T> classToInit){
		super(classToInit);
		this.studySite = studySite;
	}
	@Override
	public T create() {
		T child = super.create();
		child.setStudySite(studySite);
		return child;
	}
}
