package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ToHighSpeed extends CommandBase{

    public ToHighSpeed(){
        addRequirements(RobotContainer.judge);
    }

    @Override 
    public void execute() {
        RobotContainer.judge.toHighSpeed();
    }

    @Override
    public void end (boolean interrupted){

    }

    @Override
    public boolean isFinished(){
        return true;
    }
}