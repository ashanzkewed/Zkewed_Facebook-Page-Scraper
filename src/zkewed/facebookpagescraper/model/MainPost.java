/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author DELL
 */
@Entity
public class MainPost extends SuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MainPost_Id")
    private Integer id;
    private String pageName;
    private String fbPostId;
    private Integer likeCount;
    private Integer shareCount;
    private String imageURL;
    private String postURL;
    private String permalink_url;
    private String timeStamp;

    @Column(length = 2000)
    private String message;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mainPost")
    private Set<Comments> comments = new HashSet<>();

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the fbPostId
     */
    public String getFbPostId() {
        return fbPostId;
    }

    /**
     * @param fbPostId the fbPostId to set
     */
    public void setFbPostId(String fbPostId) {
        this.fbPostId = fbPostId;
    }

    /**
     * @return the likeCount
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * @param likeCount the likeCount to set
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * @return the shareCount
     */
    public Integer getShareCount() {
        return shareCount;
    }

    /**
     * @param shareCount the shareCount to set
     */
    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    /**
     * @return the timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the comments
     */
    public Set<Comments> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(Set<Comments> comments) {
        this.comments = comments;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the pageName
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * @param pageName the pageName to set
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    /**
     * @return the imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * @param imageURL the imageURL to set
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * @return the postURL
     */
    public String getPostURL() {
        return postURL;
    }

    /**
     * @param postURL the postURL to set
     */
    public void setPostURL(String postURL) {
        this.postURL = postURL;
    }

    /**
     * @return the permalink_url
     */
    public String getPermalink_url() {
        return permalink_url;
    }

    /**
     * @param permalink_url the permalink_url to set
     */
    public void setPermalink_url(String permalink_url) {
        this.permalink_url = permalink_url;
    }

}
