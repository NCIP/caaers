
// STUDY ACTIONS

function showDashboardStudiesMenuOptions(_element, _roles, _ssi, _id, _complete) {
    var rolesSize = _roles.length;

    var _optionDetails = "";
    if (hasRole('supplemental_study_information_manager') || (_complete == 'true' && hasRole('study_qa_manager')) || (_complete == 'false' && hasRole('study_creator')))
        _optionDetails = "<li><a class='submitter-blue' href='#' onclick='doEdit(\"" + _id + "\")'>Edit study details</a></li>";

    var _optionSites = "";
    if (hasRole('study_site_participation_administrator'))
        _optionSites = "<li><a class='submitter-blue' href='#' onclick='addStudySite(\"" + _id + "\", " + _complete + ")'>Add study site</a></li>";

    var _optionInvestigators = "";
    if (hasRole('study_team_administrator'))
        _optionInvestigators = "<li><a class='submitter-blue' href='#' onclick='addStudyInvestigators(\"" + _id + "\")'>Manage investigators</a></li>";

    var _optionPersonnel = "";
    if (hasRole('study_team_administrator'))
        _optionPersonnel = "<li><a class='submitter-blue' href='#' onclick='addStudyPersonnel(\"" + _id + "\")'>Manage personnel</a></li>";

    var _optionRegisterSubject = "";
    if (hasRole('registrar') && hasRole('subject_manager'))
        _optionRegisterSubject = "<li><a class='submitter-blue' href='#' onclick='doRegisterSubject(\"" + _id + "\")'>Register subject</a></li>";

    var html = "<div><ul style='font-family:tahoma;'>" +
            // "<li><a class='submitter-blue' href='#'>" + rolesSize + "</a></li>" +
            _optionDetails +
            _optionSites +
            _optionInvestigators +
            _optionPersonnel +
            _optionRegisterSubject +
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

    var _optionSubjectEdit = "";
    var _optionSubjectMedicalHistory = "";
    if (hasRole('registration_qa_manager') || hasRole('registration_qa_manager') || hasRole('subject_manager') || hasRole('registrar') || hasRole('data_reader')) {
        _optionSubjectEdit = "<li><a class='submitter-blue' href='#' onclick='editSubjectDetails(" +_studyId + ", " + _subjectId + ")'>Edit Subject Details</a></li>";
        _optionSubjectMedicalHistory = "<li><a class='submitter-blue' href='#' onclick='editMedicalHistory(" +_studyId + ", " + _subjectId + ", " + _assignmentId + ")'>Edit Medical History</a></li>";
    }

    var _optionEnterAdverseEvents = "";
    if (hasRole('ae_reporter') || hasRole('data_reader')) _optionEnterAdverseEvents = "<li><a class='submitter-blue' href='#' onclick='enterAdverseEvents(" +_studyId + ", " + _subjectId + ")'>Enter Adverse Events</a></li>";

    var _optionAssignToStudy = "";
    if (hasRole('registrar')) _optionAssignToStudy = "<li><a class='submitter-blue' href='#' onclick='assignToStudy(" +_studyId + ", " + _subjectId + ")'>Assign to Study</a></li>";

    var html = "<div><ul style='font-family:tahoma;'>" +
            _optionSubjectEdit +
            _optionSubjectMedicalHistory +
            _optionEnterAdverseEvents +
            _optionAssignToStudy +
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

function showUserMenuOptions(_element, strId, rt, un, active) {
    var _el = jQuery(_element);
    var html_start = "<div><ul style='font-family:tahoma;'>";
    var html_end = "</ul></div>";
    var _editAction = "<li><a class='submitter-blue' href='#' onclick='javascript:doEdit(#{strId}, \"#{rt}\", \"#{un}\")'>Edit</a></li>";
    var _action = "Activate";
    if (active == "Active") {
        _action = "Deactivate"
    }


    var _activateAction = "<li><a class='submitter-blue' href='#' onclick='javascript:doActivate(#{strId}, \"#{rt}\", \"#{un}\", \"#{active}\")'>" + _action + "</a></li>";
    
    var html = html_start + _editAction + (un != "" ? _activateAction : "") + html_end;
    var html = html.interpolate({strId:strId, rt:rt, un:un, active:active});

     _el.menu({
        content: html,
        maxHeight: 180,
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
