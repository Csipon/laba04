/**
 * Created by HOUSE on 2/23/2017.
 */
var request;
function initRequest() {
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

var manager;

function loadFreeEmployees(idManager) {
    manager = idManager;
    var url = 'loadFreeEmps?idManager=' + idManager;
    initRequest();

    request.onreadystatechange = viewFreeEmployees;
    request.open("GET", url, true);
    request.send();
}

function viewFreeEmployees() {
    if (request.readyState == 4){
        document.getElementById('employees').innerHTML = parseFreeEmployees(request.responseText);
    }
}


function parseFreeEmployees(list) {
    var emps = JSON.parse(list);

    var result = '<div class="viewEmployees">';

    for (var i = 0; i < emps.length; i++){
        var emp = emps[i];
        result += '<p>' + emp.name + ' ' + emp.surname + ', level qualification - ' + emp.levelQualification +
                    '<a onclick="addEmpInArray(' + emp.id + ')">' +
                        '<img src="../../../images/add.png">' +
                    '</a>' +
                  '</p>';
    }
    result += '</div>';

    return result;
}


function addEmpInArray(id) {
    addSubordinate(id);
    loadFreeEmployees(manager);
}

function addSubordinate(idEmployee) {
    var url = 'addSubordinateToManager?idEmployee=' + idEmployee + '&idManager=' + manager;
    initRequest();

    request.onreadystatechange = successfulAddEmployee;
    request.open("GET", url, true);
    request.send();
}

function successfulAddEmployee() {
    if (request.readyState == 4){
        document.getElementById('successfulAdd').innerHTML = request.responseText;
    }
}
