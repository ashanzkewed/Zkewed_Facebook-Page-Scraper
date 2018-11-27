/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.fbclient;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

/**
 *
 * @author DELL
 */
public class FbClientConnection {

    private static FbClientConnection fbClientConnection;
    private FacebookClient facebookClient;
    public static String accessToken="";  

    private FbClientConnection() {
        facebookClient = new DefaultFacebookClient(accessToken, Version.LATEST);
    }   

    public static FbClientConnection getFbClientConnection() {
        if (fbClientConnection == null) {
            fbClientConnection = new FbClientConnection();
        }
        return fbClientConnection;
    }

    public FacebookClient getFacebookClient() {
        return facebookClient;
    }
}
