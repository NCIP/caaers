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
package org.tp23.antinstaller.page;


/**
 *
 * <p>Represents a license page witha  resouce that contains the text of the licenses </p>
 * <p> </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: LicensePage.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class LicensePage
	extends Page {

	private String resource;
	private String usePaging;

	public LicensePage() {
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getUsePaging() {
		return usePaging;
	}

	public void setUsePaging(String usePaging) {
		this.usePaging = usePaging;
	}
}
