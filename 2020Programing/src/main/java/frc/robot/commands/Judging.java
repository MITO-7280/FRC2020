package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Judging extends CommandBase{
    public Judging(){
        addRequirements(RobotContainer.judge);
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        RobotContainer.judge.shooterDetecting();
        RobotContainer.judge.isForwardDetecting();
    }

    @Override
    public void end(boolean interrupted){

    }

    @Override
    public boolean isFinished(){
        return false;
    }
}