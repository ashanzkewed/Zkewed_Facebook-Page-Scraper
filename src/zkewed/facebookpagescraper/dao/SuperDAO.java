/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.dao;

import java.io.Serializable;
import java.util.List;
import zkewed.facebookpagescraper.model.FbPage;
import zkewed.facebookpagescraper.model.MainPost;
import zkewed.facebookpagescraper.model.SuperModel;

/**
 *
 * @author DELL
 */
public interface SuperDAO<T extends SuperModel> {

    public Serializable addPost(MainPost post);

    public Serializable addFacebookURL(FbPage fbPage);

    public List<FbPage> getAllFbPages();

    public boolean deleteFacebookPage(FbPage fbPage) throws Exception;

    public boolean clearDatabaseAlltables() throws Exception;
    
     public boolean deleteFacebookPageData(FbPage fbPage) throws Exception;

}
