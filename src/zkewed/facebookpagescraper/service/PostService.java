/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.service;

import com.restfb.exception.FacebookNetworkException;
import java.util.List;
import zkewed.facebookpagescraper.model.FbPage;

/**
 *
 * @author DELL
 */
public interface PostService {

    public String getFacebookPageId(String pageName) throws Exception;

    public boolean fetchDetails(String pageId, String datee, String pageName) throws FacebookNetworkException;

    public boolean addFacebookURL(FbPage fbPage) throws Exception;

    public boolean deleteFacebookPageData(FbPage fbPage) throws Exception;

    public List<FbPage> getAllFbPages() throws Exception;

    public boolean deleteFacebookPage(FbPage fbPage) throws Exception;

    public boolean clearDatabaseAlltables() throws Exception;
}
