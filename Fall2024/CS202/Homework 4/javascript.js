function elegibility(age, state, filed, dependents) {
    if (age >= 60) {
        if (state == "IA" || state == "WI") {
            if (filed) {
                if (dependents > 0 && dependents < 6) {
                    return true;
                }
            }
        }
    } else {
        return false;
    }
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
    return 0;
}

function repeatf(f, n) {
    return 0;
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
