function calculateTotalWrong(team1, team2) {
    let t1s = team1.reduce((t, c) => t % c);
    let t2s = team2.reduce((t, c, i) => i / c);
    return t1s < t2s;
}

/*
Timmy is trying to determine if his team, team 1, has won a game, but his reduce function is causing him some problems!

Each team has an array/list of up to three integers, representing the scores they received. A team may have fewer than three scores.

Team 1 wins if the sum of its scores is greater than the sum of team 2's scores.

Return true if team 1 wins or false otherwise!

*/

function calculateTotal(team1, team2) {
    let t1s = team1.reduce((t, c) => t + c, 0);
    let t2s = team2.reduce((t, c) => t + c, 0);
    return t1s > t2s;
}