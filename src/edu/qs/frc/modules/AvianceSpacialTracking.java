/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 * @author pawel
 */
public class AvianceSpacialTracking extends AvianceThread{
    private Encoder e_front_left = Hardware.encoder_front_left;
    private Encoder e_front_right = Hardware.encoder_front_right;
    private Encoder e_rear_left = Hardware.encoder_back_left;
    private Encoder e_rear_right = Hardware.encoder_back_right;
    
//    private Ultrasonic u_left = HardwareOLD.ultrasonics[HardwareOLD.ULTRASONIC_LEFT];
//    private Ultrasonic u_right = HardwareOLD.ultrasonics[HardwareOLD.ULTRASONIC_RIGHT];
//    private Gyro gyro = HardwareOLD.gyros[HardwareOLD.GYRO_XY];
//    private ADXL345_I2C accelerometer = HardwareOLD.accelerometer;
    
    {
        AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this); // continuous thread
    }
   
    
    
    /**
     * Gets the heading angle to the gyro relative to when the competition began
     * @return 
     */
    public double headingAngle(){
        return 0;
    }
    
    /**
     * Computes the robot's angle to the wall (180 if no wall)
     * @return 
     */
    public double sonarAngle(){
        return 0;
    }
    
    /**
     * Gets the ratio of speed between both sides of the robot L:R 
     * @return 
     */
    public double wheelSpeedRatio(){
        return 0;
    }
    
    /**
     * Gets the total distance traveled by the robot in the forward direction
     * based on values from the encoders (accelerometer is not guaranteed to be very accurate but may be used in the future)
     * @return 
     */
    public double totalMileage(){
        return 0;
    }
    
    
    /**
     * The main thread responsible for updating all sensor values according to sensor input
     */
    protected void iteration(){
      //  System.out.println("SENSOR READINGS NECESSARY TO READ");
        //get values from sensors
        //perform math
        //update class variables
    }
    protected void reset(){
   // System.out.println("SENSOR READINGS INTERRUPTED");
    }
}
