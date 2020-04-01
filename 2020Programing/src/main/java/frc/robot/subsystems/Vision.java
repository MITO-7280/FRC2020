package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Vision extends SubsystemBase{
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable shoot = inst.getTable("tape");
    NetworkTableEntry tapeDistance = shoot.getEntry("Z");
    NetworkTableEntry tapeAngle = shoot.getEntry("Y");
    
    NetworkTable sender = inst.getTable("isNeeded");
    NetworkTableEntry isNeeded = sender.getEntry("X");

    public int angle;
    public int distance;
    double x = 0;
    
    public Vision(){
        inst.startClientTeam(7280);
        inst.startDSClient();
    }
    
    @Override
    public void setDefaultCommand(Command defaultCommand){
        super.setDefaultCommand(defaultCommand);
    }

    public void getTableData(){
        angle = (int) tapeAngle.getDouble(999.0);
        distance = (int) tapeDistance.getDouble(999.0);

        isNeeded.setDouble(x);
        if (RobotContainer.judge.isVisionOn){
            x = 1.0;
        } else {
            x = 0.0;
        }
    }
}