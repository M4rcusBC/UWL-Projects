/*
 * Marcus Clements
 * CS202, Fall 2024
 * Homework 4
 * 11/14/2024
 */

function eligibility(age, state, filed, dependents) {
    // If there are not exactly 4 elements, we cannot determine eligibility; return false
    if (arguments.length !== 4) {
        return false;
    }
    if (!filed) {
        return false;
    }

    if (age < 60) {
        return false;
    }

    if (dependents <= 0 || dependents >= 6) {
        return false;
    }

    // This comparison saved for last to avoid unnecessary computation
    state = state.toUpperCase(); 

    if (state !== "IA" && state !== "WI") {
        return false;
    }

    // If all conditions are met, return true
    return true;
}

function oddlyEven(data) {
    // Map for case where data is a number
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
    };

    // Map for case where data is a string
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
    };

    if (typeof data === "number" && data >= 0 && Number.isInteger(data)) {
        // Generate the appropriate string based on the number
        return String(data)
            .split("")
            .map((digit) => oddEvenMap[digit])
            .join(":");
    } else if (typeof data === "string") {
        const tokens = data.split(":"); // Split the string into tokens based on the colon
        const decoded = tokens.map((token) => reverseOddEvenMap[token]);
        if (decoded.includes(undefined)) {
            // If any token is not in the map, return undefined
            return undefined;
        }
        return parseInt(decoded.join(""), 10);
    } else {
        return undefined; // Return undefined if the input is not a number or string
    }
}

function allAnagrams(words) {
    if (words === "") return true; // Return true if the input is an empty string
    const wordList = words.split(" ");
    const sorted = wordList.map((word) => word.split("").sort().join(""));
    return sorted.every((word) => word === sorted[0]); // Return true if all words are anagrams of each other, false otherwise
}

function props(list, propertyName) {
    return list.map((element) => element[propertyName]); // Return a list of the property values for each element in the list
}

function grouper(xs, n) {
    let results = [];
    if (n < 1) {
        return xs; // Return the original list if n is less than 1
    }
    for (let i = 0; i < xs.length; i += n) {
        results.push(xs.slice(i, i + n)); // Take a slice of n elements for all elements in the list, then push that slice to the results list
    }
    return results;
}

function sequence(start, step) {
    let i = 0; // Initialize i to 0 - needed to keep track of the current index in the sequence

    return function () {
        return start + step * i++; // Return the next value in the sequence defined by start and step
    };
}

function repeat(text, n) {
    // Base case: if n is less than 1, return an empty string
    if (n < 1) {
        return "";
    }

    // Recursive case: return the text concatenated with the result of repeat(text, n - 1)
    return text + repeat(text, n - 1);
}

function repeatf(f, n) {
    const results = [];
    // Execute the function n times and push that return value to results
    for (let i = 0; i < n; i++) {
        results.push(f());
    }

    return results; // Return the list of results where results.length >= 1
}

function matchmaker(obj) {
    return function (input) {
        for (let key in obj) { // Iterate over the keys in the object
            if (obj[key] !== input[key]) {
                // Return false if any key-value pair does not match
                return false;
            }
        }
        // All key-value pairs match, so return true
        return true;
    };
}

function breakup(list, partitioner) {
    const result = {};
    list.map((element) => {
        const key = partitioner(element); // Get the key for the element
        if (!result[key]) {
            // Initialize the key in the result object if it does not yet exist
            result[key] = [];
        }
        result[key].push(element);
    });
    return result; // Return the object with the partitioned elements
}

function none(list, predicate) {
    // return !list.some(predicate);
    // equivalent to the following:

    for (let i = 0; i < list.length; i++) {
        if (predicate(list[i])) {
            return false; // Return false if any element satisfies the predicate
        }
    }
    // No elements satisfy the predicate, so return true
    return true;
}

function noSql(list, query) {
    var keys = Object.keys(query); // Get the keys of the query object to compare against

    return list.filter((element) => {
        // Use filter to iterate over the list and return elements that match the query
        for (let i = 0; i < keys.length; i++) {
            if (element[keys[i]] !== query[keys[i]]) {
                return false; // Return false if any key-value pair does not match
            }
        }
        // All key-value pairs match, so return true
        return true;
    });
}

function myChoice(items) {
    // Choose a random item from the list
    let chosenItem = items[Math.floor(Math.random() * items.length)]; // Range is [0, items.length - 1]

    // Return a function that handles the logic, allowing the chosen item to be changed
    return function (input) {
        if (input === "rechoose") {
            // Choose a new random item if the input is 'rechoose'
            chosenItem = items[Math.floor(Math.random() * items.length)];
        }
        // Return the chosen item
        return chosenItem;
    };
}
