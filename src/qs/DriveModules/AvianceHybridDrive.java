/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qs.DriveModules;

import qs.RobotHardware.Hardware;
import qs.RobotHardware.Joystick1Simplify;
import qs.ThreadSetup.AvianceThread;
import qs.ThreadSetup.AvianceThreadManager;
import edu.wpi.first.wpilibj.Talon;
import qs.GeneralModules.AvianceRobot;
import qs.RobotHardware.DriverName;

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
    boolean speedlimitenforced = false;
       double speedlimit = 1;
    protected void iteration(){
        
        DPadXValue = Joystick1Simplify.DPadXAxis();
        DPadYValue = Joystick1Simplify.DPadYAxis();
        RightJoystick = Joystick1Simplify.RightJoystickYAxis();
        LeftJoystick = Joystick1Simplify.LeftJoystickYAxis();
        
        if(speedlimitenforced == true){ DriverSpeedLimit(DriverName.driver);}
        else{speedlimit = 1;}
     
        
        leftspeed = DPadYValue -DPadXValue + LeftJoystick * speedlimit;
    rightspeed = DPadYValue + DPadXValue + RightJoystick;
    limit(leftspeed);
    limit(rightspeed);
    
    drive_left.set(leftspeed);
    drive_right.set(rightspeed);
    }
    protected void reset(){}

    private double limit(double speed) {
        if(Math.abs(speed) > 1){ speed = 1;}
        else{speed = speed;}
        return speed;
    }
   private double DriverSpeedLimit(String Driver){
   if(Driver == "Naresh"){ speedlimit = 1;}
   if(Driver == "Nelson"){ speedlimit = 1;}
   if(Driver == "Sahir"){speedlimit = .8;}
   return speedlimit;}
}
