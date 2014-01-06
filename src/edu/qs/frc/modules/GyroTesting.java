/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Darvin
 */
public class GyroTesting extends AvianceThread{
    
    public GyroTesting(){
       // System.out.println("Entered Gyro Testing");
        AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this); // this is opted to run in teleop
        
    }

    Gyro gyro = new Gyro(Hardware.gyro_port);
    double startAngle = (int)gyro.getAngle();
   
    double desiredAngle = 90;
    double difference;
    double value;
    double currentAngle;
    double Kp = .27;
    double tolerance = 5;
    public static double output;
    protected void iteration(){
        SmartDashboard.putString("Gyro Testing", Hardware.Active);
         currentAngle = (int)gyro.getAngle();
       //  System.out.println(currentAngle);
        difference = (int)desiredAngle - currentAngle;
    if( difference> tolerance){
            value = (int)Kp*(difference/100);
            if(value > 1){output=1;}
            else if(value <Kp){value = Kp;}
            else{value = output;}
    }else if(difference < tolerance){output = 0;}
    
        
        
        
    }
    protected void reset(){
              SmartDashboard.putString("Gyro Testing", Hardware.Diasbled);
     //   System.out.println("Gyros Sensor Disabled in GyroTesting");
        
    }
}
