
function changeText() {
    var userIn = document.getElementById("textIn").value;

    document.getElementById("display").innerHTML = userIn;
}

function postInput() {
    //Need to change Url to GalesiBoard. It is currently just a website that tests post requests.
    const Url = 'http://localhost:8080/post/';
    const dada = {
        input: document.getElementById("text-field-fullwidth-helper").value
    };

    test = fetch(Url, {
        method: 'POST',
        mode: 'same-origin',
        headers: {
            'Accept': 'text/plain',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dada)
    }).then((result) => {
        console.log(result);
        return result.text();
    }).then(data => {
        console.log(data);
        return data;
})
}
