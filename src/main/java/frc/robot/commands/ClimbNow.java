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
    //Gets the direction depending on if the climber is up or down
    direction = upDown;
    //Gets speed from smart dashboard
    speed = climbSpeed;
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.mongClimb.climbUp(direction?speed:-speed);
    // //Goes to a specific position of the motor
    // turnAmount = SmartDashboard.getNumber("turn", 15);
    // //Gets speed from smart dashboard
    // speed = SmartDashboard.getNumber("mongSpeed", .3);
    // //If the climber is down set position to zero else set it to the turn amount
    // RobotContainer.mongClimb.encoderSpark1.setPosition(direction?0:turnAmount);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // //Depending on the direction go up or down
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
    // // if(direction)
    // //   RobotContainer.mongClimb.climbUp(speed);
    // // else
    // //   RobotContainer.mongClimb.climbDown(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    RobotContainer.mongClimb.climbUp(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
