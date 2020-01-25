/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.I2C.Port;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // current values are standins for the real thing. -1/9/20
    public static int SPARKFL = 6;
    public static int SPARKFR = 8;
    public static int SPARKBL = 5;
    public static int SPARKBR = 4;
   // public static int SPINPORT = 0;
    public static int mfr = 9;
    public static int mfl = 3;
    public static int mbr = 8;
    public static int mbl = 4;

    public static final int X_BUTTON_ID = 3;
	public static final int A_BUTTON_ID = 1;
	public static final int LEFT_TRIGGER_ID = 5;
	public static final int RIGHT_TRIGGER_ID = 6;
	public static final int XBOX_CONTROLLER_PORT = 0;
	public static final int FRONTRIGHT = 9;
	public static final int FRONTLEFT = 3;
	public static final int BACKRIGHT = 8;
	public static final int BACKLEFT = 4;
	public static final int Y_BUTTON_ID = 4;
	public static final int PNEUM = 3;
	public static final int SINGLEPNEUM = 0;
	public static final int PNEUMCLOSE = 2;
	public static final int LEFTSHIFTCLOSE = 1; //port
	public static final int LEFTSHIFTOPEN = 0; //port
	public static final int RIGHTSHIFTCLOSE = 3; //port
	public static final int RIGHTSHIFTOPEN = 2; //port
	public static final int PCMCAN = 1;
	public static final double SOLSLIDETIME = 0.03;
    public static final double RIGHTSOLSLIDETIME = .05;
    public static final Port COLOR_SENSOR_PORT = Port.kOnboard;
}
