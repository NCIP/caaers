package gov.nih.nci.ess.sr;



import org.apache.axis.types.URI;
import org.apache.axis.types.URI.MalformedURIException;
import org.apache.commons.lang.StringUtils;

import _21090.org.iso.AD;
import _21090.org.iso.ADXP;
import _21090.org.iso.AddressPartType;
import _21090.org.iso.BAG_TEL;
import _21090.org.iso.BL;
import _21090.org.iso.CD;
import _21090.org.iso.ED;
import _21090.org.iso.EDText;
import _21090.org.iso.ENXP;
import _21090.org.iso.EntityNamePartType;
import _21090.org.iso.II;
import _21090.org.iso.INT;
import _21090.org.iso.IVL_TSDateTime;
import _21090.org.iso.NullFlavor;
import _21090.org.iso.PQ;
import _21090.org.iso.PQTime;
import _21090.org.iso.ST;
import _21090.org.iso.TEL;
import _21090.org.iso.TSDateTime;



/**
 * @author Denis G. Krylov
 * 
 */
public final class ISO21090Helper {
	
	static final String SEMICOLON = ":";
	static final String X_TEXT_FAX = "x-text-fax";
	static final String TEL = "tel";
	static final String MAILTO = "mailto";

	
	private ISO21090Helper() {
	}
	public static PQTime PQTime(Double days) {
		PQTime p = new PQTime();
		p.setValue(days);
		return p;
	}
	public static final II II(String ext) {
		II ii = new II();
		if (ext != null) {
			ii.setExtension(ext);
		} else {
			ii.setNullFlavor(NullFlavor.NI);
		}
		return ii;
	}

	public static final CD CD(String code) {
		CD cd = new CD();
		if (code != null) {
			cd.setCode(code);
		} else {
			cd.setNullFlavor(NullFlavor.NI);
		}
		return cd;
	}

	public static final BL BL(Boolean b) {
		BL cd = new BL();
		if (b != null) {
			cd.setValue(b);
		} else {
			cd.setNullFlavor(NullFlavor.NI);
		}
		return cd;
	}

	public static final TSDateTime TSDateTime(String s) {
		TSDateTime dateTime = new TSDateTime();
		dateTime.setValue(s);
		return dateTime;
	}
	
	public static final IVL_TSDateTime IVL_TSDateTime(TSDateTime high) {
		IVL_TSDateTime iv = new IVL_TSDateTime();
		iv.setHigh(high);
		return iv;
	}
	
	public static final IVL_TSDateTime IVL_TSDateTime(TSDateTime low , TSDateTime high) {
		IVL_TSDateTime iv = new IVL_TSDateTime();
		if (low != null)  iv.setLow(low);
		if (high != null)  iv.setHigh(high);
		return iv;
	}
	public static final ST ST(String s) {
		ST st = new ST();
		if (s != null) {
			st.setValue(s);
		} else {
			st.setNullFlavor(NullFlavor.NI);
		}
		return st;
	}

	public static final ENXP ENXP(String s, EntityNamePartType type) {
		ENXP en = new ENXP();
		en.setValue(s);
		en.setType(type);
		return en;
	}
	

	public static final ADXP ADXP(String s, AddressPartType typ) {
		ADXP ad = new ADXP();
		ad.setType(typ);
		ad.setValue(s);
		return ad;
	}
	
	public static final PQ PQ(Double value, String units) {
		PQ pq = new PQ();
		pq.setValue(value);
		pq.setUnit(units);
		return pq;
	}
	
	public static final PQ PQ(String text) {
		PQ pq = new PQ();
		pq.setOriginalText(EDText(text));
		return pq;
	}
	
	
	public static final TEL TEL(String s) throws MalformedURIException {
		URI uri = new URI(s);
		TEL tel = new TEL();
		tel.setValue(uri);
		return tel;
	}
	
	public static final ED ED(String s) {
		ED ed = new ED();
		ed.setValue(s);
		return ed;
	}

	public static final EDText EDText(String s) {
		EDText ed = new EDText();
		if (s != null) {
			ed.setValue(s);
		} else {
			ed.setNullFlavor(NullFlavor.NI);
		}
		return ed;
	}

	public static final CD CD(NullFlavor ni) {
		CD cd = new CD();
		cd.setNullFlavor(ni);
		return cd;
	}

	public static final BL BL(NullFlavor ni) {
		BL bl = new BL();
		bl.setNullFlavor(ni);
		return bl;
	}

	public static final II II(Integer i) {
		return i != null ? II(i.toString()) : II((String) null);
	}

	public static final ST ST(Integer i) {
		return i != null ? ST(i.toString()) : ST((String) null);
	}
	
	public INT INT(Integer i) {
		return i != null ? INT(i) : null;
	}
	
	public static AD AD(String street , String city, String state, String zip, String country){
		AD ad = new AD();
		int ct = -1;
		if (!StringUtils.isBlank(city)) {			
			ad.setPart(ct++, ADXP(city, AddressPartType.CTY));
		}
		if (!StringUtils.isBlank(country)) {	
			//the string passed in for country is actually the code.
			ad.setPart(ct++, ADXP(city, AddressPartType.CNT));
		}	
		if (!StringUtils.isBlank(street)) {			
			ad.setPart(ct++, ADXP(city, AddressPartType.AL));
		}	
		if (!StringUtils.isBlank(state)) {			
			ad.setPart(ct++, ADXP(city, AddressPartType.STA));
		}
		if (!StringUtils.isBlank(zip)) {			
			ad.setPart(ct++, ADXP(city, AddressPartType.ZIP));
		}
		return ad;
	}
	
	public static BAG_TEL BAG_TEL(String email , String phone , String fax)  {
		BAG_TEL bagTel = new BAG_TEL();
		int ct = -1;
		try {
			if (!StringUtils.isBlank(email)) {
					bagTel.setItem(ct++, TEL(MAILTO + SEMICOLON + email));
			}
			if (!StringUtils.isBlank(phone)) {
				bagTel.setItem(ct++, TEL(TEL + SEMICOLON + phone));
			}
			if (!StringUtils.isBlank(fax)) {
				bagTel.setItem(ct++, TEL(X_TEXT_FAX + SEMICOLON + fax));
			}
		} catch (MalformedURIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bagTel;
	}
	

	public static final String value(ST st) {
		if (st != null) {
			return st.getValue();
		} else {
			return null;
		}
	}

}
