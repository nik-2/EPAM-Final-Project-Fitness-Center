function myFunction() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function displayTableBlockRole() {
    document.getElementById('role').style.display = 'block';
}

function displayNoneRole() {
    document.getElementById('role').style.display = 'none';
}

function setRoleAdmin() {
    var value = 'ADMIN';
    document.getElementById('btnRole').setAttribute('value', value);
    displayNoneRole();
    displayTableBlock();
}

function setRoleUser() {
    var value = 'USER';
    document.getElementById('btnRole').setAttribute('value', value);
    displayNoneRole();
    displayTableBlock();
}

function setRoleCoach() {
    var value = 'COACH';
    document.getElementById('btnRole').setAttribute('value', value);
    displayNoneRole();
    displayTableBlock();
}
var number;

function setSelectMail(elem){
    number = elem.rowIndex;
    var value = document.getElementById('myTable').rows[number].cells[1].innerHTML;
    document.getElementById('btn').setAttribute('value', value);
}

function displayTableBlock() {
    document.getElementById('change').style.display = 'block';
}

function displayTableNone() {
    document.getElementById('change').style.display = 'none';
}

var element;
function changeStyle(elem) {
    element = elem;
    elem.setAttribute('class', 'checkMarkChange');
}

function removeChangeStyle(){
    element.setAttribute('class','checkMark');
}



