package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveStraight extends Command {
	private long startTime;
	private long driveTime = 0;

	public AutoDriveStraight(long driveTime) {
		this.driveTime = driveTime;
	}

	protected void initialize() {
		this.startTime = System.currentTimeMillis();
	}

	protected void execute() {
		Robot.drivebase.drive(0.75, 0.75);
	}

	protected boolean isFinished() {
		return System.currentTimeMillis() - startTime > driveTime;
	}

	protected void end() {
		Robot.drivebase.drive(0, 0);
	}
}
