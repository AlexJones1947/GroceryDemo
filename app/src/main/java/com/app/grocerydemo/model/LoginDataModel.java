package com.app.grocerydemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginDataModel {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("clientCode")
    @Expose
    private String clientCode;
    @SerializedName("clientName")
    @Expose
    private String clientName;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("settingType")
    @Expose
    private String settingType;
    @SerializedName("settingTypeId")
    @Expose
    private String settingTypeId;
    @SerializedName("employeeId")
    @Expose
    private String employeeId;
    @SerializedName("clockInImage")
    @Expose
    private String clockInImage;
    @SerializedName("clockInTime")
    @Expose
    private String clockInTime;
    @SerializedName("clientLogoUrl")
    @Expose
    private String clientLogoUrl;
    @SerializedName("enableClockIn")
    @Expose
    private boolean enableClockIn;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("middleName")
    @Expose
    private String middleName;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("clockInImageFullPath")
    @Expose
    private String clockInImageFullPath;
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("qrcode")
    @Expose
    private String qrcode;
    @SerializedName("employeeImageFullPath")
    @Expose
    private String employeeImageFullPath;
    @SerializedName("pinnumber")
    @Expose
    private Integer pinnumber;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("personGroupId")
    @Expose
    private String personGroupId;

    public String getPersonGroupId() {
        return personGroupId;
    }

    public void setPersonGroupId(String personGroupId) {
        this.personGroupId = personGroupId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @SerializedName("personId")
    @Expose
    private String personId;

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public boolean isPersonalDetailFormSaved() {
        return isPersonalDetailFormSaved;
    }

    public void setPersonalDetailFormSaved(boolean personalDetailFormSaved) {
        isPersonalDetailFormSaved = personalDetailFormSaved;
    }

    public String getIsDeclarationRequire() {
        return isDeclarationRequire;
    }

    public void setIsDeclarationRequire(String isDeclarationRequire) {
        this.isDeclarationRequire = isDeclarationRequire;
    }

    @SerializedName("isPersonalDetailFormSaved")
    @Expose
    private boolean isPersonalDetailFormSaved;

    public boolean isIswagesDetailExist() {
        return iswagesDetailExist;
    }

    public void setIswagesDetailExist(boolean iswagesDetailExist) {
        this.iswagesDetailExist = iswagesDetailExist;
    }

    @SerializedName("iswagesDetailExist")
    @Expose
    private boolean iswagesDetailExist;
    @SerializedName("isDeclarationRequire")
    @Expose
    private String isDeclarationRequire;

    public boolean getIsWagesRequire() {
        return isWagesRequire;
    }

    public void setIsWagesRequire(boolean isWagesRequire) {
        isWagesRequire = isWagesRequire;
    }

    @SerializedName("isWagesRequire")
    @Expose
    private boolean isWagesRequire;

    public String getEmployeeImageFullPath() {
        return employeeImageFullPath;
    }

    public void setEmployeeImageFullPath(String employeeImageFullPath) {
        this.employeeImageFullPath = employeeImageFullPath;
    }

    public String getIsSortCodeExists() {
        return isSortCodeExists;
    }

    public void setIsSortCodeExists(String isSortCodeExists) {
        this.isSortCodeExists = isSortCodeExists;
    }

    public Boolean getExistMobilePhone() {
        return isExistMobilePhone;
    }

    public void setExistMobilePhone(Boolean existMobilePhone) {
        isExistMobilePhone = existMobilePhone;
    }

    public Boolean getExistWorkEmail() {
        return isExistWorkEmail;
    }

    public void setExistWorkEmail(Boolean existWorkEmail) {
        isExistWorkEmail = existWorkEmail;
    }

    public Boolean getExistNextofkin() {
        return isExistNextofkin;
    }

    public void setExistNextofkin(Boolean existNextofkin) {
        isExistNextofkin = existNextofkin;
    }

    @SerializedName("isSortCodeExists")
    @Expose
    private String isSortCodeExists;

    public Integer getPinnumber() {
        return pinnumber;
    }

    public void setPinnumber(Integer pinnumber) {
        this.pinnumber = pinnumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getClockInImageFullPath() {
        return clockInImageFullPath;
    }

    public void setClockInImageFullPath(String clockInImageFullPath) {
        this.clockInImageFullPath = clockInImageFullPath;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getClientLogoUrl() {
        return clientLogoUrl;
    }

    public void setClientLogoUrl(String clientLogoUrl) {
        this.clientLogoUrl = clientLogoUrl;
    }

    public boolean isEnableClockIn() {
        return enableClockIn;
    }

    public void setEnableClockIn(boolean enableClockIn) {
        this.enableClockIn = enableClockIn;
    }

    public String getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(String clockInTime) {
        this.clockInTime = clockInTime;
    }

    public String getClockInImage() {
        return clockInImage;
    }

    public void setClockInImage(String clockInImage) {
        this.clockInImage = clockInImage;
    }

    public String getSettingTypeId() {
        return settingTypeId;
    }

    public void setSettingTypeId(String settingTypeId) {
        this.settingTypeId = settingTypeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSettingType() {
        return settingType;
    }

    public void setSettingType(String settingType) {
        this.settingType = settingType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String authorization) {
        token = authorization;
    }



    @SerializedName("specialShiftGroupId")
    @Expose
    private String specialShiftGroupId;
    @SerializedName("lastActualCloseDate")
    @Expose
    private String lastActualCloseDate;
    @SerializedName("holidayYear")
    @Expose
    private String holidayYear;
    @SerializedName("maxAllowed")
    @Expose
    private Double maxAllowed;
    @SerializedName("entitlement")
    @Expose
    private Double entitlement;
    @SerializedName("opnBalance")
    @Expose
    private Double opnBalance;
    @SerializedName("accrued")
    @Expose
    private Double accrued;
    @SerializedName("taken")
    @Expose
    private Double taken;
    @SerializedName("accruedBalance")
    @Expose
    private Double accruedBalance;
    @SerializedName("booked")
    @Expose
    private Double booked;
    @SerializedName("allowanceBalance")
    @Expose
    private Double allowanceBalance;

    public String getSpecialShiftGroupId() {
        return specialShiftGroupId;
    }

    public void setSpecialShiftGroupId(String specialShiftGroupId) {
        this.specialShiftGroupId = specialShiftGroupId;
    }

    public String getLastActualCloseDate() {
        return lastActualCloseDate;
    }

    public void setLastActualCloseDate(String lastActualCloseDate) {
        this.lastActualCloseDate = lastActualCloseDate;
    }

    public String getHolidayYear() {
        return holidayYear;
    }

    public void setHolidayYear(String holidayYear) {
        this.holidayYear = holidayYear;
    }

    public Double getMaxAllowed() {
        return maxAllowed;
    }

    public void setMaxAllowed(Double maxAllowed) {
        this.maxAllowed = maxAllowed;
    }

    public Double getEntitlement() {
        return entitlement;
    }

    public void setEntitlement(Double entitlement) {
        this.entitlement = entitlement;
    }

    public Double getOpnBalance() {
        return opnBalance;
    }

    public void setOpnBalance(Double opnBalance) {
        this.opnBalance = opnBalance;
    }

    public Double getAccrued() {
        return accrued;
    }

    public void setAccrued(Double accrued) {
        this.accrued = accrued;
    }

    public Double getTaken() {
        return taken;
    }

    public void setTaken(Double taken) {
        this.taken = taken;
    }

    public Double getAccruedBalance() {
        return accruedBalance;
    }

    public void setAccruedBalance(Double accruedBalance) {
        this.accruedBalance = accruedBalance;
    }

    public Double getBooked() {
        return booked;
    }

    public void setBooked(Double booked) {
        this.booked = booked;
    }

    public Double getAllowanceBalance() {
        return allowanceBalance;
    }

    public void setAllowanceBalance(Double allowanceBalance) {
        this.allowanceBalance = allowanceBalance;
    }

    @SerializedName("locationId")
    @Expose
    private String locationId;
    @SerializedName("countryId")
    @Expose
    private String countryId;
    @SerializedName("nationalityId")
    @Expose
    private String nationalityId;
    @SerializedName("eligibilityId")
    @Expose
    private String eligibilityId;
    @SerializedName("proofOEligibilityComments")
    @Expose
    private String proofOEligibilityComments;
    @SerializedName("passportNumber")
    @Expose
    private String passportNumber;
    @SerializedName("passportExpiryDate")
    @Expose
    private String passportExpiryDate;
    @SerializedName("indefiniteLeave")
    @Expose
    private Boolean indefiniteLeave;
    @SerializedName("documentSorCopied")
    @Expose
    private Boolean documentSorCopied;
    @SerializedName("workPermit")
    @Expose
    private Boolean workPermit;
    @SerializedName("permitType")
    @Expose
    private String permitType;
    @SerializedName("permitNumber")
    @Expose
    private String permitNumber;
    @SerializedName("permitExpiryDate")
    @Expose
    private String permitExpiryDate;


    public Boolean getReturnString() {
        return returnString;
    }

    public void setReturnString(Boolean returnString) {
        this.returnString = returnString;
    }

    @SerializedName("returnString")
    @Expose
    private Boolean returnString;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(String nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getEligibilityId() {
        return eligibilityId;
    }

    public void setEligibilityId(String eligibilityId) {
        this.eligibilityId = eligibilityId;
    }

    public String getProofOEligibilityComments() {
        return proofOEligibilityComments;
    }

    public void setProofOEligibilityComments(String proofOEligibilityComments) {
        this.proofOEligibilityComments = proofOEligibilityComments;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportExpiryDate() {
        return passportExpiryDate;
    }

    public void setPassportExpiryDate(String passportExpiryDate) {
        this.passportExpiryDate = passportExpiryDate;
    }

    public Boolean getIndefiniteLeave() {
        return indefiniteLeave;
    }

    public void setIndefiniteLeave(Boolean indefiniteLeave) {
        this.indefiniteLeave = indefiniteLeave;
    }

    public Boolean getDocumentSorCopied() {
        return documentSorCopied;
    }

    public void setDocumentSorCopied(Boolean documentSorCopied) {
        this.documentSorCopied = documentSorCopied;
    }

    public Boolean getWorkPermit() {
        return workPermit;
    }

    public void setWorkPermit(Boolean workPermit) {
        this.workPermit = workPermit;
    }

    public String getPermitType() {
        return permitType;
    }

    public void setPermitType(String permitType) {
        this.permitType = permitType;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    public String getPermitExpiryDate() {
        return permitExpiryDate;
    }

    public void setPermitExpiryDate(String permitExpiryDate) {
        this.permitExpiryDate = permitExpiryDate;
    }


    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("probationEndDate")
    @Expose
    private String probationEndDate;
    @SerializedName("employeeNumber")
    @Expose
    private String employeeNumber;
    @SerializedName("employeeStatus")
    @Expose
    private String employeeStatus;
    @SerializedName("payrollFrequency")
    @Expose
    private String payrollFrequency;
    @SerializedName("workingDays")
    @Expose
    private String workingDays;
    @SerializedName("payType")
    @Expose
    private String payType;
    @SerializedName("payRate")
    @Expose
    private Double payRate;
    @SerializedName("annualSalary")
    @Expose
    private Double annualSalary;
    @SerializedName("daysPerWeek")
    @Expose
    private Double daysPerWeek;
    @SerializedName("hrsPerWeek")
    @Expose
    private Double hrsPerWeek;
    @SerializedName("shiftPerWeek")
    @Expose
    private Double shiftPerWeek;
    @SerializedName("annualTronc")
    @Expose
    private Double annualTronc;
    @SerializedName("annualTroncPerDay")
    @Expose
    private Double annualTroncPerDay;
    @SerializedName("troncRate")
    @Expose
    private Double troncRate;
    @SerializedName("troncPoint")
    @Expose
    private Double troncPoint;
    @SerializedName("troncRateSubType")
    @Expose
    private String troncRateSubType;
    @SerializedName("troncPointSubType")
    @Expose
    private String troncPointSubType;
    @SerializedName("weekDays")
    @Expose
    private String weekDays;


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProbationEndDate() {
        return probationEndDate;
    }

    public void setProbationEndDate(String probationEndDate) {
        this.probationEndDate = probationEndDate;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getPayrollFrequency() {
        return payrollFrequency;
    }

    public void setPayrollFrequency(String payrollFrequency) {
        this.payrollFrequency = payrollFrequency;
    }

    public String getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(String workingDays) {
        this.workingDays = workingDays;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Double getPayRate() {
        return payRate;
    }

    public void setPayRate(Double payRate) {
        this.payRate = payRate;
    }

    public Double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(Double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public Double getDaysPerWeek() {
        return daysPerWeek;
    }

    public void setDaysPerWeek(Double daysPerWeek) {
        this.daysPerWeek = daysPerWeek;
    }

    public Double getHrsPerWeek() {
        return hrsPerWeek;
    }

    public void setHrsPerWeek(Double hrsPerWeek) {
        this.hrsPerWeek = hrsPerWeek;
    }

    public Double getShiftPerWeek() {
        return shiftPerWeek;
    }

    public void setShiftPerWeek(Double shiftPerWeek) {
        this.shiftPerWeek = shiftPerWeek;
    }

    public Double getAnnualTronc() {
        return annualTronc;
    }

    public void setAnnualTronc(Double annualTronc) {
        this.annualTronc = annualTronc;
    }

    public Double getAnnualTroncPerDay() {
        return annualTroncPerDay;
    }

    public void setAnnualTroncPerDay(Double annualTroncPerDay) {
        this.annualTroncPerDay = annualTroncPerDay;
    }

    public Double getTroncRate() {
        return troncRate;
    }

    public void setTroncRate(Double troncRate) {
        this.troncRate = troncRate;
    }

    public Double getTroncPoint() {
        return troncPoint;
    }

    public void setTroncPoint(Double troncPoint) {
        this.troncPoint = troncPoint;
    }

    public String getTroncRateSubType() {
        return troncRateSubType;
    }

    public void setTroncRateSubType(String troncRateSubType) {
        this.troncRateSubType = troncRateSubType;
    }

    public String getTroncPointSubType() {
        return troncPointSubType;
    }

    public void setTroncPointSubType(String troncPointSubType) {
        this.troncPointSubType = troncPointSubType;
    }

    public String getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(String weekDays) {
        this.weekDays = weekDays;
    }


    @SerializedName("niNumberPart1")
    @Expose
    private String niNumberPart1;
    @SerializedName("niNumberPart2")
    @Expose
    private String niNumberPart2;
    @SerializedName("niNumberPart3")
    @Expose
    private String niNumberPart3;
    @SerializedName("niNumberPart4")
    @Expose
    private String niNumberPart4;
    @SerializedName("niNumberPart5")
    @Expose
    private String niNumberPart5;
    @SerializedName("completeNiNumber")
    @Expose
    private String completeNiNumber;
    @SerializedName("postalCode")
    @Expose
    private String postalCode;
    @SerializedName("addressLine1")
    @Expose
    private String addressLine1;
    @SerializedName("addressLine2")
    @Expose
    private String addressLine2;
    @SerializedName("addressLine3")
    @Expose
    private String addressLine3;
    @SerializedName("town")
    @Expose
    private String town;

    public Boolean getIsUk() {
        return isUk;
    }

    public void setIsUk(Boolean uk) {
        isUk = uk;
    }

    @SerializedName("isUk")
    @Expose
    private Boolean isUk;
    @SerializedName("kinEmployeeLocation")
    @Expose
    private Boolean isKinEmployeeLocation;

    public Boolean getIsKinEmployeeLocation() {
        return isKinEmployeeLocation;
    }

    public void setIsKinEmployeeLocation(Boolean kinEmployeeLocation) {
        isKinEmployeeLocation = kinEmployeeLocation;
    }


    public String getNiNumberPart1() {
        return niNumberPart1;
    }

    public void setNiNumberPart1(String niNumberPart1) {
        this.niNumberPart1 = niNumberPart1;
    }

    public String getNiNumberPart2() {
        return niNumberPart2;
    }

    public void setNiNumberPart2(String niNumberPart2) {
        this.niNumberPart2 = niNumberPart2;
    }

    public String getNiNumberPart3() {
        return niNumberPart3;
    }

    public void setNiNumberPart3(String niNumberPart3) {
        this.niNumberPart3 = niNumberPart3;
    }

    public String getNiNumberPart4() {
        return niNumberPart4;
    }

    public void setNiNumberPart4(String niNumberPart4) {
        this.niNumberPart4 = niNumberPart4;
    }

    public String getNiNumberPart5() {
        return niNumberPart5;
    }

    public void setNiNumberPart5(String niNumberPart5) {
        this.niNumberPart5 = niNumberPart5;
    }

    public String getCompleteNiNumber() {
        return completeNiNumber;
    }

    public void setCompleteNiNumber(String completeNiNumber) {
        this.completeNiNumber = completeNiNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Boolean getCopyAddress() {
        return isCopyAddress;
    }

    public void setCopyAddress(Boolean copyAddress) {
        isCopyAddress = copyAddress;
    }

    @SerializedName("isCopyAddress")
    @Expose
    private Boolean isCopyAddress;
    @SerializedName("personalEmail")
    @Expose
    private String personalEmail;
    @SerializedName("workEmail")
    @Expose
    private String workEmail;
    @SerializedName("mobilePhone")
    @Expose
    private String mobilePhone;
    @SerializedName("homePhone")
    @Expose
    private String homePhone;

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("maritalStatusId")
    @Expose
    private String maritalStatusId;
    @SerializedName("titleId")
    @Expose
    private String titleId;
    @SerializedName("kinTitle")
    @Expose
    private String kinTitle;
    @SerializedName("kinFirstName")
    @Expose
    private String kinFirstName;
    @SerializedName("kinMiddleName")
    @Expose
    private String kinMiddleName;
    @SerializedName("kinSurname")
    @Expose
    private String kinSurname;
    @SerializedName("kinAddress")
    @Expose
    private String kinAddress;
    @SerializedName("kinAddressMobile")
    @Expose
    private String kinAddressMobile;
    @SerializedName("kinPostCode")
    @Expose
    private String kinPostCode;
    @SerializedName("kinMobile")
    @Expose
    private String kinMobile;
    @SerializedName("kinPhone")
    @Expose
    private String kinPhone;
    @SerializedName("typeId")
    @Expose
    private String typeId;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getKinTitle() {
        return kinTitle;
    }

    public void setKinTitle(String kinTitle) {
        this.kinTitle = kinTitle;
    }

    public String getKinFirstName() {
        return kinFirstName;
    }

    public void setKinFirstName(String kinFirstName) {
        this.kinFirstName = kinFirstName;
    }

    public String getKinMiddleName() {
        return kinMiddleName;
    }

    public void setKinMiddleName(String kinMiddleName) {
        this.kinMiddleName = kinMiddleName;
    }

    public String getKinSurname() {
        return kinSurname;
    }

    public void setKinSurname(String kinSurname) {
        this.kinSurname = kinSurname;
    }

    public String getKinAddress() {
        return kinAddress;
    }

    public void setKinAddress(String kinAddress) {
        this.kinAddress = kinAddress;
    }

    public String getKinAddressMobile() {
        return kinAddressMobile;
    }

    public void setKinAddressMobile(String kinAddressMobile) {
        this.kinAddressMobile = kinAddressMobile;
    }

    public String getKinPostCode() {
        return kinPostCode;
    }

    public void setKinPostCode(String kinPostCode) {
        this.kinPostCode = kinPostCode;
    }

    public String getKinMobile() {
        return kinMobile;
    }

    public void setKinMobile(String kinMobile) {
        this.kinMobile = kinMobile;
    }

    public String getKinPhone() {
        return kinPhone;
    }

    public void setKinPhone(String kinPhone) {
        this.kinPhone = kinPhone;
    }

    public String getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(String maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }


    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }


    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    @SerializedName("sortCode")
    @Expose
    private String sortCode;
    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("bankBranch")
    @Expose
    private String bankBranch;
    @SerializedName("accountName")
    @Expose
    private String accountName;
    @SerializedName("buildingRollNumber")
    @Expose
    private String buildingRollNumber;
    @SerializedName("bankRef1")
    @Expose
    private String bankRef1;
    @SerializedName("bankRef2")
    @Expose
    private String bankRef2;
    @SerializedName("payMethod")
    @Expose
    private String payMethod;

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBuildingRollNumber() {
        return buildingRollNumber;
    }

    public void setBuildingRollNumber(String buildingRollNumber) {
        this.buildingRollNumber = buildingRollNumber;
    }

    public String getBankRef1() {
        return bankRef1;
    }

    public void setBankRef1(String bankRef1) {
        this.bankRef1 = bankRef1;
    }

    public String getBankRef2() {
        return bankRef2;
    }

    public void setBankRef2(String bankRef2) {
        this.bankRef2 = bankRef2;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getHdnvaliadtion() {
        return hdnvaliadtion;
    }

    public void setHdnvaliadtion(String hdnvaliadtion) {
        this.hdnvaliadtion = hdnvaliadtion;
    }

    @SerializedName("hdnvaliadtion")
    @Expose
    private String hdnvaliadtion;

    @SerializedName("postGraduateStatus")
    @Expose
    private String postGraduateStatus;

    public String getTableFrom() {
        return tableFrom;
    }

    public void setTableFrom(String tableFrom) {
        this.tableFrom = tableFrom;
    }

    @SerializedName("tableFrom")
    @Expose
    private String tableFrom;
    @SerializedName("isExistMobilePhone")
    @Expose
    private Boolean isExistMobilePhone;
    @SerializedName("oneWeekorMonth")
    @Expose
    private Boolean oneWeekorMonth;
    @SerializedName("isExistWorkEmail")
    @Expose
    private Boolean isExistWorkEmail;

    public Boolean isForceChangePassword() {
        return isForceChangePassword;
    }

    public void setForceChangePassword(Boolean forceChangePassword) {
        isForceChangePassword = forceChangePassword;
    }

    @SerializedName("forceChangePassword")
    @Expose
    private Boolean isForceChangePassword;

    public String getBankDetailReadOnly() {
        return IsBankDetailReadOnly;
    }

    public void setBankDetailReadOnly(String bankDetailReadOnly) {
        IsBankDetailReadOnly = bankDetailReadOnly;
    }

    @SerializedName("isBankDetailReadOnly")
    @Expose
    private String IsBankDetailReadOnly;

    public Boolean getOneWeekorMonth() {
        return oneWeekorMonth;
    }

    public void setOneWeekorMonth(Boolean oneWeekorMonth) {
        this.oneWeekorMonth = oneWeekorMonth;
    }


    @SerializedName("isEmployeeP45Details")
    @Expose
    private Boolean isEmployeeP45Details;
    @SerializedName("isExistNextofkin")
    @Expose
    private Boolean isExistNextofkin;

    public Boolean getEmpContinueStudLoan() {
        return empContinueStudLoan;
    }

    public void setEmpContinueStudLoan(Boolean empContinueStudLoan) {
        this.empContinueStudLoan = empContinueStudLoan;
    }

    @SerializedName("empContinueStudLoan")
    @Expose
    private Boolean empContinueStudLoan;
    @SerializedName("leavingDate")
    @Expose
    private String leavingDate;
    @SerializedName("officeNumber")
    @Expose
    private String officeNumber;

    @SerializedName("monthNo")
    @Expose
    private String monthNo;

    @SerializedName("weekNo")
    @Expose
    private String weekNo;

    @SerializedName("totalPay")
    @Expose
    private String totalPay;

    public String getMonthNo() {
        return monthNo;
    }

    public void setMonthNo(String monthNo) {
        this.monthNo = monthNo;
    }

    public String getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(String weekNo) {
        this.weekNo = weekNo;
    }

    public String getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(String totalPay) {
        this.totalPay = totalPay;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    @SerializedName("totalTax")
    @Expose
    private String totalTax;

    public String getEmpStatementId() {
        return empStatementId;
    }

    public void setEmpStatementId(String empStatementId) {
        this.empStatementId = empStatementId;
    }

    @SerializedName("empStatementId")
    @Expose
    private String empStatementId;

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    @SerializedName("taxCode")
    @Expose
    private String taxCode;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @SerializedName("referenceNumber")
    @Expose
    private String referenceNumber;

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }


    public String getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(String leavingDate) {
        this.leavingDate = leavingDate;
    }


    public Boolean getIsExistMobilePhone() {
        return isExistMobilePhone;
    }

    public void setIsExistMobilePhone(Boolean isExistMobilePhone) {
        this.isExistMobilePhone = isExistMobilePhone;
    }

    public Boolean getIsExistWorkEmail() {
        return isExistWorkEmail;
    }

    public void setIsExistWorkEmail(Boolean isExistWorkEmail) {
        this.isExistWorkEmail = isExistWorkEmail;
    }

    public Boolean getIsExistNextofkin() {
        return isExistNextofkin;
    }

    public void setIsExistNextofkin(Boolean isExistNextofkin) {
        this.isExistNextofkin = isExistNextofkin;
    }

}