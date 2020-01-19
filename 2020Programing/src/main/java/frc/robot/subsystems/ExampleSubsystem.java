/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ExampleSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  private TalonFX testMotor = new TalonFX(10);
  private VictorSPX testMotor2 = new VictorSPX(0);
  public ExampleSubsystem() {
    testMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx,
        Constants.kTimeoutMs);

        testMotor.configFactoryDefault();
    // make sure the sensor gieves the postive value whent the output is positive. 
    testMotor.setSensorPhase(true);

    testMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
    testMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
    testMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
    testMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    testMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    
    testMotor.configAllowableClosedloopError(1, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    

    testMotor.setInverted(false);

    testMotor.config_kF(Constants.kSlotIdx, 0);
    testMotor.config_kP(Constants.kSlotIdx, 0);
    testMotor.config_kI(Constants.kSlotIdx, 0);
    testMotor.config_kD(Constants.kSlotIdx, 0);

    testMotor.setNeutralMode(NeutralMode.Coast);

    testMotor2.follow(testMotor);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println(testMotor.getSelectedSensorVelocity());
  }

  public void testRun(double speed){
    testMotor.set(ControlMode.PercentOutput, speed);
    // testMotor2.set(ControlMode.PercentOutput, speed);
  }

/**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
}
