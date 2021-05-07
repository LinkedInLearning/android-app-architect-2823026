package de.rhistel.lassdiewuerfelrollen.gui.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import de.rhistel.lassdiewuerfelrollen.R
import de.rhistel.lassdiewuerfelrollen.databinding.MainActivityLayoutBinding
import de.rhistel.lassdiewuerfelrollen.gui.observer.MainActivityLifecycleObserver

/**
 * Einstiegspunkt in die App.
 * Sie wird automatisch von Android gestartet.
 * Die MainActivity ist im AndroidManifest.xml
 * als ParentActivity in der [DiceActivity] eingetragen worden.
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

    /**
     * Das [Menu] Objekt wir automatisch zurueck geliefert.
     * Danach wird das [Menu] auf Basis des Menulayouts entfaltet.
     * Dieses befindet sich unter res/menu/main_activity_menu_layout.
     * Das Resource-Directory menu muss vorher mti Rechtsklick->New->Android Resource Directory
     * auf res angelegt werden. Bei Resourcetype einfach menu auswaehlen. Danach kann der Layoutfile
     * mit Rechtsklick auf menu geneirert werden.
     *
     * @param mainActivityMenu : [Menu] : Menu welches generiert werden soll
     * @return true : [Boolean] : [Menu] : wird angezeigt - false nicht
     */
    override fun onCreateOptionsMenu(mainActivityMenu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.main_activity_menu_layout,mainActivityMenu)
        return true
    }

    /**
     * Wenn ein [MenuItem] geklickt wird, springtdiese Funktion immer an.
     * In ihr wird ausgewertet welches item geklickt worden ist. Anschließend
     * die weitere Logik eingleitet. Hier wird die SettingsActivty Augerufen
     */
    override fun onOptionsItemSelected(clickedMenuItem: MenuItem): Boolean {
        if(clickedMenuItem.itemId == R.id.mnuItemHelp){
            this.startSettingsActivity()
        }
        return false
    }


    //endregion

    //region 4. Klickhandling

    private fun startDiceActivity() {
        //Explizites Intent (Aufrufer MainActivity, Zielactivity DiceActivity)
        val intentStartDiceActivity = Intent(this, DiceActivity::class.java)

        //Starten
        this.startActivity(intentStartDiceActivity)
    }

    private fun startSettingsActivity() {
        //Explizites Intent (Aufrufer MainActivity, Zielactivity HelpActivity)
        val intentStartSettingsActivity = Intent(this, HelpActivity::class.java)

        //Starten
        this.startActivity(intentStartSettingsActivity)
    }
    //endregion
}