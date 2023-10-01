package com.app.grocerydemo.service;


import com.app.grocerydemo.model.LoginMainModel;
import com.app.grocerydemo.model.MainListDataModel;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface APIService {

    @POST("api/login")
    Call<LoginMainModel> loginUser(@Body Map<String, String> body);


    @GET("api/LocationClockInTypeSetting")
    Call<LoginMainModel> LocationClockInTypeSetting(@Header("Authorization") String token,
                                                    @Query("ClientCode") String ClientCode,
                                                    @Query("LocationId") String LocationId);

    @POST("api/EmployeeClockInVerification")
    Call<LoginMainModel> EmployeeClockInVerification(@Header("Authorization") String token,
                                                     @Body Map<String, String> body);

    @Multipart
    @POST("api/EmployeeTimesheet")
    Call<LoginMainModel> EmployeeTimesheet(@Header("Authorization") String accesstoken,
                                           @Part("EmployeeId") RequestBody employeeId,
                                           @Part("LocationId") RequestBody locationId,
                                           @Part("ClockInTypeId") RequestBody clockInTypeId,
                                           @Part("ClockInOutType") RequestBody clockInOutType,
                                           @Part("ClockInTime") RequestBody clockInTime,
                                           @Part("ClientCode") RequestBody clientCode,
                                           @Part("DeviceType") RequestBody deviceType,
                                           @Part MultipartBody.Part bodyImage);

    @Multipart
    @POST("api/EmployeeTimesheet/SaveEmployeeFaceImage")
    Call<ResponseBody> saveEmployeeFaceImage(@Header("Authorization") String accesstoken,
                                               @Part("ClientCode") RequestBody clientCode,
                                               @Part("LocationId") RequestBody locationId,
                                               @Part("EmployeeId") RequestBody employeeId,
                                               @Part("PersonGroupId") RequestBody PersonGroupId,
                                               @Part MultipartBody.Part bodyImage);

    @POST("api/EmployeeClockInVerification/VerifyQRCode")
    Call<LoginMainModel> VerifyQRCode(@Header("Authorization") String token,
                                      @Body Map<String, String> body);

    //======================== Rota app ===================================//


//    @GET("find/{key}")
//    Call<FindAddressModel> findAddress(@Header("api-key") String key,
//                                       @Path("key") String key_val);


    @POST("api/Login")
    Call<LoginMainModel> login(@Body Map<String, String> body);


    @GET("api/EmployeeRota/GetClockInData")
    Call<MainListDataModel> getClockInData(@Header("Authorization") String token,
                                           @Query("EmployeeId") String EmployeeId,
                                           @Query("ClientCode") String ClientCode,
                                           @Query("Startdate") String Startdate,
                                           @Query("Enddate") String Enddate);

    @Multipart
    @POST("api/EmployeeProfilePage")
    Call<LoginMainModel> uploadEmployeeProfilePage(@Header("Authorization") String token,
                                                   @Part("ClientCode") RequestBody ClientCode,
                                                   @Part("EmployeeId") RequestBody EmployeeId,
                                                   @Part MultipartBody.Part files);


    @Multipart
    @POST("api/EmployeeDocument")
    Call<LoginMainModel> uploadEmpDoc(@Header("Authorization") String token,
                                      @Part("ClientCode") RequestBody ClientCode,
                                      @Part("EmployeeId") RequestBody EmployeeId,
                                      @Part("TypeId") RequestBody TypeId,
                                      @Part("LocationId") RequestBody LocationId,
                                      @Part("FileDescription") RequestBody FileDescription,
                                      @Part MultipartBody.Part files);

    @FormUrlEncoded
    @POST("api/EmployeeDocument")
    Call<LoginMainModel> editEmpDoc(@Header("Authorization") String token,
                                    @Field("ClientCode") String ClientCode,
                                    @Field("EmployeeId") String EmployeeId,
                                    @Field("TypeId") String TypeId,
                                    @Field("FileDescription") String FileDescription,
                                    @Field("UploadFileId") String uploadFileId);

    @DELETE("api/EmployeeDocument")
    Call<LoginMainModel> deleteEmpDoc(@Header("Authorization") String token,
                                      @Query("ClientCode") String ClientCode,
                                      @Query("uploadfileId") String uploadfileId,
                                      @Query("filePath") String filePath);

    @GET("api/EmployeeDocument")
    Call<LoginMainModel> getEmployeeDocumentEdit(@Header("Authorization") String token,
                                                 @Query("EmployeeId") String EmployeeId,
                                                 @Query("ClientCode") String ClientCode,
                                                 @Query("UploadFileId") String UploadFileId);


    @GET
    Call<ResponseBody> downloadUrl(@Url String url);

    @GET("api/EmployeeDocument/DownloadFile")
    Call<ResponseBody> DownloadFile(@Query("filePath") String url);

}
