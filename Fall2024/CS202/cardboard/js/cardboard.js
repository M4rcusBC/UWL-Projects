let cards = [];

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
submitButton.onsubmit = (e) => {
  e.preventDefault();
  let desc = descInput.value;
  let url = urlInput.value;

  cb.addCard(desc, url, (data, err) => {
    if (err) {
      alert(err);
    } else {
      cards.push(data);
    }
  });
};
addCardForm.appendChild(submitButton);

let section = document.createElement("section");
section.className = "card-container";

let footer = document.createElement("footer");
footer.innerHTML = "&copy; 2024 Card Board";

cb.getCards((data, err) => {
  if (err) {
    console.error(err);
  } else {
    for (let card of data) {
      cards.push(card);
    }
  }
});

function buildCards(sectionElement) {
  for (let card of cards) {
    let cardDiv = document.createElement("div");
    cardDiv.classList.add("card");
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
    sectionElement.appendChild(cardDiv);
  }
}

function buildPage() {
  let body = document.getElementsByTagName("body")[0];
  body.appendChild(header);
  body.appendChild(addCardForm);
  body.appendChild(section);
  buildCards(section);
  body.appendChild(footer);
}

// Load all page content as soon as the DOM is ready
document.addEventListener("DOMContentLoaded", buildPage);
