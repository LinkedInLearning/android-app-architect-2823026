package de.rhistel.lassdiewuerfelrollen.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import de.rhistel.lassdiewuerfelrollen.R

/**
 * Diese Activity zeigt fuenf Wuerfel an,
 * welche durch einen Buttonklick gewuerfelt
 * werden. Jeder dieser fuenf Wuerfel ist eine
 * [ImageView], welche eine randomisierte Augenanzahl anzeigt.
 * Die Bilder hierzu finden sich im resource/drawable - Ordner.
 */
class DiceActivity : AppCompatActivity() {

    //region 1. Decl. and Init Attribute / Widgets

    /**
     * [ImageView]s welche die Wuerfel
     * und deren randomisierte Augenzahl abbilden
     */
    private val imgvsDice by lazy {
        arrayOf<ImageView>(
            this.findViewById(R.id.imgvFirstDie),
            this.findViewById(R.id.imgvSecondDie),
            this.findViewById(R.id.imgvThirdDie),
            this.findViewById(R.id.imgvFourthDie),
            this.findViewById(R.id.imgvFifthDie)
        )
    }
    //endregion

    //region 2. Lebenszyklus

    /**
     * Startet als erstes nach dem Start der Actvitiy
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //1. Layout setzen
        this.setContentView(R.layout.dice_activity_layout)

        //2. Widgets generieren

        for (imgvCurrentDie in this.imgvsDice) {
            imgvCurrentDie.setImageResource(R.drawable.six_pips)
        }

    }
    //endregion
}