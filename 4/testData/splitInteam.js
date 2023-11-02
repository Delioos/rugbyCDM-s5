const fs = require('fs'); // Assurez-vous que vous importez le module fs

let teams = ["France", "Irelande", "NewZealand", "Tonga", "England", "SouthAfrica"];

let raw = fs.readFileSync("joueursOk.json", 'utf8'); // Assurez-vous que le fichier existe et que vous spécifiez l'encodage
raw = JSON.parse(raw);

let playersByTeam = {};

teams.forEach((team, index) => {
    playersByTeam[team] = [];
    for (let i = 0; i < 20; i++) {
        playersByTeam[team].push(raw[i + index * 20]);
    }
    // on crée un JSON par équipe
    fs.writeFileSync(`teams/${team}.json`, JSON.stringify(playersByTeam[team], null, "\t"));
});
