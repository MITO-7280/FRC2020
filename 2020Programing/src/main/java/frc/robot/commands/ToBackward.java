package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ToBackward extends CommandBase{
    
    public ToBackward(){
        addRequirements(RobotContainer.chassis);
    }

    @Override
    public void execute(){
        RobotContainer.chassis.toBackward();
    }
}