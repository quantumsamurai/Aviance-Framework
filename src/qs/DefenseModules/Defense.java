/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qs.DefenseModules;

import qs.RobotHardware.Hardware;
import qs.RobotHardware.Joystick1Simplify;
import qs.ThreadSetup.AvianceThread;
import qs.ThreadSetup.AvianceThreadManager;
import edu.wpi.first.wpilibj.Relay;
import qs.GeneralModules.AvianceRobot;

/**
 *
 * @author admin
 */
public class Defense extends AvianceThread{
    public Defense(){
    AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    }
    Relay defense = (Relay)Hardware.relays[Hardware.relay_defense];
    protected void iteration(){
      if(Joystick1Simplify.getLeftTriggerButton()){//expand actuator
       defense.set(Relay.Value.kOn);
       defense.setDirection(Relay.Direction.kForward);
   }
   else if(Joystick1Simplify.getLeftBackButton()){
       defense.set(Relay.Value.kOn);
       defense.setDirection(Relay.Direction.kReverse);
   //retract
   }
   else{ defense.set(Relay.Value.kOff);
      }
    }
    protected void reset(){}
}
