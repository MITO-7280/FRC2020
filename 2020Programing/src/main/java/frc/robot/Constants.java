/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class 
Constants {
    //robotMap

    //motors
    //chassis
    public static final int leftMasterMotor = 1;
    public static final int leftSlaveMotor = 2;
    public static final int rightMasterMotor = 3;
    public static final int rightSlaveMotor = 4;

    //shooting
    public static final int shootingMaster = 5;
    public static final int shootingSlave = 6;

    //intake 
    public static final int transferMotor = 7;
    public static final int intakeMotor = 8;

    //testing
    public static final int testMotor = 9;

    //solenoids 
    //chassis
    public static final int driveModeSwitcher = 1;

    //intake
    public static final int ballTransferStopper = 2;
    public static final int intakePusher = 3;

    //constants
    //basics 
    public static final int kTimeoutMs = 30;

    public static final int kPIDLoopIdx = 0;

    public static final int kSlotIdx = 0;

    //chassis
    public static final int highSpeedConstant = 3000;
    public static final int lowSpeedConstant = 1500;

    //Joystick
    public static final int motionJoystick = 0;

    public Constants(){

    }

    public static void TalonInit(TalonFX _talon, int _peakOutput, boolean isInverted){
    
        // set up TalonSRX and closed loop
        // select an encoder and set it
        _talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
    
        _talon.configFactoryDefault();
        // make sure the sensor gieves the postive value whent the output is positive. 
        _talon.setSensorPhase(true);
    
        _talon.configNominalOutputForward(0, kTimeoutMs);
        _talon.configNominalOutputReverse(0, kTimeoutMs);
        _talon.configPeakOutputForward(1, kTimeoutMs);
        _talon.configPeakOutputReverse(-1, kTimeoutMs);
    
        _talon.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
        
        _talon.configAllowableClosedloopError(1, kPIDLoopIdx, kTimeoutMs);
        
        _talon.configClosedLoopPeakOutput(kSlotIdx, _peakOutput, kTimeoutMs);

        _talon.setInverted(isInverted);
    }

    public static void setFalconPID(TalonFX _talon, double kF, double kP, double kI, double kD){
        _talon.config_kF(kSlotIdx, kF);
        _talon.config_kP(kSlotIdx, kP);
        _talon.config_kI(kSlotIdx, kI);
        _talon.config_kD(kSlotIdx, kD);
    }

    public static void REVInit(CANSparkMax _spark, boolean isInverted, double kP, double kI, double kD, int _max, int _mini){
        CANEncoder _encoder = _spark.getEncoder();
        _spark.restoreFactoryDefaults();
        CANPIDController _pidController = _spark.getPIDController();
        _pidController.setFeedbackDevice(_encoder);
        _spark.setInverted(isInverted);
        _pidController.setP(kP);
        _pidController.setI(kI);
        _pidController.setD(kD);
        _pidController.setIZone(0);
        _pidController.setFF(0);
        _pidController.setOutputRange(_mini, _max);
    }//放到每个需要的程序里面
}
