package org.usfirst.frc.team87.robot;

import org.usfirst.frc.team87.robot.commands.TeleClimb;
import org.usfirst.frc.team87.robot.commands.TeleIntake;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick joystick;
	public Joystick gamepad;
	public ADXRS450_Gyro gyro;
	public Encoder leftEncoder;
	public Encoder rightEncoder;
	public static boolean backwardsButton = false;
	public static boolean backwardsToggle = false;

	public OI() {
		Joystick joystick = new Joystick(RobotMap.JOYSTICK);
		Joystick gamepad = new Joystick(RobotMap.GAMEPAD);
		gyro = new ADXRS450_Gyro();
		leftEncoder = new Encoder(RobotMap.ENC_l_1, RobotMap.ENC_l_2, false, Encoder.EncodingType.k4X);
		rightEncoder = new Encoder(RobotMap.ENC_r_1, RobotMap.ENC_r_2, false, Encoder.EncodingType.k4X);

		Button winchToggle = new JoystickButton(joystick, RobotMap.WINCHTOGGLE);
		Button intakeForward = new JoystickButton(joystick, RobotMap.INTAKEFORWARD);
		Button intakeReverse = new JoystickButton(joystick, RobotMap.INTAKEREVERSE);

		winchToggle.toggleWhenPressed(new TeleClimb());
		intakeForward.whileHeld(new TeleIntake(1));
		intakeReverse.whileHeld(new TeleIntake(-1));

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

	public void initGyro() {
		gyro.calibrate();
	}

	public void resetGyro() {
		gyro.reset();
	}

	public double getGyro() {
		return gyro.getAngle();
	}

	public void resetEncoder() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public int getLeftEncoder() {
		return leftEncoder.get();
	}

	public int getRightEncoder() {
		return rightEncoder.get();
	}

}
