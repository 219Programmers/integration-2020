/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.CamMode;
import frc.robot.commands.Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.SwitchLED;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LimeVisionSubsystem;
import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static Joystick mainDriver;
  public Button two;
  public Button three;
  public Button four;
  public JoystickButton lmove;
  public JoystickButton rmove;
  public static DriveTrain m_driveTrain;
  public static LimeVisionSubsystem limeSub;
  public static SwitchLED switchLight;
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
   {
    // Configure the button bindings
    limeSub = new LimeVisionSubsystem();
    m_driveTrain = new DriveTrain();
    switchLight = new SwitchLED();
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() 
  {
    mainDriver = new Joystick(0);
    three = new JoystickButton(mainDriver, 1);
    two = new JoystickButton(mainDriver, 2);
    lmove = new JoystickButton(mainDriver, 1);
    rmove = new JoystickButton(mainDriver, 5);
    four = new JoystickButton(mainDriver, 4);

    three.toggleWhenPressed(new SwitchLED());
    two.toggleWhenPressed(new CamMode());
    m_driveTrain.setDefaultCommand(new Drive());
    
  }
	/**
	 * Getter for the x-axis of the left joystick
	 * @return The x-axis on the left joystick which is used for the speed of the right motors on tank drive
	 */
  public static double getLeftSpeed() 
  {
    if (Math.abs(mainDriver.getRawAxis(1)) >= .2) 
    {
      return (mainDriver.getRawAxis(1));
		}
		return 0.0;
	}
	/**
	 * Getter for the x-axis of the right joystick
	 * @return The x-axis on the right joystick which is used for the speed of the right motors on tank drive
	 */
	public static double getRightSpeed() 
	{
		if(Math.abs(mainDriver.getRawAxis(5)) >= .2)
		{
			return (mainDriver.getRawAxis(5));
		}
		return 0.0;
	}
	

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    return m_autoCommand;
  }

}
