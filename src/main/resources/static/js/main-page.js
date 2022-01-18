$(function () {
  $(".alert-download .alert").fadeOut(5000);
  $("#myModalVideo .close").click(function () {
    $("#video").get(0).pause();
  });
  $("#myModalVideo").on("hide.bs.modal", function () {
    $("#video").get(0).pause();
  });
  $("#video")
    .parent()
    .click(function () {
      if ($("#video").get(0).paused) {
        $("#video").get(0).play();
        $(".playpause").fadeOut();
      } else {
        $("#video").get(0).pause();
        $(".playpause").fadeIn();
      }
    });
  var myVideoPlayer = document.getElementById("video"),
    time = document.getElementById("time");
  if (myVideoPlayer) {
    myVideoPlayer.addEventListener("loadedmetadata", function () {
      var sec_num = parseInt(myVideoPlayer.duration);
      var hours = Math.floor(sec_num / 3600);
      var minutes = Math.floor((sec_num - hours * 3600) / 60);
      var seconds = sec_num - hours * 3600 - minutes * 60;
      if (hours < 10) {
        hours = "0" + hours;
      }
      if (minutes < 10) {
        minutes = "0" + minutes;
      }
      if (seconds < 10) {
        seconds = "0" + seconds;
      }
      if (hours != "00") {
        time.innerHTML = hours + ":" + minutes + ":" + seconds;
      } else {
        time.innerHTML = minutes + ":" + seconds;
      }
    });
  }
  $(window).scroll(function () {
    if ($(this).scrollTop() >= 1000) {
      $("#back-to-top").fadeIn(200);
    } else {
      $("#back-to-top").fadeOut(200);
    }
  });
  $("#back-to-top").click(function () {
    $("body,html").animate({ scrollTop: 0 }, 500);
  });
});

const bitchuteRegex = new RegExp("https?:\/\/(?:www)?\.?bitchute.com\/video\/([a-zA-Z0-9]{1,64})\/")
  
const validateForm = () => {
  const value = document.getElementById("url-field").value;
  if (!bitchuteRegex.test(value)) {
    document.getElementById("url-field").setCustomValidity("This is not valid URL, try again");
    return false;
  }
  document.getElementById("url-field").setCustomValidity("This is not valid URL, try again");
  return true;
}