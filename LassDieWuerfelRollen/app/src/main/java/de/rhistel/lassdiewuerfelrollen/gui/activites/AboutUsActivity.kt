package de.rhistel.lassdiewuerfelrollen.gui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.rhistel.lassdiewuerfelrollen.R

/**
 * Diese Activity zeigt nur wer diese App entwickelt hat
 */
class AboutUsActivity : AppCompatActivity() {

	//region 1. Lebenszyklus
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		//1. Layout setzen
		this.setContentView(R.layout.about_activity_layout)
	}
	//endregion
}