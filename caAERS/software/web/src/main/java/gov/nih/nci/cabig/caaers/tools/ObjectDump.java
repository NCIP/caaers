package gov.nih.nci.cabig.caaers.tools;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: ion
 * Date: Aug 15, 2008 - 5:06:55 PM
 * @author Ion C. Olaru
 */

public class ObjectDump extends TagSupport {

    private int alpha;
    
    protected final Log log = LogFactory.getLog(getClass());
    protected JspWriter out;

    Object target;

    public int doStartTag() throws JspException {
        out = pageContext.getOut();

        Class cls = null;
        Field[] fields;
        
        try {
            cls = Class.forName(target.getClass().getName());
        } catch (ClassNotFoundException e) {
            log.error("Class <" + target.getClass().getName() + "> not found.");
        }

        try {
            out.write("<pre>");
            out.write("</pre>");
        } catch (IOException e) {
            log.error("Error." + e.getMessage());
        }
        return super.doStartTag();
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    static void write(String s) {
        System.out.print(s);
    }

    public static void main(String[] args) {
        ObjectDump target = new ObjectDump();

        Class cls = null;
        Field[] fields;

        try {
            cls = Class.forName(target.getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        write("Class: " + target.getClass().getName() + "\n");
        fields = cls.getFields();
        
        for (int i=0; i<fields.length; i++) {
            Field field = fields[i];
            write("\tfield[" + (i + 1) + "]: " + field.getName() + "\r\n");

            Type type = field.getType();
            write(type.toString());
        }



    }
}
