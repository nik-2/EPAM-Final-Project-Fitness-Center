var numberCoach = 6;
var coach = 1;
var coachImg = 1;
var flag = 0;

function openLanguageMenu() {
    document.getElementById("language").classList.toggle('active');
}

function openMenu() {
    document.getElementById("sidebar").classList.toggle('active');
    if(flag % 2 === 0) {
        document.getElementById("coach" + coach).style.opacity = 0;
    }
    else{
        document.getElementById("coach" + coach).style.opacity = 1;
    }
    document.getElementById("btn-left").classList.toggle('activeBtn');
    flag++;
}

function rightCoach() {
    var coachNow = 'coach' + coach;
    var coachImgNow = 'image' + coachImg;
    document.getElementById(coachNow.toString()).style.opacity=0;
    document.getElementById(coachImgNow.toString()).style.opacity=0;
    if(coach === numberCoach){
        coach = 0;
        coachImg = 0;
    }
    coach = coach + 1;
    coachImg = coachImg + 1;
    var coachNext = 'coach' + coach;
    var coachImgNext = 'image' + coachImg;
    document.getElementById(coachImgNext.toString()).style.opacity=1;
    if(flag % 2 === 1) {
        document.getElementById(coachNext).style.opacity = 0;
    }
    else{
        document.getElementById(coachNext).style.opacity = 1;
    }
}

function leftCoach() {
    var coachNow = 'coach' + coach;
    var coachImgNow = 'image' + coachImg;
    document.getElementById(coachNow.toString()).style.opacity=0;
    document.getElementById(coachImgNow.toString()).style.opacity=0;
    if(coach === 1){
        coach = numberCoach + 1;
        coachImg = numberCoach + 1;
    }
    coach = coach - 1;
    coachImg = coachImg - 1;
    var coachNext = 'coach' + coach;
    var coachImgNext = 'image' + coachImg;
    document.getElementById(coachImgNext.toString()).style.opacity=1;
    if(flag % 2 === 1) {
        document.getElementById(coachNext).style.opacity = 0;
    }
    else{
        document.getElementById(coachNext).style.opacity = 1;
    }
}