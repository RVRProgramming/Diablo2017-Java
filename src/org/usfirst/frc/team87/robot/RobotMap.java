package org.usfirst.frc.team87.robot;

public class RobotMap {
	//////////////
	// CONTROLS //
	//////////////
	public static final int JOYSTICK = 3; // Joystick
	public static final int GAMEPAD = 2; // Controller
	public static final int LEFTDRIVECONTROL = 1; // Left Thumbstick Y Axis on Controller
	public static final int RIGHTDRIVECONTROL = 5; // Right Thumbstick Y Axis on Controller
	public static final int REVERSE = 8; // Start Button
	public static final int SLOWDOWN = 6; // Right Bumper on Controller
	public static final int WINCH = 1; // Y Axis on Joystick
	public static final int WINCHTOGGLE = 8; // Top Right of the Button Grid on Joystick
	public static final int INTAKEFORWARD = 1; // Trigger on the Joystick
	public static final int INTAKEREVERSE = 11; // Bottom Left of the Button Grid on Joystick
	public static final int OUTPUT = 5; // Top Left Button on Top Panel of Joystick

	////////////
	// MOTORS //
	////////////
	public static final int[] DRIVEMOTORS = { 0, 1, 2, 3 }; // Sparks and CIMs {L1, L2, R1, R2}
	public static final int FUEL_IN = 4; // VictorSP and Snowblower
	public static final int FUEL_OUT = 3; // Spike and Window Motor
	public static final int WINCHL = 1; // TalonSRX and Van Door
	public static final int WINCHR = 2; // TalonSRX and Van Door

	/////////////
	// SENSORS //
	/////////////
	public static final int ENC_l_1 = 2; // Encoders use 2 cables.
	public static final int ENC_l_2 = 3; // In theory the placement of the ports
	public static final int ENC_r_1 = 0; // does not matter. This may need some
	public static final int ENC_r_2 = 1; // extra troubleshooting.
	public static final int ULTRA_IN = 4; // If ultrasonic outputs infinity,
	public static final int ULTRA_OUT = 5; // switch these two port numbers.
	public static final int LIMITTOP = 8;
	public static final int LIMITBOTTOM = 9;
	public static final int LED = 0;

	///////////////
	// Variables //
	///////////////
	public static final double WHEELDIAMETER = 6;
	public static final double INCH_TO_ENC = 256 / (WHEELDIAMETER * Math.PI); //Multiply by inches to get the equivalent in encoder value.
	public static final double SLOWDOWNSPEED = 0.75; //Percentage to slow down to when slowDown button is pressed.
	public static final double WINCHTHRESHOLD = 0.05; //Required minimum on axis to enable winch
	public static final double ANGLETOLERANCE = 0.75;
	public static final double DISTANCETOLERANCE = 1.5 * INCH_TO_ENC;
	public static boolean HOLDINGGEAR = true;
	public static double ULTRATOTAL;
	public static double ULTRATIMES;
	public static int startingSide;
	public static int startingPosition;
	public static int endDestination;
	public static int multiAutonomous;
}
