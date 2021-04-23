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
	//endregion

	//region 2. Lebenszyklus

	/**
	 * Startet als erstes nach dem Start der Actvitiy
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		//1. Layout setzen
		this.setContentView(R.layout.dice_activity_layout)

	}
	//endregion


	//region 3. Klickhandling
	/**
	 * Diese Methode Wuerfelt und zeigt das
	 * Ergebnis des Wurfes auf [txtvRollResult] an.
	 */
	fun onClickRollTheDice() {

		//Wuerfeln zufaellige Augenzahl
		val allDice = DiceHelper.rollDice();

		//Alle ImageViews und Wuerfel durchlaufen beide Arrays sind gleich groß
		for (index in this.imgvsDice.indices) {

			//Akutelle Augenzahl auswerten und passende BildId zuordnen
			val matchingPipDrawableId = when (allDice[index]) {
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

		//Wurergebenis anzeigen
		this.txtvRollResult.text = DiceHelper.evaluateDice(this,allDice);
	}
	//endregion
}