
package com.example.medi_search.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Symptom<Extras, Child> {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("common_name")
    @Expose
    private String commonName;
    @SerializedName("sex_filter")
    @Expose
    private String sexFilter;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("seriousness")
    @Expose
    private String seriousness;
    @SerializedName("extras")
    @Expose
    private Extras extras;
    @SerializedName("children")
    @Expose
    private List<Child> children = null;
    @SerializedName("image_url")
    @Expose
    private Object imageUrl;
    @SerializedName("image_source")
    @Expose
    private Object imageSource;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("parent_relation")
    @Expose
    private String parentRelation;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Symptom() {
    }

    /**
     * 
     * @param sexFilter
     * @param commonName
     * @param imageSource
     * @param children
     * @param parentRelation
     * @param seriousness
     * @param imageUrl
     * @param name
     * @param extras
     * @param id
     * @param category
     * @param parentId
     */
    public Symptom(String id, String name, String commonName, String sexFilter, String category, String seriousness, Extras extras, List<Child> children, Object imageUrl, Object imageSource, String parentId, String parentRelation) {
        super();
        this.id = id;
        this.name = name;
        this.commonName = commonName;
        this.sexFilter = sexFilter;
        this.category = category;
        this.seriousness = seriousness;
        this.extras = extras;
        this.children = children;
        this.imageUrl = imageUrl;
        this.imageSource = imageSource;
        this.parentId = parentId;
        this.parentRelation = parentRelation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getSexFilter() {
        return sexFilter;
    }

    public void setSexFilter(String sexFilter) {
        this.sexFilter = sexFilter;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeriousness() {
        return seriousness;
    }

    public void setSeriousness(String seriousness) {
        this.seriousness = seriousness;
    }

    public Extras getExtras() {
        return extras;
    }

    public void setExtras(Extras extras) {
        this.extras = extras;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Object getImageSource() {
        return imageSource;
    }

    public void setImageSource(Object imageSource) {
        this.imageSource = imageSource;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentRelation() {
        return parentRelation;
    }

    public void setParentRelation(String parentRelation) {
        this.parentRelation = parentRelation;
    }

}
