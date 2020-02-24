/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.Rev2mDistanceSensor.Unit;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DistanceSensor extends SubsystemBase {

  Rev2mDistanceSensor a = new Rev2mDistanceSensor(Port.kOnboard);
  /**
   * Creates a new DistanceSensor.
   */
  public DistanceSensor() {
    a.setAutomaticMode(true);
  }

  public DistanceSensor(int what) {
    if(what==1)
    {
      a = new Rev2mDistanceSensor(Port.kMXP);
    }
    a.setAutomaticMode(true);
  }

  public boolean spotted(double x)
  {
    
    return a.getRange()<=x;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("5 Inches", a.getRange());
  }
}
