/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;

/**
 *
 * @author admin
 */
public class FIX extends AvianceThread{
    public FIX(){
    AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    }
    
    protected void iteration(){
    System.out.println("FIX OPERATION");
    }
    protected void reset(){
    
    }
}
