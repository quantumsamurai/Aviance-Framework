/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.hardware;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;

/**The HardwareOLD class
 * 
 * This class is singlehandedly responsible for initializing and maintaining hardware
 * The robot *MUST* be physically wired to match the schematics, which means the
 * hardware must be attached in blocks, according to the positions defined in this class
 * 
 * **IMPORTANT** IF HARDWARE ASSIGNMENT ON THE ROBOT IS CHANGED, THIS CLASS MUST REFLECT
 *  THAT CHANGE (and vice-versa)
 *
 * note: the structure of this class encourages (or mandates) physical organization
 * @author pawel
 */
public final class HardwareOLD {
    /*
     * The amount of each type of hardware (used for initialization)
     * (for multiport hardware such as solendoids, total number of ports used)
     */
    public final static int knumGyros = 1;
    public final static int knumServos = 2;
    public final static int knumTalons = 5;     //AHA! this was temporary and i was saving it for when we wire the thing, lets adjust these.... you do 
    public final static int knumRelays = 2;
    public final static int knumEncoders = 2;
    public final static int knumJoysticks = 3;
    public final static int knumSolenoids = 1;
    public final static int knumUltrasonics = 1;
    /*
     * The start ports for each (used for initialization)
     * (for multiport hardware the ports are to be grouped in blocks for each element)
     */
    public final static int kstartGyros = 0;// are these values good ? for now they should be but may cause conflicts when constructing.. they are the starting ports for each sensors block (since theyll be grouped for sake of organization) ok
    public final static int kstartServos = 0;
    public final static int kstartTalons = 0;
    public final static int kstartRelays = 0;
    public final static int kstartEncoders = 0;
    public final static int kstartJoysticks = 0;
    public final static int kstartSolenoids = 0;
    public final static int kstartUltrasonics = 0;
    public final static int kstartAccelerometer = 0;
    /*
     * The arrays they're held in (used for accessing elements)
     */
    public final static Gyro[] gyros = new Gyro[knumGyros];
    public final static Servo[] servos = new Servo[knumServos];
    public final static Talon[] talons = new Talon[knumTalons];
    public final static Relay[] relays = new Relay[knumRelays];
    public final static Encoder[] encoders = new Encoder[knumEncoders];
    public final static Joystick[] joysticks = new Joystick[knumJoysticks];
    public final static Solenoid[] solenoids = new Solenoid[knumSolenoids];
    public final static Ultrasonic[] ultrasonics = new Ultrasonic[knumUltrasonics];
    /*
     * And how each hardware is to be known as (it's slot in the arrays, starting at zero)
     */
    public final static int TALON_DRIVE_FRONT_LEFT = 0;
    public final static int TALON_DRIVE_FRONT_RIGHT = 0;
    public final static int TALON_DRIVE_REAR_LEFT = 0;
    public final static int TALON_DRIVE_REAR_RIGHT = 0;
    public final static int JOYSTICK_LEFT = 1;
    public final static int JOYSTICK_RIGHT = 2;
    public final static int ENCODER_FRONT_LEFT= 0;
    public final static int ENCODER_FRONT_RIGHT= 0;
    public final static int ENCODER_REAR_LEFT= 0;
    public final static int ENCODER_REAR_RIGHT= 0;
    public final static int ULTRASONIC_LEFT = 0;
    public final static int ULTRASONIC_RIGHT = 0;
    public final static int GYRO_XY = 0;
    /*
     * For some hardware there is only one element, life can be simplified in this way
     */
    public final static ADXL345_I2C accelerometer = null;
    
    /**
     * All hardware is initialized here, any additional parameters (such as relay inversion)
     * should be set here as well
     */
static{ //
    System.out.println("init hardware...");
    try{
        for(int c = kstartTalons; c < kstartTalons + knumTalons; c++){
            talons[c] = new Talon(c);
        }
        for(int c = kstartRelays; c < kstartRelays + knumRelays; c++){
            relays[c] = new Relay(c);
        }
        for(int c = kstartJoysticks; c < kstartJoysticks + knumJoysticks; c++){
            joysticks[c] = new Joystick(c);
        }
    }
    catch(Exception e){//yea
        //one of the hardware failed to initialize
    }
}
}
