package com.app.grocerydemo.kotlin_demo.util

import android.app.Activity
import android.os.Build.VERSION
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat
import android.Manifest.permission
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import com.app.grocerydemo.R
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.*
import android.database.Cursor
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.provider.Settings
import android.text.*
import androidx.drawerlayout.widget.DrawerLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import android.text.style.ClickableSpan
import android.text.method.LinkMovementMethod
import android.util.Base64
import android.util.Base64OutputStream
import android.util.Log
import android.util.Size
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.inputmethod.InputMethodManager
import com.app.grocerydemo.kotlin_demo.MainActivity
import java.io.*
import java.lang.Exception
import java.lang.NumberFormatException
import java.util.*
import java.util.regex.Pattern

object MyUtils {
    private const val TAG = "Clock In"
    private const val isDebug = true // for log detail set isDebug true
    var errorMessage = "Error while getting data."
    private val mSelectedTitle: String? = null
    const val FILE_MAX_SIZE = 10
    const val STORAGE_PERMISSION_REQUEST_CODE = 10
    const val PERMISSION_REQUEST_CODE = 2296
    fun v(value: String?) {
        // for log detail set isDebug true
        if (isDebug) Log.v(TAG, value!!)
    }

    const val PREFS_NAME = "ClockInApp"
    const val ACCESS_TOKEN = "ACCESS_TOKEN"
    const val CLIENT_CODE = "CLIENT_CODE"
    const val CLIENT_NAME = "CLIENT_NAME"
    fun savePreferences(item: String, value: String?, context: Context) {
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        val settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_MULTI_PROCESS)
        val editor = settings.edit()
        if (item.equals(CLIENT_CODE, ignoreCase = true)) {
            editor.putString(CLIENT_CODE, value)
        } else if (item.equals(CLIENT_NAME, ignoreCase = true)) {
            editor.putString(CLIENT_NAME, value)
        }
        editor.apply()
    }

    fun getClientCodeValue(context: Context): String? {
        val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_MULTI_PROCESS)
        return preference.getString(CLIENT_CODE, null)
    }

    fun getClientNameValue(context: Context): String? {
        val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_MULTI_PROCESS)
        return preference.getString(CLIENT_NAME, null)
    }

    fun isValidPassword(password: String?): Boolean {
        val EMAIL_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$"
        val pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

    fun goToLogin(activity: Activity) {
        val settings = activity.getSharedPreferences(PREFS_NAME, Context.MODE_MULTI_PROCESS)
        val editor = settings.edit()
        editor.clear()
        editor.apply()
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
        activity.finishAffinity()
    }

    fun checkPermission(activity: Activity?): Boolean {
        return if (VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else {
            val result =
                ContextCompat.checkSelfPermission(activity!!, permission.READ_EXTERNAL_STORAGE)
            val result1 =
                ContextCompat.checkSelfPermission(activity, permission.WRITE_EXTERNAL_STORAGE)
            result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
        }
    }

    fun currentTime(): String {
        val currentTime = Calendar.getInstance()
        val hour = currentTime[Calendar.HOUR_OF_DAY]
        val minute = currentTime[Calendar.MINUTE]
        val second = currentTime[Calendar.SECOND]
        return "$hour:$minute:$second"
    }

    fun hideKeyboard(context: Context?, view: View) {
        try {
            if (context != null) {
                val imm =
                    context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun checkInternetConnection(activity: Activity?, view: View?): Boolean {
        if (activity != null) {
            if (!isNetworkAvailable(activity)) {
                val errorMessage = "No internet connection!"
                showMessage(activity, errorMessage)
            } else {
                return true
            }
        }
        return false
    }

    fun disableTouch(layout: ViewGroup, enable: Boolean) {
        if (enable) {
            layout.isEnabled = true
            for (i in 0 until layout.childCount) {
                val child = layout.getChildAt(i)
                if (child is ViewGroup) {
                    disableTouch(child, true)
                } else {
                    child.isEnabled = true
                }
            }
        } else {
            layout.isEnabled = false
            for (i in 0 until layout.childCount) {
                val child = layout.getChildAt(i)
                if (child is ViewGroup) {
                    disableTouch(child, false)
                } else {
                    child.isEnabled = false
                }
            }
        }
    }

    fun isNetworkAvailable(activity: Activity): Boolean {
        // Using ConnectivityManager to check for Network Connection
        val connectivityManager = activity
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager
            .activeNetworkInfo
        return activeNetworkInfo != null
    }

    /*public static Dialog showLoader(Activity activity) {
        Dialog mDialog = new Dialog(activity, android.R.style.Theme_Translucent);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.custom_dailog_gif_loader);

        mDialog.getWindow().setLayout(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.MATCH_PARENT);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(R.color.colorBlack2)));
        mDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mDialog.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.colorPurple));
        mDialog.setCanceledOnTouchOutside(false);
        //mDialog.show();

        ImageView imageView = mDialog.findViewById(R.id.iv_gif_image);
        Glide.with(activity).asGif().load(R.drawable.progress_loader_white).into(imageView);
        //  Glide.with(activity).load(R.drawable.progress_loader_new).into(imageView);
        return mDialog;
    }*/
    /*public static Dialog showLoaderNew(Activity activity) {
        Dialog mDialog = new Dialog(activity, android.R.style.Theme_Translucent);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.custom_dailog_gif_loader);

        //   mDialog.getWindow().setLayout(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.MATCH_PARENT);
        //    mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(R.color.colorPurple)));
        mDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mDialog.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.colorPurple));
        mDialog.setCanceledOnTouchOutside(false);
        //mDialog.show();

        ImageView imageView = mDialog.findViewById(R.id.iv_gif_image);
        // Glide.with(activity).asGif().load(R.drawable.green_gif).into(imageView);

        Glide.with(activity).asGif().load(R.drawable.green_gif)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)).listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                //onGifFinished();//do your stuff
                Toast.makeText(activity, "onLoadFailed", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onResourceReady(final GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                resource.setLoopCount(1);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            if (!resource.isRunning()) {
                                //onGifFinished();//do your stuff
                                Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                    }
                }).start();
                return false;
            }
        }).into(imageView);
        //  Glide.with(activity).load(R.drawable.progress_loader_new).into(imageView);
        return mDialog;
    }*/
    fun showPermissionAlert(activity: Activity) {
        val alertDialog = AlertDialog.Builder(activity).create()
        alertDialog.setTitle(activity.resources.getString(R.string.permission_dialog_title))
        alertDialog.setMessage(activity.resources.getString(R.string.permission_dialog_msg))
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE,
            activity.resources.getString(R.string.permission_dialog_btn)
        ) { dialog, which ->
            dialog.dismiss()
            /* Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse(ScanQrCodeActivity.this.getPackageName()));
                                startActivity(intent);*/
            val i = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + activity.packageName)
            )
            activity.startActivity(i)
        }
        alertDialog.setOnShowListener { dialog ->
            val positiveButton = (dialog as AlertDialog)
                .getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.isAllCaps = false
        }
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    fun chooseOptimalSize(
        choices: Array<Size>, textureViewWidth: Int,
        textureViewHeight: Int, maxWidth: Int, maxHeight: Int, aspectRatio: Size
    ): Size {

        // Collect the supported resolutions that are at least as big as the preview Surface
        val bigEnough: MutableList<Size> = ArrayList()
        // Collect the supported resolutions that are smaller than the preview Surface
        val notBigEnough: MutableList<Size> = ArrayList()
        val w = aspectRatio.width
        val h = aspectRatio.height
        for (option in choices) {
            if (option.width <= maxWidth && option.height <= maxHeight && option.height == option.width * h / w) {
                if (option.width >= textureViewWidth &&
                    option.height >= textureViewHeight
                ) {
                    bigEnough.add(option)
                } else {
                    notBigEnough.add(option)
                }
            }
        }
        return if (bigEnough.size > 0) {
            Collections.min(bigEnough, CompareSizesByArea())
        } else if (notBigEnough.size > 0) {
            Collections.max(notBigEnough, CompareSizesByArea())
        } else {
            Log.e("tag", "Couldn't find any suitable preview size")
            choices[0]
        }
    }

    fun fileToBase64(f: File): String {
        var inputStream: InputStream? = null
        var encodedFile = ""
        val lastVal: String
        try {
            inputStream = FileInputStream(f.absolutePath)
            val buffer = ByteArray(10240) //specify the size to allow
            var bytesRead: Int
            val output = ByteArrayOutputStream()
            val output64 = Base64OutputStream(output, Base64.DEFAULT)
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                output64.write(buffer, 0, bytesRead)
            }
            output64.close()
            encodedFile = output.toString()
        } catch (e1: FileNotFoundException) {
            e1.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        lastVal = encodedFile
        return lastVal
    }

    private var contentUri: Uri? = null
    var context: Context? = null
    @SuppressLint("NewApi")
    fun getPath(uri: Uri, cnex: Context?): String? {
        context = cnex
        // check here to KITKAT or new version
        val isKitKat = VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
        var selection: String? = null
        var selectionArgs: Array<String>? = null
        // DocumentProvider
        if (isKitKat) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                val fullPath = getPathFromExtSD(split)
                return if (fullPath !== "") {
                    fullPath
                } else {
                    null
                }
            }


            // DownloadsProvider
            if (isDownloadsDocument(uri)) {
                if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val id: String
                    var cursor: Cursor? = null
                    try {
                        cursor = context!!.contentResolver.query(
                            uri,
                            arrayOf(MediaStore.MediaColumns.DISPLAY_NAME),
                            null,
                            null,
                            null
                        )
                        if (cursor != null && cursor.moveToFirst()) {
                            val fileName = cursor.getString(0)
                            val path = Environment.getExternalStorageDirectory()
                                .toString() + "/Download/" + fileName
                            if (!TextUtils.isEmpty(path)) {
                                return path
                            }
                        }
                    } finally {
                        cursor?.close()
                    }
                    id = DocumentsContract.getDocumentId(uri)
                    if (!TextUtils.isEmpty(id)) {
                        if (id.startsWith("raw:")) {
                            return id.replaceFirst("raw:".toRegex(), "")
                        }
                        val contentUriPrefixesToTry = arrayOf(
                            "content://downloads/public_downloads",
                            "content://downloads/my_downloads"
                        )
                        for (contentUriPrefix in contentUriPrefixesToTry) {
                            return try {
                                val contentUri = ContentUris.withAppendedId(
                                    Uri.parse(contentUriPrefix),
                                    java.lang.Long.valueOf(id)
                                )
                                getDataColumn(context, contentUri, null, null)
                            } catch (e: NumberFormatException) {
                                //In Android 8 and Android P the id is not a number
                                uri.path!!.replaceFirst("^/document/raw:".toRegex(), "")
                                    .replaceFirst("^raw:".toRegex(), "")
                            }
                        }
                    }
                } else {
                    val id = DocumentsContract.getDocumentId(uri)
                    if (id.startsWith("raw:")) {
                        return id.replaceFirst("raw:".toRegex(), "")
                    }
                    try {
                        contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"),
                            java.lang.Long.valueOf(id)
                        )
                    } catch (e: NumberFormatException) {
                        e.printStackTrace()
                    }
                    if (contentUri != null) {
                        return getDataColumn(context, contentUri, null, null)
                    }
                }
            }


            // MediaProvider
            if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                contentUri = if ("image" == type) {
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                } else {
                    MediaStore.Files.getContentUri("external")
                }
                selection = "_id=?"
                selectionArgs = arrayOf(split[1])
                return getDataColumn(
                    context, contentUri, selection,
                    selectionArgs
                )
            }
            if (isGoogleDriveUri(uri)) {
                return getDriveFilePath(uri)
            }
            if (isWhatsAppFile(uri)) {
                return getFilePathForWhatsApp(uri)
            }
            if ("content".equals(uri.scheme, ignoreCase = true)) {
                if (isGooglePhotosUri(uri)) {
                    return uri.lastPathSegment
                }
                if (isGoogleDriveUri(uri)) {
                    return getDriveFilePath(uri)
                }
                return if (VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                    // return getFilePathFromURI(context,uri);
                    copyFileToInternalStorage(uri, "userfiles")
                    // return getRealPathFromURI(context,uri);
                } else {
                    getDataColumn(context, uri, null, null)
                }
            }
            if ("file".equals(uri.scheme, ignoreCase = true)) {
                return uri.path
            }
        } else {
            if (isWhatsAppFile(uri)) {
                return getFilePathForWhatsApp(uri)
            }
            if ("content".equals(uri.scheme, ignoreCase = true)) {
                val projection = arrayOf(
                    MediaStore.Images.Media.DATA
                )
                var cursor: Cursor? = null
                try {
                    cursor = context!!.contentResolver
                        .query(uri, projection, selection, selectionArgs, null)
                    val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    if (cursor.moveToFirst()) {
                        return cursor.getString(column_index)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return null
    }

    private fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    private fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    private fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

    private fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri.authority
    }

    fun isWhatsAppFile(uri: Uri): Boolean {
        return "com.whatsapp.provider.media" == uri.authority
    }

    private fun isGoogleDriveUri(uri: Uri): Boolean {
        return "com.google.android.apps.docs.storage" == uri.authority || "com.google.android.apps.docs.storage.legacy" == uri.authority
    }

    private fun getDriveFilePath(uri: Uri): String {
        val returnCursor = context!!.contentResolver.query(
            uri,
            null,
            null,
            null,
            null
        )
        /*
         * Get the column indexes of the data in the Cursor,
         *     * move to the first row in the Cursor, get the data,
         *     * and display it.
         * */
        val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)
        val size = java.lang.Long.toString(returnCursor.getLong(sizeIndex))
        val file = File(context!!.cacheDir, name)
        try {
            val inputStream = context!!.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)
            var read = 0
            val maxBufferSize = 1 * 1024 * 1024
            val bytesAvailable = inputStream!!.available()

            //int bufferSize = 1024;
            val bufferSize = Math.min(bytesAvailable, maxBufferSize)
            val buffers = ByteArray(bufferSize)
            while (inputStream.read(buffers).also { read = it } != -1) {
                outputStream.write(buffers, 0, read)
            }
            Log.e("File Size", "Size " + file.length())
            inputStream.close()
            outputStream.close()
            Log.e("File Path", "Path " + file.path)
            Log.e("File Size", "Size " + file.length())
        } catch (e: Exception) {
            Log.e("Exception", e.message!!)
        }
        return file.path
    }

    /***
     * Used for Android Q+
     * @param uri
     * @param newDirName if you want to create a directory, you can set this variable
     * @return
     */
    private fun copyFileToInternalStorage(uri: Uri, newDirName: String): String {
        val returnCursor = context!!.contentResolver.query(
            uri, arrayOf(
                OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE
            ), null, null, null
        )


        /*
         * Get the column indexes of the data in the Cursor,
         *     * move to the first row in the Cursor, get the data,
         *     * and display it.
         * */
        val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)
        val size = java.lang.Long.toString(returnCursor.getLong(sizeIndex))
        val output: File
        output = if (newDirName != "") {
            val dir = File(context!!.filesDir.toString() + "/" + newDirName)
            if (!dir.exists()) {
                dir.mkdir()
            }
            File(context!!.filesDir.toString() + "/" + newDirName + "/" + name)
        } else {
            File(context!!.filesDir.toString() + "/" + name)
        }
        try {
            val inputStream = context!!.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(output)
            var read = 0
            val bufferSize = 1024
            val buffers = ByteArray(bufferSize)
            while (inputStream!!.read(buffers).also { read = it } != -1) {
                outputStream.write(buffers, 0, read)
            }
            inputStream.close()
            outputStream.close()
        } catch (e: Exception) {
            Log.e("Exception", e.message!!)
        }
        return output.path
    }

    private fun getFilePathForWhatsApp(uri: Uri): String {
        return copyFileToInternalStorage(uri, "whatsapp")
    }

    private fun fileExists(filePath: String): Boolean {
        val file = File(filePath)
        return file.exists()
    }

    private fun getDataColumn(
        context: Context?,
        uri: Uri?,
        selection: String?,
        selectionArgs: Array<String>?
    ): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)
        try {
            cursor = context!!.contentResolver.query(
                uri!!, projection,
                selection, selectionArgs, null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    private fun getPathFromExtSD(pathData: Array<String>): String {
        val type = pathData[0]
        val relativePath = "/" + pathData[1]
        var fullPath = ""

        // on my Sony devices (4.4.4 & 5.1.1), `type` is a dynamic string
        // something like "71F8-2C0A", some kind of unique id per storage
        // don't know any API that can get the root path of that storage based on its id.
        //
        // so no "primary" type, but let the check here for other devices
        if ("primary".equals(type, ignoreCase = true)) {
            fullPath = Environment.getExternalStorageDirectory().toString() + relativePath
            if (fileExists(fullPath)) {
                return fullPath
            }
        }

        // Environment.isExternalStorageRemovable() is `true` for external and internal storage
        // so we cannot relay on it.
        //
        // instead, for each possible path, check if file exists
        // we'll start with secondary storage as this could be our (physically) removable sd card
        fullPath = System.getenv("SECONDARY_STORAGE") + relativePath
        if (fileExists(fullPath)) {
            return fullPath
        }
        fullPath = System.getenv("EXTERNAL_STORAGE") + relativePath
        return if (fileExists(fullPath)) {
            fullPath
        } else fullPath
    }

    fun isValidEmail(email: String?): Boolean {
        val EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        val pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun showMessage(activity: Context?, message: String?) {
        val mDialog = Dialog(activity!!)
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog.setContentView(R.layout.custom_dailog__show_message)
        mDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        mDialog.window!!.setLayout(
            DrawerLayout.LayoutParams.MATCH_PARENT,
            DrawerLayout.LayoutParams.WRAP_CONTENT
        )
        mDialog.setCanceledOnTouchOutside(false)
        val btn_submit = mDialog.findViewById<TextView>(R.id.btn_submit)
        val tv_text_message = mDialog.findViewById<TextView>(R.id.tv_text_message)
        tv_text_message.text = message
        btn_submit.setOnClickListener { mDialog.dismiss() }
        mDialog.show()
    }

    //    public static void setViewAndChildrenEnabled(View view, boolean enabled, Context context) {
    //        int padding = (int) context.getResources().getDimension(R.dimen.slider);
    //        if (enabled) {
    //            view.setBackgroundResource(R.drawable.radius_cardview_login);
    //            view.setPadding(padding, padding, padding, padding);
    //        } else {
    //            view.setBackgroundResource(R.drawable.radius_cardview_disable_layout);
    //            view.setPadding(padding, padding, padding, padding);
    //        }
    //        view.setEnabled(enabled);
    //        if (view instanceof ViewGroup) {
    //            ViewGroup viewGroup = (ViewGroup) view;
    //            for (int i = 0; i < viewGroup.getChildCount(); i++) {
    //                View child = viewGroup.getChildAt(i);
    //                setViewAndChildrenEnabled(child, enabled, context);
    //            }
    //        }
    //    }
    fun checkStoragePermission(activity: Activity): Boolean {
        return if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    activity.requestPermissions(
                        arrayOf(
                            permission.READ_EXTERNAL_STORAGE,
                            permission.WRITE_EXTERNAL_STORAGE
                        ), STORAGE_PERMISSION_REQUEST_CODE
                    )
                } else if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    activity.requestPermissions(
                        arrayOf(
                            permission.READ_EXTERNAL_STORAGE,
                            permission.WRITE_EXTERNAL_STORAGE
                        ), STORAGE_PERMISSION_REQUEST_CODE
                    )
                }
                false
            } else {
                true
            }
        } else {
            true
        }
    }

    fun clickify(textView: TextView, clickableText: String, activity: Activity) {
        val text = textView.text
        val string = text.toString()
        val span2: ClickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {}
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                //   Typeface face = Typeface.createFromAsset(context.getAssets(), "JosefinSans-Bold.ttf");
                //  ds.setTypeface(face);
                ds.color = activity.resources.getColor(R.color.darkGrey)
                ds.isUnderlineText = false
            }
        }
        val start = string.indexOf(clickableText)
        val end = start + clickableText.length
        if (start == -1) return
        if (text is Spannable) {
            text.setSpan(span2, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        } else {
            val s = SpannableString.valueOf(text)
            s.setSpan(span2, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            textView.text = s
        }
        val m = textView.movementMethod
        if (m == null || m !is LinkMovementMethod) {
            textView.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    fun requestPermission(activity: Activity) {
        if (VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.addCategory("android.intent.category.DEFAULT")
                intent.data = Uri.parse(String.format("package:%s", activity.packageName))
                activity.startActivityForResult(intent, PERMISSION_REQUEST_CODE)
            } catch (e: Exception) {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                activity.startActivityForResult(intent, PERMISSION_REQUEST_CODE)
            }
        } else {
            //below android 11
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(permission.WRITE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    fun getRealPathFromURI(uri: Uri, context: Context): String {
        val returnCursor = context.contentResolver.query(uri, null, null, null, null)
        val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)
        val size = java.lang.Long.toString(returnCursor.getLong(sizeIndex))
        val file = File(context.filesDir, name)
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)
            var read = 0
            val maxBufferSize = 1 * 1024 * 1024
            val bytesAvailable = inputStream!!.available()

            //int bufferSize = 1024;
            val bufferSize = Math.min(bytesAvailable, maxBufferSize)
            val buffers = ByteArray(bufferSize)
            while (inputStream.read(buffers).also { read = it } != -1) {
                outputStream.write(buffers, 0, read)
            }
            Log.e("File Size", "Size " + file.length())
            inputStream.close()
            outputStream.close()
            Log.e("File Path", "Path " + file.path)
            Log.e("File Size", "Size " + file.length())
        } catch (e: Exception) {
            Log.e("Exception", e.message!!)
        }
        return file.path
    }

    class CompareSizesByArea : Comparator<Size> {
        override fun compare(lhs: Size, rhs: Size): Int {
            return java.lang.Long.signum(
                lhs.width.toLong() * lhs.height -
                        rhs.width.toLong() * rhs.height
            )
        }
    }

    /*public static Dialog showMenu(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dailog_show_menu);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(DrawerLayout.LayoutParams.WRAP_CONTENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

        wmlp.gravity = Gravity.TOP | Gravity.END;
        wmlp.x = -70;   //x position
        wmlp.y = 85;   //y position
        wmlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        Window window = dialog.getWindow();
        if (window != null) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // This flag is required to set otherwise the setDimAmount method will not show any effect
            window.setDimAmount(0.5f); //0 for no dim to 1 for full dim
        }

        //dialog.getWindow().setGravity(Gravity.TOP);
        dialog.setCanceledOnTouchOutside(true);

        dialog.show();

        return dialog;
    }*/
    interface ClickListener {
        fun onClick(view: View?, position: Int)
        fun onLongClick(view: View?, position: Int)
    }

    class RecyclerTouchListener(
        context: Context?,
        recycleView: RecyclerView,
        private val clicklistener: ClickListener?
    ) : OnItemTouchListener {
        private val gestureDetector: GestureDetector
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

        init {
            gestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recycleView.findChildViewUnder(e.x, e.y)
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child))
                    }
                }
            })
        }
    }

    interface OnClickListener {
        fun onClick()
    }
}