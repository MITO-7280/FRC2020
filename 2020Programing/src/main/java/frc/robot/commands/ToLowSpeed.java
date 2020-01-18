package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class ToLowSpeed extends CommandBase{
    
    public ToLowSpeed(Chassis subsystem) {
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        RobotContainer.chassis.toLowSpeed();
    }
}