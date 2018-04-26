/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.dao.custom.impl;

import java.io.Serializable;
import org.hibernate.Cache;
import org.hibernate.Session;
import zkewed.facebookpagescraper.config.HibernateUtil;
import zkewed.facebookpagescraper.dao.custom.PostDao;
import zkewed.facebookpagescraper.model.MainPost;

public class PostDaoImpl implements PostDao {

    @Override
    public Serializable addPost(MainPost post) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Serializable res = session.save(post);
        session.getTransaction().commit();
      
         
        session.clear();
        HibernateUtil.getSessionFactory().close();
        return res;
    }

}
