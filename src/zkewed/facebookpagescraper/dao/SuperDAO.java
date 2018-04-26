/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.dao;

import java.io.Serializable;
import zkewed.facebookpagescraper.model.MainPost;
import zkewed.facebookpagescraper.model.SuperModel;

/**
 *
 * @author DELL
 */
public interface SuperDAO  <T extends SuperModel>{
    public Serializable addPost(MainPost post);
}
