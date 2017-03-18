package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoOutput extends Command {

	public AutoOutput() {
		requires(Robot.output);
	}

	protected void execute() {
		Robot.output.output(1);
	}

	protected boolean isFinished() {
		return Robot.output.getTopLimit();
	}

	protected void end() {
		Robot.output.output(0);
	}
}
