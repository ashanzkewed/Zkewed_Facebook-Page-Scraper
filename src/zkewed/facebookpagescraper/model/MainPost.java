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
    private String fbPostId;
    private Integer likeCount;
    private Integer shareCount;
    private String dressCode;
    private String dressCodeDescription;
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

    @Override
    public String toString() {
        return "MainPost{" + "id=" + id + ", fbPostId=" + fbPostId + ", likeCount=" + likeCount + ", shareCount=" + shareCount + ", dressCode=" + dressCode + ", dressCodeDescription=" + dressCodeDescription + ", timeStamp=" + timeStamp + ", message=" + message + ", comments=" + comments + '}';
    }

    /**
     * @return the dressCode
     */
    public String getDressCode() {
        return dressCode;
    }

    /**
     * @param dressCode the dressCode to set
     */
    public void setDressCode(String dressCode) {
        this.dressCode = dressCode;
    }

    /**
     * @return the dressCodeDescription
     */
    public String getDressCodeDescription() {
        return dressCodeDescription;
    }

    /**
     * @param dressCodeDescription the dressCodeDescription to set
     */
    public void setDressCodeDescription(String dressCodeDescription) {
        this.dressCodeDescription = dressCodeDescription;
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

}
