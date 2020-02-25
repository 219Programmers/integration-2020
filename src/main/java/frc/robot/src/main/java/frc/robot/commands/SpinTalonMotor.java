/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TalonMotor;

public class SpinTalonMotor extends CommandBase {

  TalonMotor a;
  double s;
  /**
   * Creates a new SpinTalonMotor.
   */
  public SpinTalonMotor(TalonMotor m, double speed) {
    a = m;
    s = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    a.move(s);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    a.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
