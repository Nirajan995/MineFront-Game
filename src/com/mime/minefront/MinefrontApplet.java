package com.mime.minefront;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Component;

/**
 *
 * @author DELL
 */
public class MinefrontApplet extends Applet {
    private static final long serialVersionUID=1L;
    
    private Display display=new Display();
    
    public void init(){
        setLayout(new BorderLayout());
        
    }
    
    public void start(){
        display.start();
    }
    
    public void stop(){
        display.stop();
    }
}
