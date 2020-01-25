/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Compression extends SubsystemBase {
  AnalogInput pressureSensor = new AnalogInput(3);
	Compressor compressor;

	private static final double kMaxPressure = 2.55;

	public Compression() {
		if (Robot.isReal()) {
			compressor = new Compressor();
		}

		//LiveWindow.addSensor("Pneumatics", "Pressure Sensor", pressureSensor);
	}


	/**
	 * Start the compressor going. The compressor automatically starts and stops
	 * as it goes above and below maximum pressure.
	 */
	public void start() {
		if (Robot.isReal()) {
      compressor.start();
		}
	}

  public void stop()
  {
    compressor.stop();
  }
	/**
	 * @return Whether or not the system is fully pressurized.
	 */
	public boolean isPressurized() {
		if (Robot.isReal()) {
			return kMaxPressure <= pressureSensor.getVoltage();
		} else {
			return true; // NOTE: Simulation always has full pressure
		}
	}

	/**
	 * Puts the pressure on the SmartDashboard.
	 */
	public void writePressure() {
		SmartDashboard.putNumber("Pressure", pressureSensor.getVoltage());
	}
}
