/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;


/**
 *
 * @author admin
 */
public class AvianceTankDrive extends AvianceThread{
    boolean debug = true;
  //  Talon back_left = (Talon) Hardware.pwm[Hardware.talon_back_left];
   // Talon back_right = (Talon) Hardware.pwm[Hardware.talon_back_right];
    Talon front_left = (Talon) Hardware.pwm[Hardware.talon_front_left];
    Talon front_right = (Talon) Hardware.pwm[Hardware.talon_front_right];
    Gyro gyro = new Gyro(Hardware.gyro_port);
    Servo servo = new Servo(4);
     double left;
     double right;
    public AvianceTankDrive(){
        
        AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    }
    
    protected void iteration(){
      
   
        
    if(Hardware.joystick2.getRawButton(1)){gyro.reset();}   
   
    
      
        

        System.out.println("Left "+ Hardware.encoder_front_left.getDistance() /2.2375); // 8.95 per rev
      System.out.println("Right " + Hardware.encoder_front_right.getDistance() /2.2375);// -8.95 per revolution
    // if((Hardware.encoder_front_left.getDistance() /2.2375) <= 60){front_left.set(1); front_right.set(-.6711
  //      );}
{   left = 1*(Hardware.joystick2.getRawAxis(2));
        
         right = -1*(Hardware.joystick2.getRawAxis(4));
        
        front_left.set(left);
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
