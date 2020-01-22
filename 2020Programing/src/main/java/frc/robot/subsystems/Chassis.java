
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
    public TalonFX leftMasterMotor = new TalonFX(Constants.leftMasterMotor);
    public TalonFX leftSlaveMotor = new TalonFX(Constants.leftSlaveMotor);
    public TalonFX rightMasterMotor = new TalonFX(Constants.rightMasterMotor);
    public TalonFX rightSlaveMotor = new TalonFX(Constants.rightSlaveMotor);

    public Solenoid driveModeSwitcher = new Solenoid(Constants.driveModeSwitcher);

    //set other sources
    public Constants constants = new Constants();

    //set variables
    public double leftSpeed, rightSpeed, leftPosition, rightPosition;
    public boolean isHighSpeed = true;
    public boolean isForward = true;
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
        SmartDashboard.putNumber("left Velocity", leftMasterMotor.getSelectedSensorPosition());
        SmartDashboard.putNumber("right Velocity", rightMasterMotor.getSelectedSensorVelocity());
        SmartDashboard.putNumber("left Position", leftMasterMotor.getSelectedSensorPosition());
        SmartDashboard.putNumber("right Positon", rightMasterMotor.getSelectedSensorPosition());
        if (RobotContainer.oi.motionStick.getPOV() == 0){
            isForward = true;
        } else if (RobotContainer.oi.motionStick.getPOV() == 180){
            isForward = false;
        }
    }

    public void drive(double xValue, double yValue){
        configVelocityPID();
        if (isForward){
            if (isHighSpeed){
                leftSpeed = (yValue + xValue)* Constants.highSpeedConstant;
                rightSpeed = (yValue - xValue)* Constants.highSpeedConstant;
            } else {
                leftSpeed = (yValue + xValue)* Constants.lowSpeedConstant;
                rightSpeed = (yValue - xValue)* Constants.lowSpeedConstant;
            }
        } else {
            if (isHighSpeed){
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
        leftMasterMotor.set(ControlMode.Velocity, leftSpeed);
        rightMasterMotor.set(ControlMode.Velocity, rightSpeed);
    }

    public void toHighSpeed(){
        isHighSpeed = true;
        driveModeSwitcher.set(true);
    }

    public void toLowSpeed(){
        isHighSpeed = false;
        driveModeSwitcher.set(false);
    }

    public void toForward(){
        isForward = true;
    }

    public void toBackward(){
        isForward = false;
    }

    public void configVelocityPID(){
        Constants.setFalconPID(leftMasterMotor, 0, 0, 0, 0);
        Constants.setFalconPID(rightMasterMotor, 0, 0, 0, 0);
    }

    public void configPositionPID(){
        Constants.setFalconPID(leftMasterMotor, 0, 0, 0, 0);
        Constants.setFalconPID(rightMasterMotor, 0, 0, 0, 0);
    }

}