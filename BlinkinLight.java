/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//you treat the LED like a spark controller
//also uses the constant BLINKIN which is 0 in this instance but is the PWM port its in genius
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BlinkinLight extends SubsystemBase 
{
  public Spark ledControl;
  /**
   * Creates a new BlinkinLight.
   */
  public BlinkinLight() 
  {
    ledControl = new Spark(Constants.BLINKIN);
  }

  public void setColor(double z)
  {
    ledControl.set(z);
  }

  public double getColor()
  {
    return ledControl.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
