/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qs.RobotHardware;

import qs.RobotHardware.Hardware;
import qs.ThreadSetup.AvianceThread;
import qs.ThreadSetup.AvianceThreadManager;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import qs.GeneralModules.AvianceRobot;

/**
 *
 * @author Darvin
 */
public class AvianceJoystickSwapper extends AvianceThread{
    
    public AvianceJoystickSwapper(){
        AvianceThreadManager.getInstance().addThread(AvianceRobot.continiousThreads, this);
    }
    
    private boolean swapped = false;
    private double lastSwap = Timer.getFPGATimestamp();
    private double restTime = 1; // if it's double then it makes sense to be seconds 
    private int swapButton = Hardware.joystickSwapButton;
    
    protected void iteration(){// youve beeen infinitely looped, yup it's supposed to
        if((Hardware.joystick1.getRawButton(swapButton) || Hardware.joystick2.getRawButton(swapButton))
                && Timer.getFPGATimestamp() - lastSwap >= restTime){
            
            Joystick temp = Hardware.joystick1;
            Hardware.joystick1 = Hardware.joystick2;
            Hardware.joystick2 = temp;
            
            lastSwap = Timer.getFPGATimestamp();
            swapped = !swapped;
            
            System.out.println("Joystickes swapped: " + swapped);
        }
    }
}
