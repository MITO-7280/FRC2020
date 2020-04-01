package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Judge extends SubsystemBase{
    public boolean isForward;
    public boolean isHighSpeed;
    public boolean isShooting;
    public boolean isShootReady;
    public boolean isShootingActive;
    public boolean isVisionOK;
    public boolean isVisionOn;

    public Judge(){

    }

    @Override
    public void setDefaultCommand(Command defaultCommand){
        super.setDefaultCommand(defaultCommand);
    }

    @Override
    public void periodic(){
        SmartDashboard.putBoolean("isVisionOn", isVisionOn);
        SmartDashboard.putBoolean("isForward", isForward);
        SmartDashboard.putBoolean("isHighSpeed", isHighSpeed);
    }

    //drive spped
    public void toHighSpeed(){
        isHighSpeed = true;
    }

    //drive speed
    public void toLowSpeed(){
        isHighSpeed = false;
    }

    public void isForwardDetecting(){
        //drive forward or backward
        if (RobotContainer.oi.motionStick.getPOV() == 0){
            isForward = true;
        } else if (RobotContainer.oi.motionStick.getPOV() == 180){
            isForward = false;
        }
    }

    public double deadBand(double value){
        //init joystick output dataa
        if (value >= 0.05){
            return value;
        }

        if (value <= -0.05){
            return value;
        }

        return 0;
    }

    public void shooterDetecting(){
        //prepare to shoot
        if (SmartDashboard.getBoolean("shoot", false)){
            isShootingActive = false;
        } else {
            isShootingActive = true;
        }

        //is ready to shoot
        if (RobotContainer.shooter.shootingMaster.getSelectedSensorVelocity() >= RobotContainer.shooter.shootUnitVelocity && isShooting && isVisionOK){
            isShootReady = true;
        } else {
            isShootReady = false;
        }
    }
}