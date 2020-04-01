package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Judge;
import frc.robot.subsystems.Shooter;

public class ShootBall extends CommandBase{
    public ShootBall(){
        addRequirements(RobotContainer.shooter);
        addRequirements(RobotContainer.intake);
        addRequirements(RobotContainer.judge);
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        RobotContainer.judge.isVisionOn = true;
        RobotContainer.shooter.setAngle();
        RobotContainer.judge.isShooting = true;
        RobotContainer.intake.ballTransfer();
    }
    
    @Override
    public void end (boolean interrupted){
        RobotContainer.judge.isVisionOn = false;
        RobotContainer.judge.isShooting = false;
        RobotContainer.intake.ballTransferStop();
        RobotContainer.shooter.setStop();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}