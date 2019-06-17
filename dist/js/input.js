
function changeText() {
    var userIn = document.getElementById("textIn").value;

    document.getElementById("display").innerHTML = userIn;
}

function postInput() {
    const loc = window.location;
    const Url = loc.protocol + '//' + loc.hostname + ':' + loc.port + '/post/';
    const inputData = {
        input: document.getElementById("text-field-fullwidth-helper").value
    };

    test = fetch(Url, {
        method: 'POST',
        mode: 'same-origin',
        headers: {
            'Accept': 'text/plain',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(inputData)
    }).then((result) => {
        console.log(result);
        return result.text();
    }).then(data => {
        console.log(data);
        return data;
})
}
