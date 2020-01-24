package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Intake;
import frc.robot.commands.ToHighSpeed;
import frc.robot.commands.ToLowSpeed;
import frc.robot.subsystems.ExampleSubsystem;

public class OI {
    
  public Joystick motionStick = new Joystick(Constants.motionJoystick);

  public JoystickButton test = new JoystickButton(motionStick, 1);
  public JoystickButton intake = new JoystickButton(motionStick, 2);
  public JoystickButton toFast = new JoystickButton(motionStick, 3);
  public JoystickButton toSlow = new JoystickButton(motionStick, 4);


    public OI(){
      test.whileHeld(new ExampleCommand());
      intake.whileHeld(new Intake());//intake ball
      toFast.whenPressed(new ToHighSpeed());//drive speed to fast
      toSlow.whenPressed(new ToLowSpeed());//drive speed to slow
    }
}