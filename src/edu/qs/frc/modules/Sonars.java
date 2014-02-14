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
 public static double sonarVoltage1;
 protected void iteration(){
//sonarVoltage1 = Hardware.sonar1.getAverageVoltage()/ 98*1000;
 double sonarVoltage = Hardware.sonar.getAverageVoltage() /98;
 System.out.println("Analog " + sonarVoltage);
 }
 
 protected void reset(){}
}
