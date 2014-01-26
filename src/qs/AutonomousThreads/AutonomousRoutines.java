/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qs.AutonomousThreads;

import qs.DriveModules.AvianceTankDrive;
import qs.ThreadSetup.AvianceThread;
import qs.ThreadSetup.AvianceThreadManager;
import edu.wpi.first.wpilibj.Timer;
import qs.GeneralModules.AvianceRobot;

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
    (new AutonomousRoutine1()).start();

    }
    
    if(routine == 2){System.out.println("Entered Auto 2");
     (new AutonomousRoutine2()).start();
    
    }
    
if(routine ==3)  {  System.out.println("Entered Auto 3");
 (new AutonomousRoutine3()).start();
}

    if(routine == 4){System.out.println("Entered Auto 4");
     (new AutonomousRoutine4()).start();
    }
    
    if(routine == 5){System.out.println("Entered Auto 5");
     (new AutonomousRoutine5()).start();
    }
   
    
    
    
    }
    public void reset(){}
    public void startup(){}

    
}
