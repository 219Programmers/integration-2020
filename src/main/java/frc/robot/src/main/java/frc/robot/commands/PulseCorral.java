/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class PulseCorral extends CommandBase {
  /**
   * Creates a new PulseCorral.
   */
  int numPowerCells; 
  boolean lazerTrip, lazerTripTwo;
  public PulseCorral() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.gibShoot, RobotContainer.m_ds, RobotContainer.m_ds2);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    numPowerCells = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean scanned = RobotContainer.m_ds.spotted(11);
    boolean scannedTwo = RobotContainer.m_ds2.spotted(11);
    SmartDashboard.putBoolean("See ball", scanned);
    if(!RevCor.isReverse)
    {
    //Counts when a ball has been seen by the scanner and then stops beeing seen
    if(scanned)
    {
      RobotContainer.gibShoot.runCor();
    }
    else{
      try{
      Thread.sleep(250);
      }
      catch(Exception e){}
      RobotContainer.gibShoot.stopCor();
    }
  }
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
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
