/*
* Marcus Clements
* CS202, Fall 2024
* Homework 6 (ajax)
* 12/10/2024
*/

/*
* I did my best to be consistent with using jQuery for element selection and creation below,
* besides the instruction to wrap all the code in a DOMContentLoaded event listener.
*/
document.addEventListener('DOMContentLoaded', () => {
    function buildPage() {

        // Find the search form and configure its elements
        let $searchCountryForm = $('#search-country-form');
        let $nameInput = $('<input>', {
            type: 'text',
            name: 'name',
            id: 'search-country-form-name',
            placeholder: 'Country name (or partial name)',
            required: true
        });
        let $searchButton = $('<button>', {
            type: 'submit',
            text: 'Search'
        });
        $searchCountryForm.append($nameInput, $searchButton).on('submit', function (e) {
            e.preventDefault();
            let query = $nameInput.val();
            let urlString = `https://restcountries.com/v3.1/name/${query}?fields=name,cca3`;
            let $list = $('#country-list');

            $.ajax(urlString, {
                method: 'GET',
                accepts: 'application/json',
                success: (result) => {
                    $list.empty();
                    updateFormVisibility();

                    result.forEach((country) => {
                        let $option = $('<option>', {
                            value: country['cca3'],
                            text: country['name']['official']
                        });
                        $list.append($option);
                    });
                    updateFormVisibility();
                },
                error: function () {
                    alert('Your search did not return any results.');
                    $list.empty();
                    updateFormVisibility();
                }
            });
        });

        // Find the form outline and add its elements
        let $addCountryForm = $('#add-country-form');
        let $countryList = $('<select>', {
            name: 'country-list',
            id: 'country-list',
            required: true
        });
        let $addCountryButton = $('<button>', {
            type: 'submit',
            text: 'Add'
        });
        $addCountryForm.append($countryList, $addCountryButton).on('submit', function (e) {
            e.preventDefault();
            addCountry();
        });

        // Add the global wiki links toggle checkbox
        let $wikiLinksCheckbox = $('<input>', {
            type: 'checkbox',
            id: 'wiki-links-checkbox',
            checked: true
        }).on('change', updateWikiLinksVisibility);
        let $wikiLinksLabel = $('<label>', {
            text: 'show wiki links',
            for: 'wiki-links-checkbox'
        });
        let $wikiLinksToggleDiv = $('#wiki-links-toggle-container');
        $wikiLinksToggleDiv.append($wikiLinksCheckbox, $wikiLinksLabel);

        /*
        * Add the spacer for the fixed footer; enables proper styling
        * of the grid when a positioned element exists after it - see css:
        * #footer-spacer
        */
        $('<div id="footer-spacer">').insertAfter($('#country-container'));
    }

    function addCountry() {
        let list = $('#country-list');
        let query = list.val();

        let $countries = $('#country-container').children();
        for (let i = 0; i < $countries.length; i++) {
            if ($countries.eq(i).attr('id') === query) { // If the id of an existing country div matches the id attempting to be added,
                alert(
                    'This country already exists on the page. Please choose another country from the list or search for a new set.'
                );
                return;
            }
        }

        // Prepare and execute HTTP request to get country data
        let $countryDiv; // Declare the variable here so it can be accessed in the success callback and afterward
        let urlString = `https://restcountries.com/v3.1/alpha/${query}`;

        $.ajax(urlString, {
            method: 'GET',
            accepts: 'application/json',
            success: (result) => {
                // Get the first (and only) result from the array
                result = result[0];
                let $container = $('#country-container');
                $countryDiv = $('<div>', {
                    id: result['cca3'],
                    class: 'country-card'
                });

                let $detailsDiv = $('<div>', {class: 'country-card-details'});

                // Prepare a list of statistics to display about the country
                let stats = {
                    Population: result['population'].toLocaleString(),
                    'Common Name': result['name']['common'],
                    'Country Code': result['cca3'],
                    Continents: result['continents'].join(', '),
                    Region: result['region'],
                    Subregion: result['subregion'],
                    Capital: result['capital']
                };

                let $topDiv = $('<div>', {class: 'country-card-top'});

                let $officialName = $('<h2>', {
                    text: result['name']['official'],
                    class: 'country-card-official-name'
                });
                $topDiv.append($officialName);

                let $deleteButton = $('<button>', {
                    text: '\u2716'
                }).on('click', function () {
                    // Remove the entire country div from the page when the delete button is clicked
                    $countryDiv.remove();
                });
                $topDiv.append($deleteButton);

                $detailsDiv.append($topDiv);

                let $flag = $('<img>', {
                    src: result['flags']['svg'],
                    alt: result['flags']['alt'],
                    class: 'flag'
                });
                $detailsDiv.append($flag);

                let $tableDiv = $('<div>', {class: 'country-card-stats-table'});
                let $table = $('<table>');
                
                $.each(stats, function(key, value) {
                    let $row = $('<tr>');
                    $row.append($('<td>', {text: key}));
                    $row.append($('<td>', {text: value.toString()}));
                    $table.append($row);
                });
                
                $tableDiv.append($table);
                $detailsDiv.append($tableDiv);
                $countryDiv.append($detailsDiv);

                // Add Wikipedia links container and title to div
                let $wikiLinksContainer = $('<div>', {
                    class: 'wiki-links-container'
                });
                let $title = $('<h2>', {text: 'Wikipedia Articles'});
                $wikiLinksContainer.append($title);

                // Query Wikipedia API for search results related to the country
                let formattedName = $officialName.text().replaceAll(' ', ',');
                let wikiUrlString = `https://en.wikipedia.org/w/api.php?origin=*&action=query&format=json&list=search&prop=links&srsearch=${formattedName}`;

                $.ajax(wikiUrlString, {
                    method: 'GET',
                    accepts: 'application/json',
                    success: (result) => {
                        let searchResults = result['query']['search'];
                        searchResults.forEach(function (article) {
                            let articleDiv = $('<div>', {class: 'wiki-article'});

                            // Reassign query var again to represent the article's
                            query = article['title'].replaceAll(' ', '_');
                            let link = $('<a>', {
                                // Replace spaces with underscores in the title for the URL
                                href: `https://en.wikipedia.org/wiki/${query}`,
                                class: 'wiki-article-link',
                                target: '_blank',
                                text: article['title']
                            });
                            articleDiv.append(link);

                            let snippet = $('<p>', {
                                html: article['snippet'],
                                class: 'wiki-article-snippet'
                            });
                            articleDiv.append(snippet);

                            $wikiLinksContainer.append(articleDiv);
                        });
                    }
                });
                list.empty(); // Clear the country list after a country is added
                updateFormVisibility();
                let searchValue = $('#search-country-form-name');
                searchValue.val(''); // Clear the search input after a country is added
                $countryDiv.append($wikiLinksContainer);
                // Hide the wiki links container as it's added if the global checkbox is not checked
                if (!$('#wiki-links-checkbox').is(':checked')) {
                    $wikiLinksContainer.hide();
                }
                $container.append($countryDiv);
            },
            error: function () {
                alert('There was an error adding the country to the page.');
            }
        });
    }

    /*
    * Checks state of the country list's children and updates the visibility of the add country form
    */
    function updateFormVisibility() {
        let $form = $('#add-country-form');
        let $list = $('#country-list');
        $form.css('display', $list.children().length > 0 ? 'flex' : 'none');
    }

    /*
    * Checks state of the global wiki links toggle checkbox and updates the visibility of all wiki links containers
    */
    function updateWikiLinksVisibility() {
        let $links = $('.wiki-links-container');
        $links.css('display', this.checked ? 'inline-block' : 'none');
    }

    // Build the page when the DOM is loaded
    buildPage();
});