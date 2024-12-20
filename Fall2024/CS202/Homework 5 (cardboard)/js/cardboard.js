/*
 * Marcus Clements
 * CS202, Fall 2024
 * Homework 5 (cardboard)
 * 11/24/2024
 */

// Local list of cards to be displayed on the page
let cards = [];

//Create and configure body elements
let body = document.getElementsByTagName("body")[0];
let header = document.createElement("header");
header.appendChild(document.createElement("h3")).innerHTML = "Card Board";
header.appendChild(document.createElement("h5")).innerHTML = "jdoe@uwlax.edu";

let addCardForm = document.createElement("form");
addCardForm.className = "add-card-form";

let descInput = document.createElement("input");
descInput.type = "text";
descInput.name = "desc";
descInput.placeholder = "description";
descInput.required = true;

let urlInput = document.createElement("input");
urlInput.type = "text";
urlInput.name = "url";
urlInput.placeholder = "url";
urlInput.required = true;

addCardForm.appendChild(descInput);
addCardForm.appendChild(urlInput);

let submitButton = document.createElement("button");
submitButton.type = "submit";
submitButton.innerHTML = "Add Card";
addCardForm.appendChild(submitButton);
addCardForm.onsubmit = (e) => {
    // Prevent the form from submitting and refreshing the page
    e.preventDefault();
    let desc = descInput.value;
    let url = urlInput.value;

    // Define callback function for the 'API' call for adding a card
    cb.addCard(url, desc, (data, err) => {
        if (err) {
            alert(err);
        } else {
            cards.push(data);
            buildCards(section);
        }
    });
};

let section = document.createElement("section");
section.className = "card-container";

let footer = document.createElement("footer");
footer.innerHTML = "&copy; The Card Board Team";

// Define callback function for the 'API' call for getting cards
cb.getCards((data, err) => {
    if (err) {
        console.error(err);
    } else {
        for (let card of data) {
            cards.push(card);
        }
    }
});

/* 
 * This function is built independently of buildPage to enable the cards
 * to be updated without rebuilding the entire page, passing in the
 * relevant section element for the cards to be built under
 */
function buildCards(sectionElement) {
    //clear all existing cards, if any
    sectionElement.innerHTML = "";

    // Build the cards
    for (let card of cards) {
        let cardDiv = document.createElement("div");
        cardDiv.classList.add("card");
        
        let deleteButton = document.createElement("button");
        deleteButton.innerHTML = "x";
        deleteButton.onclick = () => {
            // Define callback function for the 'API' call for removing a card
            cb.removeCard(card.id, (data, err) => {
                if (err) {
                    alert(err);
                } else {
                    if (data) {
                        cardDiv.remove();
                    }
                }
            });
        };
        cardDiv.appendChild(deleteButton);
        
        let img = document.createElement("img");
        img.src = card.url;
        img.alt = card.desc;
        img.loading = "eager";
        cardDiv.appendChild(img);
        
        let desc = document.createElement("p");
        desc.innerText = card.desc;
        cardDiv.appendChild(desc);
        sectionElement.appendChild(cardDiv);
    }
}

// Build the page by adding all elements to the body
function buildPage() {
    let body = document.getElementsByTagName("body")[0];
    body.appendChild(header);
    body.appendChild(addCardForm);
    body.appendChild(section);
    // Build the cards under the section after the section is added to the body
    buildCards(section);
    body.appendChild(footer);
}

// Load all page content as soon as the DOM is ready
document.addEventListener("DOMContentLoaded", buildPage);
