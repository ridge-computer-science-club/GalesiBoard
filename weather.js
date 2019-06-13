
function dateTime() {
    document.getElementById("text").innerHTML = ("Today's date and time is:")
    var dt = new Date();
    document.getElementById("datetime").innerHTML = (("0" + (dt.getMonth() + 1)).slice(-2)) + "/" + (("0" + dt.getDate()).slice(-2))
        + "/" + (dt.getFullYear()) + " " + (("0" + dt.getHours()).slice(-2)) + ":" + (("0" + dt.getMinutes()).slice(-2));
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
                $("#seetime").html("You will be able to see " + data.currently.visibility + "miles around you");
            }
        )
    }

    function error() {
        location.innerHTML = "Unable to retrieve your location";
    }

    location.innerHTML = "Locating...";

}

function everything() {
    dateTime();
    weather();
}