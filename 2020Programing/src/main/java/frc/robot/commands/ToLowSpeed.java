package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ToLowSpeed extends CommandBase{
    
    public ToLowSpeed() {
        addRequirements(RobotContainer.judge);
    }

    @Override
    public void execute(){
        RobotContainer.judge.toLowSpeed();
    }

    @Override
    public void end(boolean interrupted){

    }

    @Override
    public boolean isFinished(){
        return true;
    }
}