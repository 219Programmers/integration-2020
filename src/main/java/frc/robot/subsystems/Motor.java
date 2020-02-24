/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Motor extends SubsystemBase {
  public static TalonSRX left = new TalonSRX(Constants.FRONTLEFT);
  public static TalonSRX leftTwo = new TalonSRX(Constants.BACKLEFT);
  public static TalonSRX right = new TalonSRX(Constants.FRONTRIGHT);
  public static TalonSRX rightTwo = new TalonSRX(Constants.BACKRIGHT);
  /**
   * Creates a new Motor.
   */
  public Motor() {

  }

  //Moves the motor back
  public static void moveBack(double speed) {
    left.set(ControlMode.PercentOutput, speed); //backleft motor
     leftTwo.set(ControlMode.PercentOutput, speed);
    right.set(ControlMode.PercentOutput, -speed);
     rightTwo.set(ControlMode.PercentOutput, -speed);
  }

  //Moves the motor foward
  public static void moveForward(double speed)
  {
    left.set(ControlMode.PercentOutput, -speed);
    leftTwo.set(ControlMode.PercentOutput, -speed);
    right.set(ControlMode.PercentOutput, speed);
    rightTwo.set(ControlMode.PercentOutput, speed);
  }

  public static void moveForward(double speedLeft, double speedRight)
  {
    left.set(ControlMode.PercentOutput, speedLeft);
    leftTwo.set(ControlMode.PercentOutput, speedLeft);
    right.set(ControlMode.PercentOutput, -speedRight);
    rightTwo.set(ControlMode.PercentOutput, -speedRight);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static void stop() {
    left.set(ControlMode.PercentOutput, 0);
    leftTwo.set(ControlMode.PercentOutput, 0);
    right.set(ControlMode.PercentOutput, 0);
    rightTwo.set(ControlMode.PercentOutput, 0);
  }
}
