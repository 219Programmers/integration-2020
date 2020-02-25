/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class RevCor extends CommandBase {
  /**
   * Creates a new RevCor.
   */

   public static boolean isReverse;
   private boolean notReverse;
  public RevCor(boolean notReverse) {
    // Use addRequirements() here to declare subsystem dependencies.
  //  addRequirements(RobotContainer.gibShoot);
    this.notReverse = notReverse;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(notReverse)
    {
      RobotContainer.gibShoot.runCor();
    }
    else
    {
    RobotContainer.gibShoot.revCor();
    }
    isReverse=true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.gibShoot.stopCor();
    isReverse=false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
