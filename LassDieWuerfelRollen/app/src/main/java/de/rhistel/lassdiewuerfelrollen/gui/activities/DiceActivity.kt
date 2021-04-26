package de.rhistel.lassdiewuerfelrollen.gui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.rhistel.lassdiewuerfelrollen.R
import de.rhistel.lassdiewuerfelrollen.gui.viewModel.DiceActivityViewModel
import de.rhistel.lassdiewuerfelrollen.logic.DiceHelper
import de.rhistel.lassdiewuerfelrollen.settings.*

/**
 * Diese Activity zeigt fuenf Wuerfel an,
 * welche durch einen Buttonklick gewuerfelt
 * werden. Jeder dieser fuenf Wuerfel ist eine
 * [ImageView], welche eine randomisierte Augenanzahl anzeigt.
 * Die Bilder hierzu finden sich im resource/drawable - Ordner.
 */
class DiceActivity : AppCompatActivity() {

	//region 1. Decl. and Init Attribute / Widgets

	/**
	 *[TextView] zum Anzeigen des Ergebnises des Wurfes
	 * welcher beim Klick auf den Button [btnRollTheDice] gestartet wird.
	 */
	private val txtvRollResult by lazy { findViewById<TextView>(R.id.txtvRollResult) }

	/**
	 *Startet das Wuerfeln
	 */
	private val btnRollTheDice by lazy { findViewById<Button>(R.id.btnRollTheDice) }

	/**
	 * [ImageView]s welche die Wuerfel
	 * und deren randomisierte Augenzahl abbilden
	 */
	private val imgvsDice by lazy {
		arrayOf<ImageView>(
			this.findViewById(R.id.imgvFirstDie),
			this.findViewById(R.id.imgvSecondDie),
			this.findViewById(R.id.imgvThirdDie),
			this.findViewById(R.id.imgvFourthDie),
			this.findViewById(R.id.imgvFifthDie)
		)
	}


	/**
	 * Dieses Attribut wird genutzt um das Wuerfeln und dessen
	 * Ergebnisauswertung zu zentralisieren. Bitte die Dokumentationslinks
	 * in der Klasse [DiceActivityViewModel] beachten
	 */
	private lateinit var diceActivityViewModel: DiceActivityViewModel;
	//endregion

	//region 2. Lebenszyklus

	/**
	 * Startet als erstes nach dem Start der Actvitiy
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		//1. Layout setzen
		this.setContentView(R.layout.dice_activity_layout)

		//2.ViewModel generieren
		this.diceActivityViewModel = ViewModelProvider(this).get(DiceActivityViewModel::class.java)

		//3. Zu ueberwachenden Werte des View Models registrieren
		this.diceActivityViewModel.currentSetOfDice.observe(
			this,
			Observer { this.showCurrentSetOfDiceAndResultOnGui(it) })

		this.diceActivityViewModel.currentRollResult.observe(
			this,
			Observer { txtvRollResult.text = it })

		val configChange = savedInstanceState?.getBoolean(CONFIG_CHANGE)
			?: false

		/*
		 * Wenn eine Reorientuerung stattgefunden hat
		 * saveInstanceState nutzen um das zu checken
		 * Die Daten werden weiterhin vom ViewModel gehandelt
		 */
		if (configChange.not()) {
			this.diceActivityViewModel.rollAndEvaluateDice()
		}

		//4. Listener zuweisen Wuerfeln ist jetzt im ViewModel
		this.btnRollTheDice.setOnClickListener { this.diceActivityViewModel.rollAndEvaluateDice() }


	}

	/**
	 * Merkt sich die aktuellen GuiDaten
	 * wird automatisch von Android aufgerufen
	 */
	override fun onSaveInstanceState(outState: Bundle) {

		//Akutelle Gui Daten zumerken in outState Bundele MapSchreiben
		outState.putBoolean(CONFIG_CHANGE, true)

//		Android outState uebergeben
		super.onSaveInstanceState(outState)
	}
	//endregion

	//region 3. Klickhandling
	//endregion

	//region 4. Hilfsmethoden und Funktionen

	/**
	 * Zeigt das den akutellen Wurf und  das Ergbnis dessen an.
	 * Entweder direkt nach dem Wuerfeln in [rollTheDice] oder
	 * nach einer Reorientierung in [onCreate].
	 * Das Wuerfeln wird nun ueber das DiceActivityViewModel gesteuert.
	 */
	fun showCurrentSetOfDiceAndResultOnGui(currentSetOfDice: IntArray) {

		//Alle ImageViews und Wuerfel durchlaufen beide Arrays sind gleich groß
		for (index in this.imgvsDice.indices) {

			//Akutelle Augenzahl auswerten und passende BildId zuordnen
			val matchingPipDrawableId = when (currentSetOfDice[index]) {
				ONE_PIP -> R.drawable.one_pip
				TWO_PIPS -> R.drawable.two_pips
				THREE_PIPS -> R.drawable.three_pips
				FOUR_PIPS -> R.drawable.four_pips
				FIVE_PIPS -> R.drawable.five_pips
				SIX_PIPS -> R.drawable.six_pips
				else       -> R.drawable.six_pips
			}

			//In akuteller Wuerfel ImageView die Bild mit passender Augenanzahl anzeigen
			this.imgvsDice[index].setImageResource(matchingPipDrawableId);

		}

	}
}