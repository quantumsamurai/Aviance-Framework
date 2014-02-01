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
    double DPadXValue;
    double DPadYValue;
    double RightJoystick;
    double LeftJoystick;
    protected void iteration(){
        
        DPadXValue = Joystick1Simplify.DPadXAxis();
        DPadYValue = -Joystick1Simplify.DPadYAxis();
        RightJoystick = Joystick1Simplify.RightJoystickYAxis();
        LeftJoystick = Joystick1Simplify.LeftJoystickYAxis();
        
        leftspeed = DPadYValue -DPadXValue + LeftJoystick * .75;
    rightspeed = DPadYValue + DPadXValue + RightJoystick * .75;
    limit(leftspeed);
    limit(rightspeed);
    
    drive_left.set(-leftspeed);
    drive_right.set(rightspeed);
    }
    protected void reset(){}

    private double limit(double speed) {
        if(Math.abs(speed) > 1){ speed = 1;}
        else{speed = speed;}
        return speed;
    }
   
}
