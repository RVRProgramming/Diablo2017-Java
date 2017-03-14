package org.usfirst.frc.team87.robot.subsystems;

import org.usfirst.frc.team87.robot.Robot;
import org.usfirst.frc.team87.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem {
	static Spark driveR1;
	static Spark driveR2;
	static Spark driveL1;
	static Spark driveL2;
	RobotDrive diabloDrive;

	public DriveBase() {
		driveR1 = new Spark(RobotMap.DRIVE_R1);
		driveR1.enableDeadbandElimination(true);
		driveR2 = new Spark(RobotMap.DRIVE_R2);
		driveR2.enableDeadbandElimination(true);
		driveL1 = new Spark(RobotMap.DRIVE_L1);
		driveL1.enableDeadbandElimination(true);
		driveL2 = new Spark(RobotMap.DRIVE_L2);
		driveL2.enableDeadbandElimination(true);
		diabloDrive = new RobotDrive(driveL1, driveR1, driveL2, driveR2);
	}

	public void drive(double left, double right) {
		diabloDrive.tankDrive(-left, right);
	}

	public void initDefaultCommand() {

	}
}
