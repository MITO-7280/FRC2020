
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Chassis extends SubsystemBase {
    //set can devices 
    public TalonFX leftMasterMotor = new TalonFX( Constants.leftMasterMotor);
    public TalonFX leftSlaveMotor = new TalonFX(Constants.leftSlaveMotor);
    public TalonFX rightMasterMotor = new TalonFX(Constants.rightMasterMotor);
    public TalonFX rightSlaveMotor = new TalonFX(Constants.rightSlaveMotor);

    public Solenoid driveModeSwitcher = new Solenoid(Constants.driveModeSwitcher);

    //set variables
    public double leftSpeed, rightSpeed, leftPosition, rightPosition;
    public Chassis(){
        //motor init 
        Constants.TalonInit(leftMasterMotor, 40, false);
        Constants.TalonInit(leftSlaveMotor, 40, false);
        Constants.TalonInit(rightMasterMotor, 40, false);
        Constants.TalonInit(rightSlaveMotor, 40, false);

        leftSlaveMotor.follow(leftMasterMotor);
        rightSlaveMotor.follow(rightSlaveMotor);

        leftMasterMotor.setNeutralMode(NeutralMode.Brake);
        leftSlaveMotor.setNeutralMode(NeutralMode.Brake);
        rightMasterMotor.setNeutralMode(NeutralMode.Brake);
        rightSlaveMotor.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void setDefaultCommand(Command defaultCommand) {
        super.setDefaultCommand(defaultCommand);
    }
    @Override
    public void periodic(){
        //show Velocity data
        SmartDashboard.putNumber("left Velocity RPM", leftMasterMotor.getSelectedSensorVelocity()*600/4096);
        SmartDashboard.putNumber("right Velocity RPM", rightMasterMotor.getSelectedSensorVelocity()*600/4096);

        //show position data
        SmartDashboard.putNumber("left Position rotation", leftMasterMotor.getSelectedSensorPosition()*600/4096);
        SmartDashboard.putNumber("right Positon rotation", rightMasterMotor.getSelectedSensorPosition()*600/4096);

        //show drive direction data
        SmartDashboard.putBoolean("isForward", RobotContainer.judge.isForward);

        //show drive speed data 
        SmartDashboard.putBoolean("isHighSpeed", RobotContainer.judge.isHighSpeed);




        //active solenoid to fast of slow
        driveModeSwitcher.set(RobotContainer.judge.isHighSpeed);
    }

    public void drive(double xValue, double yValue){
        //set drive speed and drive with velocity PID
        configVelocityPID();
        if (RobotContainer.judge.isForward){
            if (RobotContainer.judge.isHighSpeed){
                leftSpeed = (yValue + xValue)* Constants.highSpeedConstant;
                rightSpeed = (yValue - xValue)* Constants.highSpeedConstant;
            } else {
                leftSpeed = (yValue + xValue)* Constants.lowSpeedConstant;
                rightSpeed = (yValue - xValue)* Constants.lowSpeedConstant;
            }
        } else {
            if (RobotContainer.judge.isHighSpeed){
                leftSpeed = -(yValue + xValue)* Constants.highSpeedConstant;
                rightSpeed = -(yValue - xValue)* Constants.highSpeedConstant;
            } else {
                leftSpeed = -(yValue + xValue)* Constants.lowSpeedConstant;
                rightSpeed = -(yValue - xValue)* Constants.lowSpeedConstant;
            }
        }
        speedDrive();
    }

    public void speedDrive(){
        //drive with velocity PID
        leftMasterMotor.set(ControlMode.Velocity, leftSpeed);
        rightMasterMotor.set(ControlMode.Velocity, rightSpeed);
    }

    public void configVelocityPID(){
        Constants.setFalconPID(leftMasterMotor, 0, 0.1, 0, 0);
        Constants.setFalconPID(rightMasterMotor, 0, 0.1, 0, 0);
    }

    public void configPositionPID(){
        Constants.setFalconPID(leftMasterMotor, 0, 0, 0, 0);
        Constants.setFalconPID(rightMasterMotor, 0, 0, 0, 0);
    }



}