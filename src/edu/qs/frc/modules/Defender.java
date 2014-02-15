/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.hardware.Joystick1Simplify;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author admin
 */
public class Defender {
    public Defender(){
  AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    System.out.println("Defense On");}

    protected void iteration(){

    }
    protected void reset(){}   
}
