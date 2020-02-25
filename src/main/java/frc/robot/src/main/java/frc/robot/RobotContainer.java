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
import frc.robot.commands.AutonLime;
import frc.robot.commands.CamMode;
import frc.robot.commands.ClimbNow;
import frc.robot.commands.ClimbYes;
import frc.robot.commands.CloseValve;
import frc.robot.commands.DoAuton;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.FieldMapping;
import frc.robot.commands.HarvesterUpDown;
import frc.robot.commands.LimeFindTarget;
import frc.robot.commands.MrEisnerOpenClose;
import frc.robot.commands.PIDShooterCommand;
import frc.robot.commands.PWMOpenClose;
import frc.robot.commands.Reverse;
import frc.robot.commands.ScanClosestColor;
import frc.robot.commands.SetYawZero;
import frc.robot.commands.Shift;
import frc.robot.commands.SinglePneum;
import frc.robot.commands.SinglePneumClose;
import frc.robot.commands.Spin;
import frc.robot.commands.SpinTalonMotor;
import frc.robot.commands.SwitchLED;
import frc.robot.commands.TurnToYawZero;
import frc.robot.commands.UsePneum;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.Compression;
import frc.robot.subsystems.DistanceSensor;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Harvester;
import frc.robot.subsystems.IndexCorral;
import frc.robot.subsystems.LimeVisionSubsystem;
import frc.robot.subsystems.LinearA;
import frc.robot.subsystems.Motor;
import frc.robot.subsystems.MrEisnersSolenoid;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.ShooterPID;
import frc.robot.subsystems.SinglePneumatics;
import frc.robot.subsystems.TalonMotor;
import frc.robot.subsystems.TestMotot;
import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;

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

  public Button three;
  public Button four;
 
  public Button x;

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
 
  public static DistanceSensor m_ds;
  public static DistanceSensor m_ds2;
  public static MrEisnersSolenoid m_esol;

  public static LinearA m_pwm;
  public static TalonMotor m_tMotor;
 
  public static boolean go;


  
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
    // Configure the button bindings
    limeSub = new LimeVisionSubsystem();
    m_driveTrain = new DriveTrain();
    sPID = new ShooterPID();
     switchLight = new SwitchLED();
     mongClimb = new Climb();
     m_cs = new ColorSensor();
    auto = new AutonLime();
    go = false;
     m_ds = new DistanceSensor();
     m_ds2 = new DistanceSensor(1);
     m_pwm = new LinearA();
     m_tMotor = new TalonMotor(2);
    
     m_mot = new Motor();
    // mongClimb.encoderSpark1.setPosition(0);
     pneumSingle = new SinglePneumatics(4);
     m_esol = new MrEisnersSolenoid();
    //pneum = new Pneumatics(0, 1, Constants.SOLSLIDETIME);
  
     m_harvester = new Harvester();
     m_harvestpneum = new Pneumatics(0, 1, 0.03);
     comp = new Compression();
   
     gibShoot = new IndexCorral();
    m_navx = new NavX();
    //  m_mot = new Motor();
    
    //  lPneumShift = new Pneumatics(Constants.LEFTSHIFTOPEN, Constants.LEFTSHIFTCLOSE, Constants.SOLSLIDETIME);
    //  rPneumShift = new Pneumatics(Constants.RIGHTSHIFTOPEN, Constants.RIGHTSHIFTCLOSE, Constants.RIGHTSOLSLIDETIME);
    // pneumSingle = null;
    lPneumShift = new Pneumatics(6, 7, Constants.SOLSLIDETIME);
    rPneumShift = new Pneumatics(3, 5, Constants.RIGHTSOLSLIDETIME);
    switchLight = new SwitchLED();
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
   
    xbox = new Joystick(0);
    joy = new Joystick(1);
    // maindriver buttons
    ex = new JoystickButton(xbox, 3);
    y = new JoystickButton(xbox, 4);
    a = new JoystickButton(xbox, 1);
    b = new JoystickButton(xbox, 2);
    leftT = new JoystickButton(xbox, 5);
    rightT = new JoystickButton(xbox, 6);
    // co-driver buttons
    trigger = new JoystickButton(joy, 1);
    two = new JoystickButton(joy, 2);
    six = new JoystickButton(joy, 6);
    seven = new JoystickButton(joy, 7);
    nine = new JoystickButton(joy, 9);
    // main driver controls
    leftT.toggleWhenPressed(new Shift(false));
    rightT.toggleWhenPressed(new Shift(true));
    b.toggleWhenPressed(new Reverse());
    y.toggleWhenPressed(new HarvesterUpDown());
    ex.whileHeld(new RevCor(false));
    a.whileHeld(new RevCor(true));
    // co-driver controls
    trigger.whileHeld(new PIDShooterCommand(.9));
    two.toggleWhenPressed(new LimeFindTarget());
    //six.toggleWhenPressed(new ClimbYes());
  //  seven.whileHeld(new ClimbNow(true, 0.5));
    //nine.whileHeld(new ClimbNow(false, 0.5));
   // gibShoot.setDefaultCommand(new PulseCorral());
    m_harvester.setDefaultCommand(new Harvest(false));



    //m_driveTrain.setDefaultCommand(new Drive());
 
 
  //  m_driveTrain.setDefaultCommand(new FieldMapping());
  }
	/**
	 * Getter for the x-axis of the left joystick
	 * @return The x-axis on the left joystick which is used for the speed of the right motors on tank drive
	 */
  public static double getLeftSpeed() 
  {
    // if (Math.abs(mainDriver.getRawAxis(1)) >= .2) 
    // {
    //   return (mainDriver.getRawAxis(1));
		// }
		return 0.0;
	}
	/**
	 * Getter for the x-axis of the right joystick
	 * @return The x-axis on the right joystick which is used for the speed of the right motors on tank drive
	 */
	public static double getRightSpeed() 
	{
		// if(Math.abs(mainDriver.getRawAxis(5)) >= .2)
		// {
		// 	return (mainDriver.getRawAxis(5));
		// }
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
  public Command getAutonomousCommand()
  {
    return new DoAuton(true);
  }

  public static Command getAutonLime()	
  {
    return auto;	    
  }
}
