/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

/**
 * An example command that uses an example subsystem.
 */
public class Drive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  double zValue;//turn
  double yValue;//move
  double throttle;
  double brake;

  /**
   * Creates a new ExampleCommand.
   *
   *  The subsystem used by this command.
   */
  public Drive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.chassis);
    addRequirements(RobotContainer.judge);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    zValue = RobotContainer.judge.deadBand(RobotContainer.oi.motionStick.getRawAxis(0));
    throttle = RobotContainer.judge.deadBand(RobotContainer.oi.motionStick.getRawAxis(3));
    brake = RobotContainer.judge.deadBand(RobotContainer.oi.motionStick.getRawAxis(2));
    yValue = throttle - brake;
    SmartDashboard.putNumber("yValue", yValue);
    SmartDashboard.putNumber("zValue", zValue);
    RobotContainer.chassis.drive(zValue, yValue);
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
}
