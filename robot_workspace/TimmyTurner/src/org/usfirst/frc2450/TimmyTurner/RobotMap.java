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

import org.usfirst.frc2450.TimmyTurner.commands.CubeEject;
import org.usfirst.frc2450.TimmyTurner.subsystems.ADXRS453Gyro;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
    public static CubeEject eject;
    public static double turnTime = 0.5;
	public static boolean chuteFront = false;
	public static double driveTime = 1.6;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Encoder driveTrainSubsystemLeftEncoder;
    public static Encoder driveTrainSubsystemRightEncoder;
    public static SpeedController driveTrainSubsystemLeftMotor;
    public static SpeedController driveTrainSubsystemRightMotor;
    public static DifferentialDrive driveTrainSubsystemRobotDrive2;
    public static SpeedController barClimbSubsystemBarClimbRelease;
    public static Relay barClimbSubsystemClimbLoaderMotor;
    public static SpeedController cubeIntakeSubsystemLeftIntakeMotor;
    public static SpeedController cubeIntakeSubsystemRightIntakeMotor;
    public static RobotDrive cubeIntakeSubsystemRobotIntake2;
    public static SpeedController cubeConveyorSubsystemConveyorMotor;
    public static SpeedController cubeElevatorSubsystemElevatorMotor;
    public static DigitalInput cubeElevatorSubsystemElevatorLimitSwitch;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static ADXRS453Gyro gyroSPI;
    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainSubsystemLeftEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrainSubsystem", "LeftEncoder", driveTrainSubsystemLeftEncoder);
        driveTrainSubsystemLeftEncoder.setDistancePerPulse(-0.0721);
        driveTrainSubsystemLeftEncoder.setPIDSourceType(PIDSourceType.kRate);
        driveTrainSubsystemRightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrainSubsystem", "RightEncoder", driveTrainSubsystemRightEncoder);
        driveTrainSubsystemRightEncoder.setDistancePerPulse(1.0);
        driveTrainSubsystemRightEncoder.setPIDSourceType(PIDSourceType.kRate);
        driveTrainSubsystemLeftMotor = new Spark(0);
        LiveWindow.addActuator("DriveTrainSubsystem", "LeftMotor", (Spark) driveTrainSubsystemLeftMotor);
        driveTrainSubsystemLeftMotor.setInverted(false);
        driveTrainSubsystemRightMotor = new Spark(1);
        LiveWindow.addActuator("DriveTrainSubsystem", "RightMotor", (Spark) driveTrainSubsystemRightMotor);
        driveTrainSubsystemRightMotor.setInverted(false);
        driveTrainSubsystemRobotDrive2 = new DifferentialDrive(driveTrainSubsystemLeftMotor, driveTrainSubsystemRightMotor);
        LiveWindow.addActuator("DriveTrainSubsystem", "Robot Drive 2", driveTrainSubsystemRobotDrive2);
        driveTrainSubsystemRobotDrive2.setSafetyEnabled(true);
        driveTrainSubsystemRobotDrive2.setExpiration(0.1);
        driveTrainSubsystemRobotDrive2.setMaxOutput(1.0);

        barClimbSubsystemBarClimbRelease = new VictorSP(7);
        LiveWindow.addActuator("BarClimbSubsystem", "BarClimbRelease", (VictorSP) barClimbSubsystemBarClimbRelease);
        barClimbSubsystemBarClimbRelease.setInverted(false);
        barClimbSubsystemClimbLoaderMotor = new Relay(0);
        LiveWindow.addActuator("BarClimbSubsystem", "ClimbLoaderMotor", barClimbSubsystemClimbLoaderMotor);
        
        cubeIntakeSubsystemLeftIntakeMotor = new Spark(2);
        LiveWindow.addActuator("CubeIntakeSubsystem", "LeftIntakeMotor", (Spark) cubeIntakeSubsystemLeftIntakeMotor);
        cubeIntakeSubsystemLeftIntakeMotor.setInverted(false);
        cubeIntakeSubsystemRightIntakeMotor = new Spark(3);
        LiveWindow.addActuator("CubeIntakeSubsystem", "RightIntakeMotor", (Spark) cubeIntakeSubsystemRightIntakeMotor);
        cubeIntakeSubsystemRightIntakeMotor.setInverted(false);
        cubeIntakeSubsystemRobotIntake2 = new RobotDrive(cubeIntakeSubsystemLeftIntakeMotor, cubeIntakeSubsystemRightIntakeMotor);
        
        cubeIntakeSubsystemRobotIntake2.setSafetyEnabled(true);
        cubeIntakeSubsystemRobotIntake2.setExpiration(0.1);
        cubeIntakeSubsystemRobotIntake2.setSensitivity(0.5);
        cubeIntakeSubsystemRobotIntake2.setMaxOutput(1.0);

        cubeConveyorSubsystemConveyorMotor = new Spark(4);
        LiveWindow.addActuator("CubeConveyorSubsystem", "ConveyorMotor", (Spark) cubeConveyorSubsystemConveyorMotor);
        cubeConveyorSubsystemConveyorMotor.setInverted(false);
        cubeElevatorSubsystemElevatorMotor = new Spark(5);
        LiveWindow.addActuator("CubeElevatorSubsystem", "ElevatorMotor", (Spark) cubeElevatorSubsystemElevatorMotor);
        cubeElevatorSubsystemElevatorMotor.setInverted(false);
        cubeElevatorSubsystemElevatorLimitSwitch = new DigitalInput(9);
        LiveWindow.addSensor("CubeElevatorSubsystem", "ElevatorLimitSwitch", cubeElevatorSubsystemElevatorLimitSwitch);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        gyroSPI = new ADXRS453Gyro();
        gyroSPI.startThread();
        
    }
}
