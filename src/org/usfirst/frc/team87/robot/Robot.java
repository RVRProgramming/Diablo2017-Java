package org.usfirst.frc.team87.robot;

import org.usfirst.frc.team87.robot.commands.GroupDriveStraight;
import org.usfirst.frc.team87.robot.commands.TeleDrive;
import org.usfirst.frc.team87.robot.subsystems.DriveBase;
import org.usfirst.frc.team87.robot.subsystems.Winch;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	public static OI oi;
	public static DriveBase drivebase;
	public static Winch winch;
	private Command autonomousCommand;
	private Command TeleDrive;

	@Override
	public void robotInit() {
		drivebase = new DriveBase();
		winch = new Winch();
		oi = new OI();
		drivebase.initGyro();
		TeleDrive = new TeleDrive();
	}

	public void dashDisplay() {
		SmartDashboard.putNumber("Gyro: ", drivebase.getGyro());
		SmartDashboard.putNumber("Left Encode: ", drivebase.getLeftEncoder());
		SmartDashboard.putNumber("Right Encode: ", drivebase.getRightEncoder());
	}

	@Override
	public void disabledInit() {
		drivebase.resetGyro();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		dashDisplay();
	}

	@Override	
	public void autonomousInit() {
		drivebase.resetGyro();
		autonomousCommand = new GroupDriveStraight();
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
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		oi.backwardsCheck();
		dashDisplay();
	}
}
