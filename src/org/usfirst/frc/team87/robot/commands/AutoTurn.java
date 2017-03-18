package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command {

	public AutoTurn() {
		requires(Robot.drivebase);
	}

	protected void initialize() {
		Timer.delay(0.5);
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drivebase.drive(0, 0);
	}
}
