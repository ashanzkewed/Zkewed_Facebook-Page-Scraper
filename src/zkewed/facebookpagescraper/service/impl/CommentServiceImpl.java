/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.service.impl;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.exception.FacebookGraphException;
import com.restfb.exception.FacebookNetworkException;
import com.restfb.types.Comment;
import com.restfb.types.Page;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import zkewed.facebookpagescraper.fbclient.FbClientConnection;
import zkewed.facebookpagescraper.model.Comments;
import zkewed.facebookpagescraper.service.CommentService;

public class CommentServiceImpl implements CommentService {

    final static Logger LOGGER = Logger.getLogger("CommentServiceImpl");
    private FacebookClient fbClient;

    public CommentServiceImpl() {
        fbClient = FbClientConnection.getFbClientConnection().getFacebookClient();
    }

    @Override
    public ArrayList<Comments> getComments(String postId,String pageId,String pageName) throws FacebookNetworkException {
        ArrayList<Comments> commentList = null;

        String accessToken = FbClientConnection.accessToken;

        Page page = fbClient.fetchObject(pageId, Page.class);
        try {
            Connection<Comment> allComments = fbClient.fetchConnection(postId + "/comments", Comment.class);

            if (allComments != null) {
                commentList = new ArrayList<>();
                for (List<Comment> postcomments : allComments) {
                    for (Comment comment : postcomments) {
                        if (!comment.getMessage().isEmpty()) {

                            if (comment.getMessage().length() <= 250) {
                                Comments comments = new Comments();
                                comments.setComment(comment.getMessage());
                                comments.setPageName(pageName);
                                commentList.add(comments);
//                            System.out.println(comment.getMessage());
                            }
                        }
                    }
                }
            }
        } catch (FacebookGraphException ex) {
            LOGGER.error("getComments Method : " + ex);
        }

        return commentList;
    }

}
