package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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
    public CANSparkMax shootAdjuster = new CANSparkMax(Constants.shootAdjuster, MotorType.kBrushless);


    public double shootUnitVelocity = Constants.shootVelocity*4096/600;
    //variablies
    public Shooter(){
        Constants.TalonInit(shootingMaster, 40, false);
        Constants.TalonInit(shootingSlave, 40, false);
        Constants.REVInit(shootAdjuster, false, 0.1, 0, 0, 1, -1);

        shootingSlave.follow(shootingMaster);

        Constants.setFalconPID(shootingMaster, 0, 0, 0, 0);
    }

    @Override
    public void setDefaultCommand(Command defaultCommand){
        super.setDefaultCommand(defaultCommand);
    }

    @Override
    public void periodic(){
        //active flywheel
        if (RobotContainer.judge.isShootingActive){
            shootingMaster.set(ControlMode.Velocity, shootUnitVelocity);
        } else {
            shootingMaster.set(ControlMode.PercentOutput, 0);
        }

    }

    public void setAngle(){
        if (Constants.getSparkPosition(shootAdjuster) >= 5000 || Constants.getSparkPosition(shootAdjuster) <= -5000){
            shootAdjuster.set(0);
        } else {
            if(RobotContainer.vision.angle >= 5){
                shootAdjuster.set(50);
                RobotContainer.judge.isVisionOK = false;
            } else if (RobotContainer.vision.angle <= -5){
                shootAdjuster.set(-50);
                RobotContainer.judge.isVisionOK = false;
            } else {
                shootAdjuster.set(0);
                RobotContainer.judge.isVisionOK = true;
            }
        }
    }

    public void setStop(){
        shootAdjuster.set(0);
    }
}
