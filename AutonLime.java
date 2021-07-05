/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;

public class AutonLime extends CommandBase 
{
  /**
   * Creates a new AutonLime.
   */
  public AutonLime() 
  {
    addRequirements(RobotContainer.m_driveTrain);
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
    //checks if there is a valid target and turns to the right if there isn't one
    if(Robot.target == 0)
    {
      RobotContainer.m_driveTrain.tankDrive(0.3, 0.3);
    }
    else //when it finds the target
    {
      //adjusts so it isn't further left than -4 on the screen 
      if(Robot.x < -4)
      {
        RobotContainer.m_driveTrain.tankDrive(0.3, -0.35);
      }
      //adjusts so it isn't further right than 4 on the screen
      else if(Robot.x > 4)
      {
        RobotContainer.m_driveTrain.tankDrive(0.35, -0.3);
      }
      else //drives straight if the x value is between -4 and 4
      {
        RobotContainer.m_driveTrain.tankDrive(0.3, -0.3);
      }
    }

    //checks the vertical height, if it is the very top(its close) then it stops at that position on the screen
    if(Robot.y > 15)
    {
      RobotContainer.m_driveTrain.tankDrive(0, 0);
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
    return false;
  }
}
