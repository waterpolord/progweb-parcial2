
console.log("Script para detectar offline o online")

window.addEventListener('online',  updateOnlineStatus);
window.addEventListener('offline', updateOnlineStatus);

function updateOnlineStatus(event) {
    var condition = navigator.onLine ? "online" : "offline";
    document.body.className = condition;
    indicador(condition);
}

//FUNCIÓN PARA EL FRONTEND
function indicador(condition) {

    if (condition === "online") {
        ///alerta para el usuario
        var signal = document.getElementById("demo");
        signal.innerHTML = "En linea <i id=\"logo\" class=\"fa fa-check-circle fa-fw\"></i>";
        signal.setAttribute("class", "btn-success");
        document.getElementById("logo").setAttribute("class", "fa fa-check-circle fa-fw");
    }
    if (condition === "offline"){

        ///alerta para el usuario
        var signal = document.getElementById("demo");
        signal.innerHTML = "Sin Conexión <i id=\"logo\" class=\"fa fa-times-circle fa-fw\"></i>";
        signal.setAttribute("class", "btn-danger");

    }
}