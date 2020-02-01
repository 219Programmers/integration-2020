/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DriveStraight extends CommandBase {

  public Timer time;
  public double length;
  public double direction;
  /**
   * Creates a new DriveStraight.
   */
  public DriveStraight(double t, double direction) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_mot, RobotContainer.m_navx);
    time = new Timer();
    length = t;
    this.direction = direction;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double lSpeed = SmartDashboard.getNumber("Speed", 0.3);
    double rSpeed = SmartDashboard.getNumber("Speed", 0.3);
    if(RobotContainer.m_navx.ahrs.getYaw()<direction)
    {
    lSpeed+=-0.05*(RobotContainer.m_navx.ahrs.getYaw()-direction);
    }
    else{
    rSpeed+=0.05*(RobotContainer.m_navx.ahrs.getYaw()-direction);
    }
    RobotContainer.m_mot.moveForward(lSpeed, rSpeed); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_mot.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return time.get()>length;
  }
}
