package de.rhistel.lassdiewuerfelrollen.gui.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.rhistel.lassdiewuerfelrollen.R
import de.rhistel.lassdiewuerfelrollen.logic.DiceHelper
import de.rhistel.lassdiewuerfelrollen.settings.DICE_VIEW_MODEL_TAG
import kotlinx.coroutines.Dispatchers

/**
 * ViewModel welches in der [DiceActivity] registriert und genutzt wird.
 * Dieses Klasse uebernimmt die Attribute fuer das Wurfergebnis und die
 * Wuerfel selbst. Somit verbleibt in der [DiceActivity] nur ein Attribut
 * dieser Klasse, welche als Observe-Pattern in den Lebenszyklus der
 * Activity integriert wird. Somit ist das triggern der Logik aus der
 * GuiKlasse selbst entfernt und in diesem Teil zentralisiert worden.
 *
 * Folgende Implementierung in dem  build.gradle (Module)
 * im Berech depedenics erweitern:
 *
 * def lifecycle_version = "2.3.1"
 *
 * // ViewModel
 * implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
 *
 * Siehe auch:
 * https://developer.android.com/jetpack/androidx/releases/lifecycle#kotlin
 * https://developer.android.com/reference/androidx/lifecycle/ViewModel
 * https://developer.android.com/topic/libraries/architecture/viewmodel
 */
class DiceActivityViewModel() : ViewModel() {

	/**
	 * [MutableLiveData] - [String] Diese Liste enthaelt das textliche Ergebnis der Wurfauswertung
	 */
	val currentRollResult = MutableLiveData<String>()

	/**
	 * [MutableLiveData] - [IntArray] : Die eigentlichen Wuerfel aus der [DiceActivity]
	 */
	val currentSetOfDice = MutableLiveData<IntArray>()

	/**
	 * Arbeitsreferenz auf die aktuelle Activity
	 */
	companion object {
		var context : Application
			get() {
				return context
			}
			set(value) {
			context = value
		}
	}

	init {

		Log.i(DICE_VIEW_MODEL_TAG, "ViewModel created")

		//Standardwert fuer das Ergebnis
		this.currentRollResult.value = context.getString(R.string.strRollTheDiceToStart)

		//Standardwerte der Wuerfel
		this.currentSetOfDice.value = context.resources.getIntArray(R.array.defaultSetOfDice)
	}

	/**
	 * Diese Funktion Wuerfelt und triggert
	 * gleich im Anschluss das Auswerten des Ergebnisses
	 */
	fun rollAndEvaluateDice() {
		//1. Wuerfeln
		currentSetOfDice.value = DiceHelper.rollDice()

		//2. Ergbnis auswerten und setzen
		currentRollResult.value = DiceHelper.evaluateDice(context, currentSetOfDice.value)
	}
}

/**
 * Factory for [DiceActivityViewModel].
 */
object DiceActivityViewModelFactory : ViewModelProvider.Factory {


	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		@Suppress("UNCHECKED_CAST")
		return DiceActivityViewModel() as T
	}
}
