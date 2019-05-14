package door.cyron.house.housedoor.acount;

import com.google.gson.annotations.SerializedName;

public class SignInResponseModel {

    @SerializedName("errorStatus")
    private String errorStatus;
    @SerializedName("errorCode")
    private String errorCode;

    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
