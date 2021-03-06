/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.hardware;

import InsightLT.InsightLT;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
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
    
    //Analog
  public static  Gyro gyro = new Gyro(1);
   public static AnalogChannel sonar = new AnalogChannel(2);
    // PWM ports for each element
    public static final int talon_front_left = 1; 
    public static final int talon_front_right = 2;
  //  public static final int talon_back_left = 3;
    //public static final int talon_back_right = 4;
 //   public static final int talon_indexer = 4;
    public static final int talon_left_arm = 3;
    public static final int talon_shooter = 4;
    //public static final int talon_right_arm = 4;
    //public static final int relay_defense = 2;
    //public static final int relay_winch = 3;
  //  public static final int relay_compressor =1;
    
    //toggle variables
     private static boolean motorStatus;
private static boolean previousButton = false;
private static boolean currentButton = false;
     
    //ADXL345 Accelerometer I2C
    public static final int accelerometerI2CPort = 1;
   
    //Axis Camera IP
    public static final String CamIP = "10.16.1.11";
 
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
    
    //joystick Deadband
                     public static double deadband = .3;

    
    public static final Encoder encoder_front_left = new Encoder(2,1, false, EncodingType.k4X);  //this is a more concise way of doing things even though it's slightly inconsistent
    public static final Encoder encoder_front_right = new Encoder(3,4, false, EncodingType.k4X);
    public static final Encoder encoder_shooter = new Encoder(6,7, false, EncodingType.k4X);
//    public static final Encoder encoder_back_left = new Encoder(5,6);
  //  public static final Encoder encoder_back_right = new Encoder(7,8);
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
    //    pwm[talon_front_left] = new Talon(talon_front_left);
      //  pwm[talon_front_right] = new Talon(talon_front_right);
       // pwm[talon_indexer] = new Talon(talon_indexer);
//        pwm[talon_back_left] = new Talon(talon_back_left);
//        pwm[talon_back_right] = new Talon(talon_back_right); //yep, now lets make sure it works
//        
//        relays[relay_defense] = new Relay(relay_defense);
     
      //  relays[relay_winch] = new Relay(relay_winch);
       
encoder_front_left.setDistancePerPulse((4*Math.PI) / 360);
        encoder_front_right.setDistancePerPulse((4*Math.PI) / 360);
        encoder_front_left.reset();
        encoder_front_right.reset();
        encoder_shooter.reset();
        encoder_front_left.start();
        encoder_front_right.start();
        encoder_shooter.start();

        
    //    encoder_back_left.start();    //or maybe we should do this in its own module? that works
      //  encoder_back_right.start();
     
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
