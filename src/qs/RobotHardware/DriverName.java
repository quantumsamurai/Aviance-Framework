/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qs.RobotHardware;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import qs.GeneralModules.AvianceRobot;
import qs.ThreadSetup.AvianceThread;
import qs.ThreadSetup.AvianceThreadManager;

/**
 *
 * @author Darvin
 */
public class DriverName extends AvianceThread{
    public DriverName(){
    AvianceThreadManager.getInstance().addThread(AvianceRobot.systemThreadGroup, this);
    }
     boolean driver1;
    boolean driver2;
     boolean driver3;
   // boolean driver4;
    public static String driver;
    
    protected void iteration(){
   driver1 = SmartDashboard.getBoolean("Driver 1");
  driver2 = SmartDashboard.getBoolean("Driver 2");
    driver3 = SmartDashboard.getBoolean("Driver 3");
   // driver4 = SmartDashboard.getBoolean("Driver 4");
    
    if(driver1 == true){driver = "Naresh";}
    if(driver2 == true){driver = "Nelson";}
    if(driver3 == true){driver = "Sahir";}
    
    }
    protected void reset(){}
}
