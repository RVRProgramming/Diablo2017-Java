package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;
import org.usfirst.frc.team87.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoGearCheck extends Command {

	public AutoGearCheck() {
		requires(Robot.gearsensor);
	}

	protected void execute() {
		Robot.gearsensor.checkForGear();
	}

	protected boolean isFinished() {
		if (!RobotMap.HOLDINGGEAR) {
			Timer.delay(2);
			Robot.gearsensor.checkForGear();
			if (!RobotMap.HOLDINGGEAR) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	protected void end() {
	}
}
