package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Intake extends CommandBase{

    public Intake(){
        addRequirements(RobotContainer.intake);
    }

    @Override
    public void execute(){
        RobotContainer.intake.intakeOut();
        RobotContainer.intake.intake(500);
        RobotContainer.intake.ballTransfer();
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.intake.intakeIn();
        RobotContainer.intake.intake(0);
        RobotContainer.intake.ballTransferStop();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}