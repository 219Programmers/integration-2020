/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.revrobotics.CANEncoder;
// imports for motors 
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import for Motor Nums
import frc.robot.Constants;
// import for tank drive
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.commands.ExampleCommand;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.commands.Drive;
import edu.wpi.first.wpilibj.MotorSafety;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  public CANSparkMax sparkFL, sparkFR, sparkBL, sparkBR;
  public CANEncoder encoderFL;
public CANEncoder encoderFR;
public CANEncoder encoderBL;
public CANEncoder encoderBR;


  public DifferentialDrive daryl;
  private SpeedControllerGroup leftdrive, rightdrive;
  public boolean go;
  public boolean reverse;
 

  

  public DriveTrain() 
  {
    //controllers being constructed
    sparkFL = new CANSparkMax(Constants.SPARKFL, MotorType.kBrushless);
    sparkFR = new CANSparkMax(Constants.SPARKFR, MotorType.kBrushless);
    sparkBL = new CANSparkMax(Constants.SPARKBL, MotorType.kBrushless);
    sparkBR = new CANSparkMax(Constants.SPARKBR, MotorType.kBrushless);
    /* encoders have to be constructed seperately in order to afford the stuttering problem that
    occured in the Pre-Season of 2020. See documentation in the log for more 
    details regarding the encoder problem. */
    reverse = false;
    go = true;
    encoderFL = sparkFL.getEncoder();
    encoderFR = sparkFR.getEncoder();
    encoderBL = sparkBL.getEncoder();
    encoderBR = sparkBR.getEncoder();
    sparkFL.setIdleMode(IdleMode.kCoast);
    sparkFR.setIdleMode(IdleMode.kCoast);
    sparkBL.setIdleMode(IdleMode.kCoast);
    sparkBR.setIdleMode(IdleMode.kCoast);
    leftdrive = new SpeedControllerGroup(sparkFL, sparkBL);
    rightdrive = new SpeedControllerGroup(sparkFR, sparkBR);
    daryl = new DifferentialDrive(leftdrive, rightdrive);
  }

  public void regDrive(double speedL, double speedR)
  {
    // 1.3 was the og value that was tested by james.	    
    double change = 1.5;	
    // robot was going "BRRRRRRRRR" and was being mean to James. thus had to change the robot's behavior. 	

    daryl.tankDrive(speedL/change, speedR/change);
    //sparkFL.set(speedL/change);
    // sparkBL.set(speedL/change);
    //sparkFR.set(0.5);
    // sparkBR.set(speedR/change);
  }

  public double getWheelPosFL()
  {
    return encoderFL.getPosition();
  }

  public double getWheelPosFR()
  {
    return encoderFR.getPosition();
  }

  public double getWheelPosBL()
  {
    return encoderBL.getPosition();
  }

  public double getWheelPosBR()
  {
    return encoderBR.getPosition();
  }

  public double getWheelVelocity()
  {
    return encoderFL.getVelocity();
  }

  public void brakeModeForSec(double breakForSeconds)
  {
    sparkFL.setIdleMode(IdleMode.kBrake);
    sparkFR.setIdleMode(IdleMode.kBrake);
    sparkBL.setIdleMode(IdleMode.kBrake);
    sparkBR.setIdleMode(IdleMode.kBrake);
    try
    {
      Thread.sleep((long)(breakForSeconds*1000));
    }
    catch(Exception e){}
    sparkFL.setIdleMode(IdleMode.kCoast);
    sparkFR.setIdleMode(IdleMode.kCoast);
    sparkBL.setIdleMode(IdleMode.kCoast);
    sparkBR.setIdleMode(IdleMode.kCoast);
  }
  @Override
  public void periodic() 
  {
   // setDefaultCommand(new Drive(getXboxXSpeed(), getXboxYSpeed()));
  }


}
