
package com.example.medi_search.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Patient<Age, Evidence> {

    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("age")
    @Expose
    private Age age;
    @SerializedName("evidence")
    @Expose
    private List<Evidence> evidence = null;

    private String pushId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Patient() {
    }

    /**
     * 
     * @param evidence
     * @param sex
     * @param age
     */
    public Patient(String sex, Age age, List<Evidence> evidence) {
        super();
        this.sex = sex;
        this.age = age;
        this.evidence = evidence;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public List<Evidence> getEvidence() {
        return evidence;
    }

    public void setEvidence(List<Evidence> evidence) {
        this.evidence = evidence;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

}
