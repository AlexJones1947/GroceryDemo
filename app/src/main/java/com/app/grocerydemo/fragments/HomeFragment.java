package com.app.grocerydemo.fragments;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.grocerydemo.Adapter.CategoryGridAdapter;
import com.app.grocerydemo.Adapter.FavoriteAdapter;
import com.app.grocerydemo.R;
import com.app.grocerydemo.db.DatabaseHandler;
import com.app.grocerydemo.interfaces.Categorygridquantity;
import com.app.grocerydemo.model.LoginMainModel;
import com.app.grocerydemo.model.NewCartModel;
import com.app.grocerydemo.model.NewCategoryDataModel;
import com.app.grocerydemo.service.APIService;
import com.app.grocerydemo.service.Config;
import com.app.grocerydemo.util.CurvedBottomNavigationView;
import com.app.grocerydemo.util.MyUtils;
import com.app.grocerydemo.util.SharedPref;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    TextView tvTotal;
    Button btnShopNOw;
    RecyclerView recyclerView;
    RelativeLayout noData;
    RelativeLayout viewCart;
    TextView totalItems;

   // CategoryGridAdapter categoryGridAdapter;
   FavoriteAdapter categoryGridAdapter;
    private DatabaseHandler db;
    Categorygridquantity categorygridquantity;

    private String mSelectedPath = "";
    private ActivityResultLauncher<Intent> galleryActivityLauncher;
    private ActivityResultLauncher<String> galleryActivityResultLauncher;
    private ActivityResultLauncher<Intent> cameraResult;
    private ActivityResultLauncher<String> cameraActivityLauncher;
    private String mCurrentPhotoPath = "";
    CurvedBottomNavigationView customNavigation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerCart);
        btnShopNOw = view.findViewById(R.id.btn_ShopNOw);
        viewCart = view.findViewById(R.id.viewCartItems);
        tvTotal = view.findViewById(R.id.txt_totalamount);
        totalItems = view.findViewById(R.id.txt_totalQuan);
        noData = view.findViewById(R.id.noData);
        this.db = new DatabaseHandler(getActivity());
        customNavigation = getActivity().findViewById(R.id.customBottomBar);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);


        cameraGallery();

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
        }*/



       setData();
        customNavigation.getOrCreateBadge(R.id.navigation_wallet).setNumber(5);

//        if (customNavigation != null) {
//            if (topSellingNew.size() > 0) {
//                customNavigation.getOrCreateBadge(R.id.navigation_wallet).setNumber(topSellingNew.size());
//            } else {
//                customNavigation.removeBadge(R.id.navigation_wallet);
//            }
//        }

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        List<HashMap<String, String>> map = db.getCartAll();
//        if (map.size() > 0) {
//            customNavigation.getOrCreateBadge(R.id.navigation_cart).setNumber(map.size());
//        } else {
//            customNavigation.removeBadge(R.id.navigation_cart);
//        }

        String mapDataFav = SharedPref.getListData(getActivity(), "GroceryDemo");
        if (!TextUtils.isEmpty(mapDataFav)) {
            Gson gson = new Gson();
            List<NewCartModel> topSelling = gson.fromJson(mapDataFav, new TypeToken<List<NewCartModel>>() {
            }.getType());

            List<NewCartModel> topSellingNew = new ArrayList<>();
            for (int index = 0; index < topSelling.size(); index++) {
                NewCartModel newCartModel = topSelling.get(index);
                if (!TextUtils.isEmpty(newCartModel.getIsFavorite()) && newCartModel.getIsFavorite().equals("true")) {
                    topSellingNew.add(newCartModel);
                }
            }

        }



        setData();
    }

    private void cameraGallery() {
        cameraActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if (result) {
                            cameraCall();
                            MyUtils.v("onActivityResult: PERMISSION GRANTED");
                        } else {
                            MyUtils.showPermissionAlert(getActivity());
                            MyUtils.v("onActivityResult: PERMISSION DENIED");
                        }
                    }
                });

        cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            ContentResolver var14 = getActivity().getContentResolver();
                            String newPatrh = "";
                            if (mCurrentPhotoPath.contains("file:")) {
                                newPatrh = mCurrentPhotoPath.replace("file:", "");
                            } else
                                newPatrh = mCurrentPhotoPath;

                            ExifInterface ei = null;
                            try {
                                Bitmap btimap = MediaStore.Images.Media.getBitmap(var14, Uri.parse(mCurrentPhotoPath));
                                ei = new ExifInterface(newPatrh);
                                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                        ExifInterface.ORIENTATION_UNDEFINED);

                                Bitmap rotatedBitmap = null;
                                switch (orientation) {

                                    case ExifInterface.ORIENTATION_ROTATE_90:
                                        rotatedBitmap = rotateImage(btimap, 90);
                                        break;

                                    case ExifInterface.ORIENTATION_ROTATE_180:
                                        rotatedBitmap = rotateImage(btimap, 180);
                                        break;

                                    case ExifInterface.ORIENTATION_ROTATE_270:
                                        rotatedBitmap = rotateImage(btimap, 270);
                                        break;

                                    case ExifInterface.ORIENTATION_NORMAL:
                                    default:
                                        rotatedBitmap = btimap;
                                }

                                uploadEmployeeProfilePage(mCurrentPhotoPath, rotatedBitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });

        galleryActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if (result) {
                            callAction();
                            MyUtils.v("onActivityResult: PERMISSION GRANTED");
                        } else {
                            MyUtils.showPermissionAlert(getActivity());
                            MyUtils.v("onActivityResult: PERMISSION DENIED");
                        }
                    }
                });


        galleryActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            try {
                                Intent data = result.getData();
                                Uri selectedImage = data != null ? data.getData() : null;

                                //mSelectedPath = MyUtils.getPath(selectedImage, getActivity());
                                String path = MyUtils.getRealPathFromURI(selectedImage, getActivity());
                                if (path.contains("file:")) {
                                    mSelectedPath = path.replace("file:", "");
                                } else
                                    mSelectedPath = path;
                                Bitmap btimap = null;

                                try {
                                    InputStream var15;
                                    if (selectedImage != null) {
                                        var15 = getActivity().getContentResolver().openInputStream(selectedImage);
                                    } else {
                                        var15 = null;
                                    }
                                    InputStream inputStream = var15;
                                    btimap = BitmapFactory.decodeStream(inputStream);


                                    String newPatrh = "";
                                    if (mSelectedPath.contains("file:")) {
                                        newPatrh = mSelectedPath.replace("file:", "");
                                    } else
                                        newPatrh = mSelectedPath;

                                    ExifInterface ei = new ExifInterface(newPatrh);
                                    int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                                            ExifInterface.ORIENTATION_UNDEFINED);

                                    Bitmap rotatedBitmap = null;
                                    switch (orientation) {

                                        case ExifInterface.ORIENTATION_ROTATE_90:
                                            rotatedBitmap = rotateImage(btimap, 90);
                                            break;

                                        case ExifInterface.ORIENTATION_ROTATE_180:
                                            rotatedBitmap = rotateImage(btimap, 180);
                                            break;

                                        case ExifInterface.ORIENTATION_ROTATE_270:
                                            rotatedBitmap = rotateImage(btimap, 270);
                                            break;

                                        case ExifInterface.ORIENTATION_NORMAL:
                                        default:
                                            rotatedBitmap = btimap;
                                    }

                                    uploadEmployeeProfilePage(mSelectedPath, rotatedBitmap);
                                } catch (Exception var12) {
                                    var12.printStackTrace();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    public void setData(){
        List<NewCategoryDataModel> myFavModelList = new ArrayList<>();
        List<NewCartModel> topSelling = new ArrayList<>();
        if(myFavModelList != null){
            if (myFavModelList.size() == 0) {
                Toast.makeText(getActivity(), "No data found.", Toast.LENGTH_SHORT).show();
            }

            //categoryGridAdapter = new CategoryGridAdapter( myFavModelList, getContext(),new Categorygridquantity() {
            categoryGridAdapter = new FavoriteAdapter( getContext(),topSelling,  new Categorygridquantity() {
                @Override
                public void onClick(View view, int position, String ccId, String id) {

                }

                @Override
                public void onCartItemAddOrMinus() {

                }

                @Override
                public void onProductDetials(int position) {

                }
            });
            recyclerView.setAdapter(categoryGridAdapter);
        }else  {
            Toast.makeText(getActivity(), "No data found.", Toast.LENGTH_SHORT).show();
        }
    }

    public void callAction() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryActivityLauncher.launch(intent);
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void cameraCall() {
        String cacheDir = String.valueOf(getActivity().getCacheDir());
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String timeStamp = (new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date());
        //File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "pictures");
        File path = new File(cacheDir, "pictures");

        if (!path.exists()) {
            path.mkdir();
        }

        File imageFile = null;
        try {
            imageFile = File.createTempFile("image_" + timeStamp, ".jpg", path);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Context context = getActivity().getBaseContext();
        Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", imageFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

        List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            context.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        mCurrentPhotoPath = "file:" + imageFile.getAbsolutePath();
        cameraResult.launch(intent);
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public void uploadEmployeeProfilePage(String imageFile, Bitmap bitmap) {
//        Dialog loader = MyUtils.showLoader(getActivity());
//        loader.show();

        APIService apiService = Config.getClientRota().create(APIService.class);
       // String clientCode = MyUtils.getClientCodeValue(mContext);
        //   String locationId = MyUtils.getLocationIdValue(mContext);
        //String empId = MyUtils.getEmployeeIdValue(mContext);
       // String accessToken = MyUtils.getAccessTokenValue(mContext);
        RequestBody clientCodePart = RequestBody.create(MediaType.parse("multipart/form-data"), "clientCode");
        RequestBody empIdPart = RequestBody.create(MediaType.parse("multipart/form-data"), "empId");


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageBytes);
        String fileNameNew = imageFile.substring(imageFile.lastIndexOf('/') + 1);
        MultipartBody.Part body = MultipartBody.Part.createFormData("EmployeeProfile", fileNameNew, requestFile);


        Call<LoginMainModel> call = apiService.uploadEmployeeProfilePage("accessToken", clientCodePart, empIdPart, body);
        MyUtils.v("URL is:- " + call.request().url().toString());  // print url
        call.enqueue(new Callback<LoginMainModel>() {
            @Override
            public void onResponse(Call<LoginMainModel> call, Response<LoginMainModel> response) {
                LoginMainModel lm = response.body();
                if (response.code() == 200) {
                   // loader.dismiss();
                    if (response.body() != null) {
                        String message = response.body().getMessage();
                        if (response.body().getResultStatus()) {
                            Log.e("TAG", "response is uploadEmployeeProfilePage: " + new Gson().toJson(response.body()));

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
                            tv_profile_name.setText(MyUtils.getEmpFullName(mContext));*/

                            MyUtils.showMessage(getActivity(), message);

                        } else {
                            MyUtils.showMessage(getActivity(), message);
                        }
                    }

                } else if (response.code() == 401) {
                  //  loader.dismiss();
                    try {
                        String errorMessage = response.errorBody().string();
                        MyUtils.showMessage(getActivity(), errorMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                 //   MyUtils.goToLogin(getActivity());
                } else {
                   // loader.dismiss();
                    MyUtils.showMessage(getActivity(), MyUtils.errorMessage);
                }
            }

            @Override
            public void onFailure(Call<LoginMainModel> call, Throwable t) {
               // loader.dismiss();
                MyUtils.showMessage(getActivity(), MyUtils.errorMessage);
                MyUtils.v("Response is:- " + t.getMessage());
            }
        });
    }
}
