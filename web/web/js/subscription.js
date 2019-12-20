function displayBlock() {
    document.getElementById('id01').style.display = 'block';
}

function displayNone() {
    document.getElementById('id01').style.display = 'none';
}

function tableBlock() {
    document.getElementById('takeTable').style.display = 'block';
}

function displayConfirmBlock() {
    document.getElementById('id02').style.display = 'block';
}

function displayConfirmNone() {
    changeServiceStyle();
    document.getElementById('id02').style.display = 'none';
}

function changeServiceStyle() {
    document.getElementById('11').setAttribute('class', 'line1');
    document.getElementById('12').setAttribute('class', 'line2');
    document.getElementById('13').setAttribute('class', 'line3');
    document.getElementById('14').setAttribute('class', 'line4');
    document.getElementById('21').setAttribute('class', 'line1');
    document.getElementById('22').setAttribute('class', 'line2');
    document.getElementById('23').setAttribute('class', 'line3');
    document.getElementById('24').setAttribute('class', 'line4');
    document.getElementById('31').setAttribute('class', 'line1');
    document.getElementById('32').setAttribute('class', 'line2');
    document.getElementById('33').setAttribute('class', 'line3');
    document.getElementById('34').setAttribute('class', 'line4');
    document.getElementById('41').setAttribute('class', 'line1');
    document.getElementById('42').setAttribute('class', 'line2');
    document.getElementById('43').setAttribute('class', 'line3');
    document.getElementById('44').setAttribute('class', 'line4');
    document.getElementById('51').setAttribute('class', 'line1');
    document.getElementById('52').setAttribute('class', 'line2');
    document.getElementById('53').setAttribute('class', 'line3');
    document.getElementById('54').setAttribute('class', 'line4');
    document.getElementById('55').setAttribute('class', 'line5');
    document.getElementById('61').setAttribute('class', 'line1');
    document.getElementById('62').setAttribute('class', 'line2');
    document.getElementById('63').setAttribute('class', 'line3');
    document.getElementById('64').setAttribute('class', 'line4');
    document.getElementById('65').setAttribute('class', 'line5');
}

function tableNone() {
    changeServiceStyle();
    document.getElementById('takeTable').style.display = 'none';
}

function change1(){
    displayConfirmBlock();
    document.getElementById('duration').setAttribute('value', '12');
    document.getElementById('cost').setAttribute('value', '468');
    document.getElementById('selectSub').setAttribute('value', 'MONTH12');
    document.getElementById('11').setAttribute('class', 'change1');
    document.getElementById('12').setAttribute('class', 'change2');
    document.getElementById('13').setAttribute('class', 'change3');
    document.getElementById('14').setAttribute('class', 'change4');
}

function change2(){
    displayConfirmBlock();
    document.getElementById('duration').setAttribute('value', '6');
    document.getElementById('cost').setAttribute('value', '294');
    document.getElementById('selectSub').setAttribute('value', 'MONTH6');
    document.getElementById('21').setAttribute('class', 'change1');
    document.getElementById('22').setAttribute('class', 'change2');
    document.getElementById('23').setAttribute('class', 'change3');
    document.getElementById('24').setAttribute('class', 'change4');
}

function change3(){
    displayConfirmBlock();
    document.getElementById('duration').setAttribute('value', '3');
    document.getElementById('cost').setAttribute('value', '160');
    document.getElementById('selectSub').setAttribute('value', 'MONTH3');
    document.getElementById('31').setAttribute('class', 'change1');
    document.getElementById('32').setAttribute('class', 'change2');
    document.getElementById('33').setAttribute('class', 'change3');
    document.getElementById('34').setAttribute('class', 'change4');
}

function change4(){
    displayConfirmBlock();
    document.getElementById('duration').setAttribute('value', '1');
    document.getElementById('cost').setAttribute('value', '80');
    document.getElementById('selectSub').setAttribute('value', 'MONTH');
    document.getElementById('41').setAttribute('class', 'change1');
    document.getElementById('42').setAttribute('class', 'change2');
    document.getElementById('43').setAttribute('class', 'change3');
    document.getElementById('44').setAttribute('class', 'change4');
}

function change5(){
    tableBlock();
    document.getElementById('durationCoach').setAttribute('value', '3');
    document.getElementById('costCoach').setAttribute('value', '300');
    document.getElementById('selectSubCoach').setAttribute('value', 'MONTH3_WITH_COACH');
    document.getElementById('51').setAttribute('class', 'change1');
    document.getElementById('52').setAttribute('class', 'change2');
    document.getElementById('53').setAttribute('class', 'change3');
    document.getElementById('54').setAttribute('class', 'change4');
    document.getElementById('55').setAttribute('class', 'change5');
}

function change6(){
    tableBlock();
    document.getElementById('durationCoach').setAttribute('value', '1');
    document.getElementById('costCoach').setAttribute('value', '150');
    document.getElementById('selectSubCoach').setAttribute('value', 'MONTH_WITH_COACH');
    document.getElementById('61').setAttribute('class', 'change1');
    document.getElementById('62').setAttribute('class', 'change2');
    document.getElementById('63').setAttribute('class', 'change3');
    document.getElementById('64').setAttribute('class', 'change4');
    document.getElementById('65').setAttribute('class', 'change5');
}

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

function myFunction2() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("myInput2");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable2");
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

var number;

function setSelectMail(elem){
    number = elem.rowIndex;
    var value = document.getElementById('myTable').rows[number].cells[1].innerHTML;
    document.getElementById('btn').setAttribute('value', value);
}

function setSelectMail2(elem){
    number = elem.rowIndex;
    var value = document.getElementById('myTable2').rows[number].cells[1].innerHTML;
    document.getElementById('btn2').setAttribute('value', value);
}

function displayTableBlock() {
    document.getElementById('cancel').setAttribute('class', 'newCloseTable');
    document.getElementById('change').style.display = 'block';
}

function displayTableBlock2() {
    document.getElementById('change2').style.display = 'block';
}

function displayTableNone() {
    document.getElementById('cancel').setAttribute('class', 'closeTable');
    document.getElementById('change').style.display = 'none';
}

function displayTableNone2() {
    document.getElementById('change2').style.display = 'none';
}

var element;
function changeStyle(elem) {
    element = elem;
    elem.setAttribute('class', 'checkMarkChange');
}

function changeStyle2(elem) {
    element = elem;
    elem.setAttribute('class', 'checkMarkChange');
}

function removeChangeStyle(){
    element.setAttribute('class','checkMark');
}

function removeChangeStyle2(){
    element.setAttribute('class','checkMark');
}

function checkValue(val) {
    var valueMissing;
    var patternMismatch;
    var tooLong;
    var tooShort;
    if(document.getElementById("customValidity").value === 'Menu'){
        valueMissing = 'Please, input your replenishment amount';
        patternMismatch = 'Use only digits. The first digit cannot be 0. ';
        tooLong = ' Max available length: 5 symbols.';
        tooShort = ' Min available length: 1 symbol.';
    }else{
        valueMissing = 'Пожалуйста, введите сумму пополнения';
        patternMismatch = 'Используйте только цифры. Первая цифра не может быть 0. ';
        tooLong = ' Макс. доступная длина: 5 символов.';
        tooShort = ' Мин. доступная длина: 1 символ';
    }
    var text = '';
    if(val.validity.valueMissing){
        val.setCustomValidity(valueMissing);
    }else {
        if (val.validity.patternMismatch) {
            text = text + patternMismatch;
        }
        if (val.validity.tooLong) {
            text = text + tooLong;
        }
        if (val.validity.tooShort) {
            text = text + tooShort;
        }
        val.setCustomValidity(text);
    }
    return val.reportValidity();
}