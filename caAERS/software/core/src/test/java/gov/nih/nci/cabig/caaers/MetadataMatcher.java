package gov.nih.nci.cabig.caaers;

import org.easymock.EasyMock;
import org.easymock.IArgumentMatcher;

import edu.duke.cabig.c3pr.esb.Metadata;

public class MetadataMatcher implements IArgumentMatcher {
	
	private Metadata expected;
	
	MetadataMatcher(Metadata pMetadata)
    {
        expected = pMetadata;
    }

    public static final Metadata eqMetadata(Metadata pMetadata)
    {
        EasyMock.reportMatcher(new MetadataMatcher(pMetadata));
        return null;
    }

    
	public void appendTo(StringBuffer b) {
		b.append("eqMetadata(").append(expected).append(")");		
	}

    public boolean matches(Object arg0) {
        if (! (arg0 instanceof Metadata))
        {
            return false;
        }

        Metadata actual = (Metadata) arg0;

        String operationName = expected.getOperationName();
        if (operationName == null && actual.getOperationName() != null)
        {
            return false;
        } else if (! operationName.equals(actual.getOperationName()))
        {
            return false;
        }

        String serviceType = expected.getServiceType();
        if (serviceType == null && actual.getServiceType() != null)
        {
            return false;
        } else if (! serviceType.equals(actual.getServiceType()))
        {
            return false;
        }

        String externalIdentifier = expected.getExternalIdentifier();
        if (externalIdentifier == null && actual.getExternalIdentifier() != null)
        {
            return false;
        } else if (! externalIdentifier.equals(actual.getExternalIdentifier()))
        {
            return false;
        }

        return true;
    }



}
