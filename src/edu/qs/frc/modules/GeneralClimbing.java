/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Darvin
 */
public class GeneralClimbing extends AvianceThread{
    
    public GeneralClimbing(){
   // System.out.println("Climbing Thread...");
    AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this); 
    }
    private Relay arm = ((Relay) Hardware.relays[Hardware.relay_arm]);
    private Relay winch = ((Relay) Hardware.relays[Hardware.relay_winch]);
    
    
    protected void iteration(){
              for (int i = 0; i < 1; i++){SmartDashboard.putString("Climbing", Hardware.Active);}
        if(Hardware.joystick1.getRawButton(Hardware.joystickArm)){ arm.set(Relay.Value.kOn); arm.setDirection(Relay.Direction.kForward);}
        else if(Hardware.joystick1.getRawButton(Hardware.joystickWinch)){winch.set(Relay.Value.kOn); winch.setDirection(Relay.Direction.kForward);}
        else{arm.set(Relay.Value.kOff); winch.set(Relay.Value.kOff);}
        
    }

    
    protected void reset(){
             for (int i = 0; i < 1; i++){SmartDashboard.putString("Climbing", Hardware.Diasbled);}
      //  System.out.println("Climb Interrupted");
    }
    
}
