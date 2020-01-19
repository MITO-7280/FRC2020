package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Shoot extends CommandBase{
    public Shoot(){
        addRequirements(RobotContainer.shooter);        
    }

    @Override
    public void execute() {
        RobotContainer.shooter.shoot(5000);
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.shooter.shoot(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}