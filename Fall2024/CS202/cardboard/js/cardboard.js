let cards = [];

cb.getCards((data, err) => {
    if (err) {
        console.error(err);
    } else {
        for (let card of data) {
            cards.push(card);
        }
    }
});

function buildPage() {
    let body = document.getElementsByTagName("body")[0];
    let header = document.createElement("header");
    header.appendChild(document.createElement("h3")).innerHTML = "Card Board";
    header.appendChild(document.createElement('h5')).innerHTML = "jdoe@uwlax.edu";
    body.appendChild(header);

    let addCardForm = document.createElement("form");
    addCardForm.className = "add-card-form";

    let descInput = document.createElement("input");
    descInput.type = "text";
    descInput.name = "desc";
    descInput.required = true;
    descInput.placeholder = 'description';

    let urlInput = document.createElement("input");
    urlInput.type = "text";
    urlInput.name = "url";
    urlInput.placeholder = 'url';
    urlInput.required = true;

    addCardForm.appendChild(descInput);
    addCardForm.appendChild(urlInput);

    let submitButton = document.createElement("button");
    submitButton.type = "submit";
    submitButton.innerText = "Add Card";
    addCardForm.appendChild(submitButton);

    body.appendChild(addCardForm);

    let section = document.createElement("section");
    section.classList.add('card-container');

    body.appendChild(section);
    for (let card of cards) {
        let cardDiv = document.createElement("div");
        cardDiv.classList.add('card');
        let deleteButton = document.createElement("button");
        deleteButton.innerHTML = "x";
        deleteButton.onclick = () => {
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
        cardDiv.appendChild(img);
        let desc = document.createElement("p");
        desc.innerText = card.desc;
        cardDiv.appendChild(desc);
        section.appendChild(cardDiv);
    }
    let footer = document.createElement("footer");
    footer.innerHTML = "&copy; The Card Board Team";
    body.appendChild(footer);
}

document.addEventListener("DOMContentLoaded", buildPage);
