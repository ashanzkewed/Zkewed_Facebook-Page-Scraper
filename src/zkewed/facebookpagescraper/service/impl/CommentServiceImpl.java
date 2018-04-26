/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.service.impl;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.Comment;
import com.restfb.types.Page;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import zkewed.facebookpagescraper.model.Comments;
import zkewed.facebookpagescraper.service.CommentService;

public class CommentServiceImpl implements CommentService {

    @Override
    public ArrayList<Comments> getComments(String postId) {
        ArrayList<Comments> commentList = null;

        String accessToken = "EAACEdEose0cBAL58LWGEwXHzB1oGkw8GuQTMRqX9ndmAByrKd9zAThzVZBT6ljC64K6KHe7NxO2npIxH5YsJNqclVe8vZCcK3cmHMhQ9gMRqoSViowJJE1Cf1fD3jNVYsYxdtLAhyTl61HE7CB4kgEL8WtodnwNuZCqv4BUMX47Svene8PUU8WZAgdy4ZAZBcZD";

        FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
        Page page = fbClient.fetchObject("GflockClothing", Page.class);
        Connection<Comment> allComments = fbClient.fetchConnection(postId + "/comments", Comment.class);
     
       
        if (allComments != null) {
            commentList=new ArrayList<>();
            for (List<Comment> postcomments : allComments) {
                for (Comment comment : postcomments) {
                    if (!comment.getMessage().isEmpty()) {
                       
                        Comments comments = new Comments();
                        comments.setComment(comment.getMessage());
                        commentList.add(comments);
                    }
                }
            }
        }

        return commentList;
    }

}
