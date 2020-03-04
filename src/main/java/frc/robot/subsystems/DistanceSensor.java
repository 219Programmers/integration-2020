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

  Rev2mDistanceSensor a;
  boolean display;
  /**
   * Creates a new DistanceSensor.
   */
  public DistanceSensor() {
    a = new Rev2mDistanceSensor(Port.kOnboard);
    a.setAutomaticMode(true);
    go = true;
  }

  public DistanceSensor(boolean d) {
    a = new Rev2mDistanceSensor(Port.kOnboard);
    a.setAutomaticMode(true);
    display = d;
  }


  public DistanceSensor(int what) {
    if(what == 0)
    {
      a = new Rev2mDistanceSensor(Port.kMXP);
      display = false;
    }
    else
    {
      a = new Rev2mDistanceSensor(Port.kOnboard);
      display = true;
    }
    a.setAutomaticMode(true);
  }

  public boolean spotted(double x)
  {
    
    return a.getRange()<=x;
  }


  double distance = 0;
  double distanceB = 0;
  int u = 0;
  int uT = 0;
  static boolean go;
  @Override
  public void periodic() {
    //boolean didUpdate = false;
    a.setEnabled(true);
    // This method will be called once per scheduler run
    if(display)
    {
      
     // a = new Rev2mDistanceSensor(Port.kOnboard);
      //a.setAutomaticMode(true);
      
      SmartDashboard.putNumber("Inches", a.getRange());
      u++;
      SmartDashboard.putNumber("Inches u", u);
      
    }
    else
    {
      SmartDashboard.putNumber("Inches T", a.getRange());
      uT++;
      SmartDashboard.putNumber("Inches uT", uT);
      
    }
    //SmartDashboard.putBoolean("Did Update", didUpdate);
  }
}
