package de.rhistel.lassdiewuerfelrollen.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import de.rhistel.lassdiewuerfelrollen.R
import de.rhistel.lassdiewuerfelrollen.settings.MAIN_ACTIVITY_LIFECYCLE_OBSERVER

/**
 * Einstiegspunkt in die App.
 * Sie wird automatisch von Android gestartet.
 *
 */
class MainActivity : AppCompatActivity() {


    //region 2. Lebenszyklus

    /**
     * 1. In dieser Methode wird das Layout entfaltet.
     * Sie wird immer als erstes aufgerufen. Die Activity
     * ist zu diesem Zeitpunkt noch nicht sichtbar.
     * @param savedInstanceState [Bundle] - Zwischenspeicherungsobjekt
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //1. Setzen des Layouts
        this.setContentView(R.layout.main_activity_layout)

        Log.d(MAIN_ACTIVITY_LIFECYCLE_OBSERVER,"onCreate()")

    }

    /**
     * 2. Teilweise sichtbarkeit
     */
    override fun onStart() {
        super.onStart()
        Log.d(MAIN_ACTIVITY_LIFECYCLE_OBSERVER,"onStart()")
    }

    /**
     * 3. Volle Sichtbarkeit Ausfuehrungszustand
     */
    override fun onResume() {
        super.onResume()
        Log.d(MAIN_ACTIVITY_LIFECYCLE_OBSERVER,"onResume()")
    }

    /**
     * 4. Pausieren der Activity
     */
    override fun onPause() {
        super.onPause()
        Log.d(MAIN_ACTIVITY_LIFECYCLE_OBSERVER,"onPause()")
    }

    /**
     * 5. Stoppen der Activity
     */
    override fun onStop() {
        super.onStop()
        Log.d(MAIN_ACTIVITY_LIFECYCLE_OBSERVER,"onStop()")
    }

    /**
     * 5. a) -> onStart()
     */
    override fun onRestart() {
        super.onRestart()
        Log.d(MAIN_ACTIVITY_LIFECYCLE_OBSERVER,"onRestart()")
    }

    /**
     * 6. Activity loeschen
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d(MAIN_ACTIVITY_LIFECYCLE_OBSERVER,"onDestroy()")
    }

    //endregion
}