/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qs.AutonomousThreads;

import qs.RobotHardware.TimeClass;
import qs.DriveModules.AvianceTankDrive;
import qs.ThreadSetup.AvianceThread;

/**
 *
 * @author Darvin
 */
 
public class AutonomousRoutine1 extends AvianceThread{
protected void iteration(){
            AvianceTankDrive.front_left.set(.5); AvianceTankDrive.front_right.set(.5);
    TimeClass.Time(3); 
      AvianceTankDrive.front_left.set(0); AvianceTankDrive.front_right.set(0);
      TimeClass.Time(60);
          
}
protected void reset(){}
}
