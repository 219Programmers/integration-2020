/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class SwitchLimeLine extends CommandBase 
{
  /**
   * Creates a new SwitchLimeLine.
   */
  public SwitchLimeLine() 
  {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.limeSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    // switches the pipeline so it zooms or doens't zoom based on if it zoomed or it wasn't zoomed before. 
    if(RobotContainer.limeSub.getPipeline() == 0)
    {
      RobotContainer.limeSub.setPipeline(1);
    }
    else if (RobotContainer.limeSub.getPipeline() == 1)
    {
      RobotContainer.limeSub.setPipeline(0);
    }
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return true;
  }
}
