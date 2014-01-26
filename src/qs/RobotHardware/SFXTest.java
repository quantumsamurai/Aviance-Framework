/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qs.RobotHardware;

import qs.DriveModules.AvianceTankDrive;
import qs.sensors.Sonars;
import qs.RobotHardware.Joystick1Simplify;
import qs.ThreadSetup.AvianceThread;
import qs.ThreadSetup.AvianceThreadManager;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import qs.GeneralModules.AvianceRobot;

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
