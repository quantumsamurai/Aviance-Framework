/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.hardware;

import InsightLT.InsightLT;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Darvin
 */
public class Hardware {
    // hardware will be accessed depending on type
    
    // PWM ports for each element
    public static final int talon_front_left = 1; //oops we need to give it actual values
    public static final int talon_front_right = 2;
    public static final int talon_back_left = 3;
    public static final int talon_back_right = 4;
    public static final int talon_shooter = 5;
    public static final int relay_arm = 1;
    public static final int relay_winch = 2;
    
    //toggle variables
     private static boolean motorStatus;
private static boolean previousButton = false;
private static boolean currentButton = false;
    
    //ADXL345 Accelerometer I2C
    public static final int accelerometerI2CPort = 1;
   
    
 
    //Digital I/O ports
    
    
    //joystick buttons for features
    public static final int joystickSwapButton = 6;
    public static final int joystickArm = 7;
    public static final int joystickWinch = 8;
    public static final int joystickGyro = 5;
    
    //array representation of I/O
    public static final PWM[] pwm = new PWM[10]; //the hardware needs to be initialized...  (an empty array, no objects) how may ports are there?? 10
    public static final Relay[] relays = new Relay[8]; // empty relay array
    
    //Individualized hardware
    public static Joystick joystick1 = new Joystick(1); // joysticks are NOT FINAL; this allows the JoystickSwapper to swap them
    public static Joystick joystick2 = new Joystick(2); //we can make them non-final and create a module that listens for a button to swap them and then swaps them
    
    public static final Encoder encoder_front_left = new Encoder(1,2);  //this is a more concise way of doing things even though it's slightly inconsistent
    public static final Encoder encoder_front_right = new Encoder(3,4);
    public static final Encoder encoder_back_left = new Encoder(5,6);
    public static final Encoder encoder_back_right = new Encoder(7,8);
       public static final int gyro_port = 1;
    public static final InsightLT display = new InsightLT(InsightLT.TWO_ONE_LINE_ZONES); // InsightLT Constructor
    public static final String Active ="Active";
    public static final String Diasbled = "Disabled";
    
    // we need to start the ecoders literally
   /***
    * This is responsible for initializing hardware that isn't already when the class is loaded into the VM
    */
    static{
        //this is going to be slighlty more involved..
        //actually, its not going to be difficult at all..
        System.out.println("Initializing Hardware...");
        pwm[talon_front_left] = new Talon(talon_front_left);
        pwm[talon_front_right] = new Talon(talon_front_right);
        pwm[talon_back_left] = new Talon(talon_back_left);
        pwm[talon_back_right] = new Talon(talon_back_right); //yep, now lets make sure it works
        
        relays[relay_arm] = new Relay(relay_arm);
        relays[relay_winch] = new Relay(relay_winch);
       
        
        encoder_front_left.start();
        encoder_front_right.start();
        encoder_back_left.start();    //or maybe we should do this in its own module? that works
        encoder_back_right.start();
     
    }
      public static boolean toggleJoystick1(int buttonnumber){
      previousButton = currentButton;
currentButton = Hardware.joystick1.getRawButton(buttonnumber);

if (currentButton && !previousButton) 
{motorStatus = motorStatus ? false : true;

}

return motorStatus;
    }
        public static boolean toggleJoystick2(int buttonnumber){
      previousButton = currentButton;
currentButton = Hardware.joystick2.getRawButton(buttonnumber);

if (currentButton && !previousButton) 
{motorStatus = motorStatus ? false : true;

}

return motorStatus;
    }
}
