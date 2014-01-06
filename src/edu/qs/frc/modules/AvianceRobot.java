/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import com.sun.squawk.Isolate;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadInterruptedException;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.communication.FRCControl;
import edu.wpi.first.wpilibj.communication.UsageReporting;

/**
 *
 * @author pawel
 */
public final class AvianceRobot extends AvianceThread{
    
    private AvianceThreadManager threadManager = AvianceThreadManager.getInstance();
    public static DriverStation driverStation= DriverStation.getInstance();
    private Watchdog watchdog = Watchdog.getInstance();
    private AutonomousRoutinePoller poller;
    private AvianceGeneralShooter shooter;
    
    public static final String systemThreadGroup = "roboxsysthreads";
    public static final String teleopThreads = "teleoperatedthreads";
    public static final String continiousThreads = "continiousthreads";// ok that makes sense, 
    //public static final String autonomousThreads1 = "autonomousthreads1";
    public static final String[] autonomousRoutines = new String[5];
    
   public AvianceRobot() { // where did you start the network, the network for the robot programming
       
        watchdog.setEnabled(false);
        FRCControl.observeUserProgramStarting();
        UsageReporting.report(UsageReporting.kResourceType_Language, UsageReporting.kLanguage_Java);
 
        threadManager.createGroup(teleopThreads);
        threadManager.createGroup(systemThreadGroup);
        
        threadManager.addThread(systemThreadGroup, this);
        threadManager.addThread(systemThreadGroup, poller = new AutonomousRoutinePoller()); //the robot adds this in manually, and not in the entry point
        threadManager.startThreads(systemThreadGroup);
        
        
        // I had a feeling that it was a switch case :)
        //threadManager.createGroup(autonomousThreads1); //this we'll fic in a while
        for(int i = 0; i < autonomousRoutines.length; i++)
            threadManager.createGroup( autonomousRoutines[i] = "autonomousRoutine" + i );
        
        
        System.out.println("Constructing bot...");
        //damn   we need to figure out why the main executable (isolate) is exiting prematurley. i suspect an uncaught exception
        // but where could that be in EntryPoint this   is the question   
    }
    
    /**
     * 
     */
    public void run(){
        boolean error = false;
        
        System.out.println("Starting robot manager");
        
        int autonomousRoutine = 1;
        boolean autonomous;
        boolean teleoperated;
        boolean fms;
        boolean disabled; // why did you create a boolean table?? // so instead of writing out this crap.. twice, we just check it once at the begining of the loop; makes the code more consice
        
        try{
            while(true){
                autonomousRoutine = poller.getRoutine();
                if(autonomousRoutine > autonomousRoutines.length || autonomousRoutine < 1) autonomousRoutine = 1; //incase user on dashboard attempts something fishy..
                
                disabled = driverStation.isDisabled();              //
                autonomous = driverStation.isAutonomous();          // so these guys report the state of the bot upon entering of the loop and apply for this iteration     
                teleoperated = driverStation.isOperatorControl();   
                fms = driverStation.isFMSAttached();                // Field Management System,
                //e its not really significant; becasue what does are robot do differently at the field? reset itself reset? just kidding :)
                
                //*********************** over here we should have a function that somehow returns which routine we want; i suggest another module that constantly poles the joystick..
                //                        we have a lot of unused buttons on there, we can have a system to select routine based on button press
                
                if(disabled){
                    while(driverStation.isDisabled())
                        AvianceThreadHousekeeping(sleepTime);
                }
                
                else if (autonomous){
                    threadManager.startThreads(autonomousRoutines[autonomousRoutine]);
                    
                    while(driverStation.isEnabled())
                        AvianceThreadHousekeeping(sleepTime);
                    threadManager.interruptThreads(autonomousRoutines[autonomousRoutine]); //much cleaner ill be back in a few, okay
                    
                }
                
                else if (teleoperated){
                    threadManager.startThreads(teleopThreads);
                    
                    while(driverStation.isEnabled())
                        AvianceThreadHousekeeping(sleepTime);
                    threadManager.interruptThreads(teleopThreads);
                }
                //throw new Exception();// why throw new exception, to test if new isolate was created
            }
        }
        catch(AvianceThreadInterruptedException e){
            System.out.println("Robot has completed all operations [interrupt() was invoked]"
                    + "and the robot now terminates. The details of the invoke are as follows (The following is NOT a bug)");
            e.printStackTrace();
            System.out.println("--------------------------------------------- "
            + "Interrupt invoked ^^---------------------------------------------");
        }
        catch(Throwable t){
            t.printStackTrace();
            error = true;
        }
        finally{ // worse,case senario this should be printing....
            if (error) System.out.println("OKAY, so something went wrong [unhandled exception]... hopefully the output up there is useful ^_^");
            System.out.println("As a reminder, the AvianceRobot only stops continious, autonomous and teleop threads.. \n"
                    + "All other threads not part of the system, teleop or autonomous groups are left running and are responsible for manually checking the state of the robot\n"
                    + "System threads are NOT interrupted");
            /*
             * at this point stop all teleop and autonomous threads as part of the clean up process
             */
            threadManager.interruptThreads(autonomousRoutines[autonomousRoutine]);
            threadManager.interruptThreads(teleopThreads);
            threadManager.interruptThreads(continiousThreads);
            
            
             // and over here is where we launch another Isolate program that is very simple and guaranteed to work in worse case senario
        
            Isolate failsafe = new Isolate("edu.qs.frc.FailSafeRobot", new String[0], null, Isolate.currentIsolate().getParentSuiteSourceURI());
            failsafe.start();
            failsafe.join();
            
        }
    }
    
}
