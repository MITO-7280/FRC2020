package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class GetTableData extends CommandBase{
    public GetTableData(){
        addRequirements(RobotContainer.vision);
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        RobotContainer.vision.getTableData();
    }

    @Override
    public void end(boolean interrupted){

    }

    @Override
    public boolean isFinished(){
        return false;
    }
}