package de.rhistel.lassdiewuerfelrollen.gui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import de.rhistel.lassdiewuerfelrollen.R

/**
 * Dieses [Fragment] der [HelpActivity] zeigt alle moeglichen
 * Wurfergebnise an
 */
class HelpActivityPossibleRollResultsFragment : Fragment() {


	//region 1. Lebenszyklus

	/**
	 * Hier wird das Layout, Widgets generiert und intalisiert
	 * @param inflater : [LayoutInflater] : Entfaltet das Fragementlayout
	 * @param container : [ViewGroup] :Element auf dem entfaltet wird
	 * @param savedInstanceState : [Bundle] : Kann genutzt werden um GUI-Daten wiederherzustellen
	 */
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {


		//1. Fragmentlayout entfalten
		val root = inflater.inflate(
			R.layout.help_activity_possible_roll_results_fragment_layout,
			container, false
		)

		//2. Widgets generieren
		val txtvPossibleRollResults: TextView = root.findViewById(R.id.txtvPossibleRollResults)

		//3. Text setzen
		txtvPossibleRollResults.text = this.getAllPossibleRollResults()

		return root
	}
	//endregion

	//region 2. Hilfsmethoden und Funktionen

	/**
	 * Gibt alle Wurfergebnisse als [String] zurueck.
	 * Dabei ist strFromatPossibleRollResults mit Platzhaltern versehen
	 * die mit den passenden Wert ersetzt werden.
	 * @return allPossibleRollResults : [String] : Alle moeglichen Wurfergebnisse
	 */
	private fun getAllPossibleRollResults(): String {
		return this.getString(
			R.string.strFormatPossibleRollResults,
			"\n" + this.getString(R.string.strFiveOfAkind),
			this.getString(R.string.strFourOfAkind) + "\n",
			this.getString(R.string.strFullHouse) + "\n",
			this.getString(R.string.strStraight) + "\n",
			this.getString(R.string.strThreeOfAKind) + "\n",
			this.getString(R.string.strTwoPairs) + "\n",
			this.getString(R.string.strApair) + "\n",
			this.getString(R.string.strNothingSpecial)
		)

	}
	//endregion
}