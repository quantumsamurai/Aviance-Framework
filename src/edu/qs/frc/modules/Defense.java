/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.hardware.Joystick1Simplify;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author admin
 */
public class Defense extends AvianceThread{
    public Defense(){
    AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    System.out.println("Defense On");}
    //Relay defense = (Relay)Hardware.relays[Hardware.relay_defense];

//    Compressor comp = new Compressor(5, Hardware.relay_compressor);
    protected void iteration(){

    }
    protected void reset(){}
}
