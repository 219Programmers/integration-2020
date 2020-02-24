/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.LimeLineUp;

public class MakeReadyShoot extends CommandBase {

  double y;
  double heightOfLime;
  double distance;
  double angleOfLime;
  double shootDistance = 0;
  public static double whereToMove = 0;
  public static double yawOfTarget = 0;

  /**
   * Creates a new MakeReadyShoot.
   */
  public MakeReadyShoot() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.limeSub, RobotContainer.m_navx);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    y = RobotContainer.limeSub.getYOffset();
    heightOfLime = 0;
    distance = (90.69-heightOfLime) / Math.tan(y+30);
    whereToMove = shootDistance-distance;
    yawOfTarget =  RobotContainer.m_navx.getYaw(0) - angleOfLime;
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
