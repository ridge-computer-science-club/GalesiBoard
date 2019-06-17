clicker.addEventListener("click", function countdown()
        {
            var x;
            var y;
            var time=prompt("Please enter the time You wish to start at","00:00.000");
            if (time!=null)
            {
                x=(parseInt(time.substring(0,2), 10) * 60000) + (parseInt(time.substring(3,5),10) * 1000) + (parseInt(time.substring(6), 10));
                function timeFormatter(timeInMilliseconds)
                    {
                    var time = new Date(timeInMilliseconds);
                    var minutes = time.getMinutes().toString();
                    var seconds = time.getSeconds().toString();
                    var milliseconds = time.getMilliseconds().toString();
                    if(minutes.length <2) {
                        minutes = "0" + minutes;
                    }
                    if(seconds.length <2) {
                        seconds = "0" + seconds;
                    }
                
                    while(milliseconds.length <3) {
                        milliseconds = "0" + milliseconds;
                    }
                    return minutes + " : " + seconds + " . " + milliseconds;
                    }
                y = timeFormatter(x);
                document.getElementById("stopWatch").innerHTML=y;
                document.getElementById("stopWatch").innerHTML="Test";
                function start(z)
                {
                    var time = z;
                    while(time>0)
                        {
                        time-=1;
                        var formattedTime = timeFormatter(time);
                        document.getElementById("stopWatch").innerHTML=formattedTime;
                        }
                }
                start(x);
                    //document.getElementById("stopWatch").innerHTML="Time's Up!";
            }
                
        }
);