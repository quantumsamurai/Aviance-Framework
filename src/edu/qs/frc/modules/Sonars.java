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
public class Sonars extends AvianceThread {
 public Sonars(){
 AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
 }    
 
 protected void iteration(){
 double sonarVoltage1 = Hardware.sonar1.getAverageVoltage()/9.8 * 1000;
 double sonarVoltage2 = Hardware.sonar2.getAverageVoltage() /98;
 System.out.println("Analog " + sonarVoltage1);
 }
 
 protected void reset(){}
}
