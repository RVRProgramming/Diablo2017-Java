package org.usfirst.frc.team87.robot;

import org.usfirst.frc.team87.robot.commands.NewAutoSelector;
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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	private NewAutoSelector NewAutoSelector;

	@Override
	public void robotInit() {
		autoselector = new AutonomousSelector();
		drivebase = new DriveBase();
		winch = new Winch();
		output = new Output();
		intake = new Intake();
		gearsensor = new GearSensor();
		oi = new OI();
		drivebase.initGyro();
		gearsensor.ultraSetup();
		TeleOutput = new TeleOutput();
		TeleDrive = new TeleDrive();
		NewAutoSelector = new NewAutoSelector();  // TEMP
	}

	public void dashDisplay() {
		SmartDashboard.putNumber("Gyro: ", drivebase.getGyro());
		SmartDashboard.putNumber("Ultra: ", gearsensor.getUltra());
		SmartDashboard.putNumber("Left Encode: ", drivebase.getLeftEncoder());
		SmartDashboard.putNumber("Right Encode: ", drivebase.getRightEncoder());
	}

	@Override
	public void disabledInit() {
		NewAutoSelector.initialize();
		drivebase.resetGyro();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		NewAutoSelector.execute();
		//autoselector.autoSelectorLogic();
		dashDisplay();
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
		dashDisplay();
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
		dashDisplay();
	}
}
