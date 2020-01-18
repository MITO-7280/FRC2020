package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    public CANSparkMax intakeMotor = new CANSparkMax(Constants.intakeMotor, MotorType.kBrushless);
    public CANSparkMax transferMotor = new CANSparkMax(Constants.transferMotor, MotorType.kBrushless);

    public Solenoid intakePusher = new Solenoid(Constants.intakePusher);
    public Solenoid balllTransferStopper = new Solenoid (Constants.ballTransferStopper);

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

    public void intake(double _percent){
        intakeMotor.set(_percent);
    }

    public void intakeOut(){
        intakePusher.set(true);
    }

    public void intakeIn(){
        intakePusher.set(false);
    }
}