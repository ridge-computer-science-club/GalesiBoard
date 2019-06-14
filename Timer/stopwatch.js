<<<<<<< HEAD
function Stopwatch() {
=======
function StopWatch(element) {
>>>>>>> a0de5b82feb0092393052c5d4f0bafb59d7b05ee
    var time = 0;
    var interval;
    var offset;
    function update() {
        time+=timePassed();
        var formattedTime = timeFormatter(time);
<<<<<<< HEAD
        console.log(formattedTime)
        Element.textContent = formattedTime;
=======
        element.textContent = formattedTime;
    }
    function update2()
    {
        var formattedTime = timeFormatter(time);
        element.textContent = formattedTime;
>>>>>>> a0de5b82feb0092393052c5d4f0bafb59d7b05ee
    }
    function timePassed() {
        var now = Date.now();
        var timePassed = now - offset;
        offset = now;
        return timePassed;
    }
<<<<<<< HEAD
    function timeForamtter(timeInMilliseconds) {
        var time = new Date(timeInMilliseconds);
        var minutes = time.getMinutes().toString();
        var seconds = time.getSeconds().toString();
        var milliseconds = time.milliseconds().toString();
=======
    function timeFormatter(timeInMilliseconds) {
        var time = new Date(timeInMilliseconds);
        var minutes = time.getMinutes().toString();
        var seconds = time.getSeconds().toString();
        var milliseconds = time.getMilliseconds().toString();
>>>>>>> a0de5b82feb0092393052c5d4f0bafb59d7b05ee
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
<<<<<<< HEAD

=======
 
>>>>>>> a0de5b82feb0092393052c5d4f0bafb59d7b05ee
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
<<<<<<< HEAD
        update();
    };
}

=======
        update2();
    };
 }
 
>>>>>>> a0de5b82feb0092393052c5d4f0bafb59d7b05ee
