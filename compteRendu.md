

# 1

comprehension du sujet
dans un gros projet, il est important de ne pas partir tete baissee.
C'est pour cela qu on va passer du temps dans l etape de conception.
Cela peut paraitre contre intuitif, mais pour gagner du temps, il ne faut pas commecner par le code.

## mise en forme du sujet sous schema relationnel

avant de commencer, voyons la difference entre une base SQL et non SQL (cc @mahfq)
lmeme si le but de l exercice va etre de construire une base mongo db, donc noSQL -> non relationnelle, je comence tout de meme par remettre le sujet sur une autre forme.
Pour cela je fais mon schema relationnelle (je suis plus habitue a ca), pour cerner les differents acteurs.
(j'utilise l excellent excalidraw btw)

Une fois que j ai mis en place mes acteurs, je peux transformer ce schema pour le mettre sous forme de "document" (cle : document = noSQL).
Grossierement, on va tout mettre dans un json colossal, sans soucier de la redondance ou autre.

On a deux choix, soit on s oriente autour des equipes, soit on s oriente autour des matchs
ici on choisira :

Une fois qu on a finis le travail preparatoir, on pase au developpement de l application.

# developpement
Pour commencer, on va creer notre base de donnees.
2 solutions, on peut faire les maxers et tout faire avec le cli (command line interface) donc dans mon terminal comme ceci : insrer photo

sinon on peut utiliser le gui (graphical user interface) developper par les inge de chez mongos.
Vu qu on est des flemmards on va se diriger vers le tool tout fait :) (ca reste toutefois important d etre capable de refaire soit meme toutes les commandes imo).

On initialise la bd et on creee une collection qu on appellel ici "matchs".

Viens ensuite l etape la plus fastidieuse, la creation du jeux de donnees.
Etant dans un monde fictif non gerer par la fifa, je vais devoir me deboruiller tout seul pour trouver les equipes, les joueurs et TOUTES les informations liees.
Pour cela on va utiliser le site suivamt https://www.mockaroo.com
EXPLIQUER : 
- data
- filter
- projection
- treatment
- output

preciser que les durees des joueurs ont ete genere aleatoirement masi que c est pas forcé d avoir exactement 80 * 15 car il peut y avoir des blessures ou des fautes.

## pt 2 l interface
trigger warning le code est degueulasse, j'ai commencé par le codé pour que cela fonctionne et m amuserai à opti (peutêtre) plus tard. Le plus important c est que ca tourne.
Si vous voulez apprendre à opti / clean code je vous invite à lire cet artcile sur les principes solid :)