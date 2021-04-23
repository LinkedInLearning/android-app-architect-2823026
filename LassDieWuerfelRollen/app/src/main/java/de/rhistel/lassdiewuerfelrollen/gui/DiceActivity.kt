package de.rhistel.lassdiewuerfelrollen.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import de.rhistel.lassdiewuerfelrollen.R
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
	 * Akuteller Wurf beim Start
	 * der Activity der Standardwert
	 * von auschließlich sechsen.
	 * Reorientierung des Bildschirms
	 * der letzte Wurf.
	 */
	private lateinit var currentSetOfDice: IntArray

	/**
	 * Aktueller Text in [txtvRollResult] angezeigt wird.
	 */
	private lateinit var currentRollResult: String
	//endregion

	//region 2. Lebenszyklus

	/**
	 * Startet als erstes nach dem Start der Actvitiy
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		//1. Layout setzen
		this.setContentView(R.layout.dice_activity_layout)

		/*
		 * 2.
		 * Bei Reorientierung das Wurfergebnis und aktueller Wurf wiederherstellen
		 * Beim ersten Start der Activity werden die Standardwerte eingetragen
		 */

		this.currentRollResult = savedInstanceState?.getString(CURRENT_ROLL_RESULT)
			?: this.getString(R.string.strRollTheDiceToStart)

		this.currentSetOfDice = savedInstanceState?.getIntArray(CURRENT_SET_OF_DICE)
			?: this.resources.getIntArray(R.array.defaultSetOfDice)

		//3.Daten in der Gui eintragne
		this.showCurrentSetOfDiceAndResultOnGui()

		//4. Listener setzen
		this.btnRollTheDice.setOnClickListener { rollTheDice() }

	}

	/**
	 * Merkt sich die aktuellen GuiDaten
	 * wird automatisch von Android aufgerufen
	 */
	override fun onSaveInstanceState(outState: Bundle) {

		//Akutelle Gui Daten zumerken in outState Bundele MapSchreiben
		outState.putIntArray(CURRENT_SET_OF_DICE, this.currentSetOfDice)
		outState.putString(CURRENT_ROLL_RESULT, this.currentRollResult)

		//Android outState uebergeben
		super.onSaveInstanceState(outState)
	}
	//endregion

	//region 3. Klickhandling
	/**
	 * Diese Methode Wuerfelt und zeigt das
	 * Ergebnis des Wurfes auf [txtvRollResult] an.
	 */
	fun rollTheDice() {

		//Wuerfeln zufaellige Augenzahl
		this.currentSetOfDice = DiceHelper.rollDice();

		//Wurfergebnis auswerten
		this.currentRollResult = DiceHelper.evaluateDice(this, currentSetOfDice);

		//Wurf und Ergebnis auf der Gui updaten
		this.showCurrentSetOfDiceAndResultOnGui()


	}
	//endregion

	//region 4. Hilfsmethoden und Funktionen

	/**
	 * Zeigt das den akutellen Wurf und  das Ergbnis dessen an.
	 * Entweder direkt nach dem Wuerfeln in [rollTheDice] oder
	 * nach einer Reorientierung in [onCreate].
	 */
	fun showCurrentSetOfDiceAndResultOnGui() {
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

		//Wurfergebnis anzeigen
		this.txtvRollResult.text = this.currentRollResult
	}
}