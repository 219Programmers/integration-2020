/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.Drive;

public class DriveTrain extends SubsystemBase
 {

  private TalonSRX mfr, mfl, mbr, mbl;

  public DriveTrain() 
  {
    mfr = new TalonSRX(Constants.mfr);
    mfl = new TalonSRX(Constants.mfl);
    mbr = new TalonSRX(Constants.mbr);
    mbl = new TalonSRX(Constants.mbl);
  }

  public void setTalonSpeed(double left, double right) 
  {
    mfl.set(ControlMode.PercentOutput, left);
    mbl.set(ControlMode.PercentOutput, left);
    mfr.set(ControlMode.PercentOutput, right);
    mbr.set(ControlMode.PercentOutput, right);
  }

  public void tankDrive(double left, double right)
  {
    setTalonSpeed(left, right);
  }

  public void initDefaultCommand()
  {
    setDefaultCommand(new Drive());
    //setDefaultCommand(new Sparxy());
    // TODO Auto-generated method stub
  }
  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
}
