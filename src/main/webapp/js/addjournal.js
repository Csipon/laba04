var idProject;
var idSprint;
var idTask;
var empList = [];

var request;

function init() {
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function selectProject() {
    idProject = document.getElementById('project').value;
    loadSprints();
}

function loadSprints() {
    var url = '/manager/loadSprints?id=' + idProject;

    init();

    request.onreadystatechange = viewSprints;
    request.open("GET", url, true);
    request.send();
}

function viewSprints() {
    if (request.readyState == 4) {
        document.getElementById('sprints').innerHTML = parseSprints();
    }
}

function parseSprints() {
    var list = JSON.parse(request.responseText);

    var result = '<form><select id="sprint">';

    for(var i = 0; i < list.length; i++){
        result +='<option value="' + list[i].id + '">' + list[i].name + '</option>';
    }
    result +='</select><input type="button" onclick="selectSprint()" value="Select"/>';
    result +='</form>';
    return result;
}

function selectSprint() {
    idSprint = document.getElementById('sprint').value;
    loadTasks();
}


function loadTasks() {
    var url = '/manager/loadTasks?id=' + idSprint;

    init();

    request.onreadystatechange = viewTasks;
    request.open("GET", url, true);
    request.send();
}


function viewTasks() {
    if (request.readyState == 4) {
        document.getElementById('tasks').innerHTML = parseTasks();
    }
}

function parseTasks() {
    var list = JSON.parse(request.responseText);

    var result = '<form><select id="task">';

    for(var i = 0; i < list.length; i++){
        result +='<option value="' + list[i].id + '">' + list[i].name + '</option>';
    }
    result +='</select><input type="button" onclick="selectTask()" value="Select"/>';
    result +='</form>';
    return result;
}

function selectTask() {
    idTask = document.getElementById('task').value;
    loadEmployees();
}

function loadEmployees() {
    var url = '/manager/loadEmployees';
    init();

    request.onreadystatechange = viewEmployees;
    request.open("GET", url, true);
    request.send()
}

function viewEmployees() {
    if (request.readyState == 4) {
        document.getElementById('employees').innerHTML = parseEmployees();
    }
}

function parseEmployees() {
    var list = JSON.parse(request.responseText);

    var result = '';

    for(var i = 0; i < list.length; i++) {
        var emp = list[i];
        if (contains(emp.id)) {
            result += '<p>' + emp.name + ' ' + emp.surname +
                ', level qualification - ' + emp.levelQualification + ', already added</p>';
        } else {
            result += '<p>' + emp.name + ' ' + emp.surname +
                ', level qualification - ' + emp.levelQualification +
                '<a onclick="addEmployee(' + emp.id + ')">' +
                    '<img src="../../../images/add.png">' +
                '</a>' +
                '</p>';
        }
    }
    return result;
}

function addEmployee(id) {
    empList[empList.length] = id;
    loadEmployees();
}

function contains(id) {
    for (var i = 0; i < empList.length; i++){
        if(empList[i] == id){
            return true;
        }
    }
    return false;
}

function saveAdded() {
    var list = JSON.stringify(empList);
    var desc = document.getElementById('description').value;
    var url = '/manager/addJournal?idSprint=' + idSprint + '&idTask=' + idTask + '&description=' + desc + '&list=' + list;
    init();
    request.onreadystatechange = successfulAdd;
    request.open("POST", url, true);
    request.send();
}

function successfulAdd() {
    if (request.readyState == 4 && this.status == 200) {
        location.href = '/manager/profileMgr';
    }
}

