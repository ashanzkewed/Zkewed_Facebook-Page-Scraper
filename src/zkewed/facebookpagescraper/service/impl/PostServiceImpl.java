/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.service.impl;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.StoryAttachment;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import zkewed.facebookpagescraper.dao.custom.PostDao;
import zkewed.facebookpagescraper.dao.custom.impl.PostDaoImpl;
import zkewed.facebookpagescraper.model.Comments;
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

    public PostServiceImpl() {
        commentService = new CommentServiceImpl();
        postDao = new PostDaoImpl();
    }

    @Override
    public boolean fetchDetails() {
        String accessToken = "EAACEdEose0cBAL58LWGEwXHzB1oGkw8GuQTMRqX9ndmAByrKd9zAThzVZBT6ljC64K6KHe7NxO2npIxH5YsJNqclVe8vZCcK3cmHMhQ9gMRqoSViowJJE1Cf1fD3jNVYsYxdtLAhyTl61HE7CB4kgEL8WtodnwNuZCqv4BUMX47Svene8PUU8WZAgdy4ZAZBcZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
        Page page = fbClient.fetchObject("GflockClothing", Page.class);

        Connection<Post> page1 = fbClient.fetchConnection("200818636727088/feed", Post.class, Parameter.with("fields", "reactions.summary(true),shares,message,attachments{subattachments.limit(1000000),target{id}},comments,updated_time"));

        for (List<Post> list : page1) {
            for (Post post : list) {
                MainPost mainPost1 = new MainPost();
                mainPost1.setFbPostId(post.getId());
                mainPost1.setShareCount(Integer.parseInt(Long.toString(post.getSharesCount())));
                mainPost1.setLikeCount(Integer.parseInt(Long.toString(post.getReactionsCount())));
                Date date = post.getUpdatedTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String updateDate = sdf.format(date);
                mainPost1.setTimeStamp(updateDate);

                //dressCodeWithDes
                String dressCodeWithDes = getDressCodeWithDes(post.getMessage());
                mainPost1.setDressCodeDescription(dressCodeWithDes);

                //DressCode
                String dressCode = getDressCode(mainPost1.getDressCodeDescription());
                mainPost1.setDressCode(dressCode);

                //get post comments
                ArrayList<Comments> commentList = commentService.getComments(post.getId());
                Set<Comments> newCommentList = null;
                newCommentList = new HashSet<>();
                for (int i = 0; i < commentList.size(); i++) {
                    Comments cmt = commentList.get(i);
                    cmt.setMainPost(mainPost1);
                    newCommentList.add(cmt);

                }

                mainPost1.setComments(newCommentList);

                Serializable res1 = postDao.addPost(mainPost1);
                if (res1 != null) {
                    try {
                        List<StoryAttachment> data = post.getAttachments().getData();
                        for (StoryAttachment story : data) {
                            StoryAttachment.Attachments ss = story.getSubAttachments();
                            List<StoryAttachment> data1 = ss.getData();
                            for (StoryAttachment sAttach : data1) {

                                Post post1 = post = fbClient.fetchObject("200818636727088_" + sAttach.getTarget().getId(), Post.class, Parameter.with("fields", "from,actions,message,story,to,reactions.summary(true),comments.summary(true),shares.summary(true)"));
                                MainPost mainPost2 = new MainPost();
                                mainPost2.setFbPostId("200818636727088_" + sAttach.getTarget().getId());
                                mainPost2.setLikeCount(Integer.parseInt(Long.toString(post1.getReactionsCount())));
                                mainPost2.setShareCount(Integer.parseInt(Long.toString(post1.getSharesCount())));
                                mainPost2.setTimeStamp(updateDate);

                                //dressCodeWithDes
                                String dressCodeWithDes1 = getDressCodeWithDes(post1.getMessage());
                                mainPost2.setDressCodeDescription(dressCodeWithDes1);

                                //DressCode
                                String dressCode1 = getDressCode(mainPost2.getDressCodeDescription());
                                mainPost2.setDressCode(dressCode1);

                                ArrayList<Comments> commentList1 = commentService.getComments("200818636727088_" + sAttach.getTarget().getId());

                                Set<Comments> newCommentList1 = null;
                                newCommentList1 = new HashSet<>();
                                for (int i = 0; i < commentList1.size(); i++) {
                                    Comments cmt = commentList1.get(i);
                                    cmt.setMainPost(mainPost2);
                                    newCommentList1.add(cmt);

                                }
                                mainPost2.setComments(newCommentList1);

                                Serializable res2 = postDao.addPost(mainPost2);

                            }
                        }

//                   
                    } catch (NullPointerException ex) {
                    }

                }

//               
            }

        }
        return true;
    }

    @Override
    public String getDressCode(String dressCodeWithDes) {
        String dressCode = "";
        try {

            String[] splitText = dressCodeWithDes.split("-");
            dressCode = splitText[0];

        } catch (NullPointerException ex) {
        }
        return dressCode;

    }

    @Override
    public String getDressCodeWithDes(String msg) {
        String dressCodeWithDes = "";
        try {
            String text = null;
            Pattern p = Pattern.compile("GF.+/-");
            Matcher m = p.matcher(msg);
            while (m.find()) {
                text = m.group();
            }
            String[] splitText = text.split("-");
            dressCodeWithDes = splitText[0] + "-" + splitText[1];

        } catch (NullPointerException ex) {
        }
        return dressCodeWithDes;

    }

}
