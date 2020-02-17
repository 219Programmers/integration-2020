/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// creates drivetrain for treads, will not be used with the actual robot this year, blah blah
public class Motor extends SubsystemBase {
  public static WPI_TalonSRX left = new WPI_TalonSRX(Constants.FRONTLEFT);
  public static WPI_TalonSRX leftTwo = new WPI_TalonSRX(Constants.BACKLEFT);
  public static WPI_TalonSRX right = new WPI_TalonSRX(Constants.FRONTRIGHT);
  public static WPI_TalonSRX rightTwo = new WPI_TalonSRX(Constants.BACKRIGHT);
  /**
   * Creates a new Motor.
   */
  public Motor() {

  }

  //Moves the robot back
  public static void moveBack(double speed) 
  {
    left.set(ControlMode.PercentOutput, speed); 
    leftTwo.set(ControlMode.PercentOutput, speed);
    right.set(ControlMode.PercentOutput, -speed);
    rightTwo.set(ControlMode.PercentOutput, -speed);
  }

  //Moves the robot forward
  public static void moveForward(double speed)
  {
    left.set(ControlMode.PercentOutput, -speed);
    leftTwo.set(ControlMode.PercentOutput, -speed);
    right.set(ControlMode.PercentOutput, speed);
    rightTwo.set(ControlMode.PercentOutput, speed);
  }

  //moves the robot forward at select speeds
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
// makes all the motors stop so the robot stops
  public static void stop() {
    left.set(ControlMode.PercentOutput, 0);
    leftTwo.set(ControlMode.PercentOutput, 0);
    right.set(ControlMode.PercentOutput, 0);
    rightTwo.set(ControlMode.PercentOutput, 0);
  }
}
