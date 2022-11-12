// fetch https://nak.matthias-geissendoerfer.de/statuschecker/ and display the website in div with id content2
const fragmentUrls = [
    "https://nak.matthias-geissendoerfer.de/toaster24/navigation-js/fragment",
    "https://nak.matthias-geissendoerfer.de/toaster24/aktion-mg/fragment",
    "https://nak.matthias-geissendoerfer.de/toaster24/artikel-tb/fragment",
    "https://nak.matthias-geissendoerfer.de/toaster24/footer-ts/fragment",
]

const loadContent = async () => {
    for (const url of fragmentUrls) {
        console.log(url)
        await fetchFragments(url)
    }
}

const fetchFragments = async (url) => {
    // return a promise
    return new Promise((resolve, reject) => {
        fetch(url)
            .then((response) => response.text())
            .then(function (html) {
                console.log(url)
                appendScriptTags(html)
                resolve()
            })
    })
}

const appendScriptTags = (html) => {
    // parse all script tags from html and create a new script tag for each
    const parser = new DOMParser()
    const doc = parser.parseFromString(html, "text/html")
    let scripts = doc.getElementsByTagName("script")

    for (let i = 0; i < scripts.length; i++) {
        let script = document.createElement("script")

        script.src = scripts[i].src
        script.integrity = scripts[i].integrity
        script.crossorigin = scripts[i].crossorigin
        document.body.appendChild(script)
    }

    document.getElementById("content-csi").innerHTML = html
}

loadContent()
