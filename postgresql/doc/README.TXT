Installation
Après dézippage de l' ensemble:
Nous appellerons INSTALL_HOME le chemin vers le répertoire postgresql dézippé:
0. Sous $INSTALL_HOME/postgresql/base, dézipper postgresql-*.zip: cela doit donner un sous répertoire pgsql
1. Ouvrir le fichier $INSTALL_HOME/clusters/sodifrance/bin/setclasspath.bat
2. Remplacer la ligne: 
        SET INSTALL_HOME=TODO en mettant la valeur du chemin postgresql avec des /
        SET INSTALL_HOME2=TODO en mettant la valeur du chemin postgresql avec des \
    
3. Ouvrir le fichier INSTALL_HOME/clusters/sodifrance/scripts/databases/rsi/script/database.init.sql
4. Remplacer la ligne: 
    CREATE TABLESPACE tbl_rsi LOCATION 'INSTALL_HOME/postgresql/clusters/sodifrance/tablespaces/tbl_rsi';
    avec la valeur de INSTALL_HOME
    
-------------------------
Création de la base:
a. ouvrir une ligne de commande et aller sous $INSTALL_HOME/clusters/sodifrance/bin
b. lancer create_cluster.bat : creation du cluster de base de données (au sens postgresql)
c. start.bat : démarrage du cluster postgresql
d. lancer create_database.bat rsi : création de la base rsi
e. lancer create_schema.bat rsi : création du schéma (public) de base de données: les tables, les index,...

La base est  prête à être utilisée & requêtée....

Pour arrêter postgresql: lancer stop.bat
Pour relancer le script sql de création de rsi: relancer create_schema.bat rsi

En cas de problème "insoluble"!!
Arrêter si besoin est postgresql: stop.bat
Verifier qu'il n' y a pas de process avec le gestionnaire de taches (le(s) supprimer si besoin est)
                                                      
Vider à la main les répertoires suivants:
  $INSTALL_HOME/clusters/sodifrance/cluster
  $INSTALL_HOME/clusters/sodifrance/tablespaces
  $INSTALL_HOME/clusters/sodifrance/transactions
  $INSTALL_HOME/clusters/sodifrance/logs
  
Reprendre les procédures a --> e
 



 