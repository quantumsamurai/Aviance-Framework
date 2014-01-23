/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.hardware.Joystick1Simplify;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.Talon;

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
