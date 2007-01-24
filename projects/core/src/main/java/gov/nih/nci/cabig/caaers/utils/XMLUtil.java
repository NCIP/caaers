package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

import java.io.StringWriter;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;


public class XMLUtil {

	public static String getXML(Object rootElement) {
		IMarshallingContext mctx = null;
		StringWriter writer = new StringWriter();
		try {
			IBindingFactory bfact = BindingDirectory
					.getFactory(rootElement.getClass());
			mctx = bfact.createMarshallingContext();
			mctx.setOutput(writer);
			mctx.marshalDocument(rootElement);
		} catch (JiBXException e) {
			throw new CaaersSystemException(e.getMessage(), e);
		}
		return writer.toString();
	}

}
