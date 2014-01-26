/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qs.sensors;

import qs.GeneralModules.AvianceRobot;
import qs.RobotHardware.Hardware;
import qs.ThreadSetup.AvianceThread;
import qs.ThreadSetup.AvianceThreadManager;

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
