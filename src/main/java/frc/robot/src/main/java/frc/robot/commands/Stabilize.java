/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Stabilize extends CommandBase {
  /**
   * Creates a new Stabilize.
   */
  public Stabilize() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_navx);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double rickRoll = RobotContainer.m_navx.ahrs.getRoll();
    double moveAmount = 0;
    if(rickRoll<0)
    {
      //MoveRight
      moveAmount+=0.01*RobotContainer.m_navx.ahrs.getRoll();
    }
    else
    {
      //MoveLeft
      moveAmount+=0.01*RobotContainer.m_navx.ahrs.getRoll();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
