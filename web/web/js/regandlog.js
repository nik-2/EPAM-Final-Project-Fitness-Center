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

document.addEventListener('DOMContentLoaded', function() {
    var strIn = 'block';
    var strUp = 'blockUp';
    var strConfirm = 'blockConfirm';
    if(document.getElementById("checkBlock").value === strIn) {
        displayBlockLogin();
    }
    if(document.getElementById("checkBlock").value === strUp){
        displayBlock()
    }
    if(document.getElementById("checkConfirm").value === strConfirm){
        displayConfirmBlock()
    }
}, false);

function displayBlock() {
    document.getElementById('id01').style.display='block';
}

function displayNone() {
    var url = window.location.href;
    document.getElementById('id01').style.display='none';
    if(getUrl().match('errorRegistrationMessage=.') || getUrl().match('state=.')) {
        if(getUrl().match('errorRegistrationMessage=.') && getUrl().match('state=.')) {
            url = setGetParameter(setGetParameter(window.location.href, 'state', ''), 'errorRegistrationMessage', '');
        }
        if(!getUrl().match('errorRegistrationMessage=')){
            url = setGetParameter(window.location.href, 'state', '');
        }
        if(!getUrl().match('state=')){
            url = setGetParameter(window.location.href, 'errorRegistrationMessage', '');
        }
        var hash = location.hash;
        window.location.href = url + hash;
    }
}

function displayBlockLogin() {
    document.getElementById('id02').style.display='block';
}

function displayNoneLogin() {
    var url = window.location.href;
    document.getElementById('id02').style.display='none';
    if(getUrl().match('errorLoginPassMessage=.') || getUrl().match('state=.')) {
        if(getUrl().match('errorLoginPassMessage=.') && getUrl().match('state=.')) {
            url = setGetParameter(setGetParameter(window.location.href, 'state', ''), 'errorLoginPassMessage', '');
        }
        if(!getUrl().match('errorLoginPassMessage=')){
            url = setGetParameter(window.location.href, 'state', '');
        }
        if(!getUrl().match('state=')){
            url = setGetParameter(window.location.href, 'errorLoginPassMessage', '');
        }
        var hash = location.hash;
        window.location.href = url + hash;
    }
}

function displayConfirmBlock() {
    document.getElementById('id03').style.display='block';
}

function displayConfirmNone() {
    var url = window.location.href;
    document.getElementById('id03').style.display='none';
    if(getUrl().match('stateCheck=') || getUrl().match('errorConfirmMessage')) {
        if(getUrl().match('errorConfirmPassMessage=') && getUrl().match('stateCheck=')) {
            url = setGetParameter(setGetParameter(window.location.href, 'stateCheck', ''), 'errorConfirmMessage', '');
        }
        if(!getUrl().match('errorConfirmMessage=')){
            url = setGetParameter(window.location.href, 'stateCheck', '');
        }
        if(!getUrl().match('stateCheck=')){
            url = setGetParameter(window.location.href, 'errorConfirmMessage', '');
        }
        var hash = location.hash;
        window.location.href = url + hash;
    }
}

function checkEmail(email)
{
    var valueMissing;
    var patternMismatch;
    var tooLong;
    if(document.getElementById("customValidity").value === 'Menu'){
        valueMissing = 'Please, input your email'.innerHTML;
        patternMismatch = 'Use: "a-z 0-9 _ ." and end with the standard form of mail.';
        tooLong = ' Max available length: 30 symbols.';
    }else{
        valueMissing = 'Пожалуйста, введите свой email';
        patternMismatch = 'Используйте: "a-z 0-9 _ ." и закончите стандартной формой почты.';
        tooLong = ' Макс. доступная длина: 30 символов.';
    }
    var text = '';
    if(email.validity.valueMissing){
        email.setCustomValidity(valueMissing);
    }else {
        if (email.validity.patternMismatch) {
            text = text + patternMismatch;
        }
        if (email.validity.tooLong) {
            text = text + tooLong;
        }
        email.setCustomValidity(text);
    }
    return email.reportValidity();
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
    if(document.getElementById("customValidity").value === 'Menu'){
        valueMissing = 'Please, input password';
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
