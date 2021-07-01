
package com.sachin.aislesystemassessment.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Likes {

    @SerializedName("can_see_profile")
    private Boolean mCanSeeProfile;
    @SerializedName("likes_received_count")
    private Long mLikesReceivedCount;
    @SerializedName("profiles")
    private List<LikeProfile> mProfiles;

    public Boolean getCanSeeProfile() {
        return mCanSeeProfile;
    }

    public void setCanSeeProfile(Boolean canSeeProfile) {
        mCanSeeProfile = canSeeProfile;
    }

    public Long getLikesReceivedCount() {
        return mLikesReceivedCount;
    }

    public void setLikesReceivedCount(Long likesReceivedCount) {
        mLikesReceivedCount = likesReceivedCount;
    }

    public List<LikeProfile> getProfiles() {
        return mProfiles;
    }

    public void setProfiles(List<LikeProfile> profiles) {
        mProfiles = profiles;
    }

}
