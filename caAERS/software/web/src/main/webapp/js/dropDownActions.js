
// STUDY ACTIONS

function showDashboardStudiesMenuOptions(_element, _ssi, _id, _complete) {
    var html = "<div><ul style='font-family:tahoma;'>" +
            "<li><a class='submitter-blue' href='#' onclick='doEdit(\"" + _id + "\")'>Edit study details</a></li>" +
            "<li><a class='submitter-blue' href='#' onclick='addStudySite(\"" + _id + "\", " + _complete + ")'>Add Study Site</a></li>" +
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