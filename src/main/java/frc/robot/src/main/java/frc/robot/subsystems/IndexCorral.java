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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class IndexCorral extends SubsystemBase {
  /**
   * Creates a new IndexCorral.
   */
  public static final CANSparkMax beltMoto = new CANSparkMax(Constants.BELT, MotorType.kBrushless);
 
  public static int ballAmount = 5;
  
  public IndexCorral() {
    SmartDashboard.putNumber("Ball Amount", 5);
  }

  public void runIndCor() // run corral and indexer system
  {
    beltMoto.set(Constants.BELTSPEED);
    
    ballAmount = 0;
  }

  public void runInd() // run just indexer
  {
  
  }

  public void stopInd() // run just indexer
  {
    
  }

  public void stopCor() // run just indexer
  {
    beltMoto.set( 0);  
  }

  public void runCor() // rrun just corral
  {
    beltMoto.set(-Constants.BELTSPEED);
  }

  public void revCor() // rrun just corral
  {
    beltMoto.set(Constants.BELTSPEED);
  }
  public void stopIndCor()
  {
    beltMoto.set( 0);
  }




  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    ballAmount = (int) SmartDashboard.getNumber("Ball Amount", 5);
  }
}
