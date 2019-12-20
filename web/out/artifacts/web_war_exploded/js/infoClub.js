var total_pics_num = 8;
var interval = 5000;
var time_out = 1;
var i = 0;
var timeout;
var opacity = 100;

function openLanguageMenu() {
    document.getElementById("language").classList.toggle('active');
}

function fade_to_next() {
    opacity--;
    var k = i + 1;
    var image_now = 'image' + i;
    if (i === total_pics_num) k = 1;
    var image_next = 'image' + k;
    document.getElementById(image_now).style.opacity = opacity/100;
    document.getElementById(image_now).style.filter = 'alpha(opacity='+ opacity +')';
    document.getElementById(image_next).style.opacity = (100-opacity)/100;
    document.getElementById(image_next).style.filter = 'alpha(opacity='+ (100-opacity) +')';
    timeout = setTimeout("fade_to_next()",time_out);
    if (opacity===1) {
        opacity = 100;
        clearTimeout(timeout);
    }
}
setInterval (
    function() {
        i++;
        if (i > total_pics_num) i=1;
        fade_to_next();
    }, interval
);

function openMenu() {
    document.getElementById("sidebar").classList.toggle('active');
    document.getElementById("info").classList.toggle('activeInfo');
}