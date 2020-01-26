package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class ToLowSpeed extends CommandBase{
    
    public ToLowSpeed() {
        addRequirements(RobotContainer.chassis);
    }

    @Override
    public void execute(){
        RobotContainer.chassis.toLowSpeed();
    }

    @Override
    public void end(boolean interrupted){

    }

    @Override
    public boolean isFinished(){
        return true;
    }
}