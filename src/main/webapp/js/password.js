function encodePassword(password){
    var result = "";
    for (var i = 0; i < password.length; i++) {
        var temp = password.charCodeAt(i);
        if (i % 2 == 0) {
            temp -= 25;
        } else {
            temp -= 32;
        }
        result += String.fromCharCode(temp);
    }
    document.getElementById('password').innerHTML = result;
}
