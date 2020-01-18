package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{
    //set can device 
    public TalonFX shootingMaster = new TalonFX(Constants.shootingMaster);
    public TalonFX shootingSlave = new TalonFX(Constants.shootingSlave);

    //variablies
    public double shootSpeed;
    public Shooter(){
        Constants.TalonInit(shootingMaster, 40, false);
        Constants.TalonInit(shootingSlave, 40, false);

        shootingSlave.follow(shootingMaster);

        Constants.setFalconPID(shootingMaster, 0, 0, 0, 0);
    }

    @Override
    public void setDefaultCommand(Command defaultCommand){
        super.setDefaultCommand(defaultCommand);
    }

    @Override
    public void periodic(){

    }

    public void shoot(){
        shootingMaster.set(ControlMode.Velocity, shootSpeed);
    }

    public void stopShoot(){
        shootingMaster.set(ControlMode.Velocity, shootSpeed);
    }
}