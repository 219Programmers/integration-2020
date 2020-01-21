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
  private CANSparkMax sparkFL, sparkFR, sparkBL, sparkBR;
  private CANEncoder encoderFL, encoderFR, encoderBL, encoderBR;
  public DifferentialDrive daryl;
  private SpeedControllerGroup leftdrive, rightdrive;
 

  

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


    encoderFL = sparkFL.getEncoder();
    encoderFR = sparkFR.getEncoder();
    encoderBL = sparkBL.getEncoder();
    encoderBR = sparkBR.getEncoder();

    leftdrive = new SpeedControllerGroup(sparkFL, sparkBL);
    rightdrive = new SpeedControllerGroup(sparkFR, sparkBR);
    daryl = new DifferentialDrive(leftdrive, rightdrive);
  }

  public void regDrive(double speedL, double speedR)
  {
      daryl.tankDrive(speedL, speedR);

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

  @Override
  public void periodic() 
  {
   // setDefaultCommand(new Drive(getXboxXSpeed(), getXboxYSpeed()));
  }


}
