/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.service.impl;

import com.restfb.Connection;
import com.restfb.DebugHeaderInfo;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookNetworkException;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.StoryAttachment;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import zkewed.facebookpagescraper.dao.custom.PostDao;
import zkewed.facebookpagescraper.dao.custom.impl.PostDaoImpl;
import zkewed.facebookpagescraper.fbclient.FbClientConnection;
import zkewed.facebookpagescraper.model.Comments;
import zkewed.facebookpagescraper.model.FbPage;
import zkewed.facebookpagescraper.model.MainPost;
import zkewed.facebookpagescraper.service.CommentService;
import zkewed.facebookpagescraper.service.PostService;

/**
 *
 * @author DELL
 */
public class PostServiceImpl implements PostService {

    private CommentService commentService;
    private PostDao postDao;
    final static Logger LOGGER = Logger.getLogger("PostServiceImpl");
    private FacebookClient fbClient;
    private String pageId;

    public PostServiceImpl() {
        commentService = new CommentServiceImpl();
        postDao = new PostDaoImpl();
        fbClient = FbClientConnection.getFbClientConnection().getFacebookClient();

//         PropertyConfigurator.configure("./src/log4j.properties");
    }

    @Override
    public boolean fetchDetails(String pageId, String datee, String pageName) throws FacebookNetworkException {
        System.out.println("hi");

        this.pageId = pageId;
        Page page = fbClient.fetchObject(this.pageId, Page.class);

        Connection<Post> page1 = fbClient.fetchConnection(this.pageId + "/posts", Post.class, Parameter.with("fields", "reactions.summary(true),shares,message,attachments{subattachments.limit(1000000),target{id}},comments,updated_time,link,full_picture,permalink_url"), Parameter.with("until", "today"),
                Parameter.with("since", datee));

        for (List<Post> list : page1) {
            for (Post post : list) {
                DebugHeaderInfo headerInfos = fbClient.getWebRequestor().getDebugHeaderInfo();
                System.out.println("Request Count : " + headerInfos.getAppUsage().getCallCount());
                MainPost mainPost1 = new MainPost();
                mainPost1.setFbPostId(post.getId());
                mainPost1.setPostURL(post.getLink());
                mainPost1.setPageName(pageName);
                mainPost1.setPermalink_url(post.getPermalinkUrl());
                mainPost1.setImageURL(post.getFullPicture());
                mainPost1.setShareCount(Integer.parseInt(Long.toString(post.getSharesCount())));
                mainPost1.setLikeCount(Integer.parseInt(Long.toString(post.getReactionsCount())));
                Date date = post.getUpdatedTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String updateDate = sdf.format(date);
                mainPost1.setTimeStamp(updateDate);

                if (!(post.getMessage() == null)) {
                    if (!(post.getMessage().isEmpty())) {
                        if (post.getMessage().length() <= 2000) {
                            mainPost1.setMessage(post.getMessage());
                        }
                    }
                }

                //get post comments
                ArrayList<Comments> commentList = commentService.getComments(post.getId(), this.pageId, pageName);

                if (commentList != null) {
                    Set<Comments> newCommentList = null;
                    newCommentList = new HashSet<>();
                    for (int i = 0; i < commentList.size(); i++) {
                        Comments cmt = commentList.get(i);
                        cmt.setMainPost(mainPost1);
                        newCommentList.add(cmt);

                    }

                    mainPost1.setComments(newCommentList);
                }

                Serializable res1 = postDao.addPost(mainPost1);
                if (res1 != null) {
                    getSubPost(post, updateDate, pageName);
                }

            }

        }
        return true;
    }

    public void getSubPost(Post post, String updateDate, String pageName) throws FacebookNetworkException {

        if (post.getAttachments() != null) {
            List<StoryAttachment> data = post.getAttachments().getData();
            for (StoryAttachment story : data) {
                if (story.getSubAttachments() != null) {

                    StoryAttachment.Attachments ss = story.getSubAttachments();

                    if (ss.getData() != null) {

                        List<StoryAttachment> data1 = ss.getData();
                        for (StoryAttachment sAttach : data1) {

                            if (!(sAttach.getTarget().getId() == null || sAttach.getTarget().getId().isEmpty())) {
                                try {
                                    Post post1 = fbClient.fetchObject(this.pageId + "_" + sAttach.getTarget().getId(), Post.class, Parameter.with("fields", "from,actions,message,story,to,reactions.summary(true),comments.summary(true),shares.summary(true),link,permalink_url"));
                                    DebugHeaderInfo headerInfos = fbClient.getWebRequestor().getDebugHeaderInfo();
                                    System.out.println("Request Count ::::: sub  : " + headerInfos.getAppUsage().getCallCount());
                                    MainPost mainPost2 = new MainPost();
                                    mainPost2.setPageName(pageName);
                                    mainPost2.setPostURL(post1.getLink());
                                    mainPost2.setPermalink_url(post1.getPermalinkUrl());
                                    mainPost2.setMessage(post1.getMessage());
                                    mainPost2.setImageURL(sAttach.getMedia().getImage().getSrc());
                                    mainPost2.setFbPostId(this.pageId + "_" + sAttach.getTarget().getId());
                                    mainPost2.setLikeCount(Integer.parseInt(Long.toString(post1.getReactionsCount())));
                                    mainPost2.setShareCount(Integer.parseInt(Long.toString(post1.getSharesCount())));
                                    mainPost2.setTimeStamp(updateDate);

                                    ArrayList<Comments> commentList1 = commentService.getComments(this.pageId + "_" + sAttach.getTarget().getId(), this.pageId, pageName);

                                    Set<Comments> newCommentList1 = null;

                                    newCommentList1 = new HashSet<>();
                                    for (int i = 0; i < commentList1.size(); i++) {

                                        Comments cmt = commentList1.get(i);
                                        cmt.setMainPost(mainPost2);
                                        newCommentList1.add(cmt);

                                    }
                                    mainPost2.setComments(newCommentList1);

                                    Serializable res2 = postDao.addPost(mainPost2);
                                } catch (Exception ex) {
                                    LOGGER.error("getSubPost Method : " + ex);
//                                  
                                }
                            }

                        }

                    }
                }
            }

        }
    }

    @Override
    public boolean addFacebookURL(FbPage fbPage) throws Exception {

        Serializable res = postDao.addFacebookURL(fbPage);
        if (res != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<FbPage> getAllFbPages() throws Exception {
        return postDao.getAllFbPages();
    }

    @Override
    public boolean deleteFacebookPage(FbPage fbPage) throws Exception {
        return postDao.deleteFacebookPage(fbPage);
    }

    @Override
    public boolean clearDatabaseAlltables() throws Exception {
        return postDao.clearDatabaseAlltables();
    }

    @Override
    public String getFacebookPageId(String pageName) throws Exception {

        Page page = fbClient.fetchObject(pageName, Page.class);
        Page page1 = fbClient.fetchObject(pageName, Page.class, Parameter.with("fields", "id"));
        return page1.getId();

    }

    @Override
    public boolean deleteFacebookPageData(FbPage fbPage) throws Exception {
        return postDao.deleteFacebookPageData(fbPage);
    }
}
