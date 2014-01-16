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
public class AutonomousOne extends AvianceThread{
    public  AutonomousOne(){
    
    AvianceThreadManager.getInstance().addThread(AvianceRobot.autonomousThreads, this);
    }
    
    
    public void iteration(){}
    public void reset(){}
    public void startup(){}
    
}
