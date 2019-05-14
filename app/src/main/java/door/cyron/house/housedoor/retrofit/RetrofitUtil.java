package door.cyron.house.housedoor.retrofit;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import door.cyron.house.housedoor.utility.Camera;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;


public class RetrofitUtil {

    @NonNull
    public static RequestBody getRequestBody(String value) {
        return RequestBody.create(MultipartBody.FORM, value);
    }

//    @NonNull
//    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
//        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
//        // use the FileUtils to get the actual file by uri
//        File file = FileUtils.getFile(this, fileUri);
//
//        // create RequestBody instance from file
//        RequestBody requestFile =
//                RequestBody.create(
//                        MediaType.parse(getContentResolver().getType(fileUri)),
//                        file
//                );
//
//        // MultipartBody.Part is used to send also the actual file name
//        return MultipartBody.Part.createFormData(partName, file.getTitle(), requestFile);
//    }


    /**
     * Method to generate Image Multipart from image Uri
     *
     * @param context
     * @param imageUri
     * @return Return Image Multipart file
     */
    @NonNull
    public static MultipartBody.Part getImageMultipart(Context context, Uri imageUri) {
        MultipartBody.Part imageMultipart = null;
        if (null == imageUri) {
            return null;
        } else {
            String urlPath;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                urlPath = Camera.getPath(context, imageUri);
            } else {
                urlPath = Camera.getRealPathFromURI(context, imageUri);
            }
            //urlpath can be null in some device because we are returning null inside the function getPath or getRealPathFromURI
            //not reproducible in device

            if (urlPath != null) {
                File imageFile = new File(urlPath);
                imageMultipart = MultipartBody.Part.createFormData(
                        "profile_picture", imageFile.getName(), RequestBody.create(MediaType.parse("image/*"), imageFile));
            }
            return imageMultipart;
        }
    }

}
