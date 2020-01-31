package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase{
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable shoot = inst.getTable("tape");
    NetworkTableEntry tapePosition = shoot.getEntry("Y");
    
    NetworkTable sender = inst.getTable("isNeeded");
    NetworkTableEntry isNeeded = sender.getEntry("X");
    
    public Vision(){
        inst.startClientTeam(7280);
        inst.startDSClient();
    }
    
    @Override
    public void setDefaultCommand(Command defaultCommand){
        super.setDefaultCommand(defaultCommand);
    }

    public void getTableData(){

    }
}