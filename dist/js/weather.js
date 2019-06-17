function dateTime() {
    document.getElementById("datetimeintro").innerHTML = ("Today's date and time is:");
    var dt = new Date();
    document.getElementById("datetime").innerHTML = (("0" + (dt.getMonth() + 1)).slice(-2)) + "/" + (("0" + dt.getDate()).slice(-2)) +
        "/" + (dt.getFullYear()) + " " + (("0" + dt.getHours()).slice(-2)) + ":" + (("0" + dt.getMinutes()).slice(-2));
}

function weatherPopUp() {
    var popup = document.getElementById("weatherPopup");
    popup.classList.toggle("show");
}

function timerPopup()
{
    var popup = document.getElementById("timerPopup");
    popup.classList.toggle("show");
}

function stopwatchPopup()
{
    var popup = document.getElementById("stopwatchPopup");
    popup.classList.toggle("show");
}

function weather() {
    var location = document.getElementById("location");
    var apiKey = "594bcef893696afcfc296f1d697c7cb5";
    var url = "https://api.darksky.net/forecast/";

    navigator.geolocation.getCurrentPosition(success, error);

    function success(position) {
        latitude = position.coords.latitude;
        longitude = position.coords.longitude;

        location.innerHTML =
            "Latitude is " + latitude + "° Longitude is " + longitude + "°";

        $.getJSON(
            url + apiKey + "/" + latitude + "," + longitude + "?callback=?",
            function (data) {
                $("#temp").html(data.currently.temperature + "° F");
                $("#minutely").html(data.minutely.summary);
                $("#death").html(data.currently.nearestStormDistance + " miles to the nearest death machine storm thingy");
                $("#wind").html("The wind speed is " + data.currently.windSpeed + " mph");
                $("#rain").html(data.currently.precipProbability + " % chance of precipitation");
                $("#seetime").html("You will be able to see " + data.currently.visibility + " miles around you");
            }
        )
    }

    function error() {
        location.innerHTML = "Wow. You guys literally hit this button over a 1000 times in one day. Great job, hope you're all proud of yourselves. I mean this seriously. \n\nEither one of you had to be really commited to pressing this button a bunch of times, or wrote a neat piece of code to do it instead, or you all banded together and bonded over breaking the weather funciton. I can respect that. Kudos \n -Arya, one of the dudes who made this. \nPS Hiiii - ann";
    }   

    location.innerHTML = "Locating...";

}

function post() {
    const loc = window.location;
    const Url = loc.protocol + '//' + loc.hostname + ':' + loc.port + '/post/';
    const weatherData = {
        temp: document.getElementById("temp").innerHTML,
        minutely: document.getElementById("minutely").innerHTML,
        death: document.getElementById("death").innerHTML,
        wind: document.getElementById("wind").innerHTML,
        rain: document.getElementById("rain").innerHTML,
        seetime: document.getElementById("seetime").innerHTML
    };

    test = fetch(Url, {
        method: 'POST',
        mode: 'same-origin',
        headers: {
            'Accept': 'text/plain',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(weatherData)
    }).then((result) => {
        console.log(result);
        return result.text();
    }).then(data => {
        console.log(data);
        return data;
})
    /*
        $.post(Url, dada, function(dada, status) {
            console.log('${dada} and status is ${status}')
        }); */
}

function postWeatherDateTime() {
    dateTime();
    weather();
}