package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleClimb extends Command {
	public TeleClimb() {
		requires(Robot.winch);
	}

	protected void execute() {
		Robot.winch.ledPower(true);
		Robot.winch.climb(Robot.oi.getWinchSpeed());
		SmartDashboard.putNumber("Winch Throttle", Robot.oi.getWinchSpeed());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void interrupted() {
		Robot.winch.climb(0);
		Robot.winch.ledPower(false);
	}
}
