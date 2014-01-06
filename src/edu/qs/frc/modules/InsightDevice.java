/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import InsightLT.DecimalData;
import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    float battVoltage = (float)AvianceRobot.driverStation.getBatteryVoltage();
    protected void iteration(){
        SmartDashboard.putString("Insight", Hardware.Active);
       // System.out.println(battVoltage);
        //Hardware.display.startDisplay();
       disp_batteryVoltage = new DecimalData("Batt:");
        disp_batteryVoltage.setData(battVoltage);   
        Hardware.display.registerData(disp_batteryVoltage, 2);
       
    
    }
    protected void reset(){
        SmartDashboard.putString("Insight", Hardware.Diasbled);
  //  System.out.println("Insight Thread Interrupted");
    }
    
}
