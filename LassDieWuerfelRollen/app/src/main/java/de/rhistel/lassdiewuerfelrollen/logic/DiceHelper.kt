package de.rhistel.lassdiewuerfelrollen.logic

import android.content.Context
import de.rhistel.lassdiewuerfelrollen.R
import de.rhistel.lassdiewuerfelrollen.settings.*

import java.util.*
import kotlin.random.Random

/**
 * Diese Klasse uebernimmt
 * das Wurfeln in dem fuer
 * alle 5 Wurfel eine zufaellig
 * Augenanzahl generiert wird.
 * Anschließend wird das Ergebnis ausgewertet,
 * in jeder Wuerfel mit seiner Augenanzahl zugeordnet
 * wird und anschließend wird dieses verglichen.
 *
 * Es ist zu bemerken das diese Logik hier modular
 * aufgebaut ist. Das eigentliche triggern wird ueber
 * den Buttonklick auf der Oberflaeche und danach ueber
 * das ViewModel durchgefuehrt. Dort wird zuerst das Wuerfeln
 * und dann das Auswerten angesteuert.
 */
class DiceHelper {

    /**
     * Das Compainion Object erlaubt
     * einen statischen Zugriff auf alle
     * inneren Methoden und Funktionen.
     * Dadruch ist keine Objektbindung
     * vorhanden und es kann alles
     * ueber den Klassennamen [DiceHelper]
     * mit dem Punktoperator angesprochen werden.
     */
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
         * @return dicePips [IntArray] - mit 5 zufaelligen Augen- je Wuerfel
         * Ein Element in dem Array Repreaesentiert ein Wuerfel
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
         *
         *
         *
         * Nach dem Aufruf von [rollDice] im ViewModel wird direkt im Anschluss
         * diese Funktion aufgerufen, welche die Zahlen Auswertet und das Ergebnis
         * als String dem ViewModel zurueck liefert, da dieses mit der [DiceActivity]
         * verbunden ist wird das Ergebnis auf der Gui ausgegeben.
         *
         *  @param context : [Context] : Akutelle ActvityReferenz hier die [DiceActvity]
         * um an die Strings aus der strings.xml dran zukommen
         *
         * @param allDicePips : [IntArray] : WuerfelArray geeriert durch [rollDice] getriggert
         * im ViewModel
         *
         * @return rollResult : [String] : Ergebnis des Wurfes wird ans ViewModel zurueckgeliefert
         * und dadruch auf der Gui geupdated
         */
        fun evaluateDice(context: Context, allDicePips: IntArray?): String {

            //Mappen der Wuerfel zu Augenanzahl fuer die untere Auswetung
            val diceToRolledPips = mutableMapOf(
                Pair(1, 0),
                Pair(2, 0),
                Pair(3, 0),
                Pair(4, 0),
                Pair(5, 0),
                Pair(6, 0)
            )

            //Zuordnen der Augenzahl zum Wuerfel
            for (pipsOfCurrentDie in allDicePips!!.indices) {

                val currentCount = diceToRolledPips.getOrElse(allDicePips[pipsOfCurrentDie]) { 0 }

                diceToRolledPips[allDicePips[pipsOfCurrentDie]] = currentCount + 1
            }

            //Auswerten des Ergebnisses
            return when {
                diceToRolledPips.containsValue(FIVE_OF_A_KIND) ->
                    context.getString(R.string.strFiveOfAkind)

                diceToRolledPips.containsValue(FOUR_OF_A_KIND) ->
                    context.getString(R.string.strFourOfAkind)

                isFullHouse(diceToRolledPips) ->
                    context.getString(R.string.strFullHouse)

                isStraight(allDicePips) ->
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
         * @param allDicePips [IntArray] : Wuerfel
         * @return true - Strasse - false keine Strasse
         */
        private fun isStraight(allDicePips: IntArray): Boolean {
            return (
                    (allDicePips.contains(ONE_PIP) || allDicePips.contains(SIX_PIPS)) && (
                            allDicePips.contains(TWO_PIPS) &&
                                    allDicePips.contains(THREE_PIPS) &&
                                    allDicePips.contains(FOUR_PIPS) &&
                                    allDicePips.contains(FIVE_PIPS)
                            )
                    )
        }

    }

}