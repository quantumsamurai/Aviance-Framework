/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Joystick1Simplify;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author admin
 */
public class SFXTest extends AvianceThread{
    public SFXTest(){
    AvianceThreadManager.getInstance().addThread(AvianceRobot.systemThreadGroup, this);
    }
    protected void iteration(){
    SmartDashboard.putBoolean("Button A", Joystick1Simplify.getA());
      SmartDashboard.putBoolean("Button B", Joystick1Simplify.getB());
        SmartDashboard.putBoolean("Button Y", Joystick1Simplify.getY());
          SmartDashboard.putBoolean("Button X", Joystick1Simplify.getX());
            SmartDashboard.putBoolean("Button Back", Joystick1Simplify.getBackButton());
              SmartDashboard.putBoolean("Button Start", Joystick1Simplify.getStartButton());
                SmartDashboard.putBoolean("Button Left Trigger", Joystick1Simplify.getLeftTriggerButton());
        SmartDashboard.putNumber("Drive Left", AvianceTankDrive.front_left.getSpeed());
        SmartDashboard.putNumber("Drive Right", AvianceTankDrive.front_right.getSpeed());
        SmartDashboard.putNumber("Gyro Angle", AvianceTankDrive.gyro.getAngle());
        SmartDashboard.putNumber("Sonar", Sonars.sonarVoltage1);
    }
    protected void reset(){}
}
