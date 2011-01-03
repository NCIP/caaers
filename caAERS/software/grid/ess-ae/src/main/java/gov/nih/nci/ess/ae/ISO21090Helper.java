package gov.nih.nci.ess.ae;

import _21090.org.iso.ADXP;
import _21090.org.iso.AddressPartType;
import _21090.org.iso.BL;
import _21090.org.iso.CD;
import _21090.org.iso.ED;
import _21090.org.iso.EDText;
import _21090.org.iso.ENXP;
import _21090.org.iso.EntityNamePartType;
import _21090.org.iso.II;
import _21090.org.iso.NullFlavor;
import _21090.org.iso.ST;
import _21090.org.iso.TSDateTime;

/**
 * @author Denis G. Krylov
 * 
 */
public final class ISO21090Helper {

	private ISO21090Helper() {
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

}
