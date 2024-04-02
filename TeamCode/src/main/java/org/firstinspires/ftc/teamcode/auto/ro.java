package org.firstinspires.ftc.teamcode.auto;

import static android.os.SystemClock.sleep;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Hardware;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.Objects;


// BATTERY AT MAX VOLTAGE


@Autonomous
public class ro extends OpMode {

    OpenCvWebcam webcam1 = null;
    String position = "none";
    String startposition = "none";
    Action l1;
    Action l2;
    Action l3;

    Action c1;
    Action c2;
    Action c3;

    Action r1;
    Action r2;
    Action r3;

    @Override
    public void init() {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id",
                hardwareMap.appContext.getPackageName());
        webcam1 = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"),
                cameraMonitorViewId);

        webcam1.setPipeline(new examplePipeline());

        webcam1.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            public void onOpened() {
                webcam1.startStreaming(640, 360, OpenCvCameraRotation.UPRIGHT);

            }

            public void onError(int errorCode) {
                telemetry.addData("Error", errorCode);
                telemetry.update();
            }
        });
    }

    @Override
    public void loop() {
        Hardware hardware = new Hardware(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-36, -60, Math.toRadians(90)));

        Pose2d pose2 = new Pose2d(-33.9, -12, Math.toRadians(180));
        Pose2d pose1 = new Pose2d(-34, -36, Math.toRadians(180));

        Pose2d pose1r = new Pose2d(-34, -36, Math.toRadians(0));
        Pose2d pose2r = new Pose2d(-12, -12, Math.toRadians(0));

        Pose2d pose1c = new Pose2d(-34, -36, Math.toRadians(90));
        Pose2d pose2c = new Pose2d(-12, -12, Math.toRadians(180));

        l1 = drive.actionBuilder(drive.pose)
                .splineTo(new Vector2d(-40, -31), Math.toRadians(180))
                .lineToX(-34)
                .build();

        l2 = drive.actionBuilder(pose1)
                .waitSeconds(2)
                .strafeTo(new Vector2d(-34, -12))
                .strafeTo(new Vector2d(-33.9, -12))
                .splineTo(new Vector2d(-12,-12), Math.toRadians(0))
                .splineTo(new Vector2d(24,-12), Math.toRadians(0))
                .splineTo(new Vector2d(45,-32), Math.toRadians(0))
                .build();

        l3 = drive.actionBuilder(pose2)
                .build();



        r1 = drive.actionBuilder(drive.pose)
                .splineTo(new Vector2d(-34, -36), Math.toRadians(0))
                .build();

        r2 = drive.actionBuilder(pose1r)
                .strafeTo(new Vector2d(-34, -12))
                .strafeTo(new Vector2d(-33.9, -12))
                .splineTo(new Vector2d(-12,-10), Math.toRadians(0))
                .splineTo(new Vector2d(30,-10), Math.toRadians(0))
                .splineTo(new Vector2d(40,-36), Math.toRadians(180))
                .build();

        r3 = drive.actionBuilder(pose2r)
                .build();



        c1 = drive.actionBuilder(drive.pose)
                .splineTo(new Vector2d(-33, -36), Math.toRadians(90))
                .build();

        c2 = drive.actionBuilder(pose1c)
                .strafeTo(new Vector2d(-50, -40))
                .strafeTo(new Vector2d(-50, -41))
                .lineToY(-24)
                .splineTo(new Vector2d(-12,-12), Math.toRadians(0))
                .splineTo(new Vector2d(30,-12), Math.toRadians(0))
                .splineTo(new Vector2d(40,-36), Math.toRadians(180))
                .strafeTo(new Vector2d(45, -33))
                .build();

        c3 = drive.actionBuilder(pose2c)
                .build();

        if (Objects.equals(position, "left")) {
            Actions.runBlocking(
                    l1
            );
            hardware.intake.setPower(.60);
            sleep(3000);
            hardware.intake.setPower(0);
            Actions.runBlocking(
                    l2
            );
            hardware.leftSlide.setPower(0.75);
            hardware.rightSlide.setPower(0.75);
            sleep(200);
            hardware.boxRotation.setPosition(0);
            sleep(500);
            hardware.leftSlide.setPower(0);
            hardware.rightSlide.setPower(0);
            sleep(500);
            hardware.door.setPosition(0);
            sleep(2000);

            hardware.boxRotation.setPosition(1);
            sleep(1000);
            hardware.leftSlide.setPower(-0.25);
//            hardware.rightSlide.setPower(-0.25);

            Actions.runBlocking(
                   l3
            );
        }
        else if (Objects.equals(position, "center")) {
            Actions.runBlocking(
                    c1
            );
            hardware.intake.setPower(.60);
            sleep(3000);
            hardware.intake.setPower(0);
            Actions.runBlocking(
                    c2
            );
            hardware.leftSlide.setPower(0.75);
            hardware.rightSlide.setPower(0.75);
            sleep(200);
            hardware.boxRotation.setPosition(0);
            sleep(500);
            hardware.leftSlide.setPower(0);
            hardware.rightSlide.setPower(0);
            sleep(500);
            hardware.door.setPosition(0);
            sleep(2000);

            hardware.boxRotation.setPosition(1);
            sleep(1000);
            hardware.leftSlide.setPower(-0.25);
//            hardware.rightSlide.setPower(-0.25);

            Actions.runBlocking(
                    c3
            );
        } else if (Objects.equals(position, "right")) {
            Actions.runBlocking(
                    r1
            );
            hardware.intake.setPower(.60);
            sleep(3000);
            hardware.intake.setPower(0);
            Actions.runBlocking(
                    r2
            );
            hardware.leftSlide.setPower(0.75);
            hardware.rightSlide.setPower(0.75);
            sleep(200);
            hardware.boxRotation.setPosition(0);
            sleep(500);
            hardware.leftSlide.setPower(0);
            hardware.rightSlide.setPower(0);
            sleep(500);
            hardware.door.setPosition(0);
            sleep(2000);

            hardware.boxRotation.setPosition(1);
            sleep(1000);
            hardware.leftSlide.setPower(-0.25);
//            hardware.rightSlide.setPower(-0.25);

            Actions.runBlocking(
                    r3
            );
        }
        requestOpModeStop();
    }

    class examplePipeline extends OpenCvPipeline {
        Mat YCbCr = new Mat();
        Mat leftCrop;
        Mat centerCrop;
        Mat rightCrop;
        double leftavgfin;
        double centeravgfin;
        double rightavgfin;
        Mat outPut = new Mat();
        Scalar rectColor = new Scalar(255.0, 0.0, 0.0);

        public Mat processFrame(Mat input) {
            Imgproc.cvtColor(input, YCbCr, Imgproc.COLOR_RGB2YCrCb);
            telemetry.addLine("pipeline running :)");

            Rect centerRect = new Rect(240, 90, 100, 90);
            Rect leftRect = new Rect(1, 80, 80, 100);
            Rect rightRect = new Rect(540, 90, 99, 100);

            input.copyTo(outPut);
            Imgproc.rectangle(outPut, leftRect, rectColor, 2);
            Imgproc.rectangle(outPut, centerRect, rectColor, 2);
            Imgproc.rectangle(outPut, rightRect, rectColor, 2);

            leftCrop = YCbCr.submat(leftRect);
            centerCrop = YCbCr.submat(centerRect);
            rightCrop = YCbCr.submat(rightRect);

            Core.extractChannel(leftCrop, leftCrop, 1);
            Core.extractChannel(centerCrop, centerCrop, 1);
            Core.extractChannel(rightCrop, rightCrop, 1);

            Scalar leftavg = Core.mean(leftCrop);
            Scalar centeravg = Core.mean(centerCrop);
            Scalar rightavg = Core.mean(rightCrop);

            leftavgfin = leftavg.val[0];
            centeravgfin = centeravg.val[0];
            rightavgfin = rightavg.val[0];

            if (leftavgfin > rightavgfin && leftavgfin > centeravgfin) {
                telemetry.addLine("Left");
                position = "left";
            }
            if (rightavgfin > centeravgfin && rightavgfin > leftavgfin) {
                telemetry.addLine("Right");
                position = "right";
            }
            if (centeravgfin > rightavgfin && centeravgfin > leftavgfin) {
                telemetry.addLine("Center :)");
                position = "center";
            }

            return (outPut);
        }
    }
}
