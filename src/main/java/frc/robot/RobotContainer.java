/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
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
  // private final ColorSensor scanner = new ColorSensor();
  private final XboxController controller = new XboxController(Constants.XBOX_CONTROLLER_PORT);
  public final JoystickButton x = new JoystickButton(controller, Constants.X_BUTTON_ID);
  public final JoystickButton y = new JoystickButton(controller, Constants.Y_BUTTON_ID);
  public final JoystickButton a = new JoystickButton(controller, Constants.A_BUTTON_ID);
  public static ColorSensor m_cs = new ColorSensor();
  public static Motor m_mot = new Motor();
  public static Pneumatics pneum = null; // new Pneumatics(Constants.PNEUM, Constants.PNEUMCLOSE, Constants.SOLSLIDETIME);
  public static Pneumatics lPneumShift = new Pneumatics(Constants.LEFTSHIFTOPEN, Constants.LEFTSHIFTCLOSE, Constants.SOLSLIDETIME);
  public static Pneumatics rPneumShift = new Pneumatics(Constants.RIGHTSHIFTOPEN, Constants.RIGHTSHIFTCLOSE, Constants.RIGHTSOLSLIDETIME);
  public static SinglePneumatics pneumSingle = null; //new SinglePneumatics();
  public static Compression comp = new Compression();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    SmartDashboard.putString("Calibrate", "");
    SmartDashboard.putString("Turn Color", "");
    SmartDashboard.putBoolean("Boolean", false);
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // x.toggleWhenPressed(new ColorScanMove());
    // x.toggleWhenPressed(new CompressCom());
    // y.toggleWhenPressed(new StopCompress());
    // x.whenPressed(new SinglePneum());
    // y.toggleWhenPressed(new SinglePneumClose());
    // x.whenPressed(new ColorWheel());
    // x.whenPressed(new BooleanTest());
    // y.toggleWhenPressed(new CalibrateColor());
    // a.toggleWhenPressed(new CompressCom());
    x.toggleWhenPressed(new Shift(true));
    y.toggleWhenPressed(new Shift(false));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   * 
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}