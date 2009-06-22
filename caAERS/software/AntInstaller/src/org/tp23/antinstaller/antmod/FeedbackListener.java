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
package org.tp23.antinstaller.antmod;

import java.util.ResourceBundle;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildListener;
import org.tp23.antinstaller.renderer.swing.SwingInstallerContext;
/**
 *
 * <p>Reports a message in the swing UI this mesage is reported in the
 * top of the window. </p>
 * <p>The Listeners should apparently never uses System.out or System.err</p>
 * This class is not really an Ant modification since it simply implements
 * a public interface but is here on the offchance that one day Ant changes
 * it's APIs
 * @author Paul Hinds
 * @version $Id: FeedbackListener.java,v 1.1 2006/08/19 15:35:36 kumarvi Exp $
 */
public class FeedbackListener implements BuildListener{

	private final SwingInstallerContext swingCtx;
	private static final ResourceBundle res = ResourceBundle.getBundle("org.tp23.antinstaller.renderer.Res");

	public FeedbackListener(SwingInstallerContext swingCtx) {
		this.swingCtx=swingCtx;
	}

	/**
	 * buildStarted This event results in User notification
	 *
	 * @param buildEvent BuildEvent
	 */
	public void buildStarted(BuildEvent buildEvent) {
		//swingCtx.provideAntFeedBack(buildEvent.getMessage());
		swingCtx.buildStarted(buildEvent);
	}

	/**
	 * buildFinished This event results in User notification
	 *
	 * @param buildEvent BuildEvent
	 */
	public void buildFinished(BuildEvent buildEvent) {
		swingCtx.provideAntFeedBack(res.getString("installFinished"));
		swingCtx.buildFinished(buildEvent);
	}

	/**
	 * targetStarted This event results in User notification
	 *
	 * @param buildEvent BuildEvent
	 */
	public void targetStarted(BuildEvent buildEvent) {
		swingCtx.provideAntFeedBack(res.getString("running")+buildEvent.getTarget());
		swingCtx.targetStarted(buildEvent);
	}

	/**
	 * targetFinished This event is ignored
	 * @param buildEvent BuildEvent
	 */
	public void targetFinished(BuildEvent buildEvent) {
		swingCtx.targetFinished(buildEvent);
	}

	/**
	 * taskStarted This event is ignored
	 * @param buildEvent BuildEvent
	 */
	public void taskStarted(BuildEvent buildEvent) {
		//swingCtx.provideAntFeedBack(buildEvent.getMessage());
	}

	/**
	 * taskFinished This event is ignored
	 * @param buildEvent BuildEvent
	 */
	public void taskFinished(BuildEvent buildEvent) {
		//swingCtx.provideAntFeedBack(buildEvent.getMessage());
	}

	/**
	 * messageLogged This event is ignored
	 * @param buildEvent BuildEvent
	 */
	public void messageLogged(BuildEvent buildEvent) {
		//swingCtx.provideAntFeedBack(buildEvent.getMessage());
	}
}
