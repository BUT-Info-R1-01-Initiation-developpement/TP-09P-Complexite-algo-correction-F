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
// 4, capaciteInitiale, capaciteReelle, tableauEntiers, taille

// b. Que fait le code contenu dans le bloc init ?
// Il ajoute les entiers du tableau passé en paramètre du constructeur à la liste d'entiers.

// c. Quelles sont les trois compétences principales d'un objet de type ListeEntiers ?
// 1. Retourne l'élément de la liste à l'indice spécifié => get(i:Int): Int
// 2. et 3. Ajouter un ou plusieurs entiers à la liste


class ListeEntiers(tabEntiers: Array<Int>) {

    private val capaciteInitiale = 100
    private var capaciteReelle = capaciteInitiale
    private var tableauEntiers = Array<Int?>(capaciteReelle) { null }

    var taille = 0
        private set    // rend l'accès en écriture privé

    init {
        this.ajoute(tabEntiers)
    }

    /**
     * Retourne l'élément de la liste à l'indice spécifié.
     *
     * @param i l'indice de l'élément dans la liste
     */
    operator fun get(i: Int): Int {
        require(i in this.indices()) { "Indice invalide." }
        return tableauEntiers[i]!!
    }

    /**
     * Ajoute à la liste un nouvel entier.
     *
     * @param element l'entier à ajouter à la liste
     */
    fun ajoute(element: Int) {
        // d. Quel est le but de la méthode assureCapacite() ?
        // Vérifie que la capacité de la liste a une taille suffisante et
        // augmente la taille si besoin
        this.assureCapacite()
        this.tableauEntiers[taille] = element
        this.taille++
    }

    /**
     * Ajoute à la liste les entiers contenus dans le tableau.
     *
     * @param elements le tableau contenant les entiers à ajouter à la liste.
     *
     */
    fun ajoute(elements: Array<Int>) {
        // e. Quel est le but de la méthode assureCapacite(nbElementsAAjouter: Int) ?
        // Vérifie que la capacité de la liste a une taille suffisante pour ajouter tous les
        // éléments passés en paramètre (dans un tableau) et augmente la taille si besoin
        this.assureCapacite(elements.size)
        for(element in elements) {
            this.ajoute(element)
        }
    }

    /**
     * Recherche linéaire d'un élément dans la liste.
     *
     * @param elt l'élément recherché
     *
     * @return l'indice de l'élément recherché ou -1 si l'élément n'est pas dans la liste
     */
    fun chercheAvecApprocheLineaire(elt: Int): Int {
        for (i in this.indices()) {
            if (this.tableauEntiers[i] == elt) {
                return i
            }
        }
        return -1
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

    /**
     * Retourne la liste des indices valides
     */
    fun indices(): IntRange {
        return IntRange(0,taille-1)
    }

    // f. Que fait la méthode augmenteCapacite() pour augmenter la capacité de la liste ?
    // - Elle crée un nouveau tableau dont la taille est la nouvelle taille augmentée de tailleAugmentation cases
    // - Elle recopie tous les élements de la liste dans le nouveau tableau
    // - Elle fait référencer la propriété tableauEntiers vers le nouveau tableau
    // g. Quelle est la complexité asymptotique de la méthode augmenteCapacite() quand la taille de la liste est très grande ?
    // Vous devez exprimer cette complexité avec la notation Grand O et justifier votre réponse.
    // O(n) (on recopie les éléments du tableau
    // h. Déduisez-en la complexité de l'ajout d'un élément dans une liste :
    // - dans le meilleur cas : O(1) car pas de recopie des éléments du tableau si capacité de la liste est OK
    // - dans le pire cas : O(n) car besoin de recopie de tous les éléments de la liste si capacité est KO
    private fun augmenteCapacite(tailleAugmentation: Int = this.capaciteInitiale) { // on donne une valeur par défaut à la taille d'augmentation
        this.capaciteReelle += tailleAugmentation
        val nouveauTableauElements = Array<Int?>(this.capaciteReelle) { null }
        for (i in 0 until this.taille) {
            nouveauTableauElements[i] = this.tableauEntiers[i]
        }
        this.tableauEntiers = nouveauTableauElements
    }


}