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
package org.tp23.antinstaller.renderer.swing;

import org.tp23.antinstaller.page.Page;
/**
 *
 * <p>Called when a page is complete and the user presses next.
* At this point the pages fields are validated.  If validation passes
* the next page is shown </p>
 * <p> </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: tp23</p>
 * @author Paul Hinds
 * @version $Id: PageCompletionListener.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public interface PageCompletionListener {
	public void pageComplete(Page page);
	public void pageBack(Page page);
}
