package net.braindev.microsoftlearn.Utilities;

import android.view.Gravity;

import net.braindev.microsoftlearn.BuildConfig;

/**
 * Created by JeanFritz on 3/25/2016.
 */
public class Constant {

    //region URL
    public static String URL_MICROSOFT_LEARN_EN ="https://docs.microsoft.com/en-us/learn";
    public static String URL_MICROSOFT_LEARN_FR ="https://docs.microsoft.com/fr-fr/learn";

    public static String getUrlMicrosoftLearn(String langue) {
        return "https://docs.microsoft.com/"+langue+"/learn";
    }

    public static String url_Server_APIChant = "https://chant.rezo509.com/";//
    public static String url_Server_ApiRezo509 = "https://www.rezo509.com/";//
    public static String URL_PRIVACY_POLICY_CCG_ONLINE ="" + url_Server_ApiRezo509 + "PrivacyPolicy/PrivacyPolicyCCG.aspx";
    //endregion


    //region GRAVITY TYPE
    public static int GravityCenter = Gravity.CENTER;
    public static int GravityTop = Gravity.TOP;
    public static int GravityBottom = Gravity.BOTTOM;
    public static int GravityEnd = Gravity.END;
    //endregion


    //region [ AdMob ] ID=ca-app-pub-5335107298173193~6136651602
    public static String AdMobId_CCG   = "ca-app-pub-5335107298173193~6136651602";
    public static String AdMobId_CCG_TEST   = "ca-app-pub-3940256099942544~3347511713";
    public static String getAdMobId_CCG(){
        if( BuildConfig.DEBUG ) {
            Tools.LogCat(  "getAdMobId_CCG TEST ");
            return AdMobId_CCG_TEST;
        }else{
            return AdMobId_CCG;
        }
    }

    public static String AdMob_Interstitiel_1   = "ca-app-pub-5335107298173193/3228660509";
    public static String AdMob_CCG_Bannière_1   = "ca-app-pub-5335107298173193/5994424645";
    public static String NatifAvanceAds_2       = "ca-app-pub-5335107298173193/2898812695";

    public static String AdsRewardedVideo      = "ca-app-pub-5335107298173193/5640035575";
    public static String AdsRewardedVideoTEST      = "ca-app-pub-3940256099942544/5224354917";
    public static String getAdsRewardedVideo(){
        if( BuildConfig.DEBUG ) {
            Tools.LogCat(  "getAdsRewardedVideo TEST ");
            return AdsRewardedVideoTEST;
        }else{
            return AdsRewardedVideo;
        }
    }

    //endregion

    public static String duverseau_jeanfritz_gmail_com = "duverseau.jeanfritz@gmail.com";
    public static String chantchoraleetgroupe_gmail_com = "chantchoraleetgroupe@gmail.com";
    public static String info_rezo509_com = "info@rezo509.com";
    public static String jeudyfred_saskia_gmail_com = "jeudyfred.saskia@gmail.com";

    //region [Preferences]
    public static String Pref_FireBaseToken = "FireBaseToken";

    //endregion

    //region [ In-Apps Billing ( 08-Octobre2018 ) ]
    //public static final String SKU_ccg_remove_pub_devifinitively2   = "ccg.remove.pub.devifinitively";
    public static final String SKU_ccg_remove_pub_devifinitively    = "ccg.remove.pub.devifinitively2";    // $4,75
    public static final double SKU_ccg_remove_pub_devifinitively_Prix    = 4.75;

    public static final String SKU_ccg_remove_pub_yearly            = "ccg.remove.pub.yearly";            // $1,15
    public static final double SKU_ccg_remove_pub_yearly_Prix       = 1.15;
    //public static final String SKU_ccg_remove_pub_for6month         = "ccg.remove.pub.for6month";         // $0,99

    public static final String SKU_ccg_play_all_music_devifinitively        = "ccg.play.all.music.devifinitively"; // $2,75
    public static final double SKU_ccg_play_all_music_devifinitively_Prix   = 2.75;

    public static final String SKU_ccg_play_all_music_yearly            = "ccg.play.all.music.yearly";         // $1,99
    public static final double SKU_ccg_play_all_music_yearly_Prix       = 1.99;
    //public static final String SKU_ccg_play_all_music_for6month         = "ccg.play.all.music.for6month";      // $0,99

    //public static final String SKU_ccg_read_all_solfege_devifinitively  = "ccg.read.all.solfege.devifinitively"; // $2.75
    //public static final String SKU_ccg_read_all_solfege_yearly          = "ccg.read.all.solfege.yearly";         // $1,99
    //public static final String SKU_ccg_read_all_solfege_for6month       = "ccg.read.all.solfege.for6month";     // $0,99

    public static final String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsMjmVk5SXOtpFWQ/DohhX1BDPy0bOpmTFPtzcfrb69gpoo1zkLrpWYarOSVgHPbsWus7IJZX+MtZpEO3TeJqjf1fyrJN1LWfdEYLABImJ8lkT/gdSQYj3rrZ5i4HqvxKN6QfnGRObUF3hv5Rhp1yIFLnZbSu76EPOvkitZ4WYqq/y8hb0Fgcrf4qkubwMSJtJmU2K1fPcOePs/lkXNKPbj9Ovczrfitl8Df+7R5UiEm5AHxyWzywMTXX/hhTQxZgR+YgpGN9rPYGViwZZXNHIb55kiGR7DkiftaFfLz4qNTwBdkel2zUZXN0hfO5h7TkaEAqiC/v/Va9apieNvl3SQIDAQAB";

    //endregion
}
