// document.getElementById('diet').onkeyup(validateTextarea);
//
// function validateTextarea() {
//     var errorMsg;
//     if(document.getElementById("customValidity").value === 'Menu'){
//         errorMsg = "Please, use A-Z А-Я a-z а-я - and punctuation.";
//     }else{
//         errorMsg = "Пожалуйста, используйте A-Z А-Я a-z а-я - и знаки препинания.";
//     }
//     var textarea = this;
//     var pattern = new RegExp('^' + textarea.getAttribute('pattern') + '$');
//     var hasError = !this.match(pattern);
//     if (textarea.setCustomValidity) {
//         textarea.setCustomValidity(hasError ? errorMsg : '');
//     } else {
//         // Not supported by the browser, fallback to manual error display...
//         textarea.toggleAttribute('error', !!hasError);
//         textarea.toggleAttribute('ok', !hasError);
//         if (hasError) {
//             textarea.setAttribute('title', errorMsg);
//         } else {
//             textarea.removeAttribute('title');
//         }
//     }
//     return !hasError;
// }

function changeDiet() {
    document.getElementById('changeBtn').style.display = 'none';
    document.getElementById('responseBtn').style.display = 'block';
    document.getElementById('diet').setAttribute('class','change');
    document.getElementById('diet').removeAttribute('readonly');
}

function changeExercise() {
    document.getElementById('changeBtn').style.display = 'none';
    document.getElementById('responseBtn').style.display = 'block';
    document.getElementById('exercise').setAttribute('class','change');
    document.getElementById('exercise').removeAttribute('readonly');
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