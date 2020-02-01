/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class NavX extends SubsystemBase {

  //This is the navX
  public AHRS ahrs = new AHRS(Constants.AHRSPort);

  /**
   * Creates a new NavX.
   */
  public NavX() {
    ahrs.enableLogging(true);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Yaw", ahrs.getYaw());
    SmartDashboard.putNumber("Roll", ahrs.getRoll());
  }

  
}
