/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.service;

import com.restfb.exception.FacebookNetworkException;

/**
 *
 * @author DELL
 */
public interface PostService {

    public boolean fetchDetails()throws FacebookNetworkException;

    public String getDressCode(String msg);

    public String getDressCodeWithDes(String msg);
}
