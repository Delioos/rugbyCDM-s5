const fs = require("fs");

let raw = fs.readFileSync("joueursRaw.json");
let postes = fs.readFileSync("postes.json");

raw = JSON.parse(raw);
postes = JSON.parse(postes);

for (let i = 0; i < raw.length; i++) {
  delete raw[i]["estTitullaire"];
}

fs.writeFileSync("joueursRaw.json", JSON.stringify(raw, null, "\t"));
