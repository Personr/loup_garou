<html>
 <head> 
   <meta charset="UTF-8" /> 	
   <link rel="stylesheet" type="text/css" href="styles.css" />
   <title>Creation d une partie</title>	    
 </head>
  <body>
    <h1>Creation d une partie </h1>
      <form method="post" action="homecontroleur" accept-charset="UTF-8">

  Nombre de joueurs MIN (2 - 20) : <input type="text" name="nombre_participants_min" value="5"/><br/>
  Nombre de joueurs MAX (3 - 20) : <input type="text" name="nombre_participants_max" value="20"/><br/>
  Heure du jour (0 - 23): <input type="text" name="duree_jour" value="8"/><br/>
  Heure de la nuit (0 - 23): <input type="text" name="duree_nuit" value="22"/><br/>
  Horaire de début de la partie (0 - 23): <input type="text" name="horaire_debut" value="8"/><br/>
  
  Probabilite qu'un pouvoir soit donne (0 -1): <ol>
                                                <li>Contamination <input type="text" name="pContamination" value="0"/><br/></li>
                                                <li>Spiritisme <input type="text" name="pSpiritisme" value="0"/><br/></li>
                                                <li>Voyance <input type="text" name="pVoyance" value="0"/><br/></li>
                                                <li>Insomnie <input type="text" name="pInsomnie" value="0"/><br/></li>
                                              </ol>
  
  Proportion de loups_garous (0.1 - 0.9): <input type="text" name="proportion_loup_garou" value="0.33"/><br/>

  <input type="hidden" name="action" value="createpartie" />
  <input type="submit" value="Creer"/>
</form>
    ${message}
</body>
</html>
