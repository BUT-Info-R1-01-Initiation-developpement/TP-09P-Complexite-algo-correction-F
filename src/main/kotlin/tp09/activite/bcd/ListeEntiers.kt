package tp09.activite.bcd

/**
 * Classe représentant des Listes d'entiers (tableaux dynamiques d'entiers)
 */
// Observe le paramètre du constructeur : il n'est pas préfixé par val ou var.
// Cela signifie que tabEntiers n'est pas une propriété des objets de type ListeEntiers.
// Mais le tableau passé en paramètre va permettre d'initialiser certaines des propriétés
// de l'objet.
//
// a. Les objets de type ListeEntiers sont caractérisés par combien de propriétés ?

// 4 : capaciteInitiale, capaciteReelle, tableauEntiers, taille
// tabEntiers, le paramètre du construteur, n'est pas une propriété car la déclaration
// n'est pas préfixée par val ou var.

// b. Que fait le code contenu dans le bloc init ?

// Le code du bloc init remplit la liste en cours de construction avec les élements
// du tableau passé en paramètre à l'appel du constructeur.

// c. Quelles sont les trois compétences principales d'un objet de type ListeEntiers ?

// Un objet de type ListeEntiers sait :
// - retourner la valeur de l'élément à un indice donné : méthode get()
// - ajouter un nombre entier à sa liste d'éléments : méthode ajoute(element: Int)
// - ajouter les nombres d'un tableau donné à sa liste d'éléments : méthode ajoute(elements: Array<Int>)
class ListeEntiers(tabEntiers: Array<Int>) {

    val capaciteInitiale = 100
    private var capaciteReelle = capaciteInitiale
    private var tableauEntiers = Array<Int?>(capaciteReelle) { null }

    var taille = 0
        private set // tricky : seul l'accès en modification est privé !

    init {
        this.ajoute(tabEntiers)
    }

    /**
     * Retourne l'élément de la liste à l'indice spécifié.
     *
     * @param i l'indice de l'élément dans la liste
     */
    operator fun get(i: Int): Int {
        require(i in indices()) { "Indice invalide." }
        return tableauEntiers[i]!!
    }

    /**
     * Ajoute à la liste un nouvel entier.
     *
     * @param element l'entier à ajouter à la liste
     */
    fun ajoute(element: Int) {
        // d. Quel est le but de la méthode assureCapacite() ?
        // La méthode assureCapacite() a pour but de garantir que la liste a la capacité necessaire
        // à l'ajout d'un élément dans la liste.
        // Si la capacité réelle est déjà atteinte, cette méthode déclenche une augmentation de capacité.
        this.assureCapacite()
        this.tableauEntiers[taille] = element
        this. taille++
    }

    /**
     * Ajoute à la liste les entiers contenus dans le tableau.
     *
     * @param elements le tableau contenant les entiers à ajouter à la liste.
     *
     */
    fun ajoute(elements: Array<Int>) {
        // e. Quel est le but de la méthode assureCapacite(nbElementsAAjouter: Int) ?
        // La méthode assureCapacite(nbElementsAAjouter: Int) a pour but de garantir que la liste a la capacité necessaire
        // à l'ajout de nbElementsAAjouter éléments dans la liste.
        // Si la capacité réelle n'est pas suffisante, cette méthode déclenche une augmentation de capacité permettant
        // l'ajout des nbElementsAAjouter.
        this.assureCapacite(elements.size)
        for(element in elements) {
            this.ajoute(element)
        }
    }

    /**
     * Fournit le IntRange des indices valides
     *
     * @return le IntRange des indices valides
     */
    fun indices(): IntRange {
        return IntRange(0,taille-1)
    }

    /**
     * Cherche un élément donné dans la liste courante.
     * Recherche linéaire : complexité en O(n)
     *
     * @param element l'élément recherché
     *
     * @return le premier indice de l'élément dans la liste ou -1
     * si l'élément n'est pas dans la liste.
     */
    fun chercheAvecApprocheLineaire(element: Int): Int {
        for (i in this.indices()) {
            if (this[i] == element) {
                return i
            }
        }
        return -1
    }

    /**
     * Cherche un élément donné dans la liste courante supposée triée.
     * Recherche dichotomique : complexité en O(log n)
     *
     * Si la liste n'est pas triée, le résultat est aléatoire
     *
     * @param element l'élément recherché
     *
     * @return un indice de l'élément dans la liste ou -1
     * si l'élément n'est pas dans la liste.
     */
    fun chercheAvecApprocheDichotomique(element: Int): Int {
        var gauche = 0
        var droite = taille - 1
        while (gauche <= droite) {
            val i = (droite + gauche) / 2
            if (this[i] == element) {
                return i
            } else if (this[i] < element) {
                gauche = i + 1
            } else {
                droite = i - 1
            }
        }
        return -1
    }

    /**
     * Calcule et retourne le nombre occurrences de l'élément donné
     * dans la liste.
     * Recherche linéaire : complexité en O(n)
     *
     * @param element l'élément dont on cherche le nombre occurrences
     * @return le nombre d'occurrences de l'élément donné
     */
    fun nombreOccurences(element: Int): Int {
        var nombreOccurences = 0
        for (i in this.indices()) {
            if (this[i] == element) {
                nombreOccurences++
            }
        }
        return nombreOccurences
    }

    private fun assureCapacite() {
        if (this.taille == this.capaciteReelle) {
            this.augmenteCapacite()
        }
    }

    private fun assureCapacite(nbElementsAAjouter: Int) {
        val tailleFinale = this.taille + nbElementsAAjouter
        if (tailleFinale  > this.capaciteReelle) {
            this.augmenteCapacite(tailleFinale)
        }
    }



    // f. Que fait la méthode augmenteCapacite() pour augmenter la capacité de la liste ?
    // La méthode augmenteCapacite() créé un nouveau tableau dont la taille est la taille
    // actuelle de la liste augmentée de la capacité initiale.
    // Ensuite, les valeurs de la liste sont recopiées dans ce nouveau tableau.
    // La propriété tableauEntiers est réassignée à ce nouveau tableau plus grand.

    // g. Quelle est la complexité asymptotique de la méthode augmenteCapacite() quand la taille de la liste est très grande ?
    // Vous devez exprimer cette complexité avec la notation Grand O et justifier votre réponse.
    // La complexité asymptotique est en O(n) (cf. chaque ligne pour la justification).

    // h. Déduisez-en la complexité de l'ajout d'un élément dans une liste :
    // - dans le meilleur cas : O(1) (on ne change pas la capacité du tableau),
    // - dans le pire cas : O(n) (cas ou on change la capacité du tableau).
    // Dans ce cas particulier, on peut s'intéresser à la notion de "temps amorti" pour calculer la complexité moyenne qui
    // serait alors O(1) car l'ajout de n éléments est en O(n).
    // Si les performances sont critiques, l'utilisation d'un tableau garantit que l'ajout d'un élément à la fin
    // du tableau est en O(1) dans tous les cas.
    private fun augmenteCapacite(tailleAugmentation: Int = this.capaciteInitiale) { // on donne une valeur par défaut à la taille d'augmentation
        this.capaciteReelle += tailleAugmentation  // 1 opération -> O(1)
        val nouveauTableauElements = Array<Int?>(this.capaciteReelle) { null } // ~ n + capaciteInitiale opérations -> O(n)
        for (i in 0 until this.taille) {
            nouveauTableauElements[i] = this.tableauEntiers[i] // n affectations -> O(n)
        }
        this.tableauEntiers = nouveauTableauElements // 1 opération -> O(1)
        // au total : O(1) + O(n) + O(n) + O(1) -> O(n)
    }

}