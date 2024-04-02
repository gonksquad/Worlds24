package org.firstinspires.ftc.teamcode;

import static android.os.SystemClock.sleep;

import android.drm.DrmStore;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.TankDrive;
import org.firstinspires.ftc.teamcode.ThreeDeadWheelLocalizer;
import org.firstinspires.ftc.teamcode.TwoDeadWheelLocalizer;

import org.firstinspires.ftc.teamcode.Hardware;


@Autonomous
public class TrajTest extends LinearOpMode {

    Hardware hardware;
    MecanumDrive drive;
    Action trajectory1;
    Action trajectory2;
    Action trajectory3;

    @Override
    public void runOpMode() {
        waitForStart();
        Hardware hardware = new Hardware(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(12, 60, Math.toRadians(270)));

        Pose2d pose1 = new Pose2d(10, 35, Math.toRadians(180));
        Pose2d pose2 = new Pose2d(40, 35, Math.toRadians(180));
        Pose2d pose3 = new Pose2d(60, 60, Math.toRadians(180));

        trajectory1 = drive.actionBuilder(drive.pose)
                .splineTo(new Vector2d(10, 35), Math.toRadians(180))
                .build();

        trajectory2 = drive.actionBuilder(pose1)
                .strafeTo(new Vector2d(40, 35))
                .build();

        trajectory3 = drive.actionBuilder(pose2)
                .strafeTo(new Vector2d(45, 60))
                .strafeTo(new Vector2d(60, 60))
                .build();

       Actions.runBlocking(
               trajectory1
       );

        hardware.intake.setPower(1);
        sleep(1000);
        hardware.intake.setPower(-1);
        sleep(500);
        hardware.intake.setPower(1);
        sleep(100000);
        hardware.intake.setPower(0);

//        Actions.runBlocking(
//                trajectory2
//        );

//        hardware.leftSlide.setPower(0.75);
//        hardware.rightSlide.setPower(0.75);
//        sleep(200);
//
//        hardware.boxRotation.setPosition(0);
//        sleep(500);
//
//        hardware.leftSlide.setPower(0);
//        hardware.rightSlide.setPower(0);
//        sleep(500);
//
//        hardware.door.setPosition(0);
//        sleep(2000);

//        Actions.runBlocking(
//                trajectory3
//        );
    }
}