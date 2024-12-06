function toggleLinks() {
    let links = document.getElementsByClassName("wiki-link");
    for (let i = 0; i < links.length; i++) {
        links[i].style.display = links[i].style.display === "none" ? "block" : "none";
    }
}

function addCountry() {
    let list = document.getElementById('country-list');
    // Retrieve the value of the selected option. This was set to be the country code, so it can be used to query the API
    let query = list.options[list.selectedIndex].value;

    // 10 extra credit points implementation below:
    let countries = document.getElementById('country-container').children;
    for (let i = 0; i < countries.length; i++) {
        if (countries[i].id === query) {
            alert('This country already exists on the page. Please add another country or search for a new set.');
            return; // Returns without making any API calls
        }
    }

    let urlString = `https://restcountries.com/v3.1/alpha/${query}`;

    /*
    * The following AJAX function is equivalent to past one-parameter $.ajax calls, e.g.,
    * $.ajax({
    *   url: urlString,
    *   method: 'GET',
    *   // etc.
    * });
    * The only difference is that the first parameter is the url and the second is an object containing the rest of the settings for the AJAX call.
    * 
    * */
    $.ajax(urlString, {
        method: 'GET',
        accepts: 'application/json',
        success: (result) => {
            // Given: only one country object will ever be returned by this API call
            let container = document.getElementById("country-container");
            let countryDiv = document.createElement("div");
            result = result[0]; // Extract the country object from the array
            let cca3 = result['cca3'];

            countryDiv.id = cca3;
            countryDiv.className = "country";

            let stats = {
                "Population": result['population'].toLocaleString(), // Add commas to the population number
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
    // Configure form elements
    let searchCountryForm = document.getElementById('search-country-form');

    let nameInput = document.createElement("input");
    nameInput.type = "text";
    nameInput.name = "name";
    nameInput.id = "search-country-form-name";
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

    let addCountryForm = document.getElementById('add-country-form')

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

    let wikiLinksToggleDiv = document.getElementById('wiki-links-toggle');
    wikiLinksToggleDiv.className = "wiki-links";
    wikiLinksToggleDiv.appendChild(wikiLinksCheckbox);
    wikiLinksToggleDiv.appendChild(wikiLinksLabel);
}

document.addEventListener("DOMContentLoaded", function () {
    buildPage();
});