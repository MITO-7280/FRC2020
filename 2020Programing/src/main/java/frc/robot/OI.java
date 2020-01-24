package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Intake;
import frc.robot.subsystems.ExampleSubsystem;

public class OI {
    
  public Joystick motionStick = new Joystick(Constants.motionJoystick);

  public JoystickButton test = new JoystickButton(motionStick, 1);
  public JoystickButton intake = new JoystickButton(motionStick, 2);


    public OI(){
        test.whenHeld(new ExampleCommand());

        intake.whenHeld(new Intake());
    }
}