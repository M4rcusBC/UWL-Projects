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
    header.appendChild(document.createElement("h1")).innerText = "Cardboard";
    body.appendChild(header);
    let p = document.createElement("p");
    p.innerText = 'Hello! Here is some js-injected text!';
    body.append(p);
    let main = document.createElement("main");
    body.appendChild(main);
    for (let card of cards) {
        let cardDiv = document.createElement("div");
        cardDiv.classList.add("card");
        let img = document.createElement("img");
        img.src = card.url;
        cardDiv.appendChild(img);
        let desc = document.createElement("p");
        desc.innerText = card.desc;
        cardDiv.appendChild(desc);
        let deleteButton = document.createElement("button");
        deleteButton.innerText = "Delete";
        deleteButton.onclick = () => {
            cb.removeCard(card.id, (data, err) => {
                if (err) {
                    console.error(err);
                } else {
                    if (data) {
                        cardDiv.remove();
                    }
                }
            });
        };
        cardDiv.appendChild(deleteButton);
        main.appendChild(cardDiv);
    }
    
}

document.addEventListener('DOMContentLoaded', buildPage);
