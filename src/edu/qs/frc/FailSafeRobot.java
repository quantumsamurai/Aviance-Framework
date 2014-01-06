/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.modules.AvianceRobot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Darvin
 */
public class FailSafeRobot extends SimpleRobot{



    Talon front_left = ((Talon) Hardware.pwm[Hardware.talon_front_left]);
    Talon back_left = ((Talon) Hardware.pwm[Hardware.talon_back_left]);
    Talon front_right = ((Talon) Hardware.pwm[Hardware.talon_front_right]);
    Talon back_right = ((Talon) Hardware.pwm[Hardware.talon_back_right]);
   
    

    
    
    public static void main(String[] args){
      
        //fuck it we'll do this later
        System.out.println("FAILSAFE ROBOT HAS BEEN STARTED"); //it works
     
        

    }
}
