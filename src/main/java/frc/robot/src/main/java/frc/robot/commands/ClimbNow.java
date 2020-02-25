/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ClimbNow extends CommandBase {
  boolean direction;
  double speed;
  double turnAmount;
  /**
   * Creates a new ClimbNow.
   */
  public ClimbNow(final boolean upDown, double climbSpeed) {
    addRequirements(RobotContainer.mongClimb);
    direction = upDown;
    speed = SmartDashboard.getNumber("mongSpeed", .3);
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    turnAmount = SmartDashboard.getNumber("turn", 15);
    speed = SmartDashboard.getNumber("mongSpeed", .3);
    if(direction)
    {
      RobotContainer.mongClimb.encoderSpark1.setPosition(0);
    }
    else
    {
      RobotContainer.mongClimb.encoderSpark1.setPosition(turnAmount);
    }
   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if(!direction)
    // {
    //   if(RobotContainer.mongClimb.encoderSpark1.getPosition()>0)
    //   {
    //     RobotContainer.mongClimb.climbDown(speed);
    //   }
    // }
    // else 
    // {
    //   if(RobotContainer.mongClimb.encoderSpark1.getPosition()<turnAmount)
    //   {
    //     RobotContainer.mongClimb.climbUp(speed);
    //   }
    // }
    if(direction)
      RobotContainer.mongClimb.climbUp(speed);
    else
      RobotContainer.mongClimb.climbDown(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    RobotContainer.mongClimb.climbUp(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // if(!direction&&RobotContainer.mongClimb.encoderSpark1.getPosition()>0)
    //   return false;
    // if(direction&&RobotContainer.mongClimb.encoderSpark1.getPosition()<turnAmount)
    //   return false;
    // return true;
    return false;
  }
}
