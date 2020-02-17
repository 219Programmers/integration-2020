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
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj.smartdashboard.*;

import frc.robot.subsystems.*;
import frc.robot.commands.*;
import frc.robot.Robot;



/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
 
  public static DriveTrain m_driveTrain;
  public static AutonLime auto;
  public static Joystick xbox;
  public static Joystick joy;
 // public Button a;

  public Button b;
  public JoystickButton lmove;
  public JoystickButton rmove;

  // setDefaultCommand(new Drive());

  public static LimeVisionSubsystem limeSub;
  public static SwitchLED switchLight;


  //james and simeon code
  public JoystickButton ex;
  public JoystickButton y;
  public JoystickButton a;
  public JoystickButton leftT;
  public JoystickButton rightT;
  public JoystickButton trigger;
  public JoystickButton two;
  public JoystickButton six;
  public JoystickButton seven;
  public JoystickButton nine;
  public static ColorSensor m_cs;
  public static Motor m_mot; //Commented out, might conflict with normal drive
  public static Pneumatics pneum; // new Pneumatics(Constants.PNEUM, Constants.PNEUMCLOSE, Constants.SOLSLIDETIME);
  public static Pneumatics lPneumShift;
  public static Pneumatics rPneumShift;
  public static Pneumatics m_harvestpneum;
  public static SinglePneumatics pneumSingle; //new SinglePneumatics();
  public static Compression comp;
  public static ShooterPID sPID;



  public static Climb mongClimb;
  public static IndexCorral gibShoot;
  public static Harvester m_harvester;
  public static NavX m_navx;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() 
  {
    // m_driveTrain = new DriveTrain();
    // m_harvester = new Harvester();
    // m_harvestpneum = new Pneumatics(0, 1, 0.03);
    // comp = new Compression();
    // limeSub = new LimeVisionSubsystem();
    // m_cs = new ColorSensor();
    // mongClimb = new Climb();
    // sPID = new ShooterPID();
    // gibShoot = new IndexCorral();
    m_navx = new NavX();
    //  m_mot = new Motor();
    
    // lPneumShift = new Pneumatics(Constants.LEFTSHIFTOPEN, Constants.LEFTSHIFTCLOSE, Constants.SOLSLIDETIME);
    // rPneumShift = new Pneumatics(Constants.RIGHTSHIFTOPEN, Constants.RIGHTSHIFTCLOSE, Constants.RIGHTSOLSLIDETIME);
    // pneumSingle = null;
    // switchLight = new SwitchLED();
    // pneum = null;

    // driving = new Drive(getXboxXSpeed(), getXboxYSpeed());

    // s and j
    SmartDashboard.putString("Calibrate", "");
    SmartDashboard.putString("Turn Color", "");
    SmartDashboard.putNumber("Change", 1);

    SmartDashboard.putNumber("turn", 15);
 	  SmartDashboard.putNumber("mongSpeed", .3);
    SmartDashboard.putNumber("Speed", .5);
    SmartDashboard.putNumber("SpeedF", .2);
    xbox = new Joystick(0);
    joy = new Joystick(1);
    ex = new JoystickButton(xbox, 3);

    ex.toggleWhenPressed(new VibrateOtherController());
    RobotContainer.xbox.setRumble(RumbleType.kRightRumble, 1);
    // configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    
    xbox = new Joystick(0);
    joy = new Joystick(1);
    ex = new JoystickButton(xbox, 3);
    y = new JoystickButton(xbox, 4);
    a = new JoystickButton(xbox, 1);
    b = new JoystickButton(xbox, 2);
    leftT = new JoystickButton(xbox, 5);
    rightT = new JoystickButton(xbox, 6);
    trigger = new JoystickButton(joy, 1);
    two = new JoystickButton(joy, 2);
    six = new JoystickButton(joy, 6);
    seven = new JoystickButton(joy, 7);
    nine = new JoystickButton(joy, 9);
    leftT.toggleWhenPressed(new Shift(false));
    rightT.toggleWhenPressed(new Shift(true));
    b.toggleWhenPressed(new Reverse());
    y.toggleWhenPressed(new HarvesterUpDown());
    trigger.whileHeld(new PIDShooterCommand(300));
    two.toggleWhenPressed(new LimeFindTarget());
    six.toggleWhenPressed(new ClimbYes());
    seven.whileHeld(new ClimbNow(true, 0.5));
    nine.whileHeld(new ClimbNow(false, 0.5));

    // two.toggleWhenPressed();
     m_driveTrain.setDefaultCommand(new Drive());
    //  m_harvester.setDefaultCommand(new Harvest(false).alongWith(new Harvest(true)));

  }

  public static double getRightSpeed() {
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


  public static double getLeftSpeed() {
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

  public static boolean getLeftTrigger() {
    return xbox.getRawAxis(2)>0.5;
  }

  public static boolean getRightTrigger() {
    return xbox.getRawAxis(3)>0.5;
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
 

  
  public static Command getAutonLime()
  {
    return auto;
  }

}
