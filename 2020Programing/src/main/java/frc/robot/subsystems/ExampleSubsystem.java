/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ExampleSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */

  private TalonFX testMotor = new TalonFX(1);
  private CANSparkMax testMotorB = new CANSparkMax(11, MotorType.kBrushless);
  
  public ExampleSubsystem() {
    testMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx,
        Constants.kTimeoutMs);

        testMotor.configFactoryDefault();
    // make sure the sensor gieves the postive value whent the output is positive. 
    testMotor.setSensorPhase(false);

    testMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
    testMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
    testMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
    testMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    testMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    
    testMotor.configAllowableClosedloopError(1, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    
    testMotor.setInverted(false);

    Constants.REVInit(testMotorB, false, 0.1, 0, 0, 1, -1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println(testMotor.getSelectedSensorVelocity()/4096*600/4);
    if (RobotContainer.judge.isShooting){
      testRun(18000);
    } else {
      testRun(0);
    }
  }

  public void testRun(double speedRPM){
    testMotor.config_kF(Constants.kSlotIdx, 0);
    testMotor.config_kP(Constants.kSlotIdx, 0.07);
    testMotor.config_kI(Constants.kSlotIdx, 0);
    testMotor.config_kD(Constants.kSlotIdx, 0.01);
    double speed = speedRPM*4096/600;
    if(speedRPM == 0){
      testMotor.set(ControlMode.PercentOutput, 0);
      testMotorB.set(0);
    } else {
      testMotor.set(ControlMode.Velocity, speed);
      testMotorB.set(100);
    }



  }

}
