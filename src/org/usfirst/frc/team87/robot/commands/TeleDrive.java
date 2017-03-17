package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;
import org.usfirst.frc.team87.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class TeleDrive extends Command {

	public TeleDrive() {
		requires(Robot.drivebase);
	}

	protected void execute() {
		double leftSpeed = Robot.oi.getLeftSpeed();
		double rightSpeed = Robot.oi.getRightSpeed();
		if (Robot.oi.getSlowDown()) {
			leftSpeed *= RobotMap.SLOWDOWNSPEED;
			rightSpeed *= RobotMap.SLOWDOWNSPEED;
		}
		if (Robot.oi.getBackwards()) {
			Robot.drivebase.drive(-rightSpeed, -leftSpeed);
		} else {
			Robot.drivebase.drive(leftSpeed, rightSpeed);
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void interrupted() {
		Robot.drivebase.drive(0, 0);
	}
}
