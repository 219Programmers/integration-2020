/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Pneumatics;

public class UsePneum extends CommandBase {
  /**
   * @param port PCM port wired to open/A side of valve. Close/B side is wired to
   *             PCM next port. Assumes PCM CAN Id 0.
   */
  Pneumatics pneum;
  public UsePneum() {
    addRequirements(RobotContainer.pneum);
    pneum = RobotContainer.pneum;

    }

    public UsePneum(Pneumatics a) {
      addRequirements(a);
      pneum = a;

      }
    // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
     return false;
     
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    
    pneum.SetA();
 
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interup) {
   
  }
}
