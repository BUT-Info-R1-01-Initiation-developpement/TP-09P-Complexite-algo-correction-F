package tp09.activite.bcd

fun main() {
    val liste = ListeEntiers(arrayOf(12, -56, -200, 2500))
    // i. Qu'est-ce qui ne va pas dans ce code ?
    // On accède directement à la propriété tableauEntiers pour ajouter une valeur
    // sans mettre à jour la taille et en insérant la nouvelle valeur n'importe où
    // dans le tableau => un état incohérent, non souhaité.
    // j. Commentez la ligne 8 (on en aura besoin ultérieurement).
    // Modifiez le code pour ajouter "correctement"  l'élément `501`dans la liste et  que la ligne 9
    // s'exécute sans erreur.
    // liste.tableauEntiers[5] = 501
    liste.ajoute(501)
    //liste.taille = 356
    println(liste[4])
}