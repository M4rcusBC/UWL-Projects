
function toggleLinks() {
    let links = document.getElementsByClassName("wiki-link");
    for (let i = 0; i < links.length; i++) {
        links[i].style.display = links[i].style.display === "none" ? "block" : "none";
    }
}

function addCountry() {
    let list = document.getElementById('country-list');
    let query = list.options[list.selectedIndex].value;

    let countries = document.getElementById('country-container').children;
    for (let i = 0; i < countries.length; i++) {
        if (countries[i].id === query) {
            alert('This country already exists on the page. Please add another country or search for a new set.');
            return;
        }
    }

    let urlString = `https://restcountries.com/v3.1/alpha/${query}`;

    $.ajax(urlString, {
       method: 'GET',
         accepts: 'application/json',
        success: (result) => {
           // Only one country object will ever be returned by this API call
            let container = document.getElementById("country-container");
            let countryDiv = document.createElement("div");
            result = result[0];
            console.log(result);
            let cca3 = result['cca3'];

            countryDiv.id = cca3;
            countryDiv.className = "country";

            let stats = {
                "Population": result['population'].toLocaleString(),
                "Common Name": result['name']['common'],
                "Country Code": cca3,
                "Continents": result['continents'],
                "Region": result['region'],
                "Subregion": result['subregion'],
                "Capital": result['capital']
            };

            let officialName = document.createElement("h2");
            officialName.innerHTML = result['name']['official'];
            countryDiv.appendChild(officialName);

            let flag = document.createElement("img");
            flag.src = result['flags']['svg'];
            flag.alt = result['flags']['alt'];
            flag.className = 'flag';
            countryDiv.appendChild(flag);

            let statsKeys = Object.keys(stats);
            console.log(statsKeys);
            let keyList = document.createElement("ul");
            for (let i = 0; i < statsKeys.length; i++) {
                let stat = document.createElement("li");
                stat.innerHTML = statsKeys[i];
                keyList.appendChild(stat);
            }
            countryDiv.appendChild(keyList);

            let statsValues = Object.values(stats);
            console.log(statsValues);
            let valueList = document.createElement("ul");
            for (let i = 0; i < statsValues.length; i++) {
                let stat = document.createElement("li");
                stat.innerHTML = statsValues[i].toString();
                valueList.appendChild(stat);
            }
            countryDiv.appendChild(valueList);

            container.appendChild(countryDiv);
        },
        error: () => {
            alert('There was an error adding the country to the page.');
        }
    });

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
        let query = nameInput.value;

        let urlString = `https://restcountries.com/v3.1/name/${query}?fields=name,cca3`;

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
            error: () => {
                alert('Your search did not return any results.');
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
    addCountryForm.onsubmit = (e) => {
        e.preventDefault();
        // No args required here; addCountry finds selected country from list
        addCountry();
    }

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
    countryContainer.id = "country-container";

    let footer = document.createElement("footer");
    footer.innerHTML = "&copy; Countries Inc.";

    body.appendChild(header);
    body.appendChild(countryFormContainer);
    body.appendChild(countryContainer);
    body.appendChild(footer);
}

document.addEventListener("DOMContentLoaded", function() {
    buildPage();
});