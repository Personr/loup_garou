<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head> 
        <meta charset="UTF-8" /> 	
        <link rel="stylesheet" type="text/css" href="styles.css" />
        <title>Creation d une partie</title>	    
    </head>

    <body>
        <h1>Creation d une partie </h1>
        <div class="parties">

            <form method="post" action="homecontroleur" accept-charset="UTF-8">

                Nombre de joueurs MIN (2 - 20) : <input type="text" name="nombre_participants_min" value="5"/><br/>
                Nombre de joueurs MAX (3 - 20) : <input type="text" name="nombre_participants_max" value="20"/><br/>

                <br/>
                    Probabilite qu'un pouvoir soit donne (0 -1): <ol>
                    <li>Contamination <input type="text" name="pContamination" value="0"/><br/></li>
                    <li>Spiritisme <input type="text" name="pSpiritisme" value="0"/><br/></li>
                    <li>Voyance <input type="text" name="pVoyance" value="0"/><br/></li>
                    <li>Insomnie <input type="text" name="pInsomnie" value="0"/><br/></li>
                </ol>

                Proportion de loups_garous (0.1 - 0.9): <input type="text" name="proportion_loup_garou" value="0.33"/><br/>

        
            <p>
                <label for="Lancement">Lancement de partie et changement jour / nuit :</label>
                <input type="radio" name="Automatique" value=0 checked="checked" /> Automatique
                <input type="radio" name="Automatique" value=1 /> Manuel <br />
            </p>
<<<<<<< HEAD

            <p>
                <label for="HL">Date de lancement</label><br />
                <select name="hl" id="hl">
                    <option value="00">0</option>
                    <option value="01">1</option>
                    <option value="02">2</option>
                    <option value="03">3</option>
                    <option value="04">4</option>
                    <option value="05">5</option>
                    <option value="06">6</option>
                    <option value="07">7</option>
                    <option value="08" selected>8</option>
                    <option value="09">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                </select>
                <select name="ml" id="ml">
                    <option value="00" selected>00</option>
                    <option value="05">05</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                    <option value="20">20</option>
                    <option value="25">25</option>
                    <option value="30">30</option>
                    <option value="35">35</option>
                    <option value="40">40</option>
                    <option value="45">45</option>
                    <option value="50">50</option>
                    <option value="55">55</option>
                </select>
            </p>

            <p>
                <label for="HD">Heure de passage au jour</label><br />
                <select name="hd" id="hd">
                    <option value="00">0</option>
                    <option value="01">1</option>
                    <option value="02">2</option>
                    <option value="03">3</option>
                    <option value="04">4</option>
                    <option value="05">5</option>
                    <option value="06">6</option>
                    <option value="07">7</option>
                    <option value="08" selected>8</option>
                    <option value="09">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                </select>
                <select name="md" id="md">
                    <option value="00" selected>00</option>
                    <option value="05">05</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                    <option value="20">20</option>
                    <option value="25">25</option>
                    <option value="30">30</option>
                    <option value="35">35</option>
                    <option value="40">40</option>
                    <option value="45">45</option>
                    <option value="50">50</option>
                    <option value="55">55</option>
                </select>
            </p>

            <p>
                <label for="HN">Heure de passage à la nuit</label><br />
                <select name="hn" id="hn">
                    <option value="00">0</option>
                    <option value="01">1</option>
                    <option value="02">2</option>
                    <option value="03">3</option>
                    <option value="04">4</option>
                    <option value="05">5</option>
                    <option value="06">6</option>
                    <option value="07">7</option>
                    <option value="08">8</option>
                    <option value="09">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22" selected>22</option>
                    <option value="23">23</option>
                </select>
                <select name="mn" id="mn">
                    <option value="00" selected>00</option>
                    <option value="05">05</option>
                    <option value="10">10</option>
                    <option value="15">15</option>
                    <option value="20">20</option>
                    <option value="25">25</option>
                    <option value="30">30</option>
                    <option value="35">35</option>
                    <option value="40">40</option>
                    <option value="45">45</option>
                    <option value="50">50</option>
                    <option value="55">55</option>
                </select>
            </p>
=======
            
             <p>
                 <label for="HL">Date de lancement</label><br />
                 <select name="hl" id="hl">
                     <option value="00">0</option>
                     <option value="01">1</option>
                     <option value="02">2</option>
                     <option value="03">3</option>
                     <option value="04">4</option>
                     <option value="05">5</option>
                     <option value="06">6</option>
                     <option value="07">7</option>
                     <option value="08" selected>8</option>
                     <option value="09">9</option>
                     <option value="10">10</option>
                     <option value="11">11</option>
                     <option value="12">12</option>
                     <option value="13">13</option>
                     <option value="14">14</option>
                     <option value="15">15</option>
                     <option value="16">16</option>
                     <option value="17">17</option>
                     <option value="18">18</option>
                     <option value="19">19</option>
                     <option value="20">20</option>
                     <option value="21">21</option>
                     <option value="22">22</option>
                     <option value="23">23</option>
                 </select>
                 <select name="ml" id="ml">
                     <option value="00" selected>00</option>
                     <option value="05">05</option>
                     <option value="10">10</option>
                     <option value="15">15</option>
                     <option value="20">20</option>
                     <option value="25">25</option>
                     <option value="30">30</option>
                     <option value="35">35</option>
                     <option value="40">40</option>
                     <option value="45">45</option>
                     <option value="50">50</option>
                     <option value="55">55</option>
                 </select>
                 <select name="dl" id="dl">
                     <option value="a">Aujourd'hui</option>
                     <option value="d" selected >Demain</option>
                 </select>
             </p>
             
             <p>
                 <label for="HD">Heure de passage au jour</label><br />
                 <select name="hd" id="hd">
                     <option value="00">0</option>
                     <option value="01">1</option>
                     <option value="02">2</option>
                     <option value="03">3</option>
                     <option value="04">4</option>
                     <option value="05">5</option>
                     <option value="06">6</option>
                     <option value="07">7</option>
                     <option value="08" selected>8</option>
                     <option value="09">9</option>
                     <option value="10">10</option>
                     <option value="11">11</option>
                     <option value="12">12</option>
                     <option value="13">13</option>
                     <option value="14">14</option>
                     <option value="15">15</option>
                     <option value="16">16</option>
                     <option value="17">17</option>
                     <option value="18">18</option>
                     <option value="19">19</option>
                     <option value="20">20</option>
                     <option value="21">21</option>
                     <option value="22">22</option>
                     <option value="23">23</option>
                 </select>
                 <select name="md" id="md">
                     <option value="00" selected>00</option>
                     <option value="05">05</option>
                     <option value="10">10</option>
                     <option value="15">15</option>
                     <option value="20">20</option>
                     <option value="25">25</option>
                     <option value="30">30</option>
                     <option value="35">35</option>
                     <option value="40">40</option>
                     <option value="45">45</option>
                     <option value="50">50</option>
                     <option value="55">55</option>
                 </select>
             </p>
             
              <p>
                 <label for="HN">Heure de passage à la nuit</label><br />
                 <select name="hn" id="hn">
                     <option value="00">0</option>
                     <option value="01">1</option>
                     <option value="02">2</option>
                     <option value="03">3</option>
                     <option value="04">4</option>
                     <option value="05">5</option>
                     <option value="06">6</option>
                     <option value="07">7</option>
                     <option value="08">8</option>
                     <option value="09">9</option>
                     <option value="10">10</option>
                     <option value="11">11</option>
                     <option value="12">12</option>
                     <option value="13">13</option>
                     <option value="14">14</option>
                     <option value="15">15</option>
                     <option value="16">16</option>
                     <option value="17">17</option>
                     <option value="18">18</option>
                     <option value="19">19</option>
                     <option value="20">20</option>
                     <option value="21">21</option>
                     <option value="22" selected>22</option>
                     <option value="23">23</option>
                 </select>
                 <select name="mn" id="mn">
                     <option value="00" selected>00</option>
                     <option value="05">05</option>
                     <option value="10">10</option>
                     <option value="15">15</option>
                     <option value="20">20</option>
                     <option value="25">25</option>
                     <option value="30">30</option>
                     <option value="35">35</option>
                     <option value="40">40</option>
                     <option value="45">45</option>
                     <option value="50">50</option>
                     <option value="55">55</option>
                 </select>
             </p>
>>>>>>> c975875049f51cc389f71d375df8ade7805eefcd

            <input type="hidden" name="action" value="createpartie" />
            <input type="submit" value="Creer"/>
        </form>
        ${message}
    </div>
</body>
</html>
