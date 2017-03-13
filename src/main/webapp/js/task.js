function deleteTask(idTask, idSprint, idProject) {
    console.log('In delete' + idTask);
    if (confirm("Are you really want delete this task?")) {
        document.location.href = '/deleteTask?idTask=' + idTask + '&idSprint=' + idSprint + '&idProject=' + idProject;
    }
}


var idUser;
var taskId;
var sprintId;
var projectId;
var desc;
function makeMessage(idSender, messageTask, messageSprint, messageProject, description) {
    idUser = idSender;
    taskId = messageTask;
    sprintId = messageSprint;
    projectId = messageProject;
    desc = description;
    document.getElementById('sentMessage').disabled = true;
    document.getElementById("fieldMessage").innerHTML =
        '<td><p>Text :</td></br>' +
        '<td><textarea type="text" id="text" name="text" placeholder="Set here text..." rows="5" cols="45"></textarea></td> ' +
        '<input type="button" value="Send" onclick="message(idUser, taskId, sprintId, projectId, desc)">';
}


var request;
function initRequest() {
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }
}


function message(idSender, messageTask, messageSprint, messageProject, description) {
    var text = document.getElementById('text').value;
    var url = "/maker/sendMessage?text=" + text + "&idSender=" + idSender + "&messageTask=" + messageTask
        + "&messageSprint=" + messageSprint + "&messageProject=" + messageProject + "&description=" + description;

    initRequest();

    try {
        request.onreadystatechange = getInfo;
        request.open("POST", url, true);
        request.send();
    }
    catch (e) {
        alert("Unable to connect to server");
    }
}

function getInfo() {
    if (request.readyState == 4 && this.status == 200) {
        document.getElementById('fieldMessage').innerHTML = request.responseText;
        document.getElementById('sentMessage').disabled = false;
    }
}


// add dependent tasks

var listDependentTask =[];
var idSprintTemp;

function addDependentTask(idTask) {
    listDependentTask[listDependentTask.length] = idTask;
    showTasks(idSprintTemp);
}

function contains(id) {
    for (var i = 0; i < listDependentTask.length; i++){
        if(listDependentTask[i] == id){
            return true;
        }
    }
    return false;
}

function showTasks(idSprint) {
    idSprintTemp = idSprint;
    var url = '/manager/loadTasks?id=' +idSprint;

    initRequest();
    request.onreadystatechange = viewDependTasks;
    request.open('GET', url, true);
    request.send();
}

function viewDependTasks() {
    if (request.readyState == 4) {
        document.getElementById('listTasks').innerHTML = loadTasks();
    }
}


function loadTasks() {
    var tasks = JSON.parse(request.responseText);
    var result = '';
    for (var i= 0; i < tasks.length; i++){
        var task = tasks[i];
        if (contains(task.id)){
            result += '<p>Tasks with name - ' + task.name + ', already added</p>';
        }else {
            result += '<p>Add task ' + task.name + '<a onclick="addDependentTask(' + task.id + ')">' +
                '<img src="../../../images/add.png">' +
                '</a></p>';
        }
    }
    return result;
}

var idSprint;
function addTask() {
    idSprint = document.getElementById('idSprint').value;
    var name = document.getElementById('name').value;
    var estimate = document.getElementById('estimate').value;
    var description = document.getElementById('description').value;
    var level = document.getElementById('level').value;
    var tasks = JSON.stringify(listDependentTask);
    var url = '/manager/addTask?name=' + name + '&estimate=' + estimate + '&description=' + description
                + '&qualification=' + level + '&idSprint=' + idSprint + '&tasks=' + tasks;

    initRequest();
    request.onreadystatechange = toSprint();
    request.open('POST', url, true);
    request.send();
}

function toSprint() {
    if (request.readyState == 4 && this.status == 200) {
        location.href = '/idSprint?id=' + idSprint;
    }
}



