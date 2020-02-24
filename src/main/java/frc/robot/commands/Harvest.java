/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IndexCorral;

public class Harvest extends CommandBase {
  /**
   * Creates a new Harvest.
   */
  public Harvest() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_harvester);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean scanned = false;
    if(IndexCorral.ballAmount<5 && !(IndexCorral.ballAmount == 4 && scanned))
    {
      RobotContainer.m_harvester.harvest(0.5);
    }
    else
    {
      if(IndexCorral.ballAmount == 4 && scanned)
      {
        IndexCorral.ballAmount++;
      }
      RobotContainer.m_harvester.stopHarvest();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_harvester.stopHarvest();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
