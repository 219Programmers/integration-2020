/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SinglePneumatics extends SubsystemBase {
  private final Solenoid valveOpenSide;

        /**
         * @param port DIO port wired to valve. Assumes PCM with CAN Id 0.
         */
        public SinglePneumatics(int port)
        {
                valveOpenSide = new Solenoid(port);
        }

        /**
         * @param pcmCanId PCM CAN Id number.
         * @param port DIO port wired to valve.
         */
        public SinglePneumatics()
        {
                valveOpenSide = new Solenoid(Constants.PCMCAN, Constants.SINGLEPNEUM);
        }

        /**
         * Release resources.
         */
        // public void dispose()
        // {
        //         Close();
                
        //         valveOpenSide.free();
        // }

        /**
         * Open the valve.
         */
        public void Open()
        {
                valveOpenSide.set(true);
        }

        /**
         * Close the valve.
         */
        public void Close()
        {
                valveOpenSide.set(false);
        }
}
