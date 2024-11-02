package com.example;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListenerClass implements ITestListener {
    @Override
    public void onFinish(ITestContext arg0) {
        System.out.println("Test Finished");
    }

    @Override
    public void onStart(ITestContext arg0) {
        System.out.println("Test started");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("The name of the testcase failed is :" + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("The name of the testcase Skipped is :" + result.getName());

    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(result.getName() + " test case started");

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("The name of the testcase passed is :" + result.getName());

    }

}
