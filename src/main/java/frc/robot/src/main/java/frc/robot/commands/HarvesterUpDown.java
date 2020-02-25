/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class HarvesterUpDown extends CommandBase {
  /**
   * Creates a new HarvesterUpDown.
   */
  public HarvesterUpDown() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_harvestpneum, RobotContainer.m_harvester);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(RobotContainer.m_harvester.getUp())
    {
      RobotContainer.m_harvestpneum.SetB();
    }
    else{
      RobotContainer.m_harvestpneum.SetA();
    }
    RobotContainer.m_harvester.setUp(!RobotContainer.m_harvester.getUp());
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
    return false;
  }
}
