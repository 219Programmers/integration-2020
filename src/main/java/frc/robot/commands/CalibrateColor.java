/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ColorSensor;

public class CalibrateColor extends CommandBase {
  /**
   * Creates a new CalibrateColor.
   */
  public CalibrateColor() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_robotContainer.m_cs);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_robotContainer.m_cs.calibration();
    //You can replace whats given to you with "Color Code" to use the new calibrations or they are also stored locally on the dashboard
    SmartDashboard.putString("Color Code", "{ColorMatch.makeColor(" + SmartDashboard.getNumber("BlueR", 0.13) + ", " + SmartDashboard.getNumber("BlueG", 0.42)  + ", " + SmartDashboard.getNumber("BlueB", 0.44) + "), ColorMatch.makeColor(" + SmartDashboard.getNumber("GreenR", 0.16) + ", " + SmartDashboard.getNumber("GreenG", 0.57)  + ", " + SmartDashboard.getNumber("GreenB", 0.25) + "), ColorMatch.makeColor(" + SmartDashboard.getNumber("RedR", 0.5) + ", " + SmartDashboard.getNumber("RedG", 0.35)  + ", " + SmartDashboard.getNumber("RedB", 0.13) + "), ColorMatch.makeColor(" + SmartDashboard.getNumber("YellowR", 0.31) + ", " + SmartDashboard.getNumber("YellowG", 0.55)  + ", " + SmartDashboard.getNumber("YellowB", 0.12) + ")}");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
