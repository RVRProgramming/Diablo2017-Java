package org.usfirst.frc.team87.robot.subsystems;

import org.usfirst.frc.team87.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem {
	private ADXRS450_Gyro gyro;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private RobotDrive diabloDrive;

	public DriveBase() {
		Spark[] motors = new Spark[4];
		for (int i = 0; i < motors.length; i++) {
			motors[i] = new Spark(RobotMap.DRIVEMOTORS[i]);
			motors[i].enableDeadbandElimination(true);
		}
		diabloDrive = new RobotDrive(motors[0], motors[1], motors[2], motors[3]);
		gyro = new ADXRS450_Gyro();
		//leftEncoder = new Encoder(RobotMap.ENC_l_1, RobotMap.ENC_l_2, false, Encoder.EncodingType.k4X);
		//rightEncoder = new Encoder(RobotMap.ENC_r_1, RobotMap.ENC_r_2, false, Encoder.EncodingType.k4X);
	}

	public void drive(double left, double right) {
		diabloDrive.tankDrive(-left, -right);
	}

	public void initDefaultCommand() {
	}
}
