var input = document.querySelector('#input_text');
var main = document.querySelector('#name');
var temp = document.querySelector('.temp');
var desc = document.querySelector('.desc');
var clouds = document.querySelector('.clouds');
var speed= document.querySelector('.speed');
var hum= document.querySelector('.hum');
var button= document.querySelector('.submit');



button.addEventListener('click', function(name){
    fetch('https://api.openweathermap.org/data/2.5/weather?q='+input.value+'&appid=7571364845f63b38029e8c82ceda6c8e&units=metric')
        .then(response => response.json())
        .then(data => {
            var tempValue = data['main']['temp'];
            var nameValue = data['name'];
            var descValue = data['weather'][0]['description'];
            var windValue=data['wind']['speed'];

            main.innerHTML = nameValue;
            desc.innerHTML =descValue +"!";
            temp.innerHTML =tempValue +" °C";
            speed.innerHTML =windValue +" km/h";

            input.value ="";


        })

        .catch(err => alert("Wrong city name!"));
})
