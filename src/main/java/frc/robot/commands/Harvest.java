  
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Harvester;
import frc.robot.subsystems.IndexCorral;

public class Harvest extends CommandBase {
  boolean reverse;
  /**
   * Creates a new Harvest.
   */
  public Harvest(boolean reverse) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_harvester);
    //if its reversed this is true
    this.reverse = reverse;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //If its not reversed get right trigger and if its reversed get left trigger
    if(Harvester.isAuton)
    {
      if(Harvester.run)
      {
        RobotContainer.m_harvester.harvest(0.5);
      }
      else
      {
        RobotContainer.m_harvester.harvest(0);
      }
    }
    else if(IndexCorral.isLimited && RobotContainer.m_ds.spotted(8) && !RevCor.isReverse)
    {
      RobotContainer.m_harvester.harvest(0);
    }
    else if(RevCor.isReverse);
    else if((RobotContainer.getRightTrigger()))
    {
      reverse = false;
      RobotContainer.m_harvester.harvest(reverse? -0.5: 0.5);
      boolean scanned = false;
      // if(IndexCorral.ballAmount<5 && !(IndexCorral.ballAmount == 4 && scanned))
      // {
      //   RobotContainer.m_harvester.harvest(reverse? -0.5: 0.5);
      // }
      // else
      // {
      //   if(IndexCorral.ballAmount == 4 && scanned)
      //   {
      //     IndexCorral.ballAmount++;
      //   }
      //   RobotContainer.m_harvester.stopHarvest();
      // }
    }
    else if((RobotContainer.getLeftTrigger()))
    {
      reverse = true;
      boolean scanned = false;
      RobotContainer.m_harvester.harvest(reverse? -0.5: 0.5);
      // if(IndexCorral.ballAmount<5 && !(IndexCorral.ballAmount == 4 && scanned))
      // {
      //   RobotContainer.m_harvester.harvest(reverse? -0.5: 0.5);
      // }
      // else
      // {
      //   if(IndexCorral.ballAmount == 4 && scanned)
      //   {
      //     IndexCorral.ballAmount++;
      //   }
      //   RobotContainer.m_harvester.stopHarvest();
      // }
    }
    else
    {
      RobotContainer.m_harvester.harvest(0);
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
