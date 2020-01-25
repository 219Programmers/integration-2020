/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Pneumatics;

public class Shift extends CommandBase {
  /**
   * Creates a new Shift.
   */
  boolean shiftUp;
  public Shift(boolean typeShift) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_robotContainer.lPneumShift);
    addRequirements(Robot.m_robotContainer.rPneumShift);
    shiftUp = typeShift;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    if(shiftUp)
    {
      Robot.m_robotContainer.lPneumShift.SetA();
      Robot.m_robotContainer.rPneumShift.SetA();
    }
    else
    {
      Robot.m_robotContainer.lPneumShift.SetB();
      Robot.m_robotContainer.rPneumShift.SetB();
    }
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
