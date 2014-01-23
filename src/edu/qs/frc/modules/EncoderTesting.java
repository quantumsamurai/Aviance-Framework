/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;

/**
 *
 * @author admin
 */
public class EncoderTesting extends AvianceThread{
public EncoderTesting(){
AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
}    
protected void iteration(){
    
System.out.println("Left Encoder Rate: " + Hardware.encoder_front_left.getRate());
}
protected void reset(){}
}
