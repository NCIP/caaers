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
package org.tp23.antinstaller.runtime.exe;

import org.tp23.antinstaller.InstallerContext;


/**
 * A filter chain is a series of operations to be run by the ExecInstall class.
 * This defines the sequence of events in the installer and provides for pluggable
 * extra sequences for example post installtion messages or running the application.
 * All FilterChains should end in a FinalizerFilter.
 * @author Paul Hinds
 * @version $Id: FilterChain.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public interface FilterChain {
	public void init(InstallerContext ctx);
	public ExecuteFilter[] getFilters();
}
