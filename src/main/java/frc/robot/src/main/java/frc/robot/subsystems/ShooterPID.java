/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;



public class ShooterPID extends SubsystemBase {

  private CANSparkMax m_motorLeft, m_motorRight;
  private CANPIDController m_pidController, m_pidRight;
  private CANEncoder m_encoderLeft, m_encoderRight;
  public double kP, kI, kD,kIz, kMaxOutput, kMinOutput, setPoint;
  /**
   * Creates a new ShooterPID.
   */
  public ShooterPID() {
    m_motorLeft = new CANSparkMax(Constants.SHOOTER, MotorType.kBrushless);
    m_motorRight = new CANSparkMax(Constants.SHOOTER2, MotorType.kBrushless);
    m_motorLeft.restoreFactoryDefaults();
    m_motorRight.restoreFactoryDefaults();
    m_pidController = m_motorLeft.getPIDController();
    m_pidRight = m_motorRight.getPIDController();
    m_encoderRight = m_motorRight.getEncoder();
    m_encoderLeft = m_motorLeft.getEncoder();


    // these values were tested and found to be currently the best ones that don't explode the motor.
    kP = 4e-4; 
    kI = 1e-6;
    kD = 1e-6;
    kIz = 0; 
    kMaxOutput = 1; 
    kMinOutput = -1;
    

    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);
  

    m_pidRight.setP(kP);
    m_pidRight.setI(kI);
    m_pidRight.setD(kD);
    m_pidRight.setIZone(kIz);
    m_pidRight.setOutputRange(kMinOutput, kMaxOutput);
    

    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);

  }
// send the motors a reference point (revs per minute), and the PID will correct the motors back to that reference point if need be
  public void setPIDVal(double setRef)
  {
    // set point will set from the command if wanted for high or low shots (when testing is done)
    setPoint = setRef;
    m_pidController.setReference(-setPoint, ControlType.kDutyCycle);
    m_pidRight.setReference(setPoint, ControlType.kDutyCycle);
    SmartDashboard.putNumber("Set Point", setPoint);


    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);
    double set = SmartDashboard.getNumber("Set Point", 0.1);
    // these if statements are in case we change the values from smartdashboard.
    if((p != kP)) 
    { 
      m_pidController.setP(p); 
      m_pidRight.setP(p);
      kP = p; 
    }
    if((i != kI)) 
    { 
      m_pidController.setI(i); 
      m_pidRight.setI(i);
      kI = i; 
    }
    if((d != kD)) 
    {
       m_pidController.setD(d); 
       m_pidRight.setD(d);
       kD = d; 
    }
    if((iz != kIz)) 
    { 
      m_pidController.setIZone(iz); 
      m_pidRight.setIZone(iz);
      kIz = iz; 
    }
    if((max != kMaxOutput) || (min != kMinOutput)) 
    { 
      m_pidController.setOutputRange(min, max); 
      m_pidRight.setOutputRange(min, max);
      kMinOutput = min; 
      kMaxOutput = max; 
    }
    if(set != setPoint)
    {
      setPoint = set;
      m_pidController.setReference(-setPoint, ControlType.kDutyCycle);
      m_pidRight.setReference(setPoint, ControlType.kDutyCycle);
    }
    SmartDashboard.putNumber("ProcessVariable: Left", m_encoderLeft.getVelocity());

  }
// will stop the PID< but won't stop the setpoint from going on so the motors will continue going
  public void stopPID(double rpm)
  {
    m_pidRight.setP(0);
    m_pidRight.setI(0);
    m_pidRight.setD(0);
    m_pidController.setI(0);
    m_pidController.setP(0);
    m_pidController.setI(0);

    setPoint = rpm;
    m_pidController.setReference(setPoint, ControlType.kDutyCycle);
    m_pidRight.setReference(-setPoint, ControlType.kDutyCycle);
  


  }
// shuts the motors off
  public void stopMotors()
  {

    m_pidRight.setP(0);
    m_pidRight.setI(0);
    m_pidRight.setD(0);
    m_pidController.setI(0);
    m_pidController.setP(0);
    m_pidController.setI(0);
    m_motorLeft.set(0);
    m_motorRight.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   // setPIDVal();
  // m_motorLeft.set(SmartDashboard.getNumber("SpeedF", 0.2));
   //m_motorRight.set(-SmartDashboard.getNumber("SpeedF", 0.2));
  }
}

