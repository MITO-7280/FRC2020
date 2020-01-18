package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class ToHighSpeed extends CommandBase{

    public ToHighSpeed(Chassis subsystem){
        addRequirements(subsystem);
    }

    @Override 
    public void execute() {
        RobotContainer.chassis.toHighSpeed();
    }
}