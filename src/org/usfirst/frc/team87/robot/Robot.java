package org.usfirst.frc.team87.robot;

import org.usfirst.frc.team87.robot.commands.NewAutoSelector;
import org.usfirst.frc.team87.robot.commands.TeleDrive;
import org.usfirst.frc.team87.robot.subsystems.DriveBase;
import org.usfirst.frc.team87.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	public static OI oi;
	public static AutonomousSelector autoselector;
	public static DriveBase drivebase;
	public static Winch winch;
	private Command autonomousCommand;
	private Command TeleOutput;
	private Command TeleDrive;
	private NewAutoSelector NewAutoSelector;  // TEMP

	@Override
	public void robotInit() {
		autoselector = new AutonomousSelector();
		drivebase = new DriveBase();
		winch = new Winch();
		oi = new OI();
		drivebase.initGyro();
		TeleDrive = new TeleDrive();
		NewAutoSelector = new NewAutoSelector();

	}

	public void dashDisplay() {
		SmartDashboard.putNumber("Gyro: ", drivebase.getGyro());
	}

	@Override
	public void disabledInit() {
		//NewAutoSelector.initialize();
		drivebase.resetGyro();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		NewAutoSelector.execute();
		autoselector.autoSelectorLogic();
		dashDisplay();
	}

	@Override
	public void autonomousInit() {
		Scheduler.getInstance().run();
		drivebase.resetGyro();
		autonomousCommand = autoselector.selectCommandGroup();
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		dashDisplay();
	}

	@Override
	public void teleopInit() {
		Scheduler.getInstance().run();
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
		dashDisplay();
	}
}
