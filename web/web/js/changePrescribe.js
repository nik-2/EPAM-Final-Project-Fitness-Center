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

function setSelectMail(elem){
    number = elem.rowIndex;
    var value = document.getElementById('myTable').rows[number].cells[1].innerHTML;
    document.getElementById('mail').setAttribute('value', value);
    document.getElementById('mail1').setAttribute('value', value);
    document.getElementById('diet').value = document.getElementById('myTable').rows[number].cells[6].innerHTML;
}

function setSelectMailCoach(elem){
    number = elem.rowIndex;
    var value = document.getElementById('myTable').rows[number].cells[1].innerHTML;
    document.getElementById('mail').setAttribute('value', value);
    document.getElementById('mail1').setAttribute('value', value);
    document.getElementById('exercise').value = document.getElementById('myTable').rows[number].cells[6].innerHTML;
}

function setNewDiet() {
    var value = document.getElementById('diet').value;
    document.getElementById('newDiet').setAttribute('value', value);
}

function setNewExercise() {
    var value = document.getElementById('exercise').value;
    document.getElementById('newExercise').setAttribute('value', value);
}

var flag = true;

function checkValidation(current) {
    checkDietAndExer(current);
    return flag === true;
}

function checkDietAndExer(current)
{
    var patternMismatch;
    var tooLong;
    var pattern = new RegExp('^[\\dA-Za-zА-Яа-я\\s,.!?;:()-]*$');
    flag = true;
    if(document.getElementById("customValidity").value === 'Menu'){
        patternMismatch = "Please, use A-Z А-Я a-z а-я and punctuation.";
        tooLong = ' Max available length: 600 symbols.';
    }else{
        patternMismatch = "Пожалуйста, используйте A-Z А-Я a-z а-я и знаки препинания.";
        tooLong = ' Макс. доступная длина: 600 символов.';
    }
    var text = '';
    if (!pattern.test(current.value)) {
        text = text + patternMismatch;
        flag = false;
    }
    if (current.validity.tooLong) {
        text = text + tooLong;
        flag = false;

    }
    current.setCustomValidity(text);
    return current.reportValidity();
}