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
import frc.robot.Robot;

public class AutonLime extends CommandBase 
{
  /**
   * Creates a new AutonLime.
   */

  public boolean stop = false; 
  public static boolean disableDrive = false;
  public int count = 0;
  public AutonLime() 
  {
    addRequirements(RobotContainer.limeSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    disableDrive = true;
    RobotContainer.limeSub.setPipeline(2);
    Robot.cameraSelection.setString("limelight");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  { 

    SmartDashboard.putString("Run?", "yes");
    //checks if there is a valid target and turns to the right if there isn't one
    if(Robot.target == 0)
    {
      RobotContainer.m_driveTrain.regDrive(0.3, 0.3);
    }
    else //when it finds the target
    {
      SmartDashboard.putNumber("counter2", count);
      SmartDashboard.putNumber("X Auto", Robot.x);
      //adjusts so it isn't further left than -4 on the screen 
      if(Robot.x < -7.2)
      {
        RobotContainer.m_driveTrain.regDrive(-0.5, 0.5);
        SmartDashboard.putNumber("X Auto22", Robot.x);
      }
      //adjusts so it isn't further right than 4 on the screen
      else if(Robot.x > -6.5)
      {
        RobotContainer.m_driveTrain.regDrive(0.5, -0.5);
        SmartDashboard.putNumber("x Auto7777", Robot.x);
      }
      else //drives straight if the x value is between -4 and 4
      {
       // RobotContainer.m_driveTrain.regDrive(0.3, 0.3);
        stop = true;
        SmartDashboard.putString("Shoot", "ready");
      }
    }

    //checks the vertical height, if it is the very top(its close) then it stops at that position on the screen
    // if(Robot.y > 15)
    // {
    //   RobotContainer.m_driveTrain.regDrive(0, 0);
    //   RobotContainer.sPID.setPIDVal(300);
    // }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    stop = false;
   disableDrive = false;
   RobotContainer.m_driveTrain.regDrive(0,0);
   RobotContainer.limeSub.setPipeline(1);
   Robot.cameraSelection.setString(Robot.frontCam.getName());

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return stop;
  }
}
