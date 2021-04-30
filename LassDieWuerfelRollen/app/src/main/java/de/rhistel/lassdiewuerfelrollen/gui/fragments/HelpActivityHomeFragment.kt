package de.rhistel.lassdiewuerfelrollen.gui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import de.rhistel.lassdiewuerfelrollen.R

/**
 * Dieses [Fragment] wird als erstes beim Start der [HelpActivity]
 * angezeigt. Dieses Fragement zeigt dem Benutzer an wie diese Activity zu bedienen ist.
 */
class HelpActivityHomeFragment : Fragment() {


	//reigon 1. Lebenyzyklus
	/**
	 * Hier wird das Layout, Widgets generiert und intalisiert
	 * @param inflater : [LayoutInflater] : Entfaltet das Fragementlayout
	 * @param container : [ViewGroup] :Element auf dem entfaltet wird
	 * @param savedInstanceState : [Bundle] : Kann genutzt werden um GUI-Daten wiederherzustellen
	 */
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {

		//1. Layout entfalten
		val root = inflater.inflate(
			R.layout.help_activity_home_fragment_layout,
			container, false
		)

		return root
	}
	//endregion
}