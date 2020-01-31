package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {
    public CANSparkMax intakeMotor = new CANSparkMax(Constants.intakeMotor, MotorType.kBrushless);
    public CANSparkMax transferMotor = new CANSparkMax(Constants.transferMotor, MotorType.kBrushless);

    public Solenoid intakePusher = new Solenoid(Constants.intakePusher);
    public Solenoid ballTransferStopper = new Solenoid (Constants.ballTransferStopper);

    public Intake(){
        Constants.REVInit(intakeMotor, true, 0.1, 0, 0, 1, -1);
    }

    @Override
    public void setDefaultCommand(Command defaultCommand){
        super.setDefaultCommand(defaultCommand);
    }

    @Override
    public void periodic(){
    }

    public void intake(double speed){
        //intake ball
        intakeMotor.set(speed);

        //stop transfer ball when stop intake
        if (speed == 0){
            transferMotor.set(0);
        } else {
            transferMotor.set(500);
        }
    }

    public void intakeOut(){
        intakePusher.set(true);
    }

    public void intakeIn(){
        intakePusher.set(false);
    }

    public void shootball(){
        transferMotor.set(500);
        if (RobotContainer.judge.isShootReady){
            ballTransferStopper.set(false);
        } else {
            ballTransferStopper.set(true);
        }
    }
}