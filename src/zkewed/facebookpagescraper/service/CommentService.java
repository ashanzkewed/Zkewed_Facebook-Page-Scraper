/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.service;

import java.util.ArrayList;
import java.util.Set;
import zkewed.facebookpagescraper.model.Comments;

/**
 *
 * @author DELL
 */
public interface CommentService {
    public ArrayList<Comments> getComments(String postId);
}
