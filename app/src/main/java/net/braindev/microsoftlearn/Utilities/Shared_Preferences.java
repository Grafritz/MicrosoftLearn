package net.braindev.microsoftlearn.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by JeanFritz on 5/2/2016.
 */
public class Shared_Preferences extends Activity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    //region [ ATTRIBUT ]
    private Long id;
    private Long NoUser;
    private Long ID_GroupeUser;
    private Byte PhotoByte;
    private String Photo;
    private String PhotoBase64String;
    /**
     * Not-null value.
     */
    private String UserName;
    private String Password;
    private String Civilite;
    private String Nom;
    private String Prenom;
    private String Sexe;
    private String Telephone;
    private String Telephone2;
    private String Email;
    private String DateDeNaissance;
    private String TypeDeVoix;
    private Long ID_StatutSocial;
    private String Remarques;
    private Long IDPAys;
    private Long IDCommune;
    private String Adresse;
    private Boolean AlerteNouveauteRezo;
    private Boolean AutreAlerte;
    private Boolean ActifYN;
    private String LastLogin;
    private Boolean ConnecterYN;
    private String LastIP;
    private Boolean MustChangePassword;
    private Boolean IsForcedOut;
    private String AskPassword;
    private Boolean Confirmer;
    private String Poste;
    private String CreatedBy;
    //private java.util.Date DateCreated;
    private String DateCreatedSTR;
    private String ModifBy;
    //private java.util.Date DateModif;

    private String groupeUserSTR;
    private String StatutSocialSTR;
    private Boolean IsValideAccount;

    //private FirebaseUser firebaseUser;
    private String PhotoUrl_FireBase;
    private String UidFireBase;
    private String FireBaseToken;

    //endregion

    //region [ PageWebView ]
    private String pageWebView;
    //endregion

    //region "CONTRUCTEURS"
    public Shared_Preferences(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }
    //endregion

    // Resets all settings to default./
    public void reset() {
        sharedPreferences.edit().clear().commit();
    }

    //region [ GET SET sharedPreferences ]
    public boolean get(String key, boolean defaultValue) {
        if (sharedPreferences == null)
            return defaultValue;

        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public String get(String key, String defaultValue) {
        if (sharedPreferences == null)
            return defaultValue;

        return sharedPreferences.getString(key, defaultValue);
    }

    public int get(String key, int defaultValue) {
        if (sharedPreferences == null)
            return defaultValue;

        return sharedPreferences.getInt(key, defaultValue);
    }
    //endregion

    //region [  ]
    public String getPageWebView() {
        this.pageWebView = this.sharedPreferences.getString("URL_MICROSOFT_LEARN_EN", "");
        return pageWebView;
    }

    public void setPageWebView(String pageWebView) {
        this.pageWebView = pageWebView;
        editor.putString("URL_MICROSOFT_LEARN_EN", this.pageWebView).commit();
    }
    //endregion


}
