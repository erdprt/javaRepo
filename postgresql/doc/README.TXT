Installation
Apr�s d�zippage de l' ensemble:
Nous appellerons INSTALL_HOME le chemin vers le r�pertoire postgresql d�zipp�:
0. Sous $INSTALL_HOME/postgresql/base, d�zipper postgresql-*.zip: cela doit donner un sous r�pertoire pgsql
1. Ouvrir le fichier $INSTALL_HOME/clusters/sodifrance/bin/setclasspath.bat
2. Remplacer la ligne: 
        SET INSTALL_HOME=TODO en mettant la valeur du chemin postgresql avec des /
        SET INSTALL_HOME2=TODO en mettant la valeur du chemin postgresql avec des \
    
3. Ouvrir le fichier INSTALL_HOME/clusters/sodifrance/scripts/databases/rsi/script/database.init.sql
4. Remplacer la ligne: 
    CREATE TABLESPACE tbl_rsi LOCATION 'INSTALL_HOME/postgresql/clusters/sodifrance/tablespaces/tbl_rsi';
    avec la valeur de INSTALL_HOME
    
-------------------------
Cr�ation de la base:
a. ouvrir une ligne de commande et aller sous $INSTALL_HOME/clusters/sodifrance/bin
b. lancer create_cluster.bat : creation du cluster de base de donn�es (au sens postgresql)
c. start.bat : d�marrage du cluster postgresql
d. lancer create_database.bat rsi : cr�ation de la base rsi
e. lancer create_schema.bat rsi : cr�ation du sch�ma (public) de base de donn�es: les tables, les index,...

La base est  pr�te � �tre utilis�e & requ�t�e....

Pour arr�ter postgresql: lancer stop.bat
Pour relancer le script sql de cr�ation de rsi: relancer create_schema.bat rsi

En cas de probl�me "insoluble"!!
Arr�ter si besoin est postgresql: stop.bat
Verifier qu'il n' y a pas de process avec le gestionnaire de taches (le(s) supprimer si besoin est)
                                                      
Vider � la main les r�pertoires suivants:
  $INSTALL_HOME/clusters/sodifrance/cluster
  $INSTALL_HOME/clusters/sodifrance/tablespaces
  $INSTALL_HOME/clusters/sodifrance/transactions
  $INSTALL_HOME/clusters/sodifrance/logs
  
Reprendre les proc�dures a --> e
 



 