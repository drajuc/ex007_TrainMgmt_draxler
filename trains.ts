const baseUrl = "http://localhost:8080/ex007_TrainMgmt_draxler-1.0-SNAPSHOT"

const allTrains = () => {
    fetch(`${baseUrl}/api/trains`).then(response => {
        return response.json();
    }).then(data => {
        const trains = data;
        let html = ""
        trains.forEach(t => html += `<tr><td>${t.id}</td><td>${t.type}</td></tr>`);
        document.getElementById("allTrainsTable").innerHTML = html;
    });
};

const listStations = (trainid) => {
    fetch(`${baseUrl}/api/trains/${trainid}`).then(response => {
        return response.json();
    }).then(data => {
        const stations = data;
        let html = ""
        stations.forEach(s => html += `<tr><td>${s}</td></tr>`);
        document.getElementById("stationsTable").innerHTML = html;
    });

}

const addTrain = (id, type) => {
    let trainToAdd = {
        id: id, type: type
    };
    fetch(`${baseUrl}/api/trains`, {
        method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify(trainToAdd)
    }).then(response => {
        alert(response.status + " " + response.headers.get("Location"));
    })
}

const addStation = (id, name) => {
    fetch(`${baseUrl}/api/trains/${id}`, {
        method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify(name)
    }).then(response => {
        alert(response.status + " " + response.headers.get("Location"));
    })
}
