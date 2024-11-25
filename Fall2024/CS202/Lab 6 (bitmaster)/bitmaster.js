function buildPage() {

    let numBits = document.getElementById('num-bits');
    let parent = document.getElementById('bits');

    numBits.addEventListener('change', (e) => {
        while (parent.firstChild) {
            parent.removeChild(parent.firstChild);
        }

        let numBits = e.target.value;
        for (let i = 0; i < numBits; i++) {
            let bit = document.createElement('div');
            bit.className = 'bit';

            bit.addEventListener('click', (e) => {
                console.log(e.target);
                if (bit.className === 'bit') {
                    bit.className = 'bit on';
                } else {
                    bit.className = 'bit';
                }

                calcVal();
            });

            let span1 = document.createElement('span');
            let span2 = document.createElement('span');

            bit.appendChild(span1);
            bit.appendChild(span2);
            parent.appendChild(bit);
            calcVal();
        }

    });
}

function calcVal() {
    let string_val = document.getElementById('bit-string-value');
    let bits = document.getElementsByClassName('bit');
    let value = 0;
    for (let i = 0; i < bits.length; i++) {
        if (bits[i].className === 'bit on') {
            value += Math.pow(2, i);
        }
    }
    string_val.textContent = value.toString();
}

document.addEventListener('DOMContentLoaded', buildPage);