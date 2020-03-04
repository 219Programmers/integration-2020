/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Climbre extends CommandBase {
  /**
   * Creates a new Climbre.
   */
  private boolean up;
  
  public Climbre(boolean up) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.up = up;
    RobotContainer.mongClimb.climber.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative); //Set the feedback device that is hooked up to the talon
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(up)
    {
      RobotContainer.mongClimb.climber.set(ControlMode.Position, 5000); //need change :)
    }
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
