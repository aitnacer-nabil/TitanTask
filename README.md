
### Application TaskManager

## Aperçu

TitanTask est une application conçue pour rationaliser la gestion des tâches et des projets au sein de Titan School. Il fournit des fonctionnalités pour l'ajout, la mise à jour et la suppression de tâches, ainsi que la catégorisation, la priorisation et le stockage de bases de données.

## Caractéristiques

- Gestion des tâches : ajouter, mettre à jour, supprimer des tâches.
- Catégories de tâches : attribuez des catégories et filtrez par catégorie.
- Priorités des tâches : triez les taches par priorité.
- Stockage de base de données : utilisez JDBC pour le stockage de bases de données relationnelles.
- Historique des actions : consultez l'historique des taches.
- Exporter/Importer : Enregistrez et chargez les listes de tâches au format CSV ou JSON.


##Interface de la console

### Utilisateur

1. **Afficher les tâches :**
   - Afficher les tâches assignées à l'utilisateur.
2. **Tri :**
   - Priorité, date ou catégorie.
3. **Filtrage :**
   - Priorité ou catégorie.

### Administrateur

1. **Créer un utilisateur/une catégorie/une tâche :**
   - Recueillir les informations nécessaires.
   - Créer/mettre à jour des entités.
2. **Afficher les listes :**
   - Utilisateurs, catégories, tâches et historique.
3. **Mettre à jour l'utilisateur/la catégorie/la tâche :**
   - Sélectionnez l'ID, vérifiez l'existence et mettez à jour.
4. **Supprimer la tâche :**
   - Sélectionnez l'ID, vérifiez l'existence et confirmez la suppression.
5. **Charger/Exporter :**
   - Fichier de chargement.
   - Exporter vers JSON.

## Comment utiliser

1. Clonez le référentiel.
2. Construisez le projet à l'aide de Maven.
3. Exécutez l'application.
4. Suivez les invites de la console pour les actions de l'utilisateur ou de l'administrateur.

