
package com.sachin.aislesystemassessment.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ProfileResponse {

    @SerializedName("invites")
    private Invites mInvites;
    @SerializedName("likes")
    private Likes mLikes;

    public Invites getInvites() {
        return mInvites;
    }

    public void setInvites(Invites invites) {
        mInvites = invites;
    }

    public Likes getLikes() {
        return mLikes;
    }

    public void setLikes(Likes likes) {
        mLikes = likes;
    }

}
