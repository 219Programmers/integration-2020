/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class RevCor extends CommandBase {
  /**
   * Creates a new RevCor.
   */

   public static boolean isReverse;
   private boolean notReverse;
   boolean changeSpeed;
   double seconds;
   boolean timed;
   double speed;
   Timer time = new Timer();
  public RevCor(boolean notReverse) {
    // Use addRequirements() here to declare subsystem dependencies.
  //  addRequirements(RobotContainer.gibShoot);
    this.notReverse = notReverse;
    timed = false;
    changeSpeed = false;
  }

  public RevCor(boolean notReverse, double seconds) {
    // Use addRequirements() here to declare subsystem dependencies.
  //  addRequirements(RobotContainer.gibShoot);
    this.notReverse = notReverse;
    this.seconds = seconds;
    timed = true;
    changeSpeed = false;
  }

  public RevCor(boolean notReverse, double seconds, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
  //  addRequirements(RobotContainer.gibShoot);
    this.notReverse = notReverse;
    this.seconds = seconds;
    timed = true;
    changeSpeed = true;
    this.speed = speed;
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.start();
    if(notReverse)
    {
      if(!changeSpeed)
      {
      RobotContainer.gibShoot.runCor();
      RobotContainer.m_harvester.indexMoto.set(ControlMode.PercentOutput, 0.5);
      }
      else
      {
        RobotContainer.gibShoot.runCor(speed);
        RobotContainer.m_harvester.indexMoto.set(ControlMode.PercentOutput, speed);  
      }
    }
    else
    {
      RobotContainer.gibShoot.revCor();
      RobotContainer.m_harvester.indexMoto.set(ControlMode.PercentOutput, -0.5);
    }
    isReverse=true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.gibShoot.stopCor();
    RobotContainer.m_harvester.indexMoto.set(ControlMode.PercentOutput, 0);
    isReverse=false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timed && time.get() > seconds;
  }
}
