/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.controller;

import com.restfb.exception.FacebookCheckedException;
import com.restfb.exception.FacebookNetworkException;
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

    public boolean fetchDetails() throws FacebookNetworkException{
        return postService.fetchDetails();
    }

}
