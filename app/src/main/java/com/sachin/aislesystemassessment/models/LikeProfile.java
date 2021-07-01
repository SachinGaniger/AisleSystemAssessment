package com.sachin.aislesystemassessment.models;

import com.google.gson.annotations.SerializedName;

public class LikeProfile {

    @SerializedName("avatar")
    private String mAvatar;
    @SerializedName("first_name")
    private String mFirstName;

    public String getmAvatar() {
        return mAvatar;
    }

    public String getmFirstName() {
        return mFirstName;
    }
}
