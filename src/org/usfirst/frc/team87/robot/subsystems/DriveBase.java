package org.usfirst.frc.team87.robot.subsystems;

import org.usfirst.frc.team87.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem {
	private RobotDrive diabloDrive;

	public DriveBase() {
		Spark[] motors = new Spark[4];
		for (int i = 0; i < motors.length; i++) {
			motors[i] = new Spark(RobotMap.DRIVEMOTORS[i]);
			motors[i].enableDeadbandElimination(true);
		}
		diabloDrive = new RobotDrive(motors[0], motors[1], motors[2], motors[3]);
	}

	public void drive(double left, double right) {
		diabloDrive.tankDrive(-left, -right);
	}

	public void initDefaultCommand() {
	}
}
