/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.*;

import org.w3c.dom.events.MouseEvent;


public class FieldMapping extends CommandBase implements MouseListener{
  /**
   * Creates a new FieldMapping.
   */

  class Square
  {
    int x;
    int y;
    int width;
    int height;
    JFrame f;

    Square(int x, int y, int width, int height, JFrame f){
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.f = f;
    }

    void move(int x, int y)
    {
      f.getGraphics().clearRect(x, y, width, height);
      this.x = x-width/2;
      this.y = y-height/2;
    }

    void paint()
    {
      f.getGraphics();
      f.getGraphics().drawRect(x, y, width, height);
    }
  }
  
  Square robot;
  // JFrame f;
  public FieldMapping() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_driveTrain);
    JFrame f = new JFrame("Field Map"); 
    robot = new Square(175, 175, 50, 50, f);   
    f.setSize(400,400);
    f.setLayout(null);
    f.setVisible(true);
    f.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        System.out.println(x+","+y);//these co-ords are relative to the component
        robot.move(x, y);
      }

      @Override
      public void mouseEntered(java.awt.event.MouseEvent arg0) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mouseExited(java.awt.event.MouseEvent arg0) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mousePressed(java.awt.event.MouseEvent arg0) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mouseReleased(java.awt.event.MouseEvent arg0) {
        // TODO Auto-generated method stub

      }
  });
    robot.paint();
  }

  @Override
  public void mouseClicked(java.awt.event.MouseEvent e) {
    int x=e.getX();
    int y=e.getY();
    System.out.println(x+","+y);//these co-ords are relative to the component
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    robot.paint();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }


  @Override
  public void mouseEntered(java.awt.event.MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(java.awt.event.MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(java.awt.event.MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(java.awt.event.MouseEvent arg0) {
    // TODO Auto-generated method stub

  }
}
