/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class IndexCorral extends SubsystemBase {
  /**
   * Creates a new IndexCorral.
   */
  public static final WPI_TalonSRX beltMoto = new WPI_TalonSRX(Constants.BELT);

  // will be utilizing this once the sensors come in on 2/6
  public static int ballAmount;
  
  public IndexCorral() {
    
  }

  public void runIndCor() // run corral and indexer system
  {
    beltMoto.set(ControlMode.PercentOutput, Constants.BELTSPEED);
    
    ballAmount = 0;
  }

  public void stopCor() // stopping the corral motor
  {
    beltMoto.set(ControlMode.PercentOutput, 0);  
  }

  public void runCor() // running the corral motor
  {
    beltMoto.set(ControlMode.PercentOutput, Constants.BELTSPEED);
  }





  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
