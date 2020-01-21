/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.Motor;

public class FindColor extends CommandBase {

  Color goTo;

  /**
   * Creates a new FindColor.
   */
  public FindColor() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_robotContainer.m_cs, Robot.m_robotContainer.m_mot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(SmartDashboard.getString("Turn Color", "").equals("Red"))
    {
      goTo = Robot.m_robotContainer.m_cs.colors[0];
    }
    else if(SmartDashboard.getString("Turn Color", "").equals("Green"))
    {
      goTo = Robot.m_robotContainer.m_cs.colors[3];
    }
    else if(SmartDashboard.getString("Turn Color", "").equals("Yellow"))
    {
      goTo = Robot.m_robotContainer.m_cs.colors[1];
    }
    else
    {
      goTo = Robot.m_robotContainer.m_cs.colors[2];   
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Motor.moveForward(0.1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Motor.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return goTo == Robot.m_robotContainer.m_cs.getClosest();
  }
}
