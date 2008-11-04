/* 
 * Copyright 2005 Paul Hinds
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tp23.antinstaller;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 *
 * <p>Custom Installation Exception, Hopefully compatible with JDK1.3 and 1.4</p>
 * @author Paul Hinds
 * @version $Id: InstallException.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class InstallException extends Exception{

	private Throwable cause;
	
	public InstallException() {
	}
	public InstallException(String message) {
		super(message);
	}
	public InstallException(String message,Throwable cause) {
		super(message);
        this.cause = cause;
	}
    public Throwable getException() {
        return cause;
    }
    public Throwable getCause() {
        return getException();
    }
    public void printStackTrace() {
        printStackTrace(System.err);
    }
    public void printStackTrace(PrintStream ps) {
        synchronized (ps) {
            super.printStackTrace(ps);
            if (cause != null) {
                ps.println("--- Nested Exception ---");
                cause.printStackTrace(ps);
            }
        }
    }
    public void printStackTrace(PrintWriter pw) {
        synchronized (pw) {
            super.printStackTrace(pw);
            if (cause != null) {
                pw.println("--- Nested Exception ---");
                cause.printStackTrace(pw);
            }
        }
    }
}
