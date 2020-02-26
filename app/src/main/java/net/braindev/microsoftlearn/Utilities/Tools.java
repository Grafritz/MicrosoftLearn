package net.braindev.microsoftlearn.Utilities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import net.braindev.microsoftlearn.BuildConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JeanFritz on 3/25/2016.
 */
public class Tools // extends Activity
{

    public static String TAG = "GRAFRITZ: ";
    private static Toast toast;
    private static int idMenuItemParametre;
    private static Dialog dialog;

    public static void ShowToast(Context context, String Message, int _Gravity) {
        toast = Toast.makeText(context, Message, Toast.LENGTH_LONG);
        if (_Gravity == Constant.GravityCenter)
            toast.setGravity(Constant.GravityCenter, 0, 0);
        else if (_Gravity == Constant.GravityBottom)
            toast.setGravity(Constant.GravityBottom, 0, 0);
        else if (_Gravity == Constant.GravityTop)
            toast.setGravity(Constant.GravityTop, 0, 0);
        else if (_Gravity == Constant.GravityEnd)
            toast.setGravity(Constant.GravityEnd, 0, 0);

        toast.show();
    }
    public static void ShowToast(Context context, String Message) {
        toast = Toast.makeText(context, Message, Toast.LENGTH_LONG);
        toast.setGravity(Constant.GravityBottom, 0, 0);
        toast.show();
    }
    public static void makeText(Context context, String Message) {
        Toast.makeText(context, Message, Toast.LENGTH_LONG).show();
    }

    public static boolean isShowWhenDebuging(){
        return BuildConfig.DEBUG;
        //return true;
    }
    public static void LogCat(Context context, String msg) {
        if( isShowWhenDebuging() ) {
            String TAG2 = TAG + context.getClass().getSimpleName();
            Log.i(TAG2, msg);
        }
    }
    public static void LogCat(String log, String msg) {
        if( isShowWhenDebuging() ) {
            if (log.equalsIgnoreCase("E"))
                Log.e(TAG, msg);
            if (log.equalsIgnoreCase("I"))
                Log.i(TAG, msg);
            if (log.equalsIgnoreCase("D"))
                Log.d(TAG, msg);
            if (log.equalsIgnoreCase("W"))
                Log.w(TAG, msg);
            if (log.equalsIgnoreCase("V"))
                Log.v(TAG, msg);
            else
                Log.e(TAG, msg);
        }
    }
    public static void LogCat(String msg) {
        if( isShowWhenDebuging() ) {
            Log.w(TAG, msg);
        }
    }
    public static void LogCatWithLog(String log, String msg) {
        if( isShowWhenDebuging() ) {
            if (log.equalsIgnoreCase("E"))
                Log.e(TAG, msg);
            if (log.equalsIgnoreCase("I"))
                Log.i(TAG, msg);
            if (log.equalsIgnoreCase("D"))
                Log.d(TAG, msg);
            if (log.equalsIgnoreCase("W"))
                Log.w(TAG, msg);
            if (log.equalsIgnoreCase("V"))
                Log.v(TAG, msg);
            else
                Log.e(TAG, msg);
        }
    }
    public static void LogCat(String msg, Exception ex) {
        if( isShowWhenDebuging() ) {Log.w(TAG, msg, ex);}
    }
    public static void LogCat(Exception ex) {
        if( isShowWhenDebuging() ) {Log.w(TAG, ex);}
    }
    public static void LogCat(Context context, Exception ex) {
        if ( isShowWhenDebuging() ) {
            String TAG2 = TAG + context.getClass().getSimpleName();
            Log.w(TAG2, ex);
        }
    }
    public static void LogCat(Context context, String msg, Exception ex) {
        if ( isShowWhenDebuging() ) {
            String TAG2 = TAG + context.getClass().getSimpleName();
            Log.w(TAG2, msg, ex);
        }
    }
    public static void LogCat(Context context, String msg, OutOfMemoryError ex) {
        if ( isShowWhenDebuging() ) {
            String TAG2 = TAG + context.getClass().getSimpleName();
            Log.w(TAG2, msg, ex);
        }
    }
    public static void LogCatAndShowToast(Context context, String msg) {
        String TAG2 = TAG + context.getClass().getSimpleName();
        if ( isShowWhenDebuging() ) {
            Log.i(TAG2, msg);
        }
        ShowToast(context, msg);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean GetTextFromHtml(String htmlText) {
        Pattern pattern1 = Pattern.compile("^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
        Pattern pattern = Pattern.compile("^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
        Matcher matcher = pattern.matcher(htmlText);
        return matcher.matches();
    }
    public static boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    public static void getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        String codeName = Build.VERSION.CODENAME;
        String incremental = Build.VERSION.INCREMENTAL;

        String APPLICATION_ID = BuildConfig.APPLICATION_ID;
        String VERSION_NAME = BuildConfig.VERSION_NAME;
        int VERSION_CODE = BuildConfig.VERSION_CODE;
        String FLAVOR = BuildConfig.FLAVOR;
        //return "Android SDK: " + sdkVersion + " (" + release +")";
        LogCat("Android SDK: MODEL:" + Build.MODEL
                + " \n (MANUFACTURER:" + Build.MANUFACTURER  + ")"
                + " \n (BRAND:" + Build.BRAND  + ")"
                + " \n (SERIAL:" + Build.SERIAL  + ")"
                + " \n (DISPLAY:" + Build.DISPLAY  + ")"
                + " \n (PRODUCT:" + Build.PRODUCT  + ")"
                + " \n (DEVICE:" + Build.DEVICE  + ")"
                + " \n (BOARD:" + Build.BOARD  + ")"
        );
        LogCat("Android SDK: SDK_INT:" + sdkVersion + " / (RELEASE:" + release + ")" + " / CODENAME:" + codeName + " / INCREMENTAL:" + incremental);
        LogCat("APPLICATION_ID:" + APPLICATION_ID + " / VERSION_NAME:" + VERSION_NAME + "" + " / VERSION_CODE:" + VERSION_CODE + " / FLAVOR:" + FLAVOR);
    }

    //region LOAD DATA
    public static String UTF8_WORD(String Sentences ) {
        String val = "";
        val = Sentences.replace("à", "a").replace("â", "a").replace("à", "a").replace("å", "a").replace("À", "a").replace("Á", "a").replace("Â", "a").replace("Ã", "a").replace("Ä", "a").replace("Å", "a");
        val = val.replace("é", "e").replace("è", "e").replace("ê", "e").replace("ë", "e").replace("È", "e").replace("É", "e").replace("ē", "e").replace("Ē", "e").replace("Ë", "e").replace("Ê", "e");
        val = val.replace("ò", "o").replace("ó", "o").replace("ô", "o").replace("õ", "o").replace("ö", "o").replace("Ò", "o").replace("Ó", "o").replace("Ô", "o").replace("Õ", "o").replace("Ö", "o");
        val = val.replace("ï", "i").replace("ì", "i").replace("í", "i").replace("î", "i").replace(",", "").replace("&", "");
        val = val.replace("ù", "u").replace("ú", "u").replace("û", "u").replace("ü", "u").replace(".", "").replace("%", "").replace("#", "").replace("@", "").replace("$", "").replace("æ", "").replace("↔", "");
        val = val.replace("'", "").replace("ç", "c").replace("+", "").replace("*", "").replace("\\", "");
        val = val.replace("/", "").replace("(", "").replace(")", "").replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("|", "");
        val = val.replace("<", "").replace(">", "").replace("   ", " ").replace("  ", " ").replace(" ", "_").replace("\"", "").replace("^", "").replace("`", "").replace("~", "").replace("!", "");
        val = val.replace(":", "").replace("-", "").replace("’", "");
        return val;
    }

    public static String UnCodeSpecialChar(String Sentences) {
        String val  = "";
        val = Sentences.replace("à", "a").replace("â", "a").replace("à", "a").replace("å", "a").replace("À", "a").replace("Á", "a").replace("Â", "a").replace("Ã", "a").replace("Ä", "a").replace("Å", "a");
        val = val.replace("é", "e").replace("è", "e").replace("ê", "e").replace("ë", "e").replace("È", "e").replace("É", "e").replace("ē", "e").replace("Ē", "e").replace("Ë", "e").replace("Ê", "e");
        val = val.replace("ò", "o").replace("ó", "o").replace("ô", "o").replace("õ", "o").replace("ö", "o").replace("Ò", "o").replace("Ó", "o").replace("Ô", "o").replace("Õ", "o").replace("Ö", "o");
        val = val.replace("ï", "i").replace("ì", "i").replace("í", "i").replace("î", "i");
        val = val.replace("ù", "u").replace("ú", "u").replace("û", "u").replace("ü", "u");
        val = val.replace("ç", "c");
        return val;
    }

    public static String DECODE_UTF_8(String Sentences) {
        String val = "";
        val = Sentences.replace("&", "&amp;").replace("÷", "&divide;");//.replace(">", "&lt;").replace(">", "&gt;");

        val = val.replace("À", "&Agrave;").replace("Á", "&Aacute;").replace("Â", "&Acirc;").replace("Ã", "&Atilde;").replace("Ä", "&Auml;").replace("Å", "&Aring;");

        val = val.replace("à", "&agrave;").replace("á", "&aacute;").replace("â", "&acirc;").replace("ã", "&atilde;").replace("ä", "&auml;").replace("å", "&aring;").replace("æ", "&aelig;");

        val = val.replace("ç", "&ccedil;").replace("Ç", "&Ccedil;");
        val = val.replace("È", "&Egrave;").replace("É", "&Eacute;").replace("Ê", "&Ecirc;").replace("Ë", "&Euml;");

        val = val.replace("è", "&egrave;").replace("é", "&eacute;").replace("ê", "&ecirc;").replace("ë", "&euml;");

        val = val.replace("Ì", "&Igrave;").replace("Í", "&Iacute;").replace("Î", "&Icirc;").replace("Ï", "&Iuml;");

        val = val.replace("ì", "&igrave;").replace("í", "&iacute;").replace("î", "&icirc;").replace("ï", "&iuml;");

        val = val.replace("Ñ", "&Ntilde;");

        val = val.replace( "Ò", "&Ograve;").replace( "Ó","&Oacute;").replace( "Ô","&Ocirc;").replace( "Õ","&Otilde;").replace( "Ö","&Ouml;");

        val = val.replace( "ð","&eth;").replace( "ò","&ograve;").replace( "ó","&oacute;").replace( "ô","&ocirc;").replace( "õ","&otilde;").replace( "ö","&ouml;");

        val = val.replace( "Ù","&Ugrave;").replace( "Ú","&Uacute;").replace( "Û","&Ucirc;").replace( "Ü","&Uuml;").replace( "Ý","&Yacute;");

        val = val.replace( "ñ","&ntilde;");

        val = val.replace( "ù","&ugrave;").replace( "ú","&uacute;").replace( "û","&ucirc;").replace( "ü","&uuml;");
        return val;
    }

    public static String UNCODE_UTF_8(String Sentences) {
        String val = "";
        val = Sentences.replace("&amp;", "&").replace("&lt;", ">").replace("&gt;", ">").replace("&divide;", "÷");

        val = val.replace("&Agrave;", "À").replace("&Aacute;", "Á").replace("&Acirc;", "Â").replace("&Atilde;", "Ã").replace("&Auml;", "Ä").replace("&Aring;", "Å");

        val = val.replace("&agrave;", "à").replace("&aacute;", "á").replace("&acirc;", "â").replace("&atilde;", "ã").replace("&auml;", "ä").replace("&aring;", "å").replace("&aelig;", "æ");

        val = val.replace("&ccedil;", "ç").replace("&Ccedil;", "Ç");

        val = val.replace("&Egrave;", "È").replace("&Eacute;", "É").replace("&Ecirc;", "Ê").replace("&Euml;", "Ë");

        val = val.replace("&egrave;", "è").replace("&eacute;", "é").replace("&ecirc;", "ê").replace("&euml;", "ë");

        val = val.replace("&Igrave;", "Ì").replace("&Iacute;", "Í").replace("&Icirc;", "Î").replace("&Iuml;", "Ï");

        val = val.replace("&igrave;", "ì").replace("&iacute;", "í").replace("&icirc;", "î").replace("&iuml;", "ï");

        val = val.replace("&Ntilde;", "Ñ");

        val = val.replace("&Ograve;", "Ò").replace("&Oacute;", "Ó").replace("&Ocirc;", "Ô").replace("&Otilde;", "Õ").replace("&Ouml;", "Ö");

        val = val.replace("&eth;", "ð").replace("&ograve;", "ò").replace("&oacute;", "ó").replace("&ocirc;", "ô").replace("&otilde;", "õ").replace("&ouml;", "ö");

        val = val.replace("&Ugrave;", "Ù").replace("&Uacute;", "Ú").replace("&Ucirc;", "Û").replace("&Uuml;", "Ü").replace("&Yacute;", "Ý");

        val = val.replace("&ntilde;", "ñ");

        val = val.replace("&ugrave;", "ù").replace("&uacute;", "ú").replace("&ucirc;", "û").replace("&uuml;", "ü");

        return val;
    }

    public static String ReplaceHtmlBreakAndStrongToSlashN(String Sentences) {
        String val = "";
        val = Sentences.replace("<br />", "\n");
        val = val.replace("</strong>", "\n");
        val = val.replace("<strong>", "");
        return val;
    }
    static String ReplaceHtmlBreakToSlashN(String Sentences) {
        String val = "";
        val = Sentences.replace("<br />", "\n");
        return val;
    }
    public static String ReplaceSlashNToHtmlBreak(String Sentences) {
        String val = "";
        val = Sentences.replace("\n", "<br />");
        return val;
    }
    public static String convertToHtmlString(String Sentences) {
        String val = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val = String.valueOf(Html.fromHtml("" + Sentences, Html.FROM_HTML_OPTION_USE_CSS_COLORS));
        } else {
            val = String.valueOf(Html.fromHtml("" + Sentences));
        }
        return val;
    }
    //endregion

    //region [ PERMISSION ]
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean CheckPermission_READ_EXTERNAL_STORAGE(final Context context){
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if( currentAPIVersion >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    //endregion


    //region [ Base64 ]
    public static String encodeToBase64(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int quality, boolean saveOnDevices){
        String encodeToBaseString="";
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(compressFormat, quality, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            encodeToBaseString = Base64.encodeToString(byteArray, Base64.NO_WRAP);

            //region TO SAVE
            if(saveOnDevices) {
                File destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                Tools.LogCat("destination: " + destination.toString());
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(byteArrayOutputStream.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    Tools.LogCat(e);
                } catch (IOException e) {
                    Tools.LogCat(e);
                }
            }
            //endregion
        }catch (Exception ex) {
            Tools.LogCat(ex);
        }
        return encodeToBaseString;
    }
    public static Bitmap decodeBase64(String dataStr){
        Bitmap bitmap=null;
        try{
            byte[] decodeBytes = Base64.decode(dataStr, 0);
            bitmap = BitmapFactory.decodeByteArray(decodeBytes, 0, decodeBytes.length);
        }catch (Exception ex) {
            Tools.LogCat(ex);
        }
        return bitmap;
    }
    //endregion

    /**
     * Converting dp to pixel
     */
    public static int dpToPx( Context context, int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    //region [ IMAGE BYTE[] ]
    public static byte[] recoverImageFromPath( String imagePath ) throws Exception {
        File imagefile = new File(imagePath);
        FileInputStream fis = new FileInputStream(imagefile);
        Bitmap bm = BitmapFactory.decodeStream(fis);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100 , stream);

        return stream.toByteArray();
    }
    public static byte[] ConvertFileToByteFromPath( String imagePath ) throws Exception {
        File file = new File(imagePath);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len = 0;
        while ((len = fis.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public static byte[] recoverImageFromUrl( String urlText ) throws Exception {
        URL url = new URL(urlText);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try (InputStream inputStream = url.openStream()) {
            int n = 0;
            byte [] buffer = new byte[ 1024 ];
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
        }

        return output.toByteArray();
    }
    public static byte[] ConvertUrlToBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    public static byte[] getImageBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    //endregion

    //region [ LOACLISATION ]
    public static void GetCountryName( Context context){
        try{
            String getCountry = context.getResources().getConfiguration().locale.getCountry();
            Locale locale;
            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ) {
                locale = context.getResources().getConfiguration().getLocales().get(0);
            }else{
                locale = context.getResources().getConfiguration().locale;
            }
            LogCat("toString:"+locale.toString());
            LogCat("getDisplayName:"+locale.getDisplayName());
            LogCat("getCountry:"+locale.getCountry());
            LogCat("getDisplayCountry:"+locale.getDisplayCountry());
            LogCat("getISO3Country:"+locale.getISO3Country());

            LogCat("getCountry2:"+getCountry);
        } catch (Exception e) {
            LogCat( "GetCountryName() - Exception    -> ", e);
            e.printStackTrace();
        }
    }

    public static String GetInitialLanguage( String language ) {
        if( language.equalsIgnoreCase("HT") ){
            return "C";
        }else if( language.equalsIgnoreCase("FR") ){
            return "F";
        }else if( language.equalsIgnoreCase("EN") ){
            return "E";
        }else if( language.equalsIgnoreCase("ES") ){
            return "E";
        }
        return "";
    }
    //endregion

    public static String GetFormatFileSize( long size ){
        String hrSize = "";
        try{
            double b = size;
            double k = size/1024.0;
            double m = (k/1024.0);
            double g = (m/1024.0);
            double t = (g/1024.0);

            DecimalFormat dec = new DecimalFormat("0.00");

            if( t > 1 ){
                hrSize = dec.format( t ).concat(" TB");
            }else if( g > 1 ){
                hrSize = dec.format( g ).concat(" GB");
            }else if( m > 1 ){
                hrSize = dec.format( m ).concat(" MB");
            }else if( k > 1 ){
                hrSize = dec.format( k ).concat(" KB");
            }else {
                hrSize = dec.format( b ).concat(" Bytes");
            }
        }catch (Exception e) {
            LogCat( "GetFormatFileSize() - Exception    -> ", e);
        }
        return hrSize;
    }

    public static void GetSetPath_Test(Context context) {

        ContextWrapper c = new ContextWrapper(context);

        Tools.LogCatAndShowToast(context, c.getPackageResourcePath() );
        Tools.LogCatAndShowToast(context, c.getFilesDir().getPath() );
        Tools.LogCatAndShowToast(context, c.getFilesDir().getPath().replace("user/0/","") );
        Tools.LogCatAndShowToast(context, c.getFilesDir().getAbsolutePath() );
        try {
            Tools.LogCatAndShowToast(context, c.getFilesDir().getCanonicalPath().toString() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        Tools.LogCatAndShowToast(context, "/16_RN/RNF_AUDIO_FR/5_RNF_Je_Veux_Monter_Sur_La_Montagne.mp3" );
        //

        String Path = "/Android"+ c.getFilesDir().getPath().replace("user/0/","")
                +"/16_RN/RNF_AUDIO_FR/5_RNF_Je_Veux_Monter_Sur_La_Montagne.mp3";
        Tools.LogCatAndShowToast(context, "Path: "+Path );

        File directory = context.getCacheDir();
        //String directory2 ="5_RNF_Je_Veux_Monter_Sur_La_Montagne.mp3";
        String fileName ="5_RNF_Je_Veux_Monter_Sur_La_Montagne.mp3";
        File filein = new File( directory, fileName );
        if ( filein.exists() ) {
            Tools.LogCatAndShowToast(context, " GOOD II ");
        }else{
            Tools.LogCatAndShowToast(context, " BAD DIR ");
            //filein.mkdirs();
        }
        //GetPathApplication1();
    }

    public static String GetPathApplication1() {
        String Android_Data = "/Android/data/";
        String Path = ""+Environment.getExternalStoragePublicDirectory( Android_Data );
        File file = new File(Path);
        if ( !file.exists() ) {
            file.mkdirs();
        }
        return Path;
    }

}
