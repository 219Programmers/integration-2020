/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CamMode;
import frc.robot.commands.ClimbNow;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ScanClosestColor;
import frc.robot.commands.SetYawZero;
import frc.robot.commands.SwitchLED;
import frc.robot.commands.TurnToYawZero;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.Compression;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Harvester;
import frc.robot.subsystems.IndexCorral;
import frc.robot.subsystems.LimeVisionSubsystem;
import frc.robot.subsystems.Motor;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.SinglePneumatics;
import frc.robot.subsystems.TestMotot;
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
  public static final TestMotot m_spin = new TestMotot();
  //public static Motor m_mot = null;
// The robot's subsystems and commands are defined here...
  public static Joystick mainDriver;
  public Button two;
  public Button three;
  public Button four;
  public Button a;
  public Button b;
  public JoystickButton lmove;
  public JoystickButton rmove;
  public static DriveTrain m_driveTrain;
  public static LimeVisionSubsystem limeSub;
  public static SwitchLED switchLight;
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  public static Climb mongClimb;
  public static NavX m_navx;
  public static ColorSensor m_cs;
  public static Motor m_mot; //Commented out, might conflict with normal drive
  public static Pneumatics pneum; // new Pneumatics(Constants.PNEUM, Constants.PNEUMCLOSE, Constants.SOLSLIDETIME);
  public static Pneumatics lPneumShift;
  public static Pneumatics rPneumShift;
  public static SinglePneumatics pneumSingle; //new SinglePneumatics();
  public static Compression comp;
  public static IndexCorral gibShoot;
  public static Harvester m_harvester;
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
   {
    // Configure the button bindings
    // limeSub = new LimeVisionSubsystem();
    // // m_driveTrain = new DriveTrain();
    // switchLight = new SwitchLED();
    // mongClimb = new Climb();
    m_cs = new ColorSensor();
    m_navx = new NavX();
    m_mot = new Motor();
    // mongClimb.encoderSpark1.setPosition(0);
    SmartDashboard.putNumber("turn", 15);
    SmartDashboard.putNumber("mongSpeed", .3);
    SmartDashboard.putNumber("Speed", .5);
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
    // three = new JoystickButton(mainDriver, 1);
    // two = new JoystickButton(mainDriver, 2);
    lmove = new JoystickButton(mainDriver, 1);
    rmove = new JoystickButton(mainDriver, 5);
    four = new JoystickButton(mainDriver, 4);
    a = new JoystickButton(mainDriver, 1);
    b= new JoystickButton(mainDriver, 2);

    // three.toggleWhenPressed(new SwitchLED());
    // two.toggleWhenPressed(new CamMode());
    a.toggleWhenPressed(new ScanClosestColor());
    // a.whileHeld(new ClimbNow(true,Constants.CLIMBSPEED));
    // b.whileHeld(new ClimbNow(false,Constants.CLIMBSPEED));
    // m_driveTrain.setDefaultCommand(new Drive());

    
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
