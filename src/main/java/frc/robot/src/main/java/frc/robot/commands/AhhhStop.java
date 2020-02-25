/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class AhhhStop extends CommandBase {

  /**
   * Creates a new AhhhStop.
   */
  public AhhhStop() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_harvester);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(RobotContainer.go)
    {
    RobotContainer.m_harvester.stopHarvest();
    }
    else{
      RobotContainer.m_harvester.harvest(SmartDashboard.getNumber("Index Speed", 0.3));
    }
    RobotContainer.go = !RobotContainer.go;
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
