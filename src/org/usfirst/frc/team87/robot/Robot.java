package org.usfirst.frc.team87.robot;

import org.usfirst.frc.team87.robot.commands.TeleDrive;
import org.usfirst.frc.team87.robot.commands.TeleOutput;
import org.usfirst.frc.team87.robot.subsystems.DriveBase;
import org.usfirst.frc.team87.robot.subsystems.GearSensor;
import org.usfirst.frc.team87.robot.subsystems.Intake;
import org.usfirst.frc.team87.robot.subsystems.Output;
import org.usfirst.frc.team87.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
	public static OI oi;
	public static AutonomousSelector autoselector;
	public static DriveBase drivebase;
	public static Winch winch;
	public static Output output;
	public static Intake intake;
	public static GearSensor gearsensor;
	private Command autonomousCommand;
	private Command TeleOutput;
	private Command TeleDrive;

	@Override
	public void robotInit() {
		oi = new OI();
		drivebase = new DriveBase();
		winch = new Winch();
		output = new Output();
		intake = new Intake();
		gearsensor = new GearSensor();
		drivebase.initGyro();
		gearsensor.ultraSetup();
		TeleOutput = new TeleOutput();
		TeleDrive = new TeleDrive();
	}

	@Override
	public void disabledInit() {
		drivebase.resetGyro();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		autoselector.autoSelectorLogic();
	}

	@Override
	public void autonomousInit() {
		drivebase.resetGyro();
		autonomousCommand = autoselector.selectCommandGroup();
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		TeleDrive.start();
		TeleOutput.start();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		oi.backwardsCheck();
	}
}
