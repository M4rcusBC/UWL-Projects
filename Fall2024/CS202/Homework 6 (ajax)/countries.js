/*
 * Marcus B Clements
 * CS202 Homework 6
 * 12/7/2024
 */

document.addEventListener("DOMContentLoaded", () => {

    function buildPage() {
        // Configure form elements
        let searchCountryForm = document.getElementById("search-country-form");

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
            e.preventDefault();
            let query = nameInput.value;
            let urlString = `https://restcountries.com/v3.1/name/${query}?fields=name,cca3`;
            let list = document.getElementById("country-list");

            $.ajax(urlString, {
                method: "GET",
                accepts: "application/json",
                success: (result) => {
                    list.innerHTML = "";
                    updateFormVisibility();

                    for (let i = 0; i < result.length; i++) {
                        let option = document.createElement("option");
                        option.value = result[i]["cca3"];
                        option.innerHTML = result[i].name["official"];
                        list.appendChild(option);
                    }
                    updateFormVisibility();
                },
                error: () => {
                    alert("Your search did not return any results.");
                    // Clear the list of results in the event of an error
                    list.innerHTML = "";
                    updateFormVisibility();
                },
            });
        };

        let addCountryForm = document.getElementById("add-country-form");

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
        };

        let wikiLinksCheckbox = document.createElement("input");
        wikiLinksCheckbox.type = "checkbox";
        wikiLinksCheckbox.id = "wiki-links-checkbox";
        wikiLinksCheckbox.checked = true;
        wikiLinksCheckbox.addEventListener("change", updateWikiLinksVisibility);

        let wikiLinksLabel = document.createElement("label");
        wikiLinksLabel.innerHTML = "show wiki links";
        // Associate the label with the checkbox
        wikiLinksLabel.htmlFor = "wiki-links-checkbox";

        let wikiLinksToggleDiv = document.getElementById(
            "global-wiki-links-toggle-container"
        );
        wikiLinksToggleDiv.appendChild(wikiLinksCheckbox);
        wikiLinksToggleDiv.appendChild(wikiLinksLabel);
    }

    function addCountry() {
        let list = document.getElementById("country-list");
        // Retrieve the value of the selected option. This was set to be the country code, so it can be used to query the API
        let query = list.options[list.selectedIndex].value;

        // 10 extra credit points implementation below:
        let countries = document.getElementById("country-container").children;
        for (let i = 0; i < countries.length; i++) {
            if (countries[i].id === query) {
                alert(
                    "This country already exists on the page. Please choose another country from the list or search for a new set."
                );
                return; // Returns without making any API calls
            }
        }

        let countryDiv;
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
            method: "GET",
            accepts: "application/json",
            success: (result) => {
                // Given: only one country object will ever be returned by this API call
                let container = document.getElementById("country-container");
                countryDiv = document.createElement("div");
                result = result[0]; // Extract the country object from the array
                let cca3 = result["cca3"];

                countryDiv.id = cca3;
                countryDiv.className = "country-card";

                let stats = {
                    Population: result["population"].toLocaleString(), // Add commas to the population number
                    "Common Name": result["name"]["common"],
                    "Country Code": cca3,
                    Continents: result["continents"],
                    Region: result["region"],
                    Subregion: result["subregion"],
                    Capital: result["capital"],
                };

                let topDiv = document.createElement("div");
                topDiv.className = "country-card-top";

                let officialName = document.createElement("h2");
                officialName.innerHTML = result["name"]["official"];
                officialName.className = "country-card-official-name";
                topDiv.appendChild(officialName);

                let deleteButton = document.createElement("button");
                deleteButton.innerHTML = "\u2716"; // unicode 'X' symbol
                deleteButton.onclick = () => {
                    countryDiv.remove();
                };
                topDiv.appendChild(deleteButton);

                countryDiv.appendChild(topDiv);

                let flag = document.createElement("img");
                flag.src = result["flags"]["svg"];
                flag.alt = result["flags"]["alt"];
                flag.className = "flag";
                countryDiv.appendChild(flag);

                let listDiv = document.createElement("div");
                listDiv.className = "country-card-stats-list";

                let statsKeys = Object.keys(stats);
                let keyList = document.createElement("ul");
                for (let i = 0; i < statsKeys.length; i++) {
                    let stat = document.createElement("li");
                    stat.innerHTML = statsKeys[i];
                    keyList.appendChild(stat);
                }
                listDiv.appendChild(keyList);

                let statsValues = Object.values(stats);
                let valueList = document.createElement("ul");
                for (let i = 0; i < statsValues.length; i++) {
                    let stat = document.createElement("li");
                    stat.innerHTML = statsValues[i].toString();
                    valueList.appendChild(stat);
                }
                listDiv.appendChild(valueList);

                countryDiv.appendChild(listDiv);
                container.appendChild(countryDiv);

                let wikiLinksContainer = document.createElement("div");
                wikiLinksContainer.className = "wiki-links-container";
                countryDiv.appendChild(wikiLinksContainer);

                let title = document.createElement("h2");
                title.innerHTML = "Wikipedia Articles";
                wikiLinksContainer.appendChild(title);

                // Transform query into a string of comma-separated values containing each word in the country's official name
                query = officialName.innerHTML.replace(" ", ",");

                urlString = `https://en.wikipedia.org/w/api.php?origin=*&action=query&format=json&list=search&prop=links&srsearch=${query}`;
                console.log(urlString);
                $.ajax(urlString, {
                    method: "GET",
                    accepts: "application/json",
                    success: (result) => {
                        let searchResults = result["query"]["search"];
                        for (let i = 0; i < searchResults.length; i++) {
                            let article = document.createElement("div");
                            article.className = "wiki-article";

                            let link = document.createElement("a");
                            link.href = `https://en.wikipedia.org/wiki/${searchResults[i][
                                "title"
                            ].replace(" ", "_")}`;
                            link.className = "wiki-article-link";
                            link.target = "_blank";
                            link.innerHTML = searchResults[i]["title"];
                            article.appendChild(link);

                            let snippet = document.createElement("p");
                            snippet.innerHTML = searchResults[i]["snippet"];
                            snippet.className = "wiki-article-snippet";
                            article.appendChild(snippet);

                            wikiLinksContainer.appendChild(article);
                        }
                    },
                });
                list.innerHTML = "";
                updateFormVisibility();
                // Check the state of the checkbox and display the wiki links accordingly
                if (!document.getElementById("wiki-links-checkbox").checked) {
                    wikiLinksContainer.style.display = "none";
                }
            },
            error: () => {
                alert("There was an error adding the country to the page.");
            },
        });
    }

    function updateFormVisibility() {
        let form = document.getElementById("add-country-form");
        let list = document.getElementById("country-list");
        form.style.display = list.children.length > 0 ? "flex" : "none";
    }

    function updateWikiLinksVisibility() {
        let links = document.getElementsByClassName("wiki-links-container");
        for (let i = 0; i < links.length; i++) {
            links[i].style.display = this.checked ? "inline-block" : "none";
        }
    }

    buildPage();
});
