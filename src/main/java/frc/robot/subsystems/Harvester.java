/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
 
package frc.robot.subsystems;
 
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
 
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
 
public class Harvester extends SubsystemBase {
 
  TalonSRX TalonA;
  TalonSRX TalonB;
  // TalonSRX TalonC;
  TalonSRX indexMoto;
 
  private boolean up;
 
  /**
   * Creates a new Harvester.
   */
  public Harvester() {
    TalonA = new TalonSRX(Constants.HARVESTERTALONA);
    TalonB = new TalonSRX(Constants.HARVESTERTALONB);
    // TalonC = new TalonSRX(Constants.HARVESTERTALONC);
    indexMoto = new TalonSRX(Constants.IND);
    up = true;
  }
 
  //Runs the harvester at a select speed
  public void harvest(double speed)
  {
    TalonA.set(ControlMode.PercentOutput, speed);
    TalonB.set(ControlMode.PercentOutput, speed);
    // TalonC.set(ControlMode.PercentOutput, speed);
    indexMoto.set(ControlMode.PercentOutput, speed);
  }
 
  //Stops harvester
  public void stopHarvest()
  {
    TalonA.set(ControlMode.PercentOutput, 0);
    TalonB.set(ControlMode.PercentOutput, 0);
    // TalonC.set(ControlMode.PercentOutput, 0);
    indexMoto.set(ControlMode.PercentOutput, 0);
  }
 
  //Reverses harvester at a select speed
  public void harvestReverse(double speed)
  {
    TalonA.set(ControlMode.PercentOutput, -speed);
    TalonB.set(ControlMode.PercentOutput, -speed);
    // TalonC.set(ControlMode.PercentOutput, -speed);
    indexMoto.set(ControlMode.PercentOutput, -speed);
  }
 
  
  public boolean getUp()
  {
    return up;
  }
 
  public void setUp(boolean bool)
  {
    up = bool;
  }
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
 

