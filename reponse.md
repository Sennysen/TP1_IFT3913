# Rapport du TP1

## Partie 1 : Code TropComp
 Dans notre code, nous avions implémenter tls avec trois fonctions différente:
 1. ```java
    public void getTLS(String dirPath, String outputPath, Boolean Opath);
    ```
    cette première méthode permet d'exécuter une simple analyse de tout les fichiers
    dans le path fourni.
    2. ```java
        public int getPercentileNbFile(String dirPath);
        ```
       cette méthode permet de savoir combien il y a de fichier au total dans le répertoire
       elle permet de de calculer combien il y a de fichier dans le seuil fourni. Par exemple,
       lorsque le seuil est de 5% nous calculons tout d'abord combien il y a de fichier dans
       le top 0.05 * nb de fichier total. Ensuite nous allons utiliser la fontion
       ```java
        public PriorityQueue<scriptDetail> getTLS(String dirPath, Integer top, String whichParam)
       ```
       pour nous fournir un queue avec les fichiers classifié par order descendante avec le nombre 
       que nous avions trouvé dans la fonction précédente. Si par exemple nous obtenons 0.05 * nb de fichier = 5
       nous savions que les fichiers dans le seuil que nous cherchons est dans le top 5. Remarquez que nous avions 
       utilisé deux queues, une classé par valeur tloc une classé par valeur tcmp. Nous allons ensuite extracter
       les valeurs respectives avec tropcomp puis tous les fichiers qui sont plus grands ou égales aux 2 valeurs seront considéré.
       Cette fonction nous permet d'écrire en fichier de sortie ou en print lines les fichiers correspondantes aux valeurs threshhold:
       ```java
        public void getTLS(String dirPath, String outputPath, Boolean Opath, Integer tlocThreshholdMin, Double tcmpThreshholdMin);
       ```
 ## Partie 2: Résultats des test jfreechart
1. 1% et 5%
   - Selon notre code il n'y avait pas de fichiers correspondantes dans le top 1% et top 5% dans les deux paramètres
2. 10% 
   - il y a un fichier corresponds dans les seuils de 10% dans les deux cas
       