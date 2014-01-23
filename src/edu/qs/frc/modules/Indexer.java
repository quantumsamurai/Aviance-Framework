/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
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
      if(Hardware.joystick1.getRawButton(3)){
    arm.set(.5);}
    
      else if(Hardware.joystick1.getRawButton(1)){arm.set(-.5);
      }
      else{arm.set(0);
      }
    }
    protected void reset(){
    System.out.println("ARM Disabled now filing disability");
    }
}
