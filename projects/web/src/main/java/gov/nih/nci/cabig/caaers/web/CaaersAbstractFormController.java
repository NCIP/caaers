package gov.nih.nci.cabig.caaers.web;

import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Sujith Vellat Thayyilthodi
 * */
public abstract class CaaersAbstractFormController extends SimpleFormController {

	public CaaersAbstractFormController(){
		setBindOnNewForm(true);
	}
}
