package com.example.calculateur

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.sourceforge.jeval.EvaluationException
import net.sourceforge.jeval.Evaluator
import java.lang.Math.PI

//View.OnLongClickListener permet d'implémenter la méthode
class MainActivity : AppCompatActivity() , View.OnLongClickListener {


    override fun onLongClick(v: View?): Boolean {
        //créer la function qui permet de modifier la constante associée au bouton
        //création la variable valeur (qui est affichée)
        var valeur: Double = etScreen.text.toString().toDouble()

        //on détermine quelle action faire en fonction de quel id
        //pour le radio euro
        if(v!!.id == R.id.rbEuro){
                TAUX_EUROS = valeur
            }

        //pour le radio dollar
        if(v!!.id == R.id.rbDollar){
            TAUX_EUROS = valeur
        }

        //pour le radio livre
        if(v!!.id == R.id.rbLivre){
            TAUX_EUROS = valeur
        }

        //pour le radio bitcoin
        if(v!!.id == R.id.rbBitcoin){
            TAUX_EUROS = valeur
        }

        //on ajoute un bip pour confirmer le changement de taux
        val bip = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100)
        bip.startTone((ToneGenerator.TONE_PROP_BEEP))

        return true
    }

    /*
    Méthode 3 pour changer le taux
    val tg = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100)

    override fun onCreate(savedInstanceState: Bundle?) {
              super.onCreate(savedInstanceState)
              setContentView(R.layout.activity_main)
              rbEuros.setOnLongClickListener(View.OnLongClickListener { changerTauxEuro(); true })
              rbDollars.setOnLongClickListener(View.OnLongClickListener { changerTauxDollar(); true })
              rbLivres.setOnLongClickListener(View.OnLongClickListener { changerTauxLivre(); true })
              rbBitcoins.setOnLongClickListener(View.OnLongClickListener { changerTauxBitcoin(); true })

      }

      fun changerTauxEuro(){
              var tvValue: Double = tvScreen.text.toString().toDouble()
              TAUX_EUROS = tvValue
              tg.startTone(ToneGenerator.TONE_PROP_BEEP)
      }

      fun changerTauxDollar(){
              var tvValue: Double = tvScreen.text.toString().toDouble()
              TAUX_DOLLARS = tvValue
              tg.startTone(ToneGenerator.TONE_PROP_BEEP)
      }

      fun changerTauxLivre(){
              var tvValue: Double = tvScreen.text.toString().toDouble()
              TAUX_LIVRES = tvValue
              tg.startTone(ToneGenerator.TONE_PROP_BEEP)
      }

      fun changerTauxBitcoin(){
              var tvValue: Double = tvScreen.text.toString().toDouble()
              TAUX_BITCOINS = tvValue
              tg.startTone(ToneGenerator.TONE_PROP_BEEP)
      }

     */



    //pour la partie monnaie
    var TAUX_EUROS: Double = 1.0
    var TAUX_DOLLARS: Double = 1.185
    var TAUX_LIVRES: Double = 0.88
    var TAUX_BITCOINS: Double = 0.0001

    var ancienneMonnaie: String = "EUR"


    //pour la partie degré
    //pour pouvoir convertir les angles en degré
    val CONV_DEGRE: Double = 1.00
    val CONV_RADIAN: Double = PI / 180 //degrés
    val CONV_GRADIAN: Double = 100.0 / 90 //degrés

    var ancienAngle: String = "DEG"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //Pour rendre paramètrable le changement de taux de change par un toucher long
        /*
        Méthode 1
        Créer un listener:
           - une classe sui implémente l'interface On...Listener
           - écrire la ou les méthode(s) exigée(s) par l'interface (ALT+ENTREE)

        Associer cet listener à l'objet ( au composant)
            exemple : rbEuro.setOnLongClickListener(new classe (crée juste au dessus))


        ---------------------------------------------------------------------------------
        Méthode 2
        Faire que l'activité implémente l'interface On...Listener

        Ecrire la ou les méthode(s) exigée(s) par l'interface (ALT+ENTREE)

        Associer à l'objet :
            exemple : rbEuro.setOnLongClickListener(this)


        ---------------------------------------------------------------------------------
        Méthode 3
        Associer à l'objet un objet anonyme (créé à la volée)
            exemple : rbEuro.setOnLongClickListener(new OnLongClickListener){
                Ecrire la ou les méthode(s) exigée(s) par l'interface (ALT+ENTREE)
            }

        ce qui donne dans notre cas ici
        rbEuro.setOnLongClickListener(View.OnLongClickListener {false})

        */

        //méthode 2, ici dans notre cas :
        //on a ajouter l'interface  , View.OnLongClickListener  à MainActivity
        //
        //avec le ALT + entrée, on a ajouté l'interface : override fun onLongClick(v: View?): Boolean {...}
        //création de la function
        //
        //associer l'objet
        rbEuro.setOnLongClickListener(this)


    }





    fun touche(view: View) { //ici view est un paramètre qui est le composant qui a déclenclé le click
        var b: Button = view as Button //on cast le bouton
        etScreen.setText(etScreen.text.toString() + b.text.toString())
    }


    fun efface(view: View) {
        etScreen.setText("0")
        //etSreen.text.clear() fonctionne aussi mais le progamme veut qqchose
    }


    //il est possible de faire les convertions de 2 façon différentes
    //premier exemple avec une fonction générique (exemple avec les monnaies)
    fun convertirMonnaies(view: View){
         var valeur: Double = etScreen.text.toString().toDouble()
         var euros: Double = 0.0

         if (ancienneMonnaie == "EUR"){euros = valeur / TAUX_EUROS}
         if (ancienneMonnaie == "USD"){euros = valeur / TAUX_DOLLARS}
         if (ancienneMonnaie == "LIV"){euros = valeur / TAUX_LIVRES}
         if (ancienneMonnaie == "BIT"){euros = valeur / TAUX_BITCOINS}

         if(view.id == R.id.rbEuro){
             valeur = euros * TAUX_EUROS
             ancienneMonnaie ="EUR"
         }

         if(view.id == R.id.rbDollar){
             valeur = euros * TAUX_DOLLARS
             ancienneMonnaie ="USD"
         }

         if(view.id == R.id.rbLivre){
             valeur = euros * TAUX_LIVRES
             ancienneMonnaie ="LIV"
         }

         if(view.id == R.id.rbBitcoin){
             valeur = euros * TAUX_BITCOINS
             ancienneMonnaie ="BIT"
         }

         etScreen.setText(valeur.toString())

    }


    //Ou alors on peut faire en créant une fonction par bouton radio (exemple avec les angles)
    fun convDegre(view: View) {
        var valeur: Double = etScreen.text.toString().toDouble()
        var angle: Double = 0.0

        if(ancienAngle == "DEG"){angle = valeur / CONV_DEGRE}
        if(ancienAngle == "RAD"){angle = valeur / CONV_RADIAN}
        if(ancienAngle == "GRA"){angle = valeur / CONV_GRADIAN}

        valeur = angle * CONV_DEGRE
        ancienAngle = "DEG"
        etScreen.setText(valeur.toString())

    }


    fun convRadian(view: View) {
        var valeur: Double = etScreen.text.toString().toDouble()
        var angle: Double = 0.0

        if(ancienAngle == "DEG"){angle = valeur / CONV_DEGRE}
        if(ancienAngle == "RAD"){angle = valeur / CONV_RADIAN}
        if(ancienAngle == "GRA"){angle = valeur / CONV_GRADIAN}

        valeur = angle * CONV_RADIAN
        ancienAngle = "RAD"
        etScreen.setText(valeur.toString()) //pour 180 degré on doit trouver pi
    }


    fun convGradian(view: View) {
        var valeur: Double = etScreen.text.toString().toDouble()
        var angle: Double = 0.0

        if(ancienAngle == "DEG"){angle = valeur / CONV_DEGRE}
        if(ancienAngle == "RAD"){angle = valeur / CONV_RADIAN}
        if(ancienAngle == "GRA"){angle = valeur / CONV_GRADIAN}

        valeur = angle * CONV_GRADIAN
        ancienAngle = "GRA"
        etScreen.setText(valeur.toString()) // pour 180 degré on doit trouver 200
    }


    fun result(view: View) {

        /*
        Comme le prog ne sait pas de façon native comment faire il faut lui donner une librairie
        =>jeval.jar

        A mettre dans le dossier Calculateur\app\libs

        faire un clic droit sur jeval.jar et cliquer sur Add as library
        (après avoir mis le projet en Projet Files pour le récupérer plus facilement)

        */

        try {
            var valeur: String = etScreen.text.toString()
            var result: Double = Evaluator().evaluate(valeur).toDouble()
            etScreen.setText(result.toString())
        }
        catch (e: EvaluationException) {//traitement de l'exception
            Toast.makeText(this, "erreur", Toast.LENGTH_LONG).show()
            e.printStackTrace()

        }


    }

}
