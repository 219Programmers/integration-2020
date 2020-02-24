/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.Motor;

public class ColorWheel extends CommandBase {

  public Color lastColor;
  public int colorChanges;

  /**
   * Creates a new ColorWheel.
   */
  public ColorWheel() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_robotContainer.m_mot, Robot.m_robotContainer.m_cs);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lastColor = Robot.m_robotContainer.m_cs.getClosest();
    colorChanges = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Color Count", colorChanges);
    final Color result = Robot.m_robotContainer.m_cs.getClosest();
    if (lastColor != result) {
      lastColor = result;
      colorChanges++;
    }
    Motor.moveForward(colorChanges<20?0.5:0.25);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    Motor.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return colorChanges>=24;
  }
}
