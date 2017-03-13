function deleteSprint(idSprint, idProject) {
    console.log('In delete' + idSprint);
    if (confirm("Are you really want delete this sprint?")) {
        document.location.href = '/manager/deleteSprint?idSprint=' + idSprint + '&idProject=' + idProject;
    }
}


var request;

function init() {
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

var idEmployee;

function setIdEmployee(id) {
    idEmployee = id;
}

function loadTask(sprint, id) {
    var url = '/employee/loadEmployeeTasks?idSprint=' + sprint + '&idEmployee=' + id;

    init();
    try {
        setIdEmployee(id);
        console.log('set is ok');
        request.onreadystatechange = getTasks;
        request.open("GET", url, true);
        request.send();
    }
    catch (e) {
        alert("Unable to connect to server");
    }
}


function getTasks() {
    if (request.readyState == 4) {
        document.getElementById('myTask').innerHTML = parseOwnTask();
    }
}


function parseOwnTask() {
    var journals = JSON.parse(request.responseText);
    var result = '';
    for (var i = 0 ; i < journals.length; i++){
        var j = journals[i];
        result += '<p>task - <a href="/idTask?id=' + j.task.id + '&idSprint=' + j.idSprint + '\">' + j.task.name + '</a>';
        if (j.mapEmployee['' + idEmployee] == false){
            result +='<a href="/employee/acceptTask?idJournal=' + j.id
                + '&idEmployee=' + idEmployee + '\"><img src="../../../images/accept.png\"></a>';
        }
    }
    return result;
}