
// STUDY ACTIONS

function showDashboardStudiesMenuOptions(_element, _roles, _ssi, _id, _complete) {
    var html = "<div><ul style='font-family:tahoma;'>" +
            "<li><a class='submitter-blue' href='#' onclick='doEdit(\"" + _id + "\")'>Edit study details</a></li>" +
            "<li><a class='submitter-blue' href='#' onclick='addStudySite(\"" + _id + "\", " + _complete + ")'>Add Study Site</a></li>" +
            "<li><a class='submitter-blue' href='#' onclick='addStudyInvestigators(\"" + _id + "\")'>Manage Investigators</a></li>" +
            "<li><a class='submitter-blue' href='#' onclick='addStudyPersonnel(\"" + _id + "\")'>Manage Personnel</a></li>" +
            "<li><a class='submitter-blue' href='#' onclick='doRegisterSubject(\"" + _id + "\")'>Register Subject</a></li>" +
            "<li><a class='submitter-blue' href='#' onclick='doUpdate(\"" + _ssi + "\", \"" + _id + "\")'>Synchronize with CTEP</a></li>" +
            "</ul></div>";
    jQuery(_element).menu({
            content: html,
            maxHeight: 180,
            width: 180,
            positionOpts: {
                directionV: 'down',
                posX: 'left',
                posY: 'bottom',
                offsetX: 0,
                offsetY: 0
            },
            showSpeed: 300
        });
}

function doUpdate(_sfsId, _id) {
    var mp = showMessagePopup("please_wait");
    createStudy.syncStudyWithAdEERS(_sfsId, _id, function(_resultId) {
        mp.close();
        if (_resultId.error) {
            showTimerPopup("error_page", 3);
        }
    })
}

function openUrl(_url) {
    window.location = _url;
}

function showDashboardSubjectsAssignmentsMenuOptions(_element, _roles, _subjectId, _studyId, _assignmentId) {
    var _el = jQuery(_element);
    var html = "<div><ul style='font-family:tahoma;'>" +
            "<li><a class='submitter-blue' href='#' onclick='editSubjectDetails(" +_studyId + ", " + _subjectId + ")'>Edit Subject Details</a></li>" +
            "<li><a class='submitter-blue' href='#' onclick='editMedicalHistory(" +_studyId + ", " + _subjectId + ", " + _assignmentId + ")'>Edit Medical History</a></li>" +
            "<li><a class='submitter-blue' href='#' onclick='enterAdverseEvents(" +_studyId + ", " + _subjectId + ")'>Enter Adverse Events</a></li>" +
            "<li><a class='submitter-blue' href='#' onclick='assignToStudy(" +_studyId + ", " + _subjectId + ")'>Assign to Study</a></li>" +
            "</ul></div>";
    _el.menu({
            content: html,
            maxHeight: 180,
            width: 180,
            positionOpts: {
                directionV: 'down',
                posX: 'left',
                posY: 'bottom',
                offsetX: 0,
                offsetY: 0
            },
            showSpeed: 300
        });
}
