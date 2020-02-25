/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class FeedShooter extends CommandBase {
  /**
   * Creates a new FeedShooter.
   */
  public int numbPCells;
  public double speed;
  public boolean direction;
  public FeedShooter(int pCells , boolean direc) {
    addRequirements(RobotContainer.gibShoot);
    numbPCells = pCells;
    speed = .3;
    direction = direc;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //if lazer triggered, numbPCells--;
    if(direction)
      RobotContainer.gibShoot.runIndCor();
    else
      RobotContainer.gibShoot.runIndCor();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.gibShoot.stopIndCor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(numbPCells<=0)
      return true;
    return false;
  }
}
