/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qs.RobotHardware;

import qs.DriveModules.AvianceTankDrive;
import qs.sensors.AccelerometerReadings;
import InsightLTDisplay.DecimalData;
import qs.RobotHardware.Hardware;
import qs.ThreadSetup.AvianceThread;
import qs.ThreadSetup.AvianceThreadManager;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import qs.GeneralModules.AvianceRobot;

/**
 *
 * @author Darvin
 */
public class InsightDevice  extends AvianceThread{
    public InsightDevice(){
    //    System.out.println("Insight Thread");
    AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    
    
    }
    DecimalData disp_batteryVoltage;
    DecimalData acceleration;
    float battVoltage = (float)AvianceRobot.driverStation.getBatteryVoltage();
    float accelerometerX = (float)AccelerometerReadings.accelerometer.getAcceleration(ADXL345_I2C.Axes.kX);
    float gyroAngle =(float) AvianceTankDrive.gyro.getAngle();
    protected void iteration(){
        SmartDashboard.putString("Insight", Hardware.Active);
       // System.out.println(battVoltage);
        Hardware.display.startDisplay();
        acceleration = new DecimalData ("Gyro ");
        acceleration.setData(gyroAngle);
     //  disp_batteryVoltage = new DecimalData("Batt:");
       // disp_batteryVoltage.setData(battVoltage);   
        
        Hardware.display.registerData(acceleration, 2);
       
    
    }
    protected void reset(){
        SmartDashboard.putString("Insight", Hardware.Diasbled);
        Hardware.display.stopDisplay();
  //  System.out.println("Insight Thread Interrupted");
    }
    
}
