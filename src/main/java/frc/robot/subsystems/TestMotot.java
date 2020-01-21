/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TestMotot extends SubsystemBase {
  /**
   * Creates a new TestMotot.
   */
  private WPI_TalonSRX spinMotor;
  public TestMotot() 
  {
//   spinMotor = new WPI_TalonSRX(Constants.SPINPORT);

  }
  public void runMotor(double x)
  {
    spinMotor.set(x);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
