/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author DELL
 */
@Entity
public class Comments  extends SuperModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(length = 500)
    private String comment;
    @ManyToOne
    @JoinColumn(name = "MainPost_Id",nullable = false)
    private MainPost mainPost;
    
    private String pagename;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the mainPost
     */
    public MainPost getMainPost() {
        return mainPost;
    }

    /**
     * @param mainPost the mainPost to set
     */
    public void setMainPost(MainPost mainPost) {
        this.mainPost = mainPost;
    }

    /**
     * @return the pageName
     */
    public String getPageName() {
        return pagename;
    }

    /**
     * @param pageName the pageName to set
     */
    public void setPageName(String pageName) {
        this.pagename = pageName;
    }
    
}
