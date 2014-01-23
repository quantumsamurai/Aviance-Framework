/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.hardware;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author admin
 */
public class Joystick1Simplify {

    public static boolean getA(){
   
    return Hardware.joystick1.getRawButton(2);}
    public static boolean getB(){
    return Hardware.joystick1.getRawButton(3);}
    public static boolean getY(){
    return Hardware.joystick1.getRawButton(4);
    }
    public static boolean getX(){
    return Hardware.joystick1.getRawButton(1);
    }
    public static boolean getLeftTriggerButton(){
    return Hardware.joystick1.getRawButton(7);
    }
        public static boolean getRightTriggerButton(){
    return Hardware.joystick1.getRawButton(8);
        }
            public static boolean getLeftBackButton(){
    return Hardware.joystick1.getRawButton(5);
            }
                public static boolean getRightBackButton(){
    return Hardware.joystick1.getRawButton(6);
                }
        public static boolean getStartButton(){
    return Hardware.joystick1.getRawButton(10);}
            public static boolean getBackButton(){
    return Hardware.joystick1.getRawButton(9);}
            
            public static double DPadXAxis(){
            return Hardware.joystick1.getRawAxis(5);
            
            }
            public static double DPadYAxis(){
            return Hardware.joystick1.getRawAxis(6);
            }
               public static double LeftJoystickXAxis(){
            return Hardware.joystick1.getRawAxis(1);
            }
            public static double LeftJoystickYAxis(){
            return Hardware.joystick1.getRawAxis(2);
            }
                        public static double RightJoystickXAxis(){
            return Hardware.joystick1.getRawAxis(3);
            }
                public static double RightJoystickYAxis(){
            return Hardware.joystick1.getRawAxis(4);
            }

                
}
