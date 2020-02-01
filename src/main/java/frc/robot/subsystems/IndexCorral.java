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
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class IndexCorral extends SubsystemBase {
  /**
   * Creates a new IndexCorral.
   */
  public static final TalonSRX beltMoto = new TalonSRX(Constants.BELT);

  public static int ballAmount;
  
  public IndexCorral() {
    
  }

  public void runIndCor() // run corral and indexer system
  {
    beltMoto.set(ControlMode.PercentOutput, Constants.BELTSPEED);
    
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
    beltMoto.set(ControlMode.PercentOutput, 0);  
  }

  public void runCor() // rrun just corral
  {
    beltMoto.set(ControlMode.PercentOutput, Constants.BELTSPEED);
  }
  public void stopIndCor()
  {
    beltMoto.set(ControlMode.PercentOutput, 0);
  }




  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
