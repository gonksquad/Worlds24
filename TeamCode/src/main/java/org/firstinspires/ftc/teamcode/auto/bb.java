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
public class bb extends OpMode {

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
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(11, 60, Math.toRadians(270)));

        Pose2d pose1 = new Pose2d(34, 32, Math.toRadians(180));
        Pose2d pose2 = new Pose2d(52, 43, Math.toRadians(180));

        Pose2d pose1r = new Pose2d(12, 33, Math.toRadians(180));
        Pose2d pose2r = new Pose2d(50, 29, Math.toRadians(180));

        Pose2d pose1c = new Pose2d(16, 34, Math.toRadians(270));
        Pose2d pose2c = new Pose2d(50, 35, Math.toRadians(180));

        l1 = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(34, 32))
                .turnTo(Math.toRadians(180))
                .build();

        l2 = drive.actionBuilder(pose1)
                .strafeTo(new Vector2d(52, 43))
                .build();

        l3 = drive.actionBuilder(pose2)
                .strafeTo(new Vector2d(45, 60))
                .strafeTo(new Vector2d(60, 60))
                .build();



        r1 = drive.actionBuilder(drive.pose)
                .splineTo(new Vector2d(12, 33), Math.toRadians(180))
                .build();

        r2 = drive.actionBuilder(pose1r)
                .strafeTo(new Vector2d(50, 29))
                .build();

        r3 = drive.actionBuilder(pose2r)
                .strafeTo(new Vector2d(45, 60))
                .strafeTo(new Vector2d(60, 60))
                .build();



        c1 = drive.actionBuilder(drive.pose)
                .splineTo(new Vector2d(16, 34), Math.toRadians(270))
                .build();

        c2 = drive.actionBuilder(pose1c)
                .strafeTo(new Vector2d(50, 35))
                .turn(Math.toRadians(-90))
                .build();

        c3 = drive.actionBuilder(pose2c)
                .strafeTo(new Vector2d(45, 60))
                .strafeTo(new Vector2d(60, 60))
                .build();

        if (Objects.equals(position, "left")) {
            Actions.runBlocking(
                    l1
            );
            hardware.intake.setPower(.60);
            sleep(3000);
            hardware.intake.setPower(0);
            hardware.leftSlide.setPower(0.25);
            Actions.runBlocking(
                    l2
            );
            hardware.leftSlide.setPower(1);
            //hardware.rightSlide.setPower(0.75);
            sleep(200);
            hardware.boxRotation.setPosition(hardware.DROP_ANGLE);
            sleep(100);
            hardware.leftSlide.setPower(0.15);
            //hardware.rightSlide.setPower(0);
            sleep(500);
            hardware.door.setPosition(0);
            sleep(200);
            hardware.leftSlide.setPower(0.95);
            sleep(200);
            hardware.leftSlide.setPower(0.15);
            sleep(2000);

            hardware.boxRotation.setPosition(hardware.INTAKE_ANGLE);
            sleep(1000);
            hardware.leftSlide.setPower(-0.25);
            //hardware.rightSlide.setPower(-0.25);

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
            hardware.leftSlide.setPower(0.25);
            Actions.runBlocking(
                    c2
            );
            hardware.leftSlide.setPower(0.85);
            //hardware.rightSlide.setPower(0.75);
            sleep(200);
            hardware.boxRotation.setPosition(hardware.DROP_ANGLE);
            sleep(100);
            hardware.leftSlide.setPower(0.15);
            //hardware.rightSlide.setPower(0);
            sleep(500);
            hardware.door.setPosition(0);
            sleep(200);
            hardware.leftSlide.setPower(0.95);
            sleep(200);
            hardware.leftSlide.setPower(0.15);
            sleep(2000);

            hardware.boxRotation.setPosition(hardware.INTAKE_ANGLE);
            sleep(1000);
            hardware.leftSlide.setPower(-0.25);
            //hardware.rightSlide.setPower(-0.25);

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
            hardware.leftSlide.setPower(0.25);
            Actions.runBlocking(
                    r2
            );
            hardware.leftSlide.setPower(1);
            //hardware.rightSlide.setPower(0.75);
            sleep(200);
            hardware.boxRotation.setPosition(hardware.DROP_ANGLE);
            sleep(100);
            hardware.leftSlide.setPower(0.15);
            //hardware.rightSlide.setPower(0);
            sleep(500);
            hardware.door.setPosition(0);
            sleep(200);
            hardware.leftSlide.setPower(0.95);
            sleep(200);
            hardware.leftSlide.setPower(0.15);
            sleep(2000);

            hardware.boxRotation.setPosition(hardware.INTAKE_ANGLE);
            sleep(1000);
            hardware.leftSlide.setPower(-0.25);
            //hardware.rightSlide.setPower(-0.25);

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

            Rect centerRect = new Rect(290, 30, 50, 50);
            Rect rightRect = new Rect(1, 1, 30, 50);
            Rect leftRect = new Rect(609, 1, 30, 50);

            input.copyTo(outPut);
            Imgproc.rectangle(outPut, leftRect, rectColor, 2);
            Imgproc.rectangle(outPut, centerRect, rectColor, 2);
            Imgproc.rectangle(outPut, rightRect, rectColor, 2);

            leftCrop = YCbCr.submat(leftRect);
            centerCrop = YCbCr.submat(centerRect);
            rightCrop = YCbCr.submat(rightRect);

            Core.extractChannel(leftCrop, leftCrop, 2);
            Core.extractChannel(centerCrop, centerCrop, 2);
            Core.extractChannel(rightCrop, rightCrop, 2);

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
