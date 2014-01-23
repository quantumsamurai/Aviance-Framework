/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author admin
 */
public class AutonomousRoutines extends AvianceThread{
        
     double routine;
    public  AutonomousRoutines(){
    
    AvianceThreadManager.getInstance().addThread(AvianceRobot.autonomousThreads, this);
    }
    
    
    public void iteration(){
        routine =   AvianceRobot.poller.getRoutine();
    if(routine ==1 ){System.out.println("Entered Auto 1");
        AvianceTankDrive.front_left.set(.5); AvianceTankDrive.front_right.set(.5);
    Time(3); 
      AvianceTankDrive.front_left.set(0); AvianceTankDrive.front_right.set(0);
      Time(60);
          
    }
    if(routine == 2){System.out.println("Entered Auto 2");}
if(routine ==3)  {  System.out.println("Entered Auto 3");}
    if(routine == 4){System.out.println("Entered Auto 4");}
    if(routine == 5){System.out.println("Entered Auto 5");}
    
    
    
    
    }
    public void reset(){}
    public void startup(){}
    public static void Time(final double seconds) {
        try {
            Thread.sleep((long) (seconds * 1e3));
        } catch (final InterruptedException e) {
        }
    }
    
}
