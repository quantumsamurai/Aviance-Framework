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
public class AvianceTankDrive extends AvianceThread{
    
    Talon back_left = (Talon) Hardware.pwm[Hardware.talon_back_left];
    Talon back_right = (Talon) Hardware.pwm[Hardware.talon_back_right];
    Talon front_left = (Talon) Hardware.pwm[Hardware.talon_front_left];
    Talon front_right = (Talon) Hardware.pwm[Hardware.talon_front_right];
    
    public AvianceTankDrive(){
        AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    }
    
    protected void iteration(){
        
        double left = Hardware.joystick2.getY();
        double right = Hardware.joystick1.getY();
        
        back_left.set(left);
        front_left.set(left);
        back_right.set( -1 * right);
        front_right.set( -1 * right);
    }
    
    protected void reset(){
        System.out.println("Drive Interrupt");
    }
    
    protected void startup(){
        System.out.println("Starting Drive");
    }
}
