/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeVisionSubsystem extends SubsystemBase 
{
  	// Put methods for controlling this subsystem

	//Create variables
	public double targetD;
	public boolean hasTarget;
	public double yOffset;
	public double area;
	public double skew;
	public double LEDMode;
	public double camMode;
	public double pipeline;
	public static double MOUNTANG = 0;
    public static double LOOKANG = 0;
    public static double ROBOHGT = 0;
    public static double TARGETHGT = 0;
	
	public static NetworkTableInstance tableInstance = NetworkTableInstance.getDefault();
	public static NetworkTable table = tableInstance.getTable("limelight");

	public void initDefaultCommand() {
		// Set the default command for a subsystem here. setDefaultCommand
		//	(new LimeVisionCommand());
		}

	//Does the camera proccessor have a target?
	public boolean getHasTarget()
	 {
		targetD = table.getEntry("tv").getDouble(0); 
		if(targetD == 0) 
		{
			hasTarget = false;
		}
		else if(targetD == 1) 
		{
			hasTarget = true;
		}
		return hasTarget;
	}

	//Limelight LED state
	public double getLEDMode() 
	{
		LEDMode = table.getEntry("ledMode").getDouble(1);
		return LEDMode;
	}

	public void setLEDMode(double x)
	{
		table.getEntry("ledMode").setDouble(x);
	}

	//Limelight Camera state
	public double getCamMode()
	{
		camMode = table.getEntry("camMode").getDouble(0);
		return camMode;
	}
		//Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
		public double getYOffset() 
		{
			yOffset = table.getEntry("ty").getDouble(0);
			return yOffset;
		}
		


	//Set the LED mode of the limelight
	public void switchLED()
	 {
		if(getLEDMode() == 0)
		{
			table.getEntry("ledMode").setDouble(1);
			SmartDashboard.putString("LED Mode", "Off");
		}
		else if(getLEDMode() == 1) 
		{
			table.getEntry("ledMode").setDouble(3);
			SmartDashboard.putString("LED Mode", "On");
		}
		else if(getLEDMode() == 2) 
		{
			table.getEntry("ledMode").setDouble(1);
			SmartDashboard.putString("LED Mode", "Off");
		}
		else if(getLEDMode() == 3) 
		{
			table.getEntry("ledMode").setDouble(1);
			SmartDashboard.putString("LED Mode", "Off");
		}
	}

	//Set the camera mode
	public void switchCamera() 
	{
		if(getCamMode() == 0) 
		{
			table.getEntry("camMode").setDouble(1);
			SmartDashboard.putString("Camera Mode", "Camera");
		}
		else if(getCamMode() == 1) 
		{
			table.getEntry("camMode").setDouble(0);
			SmartDashboard.putString("Camera Mode", "Vision");
		}
	}


	// PIPELINE
	private static NetworkTableEntry pipelineEntry = table.getEntry("pipeline");

	/**
	 * @param pipeline Specified pipeline to set the limelight to
	 */
	public void setPipeline(final int pipeline) {
        // Prevent input of invalid pipelines
        if (pipeline >= 0 && pipeline <= 9) {
            pipelineEntry.setNumber(pipeline);
        }
    }

    private static NetworkTableEntry getPipelineEntry = table.getEntry("getpipe");

	//finds the active pipeline with the networktable
	public double getPipeline() 
	{
        return getPipelineEntry.getDouble(0);
	}

	//uses the formula d = (h2-h1) / tan(a1+a2) from the limelight website to calculate the distance
	public double getDistance()
	{

		return (TARGETHGT - 29.5) / Math.tan(table.getEntry("ty").getDouble(0.0) + 110);
	}

}
