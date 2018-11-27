/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.controller;

import com.restfb.exception.FacebookNetworkException;
import java.util.List;
import zkewed.facebookpagescraper.model.FbPage;
import zkewed.facebookpagescraper.service.PostService;
import zkewed.facebookpagescraper.service.impl.PostServiceImpl;

/**
 *
 * @author DELL
 */
public class PostController {

    private static PostService postService;

    public PostController() {

        postService = new PostServiceImpl();
    }

    public boolean fetchDetails(String pageId, String date, String pageName) throws FacebookNetworkException {
        return postService.fetchDetails(pageId, date, pageName);
    }

    public boolean addFacebookURL(String url, String fbN) throws Exception {
        String pageId = postService.getFacebookPageId(fbN);

        FbPage fbPage = new FbPage();
        fbPage.setUrl(url);
        fbPage.setPageName(fbN);
        fbPage.setPageId(pageId);

        return postService.addFacebookURL(fbPage);
    }

    public List<FbPage> getAllFbPages() throws Exception {
        return postService.getAllFbPages();

    }

    public boolean deleteFacebookPage(FbPage fbPage) throws Exception {
        return postService.deleteFacebookPage(fbPage);
    }

    public boolean clearDatabaseAlltables() throws Exception {
        return postService.clearDatabaseAlltables();

    }

    public boolean deleteFacebookPageData(FbPage fbPage) throws Exception {
        return postService.deleteFacebookPageData(fbPage);
    }

}
