// document.addEventListener('DOMContentLoaded', function() {
//     var strChange = 'blockChange';
//     if(document.getElementById("changePassword").value === strConfirm){
//         changePassword();
//     }
// }, false);

document.addEventListener('DOMContentLoaded', function() {
    var str = 'block';

    if(document.getElementById("checkConfirm").value === str){
        changePassword()
    }
}, false);

function changeData() {
    document.getElementById('changeBtn').style.display = 'none';
    document.getElementById('responseBtn').style.display = 'block';
    document.getElementById('name').setAttribute('class','change');
    document.getElementById('surname').setAttribute('class','change');
    document.getElementById('name').removeAttribute('readonly');
    document.getElementById('surname').removeAttribute('readonly');
    if(document.getElementById('bankCard') !== null){
        document.getElementById('bankCard').setAttribute('class','change');
        document.getElementById('bankCard').removeAttribute('readonly');
    }
}

function changeBankCard() {
    document.getElementById('changeBtn').style.display = 'none';
    document.getElementById('responseBtn').style.display = 'block';
    if(document.getElementById('bankCard') !== null){
        document.getElementById('bankCard').setAttribute('class','change');
        document.getElementById('bankCard').removeAttribute('readonly');
    }
}

function changePassword() {
    document.getElementById('changeBtn').style.display = 'none';
    document.getElementById('id03').style.display='block';
}

function changePasswordStop() {
    document.getElementById('changeBtn').style.display = 'block';
    document.getElementById('id03').style.display = 'none';
    var url = window.location.href;
    if(getUrl().match('errorChangePassword=.') || getUrl().match('stateCheck=.')) {
        if(getUrl().match('errorChangePassword=.') && getUrl().match('stateCheck=.')) {
            url = setGetParameter(setGetParameter(window.location.href, 'stateCheck', ''), 'errorChangePassword', '');
        }
        if(!getUrl().match('errorChangePassword=')){
            url = setGetParameter(window.location.href, 'stateCheck', '');
        }
        if(!getUrl().match('stateCheck=')){
            url = setGetParameter(window.location.href, 'errorChangePassword', '');
        }
        var hash = location.hash;
        window.location.href = url + hash;
    }
}

function getUrl(){
    var url = window.location.href;
    var hash = location.hash;
    url = url.replace(hash, '');
    return url;
}

function setGetParameter(url, paramName, paramValue)
{
    //var url = window.location.href;
    var hash = location.hash;
    url = url.replace(hash, '');
    if (url.indexOf(paramName + "=") >= 0)
    {
        var prefix = url.substring(0, url.indexOf(paramName + "="));
        var suffix = url.substring(url.indexOf(paramName + "="));
        suffix = suffix.substring(suffix.indexOf("=") + 1);
        suffix = (suffix.indexOf("&") >= 0) ? suffix.substring(suffix.indexOf("&")) : "";
        if(paramValue !== ''){
            url = prefix + paramName + "=" + paramValue + suffix;
        }
        if(paramValue === '' && suffix === ''){
            url = prefix.substring(0, prefix.length - 1);
        }
        if(paramValue === '' && suffix !== '' && (prefix.indexOf('?') === (prefix.length - 1))){
            url = prefix + suffix.substring(1, suffix.length);
        }
    }
    else
    {
        if (url.indexOf("?") < 0)
            url += "?" + paramName + "=" + paramValue;
        else
            url += "&" + paramName + "=" + paramValue;
    }
    return url;
    // window.location.href = url + hash;
}

function setNewName() {
    var value = document.getElementById('name').value;
    document.getElementById('newName').setAttribute('value', value);
}

function setNewSurname() {
    var value = document.getElementById('surname').value;
    document.getElementById('newSurname').setAttribute('value', value);
}

function setNewBankCard() {
    var value = document.getElementById('bankCard').value;
    if(value === null){
        value = '';
    }
    document.getElementById('newBankCard').setAttribute('value', value);
}

function checkName(name) {
    var valueMissing;
    var patternMismatch;
    var tooLong;
    if(document.getElementById("customValidity").value === 'Menu'){
        valueMissing = 'Please, input your name';
        patternMismatch = 'Enter your name in the form: "Name" or "Name-Name".';
        tooLong = ' Max available length: 20 symbols.';
    }else{
        valueMissing = 'Пожалуйста, введите своё имя';
        patternMismatch = 'Введите своё имя в виде "Name" или "Name-Name".';
        tooLong = ' Макс. доступная длина: 20 символов';
    }
    var text = '';
    if(name.validity.valueMissing){
        name.setCustomValidity(valueMissing);
    }else {
        if (name.validity.patternMismatch) {
            text = text + patternMismatch;
        }
        if (name.validity.tooLong) {
            text = text + tooLong;
        }
        name.setCustomValidity(text);
    }
    return name.reportValidity();
}

function checkSurname(surname)
{
    var valueMissing;
    var patternMismatch;
    var tooLong;
    if(document.getElementById("customValidity").value === 'Menu'){        valueMissing = 'Please, input your surname';
        patternMismatch = 'Enter your name in the form: "Surname" or "Surname-Surname".';
        tooLong = ' Max available length: 30 symbols.';
    }else{
        valueMissing = 'Пожалуйста, введите свою фамилию';
        patternMismatch = 'Введите свою фамилию в виде "Surname" или "Surname-Surname".';
        tooLong = ' Макс. доступная длина: 30 символов.';
    }
    var text = '';
    if(surname.validity.valueMissing){
        surname.setCustomValidity(valueMissing);
    }else {
        if (surname.validity.patternMismatch) {
            text = text + patternMismatch;
        }
        if (surname.validity.tooLong) {
            text = text + tooLong;
        }
        surname.setCustomValidity(text);
    }
    return surname.reportValidity();
}

function checkPassword(password)
{
    var valueMissing;
    var patternMismatch;
    var tooLong;
    var tooShort;
    if(document.getElementById("customValidity").value === 'Menu'){
        valueMissing = 'Please, input password';
        patternMismatch = 'Use: "A-Z a-z 0-9 _".';
        tooLong = ' Max available length: 15 symbols.';
        tooShort = ' Min available length 4 symbols.';
    }else{
        valueMissing = 'Пожалуйста, введите пароль';
        patternMismatch = 'Используйте: "A-Z a-z 0-9 _".';
        tooLong = ' Макс. доступная длина: 15 символов.';
        tooShort = ' Мин. доступная длина: 4 символа.';
    }
    var text = '';
    if(password.validity.valueMissing){
        password.setCustomValidity(valueMissing);
    }else {
        if (password.validity.patternMismatch) {
            text = text + patternMismatch;
        }
        if (password.validity.tooLong) {
            text = text + tooLong;
        }
        if (password.validity.tooShort) {
            text = text + tooShort;
        }
        password.setCustomValidity(text);
    }
    return password.reportValidity();
}

function checkRepeatPassword(password1, password2)
{
    var valueMissing;
    var patternMismatch;
    var tooLong;
    var tooShort;
    var match;
    if(document.getElementById("customValidity").value === 'Menu'){        valueMissing = 'Please, input password';
        patternMismatch = 'Use: "A-Z a-z 0-9 _".';
        tooLong = ' Max available length: 15 symbols.';
        tooShort = ' Min available length 4 symbols.';
        match = ' Passwords do not match.';
    }else{
        valueMissing = 'Пожалуйста, введите пароль';
        patternMismatch = 'Используйте: "A-Z a-z 0-9 _".';
        tooLong = ' Макс. доступная длина: 15 символов.';
        tooShort = ' Мин. доступная длина: 4 символа.';
        match = ' Пароли не совпадают.';
    }
    var text = '';
    if(password2.validity.valueMissing){
        password2.setCustomValidity(valueMissing);
    }else {
        if (password2.validity.patternMismatch) {
            text = text + patternMismatch;
        }
        if (password2.validity.tooLong) {
            text = text + tooLong;
        }
        if (password2.validity.tooShort) {
            text = text + tooShort;
        }
        if(password1.value !== password2.value){
            text = text + match;
        }
        password2.setCustomValidity(text);
    }
    return password2.reportValidity();
}

function checkBankCard(bankCard)
{
    var valueMissing;
    var patternMismatch;
    var tooLong;
    if(document.getElementById("customValidity").value === 'Menu'){
        valueMissing = 'Please, input your bank card id';
        patternMismatch = 'Enter your bank card id in the form: "XXXX_XXXX_XXXX_XXXX".';
        tooLong = ' Max available length: 19 symbols.';
    }else{
        valueMissing = 'Пожалуйста, введите номер своей банковской карточки';
        patternMismatch = 'Введите номер в виде "XXXX_XXXX_XXXX_XXXX".';
        tooLong = ' Макс. доступная длина: 19 символов.';
    }
    var text = '';
    if(bankCard.validity.valueMissing){
        bankCard.setCustomValidity(valueMissing);
    }else {
        if (bankCard.validity.patternMismatch) {
            text = text + patternMismatch;
        }
        if (bankCard.validity.tooLong) {
            text = text + tooLong;
        }
        bankCard.setCustomValidity(text);
    }
    return bankCard.reportValidity();
}