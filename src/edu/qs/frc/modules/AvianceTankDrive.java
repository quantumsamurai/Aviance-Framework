/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;


/**
 *
 * @author admin
 */
public class AvianceTankDrive extends AvianceThread{
    boolean debug = true;
  //  Talon back_left = (Talon) Hardware.pwm[Hardware.talon_back_left];
   // Talon back_right = (Talon) Hardware.pwm[Hardware.talon_back_right];
public static     Talon leftmotor = (Talon) Hardware.pwm[Hardware.talon_front_left];
    public static Talon front_right = (Talon) Hardware.pwm[Hardware.talon_front_right];
    
   // Servo servo = new Servo(4);
     double left;
     double right;
    
     
     public AvianceTankDrive(){
        
        AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    }
    
    protected void iteration(){
      
   
        

   
    
      
        

        System.out.println("Left Encoder"+ Hardware.encoder_front_left.getDistance() /2.2375); // 8.95 per rev
     System.out.println("Right Encoder" + Hardware.encoder_front_right.getDistance() /2.2375);// -8.95 per revolution
 

   {   left = -.5*(Hardware.joystick1.getRawAxis(2));
        
         right = .5*(Hardware.joystick1.getRawAxis(4));
        
        leftmotor.set(left);
        //front_left.set(left);
        //back_right.set( -1 * right);
        front_right.set(right);}
      
    }
    
    protected void reset(){
        System.out.println("Drive Interrupt");
    }
    
    protected void startup(){
        System.out.println("Starting Drive");
    }
    

    }
