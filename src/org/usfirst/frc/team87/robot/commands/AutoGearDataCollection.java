package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoGearDataCollection extends Command {

	public AutoGearDataCollection() {
		requires(Robot.gearsensor);
	}

	protected void execute() {
		Robot.gearsensor.update();
	}

	protected boolean isFinished() {
		return false;
	}
}
