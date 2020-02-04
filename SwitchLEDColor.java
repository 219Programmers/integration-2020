/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// flips the LED strip colors through all of the options because I don't know what colors we want yet so that's fun
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class SwitchLEDColor extends CommandBase 
{
  /**
   * Creates a new SwitchLEDColor.
   */
  public SwitchLEDColor() 
  {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.blinkblink);
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
    if(RobotContainer.blinkblink.getColor() < 1)
    {
      RobotContainer.blinkblink.setColor(RobotContainer.blinkblink.getColor() + 0.02);
    }
    else{
      RobotContainer.blinkblink.setColor(-0.98); //set back to the first color
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
