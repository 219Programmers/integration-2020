/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LinearA extends SubsystemBase {

  PWM a;
  /**
   * Creates a new LinearA.
   */
  public LinearA() {
    a = new PWM(0);
    a.setSpeed(1);
  }

  public void open()
  {
    a.setPosition(1);

  }

  public void close()
  {
    a.setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
