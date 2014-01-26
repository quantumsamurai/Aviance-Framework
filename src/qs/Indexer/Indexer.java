/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qs.Indexer;

import qs.RobotHardware.Hardware;
import qs.ThreadSetup.AvianceThread;
import qs.ThreadSetup.AvianceThreadManager;
import edu.wpi.first.wpilibj.Talon;
import qs.GeneralModules.AvianceRobot;

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
