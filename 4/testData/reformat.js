const fs = require("fs");

let equipes = [
    "France",
    "Irelande",
    "New Zealand",
    "Tonga",
    "England",
    "South Africa",
];
let raw = fs.readFileSync("joueursOk.json", "utf8"); // Assurez-vous de spécifier l'encodage
raw = JSON.parse(raw);

const joueursParEquipe = {};

equipes.forEach((equipe, index) => {
    // pour chaque equipe
    let pts = 0;
    joueursParEquipe[equipe] = [];

    // Index de départ pour cette équipe
    const startIndex = index * 20;
    const endIndex = startIndex + 20;

    // Parcourir les joueurs dans la plage startIndex à endIndex
    for (let i = startIndex; i < endIndex; i++) {
        let joueur = raw[i];

        // On delete la propriété pointsMarques
        delete joueur["pointsMarques"];
        joueur["dureeDeJeu"] = Math.floor(Math.random() * 66);
        joueur["nbEssais"] = Math.floor(Math.random() * 2);
        joueur["nbCoupDePied"] = 0;

        if (joueur["libellePost"] === "ailier") {
            joueur["nbEssais"] = Math.floor(Math.random() * 3);
        }
        if (joueur["libellePost"] === "centre") {
            joueur["nbEssais"] = Math.floor(Math.random() * 2);
            joueur["nbCoupDePied"] = Math.floor(Math.random() * 3);
        }

        if (joueur["estTitulaire"] === true) {
            joueur["dureeDeJeu"] = Math.floor(Math.random() * 80);
        } else {
            joueur["dureeDeJeu"] = Math.floor(Math.random() * 20);
            joueur["nbEssais"] = 0;
            joueur["nbCoupDePied"] = 0;
        }
        if (joueur["dureeDeJeu"] < 3) {
            joueur["nbEssais"] = 0;
            joueur["nbCoupDePied"] = 0;
        }

        joueur["pointsMarques"] = joueur["nbEssais"] * 5 + joueur["nbCoupDePied"] * 3;
        //console.log(joueur["idJoueur"] + " " + joueur["nbEssais"] + " " + joueur["pointsMarques"]);
        pts += joueur["pointsMarques"];
        // On push le joueur dans le tableau
        joueursParEquipe[equipe].push(joueur);
    }
    console.log(equipe, " ", pts);
});

fs.writeFileSync("joueursOk.json", JSON.stringify(raw, null, "\t"));
