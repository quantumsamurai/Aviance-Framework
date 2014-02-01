/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.hardware.Joystick1Simplify;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author admin
 */
public class Defense extends AvianceThread{
    public Defense(){
    AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    }
    Relay defense = (Relay)Hardware.relays[Hardware.relay_defense];
    Relay comp = (Relay) Hardware.relays[Hardware.relay_compressor];
    protected void iteration(){
       if(Hardware.toggleJoystick1(4)){comp.set(Relay.Value.kOn);
       comp.setDirection(Relay.Direction.kForward);
       }
       else{comp.set(Relay.Value.kOff);
       }
       
      if(Joystick1Simplify.getRightBackButton()){//expand actuator
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
