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
    return 0;
}

function allAnagrams(words) {
    return 0;
}

function props(list, propertyName) {
    return 0;
}

function grouper(xs, n) {
    return 0;
}

function sequence(start, step) {
    return 0;
}

function repeat(text, n) {
    if (n <= 0) {
        return "";
    }

    return text + repeat(text, n - 1);
}

function repeatf(f, n) {
    const results = [];

    if (n < 1) {
        return results;
    }

    for (i = 0; i < n; i++) {
        results.push(f());
    }

    return results;
}

function matchmaker(obj) {
    return 0;
}

function breakup(list, partitioner) {
    return 0;
}

function none(list, predicate) {
    return 0;
}

function noSql(lsit, query) {
    return 0;
}

function myChoice(items) {
    return 0;
}
