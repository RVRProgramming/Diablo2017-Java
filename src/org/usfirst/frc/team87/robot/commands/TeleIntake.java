package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleIntake extends Command {
	double speed;

	public TeleIntake(double speed) {
		requires(Robot.intake);
		this.speed = speed;
	}

	protected void execute() {
		Robot.intake.intake(speed);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void interrupted() {
		Robot.intake.intake(0);
	}
}
