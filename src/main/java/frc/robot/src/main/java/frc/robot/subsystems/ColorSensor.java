/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorSensor extends SubsystemBase {
  private ColorSensorV3 scan = new ColorSensorV3(Constants.COLOR_SENSOR_PORT);
  public Color[] colors = { ColorMatch.makeColor(0.13, 0.42, 0.44),
      ColorMatch.makeColor(0.16, 0.57, 0.25), ColorMatch.makeColor(0.5, 0.35, 0.13),
      ColorMatch.makeColor(0.31, 0.55, 0.12) };
  /**
   * Creates a new ColorSensor.
   */
  public ColorSensor() {
    // colors[0] = ColorMatch.makeColor(SmartDashboard.getNumber("BlueR", 0.13), SmartDashboard.getNumber("BlueG", 0.42), SmartDashboard.getNumber("BlueB", 0.44));
    // colors[1] = ColorMatch.makeColor(SmartDashboard.getNumber("GreenR", 0.16), SmartDashboard.getNumber("GreenG", 0.57), SmartDashboard.getNumber("GreenB", 0.25));
    // colors[2] = ColorMatch.makeColor(SmartDashboard.getNumber("RedR", 0.5), SmartDashboard.getNumber("RedG", 0.35), SmartDashboard.getNumber("RedB", 0.13));
    // colors[3] = ColorMatch.makeColor(SmartDashboard.getNumber("YellowR", 0.31), SmartDashboard.getNumber("YellowG", 0.55), SmartDashboard.getNumber("YellowB", 0.12));
  }

  //Gets how different two colors are based on their rgb values (the higher the number the farther the are apart)
  public static double colorDifference(Color compare, Color compareTwo)
  {
    return Math.abs(compareTwo.red - compare.red) + Math.abs(compareTwo.blue - compare.blue) + Math.abs(compareTwo.green - compare.green);
  }

  // Displays the RGB values of the color scanned by the ColorSensorV3
  public void displayRGB() {
    final Color currentColor = scan.getColor();
    SmartDashboard.putNumber("R", currentColor.red);
    SmartDashboard.putNumber("G", currentColor.green);
    SmartDashboard.putNumber("B", currentColor.blue);
  }

  //Calibrates selected Color (Blue, Green, Red, and Yellow) depending on what's listed under "Calibrate" on the SmartDashboard
  public void calibration()
  {
    Color colorScanned = scan.getColor();
    String calibrate = SmartDashboard.getString("Calibrate", "").toLowerCase();
    if(calibrate.equals("red"))
    {
      SmartDashboard.putNumber("Previous red difference", colorDifference(colorScanned, colors[2]));
      colors[2] = colorScanned;
      SmartDashboard.putNumber("RedR", colorScanned.red);
      SmartDashboard.putNumber("RedG", colorScanned.green);
      SmartDashboard.putNumber("RedB", colorScanned.blue);
      SmartDashboard.putString("Calibrated Color", "Red"); 
    }
    else if(calibrate.equals("blue"))
    {
      SmartDashboard.putNumber("Previous blue difference", colorDifference(colorScanned, colors[0]));
      colors[0] = colorScanned;
      SmartDashboard.putNumber("BlueR", colorScanned.red);
      SmartDashboard.putNumber("BlueG", colorScanned.green);
      SmartDashboard.putNumber("BlueB", colorScanned.blue);
      SmartDashboard.putString("Calibrated Color", "Blue");
    }
    else if(calibrate.equals("green"))
    {
      SmartDashboard.putNumber("Previous green difference", colorDifference(colorScanned, colors[1]));
      colors[1] = colorScanned;
      SmartDashboard.putNumber("GreenR", colorScanned.red);
      SmartDashboard.putNumber("GreenG", colorScanned.green);
      SmartDashboard.putNumber("GreenB", colorScanned.blue);
      SmartDashboard.putString("Calibrated Color", "Green");
    }
    else if(calibrate.equals("yellow"))
    {
      SmartDashboard.putNumber("Previous yellow difference", colorDifference(colorScanned, colors[3]));
      colors[3] = colorScanned;
      SmartDashboard.putNumber("YellowR", colorScanned.red);
      SmartDashboard.putNumber("YellowG", colorScanned.green);
      SmartDashboard.putNumber("YellowB", colorScanned.blue);
      SmartDashboard.putString("Calibrated Color", "Yellow");
    }
    else
    {
      SmartDashboard.putString("Calibrated Color", "Nothing");
    }
  }
  // Matches color with another color from an array based on the difference of rgb values
  public static Color compareColors(final Color[] colorArr, final Color compare) {
    Color result = colorArr[0];
    double differentLow = 3;
    for (final Color x : colorArr) {
      final double currentDiff = colorDifference(x, compare);
      if (currentDiff < differentLow) {
        differentLow = currentDiff;
        result = x;
      }
    }
    return result;
  }
  
  
  // Matches currently selected color with blue, red, green, yellow, depending
  // on whichever it detects it is closest too and diplays it to the Smart
  // Dashboard
  public Color getClosest() {
    final Color currentColor = scan.getColor();
    final Color compared = compareColors(colors, currentColor);
      if(compared == colors[2])
      {
        SmartDashboard.putString("Color", "Red");
      }
      else if(compared == colors[0])
      {
        SmartDashboard.putString("Color", "Blue");
      }
      else if(compared == colors[1])
      {
        SmartDashboard.putString("Color", "Green");
      }
      else if(compared == colors[3])
      {
        SmartDashboard.putString("Color", "Yellow");
      }
      else
      {
        SmartDashboard.putString("Color", "Other"); //Should never happen
      }
      return compared; //returns matched color  
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
