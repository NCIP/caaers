package gov.nih.nci.cabig.caaers.rules.common;

import gov.nih.nci.cabig.caaers.RuleException;

import java.io.StringReader;
import java.io.StringWriter;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;


public class XMLUtil {

	public static String toXML(Object rootElement) {
		IMarshallingContext mctx = null;
		StringWriter writer = new StringWriter();
		try {
			IBindingFactory bfact = BindingDirectory
					.getFactory(rootElement.getClass());
			mctx = bfact.createMarshallingContext();
			mctx.setOutput(writer);
			mctx.marshalDocument(rootElement);
		} catch (JiBXException e) {
			throw new RuleException(e.getMessage(), e);
		}
		//String returnString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + writer.toString();
		//return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + writer.toString();
		return writer.toString();
	}
	
	public static Object toObject(String xml){
		IUnmarshallingContext uctx = null;
		Object obj = null;
		try {
			obj = uctx.unmarshalDocument(new StringReader(xml));
		} catch (JiBXException e) {
			throw new RuleException(e.getMessage(), e);
		}
		return obj;
	}

}
