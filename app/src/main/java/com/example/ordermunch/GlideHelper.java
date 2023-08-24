package com.example.ordermunch;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class GlideHelper {
    public static void loadImage(@NonNull String url, @NonNull ImageView image) {

        Context context = image.getContext();
        if (isValidContextForExecute(context)) {
            RequestOptions requestOptions = new RequestOptions();
            Glide.with(context)
                    .load(url)
                    .thumbnail(0.3f)
                    .apply(
                            requestOptions
                                    .format(DecodeFormat.PREFER_RGB_565)
                                    .error(R.drawable.ic_error_image)
                                    .placeholder(R.drawable.ic_error_image)
                                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                                    .circleCrop()
                    ).into(image);
        }
    }
    public static void loadImageNoCrop(@NonNull String url, @NonNull ImageView image) {

        Context context = image.getContext();
        if (isValidContextForExecute(context)) {
            RequestOptions requestOptions = new RequestOptions();
            Glide.with(context)
                    .load(url)
                    .thumbnail(0.3f)
                    .apply(
                            requestOptions
                                    .format(DecodeFormat.PREFER_RGB_565)
                                    .error(R.drawable.ic_error_image)
                                    .placeholder(R.drawable.ic_error_image)
                                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    ).into(image);
        }
    }
    public static boolean isValidContextForExecute(final Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            return !activity.isDestroyed() && !activity.isFinishing();
        }
        return true;
    }

}
