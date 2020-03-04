/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Harvester;
import frc.robot.subsystems.IndexCorral;

public class PulseCorral extends CommandBase {
  /**
   * Creates a new PulseCorral.
   */
  public static DigitalInput limitSwitch;
  int numPowerCells; 
  boolean lazerTrip, lazerTripTwo;
  public PulseCorral() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.gibShoot, RobotContainer.m_ds);///, RobotContainer.m_ds2);
    limitSwitch = new DigitalInput(9);
  }

  public PulseCorral(int doesntMatter) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.gibShoot, RobotContainer.m_ds);///, RobotContainer.m_ds2);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    numPowerCells = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    boolean scanned = RobotContainer.m_ds.spotted(11.5);
   boolean scannedTwo = !limitSwitch.get();
    SmartDashboard.putBoolean("See ball", scanned);
    SmartDashboard.putBoolean("See ball 2", scannedTwo);
    if(Harvester.isAuton && !RevCor.isReverse)
    {
      if(scanned)
      {
        RobotContainer.gibShoot.runCor();
      }
      else
      {
        try
        {
          Thread.sleep(150);
        }
        catch(Exception e){}
        RobotContainer.gibShoot.stopCor();
      }
    }
    else if(!RevCor.isReverse && !scannedTwo && !IndexCorral.isLimited)
    {
      if(scanned)
      {
        RobotContainer.gibShoot.runCor();
      }
      else
      {
        try
        {
          Thread.sleep(150);
        }
        catch(Exception e){}
        RobotContainer.gibShoot.stopCor();
      }
      //if(scannedTwo)
      {
        //RobotContainer.m_harvester.stopIndex();
      }
    }
    else if(IndexCorral.isLimited && !RevCor.isReverse)
    {
      RobotContainer.gibShoot.stopCor();
    }
  
    //Counts when a ball has been seen by the scanner and then stops beeing seen
    // if(lazerTrip && !scanned)
    // {
    //   RobotContainer.gibShoot.ballAmount++;
    // }
    // lazerTrip = scanned;
    // if(RobotContainer.gibShoot.ballAmount<5 && scanned)
    // {
    //   RobotContainer.gibShoot.runCor();
    // }
    // else
    // {
    //   RobotContainer.gibShoot.stopCor();
    // }
    // //Decreases count when a ball has been seen by the scanner and then stops beeing seen
    // if(!scannedTwo && lazerTripTwo)
    // {
    //   RobotContainer.gibShoot.ballAmount--;
    // }
    // lazerTripTwo = scannedTwo;
    // hiiiiiiiiiii simeon look at meeeeeeeeeeeee i am a computer and my pupils are dilateddddddddddddddddddddddddddd ;)
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.gibShoot.stopCor();
    RobotContainer.m_harvester.stopIndex();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
