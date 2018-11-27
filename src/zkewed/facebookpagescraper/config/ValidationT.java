/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassFiles;

import javax.swing.JTextField;

/**
 *
 * @author Ashan Tharuka
 */
public class ValidationT {

    public static void facebookURL(JTextField textFild) throws IndexOutOfBoundsException{
        String txt = textFild.getText();
        int caretPosition = textFild.getCaretPosition();
        if (!txt.matches("https://www.facebook.com+[/]{1}+[\\w]+/")) {
            txt = txt.substring(0, caretPosition - 1) + txt.substring(caretPosition);
            textFild.setText(txt);
            textFild.setCaretPosition(caretPosition - 1);
        }

    }

   
}
