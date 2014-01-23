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
public class AvianceHybridDrive extends AvianceThread{
    public AvianceHybridDrive(){
        AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    }
    Talon drive_left = (Talon) Hardware.pwm[Hardware.talon_front_left];
    Talon drive_right = (Talon) Hardware.pwm[Hardware.talon_front_right];
       double leftspeed;
    double rightspeed;
    protected void iteration(){
 
    
    if(Joystick1Simplify.DPadXAxis() != 0 ){leftspeed = Joystick1Simplify.DPadXAxis();
    rightspeed = Joystick1Simplify.DPadXAxis();}   
   else if(Joystick1Simplify.DPadYAxis() != 0 ){leftspeed = Joystick1Simplify.DPadYAxis();
    rightspeed = Joystick1Simplify.DPadYAxis();}
    }
//    else {leftspeed = Joystick1Simplify.LeftJoystickYAxis();  
//    rightspeed = Joystick1Simplify.RightJoystickYAxis();}
//    
    
    protected void reset(){}
}
