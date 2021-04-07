
window.addEventListener('online',  updateOnlineStatus);
window.addEventListener('offline', updateOnlineStatus);

function updateOnlineStatus(event) {
    var condition = navigator.onLine ? "online" : "offline";
    document.body.className = condition;
    networkIndicator(condition);
}
function networkIndicator(condition) {

    switch (condition) {
        case "online":
            var signal = document.getElementById("idIndicator");
            signal.innerHTML = "En linea <i id=\"logo\" class=\"fa fa-check-circle fa-fw\"></i>";
            signal.setAttribute("class", "btn-success");
            break;
        case "offline":
            var signal = document.getElementById("idIndicator");
            signal.innerHTML = "Sin Conexión <i id=\"logo\" class=\"fa fa-times-circle fa-fw\"></i>";
            signal.setAttribute("class", "btn-danger");
            break;
    }
}