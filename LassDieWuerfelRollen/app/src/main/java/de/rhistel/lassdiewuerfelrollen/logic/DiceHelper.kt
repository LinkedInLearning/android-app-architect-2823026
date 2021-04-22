package de.rhistel.lassdiewuerfelrollen.logic

import android.content.Context
import de.rhistel.lassdiewuerfelrollen.R
import de.rhistel.lassdiewuerfelrollen.settings.*

import java.util.*
import kotlin.random.Random

/**
 * Diese Klasse uebernimmt
 * das Wurfeln in dem fuer
 * alle 5 Wurfel eine zufaellige
 * Augenanzahl generiert wird.
 * Anschließend wird das Ergebnis ausgewertet.
 */
class DiceHelper {
    companion object {

        /**
         * Geneiert eine randomisierte
         * Nummer zwischen 1 und 6,
         * diese repreasentiert die Augenanzahl
         * auf dem Wuerfel.
         */
        private fun getRandomPips(): Int {
            return Random.nextInt(MIN_PIPS, MAX_PIPS)
        }

        /**
         * Generiert durch den Aufruf von [getRandomPips]
         * pro Wuerfel eine zufaellige Augenzahl
         *
         * Diese Funktion wird im ViewModel aufgerufen nach dem der Button
         * in der DiceActivity geklickt wurde.
         *
         * @return [IntArray] - mit 5 zufaelligen Werten- je Wuerfel einer
         *
         *
         *
         */
        fun rollDice(): IntArray {
            return intArrayOf(
                getRandomPips(),
                getRandomPips(),
                getRandomPips(),
                getRandomPips(),
                getRandomPips()
            )
        }


        /**
         * Hier wird das Ergebnis des Wuerfelns ausgewertet
         * @param context : [Context] : Akutelle ActvityReferenz hier die [DiceActvity]
         * um an die Strings aus der strings.xml dran zukommen
         * Nach dem Aufruf von [rollDice] im ViewModel wird direkt im Anschluss
         * diese Funktion aufgerufen, welche die Zahlen Auswertet und das Ergebnis
         * als String dem ViewModel zurueck liefert, da dieses mit der [DiceActivity]
         * verbunden ist wird das Ergebnis auf der Gui ausgegeben.
         * @param allDice : [IntArray] : WuerfelArray
         */
        fun evaluateDice(context: Context, allDice: IntArray?): String {

            //Veraenderbare Map zu speichern der Auswertung
            val diceToRolledPips = mutableMapOf(
                Pair(1, 0),
                Pair(2, 0),
                Pair(3, 0),
                Pair(4, 0),
                Pair(5, 0),
                Pair(6, 0)
            )

            //Zuordnen der Augenzahl zum Wuerfel
            for (currentDie in allDice!!.indices) {

                val currentCount = diceToRolledPips.getOrElse(allDice[currentDie]) { 0 }

                diceToRolledPips[allDice[currentDie]] = currentCount + 1
            }

            //Auswerten des Ergebnisses
            return when {
                diceToRolledPips.containsValue(FIVE_OF_A_KIND) ->
                    context.getString(R.string.strFiveOfAkind)

                diceToRolledPips.containsValue(FOUR_OF_A_KIND) ->
                    context.getString(R.string.strFourOfAkind)

                isFullHouse(diceToRolledPips) ->
                    context.getString(R.string.strFullHouse)

                isStraight(allDice) ->
                    context.getString(R.string.strStraight)

                diceToRolledPips.containsValue(THREE_OF_KIND) ->
                    context.getString(R.string.strThreeOfAKind)

                isTwoPairs(diceToRolledPips.values) ->
                    context.getString(R.string.strTwoPairs)

                diceToRolledPips.containsValue(A_PAIR) ->
                    context.getString(R.string.strApair)
                else ->
                    context.getString(R.string.strNothingSpecial)
            }

        }

        /**
         * Checkt in der uebergebenen Map ob eine volles Haus vorliegt
         * @param diceToRolledPips [MutableList]Zuordung von Wuerfel und Augenzahl
         * @return true - volles Haus - false kein volles Haus
         */
        private fun isFullHouse(diceToRolledPips: MutableMap<Int, Int>): Boolean {
            return diceToRolledPips.containsValue(THREE_OF_KIND) && diceToRolledPips.containsValue(
                A_PAIR
            )
        }

        /**
         * Ueberpreuft ob zwei Paare geworfen wurde
         * @param pipsOfDice : Augenzahl aller Wuerfel
         * @return true - 2 Paare - false keine 2 Paare
         */
        private fun isTwoPairs(pipsOfDice: MutableCollection<Int>): Boolean {
            var alreadyFoundATwo = false

            for (value in pipsOfDice) {
                if (value == 2) {

                    if (alreadyFoundATwo)
                        return true
                    else
                        alreadyFoundATwo = true
                }
            }

            return false
        }


        /**
         * Ueberpreuft ob eine Straße geworfen wurde
         * @param allDice [IntArray] : Wuerfel
         * @return true - Strasse - false keine Strasse
         */
        private fun isStraight(allDice: IntArray): Boolean {
            return (
                    (allDice.contains(ONE_PIP) || allDice.contains(SIX_PIPS)) && (
                            allDice.contains(TWO_PIPS) &&
                                    allDice.contains(THREE_PIPS) &&
                                    allDice.contains(FOUR_PIPS) &&
                                    allDice.contains(FIVE_PIPS)
                            )
                    )
        }

    }

}