/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

//import frc.robot.subsystems.LimeVisionSubsystem;



import edu.wpi.first.wpilibj.smartdashboard.*;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static RobotContainer m_robotContainer;

  public static Command autoLine;
  public double leftSpeed;
  public double rightSpeed;
  public static double target;
  public static double x;
  public static double y;
  public static double area;
  public static double pipeline;

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  
   // SmartDashboard.putBoolean("Safety", safe.isSafetyEnabled());
   
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tv = table.getEntry("tv");


     //read values periodically
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
    target = tv.getDouble(0.0);


   // post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putNumber("Target ", target);

    CommandScheduler.getInstance().run();
   
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
   /* m_autonomousCommand = m_robotContainer.getAutonomousCommand();
   // autoLine = m_robotContainer.getAutonLime();
   // autoLine.schedule();
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
*/
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

    CommandScheduler.getInstance().run();

    //checks if there is a valid target and turns to the right if there isn't one
    if(target == 0)
    {
      RobotContainer.m_driveTrain.regDrive(0.2, 0.2);
    }
    else //when it finds the target
    {
      //adjusts so it isn't further left than -4 on the screen 
      if(x < -4)
      {
        RobotContainer.m_driveTrain.regDrive(0.2, -0.5);
      }
      //adjusts so it isn't further right than 4 on the screen
      else if(x > 4)
      {
        RobotContainer.m_driveTrain.regDrive(0.5, -0.2);
      }
      else //drives straight if the x value is between -4 and 4
      {
        RobotContainer.m_driveTrain.regDrive(0.2, -0.2);
      }
    }

    //checks the vertical height, if it is the very top(its close) then it stops at that position on the screen
    if(y > 20)
    {
      RobotContainer.m_driveTrain.regDrive(0, 0);
    }
  
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
  //  autoLine.cancel();
  /*  if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    */
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic()
  {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
