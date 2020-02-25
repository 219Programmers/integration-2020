/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pneumatics extends SubsystemBase {
  private Solenoid  valveOpenSide, valveCloseSide;

  public double solenoidSlideTime;

  /**
   * @param port PCM port wired to open/A side of valve. Close/B side is wired to PCM next port.
   * Assumes PCM CAN Id 0.
   */

  // public Pneumatics()
  // {
  //         valveOpenSide = new Solenoid(Constants.PNEUM);
  //         valveCloseSide = new Solenoid(Constants.PNEUMCLOSE);

  //         solenoidSlideTime = .05;
  // }

  /**
   * @param pcmCanId PCM CAN Id number.
   * @param port PCM port wired to open/A side of valve. Close/B side is wired to PCM next port.
   */
  //portOpen is the open port, portClose is the close port, slideTime is the time it takes to slide
  public Pneumatics(int portOpen, int portClose, double slideTime)
  {
          valveOpenSide = new Solenoid(Constants.PCMCAN, portOpen);
          valveCloseSide = new Solenoid(Constants.PCMCAN, portClose);
          solenoidSlideTime = slideTime;
  }

  /**
   * Release resources.
   */
  // protected void dispose()
  // {
  //         valveOpenSide.free();
  //         valveCloseSide.free();
  // } Commented out because the free method is apparently not working

  /**
   * Open the valve (pressurize port).
   */
  public void Open()
  {
          valveCloseSide.set(false);
          valveOpenSide.set(true);
          Timer.delay(solenoidSlideTime);
          valveOpenSide.set(false);
  }
  
  /**
   * Pressurize the A side of the valve.
   */
  public void SetA()
  {
          Open();
  }

  /**
   * Close the valve (pressurize port+1).
   */
  public void Close()
  {
          valveOpenSide.set(false);

          valveCloseSide.set(true);
          Timer.delay(solenoidSlideTime);
          valveCloseSide.set(false);
  }
  
  /**
   * Pressurize the B side of the valve.
   */
  public void SetB()
  {
          Close();
  }


}
