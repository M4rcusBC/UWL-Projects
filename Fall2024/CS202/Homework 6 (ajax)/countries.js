/*
* Marcus Clements
* CS202, Fall 2024
* Homework 6 (ajax)
* 12/7/2024
* */

$(document).ready(function () {
    function buildPage() {
        // Configure form elements
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
                success: function (result) {
                    $list.empty();
                    updateFormVisibility();

                    result.forEach(function (country) {
                        let $option = $('<option>', {
                            value: country.cca3,
                            text: country.name.official
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

        let $wikiLinksToggleDiv = $('#global-wiki-links-toggle-container');
        $wikiLinksToggleDiv.append($wikiLinksCheckbox, $wikiLinksLabel);
        
        $('<div id="footer-spacer">').insertAfter($('#country-container'));
        
    }

    function addCountry() {
        let list = $('#country-list');
        let query = list.val();

        let $countries = $('#country-container').children();
        for (let i = 0; i < $countries.length; i++) {
            if ($countries.eq(i).attr('id') === query) { // If the id of an existing country div matches the query
                alert(
                    'This country already exists on the page. Please choose another country from the list or search for a new set.'
                );
                return;
            }
        }

        let $countryDiv;
        let urlString = `https://restcountries.com/v3.1/alpha/${query}`;

        $.ajax(urlString, {
            method: 'GET',
            accepts: 'application/json',
            success: function (result) {
                result = result[0];
                let $container = $('#country-container');
                $countryDiv = $('<div>', {
                    id: result['cca3'],
                    class: 'country-card'
                });

                let stats = {
                    Population: result['population'].toLocaleString(),
                    'Common Name': result['name']['common'],
                    'Country Code': result['cca3'],
                    Continents: result['continents'],
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
                    $countryDiv.remove();
                });
                $topDiv.append($deleteButton);

                $countryDiv.append($topDiv);

                let $flag = $('<img>', {
                    src: result['flags']['svg'],
                    alt: result['flags']['alt'],
                    class: 'flag'
                });
                $countryDiv.append($flag);

                let $listDiv = $('<div>', {class: 'country-card-stats-list'});

                let $keyList = $('<ul>');
                $.each(stats, function (key, value) {
                    $('<li>', {text: key}).appendTo($keyList);
                });
                $listDiv.append($keyList);

                let $valueList = $('<ul>');
                $.each(stats, function (key, value) {
                    $('<li>', {text: value.toString()}).appendTo($valueList);
                });
                $listDiv.append($valueList);

                $countryDiv.append($listDiv);
                $container.append($countryDiv);

                // Add the Wikipedia links container and title to div

                let $wikiLinksContainer = $('<div>', {
                    class: 'wiki-links-container'
                });

                let $title = $('<h2>', {text: 'Wikipedia Articles'});
                $wikiLinksContainer.append($title);

                let formattedName = $officialName.text().replace(' ', ',');
                let wikiUrlString = `https://en.wikipedia.org/w/api.php?origin=*&action=query&format=json&list=search&prop=links&srsearch=${formattedName}`;

                $.ajax(wikiUrlString, {
                    method: 'GET',
                    accepts: 'application/json',
                    success: function (result) {
                        let searchResults = result['query']['search'];
                        searchResults.forEach(function (article) {
                            let articleDiv = $('<div>', {class: 'wiki-article'});

                            let link = $('<a>', {
                                // Replace spaces with underscores in the title for the URL
                                href: `https://en.wikipedia.org/wiki/${article['title'].replace(' ', '_')}`,
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
                list.empty();
                updateFormVisibility();
                $countryDiv.append($wikiLinksContainer);
                // Hide the wiki links container as it's added if the global checkbox is not checked
                if (!$('#wiki-links-checkbox').is(':checked')) {
                    $wikiLinksContainer.hide();
                }
            },
            error: function () {
                alert('There was an error adding the country to the page.');
            }
        });
    }

    function updateFormVisibility() {
        let $form = $('#add-country-form');
        let $list = $('#country-list');
        $form.css('display', $list.children().length > 0 ? 'flex' : 'none');
    }

    function updateWikiLinksVisibility() {
        let $links = $('.wiki-links-container');
        $links.css('display', this.checked ? 'inline-block' : 'none');
    }

    buildPage();
});