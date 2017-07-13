package org.usfirst.frc.team87.robot;

import org.usfirst.frc.team87.robot.commands.TeleClimb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	private Joystick joystick;
	private Joystick gamepad;
	private boolean backwardsButton = false;
	private boolean backwardsToggle = false;

	public OI() {
		joystick = new Joystick(RobotMap.JOYSTICK);
		gamepad = new Joystick(RobotMap.GAMEPAD);
		Button winchToggle = new JoystickButton(joystick, RobotMap.WINCHTOGGLE);
		Button intakeForward = new JoystickButton(joystick, RobotMap.INTAKEFORWARD);
		Button intakeReverse = new JoystickButton(joystick, RobotMap.INTAKEREVERSE);

		winchToggle.toggleWhenPressed(new TeleClimb());

	}

	public double getLeftSpeed() {
		return gamepad.getRawAxis(RobotMap.LEFTDRIVECONTROL);
	}

	public double getRightSpeed() {
		return gamepad.getRawAxis(RobotMap.RIGHTDRIVECONTROL);
	}

	public void backwardsCheck() {
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

	public boolean getOutput() {
		return joystick.getRawButton(RobotMap.OUTPUT);
	}
	
	public int getPOV(){
		return gamepad.getPOV();
	}
}
