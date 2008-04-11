package gov.nih.nci.cabig.caaers.tools;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

/**
 * @author Rhett Sutphin
 */
public class ObjectToolsTest extends TestCase {
    public void testBuildReduced() throws Exception {
        Bean src = new Bean("A", 1, 14L, (byte) 2);
        Bean reduced = ObjectTools.reduce(src, "string", "primitiveLong");

        assertBean(src.getString(), null, src.getPrimitiveLong(), (byte) 0, reduced);
    }

    public void testReduceAll() throws Exception {
        List<Bean> src = Arrays
                        .asList(new Bean("A", 1, 2, (byte) 3), new Bean("E", 3, 6, (byte) 9));
        List<Bean> reduced = ObjectTools.reduceAll(src, "integer", "primitiveLong");
        assertEquals("Wrong number of beans copied", 2, reduced.size());
        assertBean(null, 1, 2, (byte) 0, reduced.get(0));
        assertBean(null, 3, 6, (byte) 0, reduced.get(1));
    }

    private static void assertBean(String expectedString, Integer expectedInteger,
                    long expectedLong, byte expectedByte, Bean actual) {
        assertEquals("Wrong string", expectedString, actual.getString());
        assertEquals("Wrong integer", expectedInteger, actual.getInteger());
        assertEquals("Wrong long", expectedLong, actual.getPrimitiveLong());
        assertEquals("Wrong byte", expectedByte, actual.getPrimitiveByte());
    }

    private static class Bean {
        private String string;

        private Integer integer;

        private long primitiveLong;

        private byte primitiveByte;

        public Bean() {
        }

        public Bean(String string, Integer integer, long primitive, byte primitiveByte) {
            this.string = string;
            this.integer = integer;
            this.primitiveLong = primitive;
            this.primitiveByte = primitiveByte;
        }

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public Integer getInteger() {
            return integer;
        }

        public void setInteger(Integer integer) {
            this.integer = integer;
        }

        public long getPrimitiveLong() {
            return primitiveLong;
        }

        public void setPrimitiveLong(long primitiveLong) {
            this.primitiveLong = primitiveLong;
        }

        public byte getPrimitiveByte() {
            return primitiveByte;
        }

        public void setPrimitiveByte(byte primitiveByte) {
            this.primitiveByte = primitiveByte;
        }
    }
}
