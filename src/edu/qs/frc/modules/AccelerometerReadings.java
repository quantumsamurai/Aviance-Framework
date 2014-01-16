/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;


import edu.qs.frc.hardware.Hardware;

import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Darvin
 */
public class AccelerometerReadings extends AvianceThread{
    private double accelerationX;
    private double accelerationY;
    private double accelerationZ;
    private double updateInterval; //time between each reading how many ms? //each iteration we need to find the time difference, we cant rely on sleep
    private String active = "Active";
    private String disabled = "Disabled";
    
        
        public AccelerometerReadings(){
        System.out.println("AccelerometerReadings");
    AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    
    
    }
     public static ADXL345_I2C accelerometer = new ADXL345_I2C(Hardware.accelerometerI2CPort, ADXL345_I2C.DataFormat_Range.k4G); 
    
    
    protected void iteration(){
       
               accelerationX = accelerometer.getAcceleration(ADXL345_I2C.Axes.kX);
        accelerationY = accelerometer.getAcceleration(ADXL345_I2C.Axes.kY);
       accelerationZ = accelerometer.getAcceleration(ADXL345_I2C.Axes.kZ);
       
    System.out.println("Accelerometer X" + accelerationX);
      System.out.println("Accelerometer Y" + accelerationY);
        System.out.println("Accelerometer Z" + accelerationZ);
    }
    protected void startup(){SmartDashboard.putString("Accelerometer Status", active );}

    protected void reset(){
        SmartDashboard.putString("Accelerometer Status", disabled);
   // System.out.println("Accelerometer Interrupted");
    }
    }

    

