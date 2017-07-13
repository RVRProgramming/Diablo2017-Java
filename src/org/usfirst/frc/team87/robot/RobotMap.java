package org.usfirst.frc.team87.robot;

public class RobotMap {
	//////////////
	// CONTROLS //
	//////////////
	public static final int JOYSTICK = 0; // Joystick
	public static final int GAMEPAD = 1; // Controller
	public static final int LEFTDRIVECONTROL = 1; // Left Thumbstick Y Axis on Controller
	public static final int RIGHTDRIVECONTROL = 5; // Right Thumbstick Y Axis on Controller
	public static final int REVERSE = 8; // Start Button
	public static final int SLOWDOWN = 6; // Right Bumper on Controller
	public static final int WINCH = 1; // Y Axis on Joystick
	public static final int WINCHTOGGLE = 8; // Top Right of the Button Grid on Joystick

	////////////
	// MOTORS //
	////////////
	public static final int[] DRIVEMOTORS = { 0, 1, 2, 3 }; // Sparks and CIMs {L1, L2, R1, R2}
	public static final int WINCHL = 1; // TalonSRX and Van Door
	public static final int WINCHR = 2; // TalonSRX and Van Door

	/////////////
	// SENSORS //
	/////////////
	public static final int LED = 0;

	///////////////
	// Variables //
	///////////////
	public static final double SLOWDOWNSPEED = 0.75; //Percentage to slow down to when slowDown button is pressed.
	public static final double WINCHTHRESHOLD = 0.05; //Required minimum on axis to enable winch
}
