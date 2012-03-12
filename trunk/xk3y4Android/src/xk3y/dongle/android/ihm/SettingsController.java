package xk3y.dongle.android.ihm;

import org.acra.ErrorReporter;

import xk3y.dongle.android.R;
import xk3y.dongle.android.utils.ConfigUtils;
import xk3y.dongle.android.utils.LoadingUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * Listener for the main view
 * @author maloups
 *
 */
public class SettingsController implements OnClickListener {

	/** the view */
	private SettingsActivity view;

	
	/** Contructor */
	public SettingsController (SettingsActivity maVue) {
		this.view = maVue;
	}

	/**
	 * Evenement r�lalis� lors d'un click sur un bouton
	 */
	public void onClick(View v) {
		if (v == view.getBtBack()) {
			view.finish();
		} else if (v == view.getBtSave()) {
			saveSettings();
		}
		
		
	}

	/**
	 * Save the settings
	 */
	private void saveSettings() {
		try {
			ConfigUtils.getConfig().setIpAdress(view.getTextIp().getText().toString());
			ConfigUtils.getConfig().setLightTheme(view.getCkbLightTheme().isChecked());
			//ConfigUtils.getConfig().setCacheData(view.getCkbCacheData().isChecked());
			Toast.makeText(view, R.string.setting_save, Toast.LENGTH_SHORT).show();
			
			if (view.getCkbClearCache().isChecked()) {
				LoadingUtils.removeDataCache();
				Toast.makeText(view, R.string.clear_cache, Toast.LENGTH_SHORT).show();
			}
			view.finish();
		} catch (Exception e) {
			Toast.makeText(view, "Error during save !", Toast.LENGTH_SHORT).show();
			ErrorReporter.getInstance().handleException(e);
		}
	}



}
