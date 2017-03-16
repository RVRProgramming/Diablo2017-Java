package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleOutput extends Command {

	public TeleOutput() {
		requires(Robot.output);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

	}

	protected boolean isFinished() {
		return !DriverStation.getInstance().isOperatorControl();
	}

	protected void end() {
		Robot.output.output(0);
	}

	protected void interrupted() {
		Robot.output.output(0);
	}
}
