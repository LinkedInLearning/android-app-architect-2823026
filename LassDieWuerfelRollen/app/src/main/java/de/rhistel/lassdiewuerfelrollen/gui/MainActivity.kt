package de.rhistel.lassdiewuerfelrollen.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.rhistel.lassdiewuerfelrollen.R

/**
 * Einstiegspunkt in die App.
 * Sie wird automatisch von Android gestartet.
 *
 */
class MainActivity : AppCompatActivity() {
    //region 0. Konstanten

    //endregion

    //region 2. Lebenszyklus

    /**
     * In dieser Methode wird das Layout entfaltet.
     * Sie wird immer als erstes aufgerufen. Die Activity
     * ist zu diesem Zeitpunkt noch nicht sichtbar.
     * @param savedInstanceState [Bundle] - Zwischenspeicherungsobjekt
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //1. Setzen des Layouts
        this.setContentView(R.layout.main_activity_layout)


    }
    //endregion
}