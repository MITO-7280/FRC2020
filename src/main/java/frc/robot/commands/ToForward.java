package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ToForward extends CommandBase{

    public ToForward(){
        addRequirements(RobotContainer.chassis);
    }

    @Override
    public void execute(){
        RobotContainer.chassis.toForward();
    }
}