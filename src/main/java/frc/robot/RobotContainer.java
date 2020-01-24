/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

// imported needed libraries to use to get buttons, joysticks, and put this on smartdashbaord
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj.smartdashboard.*;

import frc.robot.commands.CamMode;
import frc.robot.commands.Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.SwitchLED;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LimeVisionSubsystem;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Solenoid;

// importing our code so we can program the buttons to do certain commands
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.TestMotot;
import frc.robot.commands.*;

// simeon and james code 1/23
import frc.robot.commands.BooleanTest;
import frc.robot.commands.CalibrateColor;
import frc.robot.commands.CloseValve;
import frc.robot.commands.ColorScanMove;
import frc.robot.commands.ColorWheel;
import frc.robot.commands.CompressCom;
import frc.robot.commands.FindColor;
import frc.robot.commands.ScanClosestColor;
import frc.robot.commands.Shift;
import frc.robot.commands.SinglePneum;
import frc.robot.commands.SinglePneumClose;
import frc.robot.commands.StopCompress;
import frc.robot.commands.TurnAndStop;
import frc.robot.commands.UsePneum;
//import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.Compression;
import frc.robot.subsystems.Motor;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.SinglePneumatics;




/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  public static DriveTrain m_driveTrain;
  public static TestMotot m_spin;
  
  public static Joystick xbox;
 // public Button a;
  public Button b;
  public JoystickButton lmove;
  public JoystickButton rmove;
  public JoystickButton mistake;
  public Drive driving;
  // setDefaultCommand(new Drive());

  public static LimeVisionSubsystem limeSub;
  public static SwitchLED switchLight;

  //james and simeon code
  public JoystickButton ex;
  public JoystickButton y;
  public JoystickButton a;
  public static ColorSensor m_cs;
  public static Motor m_mot;
  public static Pneumatics pneum; // new Pneumatics(Constants.PNEUM, Constants.PNEUMCLOSE, Constants.SOLSLIDETIME);
  public static Pneumatics lPneumShift;
  public static Pneumatics rPneumShift;
  public static SinglePneumatics pneumSingle; //new SinglePneumatics();
  public static Compression comp;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_driveTrain = new DriveTrain();
    m_spin = new TestMotot();
    comp = new Compression();
    limeSub = new LimeVisionSubsystem();
    m_cs = new ColorSensor();
    m_mot = new Motor();
    
    lPneumShift = new Pneumatics(Constants.LEFTSHIFTOPEN, Constants.LEFTSHIFTCLOSE, Constants.SOLSLIDETIME);
    rPneumShift = new Pneumatics(Constants.RIGHTSHIFTOPEN, Constants.RIGHTSHIFTCLOSE, Constants.RIGHTSOLSLIDETIME);
    pneumSingle = null;
    switchLight = new SwitchLED();
    pneum = null;
    // driving = new Drive(getXboxXSpeed(), getXboxYSpeed());

    // s and j
    SmartDashboard.putString("Calibrate", "");
    SmartDashboard.putString("Turn Color", "");
    SmartDashboard.putBoolean("Boolean", false);

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // a.whenPressed(new Spin());
    xbox = new Joystick(1);
    rmove = new JoystickButton(xbox, 1);
    lmove = new JoystickButton(xbox, 5);
    mistake = new JoystickButton(xbox, 2);
    ex = new JoystickButton(xbox, Constants.X_BUTTON_ID);
    y = new JoystickButton(xbox, Constants.Y_BUTTON_ID);
    a = new JoystickButton(xbox, Constants.A_BUTTON_ID);

    JoystickButton three = new JoystickButton(xbox, 1);
    JoystickButton two = new JoystickButton(xbox, 2);
    lmove = new JoystickButton(xbox, 1);
    rmove = new JoystickButton(xbox, 5);
    JoystickButton four = new JoystickButton(xbox, 4);

    three.toggleWhenPressed(new SwitchLED());
    two.toggleWhenPressed(new CamMode());
    
    m_driveTrain.setDefaultCommand(new Drive());


    // s and j
    ex.toggleWhenPressed(new Shift(true));
    y.toggleWhenPressed(new Shift(false));


  }

  public static double getXboxYSpeed() {
    // if the y axis is more pushed more than a certain amount then (to account for
    // drivers accidentally
    // pressing on buttons) then the raw axis will be returned. That value will then
    // be used to drive
    // the robot. otherwise the it will return 0 and it will not move. 1 is the Y
    // Axis on the xbox controller
    if (Math.abs(xbox.getRawAxis(1)) >= .2) {
      SmartDashboard.putNumber("Yaxis", xbox.getRawAxis(1));
      return (xbox.getRawAxis(1));
    }
    return 0.0;
  }

  public static double getXboxXSpeed() {
    // if the x axis is more pushed more than a certain amount then (to account for
    // drivers accidentally
    // pressing on buttons) then the raw axis will be returned. That value will then
    // be used to drive
    // the robot. otherwise the it will return 0 and it will not move. 0 is the x
    // Axis on the xbox controller
    if (Math.abs(xbox.getRawAxis(5)) >= .2)
    {
        return (xbox.getRawAxis(5));
    }
    return 0.0;
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

  public Command getDrive()
  {
    return driving;
  }
}
