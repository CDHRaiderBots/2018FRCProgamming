// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc2450.TimmyTurner;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc2450.TimmyTurner.commands.*;
import org.usfirst.frc2450.TimmyTurner.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	Preferences prefs;

	double startpos;
	String switchpos;
	String switchface;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	public static OI oi;
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public static DriveTrainSubsystem driveTrainSubsystem;
	public static BarClimbSubsystem barClimbSubsystem;
	public static CubeIntakeSubsystem cubeIntakeSubsystem;
	public static CubeConveyorSubsystem cubeConveyorSubsystem;
	public static CubeElevatorSubsystem cubeElevatorSubsystem;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		RobotMap.init();
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		driveTrainSubsystem = new DriveTrainSubsystem();
		barClimbSubsystem = new BarClimbSubsystem();
		cubeIntakeSubsystem = new CubeIntakeSubsystem();
		cubeConveyorSubsystem = new CubeConveyorSubsystem();
		cubeElevatorSubsystem = new CubeElevatorSubsystem();

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		// Add commands to Autonomous Sendable Chooser
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

		chooser.addDefault("AutoNothing", new AutoNothing());

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
		startCamera();
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();

		RobotMap.gyroSPI.reset();
		prefs = Preferences.getInstance();
		// switchpos = prefs.getString("switchpos", "L");
		startpos = prefs.getDouble("startpos", 1);
		switchface = prefs.getString("switchface", "F");
		SmartDashboard.putString("Auto Debug", "switchpos: " + switchpos + ", startpos: " + startpos + ", switchface: " + switchface);
//		if (gameData.length() > 0) {
//			if (gameData.charAt(0) == 'L') {
//				if (startpos == 0) {
//					if (switchface.equals("F")) {
//						autonomousCommand = (Command) new AutoPos0LS(true);
//					} else {
//						autonomousCommand = (Command) new AutoPos0LS(true);
//					}
//				}
//				if (startpos == 1) {
//					if (switchface.equals("F")) {
//						autonomousCommand = (Command) new AutoPos1LF(true);
//					} else {
//						autonomousCommand = (Command) new AutoPos1LF(true);
//					}
//				}
//				if (startpos == 2) {
//					if (switchface.equals("F")) {
//						autonomousCommand = (Command) new AutoPos2LF(true);
//					} else {
//						autonomousCommand = (Command) new AutoPos2LF(true);
//					}
//				}
//				if (startpos == 3) {
//					if (switchface.equals("F")) {
//						autonomousCommand = (Command) new AutoPos3RF(false);
//					} else {
//						autonomousCommand = (Command) new AutoPos3RF(false);
//					}
//				}
//				if (startpos == 4) {
//					if (switchface.equals("F")) {
//						autonomousCommand = (Command) new AutoPos4RS(false);
//					} else {
//						autonomousCommand = (Command) new AutoPos4RS(false);
//					}
//
//				}
//				if (startpos == 5) {
//					if (switchface.equals("F")) {
//						autonomousCommand = (Command) new DefaultAuto(true);
//					} else {
//						autonomousCommand = (Command) new DefaultAuto(true);
//					}
//				}
//				if (startpos == 6) {
//					if (switchface.equals("F")) {
//						autonomousCommand = (Command) new DefaultAuto(false);
//					} else {
//						autonomousCommand = (Command) new DefaultAuto(false);
//					}
//				}
//			} else {
//				if (startpos == 0) {
//					if (switchface.equals("F")) {
//						autonomousCommand = (Command) new AutoPos0LS(false);
//					} else {
//						autonomousCommand = (Command) new AutoPos0LS(false);
//					}
//				}
//				if (startpos == 1) {
//					if (switchface.equals("F")) {
//						autonomousCommand = (Command) new AutoPos1LF(false);
//					} else {
//						autonomousCommand = (Command) new AutoPos1LF(false);
//					}
//				}
//				if (startpos == 2) {
//					if (switchface.equals("F")) {
//						autonomousCommand = (Command) new AutoPos2RF(true);
//					} else {
//						autonomousCommand = (Command) new AutoPos2RF(true);
//					}
//				}
//				if (startpos == 3) {
//					if (switchface.equals("F")) {
//						autonomousCommand = (Command) new AutoPos3RF(true);
//					} else {
//						autonomousCommand = (Command) new AutoPos3RF(true);
//					}
//				}
//				if (startpos == 4) {
//					if (switchface.equals("F")) {
//						autonomousCommand = (Command) new AutoPos4RS(true);
//					} else {
//						autonomousCommand = (Command) new AutoPos4RS(true);
//					}
//				}
//				if (startpos == 5) {
//					if (switchface.equals("F")) {
//						autonomousCommand = (Command) new DefaultAuto(false);
//					} else {
//						autonomousCommand = (Command) new DefaultAuto(false);
//					}
//				}
//				if (startpos == 6) {
//					if (switchface.equals("F")) {
//						autonomousCommand = (Command) new DefaultAuto(true);
//					} else {
//						autonomousCommand = (Command) new DefaultAuto(true);
//					}
//				}
	if (gameData.length() > 0) {
		if (gameData.charAt(0) == 'L') {
		if(startpos == 1)
		{
			autonomousCommand = (Command) new AutoPos0LS(true);
		}
		if(startpos == 2)
		{
			autonomousCommand = (Command) new AutoPos2LF(true);
		}
		if(startpos == 3)
		{
			autonomousCommand = (Command) new AutoPos3RF(false);
		}
		if(startpos == 4)
		{
			autonomousCommand = (Command) new AutoPos4RS(false);
		}
	}
		else
		{
			if(startpos == 1)
			{
				autonomousCommand = (Command) new AutoPos0LS(false);
			}
			if(startpos == 2)
			{
				autonomousCommand = (Command) new AutoPos2RF(true);
			}
			if(startpos == 3)
			{
				autonomousCommand = (Command) new AutoPos3RF(true);
			}
			if(startpos == 4)
			{
				autonomousCommand = (Command) new AutoPos4RS(true);
			}
		}
			autonomousCommand.start();
		}
	}


	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {

		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	private boolean cameraOn = false;

	private void startCamera() {
		if (!cameraOn) {
			new Thread(() -> {

				UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
				camera.setResolution(640, 480);

				CvSink cvSink = CameraServer.getInstance().getVideo();
				CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);

				Mat source = new Mat();
				Mat output = new Mat();
				while (true) {
					cvSink.grabFrame(source);
					Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
					// source = output;
					// org.opencv.core.Core.flip(source, output, 0);
					outputStream.putFrame(output);
				}
			}).start();

			cameraOn = true;
			SmartDashboard.putString("Camera", "ON");
		}
	}
}
