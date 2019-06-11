var timer = document.getElementById("timer");
var toggleBtn = document.getElementById("toggle");
var resetBtn = document.getElementById("reset");
var watch = new StopWatch(timer);

toggleBtn.addEventListener("click", function() {
<<<<<<< HEAD
    if(watch.isOn){
        watch.stop();
    } else {
        watch.start();
    }
});

resetBtn.addEventListener("clicK", function () {
    watch.reset();
})
=======
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
>>>>>>> a0de5b82feb0092393052c5d4f0bafb59d7b05ee
