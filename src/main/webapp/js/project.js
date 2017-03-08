var request;
function initRequest() {
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

var idProj;

function showFreeEmployeesForSetOnProject(idProject) {
    idProj = idProject;
    var url = 'admin/showEmpsFreeProject?idProject=' + idProject;
    initRequest();

    request.onreadystatechange = viewFreeEmployees;
    request.open("GET", url, true);
    request.send();
}

function viewFreeEmployees() {
    if (request.readyState == 4){
        document.getElementById('showEmployees').disabled;
        document.getElementById('listEmployees').innerHTML = parseEmployeesForSetOnProject(request.responseText);
    }
}

function parseEmployeesForSetOnProject(responce) {
    var emps = JSON.parse(responce);
    var result = '';

    for (var i = 0; i < emps.length; i++){
        var emp = emps[i];
        result += '<p>' + emp.name + ' ' + emp.surname + ', level qualification - ' + emp.levelQualification +
            '<a onclick="setEmpToProject(' + emp.id + ',' + idProj + ')">' +
            '<img src="../../../images/add.png">' +
            '</a>' +
            '</p>';
    }
    return result;
}

function setEmpToProject(idEmployee, idProject) {
    var url = '/admin/setEmpToProject?idEmployee=' + idEmployee + '&idProject=' + idProject;
    initRequest();

    request.onreadystatechange = updateViewFreeEmployees;
    request.open("POST", url, true);
    request.send();
}

function updateViewFreeEmployees() {
    if (request.readyState == 4 && this.status == 200){
        showFreeEmployeesForSetOnProject(idProj);
    }
}
