/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class NavX extends SubsystemBase {

  //This is the navX
  public AHRS ahrs = new AHRS(Constants.AHRSPort);
  public double[] yawTwo = {0, 0, 0, 0, 0, 0};
  /**
   * Creates a new NavX.
   */
  public NavX() {
    ahrs.enableLogging(true);
  }
  
  public void setYawZero(int a)
  {
    yawTwo[a] = ahrs.getYaw();
  }

  public double getYaw(int a)
  {
    double x = ahrs.getYaw() - yawTwo[a];
    return lockVal(x, -180, 180);
  }

  //locks first value to inbetween other two values
  //low and high are inclusive so (-181, -180, 180) yields 180 rather than 179
  public static double lockVal(double a, double low, double high)
  {
    double x = a;
    double distance = high-low;
    while(x>high || x<low)
    {
      x = x>high?(x-high+low-1):(x+distance+1);
    }
    return x;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Yaw", ahrs.getYaw());
    SmartDashboard.putNumber("Roll", ahrs.getRoll());
  }

  
}
