/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qs.Indexer;

import qs.RobotHardware.Hardware;
import qs.RobotHardware.Joystick1Simplify;
import qs.ThreadSetup.AvianceThread;
import qs.ThreadSetup.AvianceThreadManager;
import edu.wpi.first.wpilibj.Talon;
import qs.GeneralModules.AvianceRobot;

/**
 *
 * @author admin
 */
public class IndexingWheels extends AvianceThread{
    public IndexingWheels(){
    AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    
    }
   
    Talon indexer_wheel = (Talon)Hardware.pwm[Hardware.talon_indexer];
    protected void iteration(){
   if(Joystick1Simplify.getRightTriggerButton()){//collector wheels on
       indexer_wheel.set(-.75);
       
   }
   
   
   
   
 
   //
    }
    protected void reset(){
    
    }
    
}
