function generateQrCode(url) {
  new QRCode("qrcode", {
    text: url,
    width: 200,
    height: 200,
    colorDark: "#000000",
    colorLight: "#ffffff",
    correctLevel: QRCode.CorrectLevel.L,
  });
}

const setCookie = (name, value, expiration) => {
  var expirationDate = new Date();
  expirationDate.setTime(expirationDate.getTime() + expiration * 1000);
  var c_value =
    escape(value) +
    (expiration == null ? "" : "; expires=" + expirationDate.toUTCString());
  document.cookie = name + "=" + c_value + "; path=/";
};

const getCookie = (name) => {
  var i,
    x,
    y,
    ARRcookies = document.cookie.split(";");
  for (i = 0; i < ARRcookies.length; i++) {
    x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
    y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
    x = x.replace(/^\s+|\s+$/g, "");
    if (x == name) {
      return y ? decodeURI(unescape(y.replace(/\+/g, " "))) : y; //;//unescape(decodeURI(y));
    }
  }
};

let downloadTimeout;
const checkDownloadCookie = () => {
  if (getCookie("downloadStarted") == 1) {
    setCookie("downloadStarted", "false", 100); //Expiration could be anything... As long as we reset the value
    button.classList.remove("loading");
  } else {
    downloadTimeout = setTimeout(checkDownloadCookie, 1000); //Initiate the loop to check the cookie.
  }
};

document.addEventListener("DOMContentLoaded", () => {
  for (button of document.getElementsByClassName("action-btn")) {
    button.addEventListener("click", (_) => {
      button.classList.add("loading");
      setCookie("downloadStarted", 0, 100); //Expiration could be anything... As long as we reset the value
      setTimeout(checkDownloadCookie, 1000); //Initiate the loop to check the cookie.
    });
  }
});
