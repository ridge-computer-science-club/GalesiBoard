function StopWatch(element) {
    var time = 0;
    var interval;
    var offset;
    function update() {
        time+=timePassed();
        var formattedTime = timeFormatter(time);
        element.textContent = formattedTime;
    }
    function update2()
    {
        var formattedTime = timeFormatter(time);
        element.textContent = formattedTime;
    }
    function timePassed() {
        var now = Date.now();
        var timePassed = now - offset;
        offset = now;
        return timePassed;
    }
    function timeFormatter(timeInMilliseconds) {
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

    this.isOn = false;
    this.start = function() {
        if(!this.isOn){
            interval = setInterval(update, 10);
            offset = Date.now();
            this.isOn = true;
        }
    };
    this.stop = function() {
        if(this.isOn){
            clearInterval(interval);
            interval = null;
            this.isOn = false;
        }
    };
    this.reset = function() {
        time = 0;
    };
}

