/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Darvin
 */
public class AvianceGeneralShooter extends AvianceThread{
    
    public AvianceGeneralShooter(){
        AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this); //just like that
    }
    
 
private static Talon shooter = ((Talon) Hardware.pwm[Hardware.talon_shooter]);
private static String shooter_status = "False";

    
    
    protected void iteration(){ // toggle method do not touch

        
        if(Hardware.toggleJoystick2(3)){
            shooter_status = "True";
  // true value
   } else{shooter_status = "False";}
   
    }
    
    
    
//    protected void startup(){
//        SmartDashboard.putString("Shooter Status", Hardware.Active );
//        SmartDashboard.putString("Shooter is", shooter_status );    
//    }
  

    protected void reset(){
         SmartDashboard.putString("Shooter Status", Hardware.Diasbled );
       // System.out.println("GENERAL SHOOTER INTERRUPTED");
    }
}
