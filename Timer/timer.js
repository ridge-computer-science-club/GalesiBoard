var timer = document.getElementById("timer");
var toggleBtn = document.getElementById("toggle");
var resetBtn = document.getElementById("reset");
var watch = new StopWatch(timer);

toggleBtn.addEventListener("click", function() {
   if(watch.isOn){
       watch.stop();
       document.getElementById("toggle").innerHTML = "Start";
   } else {
       watch.start();
       document.getElementById("toggle").innerHTML = "Stop";
   }
});

resetBtn.addEventListener("click", function () {
   watch.reset();
})
