/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climb extends SubsystemBase {
  // public CANSparkMax spark1;
  // public CANEncoder encoderSpark1;
  // public boolean allowClimb;
  public CANSparkMax retract;
  public CANEncoder retractEncoder;
  public WPI_TalonSRX climber;
  // public Pneumatics retractPneum;
  public static Pneumatics climberPneum;
  /**
   * Creates a new Climb.
   */
  public Climb() 
  {
    // spark1 = new CANSparkMax(Constants.CCAN, MotorType.kBrushless);
    // encoderSpark1 = spark1.getEncoder();
    // spark1.setIdleMode(IdleMode.kBrake); 
    // allowClimb = false;
    retract = new CANSparkMax(Constants.CCAN, MotorType.kBrushless);
    retractEncoder = retract.getEncoder();
    climber = new WPI_TalonSRX(Constants.CLIMBTAL);
    climberPneum = new Pneumatics(4, 5, 0.03);
  }
  //Makes motor spin one way
  public void climbUp(double speed)
  {
    // spark1.set(speed);
    // retractPneum.SetA();

    climber.set(ControlMode.PercentOutput, -speed);
  }
  //Makes motor spin down
  public void climbDown(double speed)
  {
    // retractPneum.SetB();

    // spark1.set(-speed);
    retract.set(-speed);
  }
  public double currentOut()
  {
    return climber.getStatorCurrent(); // output
   // climber.getSupplyCurrent(); // input 
  }
  public double currentIn()
  {
    return climber.getSupplyCurrent();
  }
  
  


  @Override
  public void periodic() {
    // // This method will be called once per scheduler run
    // SmartDashboard.putNumber("putPosition",encoderSpark1.getPosition());
    SmartDashboard.putNumber("Out", currentOut());
    SmartDashboard.putNumber("In", currentIn());
    SmartDashboard.putNumber("Encoder", retractEncoder.getPosition());
  }
}