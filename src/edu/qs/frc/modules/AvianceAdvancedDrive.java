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
 * @author pawel
 */
public class AvianceAdvancedDrive extends AvianceThread{
    
    private static double joystickDeadband = .1;

    
    /**
     * This block automatically adds the class to its respective group when an instance is spawned
     * ,to disable this comment out the line
     */
   public AvianceAdvancedDrive() {
       
       //System.out.println("making tank drive....");
        AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this); // add itself to threadmanager when its created
   }
    
    private static Talon front_left = ((Talon) Hardware.pwm[Hardware.talon_back_left]);
    private static Talon front_right = ((Talon) Hardware.pwm[Hardware.talon_front_right]);
    private static Talon rear_left = ((Talon) Hardware.pwm[Hardware.talon_back_left]);
    private static Talon rear_right = ((Talon) Hardware.pwm[Hardware.talon_back_right]);
    
    
    private static double left_val = 0, right_val = 0;
    
    protected void iteration(){
        {SmartDashboard.putString("TankDrive Status: ", Hardware.Active );}
       boolean EBRAKE = AvianceAdvancedDrive.EBRAKE(Hardware.joystick2.getRawButton(1));
        if(EBRAKE){left_val = 0; right_val = 0;}
        else{
        AvianceAdvancedDrive.JoystickThrottleRight(Hardware.joystick2.getY()); //DO NOT MODIFY
        AvianceAdvancedDrive.JoystickThrottleLeft(Hardware.joystick2.getY());        //DO NOT MODIFY
       if(Hardware.joystick2.getRawButton(Hardware.joystickGyro)){//DO NOT MODIFY
        left_val = GyroTesting.output;
        right_val = GyroTesting.output;
       
        }}

        
   
               { SmartDashboard.putNumber("Drive" , left_val);}


      // System.out.println("Values Left " + left_val + "Right " + right_val);

        front_left.set(left_val); 
        front_right.set(right_val);
        rear_left.set(left_val);
        rear_right.set(right_val);
    }

    protected void reset(){
        {  SmartDashboard.putString("TankDrive Status: ", Hardware.Diasbled );}
        //System.out.println("TANK DRIVE INTERRUPTED");
    }
       public static boolean betweenExclusive(double x, double min, double max) {
       return x>min && x<max;    
   }
       public static double JoystickThrottleRight(double JoystickInput){      
           if(Math.abs(JoystickInput) > joystickDeadband || JoystickInput < joystickDeadband){
           
            if(JoystickInput > joystickDeadband){
        right_val += .01;}
            
            if(JoystickInput < joystickDeadband){
        
        right_val -= .01;}
                  if(JoystickInput < joystickDeadband && JoystickInput > 0){right_val = 0;}
                 betweenExclusive(right_val, -1, 1);
                 if(right_val > 1){ right_val =1;
                  }
                 else if(right_val < -1){ right_val =-1;}
                 if(right_val > 0  && right_val >JoystickInput){
                 right_val = JoystickInput;
                 }
             if(right_val < 0  && right_val <JoystickInput){
                 right_val = JoystickInput;
                 }

        }
return JoystickInput;
}
       public static double JoystickThrottleLeft(double JoystickInput){
             if(Math.abs(JoystickInput) > joystickDeadband || JoystickInput < joystickDeadband){
           
            if(JoystickInput > joystickDeadband){
        left_val += .01;
       }
            
                        if(JoystickInput < joystickDeadband){
        left_val -= .01;
        }
                        if(JoystickInput < joystickDeadband && JoystickInput > 0){left_val = 0;}
                 betweenExclusive(left_val, -1, 1);
                 if(left_val > 1){ left_val =1;
                  }
                 else if(left_val < -1){ left_val = -1;}
                            if(left_val > 0  && left_val >JoystickInput){
                 left_val = JoystickInput;
                 }
             if(left_val < 0  && left_val <JoystickInput){
                 left_val = JoystickInput;
                 }
            

        }
       
       return JoystickInput; }
       public static boolean EBRAKE(boolean x){
       if(x == true ){System.out.println("EBRAKE");}
       return x;}
       
}
