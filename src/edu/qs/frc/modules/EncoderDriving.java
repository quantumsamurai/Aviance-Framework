/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.hardware.Joystick1Simplify;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;

/**
 *
 * @author admin
 */
public class EncoderDriving extends AvianceThread{
    public EncoderDriving(){
    AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    }
    protected void iteration(){
       if(Joystick1Simplify.getA()){AvianceTankDrive.gyro.reset();  Hardware.encoder_front_left.reset();
   Hardware.encoder_front_right.reset();}           
       if((Hardware.encoder_front_left.getDistance() /2.2375) <= 60){AvianceTankDrive.front_left.set(1); AvianceTankDrive.front_right.set(-.6711
       );}}
    protected void reset(){}
    
}
