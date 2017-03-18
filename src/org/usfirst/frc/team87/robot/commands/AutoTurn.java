package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;
import org.usfirst.frc.team87.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoTurn extends Command {
	private double speed;
	private PIDController angleController = new PIDController(0.5, 20, 3, Robot.drivebase.getSourceGyro(), speed -> this.speed = speed);

	public AutoTurn(double angle) {
		requires(Robot.drivebase);
		angleController.setAbsoluteTolerance(RobotMap.ANGLETOLERANCE);
		angleController.setOutputRange(-1, 1);
		angleController.setSetpoint(angle);
	}

	protected void initialize() {
		Timer.delay(0.5);
		Robot.drivebase.resetGyro();
		angleController.enable();
	}

	protected void execute() {
		Robot.drivebase.drive(speed, -speed);
	}

	protected boolean isFinished() {
		return angleController.onTarget();
	}

	protected void end() {
		Robot.drivebase.drive(0, 0);
		angleController.disable();
	}
}
