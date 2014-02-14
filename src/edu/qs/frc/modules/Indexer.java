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
public class Indexer extends AvianceThread{
    Talon arm = new Talon(3);
    
    public Indexer(){
    AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    }
    protected void iteration(){
      if(Joystick1Simplify.getLeftTriggerButton()){
    arm.set(1);} // intake
    
      else if(Joystick1Simplify.getRightTriggerButton()){arm.set(-1);
      }// extract
      else{arm.set(0);
      }
    }
    protected void reset(){
    System.out.println("ARM Disabled now filing disability");
    }
}
