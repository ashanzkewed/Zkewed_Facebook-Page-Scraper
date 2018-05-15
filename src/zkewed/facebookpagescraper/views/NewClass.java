/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zkewed.facebookpagescraper.views;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author DELL
 */
public class NewClass {
     final static Logger LOGGER = Logger.getLogger("NewClass");
     public static void main(String[] args) {
         PropertyConfigurator.configure("./src/log4j.properties");
        LOGGER.debug("debuggggggggggggg");
    }
}
