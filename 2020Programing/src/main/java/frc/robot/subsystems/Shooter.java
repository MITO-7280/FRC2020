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
        //when to prepare for shooting
        if (RobotContainer.oi.motionStick.getRawAxis(3) >= 0.5){
            isShooting = false;
        } else if (RobotContainer.oi.motionStick.getRawAxis(3) <= 0.5){
            isShooting = true;
        }
        SmartDashboard.putBoolean("isShooting", isShooting);

        //active flywheel
        if (isShooting){
            shootingMaster.set(ControlMode.Velocity, shootUnitVelocity);
        } else {
            shootingMaster.set(ControlMode.PercentOutput, 0);
        }

        //when is ready to shoot
        if (shootingMaster.getSelectedSensorVelocity() >= shootUnitVelocity){
            isReady = true;
        } else {
            isReady = false;
        }
        SmartDashboard.putBoolean("isReady", isReady);
    }
}