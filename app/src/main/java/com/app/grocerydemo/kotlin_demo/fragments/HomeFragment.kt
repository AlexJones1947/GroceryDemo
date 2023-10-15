package com.app.grocerydemo.kotlin_demo.fragments

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.RelativeLayout
import com.app.grocerydemo.interfaces.Categorygridquantity
import androidx.activity.result.ActivityResultLauncher
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.app.grocerydemo.R
import androidx.recyclerview.widget.GridLayoutManager
import androidx.activity.result.ActivityResultCallback
import android.app.Activity
import android.content.ContentResolver
import android.graphics.Bitmap
import android.provider.MediaStore
import android.graphics.BitmapFactory
import com.app.grocerydemo.model.NewCategoryDataModel
import com.app.grocerydemo.model.NewCartModel
import android.widget.Toast
import android.annotation.SuppressLint
import androidx.core.content.FileProvider
import android.content.pm.ResolveInfo
import android.content.pm.PackageManager
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.app.grocerydemo.kotlin_demo.Adapter.FavoriteAdapter
import com.app.grocerydemo.kotlin_demo.db.DatabaseHandler
import com.app.grocerydemo.kotlin_demo.service.APIService
import com.app.grocerydemo.kotlin_demo.service.Config
import com.app.grocerydemo.kotlin_demo.util.MyUtils
import okhttp3.RequestBody
import okhttp3.MultipartBody
import com.app.grocerydemo.model.LoginMainModel
import com.google.gson.Gson
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {
    var tvTotal: TextView? = null
    var btnShopNOw: Button? = null
    var recyclerView: RecyclerView? = null
    var noData: RelativeLayout? = null
    var viewCart: RelativeLayout? = null
    var totalItems: TextView? = null

    // CategoryGridAdapter categoryGridAdapter;
    var categoryGridAdapter: FavoriteAdapter? = null
    private var db: DatabaseHandler? = null
    var categorygridquantity: Categorygridquantity? = null
    private var mSelectedPath = ""
    private var galleryActivityLauncher: ActivityResultLauncher<Intent>? = null
    private var galleryActivityResultLauncher: ActivityResultLauncher<String>? = null
    private var cameraResult: ActivityResultLauncher<Intent>? = null
    private var cameraActivityLauncher: ActivityResultLauncher<String>? = null
    private var mCurrentPhotoPath = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerCart)
        btnShopNOw = view.findViewById(R.id.btn_ShopNOw)
        viewCart = view.findViewById(R.id.viewCartItems)
        tvTotal = view.findViewById(R.id.txt_totalamount)
        totalItems = view.findViewById(R.id.txt_totalQuan)
        noData = view.findViewById(R.id.noData)
        db = DatabaseHandler(activity)
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity, 2)
        recyclerView!!.setLayoutManager(mLayoutManager)
        cameraGallery()

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                galleryActivityResultLauncher.launch(READ_EXTERNAL_STORAGE);
            } else {
                callAction();
            }
        } else {
            callAction();
        }*/

        /*int camera = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA);

        MyUtils.v("camera: " + camera);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (camera == -1) { // -1 don't have permission // 0 has permission
                cameraActivityLauncher.launch(Manifest.permission.CAMERA);
            } else {
                cameraCall();
            }
        } else {
            cameraCall();
        }*/setData()
        return view
    }

    private fun cameraGallery() {
        cameraActivityLauncher = registerForActivityResult<String, Boolean>(
            ActivityResultContracts.RequestPermission()
        ) { result ->
            if (result) {
                cameraCall()
                MyUtils.v("onActivityResult: PERMISSION GRANTED")
            } else {
                MyUtils.showPermissionAlert(activity)
                MyUtils.v("onActivityResult: PERMISSION DENIED")
            }
        }
        cameraResult = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val var14 = requireActivity().contentResolver
                var newPatrh = ""
                newPatrh = if (mCurrentPhotoPath.contains("file:")) {
                    mCurrentPhotoPath.replace("file:", "")
                } else mCurrentPhotoPath
                var ei: ExifInterface? = null
                try {
                    val btimap =
                        MediaStore.Images.Media.getBitmap(var14, Uri.parse(mCurrentPhotoPath))
                    ei = ExifInterface(newPatrh)
                    val orientation = ei.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED
                    )
                    var rotatedBitmap: Bitmap? = null
                    rotatedBitmap = when (orientation) {
                        ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(btimap, 90f)
                        ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(btimap, 180f)
                        ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(btimap, 270f)
                        ExifInterface.ORIENTATION_NORMAL -> btimap
                        else -> btimap
                    }
                    uploadEmployeeProfilePage(mCurrentPhotoPath, rotatedBitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        galleryActivityResultLauncher = registerForActivityResult<String, Boolean>(
            ActivityResultContracts.RequestPermission()
        ) { result ->
            if (result) {
                callAction()
                MyUtils.v("onActivityResult: PERMISSION GRANTED")
            } else {
                MyUtils.showPermissionAlert(activity)
                MyUtils.v("onActivityResult: PERMISSION DENIED")
            }
        }
        galleryActivityLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                try {
                    val data = result.data
                    val selectedImage = data?.data

                    //mSelectedPath = MyUtils.getPath(selectedImage, getActivity());
                    val path = MyUtils.getRealPathFromURI(selectedImage, activity)
                    mSelectedPath = if (path.contains("file:")) {
                        path.replace("file:", "")
                    } else path
                    var btimap: Bitmap? = null
                    try {
                        val var15: InputStream?
                        var15 = if (selectedImage != null) {
                            requireActivity().contentResolver.openInputStream(selectedImage)
                        } else {
                            null
                        }
                        btimap = BitmapFactory.decodeStream(var15)
                        var newPatrh = ""
                        newPatrh = if (mSelectedPath.contains("file:")) {
                            mSelectedPath.replace("file:", "")
                        } else mSelectedPath
                        val ei = ExifInterface(
                            newPatrh
                        )
                        val orientation = ei.getAttributeInt(
                            ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_UNDEFINED
                        )
                        var rotatedBitmap: Bitmap? = null
                        rotatedBitmap = when (orientation) {
                            ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(btimap, 90f)
                            ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(btimap, 180f)
                            ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(btimap, 270f)
                            ExifInterface.ORIENTATION_NORMAL -> btimap
                            else -> btimap
                        }
                        uploadEmployeeProfilePage(mSelectedPath, rotatedBitmap)
                    } catch (var12: Exception) {
                        var12.printStackTrace()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun setData() {
        val myFavModelList: List<NewCategoryDataModel> = ArrayList()
        val topSelling: List<NewCartModel> = ArrayList()
        if (myFavModelList != null) {
            if (myFavModelList.size == 0) {
                Toast.makeText(activity, "No data found.", Toast.LENGTH_SHORT).show()
            }
            //categoryGridAdapter = new CategoryGridAdapter( myFavModelList, getContext(),new Categorygridquantity() {
            categoryGridAdapter =
                FavoriteAdapter(context, topSelling, object : Categorygridquantity {
                    override fun onClick(view: View, position: Int, ccId: String, id: String) {}
                    override fun onCartItemAddOrMinus() {}
                    override fun onProductDetials(position: Int) {}
                })
            recyclerView!!.adapter = categoryGridAdapter
        } else {
            Toast.makeText(activity, "No data found.", Toast.LENGTH_SHORT).show()
        }
    }

    fun callAction() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        galleryActivityLauncher!!.launch(intent)
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun cameraCall() {
        val cacheDir = requireActivity().cacheDir.toString()
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        //File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "pictures");
        val path = File(cacheDir, "pictures")
        if (!path.exists()) {
            path.mkdir()
        }
        var imageFile: File? = null
        try {
            imageFile = File.createTempFile("image_$timeStamp", ".jpg", path)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val context = requireActivity().baseContext
        val uri = FileProvider.getUriForFile(
            context,
            context.applicationContext.packageName + ".provider",
            imageFile!!
        )
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
        val resInfoList =
            context.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        for (resolveInfo in resInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            context.grantUriPermission(
                packageName,
                uri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
        mCurrentPhotoPath = "file:" + imageFile.absolutePath
        cameraResult!!.launch(intent)
    }

    fun uploadEmployeeProfilePage(imageFile: String, bitmap: Bitmap?) {
//        Dialog loader = MyUtils.showLoader(getActivity());
//        loader.show();
        val apiService = Config.clientRota.create(
            APIService::class.java
        )
        // String clientCode = MyUtils.getClientCodeValue(mContext);
        //   String locationId = MyUtils.getLocationIdValue(mContext);
        //String empId = MyUtils.getEmployeeIdValue(mContext);
        // String accessToken = MyUtils.getAccessTokenValue(mContext);
        val clientCodePart =
            RequestBody.create(MediaType.parse("multipart/form-data"), "clientCode")
        val empIdPart = RequestBody.create(MediaType.parse("multipart/form-data"), "empId")
        val baos = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        val requestFile = RequestBody.create(MediaType.parse("image/*"), imageBytes)
        val fileNameNew = imageFile.substring(imageFile.lastIndexOf('/') + 1)
        val body = MultipartBody.Part.createFormData("EmployeeProfile", fileNameNew, requestFile)
        val call =
            apiService.uploadEmployeeProfilePage("accessToken", clientCodePart, empIdPart, body)
        MyUtils.v("URL is:- " + call!!.request().url().toString()) // print url
        call!!.enqueue(object : Callback<LoginMainModel?> {
            override fun onResponse(
                call: Call<LoginMainModel?>,
                response: Response<LoginMainModel?>
            ) {
                val lm = response.body()
                if (response.code() == 200) {
                    // loader.dismiss();
                    if (response.body() != null) {
                        val message = response.body()!!.message
                        if (response.body()!!.resultStatus) {
                            Log.e(
                                "TAG",
                                "response is uploadEmployeeProfilePage: " + Gson().toJson(response.body())
                            )

                            /* String imagePath = lm.getData().getEmployeeImageFullPath();
                            if (!TextUtils.isEmpty(imagePath)) {

                                Glide.with(getActivity()).asGif().load(R.drawable.progress_loader_white).into(ivLoader);
                                ivLoader.setVisibility(View.VISIBLE);
                                Glide.with(getActivity())
                                        .load(imagePath)
                                        .apply(new RequestOptions()
                                                .placeholder(R.drawable.ic_profile)
                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                .error(R.drawable.ic_profile))
                                        .listener(new RequestListener<Drawable>() {
                                            @Override
                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                ivLoader.setVisibility(View.GONE);
                                                return false;
                                            }

                                            @Override
                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                                ivLoader.setVisibility(View.GONE);
                                                return false;
                                            }
                                        })
                                        .into(iv_profile);
                            }
                            MyUtils.savePreferences(MyUtils.EMP_USER_PROFILE, imagePath, mContext);
                            tv_profile_name.setText(MyUtils.getEmpFullName(mContext));*/MyUtils.showMessage(
                                activity, message
                            )
                        } else {
                            MyUtils.showMessage(activity, message)
                        }
                    }
                } else if (response.code() == 401) {
                    //  loader.dismiss();
                    try {
                        val errorMessage = response.errorBody()!!.string()
                        MyUtils.showMessage(activity, errorMessage)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    //   MyUtils.goToLogin(getActivity());
                } else {
                    // loader.dismiss();
                    MyUtils.showMessage(activity, MyUtils.errorMessage)
                }
            }

            override fun onFailure(call: Call<LoginMainModel?>, t: Throwable) {
                // loader.dismiss();
                MyUtils.showMessage(activity, MyUtils.errorMessage)
                MyUtils.v("Response is:- " + t.message)
            }
        })
    }

    companion object {
        fun rotateImage(source: Bitmap?, angle: Float): Bitmap {
            val matrix = Matrix()
            matrix.postRotate(angle)
            return Bitmap.createBitmap(
                source!!, 0, 0, source.width, source.height,
                matrix, true
            )
        }
    }
}