package org.usfirst.frc.team87.robot;

import org.usfirst.frc.team87.robot.commands.TeleClimb;
import org.usfirst.frc.team87.robot.commands.TeleIntake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public static Joystick joystick;
	public static Joystick gamepad;
	public static boolean backwardsButton = false;
	public static boolean backwardsToggle = false;

	public OI() {
		Joystick joystick = new Joystick(RobotMap.JOYSTICK);
		Joystick gamepad = new Joystick(RobotMap.GAMEPAD);
		// Button slowDown = new JoystickButton(gamepad, RobotMap.SLOWDOWN);
		Button winchToggle = new JoystickButton(joystick, RobotMap.WINCHTOGGLE);
		Button intakeForward = new JoystickButton(joystick, RobotMap.INTAKEFORWARD);
		Button intakeReverse = new JoystickButton(joystick, RobotMap.INTAKEREVERSE);
		// Button output = new JoystickButton(joystick, RobotMap.OUTPUT);
		winchToggle.toggleWhenPressed(new TeleClimb());
		intakeForward.whileHeld(new TeleIntake(1));
		intakeReverse.whileHeld(new TeleIntake(-1));
		// output.whenPressed(new output());
	}

	public double getLeftSpeed() {
		return gamepad.getRawAxis(RobotMap.LEFTDRIVECONTROL);
	}

	public double getRightSpeed() {
		return gamepad.getRawAxis(RobotMap.RIGHTDRIVECONTROL);
	}

	public static void backwardsCheck() {
		if (backwardsButton && !gamepad.getRawButton(RobotMap.REVERSE)) {
			backwardsButton = false;
		} else if (!backwardsButton && gamepad.getRawButton(RobotMap.REVERSE)) {
			backwardsButton = true;
			backwardsToggle = !backwardsToggle;
		}
	}

	public boolean getBackwards() {
		return backwardsToggle;
	}

	public boolean getSlowDown() {
		return gamepad.getRawButton(RobotMap.SLOWDOWN);
	}

	public double getWinchSpeed() {
		return joystick.getRawAxis(RobotMap.WINCH);
	}
	
	public boolean getOutput(){
		return joystick.getRawButton(RobotMap.OUTPUT);
	}

}
