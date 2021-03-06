/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qs.frc.modules;

import edu.qs.frc.hardware.Hardware;
import edu.qs.frc.hardware.Joystick1Simplify;
import edu.qs.frc.hardware.Joystick2Simplify;
import edu.qs.frc.threading.AvianceThread;
import edu.qs.frc.threading.AvianceThreadManager;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author admin
 */
public class AvianceHybridDrive extends AvianceThread{
    private double boost;
    public AvianceHybridDrive(){
        AvianceThreadManager.getInstance().addThread(AvianceRobot.teleopThreads, this);
    }
   public static Talon drive_left = new Talon(Hardware.talon_front_left);
   public static Talon drive_right = new Talon(Hardware.talon_front_right);
       Talon shooter = new Talon(4);
         DoubleSolenoid actuators = new DoubleSolenoid(1,2);
       Talon arm = new Talon(3);

  DigitalInput pressure = new DigitalInput(5);
  Relay comp = new Relay(1);
       double leftspeed;
    double rightspeed;
    double DPadXValue;
    double DPadYValue;
    double RightJoystick;
    double LeftJoystick;

    protected void iteration(){
        
        RightJoystick = -Joystick1Simplify.RightJoystickYAxis();
        LeftJoystick = -Joystick1Simplify.LeftJoystickYAxis();
        if(Joystick1Simplify.DPadXAxis() > 0){
         DPadXValue +=.1;
        }else if(Joystick1Simplify.DPadXAxis() < 0){DPadXValue -=.1;}
        else{DPadXValue = 0;}
        
        if(Joystick1Simplify.getRightTriggerButton()){boost = .25;}else{ boost = 0;}
        
        
                if(Joystick1Simplify.DPadYAxis() > 0){
         DPadYValue +=.05;
        }else if(Joystick1Simplify.DPadYAxis() < 0){DPadYValue -=.05;}
        else{DPadYValue = 0;}
        limit(DPadXValue);
        limit(DPadYValue);
        
        leftspeed = DPadYValue -DPadXValue + LeftJoystick * .75 + boost;
    rightspeed = DPadYValue + DPadXValue + RightJoystick * .75 + boost;
            if(Joystick1Simplify.getX()){Hardware.gyro.reset();}
    System.out.println("Angle" + Hardware.gyro.getAngle());
    drive_left.set(leftspeed);
    drive_right.set(-rightspeed);
    
    
      if(Hardware.joystick1.getRawButton(2)){//kick
          System.out.println("LOOP Entered");
        shooter.set(.4);
        }
    else if(Hardware.joystick1.getRawButton(3)){
         shooter.set(1);}
    else if(Hardware.joystick1.getRawButton(1)){shooter.set(-.4);
      }
    else{shooter.set(0);}
      
      
      
      defense();
      comp();
    }
    protected void reset(){}

    private double limit(double speed) {
        if(Math.abs(speed) > 1){ speed = 1;}
        else{speed = speed;}
        return speed;
    }
   
    private void defense(){
   if(Hardware.toggleJoystick1(4)){comp.set(Relay.Value.kOff);
    }else{
        comp.set(Relay.Value.kOn);
    comp.setDirection(Relay.Direction.kForward);
    }
    
      if(Joystick2Simplify.getB()){//expand actuator
       actuators.set(DoubleSolenoid.Value.kForward);
   }
   else if(Joystick2Simplify.getX()){
      actuators.set(DoubleSolenoid.Value.kReverse);
   //retract
   }    
   else{ actuators.set(DoubleSolenoid.Value.kOff);
      }
  
    
    }
    
    private void comp(){
      if(Joystick1Simplify.getLeftTriggerButton()){
    arm.set(1);} // intake
    
      else if(Joystick1Simplify.getRightTriggerButton()){arm.set(-1);
      }// extract
      else{arm.set(0);
      }
    }
}


