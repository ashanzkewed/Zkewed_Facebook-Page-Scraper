/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.dao.custom.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import zkewed.facebookpagescraper.config.HibernateUtil;
import zkewed.facebookpagescraper.dao.custom.PostDao;
import zkewed.facebookpagescraper.model.FbPage;
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

    @Override
    public Serializable addFacebookURL(FbPage fbPage) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Serializable res = session.save(fbPage);
        session.getTransaction().commit();

        session.clear();
        HibernateUtil.getSessionFactory().close();
        return res;
    }

    @Override
    public List<FbPage> getAllFbPages() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List list = session.createCriteria(FbPage.class).list();
        session.getTransaction().commit();

        session.clear();
        HibernateUtil.getSessionFactory().close();
        return list;
    }

    @Override
    public boolean clearDatabaseAlltables() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        int res1 = session.createQuery("DELETE  FbPage").executeUpdate();
        int res2 = session.createQuery("DELETE  Comments").executeUpdate();
        int res3 = session.createQuery("DELETE  MainPost").executeUpdate();

        session.getTransaction().commit();

        session.clear();
        HibernateUtil.getSessionFactory().close();
        return res3 > 0;
    }

    @Override
    public boolean deleteFacebookPage(FbPage fbPage) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        int res = session.createQuery("DELETE  FbPage where pageId= :id").setParameter("id", fbPage.getPageId()).executeUpdate();
        session.createQuery("DELETE  Comments where pagename= :name").setParameter("name", fbPage.getPageName()).executeUpdate();
        session.createQuery("DELETE  MainPost where pageName= :name").setParameter("name", fbPage.getPageName()).executeUpdate();

        session.getTransaction().commit();

        session.clear();
        HibernateUtil.getSessionFactory().close();
        return res > 0;
    }

    @Override
    public boolean deleteFacebookPageData(FbPage fbPage) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.createQuery("delete Comments where pagename = :name").setParameter("name", fbPage.getPageName()).executeUpdate();
        int res = session.createQuery("delete MainPost where pagename = :name").setParameter("name", fbPage.getPageName()).executeUpdate();
        session.getTransaction().commit();

        session.clear();
        HibernateUtil.getSessionFactory().close();
        return true;
    }

}
