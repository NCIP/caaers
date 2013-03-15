/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.search.cell;

import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySiteAjaxableDomainObject;

import java.util.ArrayList;
import java.util.List;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.Cell;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.ColumnBuilder;


/**
 * @author Biju Joseph
 * @crated Oct 2, 2008
 */
public class SelectedStudySiteCell implements Cell {

    public String getExportDisplay(TableModel model, Column column) {
        return column.getValueAsString();
    }

    public String getHtmlDisplay(TableModel model, Column column) {
        ColumnBuilder inputBuilder = new ColumnBuilder(column);
        inputBuilder.tdStart();


        try {
            StudySite ss = (StudySite) model.getCurrentRowBean();
            List<StudySite> studySites = new ArrayList<StudySite>();
            studySites.add(ss);
            
            inputBuilder.getHtmlBuilder().table(0).close();
            for (StudySite studySite : studySites) {

                inputBuilder.getHtmlBuilder().tr(0).close();
                inputBuilder.tdStart();
                inputBuilder.getHtmlBuilder().input("radio")
                        .name("studySite")
                        .id("studySite" + studySite.getId().intValue())
                        .value(studySite.getId().toString())
                        .onclick("resetStudyAndSites(this);")
                        .styleClass("sitesRadioBtn siteStudy_" + studySite.getId());
                inputBuilder.getHtmlBuilder().xclose();
                inputBuilder.tdBody(studySite.getOrganization().getName());

                inputBuilder.tdEnd();
                inputBuilder.getHtmlBuilder().trEnd(0);

            }
            inputBuilder.getHtmlBuilder().tableEnd(0);


        } catch (Exception e) {
        }

        inputBuilder.tdEnd();

        return inputBuilder.toString().trim();
    }




}