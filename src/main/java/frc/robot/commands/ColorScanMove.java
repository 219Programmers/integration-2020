/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.Motor;

public class ColorScanMove extends CommandBase {
  /**
   * Creates a new ColorScanMove.
   */
  public ColorScanMove() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_robotContainer.m_cs, Robot.m_robotContainer.m_mot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // moves forward if it sees red FOR TESTING PURPOSES ONLY
    if(Robot.m_robotContainer.m_cs.getClosest() == Robot.m_robotContainer.m_cs.colors[2])
    {
      Motor.moveForward(0.5);
    }
    // MOVES THE ROBOT BACK IF IT SEES YELLOW, FOR TESTING PURPOSE
    else if(Robot.m_robotContainer.m_cs.getClosest() == Robot.m_robotContainer.m_cs.colors[3]){
      Motor.moveBack(0.5);
    }
    else
    {
      Motor.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Motor.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // if it saw blue it stopped it's addiction to google baseball
    return Robot.m_robotContainer.m_cs.getClosest() == Robot.m_robotContainer.m_cs.colors[0];
  }
}
