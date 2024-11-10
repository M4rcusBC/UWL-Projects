/*
* Marcus Clements
* CS202, Fall 2024
* Homework 4
* 11/xx/2024
*/

function eligibility(age, state, filed, dependents) {
    if (age < 60) {
        return false;
    }

    state = state.toUpperCase();

    if (state !== "IA" && state !== "WI") {
        return false;
    }

    if (!filed) {
        return false;
    }

    if (dependents <= 0 || dependents >= 6) {
        return false;
    }

    return true;
}

function oddlyEven(data) {
    const oddEvenMap = {
        0: "*",
        1: "#",
        2: "**",
        3: "##",
        4: "***",
        5: "###",
        6: "****",
        7: "####",
        8: "*****",
        9: "#####",
    }; // Map for case where data is a number
    const reverseOddEvenMap = {
        "*": "0",
        "#": "1",
        "**": "2",
        "##": "3",
        "***": "4",
        "###": "5",
        "****": "6",
        "####": "7",
        "*****": "8",
        "#####": "9",
    }; // Map for case where data is a string
    if (typeof data === "number" && data >= 0 && Number.isInteger(data)) {
        return String(data)
            .split("")
            .map((digit) => oddEvenMap[digit])
            .join(":");
    } else if (typeof data === "string") {
        const tokens = data.split(":"); // Split the string into tokens based on the colon
        const decoded = tokens.map((token) => reverseOddEvenMap[token]);
        if (decoded.includes(undefined)) {
            return undefined;
        }
        return parseInt(decoded.join(""), 10);
    } else {
        return undefined;
    }
}

function allAnagrams(words) {
    return 0;
}

function props(list, propertyName) {
    return 0;
}

function grouper(xs, n) {
    let results = [];
    if (n < 1) {
        return xs;
    }
    for (let i = 0; i < xs.length; i += n) {
        results.push(xs.slice(i, i + n)); // Take a slice of n elements for all elements in the list, then push that slice to the results list
    }
    return results;
}

function sequence(start, step) {
    let i = 0;
    // Return a function that returns the next value in the sequence each time it is called
    return function() {
        return start + step * i++;
    };
}

function repeat(text, n) {
    if (n < 1) {
        return "";
    }

    return text + repeat(text, n - 1);
}
/*
* Executes a function f n times and returns a list of the results.
*/
function repeatf(f, n) {
    const result = []; // Empty list to hold the results

    // If n is less than 1, return an empty list, executing the function 0 times
    if (n < 1) {
        return result;
    }

    // Execute the function n times, pushing each result to the results list
    for (i = 0; i < n; i++) {
        result.push(f());
    }

    return result; // Return the list of results where results.length >= 1
}

function matchmaker(obj) {
    return 0;
}

/*
* My thinking here was that the function output for
* each list element must then become a key for the returned 
* object. This way, we can group the elements by their
* return values, saying for example that elements x, y, and z all
* returned 'true'. Represented here as {true: [x, y, z], ...}
* where '...' represents other possible outputs keys and value
* pairs from the 'partitioner' function.
*/
function breakup(list, partitioner) {
    var result = {} // Initialize an empty object to hold the results

    list.forEach(element => {
        const key = partitioner(element);
        // If the key does not yet exist in the result object, create it
        // and assign an empty list to it
        if (!result[key]) {
            result[key] = [];
        }
        // Then push that element to the list associated with that key
        result[key].push(element);
    });
    return result;
}

/*
* Returns true iff all elements in the list do NOT satisfy the predicate.
* 
* The function Array.prototype.some() works as expected, returning true if 
* some element of the array satisfies the argument provided. But even though
* this works and the function has been widely available and compatible in 
* browsers since 2015, I feel like it's not the solution you were looking
* for. I'll iterate over the list and return false as soon as I find an 
* element that satisfies the predicate, returning true if none does.
*/
function none(list, predicate) {
    // return !list.some(predicate);

    var i;
    for (i = 0; i < list.length; i++) {
        if (predicate(list[i])) {
            return false;
        }
    }
    return true;
}

function noSql(list, query) {
    var result = []; // Initialize an empty list to hold the results
    var keys = Object.keys(query); // Get the keys of the query object to compare against

    list.forEach(element => { // Iterate over the list
        var match = true; // Initialize a match variable to true for each element
        keys.forEach(key => {
            if (element[key] !== query[key]) {
                match = false; // If the element does not match the query, set match to false
            }
        });
        if (match) {
            result.push(element); // If the element matches the query, push it to the results list
        }
    });
    return result; // Return the results list; may be empty
}

function myChoice(items) {
    // Choose a random item from the list
    let chosenItem = items[Math.floor(Math.random() * items.length)];

    // Return a function that handles the logic, allowing the chosen item to be changed
    return function(input) {
        if (input === 'rechoose') {
            // Choose a new random item if the input is 'rechoose'
            chosenItem = items[Math.floor(Math.random() * items.length)];
        }
        // Return the chosen item
        return chosenItem;
    };
}
