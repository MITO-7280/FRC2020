package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase{
    //set can device 
    public TalonFX shootingMaster = new TalonFX(Constants.shootingMaster);
    public TalonFX shootingSlave = new TalonFX(Constants.shootingSlave);

    public boolean isShooting = false;
    public boolean isReady = false;

    public double shootUnitVelocity = Constants.shootVelocity*4096/600;
    //variablies
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
        if (RobotContainer.oi.motionStick.getTwist() >= 0.5){
            isShooting = true;
        } else if (RobotContainer.oi.motionStick.getTwist() <= 0.5){
            isShooting = false;
        }

        if (isShooting){
            shootingMaster.set(ControlMode.Velocity, shootUnitVelocity);
        }

        if (shootingMaster.getSelectedSensorVelocity() >= shootUnitVelocity){
            isReady = true;
        } else {
            isReady = false;
        }
    }

    public void shoot(double shootSpeed){
        shootingMaster.set(ControlMode.Velocity, shootSpeed);
    }

}