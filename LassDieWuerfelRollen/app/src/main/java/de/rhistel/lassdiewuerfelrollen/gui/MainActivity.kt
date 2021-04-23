package de.rhistel.lassdiewuerfelrollen.gui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import de.rhistel.lassdiewuerfelrollen.R
import de.rhistel.lassdiewuerfelrollen.databinding.MainActivityLayoutBinding

/**
 * Einstiegspunkt in die App.
 * Sie wird automatisch von Android gestartet.
 *
 */
class MainActivity : AppCompatActivity() {

    //region 1. Decl. and Init Attribute / Widgets
    private lateinit var binding: MainActivityLayoutBinding
    //endregion

    //region 2. Lebenszyklus

    /**
     * 1. In dieser Methode wird das Layout entfaltet.
     * Sie wird immer als erstes aufgerufen. Die Activity
     * ist zu diesem Zeitpunkt noch nicht sichtbar.
     * @param savedInstanceState [Bundle] - Zwischenspeicherungsobjekt
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //1. Binding Object erstellen
        binding = MainActivityLayoutBinding.inflate(this.layoutInflater)

        //2. Root Elemente fest legen
        val rootView = binding.root

        //3. Setzen des Hauptelementes
        this.setContentView(rootView)

        //4. Mit Binding arbeiten, Listenerhandling
        binding.btnStartRolling.setOnClickListener { startDiceActivity() }

        //6. Lifecycle Observer mit der MainActivity bekannt machen
        this.lifecycle.addObserver(MainActivityLifecycleObserver())
    }



    //endregion

    //region 3. Klickhandling

    private fun startDiceActivity() {
        //Explizites Intent (Aufrufer MainActivity, Zielactivity DiceActivity)
        val intentStartDiceActivity = Intent(this, DiceActivity::class.java)

        //Starten
        this.startActivity(intentStartDiceActivity)
    }
    //endregion
}