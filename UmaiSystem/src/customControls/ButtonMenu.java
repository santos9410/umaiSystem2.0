/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customControls;

import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

/**
 *
 * @author antonio
 */
public class ButtonMenu extends Button{

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }
    private int idCat;

    public ButtonMenu(String text) {
        this.setText(text);
    }
    
    
    
   
    
   
    
}
