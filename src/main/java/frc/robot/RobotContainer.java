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
import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// cut down the lengthy imports and simply imports all commands and subsystems all together
import frc.robot.commands.*; 
import frc.robot.subsystems.*;

import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;

import com.kauailabs.navx.frc.AHRS;

import org.opencv.videoio.VideoCapture;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
  public static final TestMotot m_spin = new TestMotot();
// The robot's subsystems and commands are defined here...

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  public static DistanceSensor m_ds;
  public static DistanceSensor m_ds2;
  public static MrEisnersSolenoid m_esol;
  public static LinearA m_pwm;
  public static TalonMotor m_tMotor;
  public static DriveTrain m_driveTrain;
  public static AutonLime auto;
  public static LimeVisionSubsystem limeSub;
  public static SwitchLED switchLight;
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
  public static AHRS ahrs;

  //james and simeon code
  public static Joystick xbox;
  public static Joystick joy;
  public static Joystick mainDriver;
  
  public JoystickButton ex;
  public JoystickButton y;
  public JoystickButton a;
  public JoystickButton leftT;
  public JoystickButton rightT;
  public JoystickButton trigger;
  public JoystickButton two;
  public JoystickButton five;
  public JoystickButton six;
  public JoystickButton seven;
  public JoystickButton nine;
  public JoystickButton ten;
  public JoystickButton lmove;
  public JoystickButton rmove;
  public JoystickButton eleven;
  public JoystickButton twelve;
  public Button b;
  public Button three;
  public Button four;
  public Button x;

  public static boolean go;
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
   {
    // making the subsystems that would be called in a command Requirements
    limeSub = new LimeVisionSubsystem();
    m_driveTrain = new DriveTrain();
    sPID = new ShooterPID();
    switchLight = new SwitchLED();
    mongClimb = new Climb();
    //m_cs = new ColorSensor(); 
    auto = new AutonLime();
    m_ds = new DistanceSensor(true);
    // m_ds2 = new DistanceSensor(1);
    m_pwm = new LinearA();
    m_tMotor = new TalonMotor(17);
    m_mot = new Motor();
    pneumSingle = new SinglePneumatics(2);
    m_esol = new MrEisnersSolenoid();
    m_harvester = new Harvester();
    m_harvestpneum = new Pneumatics(6, 7, 0.03);
    comp = new Compression();
    gibShoot = new IndexCorral();

    m_navx = new NavX();
    lPneumShift = new Pneumatics(0, 1, Constants.SOLSLIDETIME);
    //rPneumShift = new Pneumatics(3, 5, Constants.RIGHTSOLSLIDETIME); // currently fake ports
    switchLight = new SwitchLED();
   

    // mongClimb.encoderSpark1.setPosition(0);
    // pneum = new Pneumatics(4, 5, 0.03);
    // pneum = null;
    // driving = new Drive(getXboxXSpeed(), getXboxYSpeed());
    // lPneumShift = new Pneumatics(Constants.LEFTSHIFTOPEN, Constants.LEFTSHIFTCLOSE, Constants.SOLSLIDETIME);
    // rPneumShift = new Pneumatics(Constants.RIGHTSHIFTOPEN, Constants.RIGHTSHIFTCLOSE, Constants.RIGHTSOLSLIDETIME);
    // pneumSingle = null;
    // s and j
    SmartDashboard.putString("Calibrate", "");
    SmartDashboard.putString("Turn Color", "");
    SmartDashboard.putNumber("Change", 1);

    SmartDashboard.putNumber("turn", 15);
 	  SmartDashboard.putNumber("mongSpeed", .3);
    SmartDashboard.putNumber("Speed", .5);
    // SmartDashboard.putNumber("SpeedF", .2);
    // CameraServer.getInstance().startAutomaticCapture(0); // this is for the camera, might interfer with limelight (look into)
    // check camera usb port number (might not be 0)
    go = false;
 
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
    three = new JoystickButton(joy, 3);
    five = new JoystickButton(joy, 5);
    six = new JoystickButton(joy, 6);
    seven = new JoystickButton(joy, 7);
    nine = new JoystickButton(joy, 9);
    ten = new JoystickButton(joy, 10);
    eleven = new JoystickButton(joy, 11);
    twelve = new JoystickButton(joy, 12);
    
    // main driver controls
    leftT.toggleWhenPressed(new Shift(false)); // open 
    rightT.toggleWhenPressed(new Shift(true)); // close 
    b.toggleWhenPressed(new Reverse()); // reverse drive 
    y.toggleWhenPressed(new HarvesterUpDown()); // releasing the harvester down or up
    twelve.whileHeld(new RevCor(false)); // reversing the corral 
    eleven.whileHeld(new RevCor(true)); // sending the balls to the shooter 
  // b.toggleWhenPressed(new ScanClosestColor());
    // a.toggleWhenPressed(new CloseValve());
    // b.toggleWhenPressed(new UsePneum());
    //a.whileHeld(new ClimbNow(true, 0.5));
    // b.whileHeld(new ClimbNow(false, 0.5));
    five.toggleWhenPressed(new SwitchLimeLine());
     seven.toggleWhenPressed(new ClimbSequence(false)); // deploy motor then ratchet
     nine.toggleWhenPressed(new ClimbSequence(true)); // unratchet deploy then deploy motor for a little bit then retract motor
    //nine.whileHeld(new ClimbNow(false, 0.5)); // retract motor
    // ten.toggleWhenPressed(new ClimberPneumGo(true));
    // eleven.toggleWhenPressed(new ClimberPneumGo(false));
    // co-driver controls
    // a.toggleWhenPressed(new SwitchLimeLine());
    trigger.whileHeld(new ShootAndCorral(10)); // shooter at 90 rn

    two.toggleWhenPressed(new LimeFindTarget()); // supposed to straigthen the shot out
    three.toggleWhenPressed(new CamSwitch());
    // seven.toggleWhenPressed(new ClimbNow(true, 0.5)); // initating the talon that puts the climber up
    // nine.whileHeld(new ClimbNow(false, 0.5)); // sparkmax pulling us up
    //six.whileHeld(new SpinTalonMotor(m_tMotor, 0.2)); // "color wheel"
    //six.toggleWhenPressed(new MakeReadyShoot());
    six.toggleWhenPressed(new AutonLime());
    m_harvester.setDefaultCommand(new Harvest(false)); //  harvester is not going
    gibShoot.setDefaultCommand(new PulseCorral()); // sensor doesn't work rn
 //   m_navx.setDefaultCommand(new VibrateOtherController());
    // eleven.toggleWhenPressed(new Climbre(true));
    m_driveTrain.setDefaultCommand(new Drive());
    
 
  //  m_driveTrain.setDefaultCommand(new FieldMapping());
  }
	/**
	 * Getter for the x-axis of the left joystick
	 * @return The x-axis on the left joystick which is used for the speed of the right motors on tank drive
	 */
  public static double getLeftSpeed() 
  {

    if (Math.abs(xbox.getRawAxis(1)) >= .2) 
    {
      return (xbox.getRawAxis(1));
		}
		return 0.0;
	}
	/**
	 * Getter for the x-axis of the right joystick
	 * @return The x-axis on the right joystick which is used for the speed of the right motors on tank drive
	 */
	public static double getRightSpeed() 
	{
		if(Math.abs(xbox.getRawAxis(5)) >= .2)
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
  public Command getAutonomousCommand()
  {
    return new ActualAuton();
  }

  public static Command getAutonLime()	
  {
    return auto;	    
  }
}
