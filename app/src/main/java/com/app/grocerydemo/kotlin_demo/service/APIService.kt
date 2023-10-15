package com.app.grocerydemo.kotlin_demo.service

import com.app.grocerydemo.model.LoginMainModel
import okhttp3.RequestBody
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import com.app.grocerydemo.model.MainListDataModel
import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @POST("api/login")
    fun loginUser(@Body body: Map<String?, String?>?): Call<LoginMainModel?>?

    @GET("api/LocationClockInTypeSetting")
    fun LocationClockInTypeSetting(
        @Header("Authorization") token: String?,
        @Query("ClientCode") ClientCode: String?,
        @Query("LocationId") LocationId: String?
    ): Call<LoginMainModel?>?

    @POST("api/EmployeeClockInVerification")
    fun EmployeeClockInVerification(
        @Header("Authorization") token: String?,
        @Body body: Map<String?, String?>?
    ): Call<LoginMainModel?>?

    @Multipart
    @POST("api/EmployeeTimesheet")
    fun EmployeeTimesheet(
        @Header("Authorization") accesstoken: String?,
        @Part("EmployeeId") employeeId: RequestBody?,
        @Part("LocationId") locationId: RequestBody?,
        @Part("ClockInTypeId") clockInTypeId: RequestBody?,
        @Part("ClockInOutType") clockInOutType: RequestBody?,
        @Part("ClockInTime") clockInTime: RequestBody?,
        @Part("ClientCode") clientCode: RequestBody?,
        @Part("DeviceType") deviceType: RequestBody?,
        @Part bodyImage: MultipartBody.Part?
    ): Call<LoginMainModel?>?

    @Multipart
    @POST("api/EmployeeTimesheet/SaveEmployeeFaceImage")
    fun saveEmployeeFaceImage(
        @Header("Authorization") accesstoken: String?,
        @Part("ClientCode") clientCode: RequestBody?,
        @Part("LocationId") locationId: RequestBody?,
        @Part("EmployeeId") employeeId: RequestBody?,
        @Part("PersonGroupId") PersonGroupId: RequestBody?,
        @Part bodyImage: MultipartBody.Part?
    ): Call<ResponseBody?>?

    @POST("api/EmployeeClockInVerification/VerifyQRCode")
    fun VerifyQRCode(
        @Header("Authorization") token: String?,
        @Body body: Map<String?, String?>?
    ): Call<LoginMainModel?>?

    //======================== Rota app ===================================//
    //    @GET("find/{key}")
    //    Call<FindAddressModel> findAddress(@Header("api-key") String key,
    //                                       @Path("key") String key_val);
    @POST("api/Login")
    fun login(@Body body: Map<String?, String?>?): Call<LoginMainModel?>?

    @GET("api/EmployeeRota/GetClockInData")
    fun getClockInData(
        @Header("Authorization") token: String?,
        @Query("EmployeeId") EmployeeId: String?,
        @Query("ClientCode") ClientCode: String?,
        @Query("Startdate") Startdate: String?,
        @Query("Enddate") Enddate: String?
    ): Call<MainListDataModel?>?

    @Multipart
    @POST("api/EmployeeProfilePage")
    fun uploadEmployeeProfilePage(
        @Header("Authorization") token: String?,
        @Part("ClientCode") ClientCode: RequestBody?,
        @Part("EmployeeId") EmployeeId: RequestBody?,
        @Part files: MultipartBody.Part?
    ): Call<LoginMainModel?>?

    @Multipart
    @POST("api/EmployeeDocument")
    fun uploadEmpDoc(
        @Header("Authorization") token: String?,
        @Part("ClientCode") ClientCode: RequestBody?,
        @Part("EmployeeId") EmployeeId: RequestBody?,
        @Part("TypeId") TypeId: RequestBody?,
        @Part("LocationId") LocationId: RequestBody?,
        @Part("FileDescription") FileDescription: RequestBody?,
        @Part files: MultipartBody.Part?
    ): Call<LoginMainModel?>?

    @FormUrlEncoded
    @POST("api/EmployeeDocument")
    fun editEmpDoc(
        @Header("Authorization") token: String?,
        @Field("ClientCode") ClientCode: String?,
        @Field("EmployeeId") EmployeeId: String?,
        @Field("TypeId") TypeId: String?,
        @Field("FileDescription") FileDescription: String?,
        @Field("UploadFileId") uploadFileId: String?
    ): Call<LoginMainModel?>?

    @DELETE("api/EmployeeDocument")
    fun deleteEmpDoc(
        @Header("Authorization") token: String?,
        @Query("ClientCode") ClientCode: String?,
        @Query("uploadfileId") uploadfileId: String?,
        @Query("filePath") filePath: String?
    ): Call<LoginMainModel?>?

    @GET("api/EmployeeDocument")
    fun getEmployeeDocumentEdit(
        @Header("Authorization") token: String?,
        @Query("EmployeeId") EmployeeId: String?,
        @Query("ClientCode") ClientCode: String?,
        @Query("UploadFileId") UploadFileId: String?
    ): Call<LoginMainModel?>?

    @GET
    fun downloadUrl(@Url url: String?): Call<ResponseBody?>?

    @GET("api/EmployeeDocument/DownloadFile")
    fun DownloadFile(@Query("filePath") url: String?): Call<ResponseBody?>?
}