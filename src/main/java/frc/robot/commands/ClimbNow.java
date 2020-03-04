/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climb;

public class ClimbNow extends CommandBase {
  boolean direction;
  double speed;
  double turnAmount;
  double sec;
  boolean timed;
  boolean dontClose;
  boolean encoderMove;
  Timer time = new Timer();
  /**
   * Creates a new ClimbNow.
   */
  public ClimbNow(final boolean up, double climbSpeed) {
    addRequirements(RobotContainer.mongClimb);
    direction = up;
    timed = false;
    speed = climbSpeed;
    SmartDashboard.putNumber("Climb speed", climbSpeed);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public ClimbNow(final boolean up, double climbSpeed, double sec, boolean dontClose) {
    addRequirements(RobotContainer.mongClimb);
    direction = up;
    timed = true;
    this.sec = sec;
    this.dontClose = dontClose;
    speed = climbSpeed;
    SmartDashboard.putNumber("Climb speed", climbSpeed);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public ClimbNow(final boolean up, double moveAmount, boolean dontClose, double climbSpeed) {
    addRequirements(RobotContainer.mongClimb);
    direction = up;
    timed = false;
    encoderMove = true;
    turnAmount = moveAmount;
    speed = climbSpeed;
    this.dontClose = dontClose;
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.mongClimb.climber.getSensorCollection().setQuadraturePosition(0,0);
    RobotContainer.mongClimb.retract.getEncoder().setPosition(0);
    // speed = SmartDashboard.getNumber("Climb speed", 0.5);
    Climb.climberPneum.SetB();
    try
    {
      Thread.sleep(200);
    }
    catch(Exception e){}
    pause = true;
    time.start();
  }
  boolean pause = false;
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("encoder", RobotContainer.mongClimb.climber.getSensorCollection().getQuadraturePosition());
    SmartDashboard.putNumber("ClimbUpSpeed", RobotContainer.mongClimb.climber.get());
    SmartDashboard.putNumber("ClimbDownSpeed", RobotContainer.mongClimb.retract.get());

    if(pause)
    {
      if(direction)
      {
        RobotContainer.mongClimb.climbUp(speed);
      }
      else
      {
        RobotContainer.mongClimb.climbDown(speed);
      }
  }
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    RobotContainer.mongClimb.climbUp(0);
    RobotContainer.mongClimb.climbDown(0);
    if(!dontClose)
    {
      Climb.climberPneum.SetA();
    }
    pause = false;
  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // if(!direction&&RobotContainer.mongClimb.encoderSpark1.getPosition()>0)
    //   return false;
    // if(direction&&RobotContainer.mongClimb.encoderSpark1.getPosition()<turnAmount)
    //   return false;
    // return true;
    // return direction && RobotContainer.mongClimb.currentIn() > Double.MAX_VALUE;
    return timed && time.get()>sec || encoderMove && direction && RobotContainer.mongClimb.climber.getSensorCollection().getQuadraturePosition()-200 < -turnAmount || encoderMove && !direction && RobotContainer.mongClimb.retract.getEncoder().getPosition() < -turnAmount;
  }
}
