/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.MakeReadyShoot;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DoAuton extends SequentialCommandGroup {

  /**
   * Creates a new DoAuton.
   */

  public DoAuton(boolean onTheWall) {
   super(onWall(onTheWall));
  }

  public static Command onWall(boolean onTheWall)
  {
    if(!onTheWall)
    {
      return (new SetYawZero().andThen(new DriveStraight(-50, 0), new AutonLime(), new MakeReadyShoot(), new DriveStraight(MakeReadyShoot.whereToMove, MakeReadyShoot.yawOfTarget), new AutonLime()/*, new Shoot()*/));
    }
    return (new SetYawZero().andThen(new AutonHarvest(true, true), new DriveStraight(83, 0), new ShootAndCorral(2.67), new AutonHarvest(true, true), new DriveStraight(72, 0), new WaitForSeconds(1)/*,new Shoot()*/, new DriveStraight(-72, 0), new ShootAndCorral(2.67)/*,new Shoot()*/, new AutonHarvest(false, false)));
  }

}
