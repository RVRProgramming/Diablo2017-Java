package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleOutput extends Command {
	boolean toggle = false;
	boolean button = false;

	public TeleOutput() {
		requires(Robot.output);
	}

	protected void initialize() {
		Robot.output.output(0);
	}

	protected void execute() {
		if (Robot.oi.getOutput() && !button) {
			toggle = !toggle;
			button = true;
		} else if (!Robot.oi.getOutput() && button) {
			button = false;
		}
		if (toggle) {
			Robot.output.output(1);
		} else if (!toggle) {
			Robot.output.output(-1);
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void interrupted() {
		Robot.output.output(0);
	}
}
