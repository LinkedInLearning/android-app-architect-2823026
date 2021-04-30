package de.rhistel.lassdiewuerfelrollen.gui.activites

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import de.rhistel.lassdiewuerfelrollen.R

/**
 * Diese Activity ist repreasentativ fuer Programmeinstellungen
 * gedacht.
 * Sie hat einen Naviation Drawer
 */
class HelpActivity : AppCompatActivity() {

	//region 1. Decl. and Init Attribute
	private lateinit var appBarConfiguration: AppBarConfiguration
	//endregion

	//region 2. Lebenszyklus

	/**
	 * Wird als erstes im Lebenszyklus aufgerufen
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		//1. Layout setzen
		this.setContentView(R.layout.help_activity_layout)

		//2. Toolbar  generieren
		val toolbar: Toolbar = findViewById(R.id.settingsToolbar)
		val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
		val navViewHelpActivity: NavigationView = findViewById(R.id.navViewHelpActivity)
		val navController = findNavController(R.id.nav_host_fragment)

		//3. Toolbar mit der Acitivity bekannt machen
		this.setSupportActionBar(toolbar)


		//Naviagation Drawer Items  konfigurieren
		appBarConfiguration = AppBarConfiguration(
			setOf(
				R.id.navItemHome,
				R.id.navItemPossibleRollResults
			), drawerLayout
		)

		//Kontroller und App mit einander bekannt machen
		this.setupActionBarWithNavController(navController, appBarConfiguration)

		//Naviagtionsview mit Controller verbinden
		navViewHelpActivity.setupWithNavController(navController)
	}
	//endregion

	//region 3. Menu Handling
	/**
	 * Das [Menu] Objekt wir automatisch zurueck geliefert.
	 * Danach wird das [Menu] auf Basis des Menulayouts entfaltet.
	 * Dieses befindet sich unter res/menu/main_activity_menu_layout.
	 * Das Resource-Directory menu muss vorher mti Rechtsklick->New->Android Resource Directory
	 * auf res angelegt werden. Bei Resourcetype einfach menu auswaehlen. Danach kann der Layoutfile
	 * mit Rechtsklick auf menu geneirert werden.
	 *
	 * @param helpActivityMenu : [Menu] : Menu welches generiert werden soll
	 * @return true : [Boolean] : [Menu] : wird angezeigt - false nicht
	 */
	override fun onCreateOptionsMenu(helpActivityMenu: Menu?): Boolean {
		this.menuInflater.inflate(R.menu.help_activity_menu_layout, helpActivityMenu)
		return true
	}

	/**
	 * Wenn ein [MenuItem] geklickt wird, springtdiese Funktion immer an.
	 * In ihr wird ausgewertet welches item geklickt worden ist. Anschließend
	 * die weitere Logik eingleitet. Hier wird die SettingsActivty Augerufen
	 */
	override fun onOptionsItemSelected(clickedMenuItem: MenuItem): Boolean {
		if (clickedMenuItem.itemId == R.id.mnuItemAbout) {
			this.startAboutUsActivity()
		}
		return false
	}
	//endregion

	//region Navigation Drawer Handling
	override fun onSupportNavigateUp(): Boolean {
		val navController = findNavController(R.id.nav_host_fragment)

		return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
	}
	//endregion

	//region 4. Klickhandling Menu

	private fun startAboutUsActivity() {
		//Explizites Intent (Aufrufer HelpActivity, Zielactivity AboutUsActivity)
		val intentStartSettingsActivity = Intent(this, AboutUsActivity::class.java)

		//Starten
		this.startActivity(intentStartSettingsActivity)
	}
	//endregion
}