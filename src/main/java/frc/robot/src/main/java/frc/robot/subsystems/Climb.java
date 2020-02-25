/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climb extends SubsystemBase {
  public CANSparkMax spark1;
  public CANEncoder encoderSpark1;
  public boolean allowClimb;
  /**
   * Creates a new Climb.
   */
  public Climb() 
  {
    spark1 = new CANSparkMax(Constants.CCAN, MotorType.kBrushless);
    encoderSpark1 = spark1.getEncoder();
    spark1.setIdleMode(IdleMode.kBrake); 
    allowClimb = false;
  }
  //Makes motor spin one way
  public void climbUp(double speed)
  {
    spark1.set(speed);
  }
  //Makes motor spin down
  public void climbDown(double speed)
  {
    spark1.set(-speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("putPosition",encoderSpark1.getPosition());
  }
}