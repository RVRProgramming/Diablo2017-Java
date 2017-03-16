package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleClimb extends Command {
	public TeleClimb() {
		super("Climb");
		requires(Robot.winch);
	}

	protected void execute() {
		Robot.winch.climb(Robot.oi.getWinchSpeed());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void interrupted() {
		Robot.winch.climb(0);
	}
}
