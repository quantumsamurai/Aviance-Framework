/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.hardware.Joystick1Simplify;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author admin
 */
public class Defense extends AvianceThread{
    public Defense(){
    AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    System.out.println("Defense On");}
    Relay defense = (Relay)Hardware.relays[Hardware.relay_defense];

    Compressor comp = new Compressor(5, Hardware.relay_compressor);
    protected void iteration(){
       if(Hardware.toggleJoystick1(4)){comp.stop();
       }else{comp.start();}
       
      if(Joystick1Simplify.getLeftBackButton()){//expand actuator
       defense.set(Relay.Value.kOn);
       defense.setDirection(Relay.Direction.kForward);
   }
   else if(Joystick1Simplify.getRightBackButton()){
       defense.set(Relay.Value.kOn);
       defense.setDirection(Relay.Direction.kReverse);
   //retract
   }
   else{ defense.set(Relay.Value.kOff);
      }
    }
    protected void reset(){}
}
