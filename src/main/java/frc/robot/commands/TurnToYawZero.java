/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class TurnToYawZero extends CommandBase {

  double speed;
  boolean justPositive;
  /**
   * Creates a new TurnToYawZero.
   */
  public TurnToYawZero() {
    // Use addRequirements() here to declare subsystem dependencies. 
    addRequirements(Robot.m_robotContainer.m_navx, Robot.m_robotContainer.m_mot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Robot.m_robotContainer.m_driveTrain.go = false;
    speed = 1;
    // justPositive = Robot.m_robotContainer.m_navx.ahrs.getYaw()>0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double lSpeed = 0;
    double rSpeed = 0;
    lSpeed-=0.05*RobotContainer.m_navx.ahrs.getYaw();
    rSpeed+=0.05*RobotContainer.m_navx.ahrs.getYaw();
    speed = Math.abs(lSpeed) + Math.abs(rSpeed);
    RobotContainer.m_mot.moveForward(lSpeed, rSpeed);
    // if(justPositive != Robot.m_robotContainer.m_navx.ahrs.getYaw()>0)
    // {
    //   justPositive = !justPositive;
    //   speed/=1.5;
    // }
    // if(Robot.m_robotContainer.m_navx.ahrs.getYaw()>0)
    // {
    //   RobotContainer.m_mot.left.set(ControlMode.PercentOutput, -speed);
    //   RobotContainer.m_mot.leftTwo.set(ControlMode.PercentOutput, -speed);
    //   RobotContainer.m_mot.right.set(ControlMode.PercentOutput, -speed);
    //   RobotContainer.m_mot.rightTwo.set(ControlMode.PercentOutput, -speed);
    // }
    // else
    // {
    //   RobotContainer.m_mot.left.set(ControlMode.PercentOutput, speed);
    //   RobotContainer.m_mot.leftTwo.set(ControlMode.PercentOutput, speed);
    //   RobotContainer.m_mot.right.set(ControlMode.PercentOutput, speed);
    //   RobotContainer.m_mot.rightTwo.set(ControlMode.PercentOutput, speed);
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_robotContainer.m_mot.stop();
    // Robot.m_robotContainer.m_driveTrain.go = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return (double)Math.abs(Robot.m_robotContainer.m_navx.ahrs.getYaw())<SmartDashboard.getNumber("Close Enough", 1);
    return speed<0.01;
  }
}
