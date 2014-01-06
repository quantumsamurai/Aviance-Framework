/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadInterruptedException;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Darvin
 * 
 * AvianceRobot class adds this in manually, this class is NOT responsible for managing its threadgroup membership
 */
public class AutonomousRoutinePoller extends AvianceThread{
    
    private Joystick joy = Hardware.joystick2; // the hardware class will be responsible for swapping joys
    private final Object intLock = new Object();
    private int routine = 1;
    
    int value = 1;
    final int[] buttons = {1,2,3,4,5}; //these are the raw button codes for each routine
    
    {
        SmartDashboard.putNumber("Autonomous Routine", value);
    }
    
    public int getRoutine(){
        synchronized(intLock){
            return routine;
        }
    }
    
    //just look at how much nicer and more concise this is
    protected void iteration(){
        
        
                try{
            System.out.println("Poller on!");
            
            while(true){
                
                for(int i = 0; i < buttons.length; i++){
                    if(joy.getRawButton(buttons[i])) //if the button in a particular slot is pressed
                        value = i + 1;  //then the routine is set to that slot + 1 (since routines count from 1 not 0) ok
                }
                
                synchronized(intLock) {
                     
                    if (value != routine) System.out.println("Autonomous Routine changed: " + value);
                     SmartDashboard.putNumber("Autonomous Routine", value);
                    routine = value;
                }
                
                this.AvianceThreadHousekeeping(sleepTime);
            }
        }
        catch(AvianceThreadInterruptedException ex){
            interrupted = false; //every module needs this in its catch clause for interruted exception otherwise the thread will NOT start again
        }
    }
        
//        for(int i = 0; i < buttons.length; i++)
//            if(joy.getRawButton(buttons[i])) //if the button in a particular slot is pressed
//                value = i + 1;  //then the routine is set to that slot + 1 (since routines count from 1 not 0) ok
//                
//        synchronized(intLock) {
//            value = (int) SmartDashboard.getNumber("Autonomous Routine");
//            if (value != routine){ System.out.println("Autonomous Routine changed: " + value);
//                SmartDashboard.putNumber("Autonomous Routine", value);
//            }
//            routine = value;
//            
//        }
 //   }
    
    
//   
//    public void run(){

    
}
