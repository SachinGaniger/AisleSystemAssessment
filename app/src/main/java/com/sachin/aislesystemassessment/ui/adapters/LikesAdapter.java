package com.sachin.aislesystemassessment.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.sachin.aislesystemassessment.databinding.ItemProfileAdapterBinding;
import com.sachin.aislesystemassessment.models.LikeProfile;
import com.sachin.aislesystemassessment.models.Profile;
import com.sachin.aislesystemassessment.utils.GlideImageBlur;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class LikesAdapter extends RecyclerView.Adapter<LikesAdapter.ViewHolder> {

    List<LikeProfile> likeProfileList;
    Boolean canSee;
    Context context;
    private static final String TAG = "LikesAdapter";

    public LikesAdapter(List<LikeProfile> likeProfileList, Boolean canSee, Context context) {
        this.likeProfileList = likeProfileList;
        this.canSee = canSee;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemProfileAdapterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        LikeProfile likeProfile = likeProfileList.get(position);
        Log.i(TAG, "onBindViewHolder: "+ likeProfile.getmAvatar());
        Log.i(TAG, "onBindViewHolder: "+ context.getPackageName());
        if (!canSee){
            MultiTransformation<Bitmap> multi = new MultiTransformation<Bitmap>(new CenterCrop(), new BlurTransformation(25, 2));
            Glide.with(holder.itemProfileAdapterBinding.ivProfile)
                    .load(likeProfile.getmAvatar())
                    .transform(multi)
//                .centerCrop()
                    .into(holder.itemProfileAdapterBinding.ivProfile);

        }
        holder.itemProfileAdapterBinding.tvNameAge.setText(likeProfile.getmFirstName());

    }

    @Override
    public int getItemCount() {
        return likeProfileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemProfileAdapterBinding itemProfileAdapterBinding;

        public ViewHolder(ItemProfileAdapterBinding itemProfileAdapterBinding) {
            super(itemProfileAdapterBinding.getRoot());
            this.itemProfileAdapterBinding = itemProfileAdapterBinding;
        }
    }
}
