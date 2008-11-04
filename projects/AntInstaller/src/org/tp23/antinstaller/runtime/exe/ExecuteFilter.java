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

import org.tp23.antinstaller.InstallException;
import org.tp23.antinstaller.InstallerContext;


/**
 * The Execute engine is being replaced with a filter pattern to enable
 * further extension of the system,  Initiallly a hardcoded filter chain
 * will be used subsequently to be replaced by a pluggable mechanism.
 * @author Paul Hinds
 * @version $Id: ExecuteFilter.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public interface ExecuteFilter {
	public void exec(InstallerContext ctx) throws InstallException;
}
