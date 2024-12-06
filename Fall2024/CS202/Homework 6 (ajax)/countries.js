
function toggleLinks() {
    let links = document.getElementsByClassName("wiki-link");
    for (let i = 0; i < links.length; i++) {
        links[i].style.display = links[i].style.display === "none" ? "block" : "none";
    }
}

function buildPage() {
    // Create and configure body elements
    let body = document.getElementsByTagName("body")[0];
    let header = document.createElement("header");
    header.appendChild(document.createElement("h4")).innerHTML = "Global Ascension";
    header.appendChild(document.createElement("h5")).innerHTML = "jdoe@uwlax.edu";

    // Create and configure form elements
    let searchCountryForm = document.createElement("form");
    searchCountryForm.className = "add-country-form";

    let nameInput = document.createElement("input");
    nameInput.type = "text";
    nameInput.name = "name";
    nameInput.id = "name";
    nameInput.placeholder = "Country name (or partial name)";
    nameInput.required = true;

    let searchButton = document.createElement("button");
    searchButton.type = "submit";
    searchButton.innerHTML = "Search";

    searchCountryForm.appendChild(nameInput);
    searchCountryForm.appendChild(searchButton);
    searchCountryForm.onsubmit = (e) => {
        // Prevent the form from submitting and refreshing the page
        e.preventDefault();
        let name = nameInput.value;

        let urlString = `https://restcountries.com/v3.1/name/${name}?fields=name,cca3`;

        $.ajax(urlString, {
            method: 'GET',
            accepts: 'application/json',
            success: (result) => {
                console.log(result);
                let list = document.getElementById("country-list");
                list.innerHTML = "";

                for (let i = 0; i < result.length; i++) {
                    let option = document.createElement("option");
                    option.value = result[i].cca3;
                    option.innerHTML = result[i].name["official"];
                    list.appendChild(option);
                }
            },
            error: (err) => {
                alert(err);
            }
        });
    }

    let addCountryForm = document.createElement("form");
    addCountryForm.className = "add-country-form";

    let countryList = document.createElement("select");
    countryList.name = "country-list";
    countryList.id = "country-list";
    countryList.required = true;

    let addCountryButton = document.createElement("button");
    addCountryButton.type = "submit";
    addCountryButton.innerHTML = "Add";

    addCountryForm.appendChild(countryList);
    addCountryForm.appendChild(addCountryButton);

    let wikiLinksCheckbox = document.createElement("input");
    wikiLinksCheckbox.type = "checkbox";
    wikiLinksCheckbox.name = "links";
    wikiLinksCheckbox.id = "links";
    wikiLinksCheckbox.checked = true;
    wikiLinksCheckbox.addEventListener('toggle', toggleLinks);

    let wikiLinksLabel = document.createElement("label");
    wikiLinksLabel.innerHTML = "show wiki links";
    wikiLinksLabel.htmlFor = "links";

    let wikiLinksDiv = document.createElement("div");
    wikiLinksDiv.className = "wiki-links";
    wikiLinksDiv.appendChild(wikiLinksCheckbox);
    wikiLinksDiv.appendChild(wikiLinksLabel);

    let countryFormContainer = document.createElement("div");
    countryFormContainer.className = "country-form-container";
    countryFormContainer.appendChild(searchCountryForm);
    countryFormContainer.appendChild(addCountryForm);
    countryFormContainer.appendChild(wikiLinksDiv);

    let countryContainer = document.createElement("section");
    countryContainer.className = "country-container";

    let footer = document.createElement("footer");
    footer.innerHTML = "&copy; The Global Ascension Team";

    body.appendChild(header);
    body.appendChild(countryFormContainer);
    body.appendChild(countryContainer);
    body.appendChild(footer);
}

document.addEventListener("DOMContentLoaded", function() {
    buildPage();
});