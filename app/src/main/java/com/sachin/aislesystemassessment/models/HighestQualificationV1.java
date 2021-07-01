
package com.sachin.aislesystemassessment.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class HighestQualificationV1 {

    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("preference_only")
    private Boolean mPreferenceOnly;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Boolean getPreferenceOnly() {
        return mPreferenceOnly;
    }

    public void setPreferenceOnly(Boolean preferenceOnly) {
        mPreferenceOnly = preferenceOnly;
    }

}
