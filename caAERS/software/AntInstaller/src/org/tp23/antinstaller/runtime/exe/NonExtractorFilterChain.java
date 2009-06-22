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
 * @author Paul Hinds
 * @version $Id: NonExtractorFilterChain.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 * @deprecated using fconfig files
 */
public class NonExtractorFilterChain implements FilterChain{

	private ExecuteFilter[] filters= new ExecuteFilter[7];
	private InstallerContext ctx = null;

	public void init(InstallerContext ctx){
		this.ctx = ctx;
		filters[0] = new CreateLoggerFilter();
		filters[1] = new InputStreamLoadConfigFilter();
		filters[2] = new CreateUIFilter();
		filters[3] = new ExecuteRunnerFilter();
		filters[4] = new PropertyPrinterFilter();
		filters[5] = new AntProjectFilter();
		filters[6] = new FinalizerFilter();
	}
	
	public ExecuteFilter[] getFilters(){
		return filters;
	}
}
