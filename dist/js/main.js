AOS.init({duration:800,easing:"slide"}),function(o){"use strict";o(window).stellar({responsive:!0,parallaxBackgrounds:!0,parallaxElements:!0,horizontalScrolling:!1,hideDistantElements:!1,scrollProperty:"scroll"});o(".js-fullheight").css("height",o(window).height()),o(window).resize(function(){o(".js-fullheight").css("height",o(window).height())});setTimeout(function(){0<o("#ftco-loader").length&&o("#ftco-loader").removeClass("show")},1),o.Scrollax();o(".carousel-testimony").owlCarousel({center:!0,loop:!0,items:1,margin:30,stagePadding:0,nav:!0,navText:['<span class="ion-ios-arrow-back">','<span class="ion-ios-arrow-forward">'],responsive:{0:{items:1},600:{items:2},1e3:{items:3}}}),o("nav .dropdown").hover(function(){var a=o(this);a.addClass("show"),a.find("> a").attr("aria-expanded",!0),a.find(".dropdown-menu").addClass("show")},function(){var a=o(this);a.removeClass("show"),a.find("> a").attr("aria-expanded",!1),a.find(".dropdown-menu").removeClass("show")}),o("#brba").click(function(){window.open("https://www.ridgebusiness.org/","_blank")}),o("#computersharp").click(function(){window.open("https://computersharp.com/","_blank")}),o("#lgs").click(function(){window.open("https://www.lgsinnovations.com/","_blank")}),o("#linode").click(function(){window.open("https://www.linode.com/","_blank")}),o("#intellect").click(function(){window.open("https://www.intellectseec.com/","_blank")}),o("#balsamiq").click(function(){window.open("https://balsamiq.com/","_blank")}),o("#aops").click(function(){window.open("https://artofproblemsolving.com/","_blank")}),o("#gitkraken").click(function(){window.open("https://www.gitkraken.com/","_blank")}),o("#wolfram").click(function(){window.open("https://www.wolfram.com/language/","_blank")}),o("#starbucks").click(function(){window.open("https://www.starbucks.com/","_blank")}),o("#waiver_toggle").click(function(){window.open("https://ridgehacks.com/waiver/waiver.pdf","_blank")}),o("#waiver").click(function(){o("#waiver").fadeOut(250)}),o("#temboo").click(function(){window.open("https://temboo.com/","_blank")});o(window).scroll(function(){var a=o(this).scrollTop(),n=o(".ftco_navbar"),e=o(".js-scroll-wrap");150<a&&(n.hasClass("scrolled")||n.addClass("scrolled")),a<150&&n.hasClass("scrolled")&&n.removeClass("scrolled sleep"),350<a&&(n.hasClass("awake")||n.addClass("awake"),0<e.length&&e.addClass("sleep")),a<350&&(n.hasClass("awake")&&(n.removeClass("awake"),n.addClass("sleep")),0<e.length&&e.removeClass("sleep"))});o("#section-counter").waypoint(function(a){if("down"===a&&!o(this.element).hasClass("ftco-animated")){var e=o.animateNumber.numberStepFactories.separator(",");o(".number").each(function(){var a=o(this),n=a.data("number");console.log(n),a.animateNumber({number:n,numberStep:e},7e3)})}},{offset:"95%"});o(".ftco-animate").waypoint(function(a){"down"!==a||o(this.element).hasClass("ftco-animated")||(o(this.element).addClass("item-animate"),setTimeout(function(){o("body .ftco-animate.item-animate").each(function(a){var n=o(this);setTimeout(function(){var a=n.data("animate-effect");"fadeIn"===a?n.addClass("fadeIn ftco-animated"):"fadeInLeft"===a?n.addClass("fadeInLeft ftco-animated"):"fadeInRight"===a?n.addClass("fadeInRight ftco-animated"):n.addClass("fadeInUp ftco-animated"),n.removeClass("item-animate")},50*a,"easeInOutExpo")})},100))},{offset:"95%"});o(".smoothscroll[href^='#'], #ftco-nav ul li a[href^='#']").on("click",function(a){a.preventDefault();var n=this.hash,e=o(".navbar-toggler");o("html, body").animate({scrollTop:o(n).offset().top},700,"easeInOutExpo",function(){window.location.hash=n}),e.is(":visible")&&e.click()}),o("body").on("activate.bs.scrollspy",function(){console.log("nice")})}(jQuery);